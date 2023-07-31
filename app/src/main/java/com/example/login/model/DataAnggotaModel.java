package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firestore.v1.Cursor;

public class DataAnggotaModel implements Parcelable {
    private String Nama, Birthday, Email, Pimpinan, Nomor, Whatsapp;
    private int Profil;

    public DataAnggotaModel() {
    }

    public DataAnggotaModel(int profil, String nama, String birthday, String email, String pimpinan, String nomor, String whatsapp) {
        this.Profil = profil;
        this.Nama = nama;
        this.Birthday = birthday;
        this.Email = email;
        this.Pimpinan = pimpinan;
        this.Nomor = nomor;
        this.Whatsapp = whatsapp;
    }

    protected DataAnggotaModel(Parcel in) {
        Profil = in.readInt();
        Nama = in.readString();
        Birthday = in.readString();
        Email = in.readString();
        Pimpinan = in.readString();
        Nomor = in.readString();
        Whatsapp = in.readString();
    }

    public static final Creator<DataAnggotaModel> CREATOR = new Creator<DataAnggotaModel>() {
        @Override
        public DataAnggotaModel createFromParcel(Parcel in) {
            return new DataAnggotaModel(in);
        }

        @Override
        public DataAnggotaModel[] newArray(int size) {
            return new DataAnggotaModel[size];
        }
    };

    public DataAnggotaModel(int profil, String nama, String birthday, String email, String pimpinan, String nomor) {
        this.Profil = profil;
        this.Nama = nama;
        this.Birthday = birthday;
        this.Email = email;
        this.Pimpinan = pimpinan;
        this.Nomor = nomor;
    }

    public int getProfil() {
        return Profil;
    }

    public void setProfil(int profil) {
        Profil = profil;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPimpinan() {
        return Pimpinan;
    }

    public void setPimpinan(String pimpinan) {
        Pimpinan = pimpinan;
    }

    public String getNomor() {
        return Nomor;
    }

    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public String getWhatsapp() {
        return Whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        Whatsapp = whatsapp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Profil);
        dest.writeString(Nama);
        dest.writeString(Birthday);
        dest.writeString(Email);
        dest.writeString(Pimpinan);
        dest.writeString(Nomor);
        dest.writeString(Whatsapp);
    }

}
