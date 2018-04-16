package com.single.pro.request.model;

public class ProductTypeRequestModel extends BaseRequestModel {
	private static final long serialVersionUID = 1L;

	// 产品种类
	private String kindId;

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

}
