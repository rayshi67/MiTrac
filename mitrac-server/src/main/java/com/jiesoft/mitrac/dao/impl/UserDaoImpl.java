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

import org.hibernate.SessionFactory;

import com.jiesoft.mitrac.dao.UserDao;
import com.jiesoft.mitrac.domain.bo.User;

/**
 * @author Ray Shi
 */

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(final String username) {
		User retval = null;
		
		List<User> users = new ArrayList<User>();
		 
		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).list();
 
		if (users.size() > 0) {
			retval = users.get(0);
		}
		
		return retval;
	}

	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
