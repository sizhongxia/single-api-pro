package com.single.pro.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ReturnInfo {

	private static final int OK = 200;
	private static final int ERR = 500;

	public static String returnOk() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", OK);
		return JSON.toJSONString(map);
	}

	public static String returnOkWithMsg(String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", OK);
		map.put("msg", msg);
		return JSON.toJSONString(map);
	}

	public static String returnOkWithData(Object data) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", OK);
		map.put("data", data);
		return JSON.toJSONString(map);
	}

	public static String returnErr() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", ERR);
		return JSON.toJSONString(map);
	}

	public static String returnErrWithMsg(String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", ERR);
		map.put("msg", msg);
		return JSON.toJSONString(map);
	}

}
