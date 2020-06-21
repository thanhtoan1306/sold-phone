/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;

import BUS.KhachHangService;
import DTO.Model.KhachHang;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rojerusan.RSMaterialButtonRectangle;

/**
 *
 * @author User
 */
public class ThemSuaKhachHang extends javax.swing.JFrame {

    String type;
    KhachHangService qlkh = new KhachHangService();
    KhachHang khSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();
    

    public ThemSuaKhachHang(String _type, String _makh) {
        this.type = _type;
        initComponents();
        this.setLocationRelativeTo(null);

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm khách hàng");
            txMaKH.setText(qlkh.getNextID());

           
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa khách hàng");
            for (KhachHang kh : qlkh.getDskh()) {
                if (kh.getMaKH().equals(_makh)) {
                    this.khSua = kh;
                }
            }
            if (this.khSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy khách hàng");
                this.dispose();
            }

            txMaKH.setText(this.khSua.getMaKH());
            txTenKH.setText(this.khSua.getTenKH());
            txDiaChi.setText(this.khSua.getDiaChi());
            txSDT.setText(this.khSua.getSDT());

            txMaKH.setEditable(false);

           
            plButton.add(btnSua);
        }

       
        plButton.add(btnHuy);

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
            String makh = txMaKH.getText();
            String tenkh = txTenKH.getText();
            String diachi = txDiaChi.getText();
            String sdt = txSDT.getText();
            String regexSDT = "^[0-9]{10}$";
            if (!sdt.matches(regexSDT)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
                return;
            }
            
            if (qlkh.add(makh, tenkh, diachi, sdt)) {
                JOptionPane.showMessageDialog(this, "Thêm " + tenkh + " thành công!");
                this.dispose();
            }

        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String makh = txMaKH.getText();
            String tenkh = txTenKH.getText();
            String diachi = txDiaChi.getText();
            String sdt = txSDT.getText();
            String regexSDT = "^[0-9]{10}$";
            if (!sdt.matches(regexSDT)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
                return;
            }
            
            if (qlkh.update(makh, tenkh, diachi, sdt)) {
                JOptionPane.showMessageDialog(this, "Sửa " + makh + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String makh = txMaKH.getText();
        String tenkh = txTenKH.getText();
        String diachi = txDiaChi.getText();
        String sdt = txSDT.getText();

        if (makh.trim().equals("")) {
            return showErrorTx(txMaKH, "Mã khách hàng không được để trống");

        } else if (tenkh.trim().equals("")) {
            return showErrorTx(txTenKH, "Tên khách hàng không được để trống");

        } else if (diachi.trim().equals("")) {
            return showErrorTx(txDiaChi, "Địa chỉ không được để trống");

        } else if (sdt.trim().equals("") && sdt.length() == 10) {
            return showErrorTx(txSDT, "Vui lòng nhập chính xác số điện thoại");
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

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txMaKH = new javax.swing.JTextField();
        txTenKH = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txSDT = new javax.swing.JTextField();
        txDiaChi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(375, 317));
        setMinimumSize(new java.awt.Dimension(375, 317));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(576, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        txMaKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã KH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaKH.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txMaKH);

        txTenKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên KH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTenKH.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txTenKH);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        txSDT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txSDT.setPreferredSize(new java.awt.Dimension(200, 80));
        txSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSDTActionPerformed(evt);
            }
        });
        jPanel4.add(txSDT);

        txDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Địa chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txDiaChi.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel4.add(txDiaChi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
            .addComponent(plButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(plButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txSDTActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSuaKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSuaKhachHang("Thêm", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txDiaChi;
    private javax.swing.JTextField txMaKH;
    private javax.swing.JTextField txSDT;
    private javax.swing.JTextField txTenKH;
    // End of variables declaration//GEN-END:variables
}
