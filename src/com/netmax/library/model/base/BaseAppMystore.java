package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAppMystore<M extends BaseAppMystore<M>> extends Model<M> implements IBean {

	public void setId(java.math.BigDecimal id) {
		set("id", id);
	}

	public java.math.BigDecimal getId() {
		return get("id");
	}

	public void setBookId(java.math.BigDecimal bookId) {
		set("bookId", bookId);
	}

	public java.math.BigDecimal getBookId() {
		return get("bookId");
	}

	public void setReaderCode(java.lang.String readerCode) {
		set("reader_code", readerCode);
	}

	public java.lang.String getReaderCode() {
		return get("reader_code");
	}

}
