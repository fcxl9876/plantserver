/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 数据Entity
 * @author fcxl9876
 * @version 2021-03-07
 */
@Table(name="plant_data", alias="a", label="数据信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="time", attrName="time", label="时间"),
		@Column(name="soil_h", attrName="soilH", label="土壤湿度"),
		@Column(name="soil_t", attrName="soilT", label="土壤温度"),
		@Column(name="envi_h", attrName="enviH", label="环境湿度"),
		@Column(name="envi_t", attrName="enviT", label="环境温度"),
		@Column(name="envi_l", attrName="enviL", label="环境光照"),
		@Column(name="device_id", attrName="deviceId", label="设备id"),
		@Column(name="status", attrName="status", label="状态", comment="状态（0正常 1删除 2停用 3冻结 4审核 5驳回 9草稿）", isUpdate=false),
	}, orderBy="a.id DESC"
)
public class PlantData extends DataEntity<PlantData> {
	
	private static final long serialVersionUID = 1L;
	private Date time;		// 时间
	private String soilH;		// 土壤湿度
	private String soilT;		// 土壤温度
	private String enviH;		// 环境湿度
	private String enviT;		// 环境温度
	private String enviL;		// 环境光照
	private String deviceId;		// 设备id
	
	public PlantData() {
		this(null);
	}

	public PlantData(String id){
		super(id);
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	@Length(min=0, max=10, message="土壤湿度长度不能超过 10 个字符")
	public String getSoilH() {
		return soilH;
	}

	public void setSoilH(String soilH) {
		this.soilH = soilH;
	}
	
	@Length(min=0, max=10, message="土壤温度长度不能超过 10 个字符")
	public String getSoilT() {
		return soilT;
	}

	public void setSoilT(String soilT) {
		this.soilT = soilT;
	}
	
	@Length(min=0, max=10, message="环境湿度长度不能超过 10 个字符")
	public String getEnviH() {
		return enviH;
	}

	public void setEnviH(String enviH) {
		this.enviH = enviH;
	}
	
	@Length(min=0, max=10, message="环境温度长度不能超过 10 个字符")
	public String getEnviT() {
		return enviT;
	}

	public void setEnviT(String enviT) {
		this.enviT = enviT;
	}
	
	@Length(min=0, max=10, message="环境光照长度不能超过 10 个字符")
	public String getEnviL() {
		return enviL;
	}

	public void setEnviL(String enviL) {
		this.enviL = enviL;
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