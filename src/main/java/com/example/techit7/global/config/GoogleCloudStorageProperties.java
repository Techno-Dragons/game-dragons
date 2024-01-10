package com.example.techit7.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "gcp")
public class GoogleCloudStorageProperties {

	private final String BUCKET_NAME;

	private final String PROJECT_ID;

}
