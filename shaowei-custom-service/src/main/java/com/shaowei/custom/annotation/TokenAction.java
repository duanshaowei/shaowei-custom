package com.shaowei.custom.annotation;

public enum TokenAction {
	/** 初始化token保存到session */
	PREPARATION,
	/** 提交请求 将会对请求进行重复判断 */
	SUBMIT
}
