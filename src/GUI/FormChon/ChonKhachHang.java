/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormChon;

import GUI.FormHienThi.HienThiKhachHang;
import GUI.Custom.ChonButton;
import GUI.Custom.HuyButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ChonKhachHang extends javax.swing.JFrame {

    HienThiKhachHang formHienThiKH = new HienThiKhachHang();

    ChonButton btnChon = new ChonButton();
    HuyButton btnThoat = new HuyButton();

    JTextField txTarget;

    public ChonKhachHang(JTextField _txTarget) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.txTarget = _txTarget;
        this.add(formHienThiKH);

        JPanel plBtns = new JPanel();
        plBtns.setBackground(Color.white);
        plBtns.add(btnChon);
        plBtns.add(btnThoat);

        this.add(formHienThiKH, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        //set icon cho button
      //  btnThoat.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png")));
        //btnChon.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_ok_30px.png")));

        //set event cho button
        btnChon.addActionListener((ActionEvent ae) -> {

            String makh = formHienThiKH.getSelectedRow(1);
        if (makh != null) {
            this.txTarget.setText(makh);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng nào!");
        }
        });

        btnThoat.addActionListener((ae) -> {
            this.dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ChonKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new ChonKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
