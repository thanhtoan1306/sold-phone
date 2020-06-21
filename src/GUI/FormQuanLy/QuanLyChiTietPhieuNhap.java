/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BUS.ChiTietPhieuNhapService;
import GUI.FormHienThi.HienThiChiTietHoaDon;
import GUI.FormHienThi.HienThiChiTietPhieuNhap;
import GUI.FormThemSua.ThemSuaChiTietPhieuNhap;
import GUI.FormThemSua.ThemSuaChiTietPhieuNhap;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyChiTietPhieuNhap extends javax.swing.JFrame {

    HienThiChiTietPhieuNhap formHienThi;

    String mapn;


    public QuanLyChiTietPhieuNhap(String _mapn) {
        initComponents();
        this.mapn = _mapn;
        this.setLocationRelativeTo(null);
        this.setTitle("Chi tiết phiếu nhập " + this.mapn);
        
        formHienThi = new HienThiChiTietPhieuNhap(this.mapn);
        
        plHienThi.add(formHienThi, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void btnXoaMouseClicked() {
        String masp = formHienThi.getSelectedRow(2);
        if (masp != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm " + masp + " của phiếu nhập " + this.mapn + "?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new ChiTietPhieuNhapService().delete(this.mapn, masp);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn chi tiết nào để xóa");
        }
    }

    private void btnSuaMouseClicked() {
        String masp = formHienThi.getSelectedRow(2);
        System.out.println("masp:" + masp);

        ThemSuaChiTietPhieuNhap themctpn = new ThemSuaChiTietPhieuNhap("Sửa", this.mapn, masp);
        themctpn.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }

    private void btnThemMouseClicked() {
        ThemSuaChiTietPhieuNhap themcthd = new ThemSuaChiTietPhieuNhap("Thêm", this.mapn, "");
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

        plHienThi = new javax.swing.JPanel();
        plButton2 = new javax.swing.JPanel();
        btnThem1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(980, 650));

        plHienThi.setLayout(new java.awt.BorderLayout());
        getContentPane().add(plHienThi, java.awt.BorderLayout.CENTER);

        plButton2.setBackground(new java.awt.Color(255, 255, 255));

        btnThem1.setBackground(new java.awt.Color(3, 81, 145));
        btnThem1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/add_icon.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.setMaximumSize(new java.awt.Dimension(140, 40));
        btnThem1.setMinimumSize(new java.awt.Dimension(140, 40));
        btnThem1.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        plButton2.add(btnThem1);

        btnXoa1.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/delete_icon.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.setMaximumSize(new java.awt.Dimension(140, 40));
        btnXoa1.setMinimumSize(new java.awt.Dimension(140, 40));
        btnXoa1.setPreferredSize(new java.awt.Dimension(140, 40));
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });
        plButton2.add(btnXoa1);

        btnSua1.setBackground(new java.awt.Color(3, 81, 145));
        btnSua1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua1.setForeground(new java.awt.Color(255, 255, 255));
        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.setMaximumSize(new java.awt.Dimension(140, 40));
        btnSua1.setMinimumSize(new java.awt.Dimension(140, 40));
        btnSua1.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });
        plButton2.add(btnSua1);

        getContentPane().add(plButton2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        btnXoaMouseClicked();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSua1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(QuanLyChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChiTietPhieuNhap("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JPanel plButton2;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}
