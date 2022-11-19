package me.zakaria.billingservice;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Data
public class MyConsolConfig {


    private long accessTokenTimeout;

    private long refreshTokenTimeout;
}
