package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BeritaModelAdmin implements Parcelable {
    private String id, judul, deskripsi, image, userIdUploader, detail;

    public BeritaModelAdmin(){
    }

    public BeritaModelAdmin(String id, String judul, String deskripsi, String image, String userIdUploader, String detail) {
        this.userIdUploader = userIdUploader;
        this.detail = detail;
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    protected BeritaModelAdmin(Parcel in) {
        userIdUploader = in.readString();
        detail = in.readString();
        id = in.readString();
        judul = in.readString();
        deskripsi = in.readString();
        image = in.readString();
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
    public BeritaModelAdmin(String judul, String deskripsi, String image) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userIdUploader);
        parcel.writeString(id);
        parcel.writeString(judul);
        parcel.writeString(deskripsi);
        parcel.writeString(image);
    }
}
