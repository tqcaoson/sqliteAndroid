package com.example.sqlitesv;

public class SinhVienModel {
    int id;
    String hoTen, ns, truong, soThich;
    int gioiTinh;

    public SinhVienModel(String hoTen, String ns, String truong, String soThich, int gioiTinh) {
        this.hoTen = hoTen;
        this.ns = ns;
        this.truong = truong;
        this.soThich = soThich;
        this.gioiTinh = gioiTinh;
    }

    public SinhVienModel(int id, String hoTen, String ns, String truong, String soThich, int gioiTinh) {
        this.id = id;
        this.hoTen = hoTen;
        this.ns = ns;
        this.truong = truong;
        this.soThich = soThich;
        this.gioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getSoThich() {
        return soThich;
    }

    public void setSoThich(String soThich) {
        this.soThich = soThich;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
