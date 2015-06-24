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
package com.jiesoft.mitrac.message;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jiesoft.mitrac.common.AbstractMessage;
import com.jiesoft.mitrac.common.ResultCodeEnum;
import com.jiesoft.mitrac.domain.bo.Account;
import com.jiesoft.mitrac.domain.bo.Device;
import com.jiesoft.mitrac.domain.bo.DeviceGroup;
import com.jiesoft.mitrac.domain.bo.User;

/**
 * Home Message class.
 * 
 * @author Ray Shi
 */

@XmlRootElement(name = "homeMessage")
public class HomeMessage extends AbstractMessage {
	private Account account;

	private User user;
	
	private List<DeviceGroup> deviceGroups;

	private List<Device> devices;

	public HomeMessage() {
		super();
	}
	
	public HomeMessage(final ResultCodeEnum code, final String message) {
		super(code, message);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DeviceGroup> getDeviceGroups() {
		return deviceGroups;
	}

	public void setDeviceGroups(List<DeviceGroup> deviceGroups) {
		this.deviceGroups = deviceGroups;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

}
