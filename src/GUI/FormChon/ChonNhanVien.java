/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormChon;

import BUS.NhanVienService;
import GUI.DangNhap;
import GUI.Custom.ChonButton;
import GUI.Custom.HuyButton;
import GUI.FormHienThi.HienThiNhanVien;

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
public class ChonNhanVien extends javax.swing.JFrame {

    HienThiNhanVien formHienThiNV = new HienThiNhanVien();
    ChonButton btnChon = new ChonButton();
    HuyButton btnThoat = new HuyButton();

    JTextField txTarget;
    JTextField tx;

    public ChonNhanVien(JTextField _txTarget) {
        initComponents();
         this.setLocationRelativeTo(null);
        this.txTarget = _txTarget;
        this.add(formHienThiNV);

        JPanel plBtns = new JPanel();
        plBtns.setBackground(Color.white);
        plBtns.add(btnChon);
        plBtns.add(btnThoat);

        this.add(formHienThiNV, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

  
        //set event cho button
        btnChon.addActionListener((ActionEvent ae) -> {

            String makh = formHienThiNV.getSelectedRow(0);
        if (makh != null) {
            this.txTarget.setText(makh);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào!");
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
        setMinimumSize(new java.awt.Dimension(1200, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
