/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.plant.entity.PlantUser;

/**
 * 用户DAO接口
 * @author fcxl9876
 * @version 2021-03-13
 */
@MyBatisDao
public interface PlantUserDao extends CrudDao<PlantUser> {

	void updatePassword(PlantUser user);
	
	public int selectDeviceIdCount(String deviceId);
	
	public int selectDeviceCount(String deviceId);
	
}