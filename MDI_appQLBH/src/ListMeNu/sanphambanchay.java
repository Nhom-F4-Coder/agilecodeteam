/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListMeNu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class sanphambanchay extends javax.swing.JInternalFrame {

    /**
     * Creates new form sanphambanchay
     */
    Connection cnt;
    String hosting = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QUANLIBANHANGDB";
    String user = "sa";
    String pass = "123456";
    DefaultTableModel model;
    int index;

    public sanphambanchay() {
        initComponents();
        model = (DefaultTableModel) tblsanpham.getModel();
        try {
            connect();
//            JOptionPane.showMessageDialog(this, "kết nối thành công");
            filltotable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
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

        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mindate = new com.toedter.calendar.JDateChooser();
        maxdate = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setMaximizable(true);
        setTitle("Sản phẩm bán chạy");

        jLabel3.setText("Đến Ngày");

        jLabel2.setText("Từ Ngày:");

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Top", "Tên SP", "Số Lượng Bán Được"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblsanpham);

        jLabel1.setText("Thống Kê Sản Phẩm Bán Chạy Nhất");

        jButton1.setText("Xem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mindate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jButton1))
                            .addComponent(mindate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(maxdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
//        if(check()){
            timkiem();
            System.out.println("bấm");
//        }else{System.out.println("sai");}
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser maxdate;
    private com.toedter.calendar.JDateChooser mindate;
    private javax.swing.JTable tblsanpham;
    // End of variables declaration//GEN-END:variables
    private void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnt = DriverManager.getConnection(hosting, user, pass);
        } catch (Exception e) {
        }

    }

    private void filltotable() {
        // lệnh truy vấn   
        try {
            String sql = "select TENSANPHAM,HOADONCHITIET.SOLUONG \n"
                    + "from  SANPHAM inner join HOADONCHITIET on SANPHAM.MASANPHAM = HOADONCHITIET.MASANPHAM\n"
                    + "order by  HOADONCHITIET.SOLUONG desc";
            // tạo đối tg thực thi lệnh
            //b4 tạo đối tượng Statement
            Statement stt = cnt.createStatement();
            //b5 thi hành câu truy vấn 
            ResultSet rs = stt.executeQuery(sql);
            int i = 0;

            while (rs.next()) {
                i++;
                String tensanpham = rs.getString(1);
                String soluong = rs.getString(2);
                Object[] row = new Object[]{i, tensanpham, soluong};
                model.addRow(row);
                // System.out.println("id: " + tensanpham +" " + soluong);

            }
            // model.addRow(new Object[]{"0","0", "0"});

        } catch (Exception e) {
        }

    }

    private void timkiem() {

//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = mindate.getDate();
        Date d2 = maxdate.getDate();
//        try {
//            d1 = sdf.parse(txtngaymua.getText());
//            d2 = sdf.parse(txtngayhet.getText());
            try {
                String query = "select TENSANPHAM,HOADONCHITIET.SOLUONG from  SANPHAM inner join HOADONCHITIET on SANPHAM.MASANPHAM = HOADONCHITIET.MASANPHAM where NGAYMUAHANG between ? and ? order by  HOADONCHITIET.SOLUONG desc";
                PreparedStatement stt = cnt.prepareStatement(query);
                stt.setDate(1,new java.sql.Date(d1.getTime()));
                stt.setDate(2, new java.sql.Date(d2.getTime()));
                stt.execute();
                System.out.println("d1: "+new java.sql.Date(d1.getTime()));
                System.out.println("d2: "+new java.sql.Date(d2.getTime()));
                ResultSet rs = stt.executeQuery();
                int i = 0;
                model.setRowCount(0);
                while (rs.next()) {
                    i++;
                    String tensanpham = rs.getString(1);
                    String soluong = rs.getString(2);
                    Object[] row = new Object[]{i, tensanpham, soluong};
                    model.addRow(row);
                    //System.out.println("Top: " + i + "tên: " + tensanpham + "số lượng: " + soluong);
                    //filltotable();
                }

                // cnt.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "lỗi k đọc đc");

            }
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }    
    }
   

//    private boolean check() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Date d1 = null;
//        Date d2 = null;
//        try {
//            d1 = sdf.parse(txtngaymua.getText());
//            d2 = sdf.parse(txtngayhet.getText());
//        } catch (ParseException ex) {
//            JOptionPane.showMessageDialog(this, "nhập sai định dạng ngày dd-mm-yyyy");
//            txtngaymua.requestFocus();
//            return false;
//        }
//        return true;
//    }
}
