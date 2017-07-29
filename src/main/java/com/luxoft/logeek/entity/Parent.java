package com.luxoft.logeek.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Parent {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;

	public Parent(String name) {
		this.name = name;
	}

	protected Parent() {
	}
}
