package com.single.pro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.single.pro.model.ProductTypeModel;

public interface ProductTypeMapper {

	List<ProductTypeModel> getList(@Param("kindId") String kindId);

}
