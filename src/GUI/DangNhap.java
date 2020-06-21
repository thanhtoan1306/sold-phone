/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.DocFile;
import BUS.NhanVienService;
import BUS.QuyenService;
import BUS.TaiKhoanService;
import DTO.Model.NhanVien;
import DTO.Model.Quyen;
import DTO.Model.TaiKhoan;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DangNhap extends javax.swing.JFrame {

    public static String saveFileName = "temp";
    public static TaiKhoan taiKhoanLogin;
    public static NhanVien nhanVienLogin;
    public static Quyen quyenLogin;

    
    TaiKhoanService tks = new TaiKhoanService();

    public DangNhap() {
        super("Đăng nhập hệ thống");
        
        initComponents();
        this.setLocationRelativeTo(null);
        String text = new DocFile(saveFileName).read();
        if (!text.equals("")) {
            try {
                txtUser.setText(text.split(" ")[0]);
                txtPass.setText(text.split(" ")[1]);
                checkrem.setSelected(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi Giữ đăng nhập");
            }
        }
    }

    public static TaiKhoan getTaiKhoanLogin() {
        return taiKhoanLogin;
    }

    public static NhanVien getNhanVienLogin() {
        return nhanVienLogin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelImage2 = new rojerusan.RSPanelImage();
        jPanel7 = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new rojeru_san.RSMTextFull();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPass = new rojeru_san.RSMPassView();
        jPanel6 = new javax.swing.JPanel();
        checkrem = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        btnDangNhap = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setBackground(new java.awt.Color(153, 255, 255));
        setMaximumSize(new java.awt.Dimension(500, 300));
        setMinimumSize(new java.awt.Dimension(770, 400));
        setPreferredSize(new java.awt.Dimension(770, 400));

        rSPanelImage2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/bg_login.jpg"))); // NOI18N
        rSPanelImage2.setMaximumSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setMinimumSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setPreferredSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(0, 153, 255));
        jPanel7.setMaximumSize(new java.awt.Dimension(660, 260));
        jPanel7.setMinimumSize(new java.awt.Dimension(660, 260));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(660, 260));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/lock_icon.png"))); // NOI18N
        rSPanelImage1.setMaximumSize(new java.awt.Dimension(240, 240));
        rSPanelImage1.setMinimumSize(new java.awt.Dimension(240, 240));
        rSPanelImage1.setPreferredSize(new java.awt.Dimension(240, 240));

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jPanel7.add(rSPanelImage1);

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 60));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 60));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tài khoản");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel2.add(jLabel2);

        txtUser.setForeground(new java.awt.Color(153, 153, 153));
        txtUser.setBordeColorFocus(new java.awt.Color(153, 153, 153));
        txtUser.setBotonColor(new java.awt.Color(153, 153, 153));
        txtUser.setPlaceholder("Nhập tài khoản");
        jPanel2.add(txtUser);

        jPanel5.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(400, 60));
        jPanel3.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 60));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mật khẩu");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel3.add(jLabel1);

        txtPass.setForeground(new java.awt.Color(153, 153, 153));
        txtPass.setBordeColorFocus(new java.awt.Color(153, 153, 153));
        txtPass.setBotonColor(new java.awt.Color(153, 153, 153));
        txtPass.setPlaceholder("Nhập mật khẩu");
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        jPanel3.add(txtPass);

        jPanel5.add(jPanel3);

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(400, 60));
        jPanel6.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel6.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 20);
        flowLayout1.setAlignOnBaseline(true);
        jPanel6.setLayout(flowLayout1);

        checkrem.setBackground(new java.awt.Color(0, 153, 255));
        checkrem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        checkrem.setForeground(new java.awt.Color(255, 255, 255));
        checkrem.setText("Ghi nhớ tài khoản và mật khẩu");
        checkrem.setOpaque(false);
        checkrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkremActionPerformed(evt);
            }
        });
        jPanel6.add(checkrem);

        jPanel5.add(jPanel6);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(350, 60));
        jPanel4.setMinimumSize(new java.awt.Dimension(350, 60));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 60));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnDangNhap.setBackground(new java.awt.Color(204, 204, 204));
        btnDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/btn_login_icon.png"))); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel4.add(btnDangNhap);

        jPanel5.add(jPanel4);

        jPanel7.add(jPanel5);

        rSPanelImage2.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setMaximumSize(new java.awt.Dimension(770, 100));
        jPanel8.setMinimumSize(new java.awt.Dimension(770, 100));
        jPanel8.setOpaque(false);

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(770, 80));
        jPanel9.setMinimumSize(new java.awt.Dimension(770, 80));
        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(770, 80));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/management_icon.png"))); // NOI18N
        jPanel9.add(jLabel3);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quản lý cửa hàng điện thoại di động");
        jPanel9.add(jLabel4);

        jPanel8.add(jPanel9);

        rSPanelImage2.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(rSPanelImage2, java.awt.BorderLayout.PAGE_START);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void checkremActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkremActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkremActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        String user = txtUser.getText();
        String pass = txtPass.getText();

        String quyen = "admin";
        TaiKhoanService qltk = new TaiKhoanService();

        //Tạo object để gán dữ liệu
        TaiKhoan tk = qltk.getTaiKhoan(user);

        if (tk != null) {

            //Lấy dữ liệu của nhân viên thông qua tk.getMaNV
            NhanVien nv = new NhanVienService().getNhanVien(tk.getManv());

            //Kiểm tra đăng nhập
            if (tks.kiemtraLogin(user, pass) >= 0) {

                taiKhoanLogin = tk;
                nhanVienLogin = nv;

                //Lấy mã quyền
                quyenLogin = new QuyenService().getQuyen(taiKhoanLogin.getMaquyen());
                

                if (checkrem.isSelected()) {
                    // mã hóa ra tiếng trung
                    new DocFile(saveFileName).write(user + " " + pass);
                } else {
                    new DocFile(saveFileName).write(" ");
                }

                new GiaoDienChinh().setVisible(true);
                this.dispose();
//                if (quyen.equals(user)) {
//                   
//                } else {
//                    // ad.setVisible(true);
//                }
            } else {
                JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
                txtUser.setText("");
                txtPass.setText("");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại.");
        }

    }//GEN-LAST:event_btnDangNhapActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DangNhap().setVisible(true);
//                new DangNhap().rSButtonMetro1ActionPerformed(evt);
//                System.out.println( tks.getTaiKhoan(txtuser.getText(), txtpass.getText()));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JCheckBox checkrem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojerusan.RSPanelImage rSPanelImage2;
    private rojeru_san.RSMPassView txtPass;
    public rojeru_san.RSMTextFull txtUser;
    // End of variables declaration//GEN-END:variables
}
