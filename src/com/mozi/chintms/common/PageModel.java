package com.mozi.chintms.common;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 分页数据模型
 * 
 * @author wuhanhua
 */
public class PageModel<T extends BaseDomain> {

	/** 页号 */
	@JSONField(name = "page")
	private int page;

	/** 每页条数 */
	@JSONField(name = "pagesize")
	private int pagesize;

	/** 记录数 */
	@JSONField(name = "total")
	private int total;

	/** 结果集 */
	@JSONField(name = "rows")
	private List<T> rows;
	
	/** 总页数 **/
	@JSONField(name = "totalPage")
	private int totalPage;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/** 构造分页数据 */
	public PageModel(int totalRecords, List<T> list, BaseDomain sqlParam) {
		this.page = sqlParam.getPage();
		this.total = totalRecords;
		this.rows = list;
		this.pagesize = sqlParam.getRows();
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
}
