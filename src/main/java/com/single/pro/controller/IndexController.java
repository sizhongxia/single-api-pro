package com.single.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.single.pro.cache.CacheUtil;
import com.single.pro.service.TestService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-27
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	TestService testService;

	@RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		System.out.println(testService.test(null));
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = { "/json" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> json(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("data", CacheUtil.get("single:system:city", "110000"));
		return map;
	}
}
