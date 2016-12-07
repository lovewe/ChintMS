package com.mozi.chintms.common.page;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.mozi.chintms.common.BaseDomain;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class OffsetLimitInterceptor implements Interceptor {

	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	void processIntercept(final Object[] queryArgs) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, SQLException {
		// queryArgs = query(MappedStatement ms, Object parameter, RowBounds
		// rowBounds, ResultHandler resultHandler)

		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		if (!(parameter instanceof BaseDomain)) {
			return;
		}
		BaseDomain domain = (BaseDomain) parameter;
		if (domain.isUsePager()) {
			int page = domain.getPage(); // 当前页
			int limit = domain.getRows();
			int offset = (page - 1) * limit;
			// final RowBounds rowBounds = (RowBounds)
			// queryArgs[ROWBOUNDS_INDEX];
			// int offset = rowBounds.getOffset();
			// int limit = rowBounds.getLimit();

			if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {

				BoundSql boundSql = ms.getBoundSql(parameter);

				String sql = boundSql.getSql().trim();
				/*
				 * String sortField = domain.getSort();
				 * 
				 * if (!"".equals(sortField) && !"seq".equals(sortField)) { try
				 * { Field field =
				 * domain.getClass().getDeclaredField(sortField); if
				 * (field.getType() == String.class) { sql = sql +
				 * " ORDER BY CONVERT(" + domain.getSort() + " USING gbk) " +
				 * domain.getOrder() + " "; } else { sql = sql + " ORDER BY " +
				 * domain.getSort() + " " + domain.getOrder(); } } catch
				 * (Exception e) {
				 * 
				 * } }
				 */

				if (dialect.supportsLimitOffset()) {
					sql = dialect.getLimitString(sql, offset, limit);
					offset = RowBounds.NO_ROW_OFFSET;
				} else {
					sql = dialect.getLimitString(sql, 0, limit);
				}
				limit = RowBounds.NO_ROW_LIMIT;

				Map<Integer, Integer> position = SqlUtil.getQuestionMark(sql);
				List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				List<ParameterMapping> parameterMappings2 = boundSql.getParameterMappings();
				if (parameterMappings != null) {
					// Configuration configuration =
					// mappedStatement.getConfiguration();
					for (int i = parameterMappings.size() - 1; i >= 0; i--) {
						ParameterMapping parameterMapping = parameterMappings.get(i);
						if (parameterMapping.getMode() != ParameterMode.OUT) {
							Object value;
							value = "";
							String propertyName = parameterMapping.getProperty();
							PropertyTokenizer prop = new PropertyTokenizer(propertyName);
							if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
								value = boundSql.getAdditionalParameter(prop.getName());
								// if (value != null) {
								// value =
								// configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
								// }
								Integer p = position.get(i);
								String temp = sql;
								if (value == null) {
									value = "";
								}
								sql = temp.substring(0, p) + value.toString() + temp.substring(p + 1, temp.length());
								parameterMappings2.remove(i);
							}

						}
					}
				}

				queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
				BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings2, boundSql.getParameterObject());
				MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
				queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			}
		}
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		String p = StringUtils.join(ms.getKeyProperties(), ",");
		builder.keyProperty(p);
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String dialectClass = new PropertiesHelper(properties).getRequiredString("dialectClass");
		try {
			dialect = (Dialect) Class.forName(dialectClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
		}
		System.out.println(OffsetLimitInterceptor.class.getSimpleName() + ".dialect=" + dialectClass);
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.
	 * DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}

					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					// typeHandler.setParameter(ps, i + 1, value,
					// parameterMapping.getJdbcType());
				}
			}
		}
	}
}
