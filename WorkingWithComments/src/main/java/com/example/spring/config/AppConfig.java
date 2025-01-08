package com.example.spring.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String name;
    private int maxConnections;
    private boolean enableFeature;

    @PostConstruct
    public void printConfig() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "name='" + name + '\'' +
                ", maxConnections=" + maxConnections +
                ", enableFeature=" + enableFeature +
                '}';
    }
}
