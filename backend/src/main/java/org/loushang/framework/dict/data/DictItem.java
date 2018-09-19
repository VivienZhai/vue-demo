package org.loushang.framework.dict.data;

import java.io.Serializable;

public class DictItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String dictCode;

	private String value;

	private String parentCode;

	private String note;

	private String inUse;

	private int sequence;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
