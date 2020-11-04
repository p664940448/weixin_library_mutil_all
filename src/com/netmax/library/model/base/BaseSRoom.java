package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSRoom<M extends BaseSRoom<M>> extends Model<M> implements IBean {

	public void setRoomId(java.lang.Integer roomId) {
		set("room_id", roomId);
	}

	public java.lang.Integer getRoomId() {
		return get("room_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setChargeId(java.lang.Integer chargeId) {
		set("charge_id", chargeId);
	}

	public java.lang.Integer getChargeId() {
		return get("charge_id");
	}

	public void setOpNum(java.math.BigDecimal opNum) {
		set("op_num", opNum);
	}

	public java.math.BigDecimal getOpNum() {
		return get("op_num");
	}

	public void setOpTime(java.math.BigDecimal opTime) {
		set("op_time", opTime);
	}

	public java.math.BigDecimal getOpTime() {
		return get("op_time");
	}

	public void setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
	}

	public java.util.Date getEndTime() {
		return get("end_time");
	}

}
