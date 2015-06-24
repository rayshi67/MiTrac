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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiesoft.mitrac.common.AbstractMessage;
import com.jiesoft.mitrac.message.Message;

/**
 * Handles requests for the MiTrac REST requests.
 * 
 * @author Ray Shi
 */
@Controller
@RequestMapping("/mitrac")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AbstractMessage getDevices() {
		logger.debug("call getDevices:");
		
		return new Message(100, "Congratulations!", "You have accessed a Basic Auth protected resource.");
	}

}
