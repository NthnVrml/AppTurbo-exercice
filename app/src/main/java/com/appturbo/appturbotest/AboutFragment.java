package com.appturbo.appturbotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AboutFragment extends Fragment {

    /*
        Convert String to Calendar Object
     */
    private Calendar StringToCalendarObject(String date, String format){

        SimpleDateFormat curFormater = new SimpleDateFormat(format);
        Date dateObj = null;

        try {
            dateObj = curFormater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateObj);

        return calendar;
    }


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_about, container, false);
		TextView Description = (TextView) rootView.findViewById(R.id.my_description);

        // Set Description
        Description.setText(getMyDescription("Nathan", 25, StringToCalendarObject("00/10", "dd/MM"), StringToCalendarObject("00/04", "dd/MM")));
		return rootView;
	}

        // Formatting Description
	private String getMyDescription(final String name, final int age, final Calendar startTime, final Calendar endTime) {
		String MyDescription = String.format(getResources().getString(R.string.my_description), name, age, startTime, endTime);
        return MyDescription;
	}

    // Create Instance
	public static AboutFragment newInstance() {
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}
}
