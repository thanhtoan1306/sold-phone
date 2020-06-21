/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import BUS.HoaDonService;
import BUS.KhachHangService;
import BUS.KhuyenMaiService;
import BUS.NhanVienService;
import DTO.Model.HoaDon;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.Custom.DateButton;
import GUI.FormQuanLy.QuanLyChiTietHoaDon;
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
public class HienThiHoaDon extends javax.swing.JPanel {

    MyTable mtb;

    HoaDonService qlhd = new HoaDonService();
    NhanVienService qlnv = new NhanVienService();
    KhachHangService qlkh = new KhachHangService();
    KhuyenMaiService qlkm = new KhuyenMaiService();

    DatePicker dPickerNgayBD;
    DatePicker dPickerNgayKT;

    public HienThiHoaDon() {
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
        setDataToTable(qlhd.getDshd(), mtb);

        mtb.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String mahd = getSelectedRow(1);
                if (mahd != null) {
                    showInfo(mahd);
                }
            }
        });

        cbTypeSearch.addActionListener((ae) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(String.valueOf(cbTypeSearch.getSelectedItem())));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
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

    public void refresh() {
        qlhd.readDB();
        setDataToTable(qlhd.getDshd(), mtb);
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
        double tong1 = -1, tong2 = -1;
        
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
            tong1 = Double.parseDouble(txKhoangTien1.getText())/1000000;
            txKhoangTien1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien1.setForeground(Color.red);
        }
        
        try {
           tong2 = Double.parseDouble(txKhoangTien2.getText())/1000000;
            txKhoangTien2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txKhoangTien2.setForeground(Color.red);
        }
        setDataToTable(qlhd.search(cbTypeSearch.getSelectedItem().toString(), txTim.getText(), ngay1, ngay2, tong1, tong2), mtb);
    }

    private void showInfo(String mahd) {
        if (mahd != null) {
            // show hình
            for (HoaDon hd : qlhd.getDshd()) {
                if (hd.getMaHoaDon().equals(mahd)) {
                    // show info
                     String tennhanvien = qlnv.getNhanVien(hd.getMaNhanVien()).getTenNV();
                    String tenkhach = qlkh.getKhachHang(hd.getMaKhachHang()).getTenKH();
                    String tenkhuyenmai = qlkm.getKhuyenMai(hd.getMaKhuyenMai()).getTenKM();

                    txMaHoaDon.setText(hd.getMaHoaDon());
                    txMaNhanVien.setText(tennhanvien + " - " + hd.getMaNhanVien());
                    txMaKhachHang.setText(tenkhach + " - " + hd.getMaKhachHang());
                    txMaKhuyenMai.setText(tenkhuyenmai + " - " + hd.getMaKhuyenMai());
                    txNgayLap.setText(hd.getNgayLap().toString());
                    txGioLap.setText(hd.getGioLap().toString());
                    txTongTien.setText(PriceFormatter.format(hd.getTongTien()));
                    return;
                }
            }
        }
    }

    private void setDataToTable(ArrayList<HoaDon> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        for (HoaDon hd : data) {
            table.addRow(new String[]{
                String.valueOf(stt),
                hd.getMaHoaDon(),
                hd.getMaNhanVien(),
                hd.getMaKhachHang(),
                hd.getMaKhuyenMai(),
                String.valueOf(hd.getNgayLap()),
                String.valueOf(hd.getGioLap()),
                PriceFormatter.format(hd.getTongTien())});
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
        plNgayBD = new javax.swing.JPanel();
        txKhoangNgay1 = new javax.swing.JTextField();
        plNgayKT = new javax.swing.JPanel();
        txKhoangNgay2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txKhoangTien1 = new javax.swing.JTextField();
        txKhoangTien2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnXemChiTiet = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel8 = new javax.swing.JPanel();
        txMaHoaDon = new javax.swing.JTextField();
        txMaNhanVien = new javax.swing.JTextField();
        txMaKhachHang = new javax.swing.JTextField();
        txMaKhuyenMai = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();
        txNgayLap = new javax.swing.JTextField();
        txGioLap = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        rSPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelShadow1.setPreferredSize(new java.awt.Dimension(1200, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã hóa đơn", "Mã nhân viên", "Mã khuyến mãi", "Mã khách hàng", " " }));
        jPanel2.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txTim.setPreferredSize(new java.awt.Dimension(16, 80));
        jPanel2.add(txTim);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setMaximumSize(new java.awt.Dimension(500, 120));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 120));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 120));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        plNgayBD.setBackground(new java.awt.Color(255, 255, 255));
        plNgayBD.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 1));

        txKhoangNgay1.setEditable(false);
        txKhoangNgay1.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));
        txKhoangNgay1.setMaximumSize(new java.awt.Dimension(140, 80));
        txKhoangNgay1.setMinimumSize(new java.awt.Dimension(140, 80));
        txKhoangNgay1.setName(""); // NOI18N
        txKhoangNgay1.setPreferredSize(new java.awt.Dimension(140, 80));
        txKhoangNgay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKhoangNgay1ActionPerformed(evt);
            }
        });
        plNgayBD.add(txKhoangNgay1);

        jPanel3.add(plNgayBD);

        plNgayKT.setBackground(new java.awt.Color(255, 255, 255));
        plNgayKT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 1));

        txKhoangNgay2.setEditable(false);
        txKhoangNgay2.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));
        txKhoangNgay2.setMaximumSize(new java.awt.Dimension(140, 80));
        txKhoangNgay2.setMinimumSize(new java.awt.Dimension(140, 80));
        txKhoangNgay2.setPreferredSize(new java.awt.Dimension(140, 80));
        txKhoangNgay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKhoangNgay2ActionPerformed(evt);
            }
        });
        plNgayKT.add(txKhoangNgay2);

        jPanel3.add(plNgayKT);

        jPanel1.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Khoảng giá"));
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
        jPanel4.getAccessibleContext().setAccessibleName("Khoảng giá");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(50, 100));
        jPanel6.setMinimumSize(new java.awt.Dimension(50, 100));
        jPanel6.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel1.add(jPanel6);

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

        jButton2.setBackground(new java.awt.Color(3, 81, 145));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        rSPanelShadow1.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(rSPanelShadow1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());
        add(jPanel5, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setHeaders(new String[]{"STT", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Mã khuyến mãi", "Ngày lập", "Giờ lập", "Tổng tiền"});
        mtb.setColumnsWidth(new double[]{.5, 1, 1, 1, 1, 1, 1, 1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(7, JLabel.RIGHT);
        mtb.setupSort();
        jPanel5.add(new JScrollPane(mtb));

        rSPanelShadow2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelShadow2.setPreferredSize(new java.awt.Dimension(100, 150));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

        txMaHoaDon.setEditable(false);
        txMaHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã hóa đơn"));
        txMaHoaDon.setPreferredSize(new java.awt.Dimension(100, 65));
        jPanel8.add(txMaHoaDon);

        txMaNhanVien.setEditable(false);
        txMaNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân viên"));
        txMaNhanVien.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txMaNhanVien);

        txMaKhachHang.setEditable(false);
        txMaKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách hàng"));
        txMaKhachHang.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txMaKhachHang);

        txMaKhuyenMai.setEditable(false);
        txMaKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder("Khuyến mãi"));
        txMaKhuyenMai.setPreferredSize(new java.awt.Dimension(180, 65));
        jPanel8.add(txMaKhuyenMai);

        txTongTien.setEditable(false);
        txTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền( triệu)"));
        txTongTien.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txTongTien);

        txNgayLap.setEditable(false);
        txNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày lập"));
        txNgayLap.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txNgayLap);

        txGioLap.setEditable(false);
        txGioLap.setBorder(javax.swing.BorderFactory.createTitledBorder("Giờ lập"));
        txGioLap.setPreferredSize(new java.awt.Dimension(160, 65));
        jPanel8.add(txGioLap);

        rSPanelShadow2.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(rSPanelShadow2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txKhoangNgay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKhoangNgay2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKhoangNgay2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        String mahd = getSelectedRow(1);
        if (mahd != null) {
            QuanLyChiTietHoaDon qlcthd = new QuanLyChiTietHoaDon(mahd);
            qlcthd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để xem!");
        }
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void txKhoangNgay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKhoangNgay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKhoangNgay1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel plNgayBD;
    private javax.swing.JPanel plNgayKT;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private javax.swing.JTextField txGioLap;
    private javax.swing.JTextField txKhoangNgay1;
    private javax.swing.JTextField txKhoangNgay2;
    private javax.swing.JTextField txKhoangTien1;
    private javax.swing.JTextField txKhoangTien2;
    private javax.swing.JTextField txMaHoaDon;
    private javax.swing.JTextField txMaKhachHang;
    private javax.swing.JTextField txMaKhuyenMai;
    private javax.swing.JTextField txMaNhanVien;
    private javax.swing.JTextField txNgayLap;
    private javax.swing.JTextField txTim;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
