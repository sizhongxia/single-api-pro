package com.single.pro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.single.pro.mapper.TestMapper;
import com.single.pro.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestMapper testMapper;

	@Override
	public Long test(Map<String, Object> params) {
		return testMapper.test(params);
	}

}
