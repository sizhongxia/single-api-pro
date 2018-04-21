package com.single.pro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.single.pro.model.ProductKindModel;
import com.single.pro.redis.CacheUtil;
import com.single.pro.request.model.BaseRequestModel;
import com.single.pro.service.ProductKindService;
import com.single.pro.util.ReturnInfo;

/**
 * @author SiZhongxia
 * @since 2018-04-15
 */
@Controller
@RequestMapping("/product/kind")
public class ProductKindController {

	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	ProductKindService productKindService;

	@ResponseBody
	@RequestMapping(value = { "/data" }, method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String data(HttpServletRequest request, @RequestBody(required = true) BaseRequestModel requestModel) {
		String cacheKey = "single.cache:single:api:product:kind:data";
		String res = cacheUtil.get(cacheKey);
		if (StringUtils.isNotBlank(res)) {
			return res;
		}
		List<ProductKindModel> list = productKindService.getList();
		if (list == null || list.isEmpty()) {
			return ReturnInfo.returnErrWithMsg("暂无产品种类信息");
		}
		res = ReturnInfo.returnOkWithData(list);
		cacheUtil.setex(cacheKey, 600, res);
		return res;
	}
}
