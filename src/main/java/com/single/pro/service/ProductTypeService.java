package com.single.pro.service;

import java.util.List;

import com.single.pro.model.ProductTypeModel;

public interface ProductTypeService {

	List<ProductTypeModel> getList(String kindId);

}
