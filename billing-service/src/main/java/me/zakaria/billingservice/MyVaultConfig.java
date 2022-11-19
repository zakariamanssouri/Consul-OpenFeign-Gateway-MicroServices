package me.zakaria.billingservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user")
@Data
public class MyVaultConfig {


    private String username;

    private String password;

    private String otp;
}
