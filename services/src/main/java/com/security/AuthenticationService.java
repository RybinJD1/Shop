package com.security;

import com.dao.AccountDao;
import com.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(AuthenticationService.class);

    @Autowired
    @Qualifier("AccountDao")
    private AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.findAccount(username);
        log.info("Account= " + account);
        if (account == null) {
            throw new UsernameNotFoundException("User "
                    + username + " was not found in the database");
        }
        String role = account.getUserRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        grantList.add(authority);
        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = (UserDetails) new User(account.getUserName(),
                account.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantList);
        return userDetails;
    }
}
