package com.fsdio.app.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

	@GetMapping(value = "/cookie", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Map<String, Object>> getCookie(
			@CookieValue(value = "myCookie", required = false) String cookieValue,
			ServerHttpResponse response) {
		// Menambahkan cookie
		ResponseCookie cookie = ResponseCookie.from("myCookie", "crunc")
				.path("/")
				.build();
		response.addCookie(cookie);

		// Membuat struktur data untuk respons
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("message", "Cookie set and retrieved");
		responseMap.put("status", "success");
		responseMap.put("timestamp", LocalDateTime.now());
		responseMap.put("cookieValue", cookieValue != null ? cookieValue : "Cookie not found");

		return Mono.just(responseMap);
	}

	@PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Map<String, Object>> postData(@RequestBody Map<String, String> data) {
		Map<String, Object> response = new HashMap<>();
		response.put("receivedData", data);
		response.put("timestamp", LocalDateTime.now());
		return Mono.just(response);
	}

	@GetMapping(value = "/data/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Map<String, Object>> getData(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		response.put("id", id);
		response.put("timestamp", LocalDateTime.now());
		return Mono.just(response);
	}

	@GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Map<String, Object>> getQueryParam(@RequestParam String name) {
		Map<String, Object> response = new HashMap<>();
		response.put("name", name);
		response.put("timestamp", LocalDateTime.now());
		return Mono.just(response);
	}
}