package com.target.redsky.entity;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Entity
@Table("redsky")

public class RedSkyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private Integer productId;
	@Column("name")
	private String name;

	public RedSkyEntity() {

	}

	public RedSkyEntity(Integer productId, String name) {
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