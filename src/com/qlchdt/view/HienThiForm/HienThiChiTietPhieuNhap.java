/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.HienThiForm;

import com.qlchdt.model.ChiTietPhieuNhap;
import com.qlchdt.service.ChiTietHoaDonService;
import com.qlchdt.service.ChiTietPhieuNhapService;
import com.qlchdt.service.PhieuNhapService;
import com.qlchdt.service.SanPhamService;
import com.qlchdt.view.DinhDangCp.MyTable;
import com.qlchdt.view.DinhDangCp.PriceFormatter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiChiTietPhieuNhap extends javax.swing.JFrame {

    ChiTietPhieuNhapService qlctpn = new ChiTietPhieuNhapService();
    SanPhamService qlspBUS = new SanPhamService();

    String mapn;
    MyTable mtb;

    public HienThiChiTietPhieuNhap(String _mapn) {
        initComponents();
        this.mapn = _mapn;
        this.setTitle("Mã phiếu nhập " + _mapn);
        setDataToTable(qlctpn.search("Mã phiếu nhập", this.mapn), mtb);
        
         txTim.getDocument().addDocumentListener(new DocumentListener() {
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
         this.setVisible(true);
    }

    public void refresh() {
        qlctpn.readDB();

        setDataToTable(qlctpn.search("Mã phiếu nhập", this.mapn), mtb);

    }

    private void txSearchOnChange() {
        setDataToTable(qlctpn.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText()), mtb);
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
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        rSPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelShadow1.setMinimumSize(new java.awt.Dimension(10, 100));
        rSPanelShadow1.setPreferredSize(new java.awt.Dimension(450, 150));
        rSPanelShadow1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(230, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sản phẩm", "Số lượng", "Đơn giá" }));
        jPanel2.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(16, 100));
        jPanel2.add(txTim);

        rSPanelShadow1.add(jPanel2);

        jButton2.setBackground(new java.awt.Color(0, 204, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_refresh_30px.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.setAutoscrolls(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        rSPanelShadow1.add(jButton2);

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

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
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
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
