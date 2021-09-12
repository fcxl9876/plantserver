/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.plant.entity.PlantDevice;
import com.jeesite.modules.plant.dao.PlantDeviceDao;

/**
 * 设备Service
 * @author fcxl9876
 * @version 2021-03-07
 */
@Service
@Transactional(readOnly=true)
public class PlantDeviceService extends CrudService<PlantDeviceDao, PlantDevice> {
	
	/**
	 * 获取单条数据
	 * @param plantDevice
	 * @return
	 */
	@Override
	public PlantDevice get(PlantDevice plantDevice) {
		return super.get(plantDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param plantDevice 查询条件
	 * @param plantDevice.page 分页对象
	 * @return
	 */
	@Override
	public Page<PlantDevice> findPage(PlantDevice plantDevice) {
		return super.findPage(plantDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param plantDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PlantDevice plantDevice) {
		super.save(plantDevice);
	}
	
	/**
	 * 更新状态
	 * @param plantDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PlantDevice plantDevice) {
		super.updateStatus(plantDevice);
	}
	
	/**
	 * 删除数据
	 * @param plantDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PlantDevice plantDevice) {
		super.delete(plantDevice);
	}
	
}