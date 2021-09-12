package com.jeesite.modules.plant.front.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.plant.interceptor.ValidateMap;
import com.jeesite.modules.plant.dao.PlantUserDao;
import com.jeesite.modules.plant.entity.PlantUser;
import com.jeesite.modules.sys.utils.PwdUtils;

@Controller
@RequestMapping(value = "front/plant/")
public class FrontLoginController {
	
	@Autowired
	private PlantUserDao plantUserDao;
	
	/**
	 * 登录 生成 tk,后续的接口操作需要携带token,在拦截器中校验
	 */
	@RequestMapping(value = "login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String uname, String pwd) {
		Map<String, Object> rMap = new HashMap<>();

		PlantUser user = new PlantUser();
		user.setUsername(uname);
		user = plantUserDao.getByEntity(user);

		if (user == null) {
			rMap.put("status", "400");
			rMap.put("msg", "无效登录名用户！");
			return rMap;
		}

		String password = user.getPassword();

		// 验证密码是否合法
		boolean flag = false;
		if(pwd.equals(password)) {
			flag = true;
		}
		
		if (flag) {
			rMap.put("status", "200");
			rMap.put("msg", "验证成功");

			String token = IdGen.uuid();
			token = ValidateMap.addToken(token, user.getId());// 添加缓存token
			rMap.put("tk", token);

			rMap.put("id", user.getId());
			rMap.put("username", user.getUsername());
			rMap.put("deviceId", user.getDeviceId());
		} else {
			rMap.put("status", "400");
			rMap.put("msg", "用户名或者密码不合法,验证失败");
		}
		// 填充本人的信息 暂时省略
		return rMap;
	}

	/**
	 * 注销用户 剔除 token
	 */
	@RequestMapping(value = "loginout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logingout(String key) {
		Map<String, Object> rMap = new HashMap<>();
		ValidateMap.removeToken(key);
		// 填充本人的信息 暂时省略
		rMap.put("status", "200");
		rMap.put("msg", "已注销用户");
		return rMap;

	}
	
	/**
	 * 修改密码 A: 旧密码 +新密码 B：用户名 +旧密码判断是否是合法用户
	 */
	@RequestMapping(value = "updatePwd",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upPwd(String uid, String oldPwd, String newPwd) {
		Map<String, Object> rMap = new HashMap<>();

		PlantUser user = new PlantUser();
		user.setId(uid);
		user = plantUserDao.getByEntity(user);
		
		if (user == null) {
			rMap.put("status", "400");
			rMap.put("msg", "不存在此用户");
			return rMap;
		}
		
		String password = user.getPassword();
		
		// 验证密码是否合法
		boolean flag = false;
		if(oldPwd.equals(password)) {
			flag = true;
		}
		
		if (flag == false) {
			rMap.put("status", "400");
			rMap.put("msg", "原密码验证失败");
			return rMap;
		} else {
			user.setPassword(newPwd);
			
			plantUserDao.update(user);
			rMap.put("status", "200");
			rMap.put("msg", "密码已修改");
			return rMap;

		}

	}
	
	/**
	 * 修改绑定设备
	 */
	@RequestMapping(value = "changeDevice",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeDevice(String uid, String deviceId) {
		Map<String, Object> rMap = new HashMap<>();
		
		PlantUser user = new PlantUser();
		user.setId(uid);
		user = plantUserDao.getByEntity(user);
		
		if (user == null) {
			rMap.put("status", "400");
			rMap.put("msg", "不存在此用户");
			return rMap;
		}
		
		String originDeviceId = user.getDeviceId();
		
		int count = plantUserDao.selectDeviceIdCount(deviceId);
		int device = plantUserDao.selectDeviceCount(deviceId);
		if(device==0) {
			rMap.put("status", "400");
			rMap.put("msg", "设备id不存在");
			return rMap;
		} else if(count>0) {
			rMap.put("status", "400");
			rMap.put("msg", "设备id重复绑定");
			return rMap;
		} else {
			user.setDeviceId(deviceId);
			plantUserDao.update(user);
			rMap.put("status", "200");
			rMap.put("msg", "设备已绑定");
			return rMap;
		}
		
	}
	
	/**
	 * 注册函数
	 */
	@RequestMapping(value = "logon",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logon(String uname, String pwd, String device) {
		
		Map<String, Object> rMap = new HashMap<>();
		
		if(uname==null ||pwd==null ||device==null ){
			rMap.put("status", "400");
			rMap.put("msg", "参数传递有误");
			return rMap;
		}
		
		int count = plantUserDao.selectDeviceIdCount(device);
		int devicecount = plantUserDao.selectDeviceCount(device);
		if(devicecount==0) {
			rMap.put("status", "400");
			rMap.put("msg", "设备id不存在");
			return rMap;
		} else if(count>0) {
			rMap.put("status", "400");
			rMap.put("msg", "设备id重复绑定");
			return rMap;
		} else {
			PlantUser user = new PlantUser();
			user.setUsername(uname);
			user.setPassword(pwd);
			user.setDeviceId(device);
			plantUserDao.insert(user);
			rMap.put("status", "200");
			rMap.put("msg", "注册成功");
		}
		
		return rMap;
	}
}
