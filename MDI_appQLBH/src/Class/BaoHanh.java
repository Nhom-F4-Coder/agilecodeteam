/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author nguyenvandat
 */
public class BaoHanh {
    private String maBH,maSP,maKH,tenKH,SDT,ngayMua,timeBH,ngayHetBH;
    boolean trangThaiBH;

    public BaoHanh() {
    }

    public BaoHanh(String maBH, String maSP, String maKH, String tenKH, String SDT, String ngayMua, String timeBH, String ngayHetBH, boolean trangThaiBH) {
        this.maBH = maBH;
        this.maSP = maSP;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.ngayMua = ngayMua;
        this.timeBH = timeBH;
        this.ngayHetBH = ngayHetBH;
        this.trangThaiBH = trangThaiBH;
    }

    public String getMaBH() {
        return maBH;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTimeBH() {
        return timeBH;
    }

    public void setTimeBH(String timeBH) {
        this.timeBH = timeBH;
    }

    public String getNgayHetBH() {
        return ngayHetBH;
    }

    public void setNgayHetBH(String ngayHetBH) {
        this.ngayHetBH = ngayHetBH;
    }

    public boolean isTrangThaiBH() {
        return trangThaiBH;
    }

    public void setTrangThaiBH(boolean trangThaiBH) {
        this.trangThaiBH = trangThaiBH;
    }

    @Override
    public String toString() {
        return "ClassBaoHanh{" + "maBH=" + maBH + ", maSP=" + maSP + ", maKH=" + maKH + ", tenKH=" + tenKH + ", SDT=" + SDT + ", ngayMua=" + ngayMua + ", timeBH=" + timeBH + ", ngayHetBH=" + ngayHetBH + ", trangThaiBH=" + trangThaiBH + '}';
    }
    
}
