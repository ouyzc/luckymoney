package com.oyzc.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/6 下午9:03
 * @Version 1.0
 */
@RestController
public class HelloController {

    private LimitConfig limitConfig;

    @Autowired
    public void setLimitConfig(LimitConfig limitConfig) {
        this.limitConfig = limitConfig;
    }

    @GetMapping("/hello")
    public String say() {
        return "说明:" + limitConfig.getDescription();
    }
}
