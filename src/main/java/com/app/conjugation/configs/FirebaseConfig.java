package com.app.conjugation.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

//    @PostConstruct
//    public void initialize() {
//        try {
//            InputStream serviceAccount =
//                    getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");
//
//            if (serviceAccount == null) {
//                throw new IllegalStateException("Firebase service account file not found");
//            }
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//            System.out.println("FirebaseApp initialized successfully.");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to initialize FirebaseApp", e);
//        }
//    }
}
