package com.fenbi.fbms.exception;
/**
 * 当修改课程状态时将会出现的业务异常
 */
public class UpdateStatusFailException extends Exception {

	public UpdateStatusFailException(String message) {		
		super(message);
	}
	
}
