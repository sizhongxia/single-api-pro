package com.single.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.single.pro.mapper.ProductKindMapper;
import com.single.pro.model.ProductKindModel;
import com.single.pro.service.ProductKindService;
import com.single.pro.storage.RealHostReplace;

@Service
public class ProductKindServiceImpl implements ProductKindService {

	@Autowired
	ProductKindMapper productKindMapper;

	@Override
	public List<ProductKindModel> getList() {
		List<ProductKindModel> list = null;
		list = this.productKindMapper.getList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		for (ProductKindModel item : list) {
			item.setPicUrl(RealHostReplace.getResUrl(item.getPicUrl()));
		}
		return list;
	}

}
