package me.zakaria.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//annotation to refresh the config
@RefreshScope
public class CustomerConfigTestController {

    // inject a property from application.properties
    @Value("${customre.params.p1}")
    private String p1;
    @Value("${customre.params.p1}")
    private String p2;
    @Value("${global.params.p1}")
    private String x;
    @Value("${global.params.p2}")
    private String y;


    //generate Map String String
    @GetMapping("/params")
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("p1", p1);
        params.put("p2", p2);
        params.put("x", x);
        params.put("y", y);
        return params;
    }



}
