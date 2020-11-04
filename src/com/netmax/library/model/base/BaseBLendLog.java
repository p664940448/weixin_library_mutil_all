package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBLendLog<M extends BaseBLendLog<M>> extends Model<M> implements IBean {

	public void setStartNo(java.math.BigDecimal startNo) {
		set("start_no", startNo);
	}

	public java.math.BigDecimal getStartNo() {
		return get("start_no");
	}

	public void setEndNo(java.math.BigDecimal endNo) {
		set("end_no", endNo);
	}

	public java.math.BigDecimal getEndNo() {
		return get("end_no");
	}

	public void setDealFlag(java.lang.String dealFlag) {
		set("deal_flag", dealFlag);
	}

	public java.lang.String getDealFlag() {
		return get("deal_flag");
	}

	public void setDealDate(java.util.Date dealDate) {
		set("deal_date", dealDate);
	}

	public java.util.Date getDealDate() {
		return get("deal_date");
	}

	public void setDealMsg(java.lang.String dealMsg) {
		set("deal_msg", dealMsg);
	}

	public java.lang.String getDealMsg() {
		return get("deal_msg");
	}

}
