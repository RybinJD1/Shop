package com.services;

import com.entity.Account;

/**
 * Interface AccountService
 */
public interface AccountService {

    /**
     * This method find account by user name
     *
     * @param userName
     * @return
     */
    Account findAccount(String userName);

}
