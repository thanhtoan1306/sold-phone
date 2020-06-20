/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BUS.ChiTietHoaDonService;
import GUI.FormHienThi.HienThiChiTietHoaDon;
import GUI.FormThemSua.ThemSuaChiTietHoaDon;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class QuanLyChiTietHoaDon extends javax.swing.JFrame {

    HienThiChiTietHoaDon formHienThi;
    String mahd;

    public QuanLyChiTietHoaDon(String _mahd) {
        initComponents();
        
        this.mahd = _mahd;
        this.setTitle("Chi tiết hóa đơn " + this.mahd);
        this.setLocationRelativeTo(null);

        formHienThi = new HienThiChiTietHoaDon(this.mahd);

        plHienThi.add(formHienThi, BorderLayout.CENTER);

     
        this.setVisible(true);

    }

    private void btnSuaMouseClicked() {
        String masp = formHienThi.getSelectedRow(2);
        ThemSuaChiTietHoaDon themcthd = new ThemSuaChiTietHoaDon("Sửa", this.mahd, masp);
        themcthd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }

    private void btnXoaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        String soluong = formHienThi.getSelectedRow(2);
        if (masp != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa " + soluong + " sản phẩm " + masp + " của hóa đơn " + this.mahd + "?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new ChiTietHoaDonService().delete(this.mahd, masp);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn chi tiết nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaChiTietHoaDon themcthd = new ThemSuaChiTietHoaDon("Thêm", this.mahd, "");
        themcthd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        plHienThi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        plButton.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(140, 40));
        btnThem.setMinimumSize(new java.awt.Dimension(140, 40));
        btnThem.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        plButton.add(btnThem);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(140, 40));
        btnXoa.setMinimumSize(new java.awt.Dimension(140, 40));
        btnXoa.setPreferredSize(new java.awt.Dimension(140, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        plButton.add(btnXoa);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(140, 40));
        btnSua.setMinimumSize(new java.awt.Dimension(140, 40));
        btnSua.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        plButton.add(btnSua);

        jPanel1.add(plButton, java.awt.BorderLayout.PAGE_END);

        plHienThi.setLayout(new java.awt.BorderLayout());
        jPanel1.add(plHienThi, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnXoaMouseClicked();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThemActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(QuanLyChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new QuanLyChiTietHoaDon("HD01").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel plButton;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}