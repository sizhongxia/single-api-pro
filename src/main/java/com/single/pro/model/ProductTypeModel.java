package com.single.pro.model;

public class ProductTypeModel extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String picUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return "ProductKindModel [id=" + id + ", name=" + name + ", picUrl=" + picUrl + "]";
	}

}
