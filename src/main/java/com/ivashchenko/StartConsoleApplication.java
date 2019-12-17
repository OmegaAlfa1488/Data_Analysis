package com.ivashchenko;

import com.ivashchenko.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class StartConsoleApplication implements CommandLineRunner {

    @Autowired
    private MessageService helloService;


    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(StartConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        helloService.call_me();

        exit(0);

    }
}