package com.jiesoft.android.app.micart.auth;

import java.util.Collections;

import android.content.Intent;
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

import com.jiesoft.android.app.micart.MainActivity;
import com.jiesoft.android.app.micart.Message;
import com.jiesoft.android.app.micart.R;
import com.jiesoft.android.app.micart.R.id;
import com.jiesoft.android.app.micart.R.layout;
import com.jiesoft.android.app.micart.R.string;
import com.jiesoft.android.app.micart.common.AbstractAsyncActivity;
import com.jiesoft.android.app.micart.util.AlertDialogManager;

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
        
        // Session Manager
        session = new SessionManager(getApplicationContext());
        
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        
        
        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        
        
        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
        	public void onClick(View v) {
				new FetchSecuredResourceTask().execute();
			}
		});
    }
    
	private void displayResponse(Message response) {
		Toast.makeText(this, response.getText(), Toast.LENGTH_LONG).show();
	}
	
	private class FetchSecuredResourceTask extends AsyncTask<Void, Void, Message> {

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
			final String url = getString(R.string.base_uri) + "/getmessage";

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
			displayResponse(result);
			
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