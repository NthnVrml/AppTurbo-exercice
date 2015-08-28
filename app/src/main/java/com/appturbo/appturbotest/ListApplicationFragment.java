package com.appturbo.appturbotest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appturbo.appturbotest.model.Application;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListApplicationFragment extends ListFragment {

    private final List<Application> list = new ArrayList<>();

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOGO = "logo";
    private static final String SCREENSHOT = "screenshot";
    private static final String URL = "http://ligol.free.fr/Android/data.json";

    private String name;
    private String description;
    private String logo;
    private String screenshot;

    Application Apps;

    public static ListApplicationFragment newInstance() {
        ListApplicationFragment fragment = new ListApplicationFragment();
        return fragment;
    }

    /*
     * I load the view of the Fragment, this need to be a listview with the android standard list id.
     */


    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// I load data and stock its in list of Application Object.
        loadData();

        // Init Custom Adapter
        ListViewAdpater adapter = new ListViewAdpater(getActivity(), list);

        // Set Custom adapter
        setListAdapter(adapter);

        //Click Action and drop data in DetailActivity.
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra(DetailActivity.EXTRA_APPLICATION, list.get(position));
                startActivity(i);
            }
        });
	}

    /*
	* Load the json from the Web, parse it and build a list of Application model in order to notify the Adapter with new data.
	 */

    private void loadData() {

        JSONArray jsonArray;

        /*
         * Making a request to url and getting response
         * Go to GetObjectJSONByURL()
         */
        GetObjectJSONByURL Object = new GetObjectJSONByURL();
        String response = null;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            response = Object.run(URL);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
                if (response != null) {
                    try {
                        // Getting JSON Array node
                        jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            //Parsing JSON Object
                            JSONObject c = (JSONObject) jsonArray.getJSONObject(i);

                            //Finding
                            name = c.getString(NAME);
                            description = c.getString(DESCRIPTION);
                            logo = c.getString(LOGO);
                            screenshot = c.getString(SCREENSHOT);

                           // Template List for Single Application
                            Apps = new Application(name, description, logo, screenshot);

                            //// Adding each child node to list
                            list.add(Apps);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("ServiceHandler Couldn't get any data from the url");
                }
	}

    /*
     *  This function downloads a URL and return its contents as a string
     *   For information: http://square.github.io/okhttp/
     */
    public class GetObjectJSONByURL {

        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    /*
     *  I develop the ListAdapter in order to show each item in the list. This adapter load the @layout/list_application_item and load all of the data in it from the Application model or the Network.
     */

    public class ListViewAdpater extends ArrayAdapter<Application> {

        public ListViewAdpater(Context context, List<Application> items) {
            super(context, R.layout.list_application_item, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_application_item, parent, false);

                // Configure view holder
                viewHolder = new ViewHolder();
                viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.logo);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.app_name);
                viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.app_desc);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Fill data
            Application item = getItem(position);
            viewHolder.ivIcon.setImageDrawable(getDrawable(item.getLogo()));
            viewHolder.tvTitle.setText(item.getName());
            viewHolder.tvDescription.setText(item.getDescription());

            return convertView;
        }

        public Drawable getDrawable(String bitmapUrl) {
            try {
                URL url = new URL(bitmapUrl);
                Drawable d = new BitmapDrawable(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
                return d;
            } catch (Exception ex) {
                return null;
            }
        }

        private class ViewHolder {
            ImageView ivIcon;
            TextView tvTitle;
            TextView tvDescription;
        }
    }

}

