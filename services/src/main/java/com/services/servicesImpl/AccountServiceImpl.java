package com.services.servicesImpl;

import com.dao.AccountDao;
import com.entity.Account;
import com.services.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger log = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDao accountDao;

    @Override
    public Account findAccount(String userName) {
        log.info("findAccount:" + userName);
        return accountDao.findAccount(userName);
    }
}
