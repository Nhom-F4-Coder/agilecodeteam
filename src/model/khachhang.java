/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class khachhang {
    protected String makh,hoten,diachi,sdt,gioitinh;

    public khachhang() {
    }

    public khachhang(String makh, String hoten, String diachi, String sdt, String gioitinh) {
        this.makh = makh;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
    }

    

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    @Override
    public String toString() {
        return "khachhang{" + "makh=" + makh + ", hoten=" + hoten + ", diachi=" + diachi + ", sdt=" + sdt + ", gioitinh=" + gioitinh + '}';
    }
}
