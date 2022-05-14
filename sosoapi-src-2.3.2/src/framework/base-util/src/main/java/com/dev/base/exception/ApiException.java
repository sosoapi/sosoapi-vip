package com.dev.base.exception;

import com.dev.base.exception.errorcode.SysErrorCode;

/**
 * 
		* <p>Title: session过期异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日上午11:17:23</p>
 */
public class ApiException extends BaseRuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ApiException(){
		super(SysErrorCode.SYS_012);
	}
	
	public ApiException(String errorCode) {
		super(errorCode);
	}

	public ApiException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public ApiException(String errorCode, String errorMsg,Object data) {
		super(errorCode, errorMsg, data);
	}
}
