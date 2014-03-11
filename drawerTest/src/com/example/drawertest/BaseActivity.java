package com.example.drawertest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BaseActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    
    public static final int HDR_POS1 = 1;
    public static final int HDR_POS2 = 5;
    public static final int HDR_POS3 = 8;

    private static final Integer LIST_HEADER = 0;
    private static final Integer LIST_ITEM = 1;
    
    public static final String[] mTitles = {"MENU",
		"LIGHTS",
		"LIGHTS", "FAVORITES", "MOST RECENT",
		"ADMIN", 
		"MANAGE USERS", "MANAGE LIGHTS",
		"SETTINGS",
		"APP SETTINGS", "ABOUT", "HELP", "LOG OUT"};
    private static final int[] mImages = 
		{R.drawable.ic_a, 
			0,
			R.drawable.ic_b, R.drawable.ic_b, R.drawable.ic_b,
			0,
			R.drawable.ic_c, R.drawable.ic_c,
			0,
			R.drawable.ic_d, R.drawable.ic_d, R.drawable.ic_d, R.drawable.ic_d
			};
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new MyListAdapter(this));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    
    
    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String headerText = getHeader(position);
            if(headerText != null) {

                View item = convertView;
                if(convertView == null || convertView.getTag() == LIST_ITEM) {

                    item = LayoutInflater.from(mContext).inflate(
                            R.layout.drawer_header, parent, false);
                    item.setTag(LIST_HEADER);

                }

                TextView headerTextView = (TextView)item.findViewById(R.id.drawer_list_header);
                headerTextView.setText(headerText);
                return item;
            }

            View item = convertView;
            if(convertView == null || convertView.getTag() == LIST_HEADER) {
                item = LayoutInflater.from(mContext).inflate(
                        R.layout.drawer_item, parent, false);
                item.setTag(LIST_ITEM);
            }

            TextView header = (TextView)item.findViewById(R.id.drawer_title);
            header.setText(mTitles[position % mTitles.length]);
            
            ImageView image = (ImageView)item.findViewById(R.id.drawer_logo);
            image.setImageResource(mImages[position % mImages.length]);
            

            return item;
        }

        private String getHeader(int position) {

            if(position == HDR_POS1  || position == HDR_POS2 || position == HDR_POS3) {
                return mTitles[position];
            }

            return null;
        }

        private final Context mContext;
    }    
    

    
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mDrawerList.setItemChecked(position, true);
	        setTitle(mTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	        
	        // Do not use case number for header position (1,5,8)
	        Intent intent;
	        switch(position){
	        case 0:
	        	intent = new Intent(BaseActivity.this, MainActivity.class);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	startActivity(intent);
	        	break;
	        case 2:
	        	intent = new Intent(BaseActivity.this, Test1.class);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	startActivity(intent);
	        	break;
	        case 3:
	        	intent = new Intent(BaseActivity.this, Test2.class);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        	startActivity(intent);
	        	break;
	        }
	        
		}
    }
}
