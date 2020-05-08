package com.oyzc.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @ClassName LuckymoneyService
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/8 下午9:05
 * @Version 1.0
 */
@Service
public class LuckymoneyService {

    private LuckymoneyRepository repository;

    @Autowired
    public void setRepository(LuckymoneyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createTwo() {
        Luckymoney luckymoneyone =new Luckymoney();
        luckymoneyone.setProducer("帅哥");
        luckymoneyone.setMoney(new BigDecimal(520));
        repository.save(luckymoneyone);

        Luckymoney luckymoneytwo =new Luckymoney();
        luckymoneytwo.setProducer("帅哥");
        luckymoneytwo.setMoney(new BigDecimal(1314));
        repository.save(luckymoneytwo);
    }
}
