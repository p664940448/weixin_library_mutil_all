package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBMaintain<M extends BaseBMaintain<M>> extends Model<M> implements IBean {

	public void setMId(java.lang.String mId) {
		set("m_id", mId);
	}

	public java.lang.String getMId() {
		return get("m_id");
	}

	public void setMName(java.lang.String mName) {
		set("m_name", mName);
	}

	public java.lang.String getMName() {
		return get("m_name");
	}

	public void setMObject(java.lang.String mObject) {
		set("m_object", mObject);
	}

	public java.lang.String getMObject() {
		return get("m_object");
	}

	public void setMDj(java.lang.Integer mDj) {
		set("m_dj", mDj);
	}

	public java.lang.Integer getMDj() {
		return get("m_dj");
	}

}
