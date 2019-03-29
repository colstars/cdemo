package com.cdemo.util.log;

/**
*@description: 日志类型枚举类
*@create: 2019-03-29 21:55:22
*@author: Mr.Yanxingxing
*/
public enum LogEnum {

	LOG_TEST("test","测试类使用日志"),
	LOG_DEMO("demo", "示例使用日志");

	public String type;
	public String msg;

	LogEnum(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}

}
