package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBPublisher<M extends BaseBPublisher<M>> extends Model<M> implements IBean {

	public void setPubIsbn(java.lang.String pubIsbn) {
		set("pub_isbn", pubIsbn);
	}

	public java.lang.String getPubIsbn() {
		return get("pub_isbn");
	}

	public void setPubName(java.lang.String pubName) {
		set("pub_name", pubName);
	}

	public java.lang.String getPubName() {
		return get("pub_name");
	}

	public void setPubAddr(java.lang.String pubAddr) {
		set("pub_addr", pubAddr);
	}

	public java.lang.String getPubAddr() {
		return get("pub_addr");
	}

}
