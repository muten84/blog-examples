package it.luigibifulco.jsinterop.client;

import jsinterop.annotations.JsType;

@JsType(name="Greetings", namespace="exported")
@com.google.gwt.core.client.js.JsType(prototype="exported.Greetings")
public class Greetings {
	
	private String msg;

	public Greetings(String msg){
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}

