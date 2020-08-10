/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListMeNu;

import Class.BaoHanh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvandat
 */
public class BaoHanhSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form BaoHanhSanPham
     */
    String hosting = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";
    String user = "sa";
    String password = "123456";
    Connection con;
    DefaultTableModel model;
    int index;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();
    List<BaoHanh> listBH = new ArrayList<>();

    public BaoHanhSanPham() {
        initComponents();
        model = (DefaultTableModel) tableBH.getModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = (Connection) DriverManager.getConnection(hosting, user, password);
            System.out.println("Connection Succesfully!");
        } catch (Exception e) {
            System.out.println("Connection Eror!");
        }
        fillDaTaDB();
    }

    private void fillDaTaDB() {
        try {
            model.setRowCount(0);
            String sql = "select*from BaoHanh";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maBH = rs.getString(1);
                String maSP = rs.getString(2);
                String maKH = rs.getString(3);
                String hoTen = rs.getString(4);
                String SDT = rs.getString(5);
                Date ngayMua = rs.getDate(6);
                String ngayMua2 = sdf1.format(ngayMua);
                String timeBH = rs.getString(7);
                Date ngayHetBH = rs.getDate(8);
                String ngayHetBH2 = sdf1.format(ngayHetBH);
                boolean trangThaiBH = true;
                if(date.before(ngayHetBH)){
                    trangThaiBH = true;
                }else{
                    trangThaiBH = false;
                }
                String trangThai;
                if (trangThaiBH == true) {
                    trangThai = "Con BH";
                } else {
                    trangThai = "Het BH";
                }
                model.addRow(new Object[]{maBH, maSP, maKH, hoTen, SDT, ngayMua2, timeBH, ngayHetBH2, trangThai});
                listBH.add(new BaoHanh(maBH, maSP, maKH, maKH, SDT, ngayMua2, timeBH, ngayHetBH2, trangThaiBH));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean ValidateForm() {
        if (txtMaBH.getText().equals("")) {
            txtMaBH.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào Mã BH!");
            return true;
        }
        String sql = "select*from BAOHANH";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maBH = rs.getString(1);
                if (txtMaBH.getText().equalsIgnoreCase(maBH)) {
                    txtMaBH.requestFocus();
                    JOptionPane.showMessageDialog(this, "Đã tồn tại mã bảo hành này,vui lòng nhập mã khác!");
                    return true;
                }
            }
        } catch (Exception e) {
            return true;
        }
        if (txtMaKH.getText().equals("")) {
            txtMaKH.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào Mã KH!");
            return true;
        }
        if (txtMaSP.getText().equals("")) {
            txtMaSP.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào Mã SP!");
            return true;
        }
        if (txtTenKH.getText().equals("")) {
            txtTenKH.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào Tên KH!");
            return true;
        }
        String sdt = "\\d{10}";
        if (txtSDT.getText().equals("")) {
            txtSDT.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào SDT!");
            return true;
        }
        if (!txtSDT.getText().matches(sdt)) {
            txtSDT.requestFocus();
            JOptionPane.showMessageDialog(this, "Số điện thoại phải nhập đúng 10 số!");
            return true;
        }
        return false;
    }

    private void AddDB() {
        if (ValidateForm()) {
            return;
        }
        int timeBH = 0;
        if (cboTimeBH.getSelectedIndex() == 0) {
            timeBH = 3;
        } else if (cboTimeBH.getSelectedIndex() == 1) {
            timeBH = 6;
        } else if (cboTimeBH.getSelectedIndex() == 2) {
            timeBH = 9;
        } else if (cboTimeBH.getSelectedIndex() == 3) {
            timeBH = 12;
        } else if (cboTimeBH.getSelectedIndex() == 4) {
            timeBH = 18;
        } else if (cboTimeBH.getSelectedIndex() == 5) {
            timeBH = 24;
        } else {
            timeBH = 36;
        }
        String ngayMuaHang = sdf2.format(date);
        cal.add(Calendar.MONTH, timeBH);
        date = cal.getTime();
        String ngayHetBH = sdf2.format(date);
        cal.add(Calendar.MONTH, -timeBH);
        int i = 0;
        try {
            Date date1 = sdf1.parse(ngayHetBH);
            Date date2 = cal.getTime();
            if (date2.before(date1)) {
                i = 0;
            } else {
                i = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "INSERT INTO BAOHANH VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtMaBH.getText());
            ps.setString(2, txtMaSP.getText());
            ps.setString(3, txtMaKH.getText());
            ps.setString(4, txtTenKH.getText());
            ps.setString(5, txtSDT.getText());
            ps.setString(6, ngayMuaHang);
            ps.setString(7, cboTimeBH.getSelectedItem().toString());
            ps.setString(8, ngayHetBH);
            ps.setInt(9, Integer.valueOf(i));
            ps.executeUpdate();
            ps.close();
            fillDaTaDB();
            System.out.println("Thanh cong");
        } catch (Exception e) {
            System.out.println("Loi!");
        }
    }

    private void UpdateDB() {
        int timeBH = 0;
        if (cboTimeBH.getSelectedIndex() == 0) {
            timeBH = 3;
        } else if (cboTimeBH.getSelectedIndex() == 1) {
            timeBH = 6;
        } else if (cboTimeBH.getSelectedIndex() == 2) {
            timeBH = 9;
        } else if (cboTimeBH.getSelectedIndex() == 3) {
            timeBH = 12;
        } else if (cboTimeBH.getSelectedIndex() == 4) {
            timeBH = 18;
        } else if (cboTimeBH.getSelectedIndex() == 5) {
            timeBH = 24;
        } else {
            timeBH = 36;
        }
        String ngayMua = "";
        for(BaoHanh bh : listBH){
            if(txtMaBH.getText().equals(bh.getMaBH())){
                ngayMua = bh.getNgayMua();
            }
        }
        String ngayHetBH = "";
        int month = 0;
        Date d= new Date();
        try {
            d = sdf1.parse(ngayMua);
            month = d.getMonth();
            d.setMonth(month + timeBH);
            ngayHetBH = sdf2.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(d);
        System.out.println(month);
        int i = 0;
        try {
            Date date1 = sdf1.parse(ngayHetBH);
            Date date2 = cal.getTime();
            if (date2.before(date1)) {
                i = 0;
            } else {
                i = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (txtMaBH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập vào Mã BH");
            return;
        }
        String sql = "UPDATE BAOHANH SET MASANPHAM = ?,MAKHACHHANG = ?,HOTENKHACHHANG = ?,SODIENTHOAI = ?"
               +",THOIGIANBAOHANH = ?,NGAYHETBAOHANH = ?,TRANGTHAIBAOHANH = ? WHERE MABAOHANH = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtMaSP.getText());
            ps.setString(2, txtMaKH.getText());
            ps.setString(3, txtTenKH.getText());
            ps.setString(4, txtSDT.getText());
            ps.setString(5, cboTimeBH.getSelectedItem().toString());
            ps.setString(6, ngayHetBH);
            ps.setInt(7, Integer.valueOf(i));
            ps.setString(8, txtMaBH.getText());
            ps.executeUpdate();
            ps.close();
            fillDaTaDB();
        } catch (Exception e) {

        }
    }

    private void Delete() {
        if (txtMaBH.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhap vao ma BH");
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "ban chac chan muon xoa dong nay");
        if (result == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM BAOHANH WHERE MABAOHANH = ?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtMaBH.getText());
                ps.executeUpdate();
                ps.close();
                fillDaTaDB();
            } catch (Exception e) {

            }
        }
    }

    private void showDaTaDB() {
        index = tableBH.getSelectedRow();
        BaoHanh bh = listBH.get(index);
        txtMaBH.setText(bh.getMaBH());
        txtMaSP.setText(bh.getMaSP());
        txtMaKH.setText(bh.getMaKH());
        txtTenKH.setText(bh.getTenKH());
        txtSDT.setText(bh.getSDT());
        cboTimeBH.setSelectedItem(bh.getTimeBH());
    }

    private void Clear() {
        txtMaBH.setText("");
        txtMaSP.setText("");
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        cboTimeBH.setSelectedIndex(0);
    }
    
    private void SeachTenKH(){
        try {
            model.setRowCount(0);
            String sql = "select*from BAOHANH where HOTENKHACHHANG like '%" + txtSeachTenKH.getText() + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maBH = rs.getString(1);
                String maSP = rs.getString(2);
                String maKH = rs.getString(3);
                String hoTen = rs.getString(4);
                String SDT = rs.getString(5);
                Date ngayMua = rs.getDate(6);
                String ngayMua2 = sdf1.format(ngayMua);
                String timeBH = rs.getString(7);
                Date ngayHetBH = rs.getDate(8);
                String ngayHetBH2 = sdf1.format(ngayHetBH);
                boolean trangThaiBH = true;
                if(date.before(ngayHetBH)){
                    trangThaiBH = true;
                }else{
                    trangThaiBH = false;
                }
                String trangThai;
                if (trangThaiBH == true) {
                    trangThai = "Con BH";
                } else {
                    trangThai = "Het BH";
                }
                model.addRow(new Object[]{maBH, maSP, maKH, hoTen, SDT, ngayMua2, timeBH, ngayHetBH2, trangThai});
                listBH.add(new BaoHanh(maBH, maSP, maKH, maKH, SDT, ngayMua2, timeBH, ngayHetBH2, trangThaiBH));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void SeachSDT(){
        try {
            model.setRowCount(0);
            String sql = "select*from BAOHANH where SODIENTHOAI like '%" + txtSeachSDT.getText() + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maBH = rs.getString(1);
                String maSP = rs.getString(2);
                String maKH = rs.getString(3);
                String hoTen = rs.getString(4);
                String SDT = rs.getString(5);
                Date ngayMua = rs.getDate(6);
                String ngayMua2 = sdf1.format(ngayMua);
                String timeBH = rs.getString(7);
                Date ngayHetBH = rs.getDate(8);
                String ngayHetBH2 = sdf1.format(ngayHetBH);
                boolean trangThaiBH = true;
                if(date.before(ngayHetBH)){
                    trangThaiBH = true;
                }else{
                    trangThaiBH = false;
                }
                String trangThai;
                if (trangThaiBH == true) {
                    trangThai = "Con BH";
                } else {
                    trangThai = "Het BH";
                }
                model.addRow(new Object[]{maBH, maSP, maKH, hoTen, SDT, ngayMua2, timeBH, ngayHetBH2, trangThai});
                listBH.add(new BaoHanh(maBH, maSP, maKH, maKH, SDT, ngayMua2, timeBH, ngayHetBH2, trangThaiBH));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaBH = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cboTimeBH = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBH = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSeachSDT = new javax.swing.JTextField();
        txtSeachTenKH = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Bảo Hành Sản Phẩm");

        jLabel1.setText("Mã BH:");

        jLabel2.setText("Mã SP:");

        jLabel3.setText("Tên KH:");

        jLabel4.setText("SDT:");

        jLabel5.setText("Thời Gian BH:");

        jLabel6.setText("Mã KH:");

        cboTimeBH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 Tháng", "6 Tháng", "9 Tháng", "12 Tháng", "18 Tháng", "24 Tháng", "36 Tháng" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton1.setText("Thêm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh (1).png"))); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        jButton3.setText("Xoá");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/criss-cross.png"))); // NOI18N
        jButton4.setText("Huỷ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm Kiếm");

        tableBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã BH", "Mã SP", "Mã KH", "Tên KH", "SDT", "Ngày Mua Hàng", "Thời Gian BH", "Ngày Hết BH", "Trạng Thái"
            }
        ));
        tableBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBH);

        jLabel8.setText("Tên KH:");

        jLabel9.setText("SĐT:");

        txtSeachSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSeachSDTKeyReleased(evt);
            }
        });

        txtSeachTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSeachTenKHKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(32, 32, 32)
                        .addComponent(txtSeachTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(txtSeachSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaBH)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTimeBH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(64, 64, 64)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(332, 332, 332)
                                .addComponent(jLabel9))
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtMaBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimeBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(46, 46, 46)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSeachSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSeachTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddDB();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        UpdateDB();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBHMouseClicked
        // TODO add your handling code here:
        showDaTaDB();
    }//GEN-LAST:event_tableBHMouseClicked

    private void txtSeachTenKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeachTenKHKeyPressed
        // TODO add your handling code here:
        SeachTenKH();
    }//GEN-LAST:event_txtSeachTenKHKeyPressed

    private void txtSeachSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeachSDTKeyReleased
        // TODO add your handling code here:
        SeachSDT();
    }//GEN-LAST:event_txtSeachSDTKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTimeBH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableBH;
    private javax.swing.JTextField txtMaBH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSeachSDT;
    private javax.swing.JTextField txtSeachTenKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
