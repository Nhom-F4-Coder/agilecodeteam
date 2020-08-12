/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author My PC
 */
public class SanPham {
    protected String maSP,tenSP,tinhTrang,cauHinh,ngayNhapKho;
    protected int soLuong,khuyenMai,gia;
    
    
    
    public SanPham(){
        
    }

    public SanPham(String maSP, String tenSP, String tinhTrang, String cauHinh, String ngayNhapKho, int soLuong, int khuyenMai, int gia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.tinhTrang = tinhTrang;
        this.cauHinh = cauHinh;
        this.ngayNhapKho = ngayNhapKho;
        this.soLuong = soLuong;
        this.khuyenMai = khuyenMai;
        this.gia = gia;
    }

    public String getMaSP() {
        return maSP;
    }

    

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getNgayNhapKho() {
        return ngayNhapKho;
    }

    public void setNgayNhapKho(String ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    

    

   
    
    
  
    
    
}
