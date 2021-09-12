/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 用户Entity
 * @author fcxl9876
 * @version 2021-03-13
 */
@Table(name="plant_user", alias="a", label="用户信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="username", attrName="username", label="用户名"),
		@Column(name="password", attrName="password", label="密码"),
		@Column(name="device_id", attrName="deviceId", label="设备id"),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用 3冻结 4审核 5驳回 9草稿）", isUpdate=false),
	}, orderBy="a.id DESC"
)
public class PlantUser extends DataEntity<PlantUser> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 用户名
	private String password;		// 密码
	private String deviceId;		// 设备id
	
	public PlantUser() {
		this(null);
	}

	public PlantUser(String id){
		super(id);
	}
	
	@NotBlank(message="用户名不能为空")
	@Length(min=0, max=20, message="用户名长度不能超过 20 个字符")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotBlank(message="密码不能为空")
	@Length(min=0, max=20, message="密码长度不能超过 20 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotBlank(message="设备id不能为空")
	@Length(min=0, max=10, message="设备id长度不能超过 10 个字符")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}