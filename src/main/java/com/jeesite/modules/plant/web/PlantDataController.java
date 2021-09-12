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
import com.jeesite.modules.plant.entity.PlantData;
import com.jeesite.modules.plant.service.PlantDataService;

/**
 * 数据Controller
 * @author fcxl9876
 * @version 2021-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/plant/plantData")
public class PlantDataController extends BaseController {

	@Autowired
	private PlantDataService plantDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PlantData get(String id, boolean isNewRecord) {
		return plantDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("plant:plantData:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlantData plantData, Model model) {
		model.addAttribute("plantData", plantData);
		return "modules/plant/plantDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("plant:plantData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PlantData> listData(PlantData plantData, HttpServletRequest request, HttpServletResponse response) {
		plantData.setPage(new Page<>(request, response));
		Page<PlantData> page = plantDataService.findPage(plantData);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("plant:plantData:view")
	@RequestMapping(value = "form")
	public String form(PlantData plantData, Model model) {
		model.addAttribute("plantData", plantData);
		return "modules/plant/plantDataForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("plant:plantData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PlantData plantData) {
		plantDataService.save(plantData);
		return renderResult(Global.TRUE, text("保存数据成功！"));
	}
	
	/**
	 * 停用数据
	 */
	@RequiresPermissions("plant:plantData:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PlantData plantData) {
		plantData.setStatus(PlantData.STATUS_DISABLE);
		plantDataService.updateStatus(plantData);
		return renderResult(Global.TRUE, text("停用数据成功"));
	}
	
	/**
	 * 启用数据
	 */
	@RequiresPermissions("plant:plantData:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PlantData plantData) {
		plantData.setStatus(PlantData.STATUS_NORMAL);
		plantDataService.updateStatus(plantData);
		return renderResult(Global.TRUE, text("启用数据成功"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("plant:plantData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PlantData plantData) {
		plantDataService.delete(plantData);
		return renderResult(Global.TRUE, text("删除数据成功！"));
	}
	
}