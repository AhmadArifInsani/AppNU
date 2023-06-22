package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PosterModelAdmin implements Parcelable {
    private String id, Judul, Deskripsi, Image, UserId;

    public PosterModelAdmin(){
    }

    public PosterModelAdmin(String judul, String deskripsi, String image, String userIdUploader) {
        this.Judul = judul;
        this.Deskripsi = deskripsi;
        this.Image = image;
        this.UserId = userIdUploader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    protected PosterModelAdmin(Parcel in) {
        id = in.readString();
        Judul = in.readString();
        Deskripsi = in.readString();
        Image = in.readString();
        UserId = in.readString();
    }

    public static final Creator<PosterModelAdmin> CREATOR = new Creator<PosterModelAdmin>() {
        @Override
        public PosterModelAdmin createFromParcel(Parcel in) {
            return new PosterModelAdmin(in);
        }

        @Override
        public PosterModelAdmin[] newArray(int size) {
            return new PosterModelAdmin[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
