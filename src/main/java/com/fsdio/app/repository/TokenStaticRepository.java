package com.fsdio.app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fsdio.app.entity.TokenStaticEntity;

import reactor.core.publisher.Flux;

public interface TokenStaticRepository extends ReactiveCrudRepository<TokenStaticEntity, Long> {
	Flux<TokenStaticEntity> findByName(String name);
}
