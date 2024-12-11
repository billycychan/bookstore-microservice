package com.billycychan.notifications;

import org.springframework.boot.SpringApplication;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(NotificationServiceApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
