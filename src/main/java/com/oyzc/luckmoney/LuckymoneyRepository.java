package com.oyzc.luckmoney;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName LuckymoneyRepository
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/8 下午8:01
 * @Version 1.0
 */
public interface LuckymoneyRepository extends JpaRepository<Luckymoney, Integer> {
}
