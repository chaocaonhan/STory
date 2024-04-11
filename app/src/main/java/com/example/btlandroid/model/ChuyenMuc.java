package com.example.btlandroid.model;

public class ChuyenMuc {

    private String TenChuyenMuc;
    private int HinhAnhChuyenMuc;

    public ChuyenMuc(String tenChuyenMuc, int hinhAnhChuyenMuc) {
        TenChuyenMuc = tenChuyenMuc;
        HinhAnhChuyenMuc = hinhAnhChuyenMuc;
    }

    public String getTenChuyenMuc() {
        return TenChuyenMuc;
    }

    public void setTenChuyenMuc(String tenChuyenMuc) {
        TenChuyenMuc = tenChuyenMuc;
    }

    public int getHinhAnhChuyenMuc() {
        return HinhAnhChuyenMuc;
    }

    public void setHinhAnhChuyenMuc(int hinhAnhChuyenMuc) {
        HinhAnhChuyenMuc = hinhAnhChuyenMuc;
    }
}
