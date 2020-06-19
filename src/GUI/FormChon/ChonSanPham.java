/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormChon;

import GUI.Custom.ChonButton;
import GUI.Custom.HuyButton;
import GUI.FormHienThi.HienThiSanPham;
import GUI.FormQuanLy.SanPhamForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ChonSanPham extends javax.swing.JFrame {

    HienThiSanPham formHienThiSanPham = new HienThiSanPham();
    ChonButton btnChon = new ChonButton();
    HuyButton btnThoat = new HuyButton();

    JTextField txMasp;
    JTextField txMalsp;
    JTextField txTensp;
    JTextField txDonGia;
    JTextField txSoluong;

    public ChonSanPham(JTextField _masp, JTextField _malsp, JTextField _tensp, JTextField _donGia, JTextField _soLuong) {
        initComponents();
        this.txMasp = _masp;
        this.txMalsp = _malsp;
        this.txTensp = _tensp;
        this.txDonGia = _donGia;
        this.txSoluong = _soLuong;
        this.setLocationRelativeTo(null);

        JPanel plBtns = new JPanel();
        plBtns.setBackground(Color.white);
        plBtns.add(btnChon);
        plBtns.add(btnThoat);

        this.add(formHienThiSanPham, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);
        
         btnChon.addActionListener((ActionEvent ae) -> {
            btnChonSPOnClick();

        });

        btnThoat.addActionListener((ae) -> {
            this.dispose();
        });
        this.setVisible(true);
    }

    private void btnChonSPOnClick() {

        if (formHienThiSanPham.getSelectedSanPham(0) != null) {
            if (this.txMasp != null) {
                this.txMasp.setText(formHienThiSanPham.getSelectedSanPham(0));
            }
            if (this.txMalsp != null) {
                this.txMalsp.setText(formHienThiSanPham.getSelectedSanPham(1));
            }
            if (this.txTensp != null) {
                this.txTensp.setText(formHienThiSanPham.getSelectedSanPham(2));
            }
            if (this.txDonGia != null) {
                this.txDonGia.setText(formHienThiSanPham.getSelectedSanPham(3));
            }
            if (this.txSoluong != null) {
                this.txSoluong.setText(formHienThiSanPham.getSelectedSanPham(4));
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm nào!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(ChonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChonSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChonSanPham().setVisible(true);
//            }
//        });
//}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
