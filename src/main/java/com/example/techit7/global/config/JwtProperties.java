package com.example.techit7.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	private final String SECRET_KEY;

}