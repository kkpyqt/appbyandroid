package org.jeecg.modules.healthmanage.measure.utils;

public class Message<T> {
	private Integer code;
	private T result;
	private String msg;

	public Message() {
		super();
	}

	public Message(Integer code, T result, String msg) {
		super();
		this.code = code;
		this.result = result;
		this.msg = msg;
	}

	public Message(Integer code, T result) {
		super();
		this.code = code;
		this.result = result;
	}

	public Message(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	

	public Message(Integer code) {
		super();
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
