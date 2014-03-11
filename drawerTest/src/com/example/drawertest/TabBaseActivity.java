package com.example.drawertest;

import java.util.ArrayList;
import java.util.List;

import com.example.drawertest.R.drawable;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabBaseActivity extends BaseActivity implements TabListener{
	List<Fragment> fragmentList = new ArrayList<Fragment>();
	Fragment f = null;
	TabFragment tf = null;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		ActionBar bar = getActionBar();
		int tabIcon = 0;
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i=0;i<5;i++){
        	Tab tab = bar.newTab();
        	if (i==0){
        		tabIcon=drawable.ic_a;
        	}
        	else if (i==1){
        		tabIcon=drawable.ic_b;
        	}
        	else if (i==2){
        		tabIcon=drawable.ic_c;
        	}
        	else if (i==3){
        		tabIcon=drawable.ic_d;
        	}
        	else if (i==4){
        		tabIcon=drawable.ic_e;
        	}
        	tab.setIcon(tabIcon);
        	tab.setTabListener(this);
        	bar.addTab(tab);
        }
        
	}
	
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		if (fragmentList.size() > tab.getPosition()){
			fragmentList.get(tab.getPosition());
		}
		
		if (f == null){
			tf = new TabFragment();
			Bundle data = new Bundle();
			data.putInt("idx",tab.getPosition());
			tf.setArguments(data);
			fragmentList.add(tf);
		}
		else
			tf = (TabFragment) f;

		ft.replace(R.id.content_frame, tf);
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (fragmentList.size() > tab.getPosition()){
			ft.remove(fragmentList.get(tab.getPosition()));
		}
	}
	
	public static class TabFragment extends Fragment {
		
		private int index;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Bundle data = getArguments();
			index = data.getInt("idx");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = null;
			
			// favorite switches
			switch(index){
			case 0:
				rootView = inflater.inflate(R.layout.test_tab_0, container, false);
				break;
			// recent switches
			case 1:
				rootView = inflater.inflate(R.layout.test_tab_1, container, false);
				break;
			// on switches
			case 2:
				rootView = inflater.inflate(R.layout.test_tab_2, container, false);
				break;
			// off switches
			case 3:
				rootView = inflater.inflate(R.layout.test_tab_0, container, false);
				break;
			// all switches
			case 4:
				rootView = inflater.inflate(R.layout.test_tab_1, container, false);
				break;
			}
			return rootView;
		}
		
		
	}

}
