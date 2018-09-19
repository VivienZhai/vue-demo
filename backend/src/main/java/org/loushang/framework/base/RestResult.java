package org.loushang.framework.base;

import java.io.Serializable;

public class RestResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String code = "200";
	protected String message = "success";
	protected T data;

	public RestResult() {
		this(null);
	}

	public RestResult(T data) {
		this.data = data;
	}

	public RestResult(String code, String message) {
		this.code = code;
		this.message = message;
		this.data = null;
	}

	public RestResult(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static RestResult<Boolean> success() {
		return new RestResult<Boolean>(true);
	}

}
