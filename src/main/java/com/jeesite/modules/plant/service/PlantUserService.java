/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.plant.entity.PlantUser;
import com.jeesite.modules.plant.dao.PlantUserDao;

/**
 * 用户Service
 * @author fcxl9876
 * @version 2021-03-13
 */
@Service
@Transactional(readOnly=true)
public class PlantUserService extends CrudService<PlantUserDao, PlantUser> {
	
	/**
	 * 获取单条数据
	 * @param plantUser
	 * @return
	 */
	@Override
	public PlantUser get(PlantUser plantUser) {
		return super.get(plantUser);
	}
	
	/**
	 * 查询分页数据
	 * @param plantUser 查询条件
	 * @param plantUser.page 分页对象
	 * @return
	 */
	@Override
	public Page<PlantUser> findPage(PlantUser plantUser) {
		return super.findPage(plantUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param plantUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PlantUser plantUser) {
		super.save(plantUser);
	}
	
	/**
	 * 更新状态
	 * @param plantUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PlantUser plantUser) {
		super.updateStatus(plantUser);
	}
	
	/**
	 * 删除数据
	 * @param plantUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PlantUser plantUser) {
		super.delete(plantUser);
	}
	
}