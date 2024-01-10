package com.example.techit7.global.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GoogleCloudStorageConfig {
	private final GoogleCloudStorageProperties googleCloudStorageProperties;

	@Bean
	public Storage googleCloudStorage() throws IOException {
		ClassPathResource resource = new ClassPathResource(googleCloudStorageProperties.getBUCKET_NAME() + ".json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
		String projectId = googleCloudStorageProperties.getPROJECT_ID();
		return StorageOptions.newBuilder()
			.setProjectId(projectId)
			.setCredentials(credentials)
			.build()
			.getService();
	}
}