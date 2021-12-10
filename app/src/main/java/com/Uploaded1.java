package com;

public class Uploaded1 {
    private String imageUrl,namaDriver,waktu,email,imgAnak;
    private String imageanak,namaanak,waktuanak;
    public Uploaded1(){

    }
    public Uploaded1(String imageUrl, String namaDriver, String waktu, String email, String waktuanak, String namaanak){
        this.imageUrl = imageUrl;
        this.namaDriver = namaDriver;
        this.waktu = waktu;
        this.email = email;
        this.waktuanak = waktuanak;
        this.namaanak = namaanak;
    }
    public Uploaded1(String imageUrl, String namaDriver, String waktu, String email, String imageanak, String namaanak, String waktuanak){
        this.imageUrl = imageUrl;
        this.namaDriver = namaDriver;
        this.waktu = waktu;
        this.email = email;
        this.imageanak = imageanak;
        this.namaanak = namaanak;
        this.waktuanak = waktuanak;
    }

    public String getImageanak() {
        return imageanak;
    }

    public void setImageanak(String imageanak) {
        this.imageanak = imageanak;
    }

    public String getNamaanak() {
        return namaanak;
    }

    public void setNamaanak(String namaanak) {
        this.namaanak = namaanak;
    }

    public String getWaktuanak() {
        return waktuanak;
    }

    public void setWaktuanak(String waktuanak) {
        this.waktuanak = waktuanak;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNamaDriver() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
