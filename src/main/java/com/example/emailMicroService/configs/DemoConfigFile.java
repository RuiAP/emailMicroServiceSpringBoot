package com.example.emailMicroService.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//Only for learning purposes
@Component
@ConfigurationProperties("demo.configfile")
public class DemoConfigFile {

    //This class attributes can be defined in the application.properties file using "demo.configfile.<attributeName>"
    private String name;
    private String email;

    public DemoConfigFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
