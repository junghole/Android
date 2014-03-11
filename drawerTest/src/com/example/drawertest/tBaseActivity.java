package com.example.drawertest;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

public class tBaseActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// create tabs
		ActionBar.Tab favoriteSwitch = bar.newTab().setIcon(R.drawable.ic_a);
		ActionBar.Tab recentSwitch = bar.newTab().setIcon(R.drawable.ic_b);
		ActionBar.Tab onSwitch = bar.newTab().setIcon(R.drawable.ic_c);
		ActionBar.Tab offSwitch = bar.newTab().setIcon(R.drawable.ic_d);
		ActionBar.Tab allSwitch = bar.newTab().setIcon(R.drawable.ic_e);
		
		// create fragments
		Fragment favoriteFragment = new favoriteSwitchFragment();
        Fragment recentFragment = new recentSwitchFragment();
        Fragment onFragment = new onSwitchFragment();
        Fragment offFragment = new offSwitchFragment();
        Fragment allFragment = new allSwitchFragment();
        
        // bind fragments to tabs
        favoriteSwitch.setTabListener(new tabListener(favoriteFragment, getApplicationContext()));
        recentSwitch.setTabListener(new tabListener(recentFragment, getApplicationContext()));
        onSwitch.setTabListener(new tabListener(onFragment, getApplicationContext()));
        offSwitch.setTabListener(new tabListener(offFragment, getApplicationContext()));
        allSwitch.setTabListener(new tabListener(allFragment, getApplicationContext()));
        
        // add tabs to the action bar
        bar.addTab(favoriteSwitch);
        bar.addTab(recentSwitch);
        bar.addTab(onSwitch);
        bar.addTab(offSwitch);
        bar.addTab(allSwitch);
	}

	private class tabListener implements ActionBar.TabListener {
		Fragment fragment;
		Context context;
		private tabListener (Fragment fragment, Context context){
			this.fragment=fragment;
			this.context=context;
		}
		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.content_frame, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}	
	}
}