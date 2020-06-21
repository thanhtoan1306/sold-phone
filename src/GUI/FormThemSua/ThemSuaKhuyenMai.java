/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import BUS.KhuyenMaiService;
import DTO.Model.KhuyenMai;
import GUI.Custom.DateButton;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ThemSuaKhuyenMai extends javax.swing.JFrame {

    String type;
    KhuyenMaiService qlkm = new KhuyenMaiService();
    KhuyenMai kmSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    
    DatePicker dPickerNgayBD;
    DatePicker dPickerNgayKT;
    
    public ThemSuaKhuyenMai(String _type, String _makm) {
        initComponents();
        this.type = _type;
        this.setLocationRelativeTo(null);
        
        // date picker
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPickerNgayBD = new DatePicker(pickerSettings);
        dPickerNgayBD.setBackground(Color.white);
        dPickerNgayBD.setDateToToday();
        DateButton db = new DateButton(dPickerNgayBD);

        DatePickerSettings pickerSettings2 = new DatePickerSettings();
        pickerSettings2.setVisibleDateTextField(false);
        dPickerNgayKT = new DatePicker(pickerSettings2);
        dPickerNgayKT.setBackground(Color.white);
        DateButton dk = new DateButton(dPickerNgayKT);


        plNgayBD.add(dPickerNgayBD);
        plNgayKT.add(dPickerNgayKT);
        
        
      // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm khuyến mãi");
            txMaKM.setText(qlkm.getNextID());
        
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa khuyến mãi");
            for (KhuyenMai km : qlkm.getDskm()) {
                if (km.getMaKM().equals(_makm)) {
                    this.kmSua = km;
                }
            }
            if (this.kmSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy khuyến mãi");
                this.dispose();
            }

            txMaKM.setText(this.kmSua.getMaKM());
            txTenKM.setText(this.kmSua.getTenKM());
            txDieuKienKhuyenMai.setText(String.valueOf(this.kmSua.getDieuKienKM()));
            txPhanTramKhuyenMai.setText(String.valueOf(this.kmSua.getPhanTramKM()));
            txNgayBD.setText(this.kmSua.getNgayBD().toString());
            dPickerNgayBD.setDate(this.kmSua.getNgayBD());
            txNgayKT.setText(this.kmSua.getNgayKT().toString());
            dPickerNgayKT.setDate(this.kmSua.getNgayKT());

            txMaKM.setEditable(false);

            
            plButton.add(btnSua);
        }

     
        plButton.add(btnHuy);
        this.setVisible(true);
        
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
        dPickerNgayBD.addDateChangeListener((dce) -> {
            txNgayBD.setText(dPickerNgayBD.getDateStringOrEmptyString());
        });
        dPickerNgayKT.addDateChangeListener((dce) -> {
            txNgayKT.setText(dPickerNgayKT.getDateStringOrEmptyString());
        });
           

    }
    
    
    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String makm = txMaKM.getText();
            String tenkm = txTenKM.getText();
            float dieukien = Float.parseFloat(txDieuKienKhuyenMai.getText());
            float phantram = Float.parseFloat(txPhanTramKhuyenMai.getText());
            LocalDate ngaybd = LocalDate.parse(txNgayBD.getText());
            LocalDate ngaykt = LocalDate.parse(txNgayKT.getText());

            if (qlkm.add(makm, tenkm, dieukien, phantram, ngaybd, ngaykt)) {
                JOptionPane.showMessageDialog(this, "Thêm " + tenkm + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String makm = txMaKM.getText();
            String tenkm = txTenKM.getText();
            float dieukien = Float.parseFloat(txDieuKienKhuyenMai.getText());
            float phantram = Float.parseFloat(txPhanTramKhuyenMai.getText());
            LocalDate ngaybd = LocalDate.parse(txNgayBD.getText());
            LocalDate ngaykt = LocalDate.parse(txNgayKT.getText());

            if (qlkm.update(makm, tenkm, dieukien, phantram, ngaybd, ngaykt)) {
                JOptionPane.showMessageDialog(this, "Sửa " + makm + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String makm = txMaKM.getText();
        String tenkm = txTenKM.getText();
        String dieukien = txDieuKienKhuyenMai.getText();
        String phantram = txPhanTramKhuyenMai.getText();
        String ngaybd = txNgayBD.getText();
        String ngaykt = txNgayKT.getText();

        if (makm.trim().equals("")) {
            return showErrorTx(txMaKM, "Mã khuyến mãi không được để trống");

        } else if (tenkm.trim().equals("")) {
            return showErrorTx(txTenKM, "Tên khuyến mãi không được để trống");

        } else if (dieukien.trim().equals("")) {
            return showErrorTx(txTenKM, "Điều kiện khuyến mãi không được để trống");

        } else if (phantram.trim().equals("")) {
            return showErrorTx(txTenKM, "Phần trăm khuyến mãi không được để trống");

        } else {
            try {
                Float.parseFloat(dieukien);
            } catch (NumberFormatException e) {
                return showErrorTx(txDieuKienKhuyenMai, "Điều kiện khuyến mãi là giá hóa đơn tối thiểu để được khuyến mãi, phải là số thực");
            }
            try {
                float fPhanTram = Float.parseFloat(phantram);
                if (fPhanTram > 100) {
                    return showErrorTx(txPhanTramKhuyenMai, "Phần trăm khuyến mãi phải là số thực < 100 (%)");
                }
            } catch (NumberFormatException e) {
                return showErrorTx(txPhanTramKhuyenMai, "Phần trăm khuyến mãi phải là số thực");
            }
            try {
                LocalDate.parse(ngaybd);
            } catch (Exception e) {
                return showErrorTx(txNgayBD, "Ngày bắt đầu không hợp lệ");
            }
            try {
                LocalDate.parse(ngaykt);
            } catch (Exception e) {
                return showErrorTx(txNgayKT, "Ngày kết thúc không hợp lệ");
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

        dateComponentFormatter1 = new org.jdatepicker.impl.DateComponentFormatter();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txMaKM = new javax.swing.JTextField();
        txTenKM = new javax.swing.JTextField();
        txDieuKienKhuyenMai = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txPhanTramKhuyenMai = new javax.swing.JTextField();
        plNgayBD = new javax.swing.JPanel();
        txNgayBD = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        plNgayKT = new javax.swing.JPanel();
        txNgayKT = new javax.swing.JTextField();
        plButton = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(920, 500));
        setMinimumSize(new java.awt.Dimension(920, 500));
        setPreferredSize(new java.awt.Dimension(920, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 10));

        txMaKM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaKM.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txMaKM);

        txTenKM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTenKM.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txTenKM);

        txDieuKienKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Điều kiện khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txDieuKienKhuyenMai.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txDieuKienKhuyenMai);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(456, 120));
        jPanel6.setPreferredSize(new java.awt.Dimension(640, 120));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 10));

        txPhanTramKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phần trăm khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txPhanTramKhuyenMai.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel6.add(txPhanTramKhuyenMai);

        plNgayBD.setBackground(new java.awt.Color(255, 255, 255));
        plNgayBD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày bắt đầu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        plNgayBD.setForeground(new java.awt.Color(255, 102, 0));
        plNgayBD.setMaximumSize(new java.awt.Dimension(250, 100));
        plNgayBD.setMinimumSize(new java.awt.Dimension(250, 100));
        plNgayBD.setPreferredSize(new java.awt.Dimension(250, 100));
        plNgayBD.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        txNgayBD.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txNgayBD.setMaximumSize(new java.awt.Dimension(250, 40));
        txNgayBD.setMinimumSize(new java.awt.Dimension(250, 40));
        txNgayBD.setPreferredSize(new java.awt.Dimension(250, 40));
        plNgayBD.add(txNgayBD);

        jPanel6.add(plNgayBD);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMinimumSize(new java.awt.Dimension(360, 120));
        jPanel8.setPreferredSize(new java.awt.Dimension(360, 120));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        plNgayKT.setBackground(new java.awt.Color(255, 255, 255));
        plNgayKT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày kết thúc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        plNgayKT.setMaximumSize(new java.awt.Dimension(250, 100));
        plNgayKT.setMinimumSize(new java.awt.Dimension(250, 100));
        plNgayKT.setPreferredSize(new java.awt.Dimension(250, 100));
        plNgayKT.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        txNgayKT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txNgayKT.setMaximumSize(new java.awt.Dimension(250, 40));
        txNgayKT.setMinimumSize(new java.awt.Dimension(250, 40));
        txNgayKT.setPreferredSize(new java.awt.Dimension(250, 40));
        plNgayKT.add(txNgayKT);

        jPanel8.add(plNgayKT);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(903, 80));
        plButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));
        jPanel5.add(plButton, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
//            java.util.logging.Logger.getLogger(ThemSuaKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThemSuaKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//               new ThemSuaKhuyenMai("Thêm","").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdatepicker.impl.DateComponentFormatter dateComponentFormatter1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel plButton;
    private javax.swing.JPanel plNgayBD;
    private javax.swing.JPanel plNgayKT;
    private javax.swing.JTextField txDieuKienKhuyenMai;
    private javax.swing.JTextField txMaKM;
    private javax.swing.JTextField txNgayBD;
    private javax.swing.JTextField txNgayKT;
    private javax.swing.JTextField txPhanTramKhuyenMai;
    private javax.swing.JTextField txTenKM;
    // End of variables declaration//GEN-END:variables
}
