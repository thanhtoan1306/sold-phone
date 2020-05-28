/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.formquanly;

import com.qlchdt.model.SanPham;
import com.qlchdt.service.HangSanPhamService;
import com.qlchdt.service.SanPhamService;
import com.qlchdt.service.format.MyTable;
import com.qlchdt.service.format.PriceFormatter;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
public class ChonSanPhamForm extends javax.swing.JPanel {

    SanPhamService sanphamService;
    HangSanPhamService hangSanPhamService = new HangSanPhamService();
    HoaDonBanHangForm hdbh = new HoaDonBanHangForm();    
    MyTable tbSanPham;
    
    FormHang _target = new FormHang();

  


    public ChonSanPhamForm() {

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
                    ImageIcon img = new ImageIcon(getClass().getResource("/com/qlchdt/assets/phones/"+sp.getFileNameHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblImage.setIcon(img);
                    lblImage.setIcon(new ImageIcon(imgScaled));

                    // show info
                   String loai = hangSanPhamService.getHangSanPham(sp.getMaHSP()).getTenHang();
                    txMaSP.setText(sp.getMaSP());
                    txTenSP.setText(sp.getTenSP());
                    txHSP.setText(loai + " - " + sp.getMaHSP());
                    txDonGia.setText(PriceFormatter.format(sp.getDonGia()));
                    txSoLuong.setText(String.valueOf(soluong));
                    return;
                }
            }
        }
    }
    public void setTarget(FormHang target) {
        _target = target;
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

        txTimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txMaSP = new javax.swing.JTextField();
        txTenSP = new javax.swing.JTextField();
        txSoLuong = new javax.swing.JTextField();
        txDonGia = new javax.swing.JTextField();
        txHSP = new javax.swing.JTextField();
        btnThem = new rojerusan.RSMaterialButtonRectangle();
        lblImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLamMoi = new rojerusan.RSMaterialButtonRectangle();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(506, 719));
        setPreferredSize(new java.awt.Dimension(652, 777));

        txTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTimKiemActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txMaSP.setEditable(false);
        txMaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaSPActionPerformed(evt);
            }
        });

        txTenSP.setEditable(false);
        txTenSP.setText("dasdsad");
        txTenSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N

        txSoLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N

        txDonGia.setEditable(false);
        txDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N

        txHSP.setEditable(false);
        txHSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(255, 102, 0))); // NOI18N

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txHSP, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(txDonGia)))
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txHSP, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(txMaSP))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDonGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Tìm kiếm");

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbSanPham = new MyTable();
        tbSanPham.setPreferredSize(new Dimension(1200 - 500, 600));
        tbSanPham.setHeaders(new String[]{"Mã SP", "Hãng SP", "Tên", "Đơn giá(triệu)", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.2, .1, .3, .2, .1});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbSanPham));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refreshTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTimKiemActionPerformed

    }//GEN-LAST:event_txTimKiemActionPerformed

    private void txMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
               try {
                String masp = txMaSP.getText();
                int soluong = Integer.parseInt(txSoLuong.getText());
                if (soluong > 0) {
                    this._target.addChiTiet(masp, soluong);
                   // hdbh.refreshTable();
                    
                } else {
                    JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số dương!");
                    txSoLuong.requestFocus();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số nguyên!");
                txSoLuong.requestFocus();
            }
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnLamMoi;
    private rojerusan.RSMaterialButtonRectangle btnThem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField txDonGia;
    private javax.swing.JTextField txHSP;
    private javax.swing.JTextField txMaSP;
    private javax.swing.JTextField txSoLuong;
    private javax.swing.JTextField txTenSP;
    private javax.swing.JTextField txTimKiem;
    // End of variables declaration//GEN-END:variables
}
