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

import java.util.ArrayList;
import java.util.List;

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
import com.jiesoft.mitrac.dao.DeviceGroupDao;
import com.jiesoft.mitrac.dao.UserDao;
import com.jiesoft.mitrac.dao.UserDeviceGroupDao;
import com.jiesoft.mitrac.domain.bo.Account;
import com.jiesoft.mitrac.domain.bo.DeviceGroup;
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
	
	@Autowired
    private UserDeviceGroupDao userDeviceGroupDao;

	@Autowired
    private DeviceGroupDao deviceGroupDao;
	
	@Transactional(readOnly=true)
	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeMessage getAccountUserDevices(Authentication authentication) {
		logger.debug("call getDevices:");
		
		// load user
		
		final String userId = StringUtils.substringBefore(authentication.getName(), SecurityManager.USER_ACCOUNT_SEPARATOR);
		final String accountId = StringUtils.substringAfter(authentication.getName(), SecurityManager.USER_ACCOUNT_SEPARATOR);
		
    	logger.debug("auth userId = " + userId);
    	logger.debug("auth accountId = " + accountId);
    	
    	com.jiesoft.mitrac.domain.bo.User user = null;
    	Account account = null;
   
    	if (StringUtils.isEmpty(userId)) {  // only account name
    		account = accountDao.findByAccountName(accountId);
    	} else {
    		if (StringUtils.isEmpty(accountId)) {  // only username that is unique
    			final List<com.jiesoft.mitrac.domain.bo.User> users = userDao.findByUserName(userId);
    			user = users.get(0);	
    		} else {
    			user = userDao.findByUserAccountName(userId, accountId);
    		}
    		
    		// hide password
    		user.setPassword(null);
    		
        	// load account
    		account = accountDao.findByAccountName(user.getId().getAccountId());
    	}

		// load device groups
		
		// 1) if user exists, get DeviceGroup ID from the GroupList table if any,
		// 1.1) otherwise fall back to all the DeviceGroup IDs available to its account in the DeviceGroup table
		// 2) if user does not exist, same as 1.1 
		
		List<DeviceGroup> deviceGroups = new ArrayList<DeviceGroup>();
		
		if (user == null) {
			deviceGroups.addAll(deviceGroupDao.findDeviceGroupsByAccountName(account.getAccountId()));
		} else {
			final List<DeviceGroup> userDeviceGroups = 
					userDeviceGroupDao.findDeviceGroupsByUserAccountName(
							user.getId().getUserId(), 
							user.getId().getAccountId());
			
			if (userDeviceGroups.isEmpty()) {
				deviceGroups.addAll(deviceGroupDao.findDeviceGroupsByAccountName(account.getAccountId()));
			} else {
				deviceGroups.addAll(userDeviceGroups);
			}
		}
		
		HomeMessage message = new HomeMessage(ResultCodeEnum.Success, null);
		
		message.setUser(user);
		message.setAccount(account);
		message.setDeviceGroups(deviceGroups);
		
		return message;
	}

}
