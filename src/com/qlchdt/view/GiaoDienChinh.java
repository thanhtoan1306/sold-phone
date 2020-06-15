/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;

import com.qlchdt.service.ChuyenPanel;
import com.qlchdt.view.QuanLy.QLPhieuNhap;
import com.qlchdt.view.QuanLy.QLTaiKhoan;
import com.qlchdt.view.QuanLy.QuanLyBanHang;
import com.qlchdt.view.QuanLy.QuanLyHoaDon;
import com.qlchdt.view.QuanLy.QuanLyKhachHang;
import com.qlchdt.view.QuanLy.QuanLyKhuyenMai;
import com.qlchdt.view.QuanLy.QuanLyNhaCungCap;
import com.qlchdt.view.QuanLy.QuanLyNhanVien;
import com.qlchdt.view.QuanLy.QuanLyNhapHang;
import com.qlchdt.view.QuanLy.QuanLyQuyen;
import com.qlchdt.view.QuanLy.QuanLyTaiKhoan;
import com.qlchdt.view.QuanLy.QuanLyHangSanPham;
import com.qlchdt.view.QuanLy.QuanLySanPham;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class GiaoDienChinh extends javax.swing.JFrame {

    QuanLyBanHang fbh = new QuanLyBanHang();

    public GiaoDienChinh() {
        initComponents();
        this.setLocationRelativeTo(null);

        String chitietquyen = DangNhap.quyenLogin.getChiTietQuyen();
        
        btnBanHang.setVisible(false);
        if (chitietquyen.contains("BanHang")) {
            btnBanHang.setVisible(true);
        }
        
        btnNhapHang.setVisible(false);
        if (chitietquyen.contains("NhapHang")) {
            btnNhapHang.setVisible(true);
        }
        
        btnSanPham.setVisible(false);
        if (chitietquyen.contains("SanPham")) {
            btnSanPham.setVisible(true);
        }
        
        btnHSP.setVisible(false);
        if (chitietquyen.contains("HangSanPham")) {
            btnHSP.setVisible(true);
        }
        
        btnHoaDon.setVisible(false);
        if (chitietquyen.contains("HoaDon")) {
            btnHoaDon.setVisible(true);
        }
        
        btnPhieuNhap.setVisible(false);
        if (chitietquyen.contains("PhieuNhap")) {
            btnPhieuNhap.setVisible(true);
        }
        
        btnKhuyenMai.setVisible(false);
        if (chitietquyen.contains("KhuyenMai")) {
            btnKhuyenMai.setVisible(true);
        }
        
        btnNhanVien.setVisible(false);
        if (chitietquyen.contains("NhanVien")) {
            btnNhanVien.setVisible(true);
        }
        
        btnKhachHang.setVisible(false);
        if (chitietquyen.contains("KhachHang")) {
            btnKhachHang.setVisible(true);
        }
        
        btnNhaCungCap.setVisible(false);
        if (chitietquyen.contains("NCC")) {
            btnNhaCungCap.setVisible(true);
        }
        
        btnTaiKhoan.setVisible(false);
        if (chitietquyen.contains("TaiKhoan")) {
            btnTaiKhoan.setVisible(true);
        }
        
        btnQuyen.setVisible(false);
        if (chitietquyen.contains("Quyen")) {
            btnQuyen.setVisible(true);
        }
        
        btnThongKe.setVisible(true);    // 
        
        /*
        if (chitietquyen.contains("qlBanHang xemSanPham xemLoaiSanPham xemHoaDon xemKhuyenMai xemKhachHang xemNCC")) { // Nhân viên bán hàng
            btnNhapHang.setVisible(false);
            btnNhanVien.setVisible(false);
            btnPhieuNhap.setVisible(false);
            btnTaiKhoan.setVisible(false);
            btnQuyen.setVisible(false);
            btnThongKe.setVisible(false);
        } else if (chitietquyen.contains("qlNhapHang xemSanPham xemLoaiSanPham xemNhanVien qlPhieuNhap qlNCC")) {   //Nhân viên nhập hàng
            btnBanHang.setVisible(false);
            btnHoaDon.setVisible(false);
            btnKhuyenMai.setVisible(false);
            btnKhachHang.setVisible(false);
            btnTaiKhoan.setVisible(false);
            btnQuyen.setVisible(false);
            btnThongKe.setVisible(false);

        } else if (chitietquyen.contains("xemSanPham xemLoaiSanPham xemHoaDon qlNhanVien qlKhachHang xemPhieuNhap xemNCC qlTaiKhoan qlQuyen"))  { //Quản lý
            btnBanHang.setVisible(false);
            btnNhapHang.setVisible(false);
            btnKhuyenMai.setVisible(false);
            btnThongKe.setVisible(false);
        } else {
            //Admin
            btnBanHang.setVisible(true);
            btnNhapHang.setVisible(true);
            btnSanPham.setVisible(true);
            btnHSP.setVisible(true);
            btnHoaDon.setVisible(true);
            btnPhieuNhap.setVisible(true);
            btnKhuyenMai.setVisible(true);
            btnNhanVien.setVisible(true);
            btnKhachHang.setVisible(true);
            btnNhaCungCap.setVisible(true);
            btnTaiKhoan.setVisible(true);
            btnQuyen.setVisible(true);
            btnThongKe.setVisible(true);
        }
        */
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plTitle = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        plButton = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnDoiMatKhau = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnThoat = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        plMenu = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        btnNhapHang = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnHSP = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnPhieuNhap = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhaCungCap = new javax.swing.JButton();
        btnTaiKhoan = new javax.swing.JButton();
        btnQuyen = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        plMain = new javax.swing.JPanel();
        plHienthi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hệ thống");
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1400, 900));
        setPreferredSize(new java.awt.Dimension(1600, 900));

        plTitle.setBackground(new java.awt.Color(2, 155, 212));
        plTitle.setMaximumSize(new java.awt.Dimension(1400, 125));
        plTitle.setMinimumSize(new java.awt.Dimension(1400, 125));
        plTitle.setPreferredSize(new java.awt.Dimension(1400, 125));
        plTitle.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(2, 155, 212));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 2));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icon_app.png"))); // NOI18N
        rSPanelImage1.setMaximumSize(new java.awt.Dimension(120, 120));
        rSPanelImage1.setMinimumSize(new java.awt.Dimension(120, 120));
        rSPanelImage1.setPreferredSize(new java.awt.Dimension(120, 120));
        rSPanelImage1.setLayout(new java.awt.BorderLayout());
        jPanel6.add(rSPanelImage1);

        plTitle.add(jPanel6, java.awt.BorderLayout.LINE_START);

        plButton.setBackground(new java.awt.Color(2, 155, 212));

        jPanel3.setBackground(new java.awt.Color(2, 155, 212));
        jPanel3.setMaximumSize(new java.awt.Dimension(350, 130));
        jPanel3.setMinimumSize(new java.awt.Dimension(350, 130));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 130));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 5, 20));

        jPanel11.setBackground(new java.awt.Color(255, 51, 51));
        jPanel11.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 80));

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
        jPanel11.add(btnDoiMatKhau);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đổi mật khẩu");
        jPanel11.add(jLabel2);

        jPanel3.add(jPanel11);

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 80));

        btnDangXuat.setBackground(new java.awt.Color(3, 81, 145));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/logout.png"))); // NOI18N
        btnDangXuat.setToolTipText("");
        btnDangXuat.setMaximumSize(new java.awt.Dimension(50, 50));
        btnDangXuat.setMinimumSize(new java.awt.Dimension(50, 50));
        btnDangXuat.setOpaque(false);
        btnDangXuat.setPreferredSize(new java.awt.Dimension(50, 50));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jPanel5.add(btnDangXuat);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Đăng xuất");
        jPanel5.add(jLabel3);

        jPanel3.add(jPanel5);

        jPanel7.setMaximumSize(new java.awt.Dimension(100, 80));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 80));

        btnThoat.setBackground(new java.awt.Color(3, 81, 145));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/shutdown.png"))); // NOI18N
        btnThoat.setToolTipText("");
        btnThoat.setMaximumSize(new java.awt.Dimension(50, 50));
        btnThoat.setMinimumSize(new java.awt.Dimension(50, 50));
        btnThoat.setOpaque(false);
        btnThoat.setPreferredSize(new java.awt.Dimension(50, 50));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel7.add(btnThoat);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thoát");
        jPanel7.add(jLabel5);

        jPanel3.add(jPanel7);

        plButton.add(jPanel3);

        plTitle.add(plButton, java.awt.BorderLayout.LINE_END);

        getContentPane().add(plTitle, java.awt.BorderLayout.PAGE_START);

        plMenu.setBackground(new java.awt.Color(2, 155, 212));
        plMenu.setMaximumSize(new java.awt.Dimension(250, 775));
        plMenu.setMinimumSize(new java.awt.Dimension(250, 775));
        plMenu.setPreferredSize(new java.awt.Dimension(250, 775));
        plMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, -6));

        jPanel10.setBackground(new java.awt.Color(2, 155, 212));
        jPanel10.setMaximumSize(new java.awt.Dimension(250, 775));
        jPanel10.setMinimumSize(new java.awt.Dimension(250, 775));
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(250, 775));

        btnBanHang.setBackground(new java.awt.Color(3, 81, 145));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/ecommerce.png"))); // NOI18N
        btnBanHang.setText("BÁN HÀNG");
        btnBanHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBanHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBanHang.setIconTextGap(20);
        btnBanHang.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnBanHang.setMaximumSize(new java.awt.Dimension(240, 50));
        btnBanHang.setMinimumSize(new java.awt.Dimension(240, 50));
        btnBanHang.setOpaque(false);
        btnBanHang.setPreferredSize(new java.awt.Dimension(240, 50));
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });
        jPanel10.add(btnBanHang);

        btnNhapHang.setBackground(new java.awt.Color(3, 81, 145));
        btnNhapHang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/logistics.png"))); // NOI18N
        btnNhapHang.setText("NHẬP HÀNG");
        btnNhapHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhapHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNhapHang.setIconTextGap(20);
        btnNhapHang.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnNhapHang.setMaximumSize(new java.awt.Dimension(240, 50));
        btnNhapHang.setMinimumSize(new java.awt.Dimension(240, 50));
        btnNhapHang.setOpaque(false);
        btnNhapHang.setPreferredSize(new java.awt.Dimension(240, 50));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        jPanel10.add(btnNhapHang);

        btnSanPham.setBackground(new java.awt.Color(3, 81, 145));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/smartphone.png"))); // NOI18N
        btnSanPham.setText("SẢN PHẨM");
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSanPham.setIconTextGap(20);
        btnSanPham.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnSanPham.setMaximumSize(new java.awt.Dimension(240, 50));
        btnSanPham.setMinimumSize(new java.awt.Dimension(240, 50));
        btnSanPham.setOpaque(false);
        btnSanPham.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnHSP.setMaximumSize(new java.awt.Dimension(240, 50));
        btnHSP.setMinimumSize(new java.awt.Dimension(240, 50));
        btnHSP.setOpaque(false);
        btnHSP.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnHoaDon.setMaximumSize(new java.awt.Dimension(240, 50));
        btnHoaDon.setMinimumSize(new java.awt.Dimension(240, 50));
        btnHoaDon.setOpaque(false);
        btnHoaDon.setPreferredSize(new java.awt.Dimension(240, 50));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        jPanel10.add(btnHoaDon);

        btnPhieuNhap.setBackground(new java.awt.Color(3, 81, 145));
        btnPhieuNhap.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/invoice.png"))); // NOI18N
        btnPhieuNhap.setText("PHIẾU NHẬP");
        btnPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhieuNhap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPhieuNhap.setIconTextGap(20);
        btnPhieuNhap.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnPhieuNhap.setMaximumSize(new java.awt.Dimension(240, 50));
        btnPhieuNhap.setMinimumSize(new java.awt.Dimension(240, 50));
        btnPhieuNhap.setOpaque(false);
        btnPhieuNhap.setPreferredSize(new java.awt.Dimension(240, 50));
        btnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapActionPerformed(evt);
            }
        });
        jPanel10.add(btnPhieuNhap);

        btnKhuyenMai.setBackground(new java.awt.Color(3, 81, 145));
        btnKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/sale.png"))); // NOI18N
        btnKhuyenMai.setText("KHUYẾN MÃI");
        btnKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhuyenMai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKhuyenMai.setIconTextGap(20);
        btnKhuyenMai.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnKhuyenMai.setMaximumSize(new java.awt.Dimension(240, 50));
        btnKhuyenMai.setMinimumSize(new java.awt.Dimension(240, 50));
        btnKhuyenMai.setOpaque(false);
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnNhanVien.setMaximumSize(new java.awt.Dimension(240, 50));
        btnNhanVien.setMinimumSize(new java.awt.Dimension(240, 50));
        btnNhanVien.setOpaque(false);
        btnNhanVien.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnKhachHang.setMaximumSize(new java.awt.Dimension(240, 50));
        btnKhachHang.setMinimumSize(new java.awt.Dimension(240, 50));
        btnKhachHang.setOpaque(false);
        btnKhachHang.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnNhaCungCap.setMaximumSize(new java.awt.Dimension(240, 50));
        btnNhaCungCap.setMinimumSize(new java.awt.Dimension(240, 50));
        btnNhaCungCap.setOpaque(false);
        btnNhaCungCap.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnTaiKhoan.setMaximumSize(new java.awt.Dimension(240, 50));
        btnTaiKhoan.setMinimumSize(new java.awt.Dimension(240, 50));
        btnTaiKhoan.setOpaque(false);
        btnTaiKhoan.setPreferredSize(new java.awt.Dimension(240, 50));
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
        btnQuyen.setMaximumSize(new java.awt.Dimension(240, 50));
        btnQuyen.setMinimumSize(new java.awt.Dimension(240, 50));
        btnQuyen.setOpaque(false);
        btnQuyen.setPreferredSize(new java.awt.Dimension(240, 50));
        btnQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuyenActionPerformed(evt);
            }
        });
        jPanel10.add(btnQuyen);

        btnThongKe.setBackground(new java.awt.Color(3, 81, 145));
        btnThongKe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/graphic.png"))); // NOI18N
        btnThongKe.setText("THỐNG KÊ");
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThongKe.setIconTextGap(20);
        btnThongKe.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnThongKe.setMaximumSize(new java.awt.Dimension(240, 50));
        btnThongKe.setMinimumSize(new java.awt.Dimension(240, 50));
        btnThongKe.setOpaque(false);
        btnThongKe.setPreferredSize(new java.awt.Dimension(240, 50));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel10.add(btnThongKe);

        plMenu.add(jPanel10);

        getContentPane().add(plMenu, java.awt.BorderLayout.LINE_START);

        plMain.setBackground(new java.awt.Color(0, 0, 0));
        plMain.setMaximumSize(new java.awt.Dimension(1200, 900));
        plMain.setMinimumSize(new java.awt.Dimension(1200, 900));
        plMain.setLayout(new java.awt.BorderLayout());

        plHienthi.setBackground(new java.awt.Color(255, 255, 255));
        plHienthi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 229), 2, true));
        plHienthi.setMaximumSize(new java.awt.Dimension(1200, 775));
        plHienthi.setMinimumSize(new java.awt.Dimension(1200, 775));
        plHienthi.setPreferredSize(new java.awt.Dimension(1200, 775));
        plHienthi.setLayout(new java.awt.BorderLayout());
        plMain.add(plHienthi, java.awt.BorderLayout.CENTER);

        getContentPane().add(plMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyBanHang());
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyNhapHang());
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        new ChuyenPanel(plHienthi, new QuanLySanPham());
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHSPActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyHangSanPham());
    }//GEN-LAST:event_btnHSPActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyHoaDon());
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapActionPerformed
         new ChuyenPanel(plHienthi, new QLPhieuNhap());// TODO add your handling code here:
    }//GEN-LAST:event_btnPhieuNhapActionPerformed

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
      //  new ChuyenPanel(plHienthi, new QuanLyTaiKhoan()); // code cũ
        new ChuyenPanel(plHienthi, new QLTaiKhoan());// TODO add your handling code here:
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuyenActionPerformed
        new ChuyenPanel(plHienthi, new QuanLyQuyen());
    }//GEN-LAST:event_btnQuyenActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        new ChuyenPanel(plHienthi, new ThongKe());
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        DangNhap dn = new DangNhap();
        new DoiMatKhauForm(dn.txtUser.getText()).setVisible(true);
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc không ? ", "Thoát", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            //GiaoDienChinh t = new GiaoDienChinh();
            //t.setVisible(true);
            //this.dispose();
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc không ? ", "Đăng xuất ", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            this.dispose();
            DangNhap dn = new DangNhap();
            dn.setVisible(true);
        } else {
          //  this.dispose();
           // GiaoDienChinh t = new GiaoDienChinh();
           // t.setVisible(true);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnHSP;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnNhaCungCap;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnPhieuNhap;
    private javax.swing.JButton btnQuyen;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnTaiKhoan;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel plButton;
    private javax.swing.JPanel plHienthi;
    private javax.swing.JPanel plMain;
    private javax.swing.JPanel plMenu;
    private javax.swing.JPanel plTitle;
    private rojerusan.RSPanelImage rSPanelImage1;
    // End of variables declaration//GEN-END:variables
}
