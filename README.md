## 创建SpringBoot项目

![1](image/1.png)

![2](image/2.png)

![3](image/3.png)

![4](image/4.png)

创建一个Controller类

```java
package com.oyzc.luckmoney;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String say() {
        return "springboot";
    }
}
```

IDEA启动项目

### 使用命令打包成jar包启动项目

进入项目目录，执行如下命令打包，在target目录下会生成jar文件

>  mvn clean package

使用命令启动

> Java -jar target/文件名

### 配置

在application.properties修改端口，项目路径

```xml
server.port=8081
server.servlet.context-path=/luckymoney
```

(推荐写法)新建一个application.yml文件

```xml
server:
  port: 8081
  servlet:
    context-path: /luckymoney
```

可以在文件中配置变量

```xml
minMoney: 1
maxMoney: 200
description: 最少发${limit.minMoney}元，最多${limit.maxMoney}元
```

在类中使用注解获取变量

```java
@Value("${minMoney}")
private BigDecimal minMoney;
```

变量多可以使用对象配置

```xml
limit:
  minMoney: 1
  maxMoney: 200
  description: 最少发${limit.minMoney}元，最多${limit.maxMoney}元
```

创建一个limitConfog类，使用三个注解

```java
package com.oyzc.luckmoney;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@EnableConfigurationProperties(LimitConfig.class)
@ConfigurationProperties(prefix = "limit")
public class LimitConfig {

    private BigDecimal minMoney;

    private BigDecimal maxMoney;

    private String description;

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

在Controller类中使用对象

```java
private LimitConfig limitConfig;

@Autowired
public void setLimitConfig(LimitConfig limitConfig) {
		this.limitConfig = limitConfig;
}
```

#### 开发环境的配置文件和生产环境不一样，可以这样配置

复制application.yml文件改为application-dev.yml(开发环境)

```xml
server:
  port: 8081
  servlet:
    context-path: /luckymoney

limit:
  minMoney: 0.01
  maxMoney: 200
  description: 最少发${limit.minMoney}元，最多${limit.maxMoney}元
```

复制application.yml文件改为application-prod.yml(生产环境)

```xml
server:
  port: 8081
  servlet:
    context-path: /luckymoney

limit:
  minMoney: 1
  maxMoney: 200
  description: 最少发${limit.minMoney}元，最多${limit.maxMoney}元
```

application.yml配置如下：

```xml
spring:
  profiles:
    active: dev
```

如果要不改变application.yml配置文件的情况下使用生产环境的配置

先打包成jar包

使用如下命令启动项目

> Java -jar -Dspring.profiles.active=prod target/文件名

| 注解            | 解释                                                         |
| --------------- | ------------------------------------------------------------ |
| @Controller     | 处理http请求                                                 |
| @RestController | Spring4之后新加的注解，原来返回json需要@ResponseBody配合@Controller |
| @RequestMapping | 配置url映射                                                  |

Restful风格 

```java
@GetMapping("/hello/{id}")
public String say(@PathVariable Integer id) {
		return "说明:" + limitConfig.getDescription();
}
```

如果是用/hello?id=100来传值

```java
@GetMapping("/hello")
public String say(@RequestParam("id") Integer id) {
		return "说明:" + limitConfig.getDescription();
}
```

不传ID会报错，怎么让ID不传也可以执行呢，required为false，加上默认值

```java
@RequestParam(value = "id", required = false, defaultValue = "0")
```

使用Post传值

```java
@PostMapping("/hello")
```

同样使用@RequestParam，不管是url传值还是body传值都可以识别到

```java
@RequestParam(value = "id", required = false, defaultValue = "0")
```

## 使用jpa整合hibernate操作数据库

### 导入依赖

使用Springboot不需要指定版本，下面这句表示继承父类版本

```xml
<relativePath/> <!-- lookup parent from repository -->
```

```xml
<!--jpa依赖-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!--mysql依赖-->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
```

数据库中创建表空间

```mysql
create database luckymoney
```

### 在application.yml中配置数据库

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/luckymoney
    username: root
    password: 12345678
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
```

### 创建数据库对象类

```java
package com.oyzc.luckmoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 数据库对象类：luckymoney
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
```

启动项目，会自动创建luckymony表

往表中插入一条数据，重新启动项目会将数据清除，原因是配置文件中为create，启动时会将原有的表删除，重新创建一个新表，改为update就不会重新创建。

### 创建一个数据库操作类

```java
package com.oyzc.luckmoney;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckymoneyRepository extends JpaRepository<Luckymoney, Integer> {
  
}
```

### 创建controller类

```java
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

    @Autowired
    public void setRepository(LuckymoneyRepository repository) {
        this.repository = repository;
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
}
```

findById方法返回的是Optional，需要调用orElse方法返回对象

Optional的说明查看[链接](https://www.runoob.com/java/java8-optional-class.html)

### 插入数据出现中文乱码

在配置文件url后面加上

```yml
url: jdbc:mysql://localhost:3306/luckymoney?characterEncoding=utf-8
```

### 事务管理

创建一个service类

将数据库表money字段长度修改为5

使用`@Transactional`注解开启事务

```java
package com.oyzc.luckmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
```

### 开始事务无效的解决方法

开启了事务500那条数据还是插入了，因为hibernate创建表的默认引擎是`myisam`，不支持事务，需要将引擎改为`Innodb`，也可以添加下面的方言修改默认引擎

```yml
jpa:
	hibernate:
    ddl-auto: create
  show-sql: true
  database-platform: org.hibernate.dialect.MySQL5InnoDBDialect # 修改默认引擎为InnoDB
```

