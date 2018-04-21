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

import com.single.pro.model.ProductTypeModel;
import com.single.pro.redis.CacheUtil;
import com.single.pro.request.model.ProductTypeRequestModel;
import com.single.pro.service.ProductTypeService;
import com.single.pro.util.ReturnInfo;

/**
 * @author SiZhongxia
 * @since 2018-04-15
 */
@Controller
@RequestMapping("/product/type")
public class ProductTypeController {

	@Autowired
	CacheUtil cacheUtil;
	@Autowired
	ProductTypeService productTypeService;

	@ResponseBody
	@RequestMapping(value = { "/data" }, method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String data(HttpServletRequest request, @RequestBody(required = true) ProductTypeRequestModel requestModel) {
		String kindId = requestModel.getKindId();
		if (StringUtils.isBlank(kindId)) {
			return ReturnInfo.returnErrWithMsg("无效的产品种类标识");
		}
		String cacheKey = "single.cache:single:api:product:type:data:" + kindId;
		String res = cacheUtil.get(cacheKey);
		if (StringUtils.isNotBlank(res)) {
			return res;
		}
		List<ProductTypeModel> list = productTypeService.getList(kindId);
		if (list == null || list.isEmpty()) {
			return ReturnInfo.returnErrWithMsg("当前种类下暂无类别信息");
		}
		res = ReturnInfo.returnOkWithData(list);
		cacheUtil.setex(cacheKey, 600, res);
		return res;
	}
}
