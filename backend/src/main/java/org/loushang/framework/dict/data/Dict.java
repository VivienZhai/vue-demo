package org.loushang.framework.dict.data;

import java.io.Serializable;

public class Dict implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String setMan;
	private String setTime;
	private String note;
	private String inUse;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSetMan() {
		return setMan;
	}

	public void setSetMan(String setMan) {
		this.setMan = setMan;
	}

	public String getSetTime() {
		return setTime;
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

}
