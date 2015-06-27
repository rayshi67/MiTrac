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
package com.jiesoft.mitrac.common;

/**
 * Abstract Message class.
 * 
 * @author Ray Shi
 */

public class AbstractMessage implements java.io.Serializable {
	
	private static final long serialVersionUID = -1L;
	
	protected ResultCodeEnum code;
	protected String message;

	protected AbstractMessage() {
	}
	
	protected AbstractMessage(final ResultCodeEnum code, final String message) {
		this.code = code;
		this.message = message;
	}

	public ResultCodeEnum getCode() {
		return code;
	}

	public void setCode(ResultCodeEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
