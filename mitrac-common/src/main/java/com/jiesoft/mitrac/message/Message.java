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

import javax.xml.bind.annotation.XmlRootElement;

import com.jiesoft.mitrac.common.AbstractMessage;

/**
 * Message class.
 * 
 * @author Ray Shi
 */

@XmlRootElement(name = "message")
public class Message extends AbstractMessage {
	private long id;

	private String subject;

	private String text;

	public Message() {
		super();
	}

	public Message(long id, String subject, String text) {
		this.id = id;
		this.subject = subject;
		this.text = text;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return "Id:[" + this.getId() + "] Subject:[" + this.getSubject() + "] Text:[" + this.getText() + "]";
	}
}
