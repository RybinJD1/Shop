package com.dao.impl;

import com.dao.AccountDao;
import com.entity.Account;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class DAO for accounts.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    private static final Logger log = Logger.getLogger(AccountDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account findAccount(String userName) {
        log.info("findAccount to dao :" + userName);
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Account.class);
        crit.add(Restrictions.eq("userName", userName));
        return (Account) crit.uniqueResult();
    }
}
