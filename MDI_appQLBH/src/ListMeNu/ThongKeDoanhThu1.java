/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListMeNu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyenvandat
 */
public class ThongKeDoanhThu1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form ThongKeDoanhThu
     */
    String hosting = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";
    String user = "sa";
    String password = "123456";
    Connection con;
    DefaultTableModel model;
    int tongDoanhThu = 0;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    Date d1;
    Date d2;

    public ThongKeDoanhThu1() {
        initComponents();
        model = (DefaultTableModel) tableTK.getModel();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(hosting, user, password);
            System.out.println("Connection Succesfully!");
        } catch (Exception e) {
            System.out.println("Connection Eror!");
        }
        fillDaTaDB();
    }

    private void fillDaTaDB() {
        try {
            model.setRowCount(0);
            String sql = "select*from TKDT";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                int soluong = rs.getInt(3);
                Date ngayMuaHang = rs.getDate(4);
                String ngayMua = sdf1.format(ngayMuaHang);
                String maKH = rs.getString(5);
                String tenKH = rs.getString(6);
                int thanhTien = rs.getInt(7);
                model.addRow(new Object[]{maSP, tenSP,soluong, ngayMua, maKH, tenKH, thanhTien});
                tongDoanhThu += thanhTien;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTongDoanhThu.setText(Integer.valueOf(tongDoanhThu).toString());
    }
    String sql;

    private void Seach() {
        String date1 = sdf2.format(Date1.getDate());
        String date2 = sdf2.format(Date2.getDate());
        sql = "select*from TKDT where NGAYMUAHANG between '" + date1 + "'and'" + date2 + "'";
        if (txtTenSP.getText().length() > 0) {
            sql = "select*from TKDT where NGAYMUAHANG between '" + date1 + "'and'" + date2 + "'AND TENSANPHAM = '" + txtTenSP.getText() + "'";
        }
        try {
            model.setRowCount(0);
            tongDoanhThu = 0;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                int soluong = rs.getInt(3);
                Date ngayMuaHang = rs.getDate(4);
                String ngayMua = sdf1.format(ngayMuaHang);
                String maKH = rs.getString(5);
                String tenKH = rs.getString(6);
                int thanhTien = rs.getInt(7);
                model.addRow(new Object[]{maSP, tenSP,soluong, ngayMua, maKH, tenKH, thanhTien});
                tongDoanhThu += thanhTien;
            }
            txtTongDoanhThu.setText(Integer.valueOf(tongDoanhThu).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SeachTenSP() {
        try {
            String sql = "select * from TKDT where TENSANPHAM like '%" + txtTenSP.getText() + "%'";
            model.setRowCount(0);
            tongDoanhThu = 0;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                int soluong = rs.getInt(3);
                Date ngayMuaHang = rs.getDate(4);
                String ngayMua = sdf1.format(ngayMuaHang);
                String maKH = rs.getString(5);
                String tenKH = rs.getString(6);
                int thanhTien = rs.getInt(7);
                model.addRow(new Object[]{maSP, tenSP,soluong, ngayMua, maKH, tenKH, thanhTien});
                tongDoanhThu += thanhTien;
            }
            txtTongDoanhThu.setText(Integer.valueOf(tongDoanhThu).toString());
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTK = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Date2 = new com.toedter.calendar.JDateChooser();
        Date1 = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setTitle("Thống kê doanh thu");

        jLabel2.setText("Từ Ngày:");

        jLabel3.setText("Đến Ngày");

        jButton1.setText("Xem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên SP:");

        txtTenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenSPKeyReleased(evt);
            }
        });

        tableTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Ngày Mua Hàng", "Mã KH", "Tên KH", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTK);

        jLabel4.setText("Tổng Doanh Thu:");

        jLabel5.setText("Thống Kê Doanh Thu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Seach();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTenSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSPKeyReleased
        // TODO add your handling code here:
        SeachTenSP();
    }//GEN-LAST:event_txtTenSPKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date1;
    private com.toedter.calendar.JDateChooser Date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableTK;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
