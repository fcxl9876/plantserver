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
import com.jeesite.modules.plant.entity.PlantDevice;
import com.jeesite.modules.plant.service.PlantDeviceService;

/**
 * 设备Controller
 * @author fcxl9876
 * @version 2021-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/plant/plantDevice")
public class PlantDeviceController extends BaseController {

	@Autowired
	private PlantDeviceService plantDeviceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PlantDevice get(String id, boolean isNewRecord) {
		return plantDeviceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("plant:plantDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlantDevice plantDevice, Model model) {
		model.addAttribute("plantDevice", plantDevice);
		return "modules/plant/plantDeviceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("plant:plantDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PlantDevice> listData(PlantDevice plantDevice, HttpServletRequest request, HttpServletResponse response) {
		plantDevice.setPage(new Page<>(request, response));
		Page<PlantDevice> page = plantDeviceService.findPage(plantDevice);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("plant:plantDevice:view")
	@RequestMapping(value = "form")
	public String form(PlantDevice plantDevice, Model model) {
		model.addAttribute("plantDevice", plantDevice);
		return "modules/plant/plantDeviceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("plant:plantDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PlantDevice plantDevice) {
		plantDeviceService.save(plantDevice);
		return renderResult(Global.TRUE, text("保存设备成功！"));
	}
	
	/**
	 * 停用数据
	 */
	@RequiresPermissions("plant:plantDevice:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PlantDevice plantDevice) {
		plantDevice.setStatus(PlantDevice.STATUS_DISABLE);
		plantDeviceService.updateStatus(plantDevice);
		return renderResult(Global.TRUE, text("停用设备成功"));
	}
	
	/**
	 * 启用数据
	 */
	@RequiresPermissions("plant:plantDevice:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PlantDevice plantDevice) {
		plantDevice.setStatus(PlantDevice.STATUS_NORMAL);
		plantDeviceService.updateStatus(plantDevice);
		return renderResult(Global.TRUE, text("启用设备成功"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("plant:plantDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PlantDevice plantDevice) {
		plantDeviceService.delete(plantDevice);
		return renderResult(Global.TRUE, text("删除设备成功！"));
	}
	
}