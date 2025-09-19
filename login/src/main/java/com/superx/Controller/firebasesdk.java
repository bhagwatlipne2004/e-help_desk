package com.superx.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;
import java.io.IOException;

public class firebasesdk {

    public static void initialize() {
        try {

            String fileName = "login-signup-971fe-firebase-adminsdk-fbsvc-9821d104be.json";

            InputStream serviceAccount = firebasesdk.class.getResourceAsStream("/" + fileName);

            if (serviceAccount == null) {
                throw new IOException(fileName + " not found on the classpath. Make sure it's in src/main/resources.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))

                    .setStorageBucket("login-signup-971fe.firebasestorage.app")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}