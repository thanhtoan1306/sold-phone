/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormChon;

import GUI.FormHienThi.HienThiKhuyenMai;
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
import rojeru_san.RSPanelShadow;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author User
 */
public class ChonKhuyenMai extends javax.swing.JFrame {

    HienThiKhuyenMai formHienThiKM = new HienThiKhuyenMai();

    ChonButton btnChon = new ChonButton();
    HuyButton btnThoat = new HuyButton();

    JTextField txTarget;

    public ChonKhuyenMai(JTextField _txTarget) {

        initComponents();
        this.setLocationRelativeTo(null);
        this.txTarget = _txTarget;
        this.add(formHienThiKM);

        JPanel plBtns = new JPanel();
               plBtns.setBackground(Color.white);
        plBtns.add(btnChon);
        plBtns.add(btnThoat);

        this.add(formHienThiKM, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);
//
//        //set icon cho button
//        btnThoat.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png")));
//        btnChon.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_ok_30px.png")));

        //set event cho button
        btnChon.addActionListener((ActionEvent ae) -> {

            String makm = formHienThiKM.getSelectedRow(1);

            if (makm != null) {
                this.txTarget.setText(makm);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi nào!");
            }
        });

        btnThoat.addActionListener((ae) -> {
            this.dispose();
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chọn khuyến mãi");
        setMinimumSize(new java.awt.Dimension(1200, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
