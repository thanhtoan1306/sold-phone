/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.QuanLy;

import com.qlchdt.service.NhaCungCapService;
import com.qlchdt.view.ThemSua.ThemSuaNhaCungCap;
import com.qlchdt.view.HienThiForm.HienThiNhaCungCap;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyNhaCungCap extends javax.swing.JPanel {

     HienThiNhaCungCap formHienThi = new HienThiNhaCungCap();

    public QuanLyNhaCungCap() {
        initComponents();
        plHienThi.add(formHienThi,BorderLayout.CENTER);
    }

    private void btnSuaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            ThemSuaNhaCungCap suancc = new ThemSuaNhaCungCap("Sửa", mancc);

            suancc.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mancc = formHienThi.getSelectedRow(1);
        if (mancc != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhà cung cấp " + mancc + " ?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new NhaCungCapService().delete(mancc);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhà cung cấp nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaNhaCungCap themncc = new ThemSuaNhaCungCap("Thêm", "");
        themncc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        plHienThi = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_add_30px.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.setPreferredSize(new java.awt.Dimension(130, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png"))); // NOI18N
        jButton2.setText("Xóa");
        jButton2.setPreferredSize(new java.awt.Dimension(130, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png"))); // NOI18N
        jButton3.setText("Sửa");
        jButton3.setPreferredSize(new java.awt.Dimension(130, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        plHienThi.setLayout(new java.awt.BorderLayout());
        add(plHienThi, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       btnThemMouseClicked();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        btnXoaMouseClicked();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        btnSuaMouseClicked();
         formHienThi.refresh();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}
