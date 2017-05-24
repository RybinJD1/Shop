package com.services;

import com.entity.Account;
import com.services.exceptions.ServiceException;

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
    Account findAccount(String userName) throws ServiceException;

}
