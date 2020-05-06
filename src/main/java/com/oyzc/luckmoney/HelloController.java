package com.oyzc.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/hello")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return "id：" + id;
    }
}
