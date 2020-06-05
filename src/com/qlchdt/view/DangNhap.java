/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;

import com.qlchdt.model.TaiKhoan;
import com.qlchdt.service.DocFile;
import com.qlchdt.service.TaiKhoanService;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class DangNhap extends javax.swing.JFrame {

    TaiKhoanService tks = new TaiKhoanService();

    GiaoDienAdmin ad=new GiaoDienAdmin();
    TestHeThong ht = new TestHeThong();
    public DangNhap() {
        super("Đăng nhập hệ thống");
        this.setLocationRelativeTo(null);
        initComponents();
        String text = new DocFile(saveFileName).read();
        if (!text.equals("")) {
            try {
                txtuser.setText(text.split(" ")[0]);
                txtpass.setText(text.split(" ")[1]);
                checkrem.setSelected(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi Giữ đăng nhập");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    /*
     String user=txtuser.getText();
       String pass=txtpass.getText();
//       if(user.length()==0 || pass.length()==0)
//       {
//           JOptionPane.showMessageDialog(null,"Chưa nhập thông tin","Thông báo",1);
//       }
       if( tks.kiemtraLogin(user, pass)>=0)
       {   
           JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
           this.setVisible(false);
           ht.setVisible(true);
           if(checkrem.isSelected())
           {
               // mã hóa ra tiếng trung
               new DocFile(saveFileName).write(user+" "+pass);
           }
           else
           {
               new DocFile(saveFileName).write(" ");
           }
       }
       else{
          JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
          txtuser.setText("");
          txtpass.setText("");
       }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelImage2 = new rojerusan.RSPanelImage();
        jPanel7 = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtuser = new rojeru_san.RSMTextFull();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpass = new rojeru_san.RSMPassView();
        jPanel6 = new javax.swing.JPanel();
        checkrem = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        btnDangNhap = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setBackground(new java.awt.Color(153, 255, 255));
        setMaximumSize(new java.awt.Dimension(770, 400));
        setMinimumSize(new java.awt.Dimension(770, 400));
        setPreferredSize(new java.awt.Dimension(770, 400));

        rSPanelImage2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/background_login.jpg"))); // NOI18N
        rSPanelImage2.setMaximumSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setMinimumSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setPreferredSize(new java.awt.Dimension(770, 400));
        rSPanelImage2.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(0, 153, 255));
        jPanel7.setMaximumSize(new java.awt.Dimension(660, 260));
        jPanel7.setMinimumSize(new java.awt.Dimension(660, 260));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(660, 260));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/lock.png"))); // NOI18N
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

        txtuser.setForeground(new java.awt.Color(153, 153, 153));
        txtuser.setBordeColorFocus(new java.awt.Color(153, 153, 153));
        txtuser.setBotonColor(new java.awt.Color(153, 153, 153));
        txtuser.setPlaceholder("Nhập tài khoản");
        jPanel2.add(txtuser);

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

        txtpass.setForeground(new java.awt.Color(153, 153, 153));
        txtpass.setBordeColorFocus(new java.awt.Color(153, 153, 153));
        txtpass.setBotonColor(new java.awt.Color(153, 153, 153));
        txtpass.setPlaceholder("Nhập mật khẩu");
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        jPanel3.add(txtpass);

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
        jPanel4.setMaximumSize(new java.awt.Dimension(400, 60));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 60));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 60));

        btnDangNhap.setBackground(new java.awt.Color(204, 204, 204));
        btnDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8-login-50.png"))); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setPreferredSize(new java.awt.Dimension(170, 50));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel4.add(btnDangNhap);

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8-forgot-password-50.png"))); // NOI18N
        jButton3.setText("Quên mật khẩu");
        jButton3.setPreferredSize(new java.awt.Dimension(193, 50));
        jPanel4.add(jButton3);

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
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8-management-96.png"))); // NOI18N
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

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void checkremActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkremActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkremActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
       String user=txtuser.getText();
       String pass=txtpass.getText();
       String quyen="admin" ;
       if( tks.kiemtraLogin(user, pass)>=0)
       {   

           JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
           this.setVisible(false);

           if(checkrem.isSelected())
           {
               // mã hóa ra tiếng trung
               new DocFile(saveFileName).write(user+" "+pass);
           }
           else
           {
               new DocFile(saveFileName).write(" ");
           }
           JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
           this.setVisible(false);
           if(quyen.equals(user))
           {
               ht.setVisible(true);
           }
           else
           {
              ad.setVisible(true);
           }
       }
       else{
          JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
          txtuser.setText("");
          txtpass.setText("");
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
    public static String saveFileName = "temp";
    public static TaiKhoan taiKhoanLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JCheckBox checkrem;
    private javax.swing.JButton jButton3;
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
    private rojeru_san.RSMPassView txtpass;
    private rojeru_san.RSMTextFull txtuser;
    // End of variables declaration//GEN-END:variables
}
