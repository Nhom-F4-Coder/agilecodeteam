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
public class SanPham {
    protected String masp, tensp;
    protected double gia;
    protected int khuyenmai;

    public SanPham() {
    }

    public SanPham(String masp, String tensp, double gia, int khuyenmai) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.khuyenmai = khuyenmai;
    }

    @Override
    public String toString() {
        return "SanPham{" + "masp=" + masp + ", tensp=" + tensp + ", gia=" + gia + ", khuyenmai=" + khuyenmai + '}';
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }
    
}
