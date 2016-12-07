package com.mozi.chintms.common.page;

/**
 * SQLServerDialect.java
 * 
 * @author wuhanhua
 * @version 1.0
 * */
public class SQLServerDialect implements Dialect {

	protected static final String SQL_END_DELIMITER = ";";


	public String getPaginationSql(String sql, int pageNo, int pageSize) {
		return "select top " + pageSize + " from (" + sql + ") t where t.id not in (select top " + (pageNo - 1) * pageSize + " t1.id from (" + sql + ") t1)";
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer(sql.length() + 20).append(trim(sql)).append(hasOffset ? " limit ?,?" : " limit ?").append(SQL_END_DELIMITER).toString();
	}

	public String getLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		if (offset > 0) {
			sb.append(" limit ").append(offset).append(',').append(limit).append(SQL_END_DELIMITER);
		} else {
			sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
		}
		return sb.toString();
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	private String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
		}
		return sql;
	}
	
	public static void main(String[] args) {
		System.out.println(new SQLServerDialect().getPaginationSql("select * from t_company", 2,10));
	}
}
