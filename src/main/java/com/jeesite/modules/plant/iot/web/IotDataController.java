package com.jeesite.modules.plant.iot.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.plant.entity.PlantData;
import com.jeesite.modules.plant.service.PlantDataService;

@Controller
@RequestMapping(value = "iot/data/")
public class IotDataController {
	
	@Autowired
	private PlantDataService plantDataService;
	
	@RequestMapping(value = "upload",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> upload(String data){
		Map<String, Object> rMap = new HashMap<>();
		
		PlantData plantData = new PlantData();
		JSONObject jsonData = JSONObject.parseObject(data);
		Timestamp time = new Timestamp(System.currentTimeMillis());	//时间
		float soilHNum = Float.parseFloat(jsonData.getString("soilH"));
		soilHNum = 800 - soilHNum;
		String soilH = String.valueOf(soilHNum);					//土壤湿度
		String soilT = jsonData.getString("soilT");					//土壤温度
		String enviH = jsonData.getString("enviH");					//环境湿度
		String enviT = jsonData.getString("enviT");					//环境温度
		String enviL = jsonData.getString("enviL");					//环境光照
		String deviceId = jsonData.getString("deviceId");			//设备id
		
		if( deviceId==null || deviceId=="" ) {
			rMap.put("status", "400");
			rMap.put("msg", "参数传递有误");
		} else {
			plantData.setTime(time);
			plantData.setSoilH(soilH);
			plantData.setSoilT(soilT);
			plantData.setEnviH(enviH);
			plantData.setEnviT(enviT);
			plantData.setEnviL(enviL);
			plantData.setDeviceId(deviceId);
			
			plantDataService.save(plantData);
			rMap.put("status", "200");
			rMap.put("msg", "操作成功完成");
		}
		
		return rMap;
	}
	
}
