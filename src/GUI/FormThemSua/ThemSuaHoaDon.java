/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;

import BUS.HoaDonService;
import BUS.KhuyenMaiService;
import DTO.Model.HoaDon;
import DTO.Model.KhuyenMai;
import GUI.FormChon.ChonKhachHang;
import GUI.FormChon.ChonKhuyenMai;
import GUI.FormChon.ChonNhanVien;
import GUI.Custom.HuyButton;
import GUI.Custom.PriceFormatter;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ThemSuaHoaDon extends javax.swing.JFrame {

    String type;
    HoaDonService qlhd = new HoaDonService();
    KhuyenMaiService qlkm = new KhuyenMaiService();
    HoaDon hdSua;
    KhuyenMai khuyenMai;
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaHoaDon(String _type, String _mahd) {
        initComponents();
        this.type = _type;
        this.setLocationRelativeTo(null);

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm hóa đơn");
            txMaHD.setText(qlhd.getNextID());

            LocalDate ngayLap = java.time.LocalDate.now();
            LocalTime gioLap = java.time.LocalTime.now();

            this.txNgayLap.setText(String.valueOf(ngayLap));
            this.txGioLap.setText(String.valueOf(gioLap));
            txTongTien.setVisible(false);

            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa hóa đơn");

            for (HoaDon hd : qlhd.getDshd()) {
                if (hd.getMaHoaDon().equals(_mahd)) {
                    this.hdSua = hd;
                }
            }

            if (this.hdSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy hóa đơn");
                this.dispose();
            }

            txMaHD.setText(this.hdSua.getMaHoaDon());
            txMaNV.setText(this.hdSua.getMaNhanVien());
            txMaKH.setText(this.hdSua.getMaKhachHang());
            txMaKM.setText(this.hdSua.getMaKhuyenMai());
            txNgayLap.setText(String.valueOf(this.hdSua.getNgayLap()));
            txGioLap.setText(String.valueOf(this.hdSua.getGioLap()));
            txTongTien.setText(String.valueOf(this.hdSua.getTongTien()));

            txMaHD.setEditable(false);

            plButton.add(btnSua);

        }

        plButton.add(btnHuy);

        // mouse listener
        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });

        this.setVisible(true);

    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String mahd = txMaHD.getText();
            String manv = txMaNV.getText();
            String makh = txMaKH.getText();
            String makm = txMaKM.getText();
            LocalDate ngayLap = LocalDate.now();
            LocalTime gioLap = LocalTime.now();
           

            this.txNgayLap.setText(String.valueOf(ngayLap));
            this.txGioLap.setText(String.valueOf(gioLap));
            
            HoaDon hd = new HoaDon(mahd, manv, makh, makm, ngayLap, gioLap, 0);

            if (qlhd.add(hd)) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn " + hd.getMaHoaDon() + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String mahd = txMaHD.getText();
        String manv = txMaNV.getText();
        String makh = txMaKH.getText();
        String makm = txMaKM.getText();
        String ngayLap = txNgayLap.getText();
        String gioLap = txGioLap.getText();

        if (mahd.trim().equals("")) {
            return showErrorTx(txMaHD, "Mã hóa đơn không được để trống");

        } else if (manv.trim().equals("")) {
            return showErrorTx(txMaNV, "Mã nhân viên không được để trống");

        } else if (makh.trim().equals("")) {
            return showErrorTx(txMaKH, "Mã khách hàng không được để trống");

        } else if (makm.trim().equals("")) {
            return showErrorTx(txMaKM, "Mã khuyến mãi không được để trống");

        } else if (ngayLap.trim().equals("")) {
            return showErrorTx(txNgayLap, "Ngày lập không được để trống");

        } else if (gioLap.trim().equals("")) {
            return showErrorTx(txGioLap, "Giờ lập không được để trống");

        } else {
            try {
                LocalDate ngay = java.time.LocalDate.parse(ngayLap);
            } catch (DateTimeParseException e) {
                return showErrorTx(txNgayLap, "Ngày lập không hợp lệ yyyy-mm-dd ( ví dụ: 2018-12-31)");
            }

            try {
                LocalTime gio = java.time.LocalTime.parse(gioLap);
            } catch (DateTimeParseException e) {
                return showErrorTx(txGioLap, "Giờ lập không hợp lệ hh:mm (ví dụ: 18:25)");
            }
        }
        return true;
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String mahd = txMaHD.getText();
            String manv = txMaNV.getText();
            String makh = txMaKH.getText();
            String makm = txMaKM.getText();
            
            LocalDate ngayLap = java.time.LocalDate.parse(txNgayLap.getText());
            LocalTime gioLap = java.time.LocalTime.parse(txGioLap.getText());            
            float tongTienSauKhuyenMai = 0;
            float tongTien = 0;
            
             khuyenMai = qlkm.getKhuyenMai(makm);

            if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongTien) {
                float giaTriKhuyenMai = tongTien * khuyenMai.getPhanTramKM() / 100;
                 tongTienSauKhuyenMai = tongTien - giaTriKhuyenMai;               
                txTongTien.setText(String.valueOf(tongTienSauKhuyenMai));
            } else {
                txTongTien.setText(String.valueOf(tongTien));
            }               
            
            
            if (qlhd.update(mahd, manv, makh, makm, ngayLap, gioLap, tongTienSauKhuyenMai)) {
                JOptionPane.showMessageDialog(this, "Sửa " + mahd + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plButton = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txMaHD = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txMaNV = new javax.swing.JTextField();
        btnChonNV = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txMaKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txMaKM = new javax.swing.JTextField();
        btnChonKM = new javax.swing.JButton();
        txNgayLap = new javax.swing.JTextField();
        txGioLap = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(568, 503));
        setMinimumSize(new java.awt.Dimension(568, 503));
        setPreferredSize(new java.awt.Dimension(568, 503));

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(935, 80));
        getContentPane().add(plButton, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        txMaHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaHD.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txMaHD);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaNV.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel1.add(txMaNV);

        btnChonNV.setBackground(new java.awt.Color(3, 81, 145));
        btnChonNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/employee_icon.png"))); // NOI18N
        btnChonNV.setMaximumSize(new java.awt.Dimension(45, 45));
        btnChonNV.setMinimumSize(new java.awt.Dimension(45, 45));
        btnChonNV.setPreferredSize(new java.awt.Dimension(45, 45));
        btnChonNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNVActionPerformed(evt);
            }
        });
        jPanel1.add(btnChonNV);

        jPanel2.add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaKH.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(txMaKH);

        btnChonKH.setBackground(new java.awt.Color(3, 81, 145));
        btnChonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/customer_icon.png"))); // NOI18N
        btnChonKH.setMaximumSize(new java.awt.Dimension(45, 45));
        btnChonKH.setMinimumSize(new java.awt.Dimension(45, 45));
        btnChonKH.setPreferredSize(new java.awt.Dimension(45, 45));
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });
        jPanel3.add(btnChonKH);

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaKM.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(txMaKM);

        btnChonKM.setBackground(new java.awt.Color(3, 81, 145));
        btnChonKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/gift_icon.png"))); // NOI18N
        btnChonKM.setMaximumSize(new java.awt.Dimension(45, 45));
        btnChonKM.setMinimumSize(new java.awt.Dimension(45, 45));
        btnChonKM.setPreferredSize(new java.awt.Dimension(45, 45));
        btnChonKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKMActionPerformed(evt);
            }
        });
        jPanel4.add(btnChonKM);

        jPanel2.add(jPanel4);

        txNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txNgayLap.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txNgayLap);

        txGioLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txGioLap.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txGioLap);

        txTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTongTien.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txTongTien);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNVActionPerformed
        ChonNhanVien cnv = new ChonNhanVien(txMaNV);
    }//GEN-LAST:event_btnChonNVActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        ChonKhachHang ckh = new ChonKhachHang(txMaKH);
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnChonKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKMActionPerformed
        ChonKhuyenMai ckm = new ChonKhuyenMai(txMaKM);
    }//GEN-LAST:event_btnChonKMActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSuaHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSuaHoaDon("Thêm", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonKM;
    private javax.swing.JButton btnChonNV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txGioLap;
    private javax.swing.JTextField txMaHD;
    private javax.swing.JTextField txMaKH;
    private javax.swing.JTextField txMaKM;
    private javax.swing.JTextField txMaNV;
    private javax.swing.JTextField txNgayLap;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
