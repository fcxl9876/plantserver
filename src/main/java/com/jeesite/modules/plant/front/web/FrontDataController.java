package com.jeesite.modules.plant.front.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeesite.modules.plant.dao.PlantDataDao;
import com.jeesite.modules.plant.entity.PlantData;
import com.jeesite.modules.util.MyTimeFormat;

@Controller
@RequestMapping(value = "front/plant/")
public class FrontDataController {
	@Autowired
	private PlantDataDao plantDataDao;
	
	/**
	 * 获取最新的单条数据
	 */
	@RequestMapping(value = "getRealTime",method=RequestMethod.POST)
	public Map<String, Object> getRealTime(String deviceId){
		Map<String, Object> rMap = new HashMap<>();
		PlantData plantData = new PlantData();
		plantData.setDeviceId(deviceId);
		PlantData dbPlantData = plantDataDao.getRealTimeData(deviceId);
		float soilHNum = Float.parseFloat(dbPlantData.getSoilH());
		//处理湿度数字
		String soilHString  = "";
		if(soilHNum<200) {
			soilHString = "过于干燥";
		}else if (soilHNum<400) {
			soilHString = "较为干燥";
		}else if(soilHNum<600) {
			soilHString = "较为湿润";
		}else {
			soilHString = "过于湿润";
		}
		//处理时间戳
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeString = sdf.format(dbPlantData.getTime());
		
		rMap.put("status", "200");
		rMap.put("msg", "查询最新数据成功");
		rMap.put("time", timeString);
		rMap.put("soilH", soilHString);
		rMap.put("soilT", dbPlantData.getSoilT());
		rMap.put("enviH", dbPlantData.getEnviH());
		rMap.put("enviT", dbPlantData.getEnviT());
		rMap.put("enviL", dbPlantData.getEnviL());
		return rMap;
	}
	
	@RequestMapping(value = "getUnusualTime",method=RequestMethod.POST)
	public Map<String, Object> getUnusualTime(String deviceId){
		Map<String, Object> rMap = new HashMap<>();
		int soilHHConunt = Integer.parseInt(plantDataDao.getUnusualHightTime(deviceId, "soilH"));
		int soilHLConunt = Integer.parseInt(plantDataDao.getUnusualLowTime(deviceId, "soilH"));
		String soilHHTimesString = MyTimeFormat.myFormat(soilHHConunt);
		String soilHLTimesString = MyTimeFormat.myFormat(soilHLConunt);
		if( soilHHTimesString!=null || soilHLTimesString!=null ) {
			rMap.put("status", "200");
			rMap.put("msg", "获取成功！");
			rMap.put("soilHH", soilHHTimesString);
			rMap.put("soilHL", soilHLTimesString);
			return rMap;
		}
		rMap.put("status", "400");
		rMap.put("msg", "获取数据失败，暂无数据");
		return rMap;
	}
}
