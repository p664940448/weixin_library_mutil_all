package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDmBkaddr<M extends BaseDmBkaddr<M>> extends Model<M> implements IBean {

	public void setCode(java.lang.String code) {
		set("code", code);
	}

	public java.lang.String getCode() {
		return get("code");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setSysId(java.lang.String sysId) {
		set("sys_id", sysId);
	}

	public java.lang.String getSysId() {
		return get("sys_id");
	}

	public void setBill(java.math.BigDecimal bill) {
		set("bill", bill);
	}

	public java.math.BigDecimal getBill() {
		return get("bill");
	}

	public void setPrice(java.math.BigDecimal price) {
		set("price", price);
	}

	public java.math.BigDecimal getPrice() {
		return get("price");
	}

	public void setFlag(java.math.BigDecimal flag) {
		set("flag", flag);
	}

	public java.math.BigDecimal getFlag() {
		return get("flag");
	}

	public void setFree(java.math.BigDecimal free) {
		set("free", free);
	}

	public java.math.BigDecimal getFree() {
		return get("free");
	}

	public void setCloseTime(java.util.Date closeTime) {
		set("close_time", closeTime);
	}

	public java.util.Date getCloseTime() {
		return get("close_time");
	}

	public void setSId(java.lang.String sId) {
		set("S_ID", sId);
	}

	public java.lang.String getSId() {
		return get("S_ID");
	}

}
