package com.example.login;

public class UserHelperClass {
    String nama, email, password, nomorTlp;

    public UserHelperClass() {
    }

    public UserHelperClass(String nama, String email, String password, String nomorTlp) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.nomorTlp = nomorTlp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomorTlp() {
        return nomorTlp;
    }

    public void setNomorTlp(String nomorTlp) {
        this.nomorTlp = nomorTlp;
    }
}
