package com.myRetail.products.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.myRetail.products.configuration.YAMLConfig;

@Service
public class FirebaseInitialize {

	@Autowired
	private YAMLConfig yamlConfig;

	@PostConstruct
	public void initialize() {
		try {
			FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl(yamlConfig.getSource())
					.build();

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
