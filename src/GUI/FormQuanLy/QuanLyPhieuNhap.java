/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BUS.ChiTietHoaDonService;
import BUS.ChiTietPhieuNhapService;
import BUS.HoaDonService;
import BUS.PhieuNhapService;
import BUS.DIO.GhiPDF;
import BUS.DIO.XuatExcel;
import GUI.Custom.MyTable;
import GUI.FormHienThi.HienThiHoaDon;
import GUI.FormHienThi.HienThiPhieuNhap;
import GUI.FormThemSua.ThemSuaHoaDon;
import GUI.FormThemSua.ThemSuaPhieuNhap;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyPhieuNhap extends javax.swing.JPanel {

    HienThiPhieuNhap formHienThi = new HienThiPhieuNhap();

    public QuanLyPhieuNhap() {
        initComponents();
        plHienThi.add(formHienThi, BorderLayout.CENTER);
    }

    
       private void btnSuaMouseClicked() {
        String MaPN = formHienThi.getSelectedRow(1);
        if (MaPN != null) {
            ThemSuaPhieuNhap tspn = new ThemSuaPhieuNhap("Sửa", MaPN);
            tspn.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    formHienThi.refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String MaPN = formHienThi.getSelectedRow(1);
        if (MaPN != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phiếu nhập " + MaPN
                    + " ? Mọi chi tiết trong phiếu nhập sẽ bị xóa theo",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new ChiTietPhieuNhapService().deleteAll(MaPN);
                new PhieuNhapService().delete(MaPN);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaPhieuNhap thempn = new ThemSuaPhieuNhap("Thêm", "");
        thempn.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }
    
    
    public void printPDF() {

        if (formHienThi.getSelectedRow(0) != null) {
            MyTable mtb = formHienThi.getTable();
            new GhiPDF().writePhieuNhap(String.valueOf(mtb.getTable().getValueAt(mtb.getTable().getSelectedRow(), 1)));
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để in");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        plHienThi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 196, 229)));
        jPanel2.setMaximumSize(new java.awt.Dimension(1200, 50));
        jPanel2.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHIẾU NHẬP");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));
        jPanel2.add(jLabel1);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        plHienThi.setBackground(new java.awt.Color(51, 255, 153));
        plHienThi.setLayout(new java.awt.BorderLayout());
        add(plHienThi, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnThem.setBackground(new java.awt.Color(3, 81, 145));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/add_icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(150, 50));
        btnThem.setMinimumSize(new java.awt.Dimension(150, 50));
        btnThem.setPreferredSize(new java.awt.Dimension(150, 50));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnXoa.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/delete_icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(150, 50));
        btnXoa.setMinimumSize(new java.awt.Dimension(150, 50));
        btnXoa.setPreferredSize(new java.awt.Dimension(150, 50));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        btnSua.setBackground(new java.awt.Color(3, 81, 145));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(150, 50));
        btnSua.setMinimumSize(new java.awt.Dimension(150, 50));
        btnSua.setPreferredSize(new java.awt.Dimension(150, 50));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        jButton4.setBackground(new java.awt.Color(3, 81, 145));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/excel_icon.png"))); // NOI18N
        jButton4.setText("Xuất Excel");
        jButton4.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton4.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton4.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        btnPDF.setBackground(new java.awt.Color(3, 81, 145));
        btnPDF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pdf_icon.png"))); // NOI18N
        btnPDF.setText("In PDF");
        btnPDF.setMaximumSize(new java.awt.Dimension(150, 50));
        btnPDF.setMinimumSize(new java.awt.Dimension(150, 50));
        btnPDF.setPreferredSize(new java.awt.Dimension(150, 50));
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDF);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new XuatExcel().xuatFileExcelPhieuNhap();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        printPDF();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnXoaMouseClicked();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel plHienThi;
    // End of variables declaration//GEN-END:variables
}
