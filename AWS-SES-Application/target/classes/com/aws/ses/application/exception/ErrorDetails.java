package com.aws.ses.application.exception;

import java.util.Date;

public class ErrorDetails {
	
	private Date timestamp;
	private String errMsg;
	private String errCode;
	
	public ErrorDetails(Date timestaDate, String errMsg, String errCode) {
		super();
		this.timestamp = timestaDate;
		this.errMsg = errMsg;
		this.errCode = errCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
