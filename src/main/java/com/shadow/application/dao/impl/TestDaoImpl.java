package com.shadow.application.dao.impl;

import com.shadow.application.dao.TestDao;
import com.shadow.application.entity.User;
import com.shadow.utils.ConsolePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void save(User user) {
        ConsolePrinter.printlnYellow("save user : " + user );
        ConsolePrinter.printlnYellow("dao 所属容器：" + this.applicationContext.hashCode());
    }

}
