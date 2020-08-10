/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListMeNu;

/**
 *
 * @author Thanh
 */
public class TaiKhoan {
    private String userName,passWord,chucVu;

    public TaiKhoan(String userName, String passWord, String chucVu) {
        this.userName = userName;
        this.passWord = passWord;
        this.chucVu = chucVu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    


    


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "userName=" + userName + ", passWord=" + passWord + ", chucVu=" + chucVu + '}';
    }
    
}
