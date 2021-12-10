package com.e_carter.userroles;

public class Pengantaran {
    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public String getWaktuJemput() {
        return waktuJemput;
    }

    public void setWaktuJemput(String waktuJemput) {
        this.waktuJemput = waktuJemput;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //    Model class
    String namaAnak;
    String waktuJemput;

    public String getNamaAnak2() {
        return namaAnak2;
    }

    public void setNamaAnak2(String namaAnak2) {
        this.namaAnak2 = namaAnak2;
    }

    public String getWaktuAntar() {
        return waktuAntar;
    }

    public void setWaktuAntar(String waktuAntar) {
        this.waktuAntar = waktuAntar;
    }

    public String getImgAnak() {
        return imgAnak;
    }

    public void setImgAnak(String imgAnak) {
        this.imgAnak = imgAnak;
    }

    String imageUrl;
    String email;
    String namaAnak2;
    String waktuAntar;
    String imgAnak;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pengantaran(){

    }

    public Pengantaran(String namaAnak, String waktuJemput, String imageUrl, String email, String namaAnak2,String waktuAntar,String imgAnak){
        this.namaAnak = namaAnak;
        this.waktuJemput = waktuJemput;
        this.imageUrl = imageUrl;
        this.email = email;
        this.namaAnak = namaAnak;
        this.waktuAntar = waktuAntar;
        this.imgAnak = imgAnak;
    }
}
