/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;

import com.qlchdt.service.ChuyenPanel;
import com.qlchdt.view.QuanLy.QuanLyBanHang;
import com.qlchdt.view.QuanLy.QuanLyHoaDon;
import com.qlchdt.view.QuanLy.QuanLyKhachHang;
import com.qlchdt.view.QuanLy.QuanLyKhuyenMai;
import com.qlchdt.view.QuanLy.QuanLyNhaCungCap;
import com.qlchdt.view.QuanLy.QuanLyNhanVien;
import com.qlchdt.view.QuanLy.QuanLyQuyen;
import com.qlchdt.view.QuanLy.QuanLyTaiKhoan;
import com.qlchdt.view.QuanLy.qlhsp;
import com.qlchdt.view.QuanLy.qlsp;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class TestHeThong extends javax.swing.JFrame {

     QuanLyBanHang fbh = new QuanLyBanHang();
     
    public TestHeThong() {
        initComponents();
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnDoiMatKhau = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnHSP = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhaCungCap = new javax.swing.JButton();
        btnTaiKhoan = new javax.swing.JButton();
        btnQuyen = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        plHienthi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hệ thống");
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(1400, 900));
        setMinimumSize(new java.awt.Dimension(1400, 900));
        setPreferredSize(new java.awt.Dimension(1400, 900));

        jPanel1.setBackground(new java.awt.Color(2, 155, 212));
        jPanel1.setMaximumSize(new java.awt.Dimension(1400, 130));
        jPanel1.setMinimumSize(new java.awt.Dimension(1400, 130));
        jPanel1.setPreferredSize(new java.awt.Dimension(1400, 130));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/logo_team.png"))); // NOI18N
        rSPanelImage1.setMaximumSize(new java.awt.Dimension(220, 120));
        rSPanelImage1.setMinimumSize(new java.awt.Dimension(220, 100));
        rSPanelImage1.setPreferredSize(new java.awt.Dimension(220, 120));

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.add(rSPanelImage1);

        jPanel9.setMaximumSize(new java.awt.Dimension(800, 0));
        jPanel9.setMinimumSize(new java.awt.Dimension(800, 0));
        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(800, 0));
        jPanel9.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9);

        jPanel3.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 80));

        btnDoiMatKhau.setBackground(new java.awt.Color(3, 81, 145));
        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/password (1).png"))); // NOI18N
        btnDoiMatKhau.setToolTipText("");
        btnDoiMatKhau.setMaximumSize(new java.awt.Dimension(50, 50));
        btnDoiMatKhau.setMinimumSize(new java.awt.Dimension(50, 50));
        btnDoiMatKhau.setOpaque(false);
        btnDoiMatKhau.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        jPanel3.add(btnDoiMatKhau);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đổi mật khẩu");
        jPanel3.add(jLabel2);

        jPanel1.add(jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 80));

        jButton2.setBackground(new java.awt.Color(3, 81, 145));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/logout.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton2.setOpaque(false);
        jButton2.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Đăng xuất");
        jPanel5.add(jLabel3);

        jPanel6.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 80));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/password.png"))); // NOI18N
        jButton3.setToolTipText("");
        jButton3.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton3.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton3.setOpaque(false);
        jButton3.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel6.add(jButton3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Đổi mật khẩu");
        jPanel6.add(jLabel4);

        jPanel5.add(jPanel6);

        jPanel1.add(jPanel5);

        jPanel7.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 80));

        jButton4.setBackground(new java.awt.Color(3, 81, 145));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/shutdown.png"))); // NOI18N
        jButton4.setToolTipText("");
        jButton4.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton4.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton4.setOpaque(false);
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thoát");
        jPanel7.add(jLabel5);

        jPanel8.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel8.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 80));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/password.png"))); // NOI18N
        jButton5.setToolTipText("");
        jButton5.setMaximumSize(new java.awt.Dimension(50, 50));
        jButton5.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton5.setOpaque(false);
        jButton5.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel8.add(jButton5);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Đổi mật khẩu");
        jPanel8.add(jLabel6);

        jPanel7.add(jPanel8);

        jPanel1.add(jPanel7);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(2, 155, 212));
        jPanel2.setMaximumSize(new java.awt.Dimension(250, 900));
        jPanel2.setMinimumSize(new java.awt.Dimension(250, 900));
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 900));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, -6));

        jPanel10.setBackground(new java.awt.Color(2, 155, 212));
        jPanel10.setMaximumSize(new java.awt.Dimension(250, 900));
        jPanel10.setMinimumSize(new java.awt.Dimension(250, 900));
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(250, 900));

        btnBanHang.setBackground(new java.awt.Color(3, 81, 145));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/ecommerce.png"))); // NOI18N
        btnBanHang.setText("BÁN HÀNG");
        btnBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBanHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBanHang.setIconTextGap(20);
        btnBanHang.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnBanHang.setMaximumSize(new java.awt.Dimension(250, 50));
        btnBanHang.setMinimumSize(new java.awt.Dimension(250, 50));
        btnBanHang.setOpaque(false);
        btnBanHang.setPreferredSize(new java.awt.Dimension(250, 50));
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });
        jPanel10.add(btnBanHang);

        jButton7.setBackground(new java.awt.Color(3, 81, 145));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/logistics.png"))); // NOI18N
        jButton7.setText("NHẬP HÀNG");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.setIconTextGap(20);
        jButton7.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jButton7.setMaximumSize(new java.awt.Dimension(250, 50));
        jButton7.setMinimumSize(new java.awt.Dimension(250, 50));
        jButton7.setOpaque(false);
        jButton7.setPreferredSize(new java.awt.Dimension(250, 50));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton7);

        btnSanPham.setBackground(new java.awt.Color(3, 81, 145));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/smartphone.png"))); // NOI18N
        btnSanPham.setText("SẢN PHẨM");
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSanPham.setIconTextGap(20);
        btnSanPham.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnSanPham.setMaximumSize(new java.awt.Dimension(250, 50));
        btnSanPham.setMinimumSize(new java.awt.Dimension(250, 50));
        btnSanPham.setOpaque(false);
        btnSanPham.setPreferredSize(new java.awt.Dimension(250, 50));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        jPanel10.add(btnSanPham);

        btnHSP.setBackground(new java.awt.Color(3, 81, 145));
        btnHSP.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnHSP.setForeground(new java.awt.Color(255, 255, 255));
        btnHSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_multiple_smartphones_30px.png"))); // NOI18N
        btnHSP.setText("HÃNG SẢN PHẨM");
        btnHSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHSP.setIconTextGap(20);
        btnHSP.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnHSP.setMaximumSize(new java.awt.Dimension(250, 50));
        btnHSP.setMinimumSize(new java.awt.Dimension(250, 50));
        btnHSP.setOpaque(false);
        btnHSP.setPreferredSize(new java.awt.Dimension(250, 50));
        btnHSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHSPActionPerformed(evt);
            }
        });
        jPanel10.add(btnHSP);

        btnHoaDon.setBackground(new java.awt.Color(3, 81, 145));
        btnHoaDon.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/bill.png"))); // NOI18N
        btnHoaDon.setText("HÓA ĐƠN");
        btnHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHoaDon.setIconTextGap(20);
        btnHoaDon.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnHoaDon.setMaximumSize(new java.awt.Dimension(250, 50));
        btnHoaDon.setMinimumSize(new java.awt.Dimension(250, 50));
        btnHoaDon.setOpaque(false);
        btnHoaDon.setPreferredSize(new java.awt.Dimension(250, 50));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        jPanel10.add(btnHoaDon);

        jButton11.setBackground(new java.awt.Color(3, 81, 145));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/invoice.png"))); // NOI18N
        jButton11.setText("PHIẾU NHẬP");
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton11.setIconTextGap(20);
        jButton11.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jButton11.setMaximumSize(new java.awt.Dimension(250, 50));
        jButton11.setMinimumSize(new java.awt.Dimension(250, 50));
        jButton11.setOpaque(false);
        jButton11.setPreferredSize(new java.awt.Dimension(250, 50));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton11);

        btnKhuyenMai.setBackground(new java.awt.Color(3, 81, 145));
        btnKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/sale.png"))); // NOI18N
        btnKhuyenMai.setText("KHUYẾN MÃI");
        btnKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhuyenMai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKhuyenMai.setIconTextGap(20);
        btnKhuyenMai.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnKhuyenMai.setMaximumSize(new java.awt.Dimension(250, 50));
        btnKhuyenMai.setMinimumSize(new java.awt.Dimension(250, 50));
        btnKhuyenMai.setOpaque(false);
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(250, 50));
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel10.add(btnKhuyenMai);

        btnNhanVien.setBackground(new java.awt.Color(3, 81, 145));
        btnNhanVien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/employee.png"))); // NOI18N
        btnNhanVien.setText("NHÂN VIÊN");
        btnNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhanVien.setIconTextGap(20);
        btnNhanVien.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnNhanVien.setMaximumSize(new java.awt.Dimension(250, 50));
        btnNhanVien.setMinimumSize(new java.awt.Dimension(250, 50));
        btnNhanVien.setOpaque(false);
        btnNhanVien.setPreferredSize(new java.awt.Dimension(250, 50));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jPanel10.add(btnNhanVien);

        btnKhachHang.setBackground(new java.awt.Color(3, 81, 145));
        btnKhachHang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/people.png"))); // NOI18N
        btnKhachHang.setText("KHÁCH HÀNG");
        btnKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKhachHang.setIconTextGap(20);
        btnKhachHang.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnKhachHang.setMaximumSize(new java.awt.Dimension(250, 50));
        btnKhachHang.setMinimumSize(new java.awt.Dimension(250, 50));
        btnKhachHang.setOpaque(false);
        btnKhachHang.setPreferredSize(new java.awt.Dimension(250, 50));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        jPanel10.add(btnKhachHang);

        btnNhaCungCap.setBackground(new java.awt.Color(3, 81, 145));
        btnNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/supplier.png"))); // NOI18N
        btnNhaCungCap.setText("NHÀ CUNG CẤP");
        btnNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhaCungCap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhaCungCap.setIconTextGap(20);
        btnNhaCungCap.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnNhaCungCap.setMaximumSize(new java.awt.Dimension(250, 50));
        btnNhaCungCap.setMinimumSize(new java.awt.Dimension(250, 50));
        btnNhaCungCap.setOpaque(false);
        btnNhaCungCap.setPreferredSize(new java.awt.Dimension(250, 50));
        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });
        jPanel10.add(btnNhaCungCap);

        btnTaiKhoan.setBackground(new java.awt.Color(3, 81, 145));
        btnTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/password (1).png"))); // NOI18N
        btnTaiKhoan.setText("TÀI KHOẢN");
        btnTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTaiKhoan.setIconTextGap(20);
        btnTaiKhoan.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnTaiKhoan.setMaximumSize(new java.awt.Dimension(250, 50));
        btnTaiKhoan.setMinimumSize(new java.awt.Dimension(250, 50));
        btnTaiKhoan.setOpaque(false);
        btnTaiKhoan.setPreferredSize(new java.awt.Dimension(250, 50));
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });
        jPanel10.add(btnTaiKhoan);

        btnQuyen.setBackground(new java.awt.Color(3, 81, 145));
        btnQuyen.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnQuyen.setForeground(new java.awt.Color(255, 255, 255));
        btnQuyen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/permission.png"))); // NOI18N
        btnQuyen.setText("QUYỀN");
        btnQuyen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuyen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnQuyen.setIconTextGap(20);
        btnQuyen.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnQuyen.setMaximumSize(new java.awt.Dimension(250, 50));
        btnQuyen.setMinimumSize(new java.awt.Dimension(250, 50));
        btnQuyen.setOpaque(false);
        btnQuyen.setPreferredSize(new java.awt.Dimension(250, 50));
        btnQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuyenActionPerformed(evt);
            }
        });
        jPanel10.add(btnQuyen);

        jButton18.setBackground(new java.awt.Color(3, 81, 145));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/graphic.png"))); // NOI18N
        jButton18.setText("THỐNG KÊ");
        jButton18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton18.setIconTextGap(20);
        jButton18.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jButton18.setMaximumSize(new java.awt.Dimension(250, 50));
        jButton18.setMinimumSize(new java.awt.Dimension(250, 50));
        jButton18.setOpaque(false);
        jButton18.setPreferredSize(new java.awt.Dimension(250, 50));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton18);

        jPanel2.add(jPanel10);

        jScrollPane1.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setMaximumSize(new java.awt.Dimension(1200, 900));
        jPanel4.setMinimumSize(new java.awt.Dimension(1200, 900));
        jPanel4.setLayout(new java.awt.BorderLayout());

        plHienthi.setBackground(new java.awt.Color(255, 255, 255));
        plHienthi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 229), 2, true));
        plHienthi.setMaximumSize(new java.awt.Dimension(1600, 900));
        plHienthi.setMinimumSize(new java.awt.Dimension(1200, 900));
        plHienthi.setLayout(new java.awt.BorderLayout());
        jPanel4.add(plHienthi, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
       new ChuyenPanel(plHienthi, new QuanLyBanHang());
        
        
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
          new ChuyenPanel(plHienthi, new qlsp());
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHSPActionPerformed
        new ChuyenPanel(plHienthi, new qlhsp());
    }//GEN-LAST:event_btnHSPActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
         new ChuyenPanel(plHienthi, new QuanLyHoaDon());
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
         new ChuyenPanel(plHienthi, new QuanLyKhuyenMai());
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyNhanVien());
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
          new ChuyenPanel(plHienthi, new QuanLyKhachHang());
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
         new ChuyenPanel(plHienthi, new QuanLyNhaCungCap());
    }//GEN-LAST:event_btnNhaCungCapActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
         new ChuyenPanel(plHienthi, new QuanLyTaiKhoan());        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuyenActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyQuyen());
    }//GEN-LAST:event_btnQuyenActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        DangNhap dn = new DangNhap();
        new DoiMatKhauForm(dn.txtuser.getText()).setVisible(true);
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc không");
        if (input == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            TestHeThong t = new TestHeThong();
            t.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc không");
        if (input == JOptionPane.YES_OPTION) {
            this.dispose();
            DangNhap dn = new DangNhap();
            dn.setVisible(true);
        } else {
            this.dispose();
            TestHeThong t = new TestHeThong();
            t.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TestHeThong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestHeThong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestHeThong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestHeThong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestHeThong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnHSP;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnNhaCungCap;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnQuyen;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnTaiKhoan;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel plHienthi;
    private rojerusan.RSPanelImage rSPanelImage1;
    // End of variables declaration//GEN-END:variables
}
