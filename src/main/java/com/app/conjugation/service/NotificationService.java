package com.app.conjugation.service;

import com.app.conjugation.model.Batch;
import com.app.conjugation.repository.BatchRepository;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
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

//    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 19 * * *") 
    public void sendDueNotifications() {
//        // Fetch due tasks from the database
//        List<Task> dueTasks = getDueTasks();
    	   List<Batch> dueBatches = batchRepository.findDueBatches();
    	   for(Batch dueBatch: dueBatches) {
    		   System.out.println(dueBatch.getUser().getEmail() + " - " + dueBatch.getUser().getId());
    		   String deviceToken = dueBatch.getUser().getDeviceToken();
    		   String learningLanguage = dueBatch.getUserLearningLanguage().getLearningLanguage().getName();
    		   sendNotification(
				   deviceToken, 
				   "Reminder: Repetition(s) Due Today", 
				   "Keep up your progress! Complete your repetitions today to reinforce what you've learned."
			   );
    	   }
    	   
//
//        // Iterate over tasks and send notifications
//        for (Task task : dueTasks) {
        	String token = "ebgKkmtWQfiJKLOKeEywRH:APA91bGV_6u0L4ls7Us3l8Fy_82fQZT3Y0l6Ee_CMPQ-uWny2I4h-xY0JZI4798YsAAfaCBGT0tuUrgreNAMf5-PXyb00029om0I4U1yOg_lVQ2X_soEb5F7HW8grK2ARFeLzmbivCHi";
//            sendNotification(token, "Task Due", "Your task is due today!");
//        }
    }

    private void sendNotification(String deviceToken, String title, String body) {
    	
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Batch> getDueBatches() {
        
    	
        return new ArrayList<>();
    }
}
