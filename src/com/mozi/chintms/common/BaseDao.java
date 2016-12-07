package com.mozi.chintms.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 持久层父类<br>
 * 1.放置批量处理公共方法 
 * 2.增删改查通用方法统一命名
 * 
 * @author wuhanhua
 *
 */
//@Component
public abstract class BaseDao<D extends BaseDomain> extends SqlSessionDaoSupport {

	protected final Logger logger_sys = LoggerFactory.getLogger("sys_info");
	protected final Logger logger_error = LoggerFactory.getLogger("error_stack");

	@Resource
	private SqlSessionFactory sqlSessionFactory;

	/** 批量提交的条数 */
	//@Value("${batch_deal_num}") TODO
	private int BATCH_DEAL_NUM = 100;

	@PostConstruct
	public void setSqlSessionFactory() {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 批量插入
	 * 
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchInsert(String statement, List<D> list) {
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++) {
			batchSession.insert(statement, list.get(i));
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.flushStatements();
			}
		}

		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	/**
	 * 批量更新
	 * 
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchUpdate(String statement, List<D> list) {
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++) {
			batchSession.update(statement, list.get(i));
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	/**
	 * 批量删除
	 * 
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchDelete(String statement, List<?> list) {
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++) {
			batchSession.delete(statement, list.get(i));
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	/**
	 * 插入
	 * 
	 * @param model
	 */
	public abstract int insert(D domain);

	/**
	 * 根据表主键进行更新，只有非空的项目才更新
	 * 
	 * @param model
	 */
	public abstract int updateByPrimaryKeySelective(D domain);

	/**
	 * 根据表主键进行更新，空的项目数据库字段为被更新成NULL
	 * 
	 * @param model
	 */
	public abstract int updateByPrimaryKey(D domain);

	/**
	 * 更新
	 * 
	 * @param model
	 */
	public abstract int update(D domain);
	/**
	 * 根据表主键进行删除
	 * 
	 * @param model
	 */
	public abstract int deleteByPrimaryKey(D domain);
	/**
	 * 删除
	 * 
	 * @param model
	 */
	public abstract int delete(D domain);

	/**
	 * 查询单个实体
	 * 
	 * @param model
	 */
	public abstract D selectOne(D domain);

	/**
	 * 查询实体列表
	 * 
	 * @param model
	 */
	public abstract List<D> selectList(D domain);

	/**
	 * 查询实体分页列表
	 * 
	 * @param model
	 */
	public abstract PageModel<D> selectPage(D domain);
}
