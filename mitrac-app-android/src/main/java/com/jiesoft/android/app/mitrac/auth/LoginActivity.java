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

package com.jiesoft.android.app.mitrac.auth;

import java.util.Collections;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.jiesoft.mitrac.common.Message;

import com.jiesoft.android.app.mitrac.R;
import com.jiesoft.android.app.mitrac.common.AbstractAsyncActivity;
import com.jiesoft.android.app.mitrac.controller.main.MainActivity;
import com.jiesoft.android.app.mitrac.util.AlertDialogManager;

/**
 * @author Ray Shi
 */

public class LoginActivity extends AbstractAsyncActivity {
	
	// Email, password edittext
	EditText txtUsername, txtPassword;
	
	// login button
	Button btnLogin;
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Session Manager Class
	SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent))); 
        
        // Session Manager
        session = new SessionManager(getApplicationContext());
        
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        
        
        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        
        
        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
        	public void onClick(View v) {
				new GetUserDevicesTask().execute();
			}
		});
    }
    
    /**
     * @deprecated used for testing only
     * @param response
     */
	private void displayResponse(Message response) {
		Toast.makeText(this, response.getText(), Toast.LENGTH_LONG).show();
	}
	
	private class GetUserDevicesTask extends AsyncTask<Void, Void, Message> {

		private String username;

		private String password;

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();

			// build the message object
			EditText editText = (EditText) findViewById(R.id.txtUsername);
			this.username = editText.getText().toString();

			editText = (EditText) findViewById(R.id.txtPassword);
			this.password = editText.getText().toString();
		}

		@Override
		protected Message doInBackground(Void... params) {
			final String url = getString(R.string.base_uri) + getString(R.string.context_devices);

			// Populate the HTTP Basic Authentitcation header with the username and password
			HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAuthorization(authHeader);
			requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

			try {
				// Make the network request
				Log.d(TAG, url);
				ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Message.class);
				return response.getBody();
			} catch (HttpClientErrorException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return new Message(0, e.getStatusText(), e.getLocalizedMessage());
			} catch (ResourceAccessException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return new Message(0, e.getClass().getSimpleName(), e.getLocalizedMessage());
			}
		}

		@Override
		protected void onPostExecute(Message result) {
			dismissProgressDialog();
			//displayResponse(result);
			
			if (100 == result.getId()) {
				// FIXME
				session.createLoginSession("Android Hive", "anroidhive@gmail.com");
			
				// Staring MainActivity
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();	
			} else {
				// username / password doesn't match
				alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
			}
		}
	}
	
}