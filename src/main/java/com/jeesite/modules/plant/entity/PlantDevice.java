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
 * 设备Entity
 * @author fcxl9876
 * @version 2021-03-07
 */
@Table(name="plant_device", alias="a", label="设备信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="device_id", attrName="deviceId", label="设备id"),
		@Column(name="device_name", attrName="deviceName", label="设备名", queryType=QueryType.LIKE),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用 3冻结 4审核 5驳回 9草稿）", isUpdate=false),
	}, orderBy="a.id DESC"
)
public class PlantDevice extends DataEntity<PlantDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
	private String deviceName;		// 设备名
	
	public PlantDevice() {
		this(null);
	}

	public PlantDevice(String id){
		super(id);
	}
	
	@NotBlank(message="设备id不能为空")
	@Length(min=0, max=10, message="设备id长度不能超过 10 个字符")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=20, message="设备名长度不能超过 20 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}