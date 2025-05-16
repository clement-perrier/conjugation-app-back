package com.app.conjugation.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import org.springframework.stereotype.Service;

@Service
public class SesEmailService {

    private final SesClient sesClient;

    public SesEmailService() {
        this.sesClient = SesClient.builder()
                .region(Region.US_WEST_1) // Change to your SES region
                .build(); // Uses IAM Role credentials from EC2 (Elastic Beanstalk)
    }

    public void sendEmail(String to, String from, String subject, String bodyText) {
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Message message = Message.builder()
                .subject(Content.builder().data(subject).build())
                .body(Body.builder().text(Content.builder().data(bodyText).build()).build())
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(from)
                .build();

        sesClient.sendEmail(request);
    }
}
