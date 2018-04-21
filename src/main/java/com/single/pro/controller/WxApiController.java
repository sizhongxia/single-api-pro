package com.single.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.single.pro.config.WxmpConfig;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-27
 */
@Controller
@RequestMapping("/wxmp/")
public class WxApiController {

	Logger logger = LoggerFactory.getLogger(WxApiController.class);

	@Autowired
	WxmpConfig wxmpConfig;

	@ResponseBody
	@RequestMapping("/login")
	public Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("appId", request.getHeader("appId"));
		return map;
	}
}
