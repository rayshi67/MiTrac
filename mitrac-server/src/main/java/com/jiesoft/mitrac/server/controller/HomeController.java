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

import com.jiesoft.mitrac.common.Message;

/**
 * Handles requests for the application home page.
 * 
 * @author Ray Shi
 */
@Controller
@RequestMapping("/*")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Spring Android Basic Auth");
		return "home";
	}

	@RequestMapping(value = "/getmessage", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Message getMessage() {
		logger.info("Accessing protected resource");
		return new Message(100, "Congratulations!", "You have accessed a Basic Auth protected resource.");
	}

}
