/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import BUS.NhaCungCapService;
import BUS.NhanVienService;
import BUS.PhieuNhapService;
import DTO.Model.PhieuNhap;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.FormQuanLy.QuanLyChiTietPhieuNhap;
import GUI.Custom.DateButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiPhieuNhap extends javax.swing.JPanel {

    PhieuNhapService qlpn = new PhieuNhapService();
    NhanVienService qlnv = new NhanVienService();
    NhaCungCapService qlncc = new NhaCungCapService();

    MyTable mtb;

    DatePicker dPickerNgayBD;
    DatePicker dPickerNgayKT;

    public HienThiPhieuNhap() {
        initComponents();
        // khoang ngay

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPickerNgayBD = new DatePicker(pickerSettings);
        dPickerNgayBD.setBackground(Color.white);
        dPickerNgayBD.setDateToToday();
        DateButton db = new DateButton(dPickerNgayBD);

        DatePickerSettings pickerSettings2 = new DatePickerSettings();
        pickerSettings2.setVisibleDateTextField(false);
        dPickerNgayKT = new DatePicker(pickerSettings2);
        dPickerNgayKT.setBackground(Color.white);
        DateButton dk = new DateButton(dPickerNgayKT);

        plNgayBD.add(dPickerNgayBD, BorderLayout.CENTER);
        plNgayKT.add(dPickerNgayKT, BorderLayout.CENTER);
        
        
        
        setDataToTable(qlpn.getDspn(), mtb);
        
        
        
        mtb.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String mapn = getSelectedRow(1);
                if (mapn != null) {
                    showInfo(mapn);
                }
            }
        });
        
           dPickerNgayBD.addDateChangeListener((dce) -> {
            txKhoangNgay1.setText(dPickerNgayBD.getDateStringOrEmptyString());
        });
        dPickerNgayKT.addDateChangeListener((dce) -> {
            txKhoangNgay2.setText(dPickerNgayKT.getDateStringOrEmptyString());
        });
        
        addDocumentListener(txTim);
        addDocumentListener(txKhoangNgay1);
        addDocumentListener(txKhoangNgay2);
        addDocumentListener(txKhoangTien1);
        addDocumentListener(txKhoangTien2);

        cbTypeSearch.addActionListener((ae) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(String.valueOf(cbTypeSearch.getSelectedItem())));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });
    }
    
       public MyTable getTable() {
        return this.mtb;
    }

    public String getSelectedRow(int col) {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public void showInfo(String mapn) {
        if (mapn != null) {
            // show hình
            for (PhieuNhap pn : qlpn.getDspn()) {
                if (pn.getMaPN().equals(mapn)) {
                    // show info
                    String tennhanvien = qlnv.getNhanVien(pn.getMaNV()).getTenNV();
                    String tenncc = qlncc.getNhaCungCap(pn.getMaNCC()).getTenNCC();

                    txMaPN.setText(pn.getMaPN());
                    txMaNCC.setText(tenncc + " - " + pn.getMaNCC());
                    txMaNV.setText(tennhanvien + " - " + pn.getMaNV());
                    txNgayNhap.setText(pn.getNgayNhap().toString());
                    txGioNhap.setText(pn.getGioNhap().toString());
                    txTongTien.setText(PriceFormatter.format(pn.getTongTien()));
                    return;
                }
            }
        }
    }

    public void refresh() {
        qlpn.readDB();
        setDataToTable(qlpn.getDspn(), mtb);
        dPickerNgayBD.setDate(null);
        dPickerNgayKT.setDate(null);
        txTim.setText("");
        txKhoangNgay1.setText("");
        txKhoangNgay2.setText("");
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

    private void txSearchOnChange() {
        LocalDate ngay1 = null, ngay2 = null;
        int tong1 = -1, tong2 = -1;
        try {
            ngay1 = java.time.LocalDate.parse(txKhoangNgay1.getText());
            txKhoangNgay1.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay1.setForeground(Color.red);
        }
        try {
            ngay2 = java.time.LocalDate.parse(txKhoangNgay2.getText());
            txKhoangNgay2.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay2.setForeground(Color.red);
        }
        try {
            tong1 = Integer.parseInt(txKhoangTien1.getText());
            txKhoangTien1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien1.setForeground(Color.red);
        }
        try {
            tong2 = Integer.parseInt(txKhoangTien2.getText());
            txKhoangTien2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien2.setForeground(Color.red);
        }
        setDataToTable(qlpn.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText(), ngay1, ngay2, tong1, tong2), mtb);
    }

    private void btnDetailsMouseClicked() {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            QuanLyChiTietPhieuNhap qlctpn = new QuanLyChiTietPhieuNhap(mtb.getModel().getValueAt(i, 1).toString()); //truyen tham so cho qlctpn
            qlctpn.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn phiếu nhập nào để xem!");
        }
    }

    private void setDataToTable(ArrayList<PhieuNhap> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        for (PhieuNhap pn : data) {
            table.addRow(new String[]{
                String.valueOf(stt),
                pn.getMaPN(),
                pn.getMaNCC(),
                pn.getMaNV(),
                String.valueOf(pn.getNgayNhap()),
                String.valueOf(pn.getGioNhap()),
                PriceFormatter.format(pn.getTongTien())
            });
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbTypeSearch = new javax.swing.JComboBox<>();
        txTim = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txKhoangNgay1 = new javax.swing.JTextField();
        plNgayBD = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txKhoangNgay2 = new javax.swing.JTextField();
        plNgayKT = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txKhoangTien1 = new javax.swing.JTextField();
        txKhoangTien2 = new javax.swing.JTextField();
        btnXemChiTiet = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel8 = new javax.swing.JPanel();
        txMaPN = new javax.swing.JTextField();
        txMaNCC = new javax.swing.JTextField();
        txMaNV = new javax.swing.JTextField();
        txNgayNhap = new javax.swing.JTextField();
        txGioNhap = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        rSPanelShadow1.setPreferredSize(new java.awt.Dimension(1200, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày lập", "Giờ lập", "Tổng tiền" }));
        jPanel2.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel2.add(txTim);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(260, 180));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0, 10, 5));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        txKhoangNgay1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangNgay1.setMinimumSize(new java.awt.Dimension(100, 43));
        txKhoangNgay1.setPreferredSize(new java.awt.Dimension(200, 80));
        txKhoangNgay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKhoangNgay1ActionPerformed(evt);
            }
        });
        jPanel6.add(txKhoangNgay1);

        plNgayBD.setMaximumSize(new java.awt.Dimension(54, 54));
        plNgayBD.setMinimumSize(new java.awt.Dimension(54, 54));
        plNgayBD.setPreferredSize(new java.awt.Dimension(40, 40));
        plNgayBD.setLayout(new java.awt.BorderLayout());
        jPanel6.add(plNgayBD);

        jPanel3.add(jPanel6);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        txKhoangNgay2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangNgay2.setMinimumSize(new java.awt.Dimension(100, 43));
        txKhoangNgay2.setPreferredSize(new java.awt.Dimension(200, 80));
        txKhoangNgay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKhoangNgay2ActionPerformed(evt);
            }
        });
        jPanel7.add(txKhoangNgay2);

        plNgayKT.setMaximumSize(new java.awt.Dimension(54, 54));
        plNgayKT.setMinimumSize(new java.awt.Dimension(54, 54));
        plNgayKT.setPreferredSize(new java.awt.Dimension(40, 40));
        plNgayKT.setLayout(new java.awt.BorderLayout());
        jPanel7.add(plNgayKT);

        jPanel3.add(jPanel7);

        jPanel1.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền (/triệu)"));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        txKhoangTien1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangTien1.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel4.add(txKhoangTien1);

        txKhoangTien2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangTien2.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel4.add(txKhoangTien2);

        jPanel1.add(jPanel4);

        btnXemChiTiet.setBackground(new java.awt.Color(3, 81, 145));
        btnXemChiTiet.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXemChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnXemChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/show_property_icon.png"))); // NOI18N
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.setMaximumSize(new java.awt.Dimension(150, 50));
        btnXemChiTiet.setMinimumSize(new java.awt.Dimension(150, 50));
        btnXemChiTiet.setPreferredSize(new java.awt.Dimension(150, 50));
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });
        jPanel1.add(btnXemChiTiet);

        btnLamMoi.setBackground(new java.awt.Color(3, 81, 145));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMaximumSize(new java.awt.Dimension(150, 50));
        btnLamMoi.setMinimumSize(new java.awt.Dimension(150, 50));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(150, 50));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi);

        rSPanelShadow1.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(rSPanelShadow1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        rSPanelShadow2.setPreferredSize(new java.awt.Dimension(100, 150));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        txMaPN.setEditable(false);
        txMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã phiếu nhập"));
        txMaPN.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txMaPN);

        txMaNCC.setEditable(false);
        txMaNCC.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà cung cấp"));
        txMaNCC.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txMaNCC);

        txMaNV.setEditable(false);
        txMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân viên"));
        txMaNV.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txMaNV);

        txNgayNhap.setEditable(false);
        txNgayNhap.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày nhập"));
        txNgayNhap.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txNgayNhap);

        txGioNhap.setEditable(false);
        txGioNhap.setBorder(javax.swing.BorderFactory.createTitledBorder("Giờ nhập"));
        txGioNhap.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txGioNhap);

        txTongTien.setEditable(false);
        txTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền( triệu)"));
        txTongTien.setPreferredSize(new java.awt.Dimension(160, 65));
        txTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTongTienActionPerformed(evt);
            }
        });
        jPanel8.add(txTongTien);

        rSPanelShadow2.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel5.add(rSPanelShadow2, java.awt.BorderLayout.PAGE_END);

        add(jPanel5, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setHeaders(new String[]{"STT", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Giờ nhập", "Tổng tiền"});
        mtb.setColumnsWidth(new double[]{.5, 1.6, 1.6, 1.6, 1.6, 1.5, 1.6});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(4, JLabel.CENTER);
        mtb.setAlignment(5, JLabel.CENTER);
        mtb.setAlignment(6, JLabel.RIGHT);
        mtb.setupSort();
        jPanel5.add(new JScrollPane(mtb));
    }// </editor-fold>//GEN-END:initComponents

    private void txKhoangNgay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKhoangNgay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKhoangNgay1ActionPerformed

    private void txKhoangNgay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKhoangNgay2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKhoangNgay2ActionPerformed

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        btnDetailsMouseClicked();
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTongTienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel plNgayBD;
    private javax.swing.JPanel plNgayKT;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private javax.swing.JTextField txGioNhap;
    private javax.swing.JTextField txKhoangNgay1;
    private javax.swing.JTextField txKhoangNgay2;
    private javax.swing.JTextField txKhoangTien1;
    private javax.swing.JTextField txKhoangTien2;
    private javax.swing.JTextField txMaNCC;
    private javax.swing.JTextField txMaNV;
    private javax.swing.JTextField txMaPN;
    private javax.swing.JTextField txNgayNhap;
    private javax.swing.JTextField txTim;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
