package ru.warmouse.limiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.warmouse")
public class Application
{
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
}
