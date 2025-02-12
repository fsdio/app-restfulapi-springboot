package com.fsdio.app.controller;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsdio.app.entity.TokenStaticEntity;
import com.fsdio.app.repository.TokenStaticRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/static-token")
public class TokenStaticController {
	private final TokenStaticRepository tokenStaticRepository;
	private final DatabaseClient databaseClient; // Ensure DatabaseClient is final and injected

	public TokenStaticController(TokenStaticRepository tokenStaticRepository, DatabaseClient databaseClient) {
		this.tokenStaticRepository = tokenStaticRepository;
		this.databaseClient = databaseClient; // Initialize DatabaseClient
	}

	@GetMapping("/{name}")
	public Flux<TokenStaticEntity> getByNameMono(@PathVariable String name) {
		return databaseClient.sql("SELECT * FROM static_token WHERE name = :name")
				.bind("name", name)
				.map((row, metadata) -> {
					// Use TokenStaticEntity instead of TokenStatic
					TokenStaticEntity tokenStaticEntity = new TokenStaticEntity();
					tokenStaticEntity.setId(row.get("id", Long.class));
					tokenStaticEntity.setName(row.get("name", String.class));
					tokenStaticEntity.setToken(row.get("token", String.class));
					return tokenStaticEntity; // Return the correct entity type
				})
				.all();
	}
}
