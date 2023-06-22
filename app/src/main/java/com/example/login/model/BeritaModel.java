package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

public class BeritaModel implements Parcelable {
    private String id, Judul, Deskripsi, Image, UserId;

    public BeritaModel() {
    }

    public BeritaModel(String judul, String deskripsi, String image, String userIdUploader) {
        this.Judul = judul;
        this.Deskripsi = deskripsi;
        this.Image = image;
        this.UserId = userIdUploader;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public String getJudul() {
        return Judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public String getImage() {
        return Image;
    }

    public String getUserId() {
        return UserId;
    }


    protected BeritaModel(Parcel in) {
        id = in.readString();
        Judul = in.readString();
        Deskripsi = in.readString();
        Image = in.readString();
        UserId = in.readString();
    }

    public static final Creator<BeritaModel> CREATOR = new Creator<BeritaModel>() {
        @Override
        public BeritaModel createFromParcel(Parcel in) {
            return new BeritaModel(in);
        }

        @Override
        public BeritaModel[] newArray(int size) {
            return new BeritaModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(Judul);
        parcel.writeString(Deskripsi);
        parcel.writeString(Image);
        parcel.writeString(UserId);
    }
}
