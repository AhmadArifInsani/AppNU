package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

public class BeritaModelAdmin implements Parcelable {
    private String id, Judul, Deskripsi, Image, UserId;

    public BeritaModelAdmin() {
    }

    public BeritaModelAdmin(String judul, String deskripsi, String image, String userIdUploader) {
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


    protected BeritaModelAdmin(Parcel in) {
        id = in.readString();
        Judul = in.readString();
        Deskripsi = in.readString();
        Image = in.readString();
        UserId = in.readString();
    }

    public static final Creator<BeritaModelAdmin> CREATOR = new Creator<BeritaModelAdmin>() {
        @Override
        public BeritaModelAdmin createFromParcel(Parcel in) {
            return new BeritaModelAdmin(in);
        }

        @Override
        public BeritaModelAdmin[] newArray(int size) {
            return new BeritaModelAdmin[size];
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
