package com.appturbo.appturbotest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lagachebaptiste on 11/06/15.
 */
public class Application implements Parcelable {
    /*
     * TODO: This class need to implement parcelable in order to be send from one Activity to another one by the Intent extra.
     */

    private String name;
    private String description;
    private String logo;
    private String screenshot;



    public  Application(String n, String d, String l, String sc) {
        name = n;
        description = d;
        logo = l;
        screenshot = sc;
    }


    public Application(Parcel in) {
        name = in.readString();
        description = in.readString();
        logo = in.readString();
        screenshot = in.readString();
    }

    public static final Creator<Application> CREATOR = new Creator<Application>() {
        @Override
        public Application createFromParcel(Parcel in) {
            return new Application(in);
        }

        @Override
        public Application[] newArray(int size) {
            return new Application[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(logo);
        dest.writeString(screenshot);
    }
}
