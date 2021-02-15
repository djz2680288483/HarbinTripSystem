package com.djz;

import com.djz.entity.Demo;
import com.djz.entity.User;
import com.djz.mapper.UserMapper;
import com.djz.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HarbinTripSystemApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    private UserMapper userMapper;

    void contextLoads() {
        Demo demo = new Demo(1001L, "李四", 21);
        redisUtils.set("1001", demo);

        System.out.println(redisUtils.get("1001"));
    }

    @Test
    void contextLoads2() {
        User user = new User();
        user.setId(3L);
        user.setName("admin");
        user.setPassword("admin");
        user.setQq("2680288483");
        user.setTelephone("15186001530");
      int b=  userMapper.updateById(user);
        System.out.println("==========="+b+"===========");

    }

}
