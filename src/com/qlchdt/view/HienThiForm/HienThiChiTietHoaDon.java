/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.HienThiForm;

import com.qlchdt.model.ChiTietHoaDon;
import com.qlchdt.service.ChiTietHoaDonService;
import com.qlchdt.service.SanPhamService;
import com.qlchdt.service.format.MyTable;
import com.qlchdt.service.format.PriceFormatter;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiChiTietHoaDon extends JFrame {

    ChiTietHoaDonService qlcthd = new ChiTietHoaDonService();
    SanPhamService qlspBUS = new SanPhamService();

    String mahd;
    MyTable mtb;

    public HienThiChiTietHoaDon(String _mahd) {
        this.mahd = _mahd;
        initComponents();
        setDataToTable(qlcthd.search("Mã hóa đơn", this.mahd, -1, -1, -1, -1), mtb);
        
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
        
        
        this.setVisible(true);
    }

    public void refresh() {
        qlcthd.readDB();
        setDataToTable(qlcthd.search("Mã hóa đơn", this.mahd, -1, -1, -1, -1), mtb);
        txTim.setText("");
        txKhoangSoLuong1.setText("");
        txKhoangSoLuong2.setText("");
        txKhoangTien1.setText("");
        txKhoangTien2.setText("");
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
        ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
        qlcthd.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText(), soLuong1, soLuong2, thanhTien1, thanhTien2).forEach((t) -> {
            if (t.getMaHoaDon().equals(mahd)) {
                dscthd.add(t);
            }
        });
        setDataToTable(dscthd, mtb);
    }

    private void setDataToTable(ArrayList<ChiTietHoaDon> data, MyTable mtb) {
        mtb.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        for (ChiTietHoaDon cthd : data) {
            mtb.addRow(new String[]{
                String.valueOf(stt),
                cthd.getMaSanPham(),
                qlspBUS.getSanPham(cthd.getMaSanPham()).getTenSP(),
                String.valueOf(cthd.getSoLuong()),
                PriceFormatter.format(cthd.getDonGia()),
                PriceFormatter.format(cthd.getSoLuong() * cthd.getDonGia())});
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
        jPanel4 = new javax.swing.JPanel();
        txKhoangSoLuong1 = new javax.swing.JTextField();
        txKhoangSoLuong2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txKhoangTien1 = new javax.swing.JTextField();
        txKhoangTien2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1200, 600));

        jPanel1.setLayout(new java.awt.BorderLayout());

        rSPanelShadow1.setMinimumSize(new java.awt.Dimension(10, 100));
        rSPanelShadow1.setLayout(new java.awt.FlowLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sản phẩm", "Số lượng", "Đơn giá" }));
        jPanel2.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel2.add(txTim);

        rSPanelShadow1.add(jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        txKhoangSoLuong1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangSoLuong1.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel4.add(txKhoangSoLuong1);

        txKhoangSoLuong2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangSoLuong2.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel4.add(txKhoangSoLuong2);

        rSPanelShadow1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền (/triệu)"));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        txKhoangTien1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangTien1.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel5.add(txKhoangTien1);

        txKhoangTien2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangTien2.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel5.add(txKhoangTien2);

        rSPanelShadow1.add(jPanel5);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_refresh_30px.png"))); // NOI18N
        jButton2.setText("Làm mới");
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
        mtb.setHeaders(new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"});
        mtb.setColumnsWidth(new double[]{.5, 4, 4, 4, 4, 4});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(1, JLabel.CENTER);
        mtb.setAlignment(3, JLabel.CENTER);
        mtb.setAlignment(4, JLabel.RIGHT);
        mtb.setAlignment(5, JLabel.RIGHT);
        mtb.setupSort();
        jPanel3.add(new JScrollPane(mtb));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private javax.swing.JTextField txKhoangSoLuong1;
    private javax.swing.JTextField txKhoangSoLuong2;
    private javax.swing.JTextField txKhoangTien1;
    private javax.swing.JTextField txKhoangTien2;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
