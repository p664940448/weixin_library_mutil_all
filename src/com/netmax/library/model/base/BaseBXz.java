package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBXz<M extends BaseBXz<M>> extends Model<M> implements IBean {

	public void setBNo(java.lang.String bNo) {
		set("b_no", bNo);
	}

	public java.lang.String getBNo() {
		return get("b_no");
	}

	public void setBXz(java.lang.String bXz) {
		set("b_xz", bXz);
	}

	public java.lang.String getBXz() {
		return get("b_xz");
	}

}
