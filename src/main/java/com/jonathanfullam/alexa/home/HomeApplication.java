package com.jonathanfullam.alexa.home;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by jfullam on 5/22/16.
 */

@SpringBootApplication
public class HomeApplication {

    @Bean
    public ServletRegistrationBean dispatcherRegistration(Speechlet speechlet) {

        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(speechlet);

        ServletRegistrationBean registration = new ServletRegistrationBean(
                speechletServlet);
        registration.addUrlMappings("/home");
        return registration;
    }



    public static void main(String[] args) {

        SpringApplication.run(HomeApplication.class, args);
    }
}
