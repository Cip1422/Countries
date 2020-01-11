package com.example.deltatest.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class NationModel implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("region")
    private String region;

    public NationModel() {
    }

    public NationModel(String name, String capital, String region) {
        this.name = name;
        this.capital = capital;
        this.region = region;
    }

    protected NationModel(Parcel in) {
        name = in.readString();
        capital = in.readString();
        region = in.readString();
    }

    public static final Creator<NationModel> CREATOR = new Creator<NationModel>() {
        @Override
        public NationModel createFromParcel(Parcel in) {
            return new NationModel(in);
        }

        @Override
        public NationModel[] newArray(int size) {
            return new NationModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public String getCapitalandName() {
        return capital + name;
    }
    public void setCapital(String capital) {
        if(capital.isEmpty()){
            this.capital = "No capital";
        }
        else {
            this.capital = capital;
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region.isEmpty()){
            this.region = "No specified region";
        }
        else {
        this.region = region;}
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeString(region);
    }

    @NonNull
    @Override
    public String toString() {

       return region + " : Name " + name + " :Capital " + capital;
    }
}
