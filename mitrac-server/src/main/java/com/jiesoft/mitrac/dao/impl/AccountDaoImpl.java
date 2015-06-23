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
import com.jiesoft.mitrac.dao.AccountDao;
import com.jiesoft.mitrac.domain.bo.Account;

/**
 * @author Ray Shi
 */

public class AccountDaoImpl extends AbstractDao implements AccountDao {

	public AccountDaoImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Account findByAccountName(final String accountName) {
		Account retval = null;
		
		List<Account> accounts = new ArrayList<Account>();
		 
		accounts = sessionFactory.getCurrentSession()
				.createQuery("FROM Account WHERE accountId = :accountId")
				.setParameter("accountId", accountName).list();
 
		if (accounts.size() > 0) {
			retval = accounts.get(0);
		}
		
		return retval;
	}

}
