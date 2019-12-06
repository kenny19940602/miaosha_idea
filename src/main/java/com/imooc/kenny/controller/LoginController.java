package com.imooc.kenny.controller;

import com.imooc.kenny.result.Result;
import com.imooc.kenny.service.IUserService;
import com.imooc.kenny.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * ClassName: LoginController
 * Function:  登录
 * Date:      2019/10/16 9:44
 * @author     Kenny
 * version    V1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        LOGGER.info(loginVo.toString());
        /**
         * 登录
         */
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }

}
