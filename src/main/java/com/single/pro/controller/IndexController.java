package com.single.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.single.pro.request.model.BaseRequestModel;

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


	@RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = { "/json" }, method = { RequestMethod.POST }, produces = "application/json")
	public Map<String, Object> json(HttpServletRequest request,
			@RequestBody(required = true) BaseRequestModel requestModel) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("name", requestModel);
		map.put("wxToken", request.getHeader("wxToken"));
		map.put("appId", request.getHeader("appId"));
		return map;
	}
}
