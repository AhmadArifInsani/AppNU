package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PosterModelAdmin implements Parcelable {
    private String id, judul, deskripsi, image, userIdUploader;

    public PosterModelAdmin(){
    }

    public PosterModelAdmin(String id, String judul, String deskripsi, String image, String userIdUploader) {
        this.userIdUploader = userIdUploader;
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
    }
    protected PosterModelAdmin(Parcel in) {
        userIdUploader = in.readString();
        id = in.readString();
        judul = in.readString();
        deskripsi = in.readString();
        image = in.readString();
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

    public PosterModelAdmin(String judul, String deskripsi, String image) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    public String getUserIdUploader() {
        return userIdUploader;
    }

    public void setUserIdUploader(String userIdUploader) {
        this.userIdUploader = userIdUploader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(userIdUploader);
        parcel.writeString(id);
        parcel.writeString(judul);
        parcel.writeString(deskripsi);
        parcel.writeString(image);
    }
}
