/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;


import BUS.HoaDonService;
import BUS.PhieuNhapService;
import DTO.Model.PhieuNhap;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import GUI.FormChon.ChonNhaCungCap;
import GUI.FormChon.ChonNhanVien;
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
public class ThemSuaPhieuNhap extends javax.swing.JFrame {

    String type;
    PhieuNhapService qlpn = new PhieuNhapService();
    PhieuNhap pnSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaPhieuNhap(String _type, String _mapn) {
        initComponents();
        this.type = _type;
        this.setLocationRelativeTo(null);

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm phiếu nhập");
            txMaPN.setText(qlpn.getNextID());
       
            LocalDate ngayNhap = java.time.LocalDate.now();
            LocalTime gioNhap = java.time.LocalTime.now();

            this.txNgayNhap.setText(String.valueOf(ngayNhap));
            this.txGioNhap.setText(String.valueOf(gioNhap));
            txTongTien.setVisible(false);

            
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa phiếu nhập");
            for (PhieuNhap pn : qlpn.getDspn()) {
                if (pn.getMaPN().equals(_mapn)) {
                    this.pnSua = pn;
                }
            }
            if (this.pnSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy phiếu nhập");
                this.dispose();
            }

            txMaPN.setText(this.pnSua.getMaPN());
            txMaNV.setText(this.pnSua.getMaNV());
            //txMaKH.setText(this.hdSua.getMaKhachHang());
            //txMaKM.setText(this.hdSua.getMaKhuyenMai());
            txMaNCC.setText(this.pnSua.getMaNCC());
            txNgayNhap.setText(String.valueOf(this.pnSua.getNgayNhap()));
            txGioNhap.setText(String.valueOf(this.pnSua.getGioNhap()));
            txTongTien.setText(String.valueOf(this.pnSua.getTongTien()));

            txMaPN.setEditable(false);

            
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
            String mapn = txMaPN.getText();
            String manv = txMaNV.getText();
            String ncc = txMaNCC.getText();
            //String  = txNgayNhap.getText();
            LocalDate ngaynhap = LocalDate.now();
            LocalTime gionhap= LocalTime.now();
            float tongTien = 0;

            this.txNgayNhap.setText(String.valueOf(ngaynhap));
            this.txGioNhap.setText(String.valueOf(gionhap));
            this.txTongTien.setText(String.valueOf(tongTien));
            PhieuNhap pn = new PhieuNhap(mapn, ncc, manv, ngaynhap, gionhap, tongTien);

            if (qlpn.add(pn)) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập " + pn.getMaPN() + " thành công!");
                this.dispose();
            }
        }
    }
    
    private Boolean checkEmpty() {
        String mapn = txMaPN.getText();
        String manv = txMaNV.getText();
        String ncc = txMaNCC.getText();
        String ngaynhap = txNgayNhap.getText();
        String gionhap = txGioNhap.getText();

        if (mapn.trim().equals("")) {
            return showErrorTx(txMaPN, "Mã phiếu nhập không được để trống");

        } else if (manv.trim().equals("")) {
            return showErrorTx(txMaNV, "Mã nhân viên không được để trống");

        } else if (ncc.trim().equals("")) {
            return showErrorTx(txMaNCC, "Mã nhà cung cấp không được để trống");

        } else if (ngaynhap.trim().equals("")) {
            return showErrorTx(txNgayNhap, "Ngày lập không được để trống");

        } else if (gionhap.trim().equals("")) {
            return showErrorTx(txGioNhap, "Giờ lập không được để trống");

        } else {
            try {
                LocalDate ngay = java.time.LocalDate.parse(ngaynhap);
            } catch (DateTimeParseException e) {
                return showErrorTx(txNgayNhap, "Ngày lập không hợp lệ yyyy-mm-dd ( ví dụ: 2018-12-31)");
            }

            try {
                LocalTime gio = java.time.LocalTime.parse(gionhap);
            } catch (DateTimeParseException e) {
                return showErrorTx(txGioNhap, "Giờ lập không hợp lệ hh:mm (ví dụ: 18:25)");
            }
        }
        return true;
    }
    
    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
           String mapn = txMaPN.getText();
           String manv = txMaNV.getText();
           String ncc = txMaNCC.getText();
            
            LocalDate ngaynhap = java.time.LocalDate.parse(txNgayNhap.getText());
            LocalTime gionhap = java.time.LocalTime.parse(txGioNhap.getText());            
           
            float tongTien = 0;
                 
            
            
            if (qlpn.update(mapn, ncc, manv, ngaynhap, gionhap, tongTien)) {
                JOptionPane.showMessageDialog(this, "Sửa " + mapn + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plButton = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txMaPN = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txMaNCC = new javax.swing.JTextField();
        btnChonNV1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txMaNV = new javax.swing.JTextField();
        btnChonNV = new javax.swing.JButton();
        txNgayNhap = new javax.swing.JTextField();
        txGioNhap = new javax.swing.JTextField();
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

        txMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaPN.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txMaPN);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaNCC.setMaximumSize(new java.awt.Dimension(200, 50));
        txMaNCC.setMinimumSize(new java.awt.Dimension(200, 50));
        txMaNCC.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(txMaNCC);

        btnChonNV1.setBackground(new java.awt.Color(3, 81, 145));
        btnChonNV1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/supplier_icon.png"))); // NOI18N
        btnChonNV1.setMaximumSize(new java.awt.Dimension(45, 45));
        btnChonNV1.setMinimumSize(new java.awt.Dimension(45, 45));
        btnChonNV1.setPreferredSize(new java.awt.Dimension(45, 45));
        btnChonNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNV1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnChonNV1);

        jPanel2.add(jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txMaNV.setMaximumSize(new java.awt.Dimension(200, 50));
        txMaNV.setMinimumSize(new java.awt.Dimension(200, 50));
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

        txNgayNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txNgayNhap.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txNgayNhap);

        txGioNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txGioNhap.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txGioNhap);

        txTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTongTien.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txTongTien);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNVActionPerformed
        ChonNhanVien cnv = new ChonNhanVien(txMaNV);
    }//GEN-LAST:event_btnChonNVActionPerformed

    private void btnChonNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNV1ActionPerformed
        ChonNhaCungCap cncc = new ChonNhaCungCap(txMaNCC);
    }//GEN-LAST:event_btnChonNV1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSuaPhieuNhap("Thêm", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNV;
    private javax.swing.JButton btnChonNV1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txGioNhap;
    private javax.swing.JTextField txMaNCC;
    private javax.swing.JTextField txMaNV;
    private javax.swing.JTextField txMaPN;
    private javax.swing.JTextField txNgayNhap;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
