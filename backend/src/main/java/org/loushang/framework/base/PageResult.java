package org.loushang.framework.base;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String code = "200";
	protected String message = "success";
	protected List<T> data;
	protected long total;

	public PageResult(List<T> data) {
		this.data = data;
		this.total = data.size();
	}

	public PageResult(List<T> data, long total) {
		this.data = data;
		this.total = total;
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
