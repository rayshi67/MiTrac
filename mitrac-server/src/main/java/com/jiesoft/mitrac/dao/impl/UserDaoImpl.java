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
import com.jiesoft.mitrac.dao.UserDao;
import com.jiesoft.mitrac.domain.bo.User;

/**
 * @author Ray Shi
 */

public class UserDaoImpl extends AbstractDao implements UserDao {

	public UserDaoImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByUserName(final String username) {		
		return sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE userId = :userId")
				.setParameter("userId", username).list();
	}

	@Override
	public User findByUserAccountName(final String username, final String accountName) {
		return (User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE userId = :userId AND accountId = :accountId")
				.setParameter("userId", username)
				.setParameter("accountId", accountName)
				.uniqueResult();
	}

}
