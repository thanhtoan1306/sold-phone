/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;


import BUS.KhachHangService;
import BUS.TaiKhoanService;
import DTO.Model.TaiKhoan;
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
public class ThemSuaTaiKhoan extends javax.swing.JFrame {

    String type;
    TaiKhoanService qltk = new TaiKhoanService();
    TaiKhoan tkSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaTaiKhoan(String _type, String _manv) {
        this.type = _type;
        initComponents();
        this.setLocationRelativeTo(null);
        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm tài khoản");

          //  btnThem.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa tài khoản");
            for (TaiKhoan tk : qltk.getDstk()) {
                if (tk.getManv().equals(_manv)) {
                    this.tkSua = tk;
                }
            }
            if (this.tkSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy tài khoản");
                this.dispose();
            }

            txTentk.setText(this.tkSua.getTentk());
            txMatKhau.setText(this.tkSua.getMk());
            txMaQuyen.setText(this.tkSua.getMaquyen());
            txMaNV.setText(this.tkSua.getManv());

            txTentk.setEditable(false);

          //  btnSua.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png")));
            plButton.add(btnSua);
        }

      //  btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png")));
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
            String tentk = txTentk.getText();
            String mk = txMatKhau.getText();
            String maquyen = txMaQuyen.getText();
            String manv = txMaNV.getText();

            if (qltk.add(tentk, mk, manv, maquyen)) {
                if (qltk.saveToDatabase(new TaiKhoan(tentk, mk, manv, maquyen))) {
                    JOptionPane.showMessageDialog(this, "Thêm " + tentk + " thành công!");
                }
                this.dispose();
            }

        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String tentk = txTentk.getText();
            String matkhau = txMatKhau.getText();
            String maquyen = txMaQuyen.getText();
            String manv = txMaNV.getText();

            if (qltk.update(tentk, matkhau, manv, maquyen)) {
                JOptionPane.showMessageDialog(this, "Sửa " + tentk + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String tentk = txTentk.getText();
        String matkhau = txMatKhau.getText();
        String maquyen = txMaQuyen.getText();
        String manv = txMaNV.getText();

        if (manv.trim().equals("")) {
            return showErrorTx(txMaNV, "Mã nhân viên không được để trống");

        } else if (tentk.trim().equals("")) {
            return showErrorTx(txTentk, "Tên tài khoản không được để trống");

        } else if (matkhau.trim().equals("")) {
            return showErrorTx(txMatKhau, "Mật khẩu không được để trống");

        } else if (maquyen.trim().equals("")) {
            return showErrorTx(txMaQuyen, "Mã quyền không được để trống");
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
        txTentk = new javax.swing.JTextField();
        txMatKhau = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txMaNV = new javax.swing.JTextField();
        txMaQuyen = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(414, 323));
        setMinimumSize(new java.awt.Dimension(414, 323));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(576, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        txTentk.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTentk.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txTentk);

        txMatKhau.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mật khẩu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMatKhau.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel3.add(txMatKhau);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        txMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaNV.setPreferredSize(new java.awt.Dimension(200, 80));
        txMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaNVActionPerformed(evt);
            }
        });
        jPanel4.add(txMaNV);

        txMaQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaQuyen.setPreferredSize(new java.awt.Dimension(200, 80));
        jPanel4.add(txMaQuyen);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
            .addComponent(plButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(plButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaNVActionPerformed

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
            java.util.logging.Logger.getLogger(ThemSuaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSuaTaiKhoan("Thêm", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txMaNV;
    private javax.swing.JTextField txMaQuyen;
    private javax.swing.JTextField txMatKhau;
    private javax.swing.JTextField txTentk;
    // End of variables declaration//GEN-END:variables
}
