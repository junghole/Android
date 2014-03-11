package com.example.drawertest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.*;

public class tabFragment extends Fragment {
private FragmentTabHost mTabHost;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    mTabHost = new FragmentTabHost(getActivity());
    mTabHost.setup();

    mTabHost.addTab(mTabHost.newTabSpec("TabA").setIndicator("TabA"),
           FragmentTab1.class, null);
    mTabHost.addTab(mTabHost.newTabSpec("TabB").setIndicator("TabB"),
            FragmentTab2.class, null);

    return mTabHost;
}


}
