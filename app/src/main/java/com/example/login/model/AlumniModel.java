package com.example.login.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlumniModel implements Parcelable {
    private String Nama, Delegation, Birthday, Email, Pimpinan, Nomor, MasaJabatan, Whatsapp;
    private int Profil;

    public AlumniModel(String nama, String delegation, String birthday, String email, String pimpinan, String nomor, int profil, String MasaJabatan, String whatsapp) {
        this.Nama = nama;
        this.Delegation = delegation;
        this.Birthday = birthday;
        this.Email = email;
        this.Pimpinan = pimpinan;
        this.Nomor = nomor;
        this.Profil = profil;
        this.MasaJabatan = MasaJabatan;
        this.Whatsapp = whatsapp;
    }

    protected AlumniModel(Parcel in) {
        Nama = in.readString();
        Delegation = in.readString();
        Birthday = in.readString();
        Email = in.readString();
        Pimpinan = in.readString();
        Nomor = in.readString();
        Profil = in.readInt();
        MasaJabatan = in.readString();
        Whatsapp = in.readString();
    }

    public static final Creator<AlumniModel> CREATOR = new Creator<AlumniModel>() {
        @Override
        public AlumniModel createFromParcel(Parcel in) {
            return new AlumniModel(in);
        }

        @Override
        public AlumniModel[] newArray(int size) {
            return new AlumniModel[size];
        }
    };

    public AlumniModel(int profil, String nama, String birthday, String email, String pimpinan, String nomor, String MasaJabatan, String whatsapp) {
        this.Profil = profil;
        this.Nama = nama;
        this.Birthday = birthday;
        this.Email = email;
        this.Pimpinan = pimpinan;
        this.Nomor = nomor;
        this.MasaJabatan = MasaJabatan;
        this.Whatsapp = whatsapp;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getDelegation() {
        return Delegation;
    }

    public void setDelegation(String delegation) {
        Delegation = delegation;
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

    public int getProfil() {
        return Profil;
    }

    public void setProfil(int profil) {
        Profil = profil;
    }

    public String getMasaJabatan() {
        return MasaJabatan;
    }

    public void setMasaJabatan(String masaJabatan) {
        MasaJabatan = masaJabatan;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Nama);
        parcel.writeString(Delegation);
        parcel.writeString(Birthday);
        parcel.writeString(Email);
        parcel.writeString(Pimpinan);
        parcel.writeString(Nomor);
        parcel.writeInt(Profil);
        parcel.writeString(Whatsapp);
    }
}
