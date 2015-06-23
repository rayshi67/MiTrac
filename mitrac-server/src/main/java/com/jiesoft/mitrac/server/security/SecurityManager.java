package com.jiesoft.mitrac.server.security;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jiesoft.mitrac.common.Pair;
import com.jiesoft.mitrac.domain.bo.Account;
import com.jiesoft.mitrac.dao.AccountDao;
import com.jiesoft.mitrac.dao.UserDao;

/**
 * The security manager that performs all the necessary authentication and authorization.
 *
 * @author Ray Shi
 */

public class SecurityManager implements UserDetailsService {

    private static Log LOG = LogFactory.getLog(SecurityManager.class);
    
    private static final String USER_ACCOUNT_SEPARATOR = "@";
    
    private UserDao userDao;

    private AccountDao accountDao;
    
    /**
     * Default constructor.
     */
    public SecurityManager() {
    }

    @Transactional(readOnly=true)
    @Override
    final public UserDetails loadUserByUsername(final String username)
    		throws UsernameNotFoundException {
    	
        if (LOG.isDebugEnabled()) {
            LOG.debug("called loadUserByUsername");
            LOG.debug("username=" + username);
        }
        
        // load user and account
        
    	Pair<com.jiesoft.mitrac.domain.bo.User, Account> userAccountPair = checkForUserAccount(username);
        
    	// validate user and account

    	final com.jiesoft.mitrac.domain.bo.User user = userAccountPair.getLeft();
    	final Account account = userAccountPair.getRight();
        
        if (account == null) {  // no account
        	final String msg = "account not exist";
        	LOG.info(msg);
            throw new UsernameNotFoundException(msg);
        }
        
        if (user == null) {  // no user
        	final String userId = StringUtils.substringBefore(username, USER_ACCOUNT_SEPARATOR);
        	if (StringUtils.isNotEmpty(userId)) {  // username exist
            	final String msg = "user not exist";
            	LOG.info(msg);
                throw new UsernameNotFoundException(msg);
        	}
        }

        boolean accountNonExpired = true;
        if (account.getExpirationTime() > 0) {
        	accountNonExpired = account.getExpirationTime() > new Date().getTime();
        }
      
        String password = null;
        boolean enabled = true;
        		
        if (user == null) {  // no user
            password = account.getPassword();
            enabled = account.getIsActive() > 0 ? true : false;
        } else {
            password = user.getPassword();
            enabled = user.getIsActive() > 0 ? true : false;
        }
        
        // load authorities
		// add role required to access the REST urls        
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_REST"));
		
        return new User(
        		username,
        		password, 
        		enabled,
        		accountNonExpired, 
        		true, 
        		true,
        		authorities);
    }

    /**
     * Check for the user ID and the account ID in the given username.
     * The username should be one of the following two formats:
     *     1) ray@jiesoft -- the user ID 'ray' with the account ID 'jiesoft'
     *     2) ray -- the user ID
     *
     * @param userAccountId
     * @return a Pair<User, Account> object
     */
    private Pair<com.jiesoft.mitrac.domain.bo.User, Account> checkForUserAccount(final String userAccountId) {
    	if (!StringUtils.contains(userAccountId, USER_ACCOUNT_SEPARATOR)) {  // no account
    		final com.jiesoft.mitrac.domain.bo.User user = userDao.findByUserName(userAccountId);
    		
    		if (user == null) {
    			return new Pair<com.jiesoft.mitrac.domain.bo.User, Account>(null, null);
    		}
    		
    		final Account account = accountDao.findByAccountName(user.getId().getAccountId());
    		
    		return new Pair<com.jiesoft.mitrac.domain.bo.User, Account>(user, account);
    	}

    	com.jiesoft.mitrac.domain.bo.User user = null;
    	Account account = null;
    	
		final String userId = StringUtils.substringBefore(userAccountId, USER_ACCOUNT_SEPARATOR);
		
    	if (StringUtils.isEmpty(userId)) {  // no username
    		user = null;
    	} else {
    		user = userDao.findByUserName(userId);
    	}
		
		final String accountId = StringUtils.substringAfter(userAccountId, USER_ACCOUNT_SEPARATOR);
		
    	if (StringUtils.isEmpty(accountId)) {  // no account
    		account = null;
    	} else {
    		account = accountDao.findByAccountName(accountId);
    	}
		
    	return new Pair<com.jiesoft.mitrac.domain.bo.User, Account>(user, account);
    }
    
	public void setUserDao(final UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAccountDao(final AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
