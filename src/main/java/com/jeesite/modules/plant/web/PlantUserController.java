/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.plant.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.plant.entity.PlantUser;
import com.jeesite.modules.plant.service.PlantUserService;

/**
 * 用户Controller
 * @author fcxl9876
 * @version 2021-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/plant/plantUser")
public class PlantUserController extends BaseController {

	@Autowired
	private PlantUserService plantUserService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PlantUser get(String id, boolean isNewRecord) {
		return plantUserService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("plant:plantUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlantUser plantUser, Model model) {
		model.addAttribute("plantUser", plantUser);
		return "modules/plant/plantUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("plant:plantUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PlantUser> listData(PlantUser plantUser, HttpServletRequest request, HttpServletResponse response) {
		plantUser.setPage(new Page<>(request, response));
		Page<PlantUser> page = plantUserService.findPage(plantUser);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("plant:plantUser:view")
	@RequestMapping(value = "form")
	public String form(PlantUser plantUser, Model model) {
		model.addAttribute("plantUser", plantUser);
		return "modules/plant/plantUserForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("plant:plantUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PlantUser plantUser) {
		plantUserService.save(plantUser);
		return renderResult(Global.TRUE, text("保存用户成功！"));
	}
	
	/**
	 * 停用数据
	 */
	@RequiresPermissions("plant:plantUser:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PlantUser plantUser) {
		plantUser.setStatus(PlantUser.STATUS_DISABLE);
		plantUserService.updateStatus(plantUser);
		return renderResult(Global.TRUE, text("停用用户成功"));
	}
	
	/**
	 * 启用数据
	 */
	@RequiresPermissions("plant:plantUser:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PlantUser plantUser) {
		plantUser.setStatus(PlantUser.STATUS_NORMAL);
		plantUserService.updateStatus(plantUser);
		return renderResult(Global.TRUE, text("启用用户成功"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("plant:plantUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PlantUser plantUser) {
		plantUserService.delete(plantUser);
		return renderResult(Global.TRUE, text("删除用户成功！"));
	}
	
}