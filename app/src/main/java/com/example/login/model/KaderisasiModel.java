package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;

public class KaderisasiModel implements Parcelable {
    private String id, nmFile, urlFile;

    public KaderisasiModel(){
    }
    public KaderisasiModel(String nmFile, String urlFile){
        this.nmFile = nmFile;
        this.urlFile = urlFile;
    }
    public KaderisasiModel(Parcel in) {
        id = in.readString();
        nmFile = in.readString();
        urlFile = in.readString();
    }

    public static final Creator<KaderisasiModel> CREATOR = new Creator<KaderisasiModel>() {
        @Override
        public KaderisasiModel createFromParcel(Parcel in) {
            return new KaderisasiModel(in);
        }

        @Override
        public KaderisasiModel[] newArray(int size) {
            return new KaderisasiModel[size];
        }
    };

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getNmFile(){
        return nmFile;
    }
    public String getUrlFile(){
        return urlFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nmFile);
        dest.writeString(urlFile);
    }
}
