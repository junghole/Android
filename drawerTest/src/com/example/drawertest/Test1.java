package com.example.drawertest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Test1 extends TabBaseActivity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Fragment fragment = new mainFragment();
		//Bundle args = new Bundle();
		//fragment.setArguments(args);
		FragmentManager fragmentManager = getFragmentManager();
    	fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	}
	
	public static class mainFragment extends Fragment {
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		View rootView = inflater.inflate(R.layout.hey, container, false);
    		return rootView;
    	}
	}
}
