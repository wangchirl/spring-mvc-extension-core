package com.shadow.application.service.impl;

import com.shadow.application.dao.TestDao;
import com.shadow.application.entity.User;
import com.shadow.application.service.TestService;
import com.shadow.utils.ConsolePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shadow
 * @create 2021-04-03
 * @description
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestDao testDao;

    @Override
    @Transactional
    public void save(User user) {
        testDao.save(user);
        ConsolePrinter.printlnPurple("service 所属容器：" + this.applicationContext.hashCode());
    }

}
