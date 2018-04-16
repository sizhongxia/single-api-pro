package com.single.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.single.pro.mapper.ProductTypeMapper;
import com.single.pro.model.ProductTypeModel;
import com.single.pro.service.ProductTypeService;
import com.single.pro.storage.RealHostReplace;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	ProductTypeMapper productTypeMapper;

	@Override
	public List<ProductTypeModel> getList(String kindId) {
		List<ProductTypeModel> list = null;
		list = this.productTypeMapper.getList(kindId);
		if (list == null || list.isEmpty()) {
			return null;
		}
		for (ProductTypeModel item : list) {
			item.setPicUrl(RealHostReplace.getResUrl(item.getPicUrl()));
		}
		return list;
	}

}
