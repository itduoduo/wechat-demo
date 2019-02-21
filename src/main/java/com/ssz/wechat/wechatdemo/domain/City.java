package com.ssz.wechat.wechatdemo.domain;

import java.io.Serializable;

public class City implements Serializable {

	private String name;

	private String lenth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLenth() {
		return lenth;
	}

	public void setLenth(String lenth) {
		this.lenth = lenth;
	}

}
