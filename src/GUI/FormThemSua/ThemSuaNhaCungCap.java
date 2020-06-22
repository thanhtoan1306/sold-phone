/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;


import BUS.NhaCungCapService;
import DTO.Model.NhaCungCap;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ThemSuaNhaCungCap extends javax.swing.JFrame {

    NhaCungCapService qlncc = new NhaCungCapService();
    String type;

    NhaCungCap nccSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaNhaCungCap(String _type, String _mancc) {
        initComponents();
        this.type = _type;
        this.setLocationRelativeTo(null);
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm nhà cung cấp");
            txMaNCC.setText(qlncc.getNextID());

            
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa nhà cung cấp");
            for (NhaCungCap ncc : qlncc.getDsncc()) {
                if (ncc.getMaNCC().equals(_mancc)) {
                    this.nccSua = ncc;
                }
            }
            if (this.nccSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy nhà cung cấp");
                this.dispose();
            }

            txMaNCC.setText(this.nccSua.getMaNCC());
            txTenNCC.setText(this.nccSua.getTenNCC());
            txDiaChi.setText(this.nccSua.getDiaChi());
            txSDT.setText(String.valueOf(this.nccSua.getSDT()));

            txMaNCC.setEditable(false);

           
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

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String maNCC = txMaNCC.getText();
            String tenNCC = txTenNCC.getText();
            String diaChi = txDiaChi.getText();
            String SDT = txSDT.getText();
            String regexSDT = "^[0-9]{10}$";
            if (!SDT.matches(regexSDT)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
                return;
            }
            
            if (qlncc.update(maNCC, tenNCC, diaChi, SDT)) {
                JOptionPane.showMessageDialog(this, "Sửa " + maNCC + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            NhaCungCap ncc = new NhaCungCap(txMaNCC.getText(), txTenNCC.getText(), txDiaChi.getText(), txSDT.getText());
            if (qlncc.add(ncc)) {
                JOptionPane.showMessageDialog(this, "Thêm " + txTenNCC.getText() + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String ma = txMaNCC.getText();
        String ten = txTenNCC.getText();
        String diachi = txDiaChi.getText();
        String sdt = txSDT.getText();
        String regexSDT = "^[0-9]{10}$";
        
        if (ma.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không được để trống");
            txMaNCC.requestFocus();
            return false;
        } else if (ten.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được để trống");
            txTenNCC.requestFocus();
            return false;
        } else if (diachi.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ nhà cung cấp không được để trống");
            txDiaChi.requestFocus();
            return false;
        } else if (sdt.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại nhà cung cấp không được để trống");
            txSDT.requestFocus();
            return false;
        } else if (!sdt.matches(regexSDT)) {  
            JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txMaNCC = new javax.swing.JTextField();
        txTenNCC = new javax.swing.JTextField();
        txDiaChi = new javax.swing.JTextField();
        txSDT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(450, 500));
        jPanel1.setLayout(new java.awt.BorderLayout());

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(450, 80));
        plButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));
        jPanel1.add(plButton, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));

        txMaNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaNCC.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel5.add(txMaNCC);

        txTenNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTenNCC.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel5.add(txTenNCC);

        txDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Địa chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txDiaChi.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel5.add(txDiaChi);

        txSDT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txSDT.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel5.add(txSDT);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ThemSuaNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSuaNhaCungCap("Thêm", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txDiaChi;
    private javax.swing.JTextField txMaNCC;
    private javax.swing.JTextField txSDT;
    private javax.swing.JTextField txTenNCC;
    // End of variables declaration//GEN-END:variables
}
