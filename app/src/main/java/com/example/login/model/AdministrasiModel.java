package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AdministrasiModel implements Parcelable {
    String name, urlPdf;

    public AdministrasiModel(String name, String urlPdf) {
        this.name = name;
        this.urlPdf = urlPdf;
    }

    protected AdministrasiModel(Parcel in) {
        name = in.readString();
        urlPdf = in.readString();
    }

    public static final Creator<AdministrasiModel> CREATOR = new Creator<AdministrasiModel>() {
        @Override
        public AdministrasiModel createFromParcel(Parcel in) {
            return new AdministrasiModel(in);
        }

        @Override
        public AdministrasiModel[] newArray(int size) {
            return new AdministrasiModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(urlPdf);
    }
}
