
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import BUS.ChiTietHoaDonService;
import BUS.ChiTietPhieuNhapService;
import BUS.PhieuNhapService;
import BUS.SanPhamService;
import DTO.Model.ChiTietPhieuNhap;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiChiTietPhieuNhap extends javax.swing.JPanel {

    ChiTietPhieuNhapService qlctpn = new ChiTietPhieuNhapService();
    SanPhamService qlspBUS = new SanPhamService();

    String mapn;
    MyTable mtb;

    public HienThiChiTietPhieuNhap(String _mapn) {
        initComponents();
        this.mapn = _mapn;

        setDataToTable(qlctpn.search("Mã phiếu nhập", this.mapn, -1,-1,-1,-1), mtb);
        
         cbTypeSearch.addActionListener((ae) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(String.valueOf(cbTypeSearch.getSelectedItem())));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });
        
        addDocumentListener(txTim);
        addDocumentListener(txKhoangSoLuong1);
        addDocumentListener(txKhoangSoLuong2);
        addDocumentListener(txKhoangTien1);
        addDocumentListener(txKhoangTien2);
    }

    public void refresh() {
        qlctpn.readDB();

        setDataToTable(qlctpn.search("Mã phiếu nhập", this.mapn, -1,-1,-1,-1), mtb);

    }
    
    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txSearchOnChange();
            }
        });
    }
    
    public String getSelectedRow(int col) {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void txSearchOnChange() {
        //setDataToTable(qlctpn.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText()), mtb);
        int soLuong1 = -1, soLuong2 = -1;
        float thanhTien1 = -1, thanhTien2 = -1;
        try {
            soLuong1 = Integer.parseInt(txKhoangSoLuong1.getText());
            txKhoangSoLuong1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangSoLuong1.setForeground(Color.red);
        }
        try {
            soLuong2 = Integer.parseInt(txKhoangSoLuong2.getText());
            txKhoangSoLuong2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangSoLuong2.setForeground(Color.red);
        }
        try {
            thanhTien1 = Float.parseFloat(txKhoangTien1.getText());
            txKhoangTien1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien1.setForeground(Color.red);
        }
        try {
            thanhTien2 = Float.parseFloat(txKhoangTien2.getText());
            txKhoangTien2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien2.setForeground(Color.red);
        }
        ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();
        qlctpn.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText(), soLuong1, soLuong2, thanhTien1, thanhTien2).forEach((t) -> {
            if (t.getMaPN().equals(mapn)) {
                dsctpn.add(t);
            }
        });
        setDataToTable(dsctpn, mtb);
    }

    private void setDataToTable(ArrayList<ChiTietPhieuNhap> data, MyTable mtb) {
        mtb.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        for (ChiTietPhieuNhap pn : data) {
            mtb.addRow(new String[]{
                String.valueOf(stt),
                pn.getMaPN(),
                pn.getMaSP(),
                qlspBUS.getSanPham(pn.getMaSP()).getTenSP(),
                String.valueOf(pn.getsLuong()),
                PriceFormatter.format(pn.getDonGia()),
                PriceFormatter.format(pn.getsLuong() * pn.getDonGia())
            });
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel2 = new javax.swing.JPanel();
        cbTypeSearch = new javax.swing.JComboBox<>();
        txTim = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txKhoangSoLuong1 = new javax.swing.JTextField();
        txKhoangSoLuong2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txKhoangTien1 = new javax.swing.JTextField();
        txKhoangTien2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        rSPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelShadow1.setMinimumSize(new java.awt.Dimension(10, 100));
        rSPanelShadow1.setPreferredSize(new java.awt.Dimension(450, 150));
        rSPanelShadow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(230, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sản phẩm", "Số lượng", "Đơn giá" }));
        jPanel2.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(16, 100));
        jPanel2.add(txTim);

        rSPanelShadow1.add(jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));
        jPanel7.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel7.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        txKhoangSoLuong1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangSoLuong1.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel7.add(txKhoangSoLuong1);

        txKhoangSoLuong2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangSoLuong2.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel7.add(txKhoangSoLuong2);

        rSPanelShadow1.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền (/triệu)"));
        jPanel8.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel8.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        txKhoangTien1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangTien1.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel8.add(txKhoangTien1);

        txKhoangTien2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangTien2.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel8.add(txKhoangTien2);

        rSPanelShadow1.add(jPanel8);

        jButton2.setBackground(new java.awt.Color(3, 81, 145));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.setAutoscrolls(true);
        jButton2.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(jButton2);

        jPanel4.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(1200, 150));
        jPanel6.setMinimumSize(new java.awt.Dimension(1200, 150));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 150));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));
        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setHeaders(new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"});
        mtb.setColumnsWidth(new double[]{.5, 4, 4, 4, 4, 4});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(1, JLabel.CENTER);
        mtb.setAlignment(3, JLabel.CENTER);
        mtb.setAlignment(4, JLabel.RIGHT);
        mtb.setAlignment(5, JLabel.RIGHT);
        mtb.setupSort();
        jPanel3.add(new JScrollPane(mtb));

        rSPanelShadow1.add(jPanel4);

        jPanel1.add(rSPanelShadow1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setHeaders(new String[]{"STT", "Mã phiếu nhập", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(4, JLabel.CENTER);
        mtb.setAlignment(5, JLabel.RIGHT);
        mtb.setAlignment(6, JLabel.RIGHT);
        mtb.setupSort();
        jPanel3.add(new JScrollPane(mtb));

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(HienThiChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HienThiChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HienThiChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HienThiChiTietPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HienThiChiTietPhieuNhap("PN01").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JTextField txKhoangSoLuong1;
    private javax.swing.JTextField txKhoangSoLuong2;
    private javax.swing.JTextField txKhoangTien1;
    private javax.swing.JTextField txKhoangTien2;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
