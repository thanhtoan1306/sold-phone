/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.HienThiForm;

import com.qlchdt.model.KhuyenMai;
import com.qlchdt.service.KhuyenMaiService;
import com.qlchdt.view.DinhDangCp.MyTable;
import com.qlchdt.view.DinhDangCp.PriceFormatter;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiKhuyenMai extends JPanel {

    KhuyenMaiService qlkm = new KhuyenMaiService();

    final int MAKM_I = 1, TENKM_I = 2;
    MyTable mtb;

    public HienThiKhuyenMai() {

        initComponents();

        //đổ dữ liệu vào table
        setDataToTable(qlkm.getDskm(), mtb);

        //set lại title tìm kiếm
        cbSearch.addActionListener((ActionEvent e) -> {
            txTimKM.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));

            txTimKM.requestFocus();
            if (!txTimKM.getText().equals("")) {
                txSearchOnChange();
            }
        });
        //tìm kiếm
        txTimKM.getDocument().addDocumentListener(new DocumentListener() {
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

    public void refresh() {
        qlkm.readDB();
        setDataToTable(qlkm.getDskm(), mtb);
    }

    private void txSearchOnChange() {
        setDataToTable(qlkm.search(txTimKM.getText(), cbSearch.getSelectedItem().toString(), -1, -1, -1, -1, null, null), mtb);
    }

    
    public String getSelectedRow(int col) {

        int i = this.mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = this.mtb.getTable().convertRowIndexToModel(i);
            return this.mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void setDataToTable(ArrayList<KhuyenMai> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại

        for (KhuyenMai km : data) {
            table.addRow(new String[]{
                String.valueOf(stt),
                km.getMaKM(),
                km.getTenKM(),
                "≥ " + PriceFormatter.format(km.getDieuKienKM()),
                String.valueOf(km.getPhanTramKM()) + " %",
                String.valueOf(km.getNgayBD()),
                String.valueOf(km.getNgayKT()),
                km.getTrangThai()
            });
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plTim = new javax.swing.JPanel();
        cbSearch = new javax.swing.JComboBox<>();
        txTimKM = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        plKhuyenMai = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        plTim.setBackground(new java.awt.Color(255, 255, 255));
        plTim.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N

        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện khuyến mãi", "Phần trăm khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc" }));

        txTimKM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tất cả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        javax.swing.GroupLayout plTimLayout = new javax.swing.GroupLayout(plTim);
        plTim.setLayout(plTimLayout);
        plTimLayout.setHorizontalGroup(
            plTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plTimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txTimKM, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );
        plTimLayout.setVerticalGroup(
            plTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plTimLayout.createSequentialGroup()
                .addGroup(plTimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel1.add(plTim);

        btnRefresh.setBackground(new java.awt.Color(3, 81, 145));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/refresh.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.setMaximumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setMinimumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setPreferredSize(new java.awt.Dimension(150, 50));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        plKhuyenMai.setLayout(new java.awt.CardLayout());
        add(plKhuyenMai, java.awt.BorderLayout.CENTER);
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT", "Mã", "Tên", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"});
        mtb.setColumnsWidth(new double[]{.5, .5, 1.5, .7, .5, 1, 1, 1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(3, JLabel.RIGHT);
        mtb.setAlignment(4, JLabel.CENTER);
        mtb.setAlignment(5, JLabel.RIGHT);
        mtb.setAlignment(6, JLabel.RIGHT);
        mtb.setupSort();
        plKhuyenMai.add(new JScrollPane(mtb));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
         refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel plKhuyenMai;
    private javax.swing.JPanel plTim;
    private javax.swing.JTextField txTimKM;
    // End of variables declaration//GEN-END:variables
}
