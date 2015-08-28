package com.appturbo.appturbotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;


public class AboutFragment extends Fragment {

	/*
     * TODO: Write the AboutFragment with the layout @layout/fragment_about and initialize the Textview @id/my_description with the text return by getMyDescription.
     */


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about, container, false);
		return rootView;
	}


	private String getMyDescription(final String name, final int age, final Calendar startTime, final Calendar endTime) {
		/*
		 * TODO: Get the string and format it with the good parameter.
		 */
        return null;
	}

	public static AboutFragment newInstance() {
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}
}
