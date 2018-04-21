package com.single.pro.request.model;

public class WxLoginRequestModel {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "WxLoginRequestModel [code=" + code + "]";
	}

}
