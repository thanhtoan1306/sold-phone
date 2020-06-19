/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import BUS.QuyenService;
import BUS.TaiKhoanService;
import DTO.Model.TaiKhoan;
import GUI.Custom.MyTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiTaiKhoan extends javax.swing.JPanel {

    TaiKhoanService tks = new TaiKhoanService();

    // index
    //final int MAQUYEN_I = 1, CHITIET_I = 2;

    MyTable mtb;

    public HienThiTaiKhoan() {
        initComponents();
        setDataToTable(tks.getDstk(), mtb);
        
        
        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
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
        tks.readDB();
        setDataToTable(tks.getDstk(), mtb);
    }

    private void txSearchOnChange() {
        //setDataToTable(tks.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), mtb);
        setDataToTable(tks.search(txTim.getText(), "Tất cả", null, null), mtb);
    }

    private void setDataToTable(ArrayList<TaiKhoan> data, MyTable mtb) {
        mtb.clear();
        int stt = 0; // lưu số thứ tự dòng hiện tại
        for (TaiKhoan tk : data) {
            mtb.addRow(new String[]{
                //String.valueOf(stt),
                tk.getTentk(),
                tk.getMk(),
                tk.getManv(),
                tk.getMaquyen()});
            //stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cbTypeSearch = new javax.swing.JComboBox<>();
        txTim = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quyền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 200));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 150));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel4.setMinimumSize(new java.awt.Dimension(600, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 100));

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền", " ", " " }));
        cbTypeSearch.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(cbTypeSearch);

        txTim.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txTim.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(txTim);

        jPanel2.add(jPanel4);

        jButton1.setBackground(new java.awt.Color(3, 81, 145));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());
        add(jPanel5, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"});
        mtb.setColumnsWidth(new double[]{.5, 2, 2, 2});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();

        jPanel5.add(new JScrollPane(mtb));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txTim;
    // End of variables declaration//GEN-END:variables
}
