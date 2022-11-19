package me.zakaria.billingservice.web;

import me.zakaria.billingservice.MyConsolConfig;
import me.zakaria.billingservice.MyVaultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigRestController {

    @Autowired
    private MyConsolConfig myConsolConfig;

    @Autowired
    private MyVaultConfig myVaultConfig;



    @GetMapping("/myConfig")
    public MyConsolConfig myConfig(){
        return myConsolConfig;
    }


    @GetMapping("/config")
    public Map<String, Object> getConfig(){
        return Map.of("vault.user",myVaultConfig,"consul.token", myConsolConfig);
    }

}
