package com.dao;

import com.entity.Account;

/**
 * Interface methods Account
 */
public interface AccountDao {

    /**
     * This method find account by user name
     *
     * @param userName
     * @return Account
     */
    Account findAccount(String userName);
}
