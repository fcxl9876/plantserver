/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.plant.entity.PlantData;
import com.jeesite.modules.plant.dao.PlantDataDao;

/**
 * 数据Service
 * @author fcxl9876
 * @version 2021-03-07
 */
@Service
@Transactional(readOnly=true)
public class PlantDataService extends CrudService<PlantDataDao, PlantData> {
	
	/**
	 * 获取单条数据
	 * @param plantData
	 * @return
	 */
	@Override
	public PlantData get(PlantData plantData) {
		return super.get(plantData);
	}
	
	/**
	 * 查询分页数据
	 * @param plantData 查询条件
	 * @param plantData.page 分页对象
	 * @return
	 */
	@Override
	public Page<PlantData> findPage(PlantData plantData) {
		return super.findPage(plantData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param plantData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PlantData plantData) {
		super.save(plantData);
	}
	
	/**
	 * 更新状态
	 * @param plantData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PlantData plantData) {
		super.updateStatus(plantData);
	}
	
	/**
	 * 删除数据
	 * @param plantData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PlantData plantData) {
		super.delete(plantData);
	}
	
}