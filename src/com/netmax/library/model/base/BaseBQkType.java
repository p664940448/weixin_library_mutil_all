package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBQkType<M extends BaseBQkType<M>> extends Model<M> implements IBean {

	public void setTypeId(java.math.BigDecimal typeId) {
		set("type_id", typeId);
	}

	public java.math.BigDecimal getTypeId() {
		return get("type_id");
	}

	public void setTypeName(java.lang.String typeName) {
		set("type_name", typeName);
	}

	public java.lang.String getTypeName() {
		return get("type_name");
	}

	public void setCanInput(java.math.BigDecimal canInput) {
		set("can_input", canInput);
	}

	public java.math.BigDecimal getCanInput() {
		return get("can_input");
	}

}
