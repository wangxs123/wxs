package com.wxs.exception;


/**
 * web 层异常
 *
 * @author wxs
 * @date 2019-05-29 9:56
 */
public class WebException extends RuntimeException {

	private static final long serialVersionUID = 8604424364318396626L;


	/**
	 * 异常代码
	 */
	private String code;

	/**
	 * 异常说明
	 */
	private String desc;


	public WebException() {
		super();
	}


	public WebException(String message ) {
		super(message);
		this.desc = message;
	}


	public WebException(String code, String desc ) {
		this.code = code;
		this.desc = desc;
	}


	public WebException(String code, String desc, Throwable cause ) {
		super(cause);
		this.code = code;
		this.desc = desc;
	}


	public WebException(String code, String desc, String message ) {
		super(message);
		this.code = code;
		this.desc = desc;
	}


	public String getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}


	@Override
	public String getMessage() {
		if ( super.getMessage() == null ) {
			return desc;
		}
		return super.getMessage();
	}
}
