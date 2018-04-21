package com.single.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.single.pro.config.WxmpConfig;
import com.single.pro.request.model.WxLoginRequestModel;
import com.single.pro.util.ReturnInfo;
import com.single.pro.util.WxRequestUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SiZhongxia
 * @since 2017-12-27
 */
@Controller
@RequestMapping("/wxapi/")
public class WxApiController {

	Logger logger = LoggerFactory.getLogger(WxApiController.class);

	@Autowired
	WxmpConfig wxmpConfig;

	@ResponseBody
	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String login(HttpServletRequest request, @RequestBody(required = true) WxLoginRequestModel requestModel) {
		String code = requestModel.getCode();
		if (StringUtils.isBlank(code)) {
			return ReturnInfo.returnErrWithMsg("无效的参数");
		}
		String wx_login = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
		String requestUrl = wx_login.replaceAll("APPID", wxmpConfig.getMpAppId())
				.replaceAll("SECRET", wxmpConfig.getMpAppSecret()).replaceAll("JSCODE", code);
		StringBuffer sb = null;
		try {
			sb = WxRequestUtil.httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnInfo.returnErrWithMsg("请求微信服务接口失败,E1!!!");
		}
		if (sb == null || sb.length() < 1) {
			return ReturnInfo.returnErrWithMsg("请求微信服务接口失败,E2!!!");
		}
		JSONObject json = null;
		try {
			json = JSONObject.parseObject(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnInfo.returnErrWithMsg("解析数据失败,E3!!!");
		}
		if (json == null) {
			return ReturnInfo.returnErrWithMsg("解析数据失败,E4!!!");
		}

		Integer errcode = json.getInteger("errcode");
		if (errcode != null && errcode.intValue() > 0) {
			return ReturnInfo.returnErrWithMsg("错误：E" + errcode + "，" + json.getString("errmsg"));
		}

		String openid = json.getString("openid");
		if (StringUtils.isBlank(openid)) {
			return ReturnInfo.returnErrWithMsg("获取OpenId失败,E5!!!");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("openid", openid);
		return ReturnInfo.returnOkWithData(data);
	}
}
