package com.appturbo.appturbotest;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.appturbo.appturbotest.model.Application;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_APPLICATION = "extra_application";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Application model = (Application) getIntent().getParcelableExtra(DetailActivity.EXTRA_APPLICATION);
        System.out.println("MODEL " + model.getName());

        TextView app_name = (TextView) findViewById(R.id.app_name);
        app_name.setText(model.getName());

        TextView app_desc = (TextView) findViewById(R.id.app_desc);
        app_desc.setText(model.getDescription());

        ImageView ivIcon = (ImageView) findViewById(R.id.logo);
        Picasso.with(this)
                .load(model.getLogo())
                .into(ivIcon);

        ImageView ivScreenshot = (ImageView) findViewById(R.id.screenshot);
        Picasso.with(this)
                .load(model.getScreenshot())
                .into(ivScreenshot);

        /*
        * TODO: Get the Application Model from the Intent Extra and init the view:
        * @id/name
        * @id/description
        * @id/logo
        * @id/screenshot
        * In order to load the data you will need to load image from the network
        */


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
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
}
