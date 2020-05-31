/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.formquanly;

import com.qlchdt.view.hienthi.HienThiChiTietHoaDon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyChiTietHoaDon extends javax.swing.JFrame {

    HienThiChiTietHoaDon formHienThi;

    String mahd;

    JButton btnThem = new JButton("Thêm");
    JButton btnXoa = new JButton("Xóa");
    JButton btnSua = new JButton("Sửa");

    public QuanLyChiTietHoaDon(String _mahd) {
        initComponents();
        this.mahd = _mahd;
        this.setTitle("Chi tiết hóa đơn " + this.mahd);
        formHienThi = new HienThiChiTietHoaDon(this.mahd);

    }

        
//    private void btnSuaMouseClicked() {
//        String masp = formHienThi.getSelectedRow(2);
//        ThemSuaChiTietHoaDonForm themcthd = new ThemSuaChiTietHoaDonForm("Sửa", this.mahd, masp);
//        themcthd.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                formHienThi.refresh();
//            }
//        });
//    }
//
//    private void btnXoaMouseClicked() {
//        String masp = formHienThi.getSelectedRow(1);
//        String soluong = formHienThi.getSelectedRow(2);
//        if (masp != null) {
//            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa " + soluong + " sản phẩm " + masp + " của hóa đơn "+ this.mahd +"?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
//                new QuanLyChiTietHoaDonBUS().delete(this.mahd, masp);
//                formHienThi.refresh();
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Chưa chọn chi tiết nào để xóa");
//        }
//    }
//
//    private void btnThemMouseClicked() {
//        ThemSuaChiTietHoaDonForm themcthd = new ThemSuaChiTietHoaDonForm("Thêm", this.mahd, "");
//        themcthd.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                formHienThi.refresh();
//            }
//        });
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        plHienThi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout plButtonLayout = new javax.swing.GroupLayout(plButton);
        plButton.setLayout(plButtonLayout);
        plButtonLayout.setHorizontalGroup(
            plButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );
        plButtonLayout.setVerticalGroup(
            plButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(plButton, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout plHienThiLayout = new javax.swing.GroupLayout(plHienThi);
        plHienThi.setLayout(plHienThiLayout);
        plHienThiLayout.setHorizontalGroup(
            plHienThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );
        plHienThiLayout.setVerticalGroup(
            plHienThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        jPanel1.add(plHienThi, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChiTietHoaDon("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel plButton;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}
