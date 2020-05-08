package com.oyzc.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName LuckymoneyController
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/8 下午8:00
 * @Version 1.0
 */
@RestController
public class LuckymoneyController {

    private LuckymoneyRepository repository;

    private LuckymoneyService service;

    @Autowired
    public void setRepository(LuckymoneyRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setService(LuckymoneyService service) {
        this.service = service;
    }

    /**
     * 获取红包列表
     */
    @GetMapping("/luckymoneys")
    public List<Luckymoney> list() {
        return repository.findAll();
    }

    /**
     * 创建红包
     */
    @PostMapping("/luckymoneys")
    public Luckymoney create(@RequestParam("producer") String producer,
                             @RequestParam("money") BigDecimal money) {
        Luckymoney luckymoney = new Luckymoney();
        luckymoney.setProducer(producer);
        luckymoney.setMoney(money);
        return repository.save(luckymoney);
    }

    /**
     * 通过ID查询红包
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 更新红包（领红包）
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckymoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer) {
        Optional<Luckymoney> optional = repository.findById(id);
        if (optional.isPresent()) {
            Luckymoney luckymoney = optional.get();
            luckymoney.setId(id);
            luckymoney.setConsumer(consumer);
            return repository.save(luckymoney);
        }
        return null;
    }

    @PostMapping("/luckymoneys/two")
    public void createTwo() {
        service.createTwo();
    }
}
