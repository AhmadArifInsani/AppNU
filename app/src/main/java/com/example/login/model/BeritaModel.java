package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BeritaModel implements Parcelable {
    private String id, judul, detail, image;

    public BeritaModel(){

    }

    public BeritaModel(String id, String judul, String detail, String image) {
        this.id = id;
        this.judul = judul;
        this.detail = detail;
        this.image = image;
    }

    protected BeritaModel(Parcel in) {
        id = in.readString();
        judul = in.readString();
        detail = in.readString();
        image = in.readString();
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
    public BeritaModel(String judul, String detail) {
        this.judul = judul;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(judul);
        parcel.writeString(detail);
        parcel.writeString(image);
    }
}
