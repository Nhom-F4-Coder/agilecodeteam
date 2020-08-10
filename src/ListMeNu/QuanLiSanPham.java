/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListMeNu;

import Class.SanPham;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvandat
 */
public class QuanLiSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLiSanPham
     */
    ArrayList<SanPham> lstSanPham;
    protected String userName;
    protected String pass;
    int index;
    
    public QuanLiSanPham() {
        initComponents();
        this.lstSanPham = new ArrayList<>();
        this.userName = "sa";
        this.pass = "123456"; 
        this.lstSanPham = this.fectList();
        this.renderTable(lstSanPham);
    }

    private void renderTable(ArrayList<SanPham> data) {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {
            SanPham x = lstSanPham.get(i);
            model.addRow(new Object[]{x.getMaSP(), x.getTenSP(), x.getGia(), x.getTinhTrang(), x.getCauHinh(), x.getKhuyenMai(), x.getSoLuong(), x.getNgayNhapKho()});
        }
    }

    protected ArrayList<SanPham> fectList() {
        ArrayList<SanPham> listresult = new ArrayList<SanPham>();
        String hosting = "jdbc:sqlserver://LocalHost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con;
        try {
            con = DriverManager.getConnection(hosting, this.userName, this.pass);
            String sql = "Select* from SANPHAM";
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString("MASANPHAM");
                String ten = rs.getString("TENSANPHAM");
                int soLuong = rs.getInt("SOLUONG");
                int gia = rs.getInt("GIA");
                int khuyenMai = rs.getInt("KHUYENMAI");
                String ngayNhap = rs.getDate("NGAYNHAPKHO") + "";
                String tinhTrang = rs.getString("TINHTRANG");
                String cauHinh = rs.getString("CAUHINH");

                listresult.add(new SanPham(ma, ten, tinhTrang, cauHinh, ngayNhap, soLuong, khuyenMai, gia));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(QuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listresult;
    }

    public void showDetails() {
        SanPham sp = lstSanPham.get(index);
        txtMaSP.setText(sp.getMaSP());
        txtNgayNhapKho.setText(sp.getNgayNhapKho());
        txtGiaSP.setText(sp.getGia() + "");
        txtSoLuongSP.setText(sp.getSoLuong() + "");
        txtTenSP.setText(sp.getTenSP());
        cboGiamGia.setSelectedItem(sp.getKhuyenMai());
        cboTinhTrang.setSelectedItem(sp.getTinhTrang());
        txtCauHinh.setText(sp.getCauHinh());
        txtNgayNhapKho.setText(sp.getNgayNhapKho());
        tblSanPham.setRowSelectionInterval(index, index);
    }

    public void checkNull() {
        try {
            if (txtTenSP.getText().equals("") || txtMaSP.getText().equals("") || txtSoLuongSP.getText().equals("") || txtGiaSP.getText().equals("") || txtCauHinh.getText().equals("") || txtNgayNhapKho.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
                return;
            }

            int so = Integer.parseInt(txtGiaSP.getText());
            if (so <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phải là số dương");
                return;
            }

            so = Integer.parseInt(txtSoLuongSP.getText());
            if (so <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm phải là số dương");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d;
            try {
                d = sdf.parse(txtNgayNhapKho.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ngày phải đúng định dạng: (yyyy-mm-dd)");
                txtNgayNhapKho.requestFocus();
                return;
            }

        } catch (Exception e) {
        }
    }

    public void clear() {
        txtGiaSP.setText("");
        txtTenSP.setText("");
        txtMaSP.setText("");
        txtSoLuongSP.setText("");
        txtCauHinh.setText("");
        txtNgayNhapKho.setText("");
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
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtGiaSP = new javax.swing.JTextField();
        cboGiamGia = new javax.swing.JComboBox<>();
        cboTinhTrang = new javax.swing.JComboBox<>();
        txtSoLuongSP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCauHinh = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiemMaSP = new javax.swing.JTextField();
        txtTimKiemTenSP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cboTinKiemMucGia = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtNgayNhapKho = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Sản Phẩm");

        jLabel1.setText("Mã SP:");

        jLabel2.setText("Tên SP:");

        jLabel3.setText("Giá:");

        jLabel4.setText("SLSP:");

        jLabel5.setText("Giảm Giá:");

        jLabel6.setText("Tình Trạng:");

        jLabel7.setText("Cấu Hình:");

        cboGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50" }));

        cboTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "LikeNew" }));

        txtCauHinh.setColumns(20);
        txtCauHinh.setRows(5);
        jScrollPane1.setViewportView(txtCauHinh);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        btnAdd.setText("Thêm Mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh (1).png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/criss-cross.png"))); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel8.setText("Danh Sách Sản Phẩm");

        jLabel9.setText("Mã SP:");

        jLabel10.setText("Tên SP:");

        jLabel11.setText("Mức Giá:");

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá Bán", "Tình Trạng", "Cấu Hình", "Giảm Giá", "SL Tồn Kho", "Nhập Kho"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        cboTinKiemMucGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5-10 Triệu", "10-15 Triệu", "15-20 Triệu", "25-30 Triệu", "30-40 Triệu", ">40 Triệu" }));

        jLabel12.setText("Ngày Nhập Kho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator1)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTimKiemMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboTinKiemMucGia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnTimKiem))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGiaSP)
                                            .addComponent(txtSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboGiamGia, 0, 100, Short.MAX_VALUE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtNgayNhapKho)))
                            .addComponent(jScrollPane2))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(txtSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnHuy)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(cboGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiemMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTinKiemMucGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        try {
            index = tblSanPham.getSelectedRow();
            if (index >= 0) {
                showDetails();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Loi clickTable");
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        checkNull();

        String hostString = "jdbc:sqlserver://Localhost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con;
            con = DriverManager.getConnection(hostString, this.userName, this.pass);
            String sql = "insert into SANPHAM values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtMaSP.getText());
            ps.setString(2, txtTenSP.getText());
            ps.setString(3, txtSoLuongSP.getText());
            ps.setString(4, txtGiaSP.getText());
            ps.setString(5, cboGiamGia.getSelectedItem() + "");
            ps.setString(6, txtNgayNhapKho.getText());
            ps.setString(7, cboTinhTrang.getSelectedItem() + "");
            ps.setString(8, txtCauHinh.getText());
            ps.execute();
            
            this.lstSanPham = this.fectList();
            this.renderTable(lstSanPham);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không được thêm trùng mã");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(this, "Ban co muon xoa khong?");
        if (input == JOptionPane.YES_OPTION) {
            index = this.tblSanPham.getSelectedRow();
            if (index == -1) {
                return;
            }
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String hosting = "jdbc:sqlserver://LocalHost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";

                Connection conn = DriverManager.getConnection(hosting, userName, pass);
                SanPham sp = lstSanPham.get(index);
                String maSp = sp.getMaSP();
                //String maSp = this.tblSanPham.getValueAt(row, 1).toString();

                System.out.println(maSp);

                String query = "delete from SANPHAM where MASANPHAM=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, maSp);
                ps.executeUpdate();
                this.lstSanPham = this.fectList();
                this.renderTable(lstSanPham);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập mã sản phẩm");
            txtMaSP.requestFocus();
            return;
        }
        try {
            String hosting = "jdbc:sqlserver://LocalHost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con;
            con = DriverManager.getConnection(hosting, this.userName, this.pass);
            String sql = "Update SANPHAM set TENSANPHAM=?,SOLUONG=?,GIA=?,KHUYENMAI=?,NGAYNHAPKHO=?,TINHTRANG=?,CAUHINH=? where MASANPHAM=?  ";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, this.txtTenSP.getText());
            ps.setString(2, this.txtSoLuongSP.getText());
            ps.setString(3, this.txtGiaSP.getText());
            ps.setString(4, this.cboGiamGia.getSelectedItem() + "");
            ps.setString(5, this.txtNgayNhapKho.getText());
            ps.setString(6, this.cboTinhTrang.getSelectedItem() + "");
            ps.setString(7, this.txtCauHinh.getText());
            ps.setString(8, this.txtMaSP.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update thành công");
            this.lstSanPham = this.fectList();
            this.renderTable(lstSanPham);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Loi update");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        clear();
        txtMaSP.requestFocus();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String id = txtTimKiemMaSP.getText();
        String tenSP = txtTimKiemTenSP.getText();
        int i;
        for (i = 0; i < lstSanPham.size(); i++) {
            SanPham e = lstSanPham.get(i);
            if (e.getMaSP().equalsIgnoreCase(id) || e.getTenSP().equalsIgnoreCase(tenSP)) {
                index = i;
                JOptionPane.showMessageDialog(this, "Tìm thành công..!!");
                showDetails();
                break;

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
                break;
            }

        }
        if (i == lstSanPham.size()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm");
            clear();
        }



    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboGiamGia;
    private javax.swing.JComboBox<String> cboTinKiemMucGia;
    private javax.swing.JComboBox<String> cboTinhTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtCauHinh;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhapKho;
    private javax.swing.JTextField txtSoLuongSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiemMaSP;
    private javax.swing.JTextField txtTimKiemTenSP;
    // End of variables declaration//GEN-END:variables
}
