/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;


import BUS.QuyenService;
import DTO.Model.Quyen;
import GUI.Custom.HuyButton;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ThemSuaQuyen extends javax.swing.JFrame {

    String type;
    QuyenService qlqBUS = new QuyenService();
    Quyen qSua;

    ChiTietQuyenForm chitietForm = new ChiTietQuyenForm();

   ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();
    

    public ThemSuaQuyen(String _type, String _maq) {
        this.type = _type;
        initComponents();

        plHienThiCTQ.add(chitietForm);
        this.setLocationRelativeTo(null);

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm quyền");
            txMaQuyen.setText(qlqBUS.getNextID());


            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa quyền");
            for (Quyen q : qlqBUS.getDsq()) {
                if (q.getMaQuyen().equals(_maq)) {
                    this.qSua = q;
                }
            }
            if (this.qSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy quyền");
                this.dispose();
            }

            txMaQuyen.setText(this.qSua.getMaQuyen());
            txTenQuyen.setText(this.qSua.getTenQuyen());
            chitietForm.setQuyen(this.qSua.getChiTietQuyen());

            txMaQuyen.setEditable(false);

            
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
            String maquyen = txMaQuyen.getText();
            String tenquyen = txTenQuyen.getText();
            String chitietquyen = chitietForm.getQuyen();

            if (qlqBUS.add(maquyen, tenquyen, chitietquyen)) {
                JOptionPane.showMessageDialog(this, "Thêm " + maquyen + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String maquyen = txMaQuyen.getText();
            String tenquyen = txTenQuyen.getText();
            String chitietquyen = chitietForm.getQuyen();
            System.out.println(chitietquyen);

            if (qlqBUS.update(maquyen, tenquyen, chitietquyen)) {
                JOptionPane.showMessageDialog(this, "Sửa " + maquyen + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String maquyen = txMaQuyen.getText();
        String tenquyen = txTenQuyen.getText();
        String chitietquyen = chitietForm.getQuyen();

        if (maquyen.trim().equals("")) {
            return showErrorTx(txMaQuyen, "Mã quyền không được để trống");

        } else if (tenquyen.trim().equals("")) {
            return showErrorTx(txMaQuyen, "Tên quyền không được để trống");

        } else if (chitietquyen.trim().equals("")) {
            return showErrorTx(txMaQuyen, "Bạn chưa chọn quyền nào cả !!");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    class ChiTietQuyenForm extends JPanel {

        final String[] type = {"Chỉ xem", "Xem và Quản lý"};
        ArrayList<PanelChooseQuyen> dsPanel = new ArrayList<>();

        public ChiTietQuyenForm() {
            setPreferredSize(new Dimension(400, 600));
            setLayout(new FlowLayout());   
            this.setBackground(Color.white);
            dsPanel.add(new PanelChooseQuyen("Bán Hàng", new String[]{"Bán hàng"}, new String[]{"qlBanHang"}));
            dsPanel.add(new PanelChooseQuyen("Nhập Hàng", new String[]{"Nhập hàng"}, new String[]{"qlNhapHang"}));
            dsPanel.add(new PanelChooseQuyen("Sản Phẩm", type, new String[]{"xemSanPham", "qlSanPham"}));
            dsPanel.add(new PanelChooseQuyen("Loại Sản Phẩm", type, new String[]{"xemLoaiSanPham", "qlLoaiSanPham"}));
            dsPanel.add(new PanelChooseQuyen("Hóa Đơn", type, new String[]{"xemHoaDon", "qlHoaDon"}));
            dsPanel.add(new PanelChooseQuyen("Khuyến Mãi", type, new String[]{"xemKhuyenMai", "qlKhuyenMai"}));
            dsPanel.add(new PanelChooseQuyen("Nhân Viên", type, new String[]{"xemNhanVien", "qlNhanVien"}));
            dsPanel.add(new PanelChooseQuyen("Khách Hàng", type, new String[]{"xemKhachHang", "qlKhachHang"}));
            dsPanel.add(new PanelChooseQuyen("Phiếu Nhập", type, new String[]{"xemPhieuNhap", "qlPhieuNhap"}));
            dsPanel.add(new PanelChooseQuyen("Nhà Cung Cấp", type, new String[]{"xemNCC", "qlNCC"}));
            dsPanel.add(new PanelChooseQuyen("Tài Khoản", type, new String[]{"xemTaiKhoan", "qlTaiKhoan"}));
            dsPanel.add(new PanelChooseQuyen("Quyền", type, new String[]{"xemQuyen", "qlQuyen"}));

            for (PanelChooseQuyen p : dsPanel) {
                this.add(p);
            }
        }

        public String getQuyen() {
            String result = "";
            for (PanelChooseQuyen p : dsPanel) {
                result += p.getValue();
            }
            return result.trim();
        }

        public void setQuyen(String quyen) {
            for (PanelChooseQuyen p : dsPanel) {
                p.setValue(quyen);
            }
        }
    }

    //tìm trên mạng
    class PanelChooseQuyen extends JPanel {

        String name;
        String[] type, value;

        JCheckBox chb;
        JComboBox<String> cb;

        public PanelChooseQuyen(String name, String[] type, String[] value) {
            this.setPreferredSize(new Dimension(250, 50));
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.white);

            this.name = name;
            this.type = type;
            this.value = value;

            this.chb = new JCheckBox(this.name);
            this.add(this.chb);

            this.cb = new JComboBox<>(this.type);
            this.cb.setEnabled(false);
            this.add(this.cb);

            chb.addActionListener((ae) -> {
                if (chb.isSelected()) {
                    this.cb.setEnabled(true);
                } else {
                    this.cb.setEnabled(false);
                }
            });
        }

        public String getValue() {
            String result = "";

            if (chb.isSelected()) {
                result += " " + value[cb.getSelectedIndex()];
            }

            return result;
        }

        public void setValue(String s) {
            if (s.equals("")) {
                chb.setSelected(false);
                return;
            }

            for (int i = 0; i < value.length; i++) {
                if (s.contains(value[i])) {
                    cb.setSelectedIndex(i);
                    cb.setEnabled(true);
                    chb.setSelected(true);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txMaQuyen = new javax.swing.JTextField();
        txTenQuyen = new javax.swing.JTextField();
        plHienThiCTQ = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 900));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new java.awt.BorderLayout());

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setMaximumSize(new java.awt.Dimension(400, 80));
        plButton.setMinimumSize(new java.awt.Dimension(400, 80));
        plButton.setPreferredSize(new java.awt.Dimension(400, 80));
        plButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));
        jPanel1.add(plButton, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 120));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 120));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 120));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        txMaQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaQuyen.setMaximumSize(new java.awt.Dimension(150, 60));
        txMaQuyen.setMinimumSize(new java.awt.Dimension(150, 60));
        txMaQuyen.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel2.add(txMaQuyen);

        txTenQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        txTenQuyen.setMaximumSize(new java.awt.Dimension(150, 60));
        txTenQuyen.setMinimumSize(new java.awt.Dimension(150, 60));
        txTenQuyen.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel2.add(txTenQuyen);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        plHienThiCTQ.setBackground(new java.awt.Color(255, 255, 255));
        plHienThiCTQ.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        plHienThiCTQ.setLayout(new java.awt.BorderLayout());
        jPanel1.add(plHienThiCTQ, java.awt.BorderLayout.CENTER);

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
            java.util.logging.Logger.getLogger(ThemSuaQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemSuaQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemSuaQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemSuaQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new ThemSuaQuyen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel plButton;
    private javax.swing.JPanel plHienThiCTQ;
    private javax.swing.JTextField txMaQuyen;
    private javax.swing.JTextField txTenQuyen;
    // End of variables declaration//GEN-END:variables
}
