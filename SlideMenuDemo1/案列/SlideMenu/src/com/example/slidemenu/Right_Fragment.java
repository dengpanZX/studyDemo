package com.example.slidemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Right_Fragment extends android.support.v4.app.Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        		Bundle savedInstanceState) {
        	View v=inflater.inflate(R.layout.fragment_right_menu,container, false);
        	return v;
        }
}
