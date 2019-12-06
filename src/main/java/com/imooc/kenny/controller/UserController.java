package com.imooc.kenny.controller;

import com.imooc.kenny.model.User;
import com.imooc.kenny.rabbitmq.MQSender;
import com.imooc.kenny.redis.RedisService;
import com.imooc.kenny.result.Result;
import com.imooc.kenny.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: UserController
 * Function:  用户控制器层
 * Date:      2019/11/28 14:27
 * @author     Kenny
 * version    V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

	@RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
		sender.send("hello,imooc");
        return Result.success("Hello，world");
    }



    @RequestMapping("/info")
    @ResponseBody
    public Result<User> info( User user) {
        return Result.success(user);
    }
}
