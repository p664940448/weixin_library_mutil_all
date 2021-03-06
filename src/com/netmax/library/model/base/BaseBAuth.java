package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBAuth<M extends BaseBAuth<M>> extends Model<M> implements IBean {

	public void setAuthId(java.lang.Integer authId) {
		set("auth_id", authId);
	}

	public java.lang.Integer getAuthId() {
		return get("auth_id");
	}

	public void setRoomId(java.lang.Integer roomId) {
		set("room_id", roomId);
	}

	public java.lang.Integer getRoomId() {
		return get("room_id");
	}

	public void setReadCard(java.lang.String readCard) {
		set("read_card", readCard);
	}

	public java.lang.String getReadCard() {
		return get("read_card");
	}

	public void setReadBm(java.lang.String readBm) {
		set("read_bm", readBm);
	}

	public java.lang.String getReadBm() {
		return get("read_bm");
	}

	public void setReadKind(java.lang.String readKind) {
		set("read_kind", readKind);
	}

	public java.lang.String getReadKind() {
		return get("read_kind");
	}

	public void setReadName(java.lang.String readName) {
		set("read_name", readName);
	}

	public java.lang.String getReadName() {
		return get("read_name");
	}

	public void setReadZj(java.lang.String readZj) {
		set("read_zj", readZj);
	}

	public java.lang.String getReadZj() {
		return get("read_zj");
	}

	public void setReadId(java.lang.String readId) {
		set("read_id", readId);
	}

	public java.lang.String getReadId() {
		return get("read_id");
	}

	public void setUseTime(java.math.BigDecimal useTime) {
		set("use_time", useTime);
	}

	public java.math.BigDecimal getUseTime() {
		return get("use_time");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
	}

	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public void setState(java.math.BigDecimal state) {
		set("state", state);
	}

	public java.math.BigDecimal getState() {
		return get("state");
	}

}
