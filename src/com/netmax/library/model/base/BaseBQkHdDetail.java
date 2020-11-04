package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBQkHdDetail<M extends BaseBQkHdDetail<M>> extends Model<M> implements IBean {

	public void setBHdNo(java.math.BigDecimal bHdNo) {
		set("b_hd_no", bHdNo);
	}

	public java.math.BigDecimal getBHdNo() {
		return get("b_hd_no");
	}

	public void setBDNo(java.math.BigDecimal bDNo) {
		set("b_d_no", bDNo);
	}

	public java.math.BigDecimal getBDNo() {
		return get("b_d_no");
	}

	public void setBBarid(java.lang.String bBarid) {
		set("b_barid", bBarid);
	}

	public java.lang.String getBBarid() {
		return get("b_barid");
	}

	public void setBBmman(java.lang.String bBmman) {
		set("b_bmman", bBmman);
	}

	public java.lang.String getBBmman() {
		return get("b_bmman");
	}

	public void setRDate(java.util.Date rDate) {
		set("r_date", rDate);
	}

	public java.util.Date getRDate() {
		return get("r_date");
	}

}
