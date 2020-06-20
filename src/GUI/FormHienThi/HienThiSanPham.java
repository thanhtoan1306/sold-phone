/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormHienThi;

import BUS.HangSanPhamService;
import BUS.SanPhamService;
import DTO.Model.SanPham;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.FormQuanLy.FormHang;
import GUI.FormQuanLy.HoaDonBanHangForm;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author User
 */
public class HienThiSanPham extends javax.swing.JPanel {

    SanPhamService sanphamService;
    HangSanPhamService hangSanPhamService = new HangSanPhamService();
    HoaDonBanHangForm hdbh = new HoaDonBanHangForm();
    MyTable tbSanPham;

    public HienThiSanPham() {
        initComponents();
        sanphamService = new SanPhamService();

        setDataToTable(sanphamService.getDssp(), tbSanPham);

        tbSanPham.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String masp = getSelectedSanPham(0);
                if (masp != null) {
                    showInfo(masp, 1);
                }
            }
        });
        addDocumentListener(txTimKiem);
        refreshAll();
    }

    private void setDataToTable(ArrayList<SanPham> data, MyTable table) {
        table.clear();
        for (SanPham sp : data) {

            table.addRow(new String[]{
                sp.getMaSP(),
                sp.getMaHSP(),
                sp.getTenSP(),
                PriceFormatter.format(sp.getDonGia()),
                String.valueOf(sp.getSoLuong()),});

        }
    }

    public void refreshTable() {
        sanphamService.readDB();
        setDataToTable(sanphamService.search("", "Tất cả", -1, -1, -1, -1), tbSanPham);
    }

    public void refreshAll() {
        refreshTable();
        txMaSP.setText("");
        txHSP.setText("");
        txTenSP.setText("");
        txDonGia.setText("");
        txSoLuong.setText("");
        lblImage.setIcon(null);
    }



    public void showInfo(String masp, int soluong) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (masp != null) {
            // show hình
            for (SanPham sp : sanphamService.getDssp()) {
                if (sp.getMaSP().equals(masp)) {
                    int w = lblImage.getWidth();
                    int h = lblImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Products/" + sp.getFileNameHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblImage.setIcon(img);
                    lblImage.setIcon(new ImageIcon(imgScaled));

                    // show info
                    String loai = hangSanPhamService.getHangSanPham(sp.getMaHSP()).getTenHang();
                    txMaSP.setText(sp.getMaSP());
                    txTenSP.setText(sp.getTenSP());
                    txHSP.setText(loai + " - " + sp.getMaHSP());
                    txDonGia.setText(PriceFormatter.format(sp.getDonGia()));
                    txSoLuong.setText(String.valueOf((sp.getSoLuong())));
                    return;
                }
            }
        }
    }

    public String getSelectedSanPham(int col) {
        int i = tbSanPham.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbSanPham.getTable().convertRowIndexToModel(i);
            return tbSanPham.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void addDocumentListener(JTextField tx) {
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
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

    public void txSearchOnChange() {
        setDataToTable(sanphamService.search(txTimKiem.getText(), "Tất cả", -1, -1, -1, -1), tbSanPham);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txTimKiem = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txMaSP = new javax.swing.JTextField();
        txHSP = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txDonGia = new javax.swing.JTextField();
        txTenSP = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txSoLuong = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1200, 900));
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(506, 719));
        jPanel1.setPreferredSize(new java.awt.Dimension(652, 777));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setMaximumSize(new java.awt.Dimension(600, 80));
        jPanel9.setMinimumSize(new java.awt.Dimension(600, 80));
        jPanel9.setPreferredSize(new java.awt.Dimension(600, 80));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 102, 0))); // NOI18N
        txTimKiem.setMaximumSize(new java.awt.Dimension(300, 70));
        txTimKiem.setMinimumSize(new java.awt.Dimension(300, 70));
        txTimKiem.setPreferredSize(new java.awt.Dimension(300, 70));
        txTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTimKiemActionPerformed(evt);
            }
        });
        jPanel3.add(txTimKiem);

        btnRefresh.setBackground(new java.awt.Color(3, 81, 145));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.setMaximumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setMinimumSize(new java.awt.Dimension(150, 50));
        btnRefresh.setPreferredSize(new java.awt.Dimension(150, 50));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel3.add(btnRefresh);

        jPanel9.add(jPanel3);

        jPanel1.add(jPanel9);

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel1.add(jPanel2);
        tbSanPham = new MyTable();
        tbSanPham.setPreferredSize(new Dimension(1200 - 500, 600));
        tbSanPham.setHeaders(new String[]{"Mã SP", "Hãng SP", "Tên", "Đơn giá(triệu)", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.2, .1, .3, .2, .1});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbSanPham));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(10, 20));
        jPanel11.setMinimumSize(new java.awt.Dimension(10, 20));
        jPanel11.setPreferredSize(new java.awt.Dimension(10, 20));
        jPanel1.add(jPanel11);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel12.setMaximumSize(new java.awt.Dimension(250, 280));
        jPanel12.setMinimumSize(new java.awt.Dimension(250, 280));
        jPanel12.setPreferredSize(new java.awt.Dimension(250, 280));
        jPanel12.setLayout(new java.awt.BorderLayout());

        lblImage.setPreferredSize(new java.awt.Dimension(200, 240));
        jPanel12.add(lblImage, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12);

        jPanel4.add(jPanel10);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(3, 0, 0, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(52, 60));
        jPanel5.setPreferredSize(new java.awt.Dimension(52, 60));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txMaSP.setEditable(false);
        txMaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txMaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaSP.setMaximumSize(new java.awt.Dimension(250, 60));
        txMaSP.setMinimumSize(new java.awt.Dimension(250, 60));
        txMaSP.setPreferredSize(new java.awt.Dimension(250, 60));
        txMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaSPActionPerformed(evt);
            }
        });
        jPanel5.add(txMaSP);

        txHSP.setEditable(false);
        txHSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txHSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txHSP.setMaximumSize(new java.awt.Dimension(250, 60));
        txHSP.setMinimumSize(new java.awt.Dimension(250, 60));
        txHSP.setPreferredSize(new java.awt.Dimension(250, 60));
        jPanel5.add(txHSP);

        jPanel8.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(52, 60));
        jPanel6.setPreferredSize(new java.awt.Dimension(52, 60));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 20, 40));

        txDonGia.setEditable(false);
        txDonGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel6.add(txDonGia);

        txTenSP.setEditable(false);
        txTenSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txTenSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel6.add(txTenSP);

        jPanel8.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        txSoLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txSoLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txSoLuong.setMaximumSize(new java.awt.Dimension(250, 60));
        txSoLuong.setMinimumSize(new java.awt.Dimension(250, 60));
        txSoLuong.setPreferredSize(new java.awt.Dimension(250, 60));
        txSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSoLuongActionPerformed(evt);
            }
        });
        jPanel7.add(txSoLuong, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel7);

        jPanel4.add(jPanel8);

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTimKiemActionPerformed

    }//GEN-LAST:event_txTimKiemActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        setDataToTable(sanphamService.getDssp(), tbSanPham);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaSPActionPerformed

    private void txSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txSoLuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField txDonGia;
    private javax.swing.JTextField txHSP;
    private javax.swing.JTextField txMaSP;
    private javax.swing.JTextField txSoLuong;
    private javax.swing.JTextField txTenSP;
    private javax.swing.JTextField txTimKiem;
    // End of variables declaration//GEN-END:variables
}
