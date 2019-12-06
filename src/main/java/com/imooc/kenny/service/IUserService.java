package com.imooc.kenny.service;

import com.imooc.kenny.model.User;
import com.imooc.kenny.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: IUserService
 * Function:  用户服务层接口
 * Date:      2019/11/26 9:14
 * author     Kenny
 * version    V1.0
 */
public interface IUserService {

    User getById(long id);

    User getByToken(HttpServletResponse response,String token);

    String login(HttpServletResponse response, LoginVo loginVo);


}
