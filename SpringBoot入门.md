## 创建SpringBoot项目

![屏幕快照 2020-05-06 下午9.10.35](/Users/ouyangzicheng/Desktop/屏幕快照 2020-05-06 下午9.10.35.png)

![屏幕快照 2020-05-06 下午9.11.39](/Users/ouyangzicheng/Desktop/屏幕快照 2020-05-06 下午9.11.39.png)

![屏幕快照 2020-05-06 下午9.12.08](/Users/ouyangzicheng/Desktop/屏幕快照 2020-05-06 下午9.12.08.png)

![屏幕快照 2020-05-06 下午9.12.44](/Users/ouyangzicheng/Desktop/屏幕快照 2020-05-06 下午9.12.44.png)

创建一个Controller类

```java
package com.oyzc.luckmoney;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ouyangzicheng
 * @Date 2020/5/6 下午9:03
 * @Version 1.0
 */
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

