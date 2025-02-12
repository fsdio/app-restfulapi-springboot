package com.fsdio.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Table("static_token")
@Getter
@Setter
public class TokenStaticEntity {

	@Id
	private Long id;
	private String name;
	private String token;
}
