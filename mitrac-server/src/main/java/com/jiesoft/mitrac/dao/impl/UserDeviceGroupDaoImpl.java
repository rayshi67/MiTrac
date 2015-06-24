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

package com.jiesoft.mitrac.dao.impl;

import java.util.List;

import com.jiesoft.mitrac.dao.AbstractDao;
import com.jiesoft.mitrac.dao.UserDeviceGroupDao;
import com.jiesoft.mitrac.domain.bo.DeviceGroup;

/**
 * @author Ray Shi
 */

public class UserDeviceGroupDaoImpl extends AbstractDao implements UserDeviceGroupDao {

	public UserDeviceGroupDaoImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceGroup> findDeviceGroupsByUserName(final String username) {
		
		// FIXME
		return sessionFactory.getCurrentSession()
				.createQuery("FROM GroupList WHERE userId = :userId")
				.setParameter("userId", username).list();

	}

}
