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

package com.jiesoft.android.app.mitrac.controller.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jiesoft.android.app.mitrac.R;
import com.jiesoft.android.app.mitrac.auth.SessionManager;
import com.jiesoft.android.app.mitrac.common.drawer.DrawerItem;
import com.jiesoft.android.app.mitrac.common.drawer.DrawerListAdapter;
import com.jiesoft.mitrac.common.StringUtilsExt;
import com.jiesoft.mitrac.domain.bo.Device;
import com.jiesoft.mitrac.message.HomeMessage;

/**
 * @author Ray Shi
 */

public class MainActivity extends FragmentActivity implements
        OnMarkerClickListener,
        OnMapReadyCallback {

	public static final String EXTRA_HOME_MESSAGE = "com.jiesoft.android.app.mitrac.home_message";

	private static final String TAG_MAP_FRAGMENT = "MAP-FRAGMENT";

	private static final int DRAW_ITEM_HOME = 0;
	private static final int DRAW_ITEM_LOGOUT = 1;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<DrawerItem> navDrawerItems;
    private DrawerListAdapter adapter;
    
    // Session Manager Class
    private SessionManager session;

    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);

    private GoogleMap mMap;

    private HomeMessage homeMessage;
    
    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;
    private Marker mAdelaide;
    private Marker mMelbourne;

    /**
     * Keeps track of the last selected marker (though it may no longer be selected).  This is
     * useful for refreshing the info window.
     */
    private Marker mLastSelectedMarker;

    private final List<Marker> mMarkerRainbow = new ArrayList<Marker>();

    private final Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Session class instance
        session = new SessionManager(getApplicationContext());
        
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();
        
        // get extras
        homeMessage = (HomeMessage) getIntent().getSerializableExtra(EXTRA_HOME_MESSAGE);
        
        // set up menu
        mTitle = mDrawerTitle = getTitle();
        
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<DrawerItem>();
 
        // adding nav drawer items to array
        // MiTrac Home
        navDrawerItems.add(new DrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Logout
        navDrawerItems.add(new DrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
         
 
        // Recycle the typed array
        navMenuIcons.recycle();
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
 
        // setting the nav drawer list adapter
        adapter = new DrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display the view for the first draw item
            displayView(DRAW_ITEM_HOME);
        }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
        	displayView(position);
        }
    }

	/**
	 * Diplaying fragment view for selected drawer list item
	 * */
	private void displayView(final int position) {
		// update the main content by replacing fragments
        Fragment fragment = null;
        
		switch (position) {
		case DRAW_ITEM_HOME:
			SupportMapFragment mapFragment = SupportMapFragment.newInstance();
			mapFragment.getMapAsync(this);
			fragment = mapFragment;
			break;
		case DRAW_ITEM_LOGOUT:
            // Clear the session data
            // This will clear all session data and 
            // redirect user to LoginActivity
            session.logoutUser();
			break;
		default:
			break;
		}
		
		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, TAG_MAP_FRAGMENT).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add device markers to the map.
        final List<Marker> markers = addMarkersToMap();

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        map.setContentDescription("Map with the devices.");

        // Pan to see all markers in view.
        mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition arg0) {
            	if (markers.isEmpty()) {
            		return;
            	}
            	
            	LatLngBounds.Builder builder = new LatLngBounds.Builder();
            	
            	for (Marker marker : markers) {
            	    builder.include(marker.getPosition());
            	}
            	
            	LatLngBounds bounds = builder.build();
            	
            	bounds = adjustBoundsForMaxZoomLevel(bounds);
            	
                // Move camera
            	mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                // Remove listener to prevent position reset on camera move.
                mMap.setOnCameraChangeListener(null);
            }
            
            private LatLngBounds adjustBoundsForMaxZoomLevel(final LatLngBounds bounds) {
            	LatLngBounds retval = bounds;
            	
            	LatLng sw = bounds.southwest;
            	LatLng ne = bounds.northeast;
            	double deltaLat = Math.abs(sw.latitude - ne.latitude);
            	double deltaLon = Math.abs(sw.longitude - ne.longitude);

            	final double zoomN = 0.005; // minimum zoom coefficient
            	if (deltaLat < zoomN) {
            	    sw = new LatLng(sw.latitude - (zoomN - deltaLat / 2), sw.longitude);
            	    ne = new LatLng(ne.latitude + (zoomN - deltaLat / 2), ne.longitude);
            	    retval = new LatLngBounds(sw, ne);
            	}
            	else if (deltaLon < zoomN) {
            	    sw = new LatLng(sw.latitude, sw.longitude - (zoomN - deltaLon / 2));
            	    ne = new LatLng(ne.latitude, ne.longitude + (zoomN - deltaLon / 2));
            	    retval = new LatLngBounds(sw, ne);
            	}

            	return retval;
            }
        });
    }

    private List<Marker> addMarkersToMap() {
    	List<Marker> markers = new ArrayList<Marker>();

    	if (homeMessage == null || homeMessage.getDevices() == null) {
    		return markers;
    	}
    	
    	for (Device device : homeMessage.getDevices()) {
    		Marker m = mMap.addMarker(new MarkerOptions()
    				.position(new LatLng(device.getLastValidLatitude(), device.getLastValidLongitude()))
    				.title(StringUtilsExt.isEmpty(device.getDisplayName()) ? device.getId().getDeviceId() : device.getDisplayName())
    				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    		
    		markers.add(m);
    	}
    	
    	return markers;
    }

    @SuppressWarnings("unused")
	private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //
    // Marker related listeners.
    //

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(mPerth)) {
            // This causes the marker at Perth to bounce into position when it is clicked.
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
        } else if (marker.equals(mAdelaide)) {
            // This causes the marker at Adelaide to change color and alpha.
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(mRandom.nextFloat() * 360));
            marker.setAlpha(mRandom.nextFloat());
        }

        mLastSelectedMarker = marker;
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}
