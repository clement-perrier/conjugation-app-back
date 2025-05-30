package com.app.conjugation.service;

import com.app.conjugation.model.Batch;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.BatchConjugationRepository;
import com.app.conjugation.repository.BatchRepository;
import com.app.conjugation.repository.UserRepository;
import com.google.firebase.messaging.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class NotificationService {
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private UserService userService;

    @Autowired
	private UserRepository userRepository;

    @Scheduled(cron = "35 48 16 * * *")
    public void sendDueNotifications() {
        // Retrieving users and learning language name with at least one due batch
        List<BatchRepository.UserLanguageInfo> usersWithDueBatch = batchRepository.findUserDeviceTokenAndLanguageNameForDueBatches();
        // Sending push notification to each user learning language with due batch(es)
        for (BatchRepository.UserLanguageInfo user: usersWithDueBatch){
            System.out.println("Sending notification to: " + user.getUserEmail());
            sendExpoNotification(user.getDeviceToken(), user.getLanguageName());
        }
    }

    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    public void sendExpoNotification(String expoPushToken, String languageName) {
        if (expoPushToken == null || !expoPushToken.startsWith("ExponentPushToken")) {
            System.out.println("Invalid Expo push token: " + expoPushToken);
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");

        Map<String, Object> message = new HashMap<>();
        message.put("to", expoPushToken);
        String flag = getFlagEmoji(languageName);
        message.put("title", flag + " Repetition(s) in " + languageName + " Due Today");
        message.put("body", "Complete your repetition(s) today to stay on track!");
        message.put("sound", "default");
        message.put("priority", "high");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(EXPO_PUSH_URL, request, String.class);
            System.out.println("Expo notification sent. Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Error sending Expo notification:");
            // e.printStackTrace();
        }
    }

    public String getFlagEmoji(String languageName) {
        return switch (languageName.toLowerCase()) {
            case "french" -> "\uD83C\uDDEB\uD83C\uDDF7"; // 🇫🇷
            case "spanish" -> "\uD83C\uDDEA\uD83C\uDDF8"; // 🇪🇸
            case "german" -> "\uD83C\uDDE9\uD83C\uDDEA"; // 🇩🇪
            case "italian" -> "\uD83C\uDDEE\uD83C\uDDF9"; // 🇮🇹
            default -> ""; // fallback: no flag
        };
    }


    private void sendNotification(String deviceToken, Integer userId) {
    	
    	Notification notification = Notification.builder()
                .setTitle("Reminder: Repetition(s) Due Today")
                .setBody("Keep up your progress! Complete your repetitions today to reinforce what you've learned.")
                .build();
    	
        Message message = Message.builder()
        		.setNotification(notification)
                .setToken(deviceToken)
                // Android config
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                // ios config
                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setAlert("Keep up your progress! Complete your repetitions today to reinforce what you've learned.")
                                .setSound("default")
                                .setBadge(1)
                                .build())
                        .build())

                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
        	System.out.println("Device token: " + deviceToken);
            if (e.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) {
            	System.out.println("Token unregistered, removing from database.");
            	// Remove the invalid token from your database
            	userService.removeUserDeviceToken(userId);
            } else {
                // Handle other exceptions
            	System.out.println("Failed to send FCM notification");
            	System.out.println(e);
            }
        }
    }

}
