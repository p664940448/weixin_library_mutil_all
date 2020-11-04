package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBIssue<M extends BaseBIssue<M>> extends Model<M> implements IBean {

	public void setPubId(java.lang.String pubId) {
		set("pub_id", pubId);
	}

	public java.lang.String getPubId() {
		return get("pub_id");
	}

	public void setPubName(java.lang.String pubName) {
		set("pub_name", pubName);
	}

	public java.lang.String getPubName() {
		return get("pub_name");
	}

	public void setPubMan(java.lang.String pubMan) {
		set("pub_man", pubMan);
	}

	public java.lang.String getPubMan() {
		return get("pub_man");
	}

	public void setPubTel(java.lang.String pubTel) {
		set("pub_tel", pubTel);
	}

	public java.lang.String getPubTel() {
		return get("pub_tel");
	}

	public void setPubEmail(java.lang.String pubEmail) {
		set("pub_email", pubEmail);
	}

	public java.lang.String getPubEmail() {
		return get("pub_email");
	}

	public void setPubAddr(java.lang.String pubAddr) {
		set("pub_addr", pubAddr);
	}

	public java.lang.String getPubAddr() {
		return get("pub_addr");
	}

}
