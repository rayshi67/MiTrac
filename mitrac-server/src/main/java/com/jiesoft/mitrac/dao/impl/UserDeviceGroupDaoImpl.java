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

import java.util.ArrayList;
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
	public List<DeviceGroup> findDeviceGroupsByUserAccountName(final String username, final String accountName) {
		final List<String> deviceGroupIds = sessionFactory.getCurrentSession()
				.createQuery("SELECT id.groupId FROM GroupList WHERE id.userId = :userId AND id.accountId = :accountId")
				.setParameter("userId", username)
				.setParameter("accountId", accountName)
				.list();
		
		if (deviceGroupIds.isEmpty()) {
			return new ArrayList<DeviceGroup>();
		}
		
		return sessionFactory.getCurrentSession()
				.createQuery("FROM DeviceGroup WHERE id.groupId IN (:deviceGroupIds) AND id.accountId = :accountId")
				.setParameterList("deviceGroupIds", deviceGroupIds)
				.setParameter("accountId", accountName)
				.list();
	}

}
