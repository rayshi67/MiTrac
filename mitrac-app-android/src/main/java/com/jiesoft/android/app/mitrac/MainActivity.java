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

package com.jiesoft.android.app.mitrac;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiesoft.android.app.mitrac.R;
import com.jiesoft.android.app.mitrac.auth.SessionManager;
import com.jiesoft.android.app.mitrac.util.AlertDialogManager;

/**
 * @author Ray Shi
 */

public class MainActivity extends Activity {
     
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
     
    // Session Manager Class
    SessionManager session;
     
    // Button Logout
    Button btnLogout;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        // Session class instance
        session = new SessionManager(getApplicationContext());
         
        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);
         
        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);
         
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
         
         
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();
         
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
         
        // name
        String name = user.get(SessionManager.KEY_NAME);
         
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
         
        // displaying user data
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));
         
         
        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and 
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });
    }
         
}

