/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.plant.entity.PlantData;

/**
 * 数据DAO接口
 * @author fcxl9876
 * @version 2021-03-07
 */
@MyBatisDao
public interface PlantDataDao extends CrudDao<PlantData> {
	
	public PlantData getRealTimeData(String deviceId);
	
	public String getUnusualHightTime(String deviceId,String type);
	
	public String getUnusualLowTime(String deviceId,String type);
	
}