package com.app.conjugation.service;

import com.app.conjugation.model.Batch;
import com.app.conjugation.model.User;
import com.app.conjugation.repository.BatchRepository;
import com.app.conjugation.repository.UserRepository;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.google.firebase.messaging.Notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private UserService userService;

//    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 20 * * *") 
    public void sendDueNotifications() {
//        // Fetch due tasks from the database
//        List<Task> dueTasks = getDueTasks();
    	   List<Batch> dueBatches = batchRepository.findDueBatches();
    	   for(Batch dueBatch: dueBatches) {
    		   User user = dueBatch.getUser();
    		   System.out.println(user.getEmail() + " - " + user.getId());
    		   String deviceToken = user.getDeviceToken();
    		   String learningLanguage = dueBatch.getUserLearningLanguage().getLearningLanguage().getName();
    		   sendNotification(
				   deviceToken, 
				   "Reminder: Repetition(s) Due Today", 
				   "Keep up your progress! Complete your repetitions today to reinforce what you've learned.",
				   user.getId()
			   );
    	   }
    }

    private void sendNotification(String deviceToken, String title, String body, Integer userId) {
    	
    	Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    	
        Message message = Message.builder()
        		.setNotification(notification)
                .setToken(deviceToken)
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
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

    private List<Batch> getDueBatches() {
        
    	
        return new ArrayList<>();
    }
}
