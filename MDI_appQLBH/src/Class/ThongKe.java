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
public class ThongKe {
    private int tongTien;
    private String tenSP,nam;

    public ThongKe() {
    }

    public ThongKe(int tongTien, String tenSP, String nam) {
        this.tongTien = tongTien;
        this.tenSP = tenSP;
        this.nam = nam;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "ThongKe{" + "tongTien=" + tongTien + ", tenSP=" + tenSP + ", nam=" + nam + '}';
    }
    
}
