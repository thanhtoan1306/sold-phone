/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import BUS.KhachHangService;
import BUS.NhanVienService;
import DTO.Model.NhanVien;
import GUI.Custom.MyTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiNhanVien extends javax.swing.JPanel {

    NhanVienService nhanVienService = new NhanVienService();
    MyTable tbNhanVien;

    public HienThiNhanVien() {
        initComponents();
        setDataToTable(nhanVienService.getDsnv(), tbNhanVien);
        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnRefresh.addActionListener((ae) -> {
            refresh();
        });
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
    }

    public String getSelectedRow(int col) {
        int i = tbNhanVien.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbNhanVien.getTable().convertRowIndexToModel(i);
            return tbNhanVien.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public void refresh() {
        nhanVienService.readDB();
        setDataToTable(nhanVienService.getDsnv(), tbNhanVien);
    }

    private void txSearchOnChange() {
        setDataToTable(nhanVienService.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), tbNhanVien);
    }

    private void setDataToTable(ArrayList<NhanVien> data, MyTable table) {
        table.clear();
        for (NhanVien nv : data) {
            table.addRow(new String[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getNgaySinh().toString(),
                nv.getGioiTinh(),
                nv.getSDT(),
                nv.getDiaChi(),
                nv.getHinhAnh(),});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cbTypeSearch = new javax.swing.JComboBox<>();
        txTim = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(470, 60));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(238, 100));
        jPanel10.setPreferredSize(new java.awt.Dimension(520, 100));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 0));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        cbTypeSearch.setBackground(new java.awt.Color(3, 81, 145));
        cbTypeSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbTypeSearch.setForeground(new java.awt.Color(255, 255, 255));
        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại" }));
        cbTypeSearch.setMaximumSize(new java.awt.Dimension(150, 50));
        cbTypeSearch.setMinimumSize(new java.awt.Dimension(150, 50));
        cbTypeSearch.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel7.add(cbTypeSearch, java.awt.BorderLayout.LINE_START);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(300, 60));
        jPanel7.add(txTim, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel7);

        btnRefresh.setBackground(new java.awt.Color(3, 81, 145));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.setMaximumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setMinimumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel10.add(btnRefresh);

        jPanel2.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());
        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setMaximumSize(new java.awt.Dimension(1200, 600));
        jPanel5.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 600));
        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);
        tbNhanVien = new MyTable();
        tbNhanVien.setPreferredSize(new Dimension(900-250, 345));
        tbNhanVien.setHeaders(new String[]{"Mã NV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Hình Ảnh"});
        tbNhanVien.setColumnsWidth(new double[]{.4, 1, .75, .4, .8, .7, 0.7});
        tbNhanVien.setAlignment(3, JLabel.RIGHT);
        tbNhanVien.setAlignment(4, JLabel.RIGHT);
        tbNhanVien.setAlignment(5, JLabel.RIGHT);
        tbNhanVien.setAlignment(6, JLabel.RIGHT);

        jPanel5.add(new JScrollPane(tbNhanVien));

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
