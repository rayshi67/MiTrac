package com.jiesoft.mitrac.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The security manager that performs all the necessary authentication and authorization.
 *
 * @author Ray Shi
 */

@Service("securityManager")
public class SecurityManager implements UserDetailsService {

    private static Log LOG = LogFactory.getLog(SecurityManager.class);
    
    /** The user details service */
//    @Autowired
//    private UserDao userDao;

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
            LOG.debug("loadUserByUsername called [username=" + username + "]");
        }

//		com.model.User user = userDao.findByUserName(username);
//		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
// 
//		return buildUserForAuthentication(user, authorities);
        
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
        return new User(
        		"roy",
        		"spring", 
        		true,
        		true, 
        		true, 
        		true, authorities);
    }

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
//	private User buildUserForAuthentication(com.mkyong.users.model.User user, 
//		List<GrantedAuthority> authorities) {
//		return new User(user.getUsername(), user.getPassword(), 
//			user.isEnabled(), true, true, true, authorities);
//	}
// 
//	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
// 
//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
// 
//		// Build user's authorities
//		for (UserRole userRole : userRoles) {
//			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//		}
// 
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
// 
//		return Result;
//	}

}
