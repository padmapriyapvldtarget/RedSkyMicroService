package com.target.redsky.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedSky implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer productId;
	private String name;

	public RedSky() {

	}

	public RedSky(Integer productId, String name) {
		super();
		this.productId = productId;
		this.name = name;

	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
