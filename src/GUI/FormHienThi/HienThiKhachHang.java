/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import GUI.FormThemSua.ThemSuaKhachHang;
import BUS.KhachHangService;
import DTO.Model.KhachHang;
import GUI.Custom.MyTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
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
public class HienThiKhachHang extends javax.swing.JPanel {

    KhachHangService khachHangService;
    MyTable mtb;
    

    public HienThiKhachHang() {
        initComponents();
        
        khachHangService = new KhachHangService();
        setDataToTable(khachHangService.getDskh(), mtb);

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
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public void refresh() {
        khachHangService.readDB();
        setDataToTable(khachHangService.getDskh(), mtb);
    }

    private void txSearchOnChange() {
        setDataToTable(khachHangService.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), mtb);
    }

    private void setDataToTable(ArrayList<KhachHang> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        //Boolean hienKhachHangAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlKhachHang");

        for (KhachHang kh : data) {
            table.addRow(new String[]{
                String.valueOf(stt),
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getDiaChi(),
                kh.getSDT(),});
            stt++;
        }
    }

//    //xóa
//    private void btnXoaMouseClicked() {
//        String makh = getSelectedRow(1);
//        if (makh != null) {
//            KhachHangService khachHangService = new KhachHangService();
//            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn khách hàng " + makh + " ?",
//                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
//                khachHangService.delete(makh);
//                refresh();
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để xóa");
//        }
//    }
//    //sửa
//
//    private void btnSuaMouseClicked() {
//        String makh = getSelectedRow(1);
//        if (makh != null) {
//            ThemSuaKhachHang suakh = new ThemSuaKhachHang("Sửa", makh);
//
//            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
//            suakh.addWindowListener(new java.awt.event.WindowAdapter() {
//                @Override
//                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                    refresh();
//                }
//            });
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng nào để sửa");
//        }
//    }
//
//    //thêm
//    private void btnThemMouseClicked() {
//        ThemSuaKhachHang themkh = new ThemSuaKhachHang("Thêm", "");
//        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                refresh();
//            }
//        });
//        System.out.println("Đã nhấn nút thêm ");
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        cbTypeSearch = new javax.swing.JComboBox<>();
        txTim = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
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

        cbTypeSearch.setBackground(new java.awt.Color(153, 153, 153));
        cbTypeSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbTypeSearch.setForeground(new java.awt.Color(255, 255, 255));
        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã KH", "Tên KH", "Địa chỉ", "Số điện thoại" }));
        cbTypeSearch.setMaximumSize(new java.awt.Dimension(150, 40));
        cbTypeSearch.setMinimumSize(new java.awt.Dimension(150, 40));
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

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
        );

        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT","Mã Kh", "Tên KH","Địa chỉ", "Số điện thoại"});
        mtb.setColumnsWidth(new double[]{.5,.5, 1.5, 1.5, 2});
        mtb.setAlignment(2, JLabel.LEFT);
        mtb.setAlignment(3, JLabel.LEFT);
        mtb.setupSort();
        jPanel6.add(new JScrollPane(mtb));

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
