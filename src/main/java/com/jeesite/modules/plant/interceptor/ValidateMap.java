package com.jeesite.modules.plant.interceptor;

import com.jeesite.common.cache.CacheUtils;

public class ValidateMap {

	public static String TK_CacheName = "AppToken";
	public static String USER_CacheName = "AppUser";
	public static long expireTime = 3600;// 过期时长 单位 /秒

	public static String addToken(String tk, String uId) {

		if (CacheUtils.get(TK_CacheName, tk) == null && CacheUtils.get(USER_CacheName, uId) == null) {

			CacheUtils.put(TK_CacheName, tk, uId, expireTime);
			CacheUtils.put(USER_CacheName, uId, tk, expireTime);
			return tk;
		}else{			
			if(CacheUtils.get(USER_CacheName, uId) != null){//未过期时间内 有重复登录，返回已经存在的token
				
				String existTK=CacheUtils.get(USER_CacheName, uId);
				return existTK;		
			}	
		}
		return "";
	}

	public static boolean isCacheToken(String tk) {
		if (CacheUtils.get(TK_CacheName, tk) != null) {
			return true;
		}
		return false;
	}

	public static Boolean isCacheUser(String uId) {

		if (CacheUtils.get(USER_CacheName, uId) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 移除 tk 同时移除 uid
	 * 
	 * @param tk
	 * @return
	 */
	public static boolean removeToken(String tk) {

		if (CacheUtils.get(TK_CacheName, tk) != null) {

			String uId = CacheUtils.get(TK_CacheName, tk);
			CacheUtils.remove(TK_CacheName, tk);
			CacheUtils.remove(USER_CacheName, uId);

			return true;
		}
		return false;

	}

}
