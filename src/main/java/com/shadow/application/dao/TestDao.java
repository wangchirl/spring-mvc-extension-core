package com.shadow.application.dao;


import com.shadow.application.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
public interface TestDao {

    void save(User user);
}
