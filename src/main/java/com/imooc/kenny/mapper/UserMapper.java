package com.imooc.kenny.mapper;

import com.imooc.kenny.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: UserMapper
 * Function:  TODO
 * Date:      2019/11/26 10:01
 * author     Kenny
 * version    V1.0
 */
public interface UserMapper {

    @Select("SELECT * FROM miaosha_user WHERE id = #{id}")
    User getById(@Param("id") long id);
}
