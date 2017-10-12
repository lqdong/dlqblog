package com.dlq.blog.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author donglq
 * @date 2017/10/4 10:13
 */
public interface UserDao {

    Object insert(@Param("user") User user);

    User select(@Param("user") User user);

}
