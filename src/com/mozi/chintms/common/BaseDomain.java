package com.mozi.chintms.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.mozi.chintms.common.utils.BeanUtil;
import com.mozi.chintms.common.utils.DateUtil;

/**
 * domain父类，放置公用属性<br>
 * 
 * @author wuhanhua
 */
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = -5851519304147570140L;

	// ============================================================================

	/** 页号 */
	private int page = 1;
	/** 每页条数 */
	private int rows = 10;

	/** 条目序号 */
	private int sn;

	/** 排序字段 */
	private String sort;
	/** 排序类型 升序-asc 降序-desc */
	private String order;

	/** 是否使用分页 */
	private boolean usePager = false;

	// ============================================================================

	/** 同步操作的错误信息 */
	private String errMsg;

	/** 辅助字段 */
	private String param;

	/** 数组辅助参数 */
	protected String[] delItems;

	// ============================================================================

	/** 查询起始日期 */
	private String beginDate;
	/** 查询截止日期 */
	private String endDate;
	/** 查询起始金额 */
	private double beginAmt;
	/** 查询截止金额 */
	private double endAmt;

	// ============================================================================

	/** 日期统计口径 0日 1周 2月 3季度 4年 */
	private int statisDateType;

	// ============================================================================

	/** 客户端版本号 */
	private String clientVer;

	// ============================================================================

	/**
	 * 处理日期参数
	 */
	public void dealDataParams() {
		if (this.getBeginDate() != null) {
			this.setBeginDate(DateUtil.getDateStr(DateUtil.getUtilDate(this.getBeginDate(), "yyyy-MM-dd"), "yyyyMMdd"));
		}
		if (this.getEndDate() != null) {
			this.setEndDate(DateUtil.getDateStr(DateUtil.getUtilDate(this.getEndDate(), "yyyy-MM-dd"), "yyyyMMdd"));
		}
		if (this.getBeginDate() != null && this.getBeginDate().equals(this.getEndDate())) {
			this.setEndDate(DateUtil.getDateStr(DateUtil.createByDays(DateUtil.getUtilDate(this.getEndDate(), "yyyyMMdd"), 1), "yyyyMMdd"));
		}
		if (this.getBeginDate() == null && this.getEndDate() == null) {
			this.setBeginDate(DateUtil.getDateStr(new Date(), "yyyyMMdd"));
			this.setEndDate(DateUtil.getDateStr(new Date(), "yyyyMMdd"));
		}
	}

	// 反射获取所有有值的属性，并组装
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Field[] fields = BeanUtil.getAllDeclaredFields(this.getClass());
		for (Field field : fields) {
			Object o = BeanUtil.getProperty(this, field.getName());
			if (o != null) {
				sb.append(field.getName() + "=[" + o + "];");
			}
		}
		return sb.toString();
	}

	// ============================================================================

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String[] getDelItems() {
		return delItems;
	}

	public void setDelItems(String[] delItems) {
		this.delItems = delItems;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getBeginAmt() {
		return beginAmt;
	}

	public void setBeginAmt(double beginAmt) {
		this.beginAmt = beginAmt;
	}

	public double getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(double endAmt) {
		this.endAmt = endAmt;
	}

	public int getStatisDateType() {
		return statisDateType;
	}

	public void setStatisDateType(int statisDateType) {
		this.statisDateType = statisDateType;
	}

	public String getClientVer() {
		return clientVer;
	}

	public void setClientVer(String clientVer) {
		this.clientVer = clientVer;
	}

	public boolean isUsePager() {
		return usePager;
	}

	public void setUsePager(boolean usePager) {
		this.usePager = usePager;
	}
}
