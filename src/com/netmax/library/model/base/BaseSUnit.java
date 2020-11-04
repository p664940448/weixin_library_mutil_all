package com.netmax.library.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSUnit<M extends BaseSUnit<M>> extends Model<M> implements IBean {

	public void setUnitId(java.lang.Integer unitId) {
		set("unit_id", unitId);
	}

	public java.lang.Integer getUnitId() {
		return get("unit_id");
	}

	public void setUnitNo(java.lang.String unitNo) {
		set("unit_no", unitNo);
	}

	public java.lang.String getUnitNo() {
		return get("unit_no");
	}

	public void setPUnitId(java.lang.Integer pUnitId) {
		set("p_unit_id", pUnitId);
	}

	public java.lang.Integer getPUnitId() {
		return get("p_unit_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setServerName(java.lang.String serverName) {
		set("server_name", serverName);
	}

	public java.lang.String getServerName() {
		return get("server_name");
	}

	public void setServerIp(java.lang.String serverIp) {
		set("server_ip", serverIp);
	}

	public java.lang.String getServerIp() {
		return get("server_ip");
	}

	public void setServerPort(java.lang.Integer serverPort) {
		set("server_port", serverPort);
	}

	public java.lang.Integer getServerPort() {
		return get("server_port");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	public java.lang.String getUserName() {
		return get("user_name");
	}

	public void setUserPass(java.lang.String userPass) {
		set("user_pass", userPass);
	}

	public java.lang.String getUserPass() {
		return get("user_pass");
	}

	public void setDbName(java.lang.String dbName) {
		set("db_name", dbName);
	}

	public java.lang.String getDbName() {
		return get("db_name");
	}

	public void setServerType(java.lang.String serverType) {
		set("server_type", serverType);
	}

	public java.lang.String getServerType() {
		return get("server_type");
	}

	public void setUnitType(java.lang.String unitType) {
		set("unit_type", unitType);
	}

	public java.lang.String getUnitType() {
		return get("unit_type");
	}

	public void setUnitFlag(java.lang.String unitFlag) {
		set("unit_flag", unitFlag);
	}

	public java.lang.String getUnitFlag() {
		return get("unit_flag");
	}

}
