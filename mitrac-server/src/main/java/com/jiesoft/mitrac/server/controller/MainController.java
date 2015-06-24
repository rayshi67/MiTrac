/*
 * Copyright 2015 Jiesoft Consulting.
 * All Rights Reserved.
 *
 * All information contained herein is, and remains the property
 * of Jiesoft Consulting. The intellectual and technical concepts 
 * contained herein are proprietary to Jiesoft and are protected by 
 * trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless 
 * prior written permission is obtained from Jiesoft Consulting.
 *
 *      http://www.jiesoft.com
 */
package com.jiesoft.mitrac.server.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiesoft.mitrac.common.ResultCodeEnum;
import com.jiesoft.mitrac.dao.AccountDao;
import com.jiesoft.mitrac.dao.UserDao;
import com.jiesoft.mitrac.domain.bo.Account;
import com.jiesoft.mitrac.message.HomeMessage;
import com.jiesoft.mitrac.server.security.SecurityManager;

/**
 * Handles requests for the MiTrac REST requests.
 * 
 * @author Ray Shi
 */
@Controller
@RequestMapping("/mitrac")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
    private UserDao userDao;

	@Autowired
    private AccountDao accountDao;

	@Transactional(readOnly=true)
	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeMessage getAccountUserDevices(Authentication authentication) {
		logger.debug("call getDevices:");
		
		// load user
		
		final String userId = StringUtils.substringBefore(authentication.getName(), SecurityManager.USER_ACCOUNT_SEPARATOR);

    	logger.debug("auth userId = " + userId);
    	
    	com.jiesoft.mitrac.domain.bo.User user = null;
   
    	if (StringUtils.isEmpty(userId)) {  // no username
    		user = null;
    	} else {
    		user = userDao.findByUserName(userId);
    		user.setPassword(null);    		
    	}
    	
    	// load account

		String accountId = StringUtils.substringAfter(authentication.getName(), SecurityManager.USER_ACCOUNT_SEPARATOR);
		
		if (user != null) {
			accountId = user.getId().getAccountId();
		}
		
    	logger.debug("auth accountId = " + accountId);
    	
		Account account = accountDao.findByAccountName(accountId);

		HomeMessage message = new HomeMessage(ResultCodeEnum.Success, null);
		
		message.setUser(user);
		message.setAccount(account);
		
		// TODO load devices
		
		return message;
	}

}
