package com.imooc.kenny.service.impl;

import com.imooc.kenny.exception.GlobalException;
import com.imooc.kenny.mapper.UserMapper;
import com.imooc.kenny.model.User;
import com.imooc.kenny.redis.RedisService;
import com.imooc.kenny.redis.UserKey;
import com.imooc.kenny.service.IUserService;
import com.imooc.kenny.result.CodeMsg;
import com.imooc.kenny.util.MD5Util;
import com.imooc.kenny.util.UUIDUtil;
import com.imooc.kenny.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: UserServiceImpl
 * Function:  用户服务层实现类
 * Date:      2019/11/26 9:15
 * @author     Kenny
 * version    V1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public User getById(long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.getToken, token, User.class);
        /**
         * 延长有效期
         */
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    @Override
    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        /**
         * 判断手机号是否存在
         */
        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXITS);
        }
        /**
         * 验证密码
         */
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        /**
         * 生成cookie
         */
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.getToken, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.getToken.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
