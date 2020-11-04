package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBLendpara<M extends BaseBLendpara<M>> extends Model<M> implements IBean {

	public void setReadKind(java.lang.String readKind) {
		set("read_kind", readKind);
	}

	public java.lang.String getReadKind() {
		return get("read_kind");
	}

	public void setBookKind(java.lang.String bookKind) {
		set("book_kind", bookKind);
	}

	public java.lang.String getBookKind() {
		return get("book_kind");
	}

	public void setLendNum(java.math.BigDecimal lendNum) {
		set("lend_num", lendNum);
	}

	public java.math.BigDecimal getLendNum() {
		return get("lend_num");
	}

	public void setLendDay(java.math.BigDecimal lendDay) {
		set("lend_day", lendDay);
	}

	public java.math.BigDecimal getLendDay() {
		return get("lend_day");
	}

}
