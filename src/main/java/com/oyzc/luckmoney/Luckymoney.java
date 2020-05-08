package com.oyzc.luckmoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName Luckymoney
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/8 下午7:55
 * @Version 1.0
 */
@Entity
public class Luckymoney {

    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal money;

    // 发送方
    private String producer;

    // 接收方
    private String consumer;

    public Luckymoney() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }
}
