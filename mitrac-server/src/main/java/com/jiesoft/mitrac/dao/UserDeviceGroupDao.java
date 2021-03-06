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

package com.jiesoft.mitrac.dao;

import java.util.List;

import com.jiesoft.mitrac.domain.bo.DeviceGroup;

/**
 * User and Device Group DAO interface.
 * 
 * @author Ray Shi
 */

public interface UserDeviceGroupDao {
	
	List<DeviceGroup> findDeviceGroupsByUserAccountName(String username, String accountName);

}
