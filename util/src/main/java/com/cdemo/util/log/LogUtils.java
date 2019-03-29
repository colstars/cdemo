package com.cdemo.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*@description: 日志帮助类
*@create: 2019-03-29 21:54:49
*@author: Mr.Yanxingxing
*/
public class LogUtils {


	/**
	*@description: 打印日志到test中
	*@param: []
	*@return: org.slf4j.Logger
	*@create: 2019-03-29 22:01:44
	*@author: Mr.Yanxingxing
	*/
	public static Logger test(){
		return LoggerFactory.getLogger(LogEnum.LOG_TEST.type);
	}


}
