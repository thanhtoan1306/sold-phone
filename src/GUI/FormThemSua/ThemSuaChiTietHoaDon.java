/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;

import BUS.ChiTietHoaDonService;
import DTO.Model.ChiTietHoaDon;
import DTO.Model.HoaDon;
import GUI.FormChon.ChonNhanVien;
import GUI.FormChon.ChonSanPham;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ThemSuaChiTietHoaDon extends javax.swing.JFrame {

    ChiTietHoaDonService qlcthd = new ChiTietHoaDonService();
    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

    String type, mahd, masp;
    int soLuongMax;
    ChiTietHoaDon cthdSua = new ChiTietHoaDon();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaChiTietHoaDon(String _type, String _mahd, String _masp) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.type = _type;
        this.mahd = _mahd;
        this.masp = _masp;
        txMaHD.setEditable(false);

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm chi tiết hóa đơn " + this.mahd);
            txMaHD.setText(this.mahd);

            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa chi tiết " + this.masp + " trên hóa đơn" + this.mahd);

            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(this.mahd) && cthd.getMaSanPham().equals(this.masp)) {
                    this.cthdSua = cthd;
                }
            }

            if (cthdSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy sản phẩm phù hợp");
                this.dispose();
            }
            txMaHD.setText(this.mahd);
            txMaHD.setEditable(false);

            txSLuong.setText(String.valueOf(cthdSua.getSoLuong()));
            txMaSP.setText(this.masp);
            btnChonSP.setVisible(true);

            plButton.add(btnSua);
        }

        plButton.add(btnHuy);

        // mouse listener
        btnThem.addActionListener((ae) -> {
            btnThemChiTietHoaDonMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaChiTietHoaDonMouseClicked();
        });

        this.setVisible(true);

    }

    private void btnSuaChiTietHoaDonMouseClicked() {
        if (checkEmpty()) {
            String mahd = txMaHD.getText();
            String masp = txMaSP.getText();
            //String dongia = txGia.getText();
            //String sluong = txSLuong.getText();
     

           float dongia = Float.parseFloat(txGia.getText())/1000000;
            int sL = Integer.parseInt(txSLuong.getText());

            if (qlcthd.update(mahd, masp, sL, dongia)) {

                JOptionPane.showMessageDialog(this, "Sửa chi tiết hóa đơn" + mahd + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnThemChiTietHoaDonMouseClicked() {
        if (checkEmpty()) {
            String maspThem = txMaSP.getText();
            //String gia = txGia.getText().replace('.000.000 đ', '.0');

            double dongia = Double.parseDouble(txGia.getText()) / 1000000;
            int soluong = Integer.parseInt(txSLuong.getText());

            if (soluong > soLuongMax) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + soLuongMax + ")");
                txSLuong.setText(String.valueOf(soLuongMax));
                return;

            } else if (soluong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ");
                txSLuong.setText(String.valueOf(soLuongMax));
                return;
            }

            ChiTietHoaDon cthd = new ChiTietHoaDon(mahd, maspThem, soluong, dongia);

//            for (ChiTietHoaDon ct : dscthd) {
//                qlcthd.add(ct);
//            }
            if (qlcthd.add(cthd) != null) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết cho " + mahd + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnChonSanPhamMouseClicked() {
        ChonSanPham csp = new ChonSanPham(txMaSP, null, null, txGia, txSLuong); // truyền vào textfield

        // lưu lại số lượng max từ txSoLuong
        csp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                soLuongMax = Integer.parseInt(txSLuong.getText());
                if (soLuongMax == 0) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm đã hết hàng!");
                }
            }
        });
    }

    private Boolean checkEmpty() {
        String mssp = txMaSP.getText();
        String mahd = txMaHD.getText();
        String dongia = txGia.getText();
        String soluong = txSLuong.getText();

        if (mssp.trim().equals("")) {
            return showErrorTx(txMaSP, "Mã sp không được để trống");

        } else if (mahd.trim().equals("")) {
            return showErrorTx(txMaHD, "Mã loại không được để trống");

        } else if (dongia.trim().equals("")) {
            return showErrorTx(txGia, "Đơn giá không được để trống");

        } else if (soluong.trim().equals("")) {
            return showErrorTx(txSLuong, "Số lượng không được để trống");

        } else {
            try {
                double dg = Double.parseDouble(dongia);
            } catch (NumberFormatException e) {

                return showErrorTx(txGia, "Đơn giá không hợp lệ (phải là số thực), Nhập theo định dạng ví dụ 7.000.000 đ -> 7.0");
            }

            try {
                int sl = Integer.parseInt(soluong);
                if (sl < 0) {
                    return showErrorTx(txSLuong, "Số lượng không hợp lệ (phải là số duơng)");
                }
            } catch (NumberFormatException e) {
                return showErrorTx(txSLuong, "Số lượng không hợp lệ (phải là số nguyên)");
            }
        }
        return true;
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
        jPanel3 = new javax.swing.JPanel();
        txMaSP = new javax.swing.JTextField();
        btnChonSP = new javax.swing.JButton();
        txGia = new javax.swing.JTextField();
        txSLuong = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setMaximumSize(new java.awt.Dimension(600, 100));
        plButton.setMinimumSize(new java.awt.Dimension(600, 100));
        plButton.setName(""); // NOI18N
        plButton.setPreferredSize(new java.awt.Dimension(600, 100));
        getContentPane().add(plButton, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(600, 300));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 300));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        txMaHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaHD.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txMaHD);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 90));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        txMaSP.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.add(txMaSP);

        btnChonSP.setBackground(new java.awt.Color(3, 81, 145));
        btnChonSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/smartphone_icon.png"))); // NOI18N
        btnChonSP.setMaximumSize(new java.awt.Dimension(45, 45));
        btnChonSP.setMinimumSize(new java.awt.Dimension(45, 45));
        btnChonSP.setPreferredSize(new java.awt.Dimension(45, 45));
        btnChonSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonSPActionPerformed(evt);
            }
        });
        jPanel3.add(btnChonSP);

        jPanel2.add(jPanel3);

        txGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá triệu (vd:7000000)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txGia.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txGia);

        txSLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txSLuong.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel2.add(txSLuong);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonSPActionPerformed
        btnChonSanPhamMouseClicked();
    }//GEN-LAST:event_btnChonSPActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaChiTietHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ThemSuaChiTietHoaDon().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonSP;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txGia;
    private javax.swing.JTextField txMaHD;
    private javax.swing.JTextField txMaSP;
    private javax.swing.JTextField txSLuong;
    // End of variables declaration//GEN-END:variables
}
