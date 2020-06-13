/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.QuanLy;

import com.qlchdt.service.KhachHangService;
import com.qlchdt.service.qlnhapxuat.NhapExcel;
import com.qlchdt.service.qlnhapxuat.XuatExcel;
import com.qlchdt.view.DangNhap;
import com.qlchdt.view.HienThiForm.HienThiKhachHang;
import com.qlchdt.view.ThemSua.ThemSuaKhachHang;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyKhachHang extends javax.swing.JPanel {

    HienThiKhachHang formHienThi = new HienThiKhachHang();

    public QuanLyKhachHang() {
        initComponents();
       
        plHienThi.add(formHienThi);
    }

    //xóa
    private void btnXoaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            KhachHangService khachHangService = new KhachHangService();
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn khách hàng " + makh + " ?",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                khachHangService.delete(makh);
                formHienThi.refresh();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để xóa");
        }
    }
    //sửa

    private void btnSuaMouseClicked() {
        String makh = formHienThi.getSelectedRow(1);
        if (makh != null) {
            ThemSuaKhachHang suakh = new ThemSuaKhachHang("Sửa", makh);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suakh.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để sửa");
        }
    }

    //thêm
    private void btnThemMouseClicked() {
        ThemSuaKhachHang themkh = new ThemSuaKhachHang("Thêm", "");
        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
        System.out.println("Đã nhấn nút thêm ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        plHienThi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(176, 196, 229));
        jPanel4.setMaximumSize(new java.awt.Dimension(1200, 50));
        jPanel4.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHÁCH HÀNG");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));
        jPanel4.add(jLabel1);

        add(jPanel4, java.awt.BorderLayout.PAGE_START);

        plHienThi.setBackground(new java.awt.Color(0, 102, 102));
        plHienThi.setLayout(new java.awt.BorderLayout());
        add(plHienThi, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(140, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/excel.png"))); // NOI18N
        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.setPreferredSize(new java.awt.Dimension(140, 40));
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhapExcel);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/excel.png"))); // NOI18N
        jButton2.setText("Xuất Excel");
        jButton2.setPreferredSize(new java.awt.Dimension(140, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new XuatExcel().xuatFileExcelKhachHang();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        new NhapExcel().docFileExcelKhachhang();
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnXoaMouseClicked();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}
