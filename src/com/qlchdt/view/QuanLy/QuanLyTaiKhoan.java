/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.QuanLy;

import com.qlchdt.model.NhanVien;
import com.qlchdt.model.TaiKhoan;
import com.qlchdt.service.NhanVienService;
import com.qlchdt.service.TaiKhoanService;
import com.qlchdt.service.format.MyTable;
import com.qlchdt.view.custom.DateLabelFormatter;
import java.awt.Dimension;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author User
 */
public class QuanLyTaiKhoan extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    TaiKhoanService taikhoanService;
    MyTable tbTaiKhoan;
    TaiKhoan TK_them;
    //JDatePickerImpl datePicker;
    //Path nhanVienImagePath;
    //Path imageLocation;

    public QuanLyTaiKhoan() {
        //super("Quản Lí Nhân Viên");
        initComponents();

        /*URL url = this.getClass().getResource("/com/qlchdt/assets/employees");
        try {
            nhanVienImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        taikhoanService = new TaiKhoanService();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setDataToTable(taikhoanService.getDstk(), tbTaiKhoan);
        tbTaiKhoan.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String manv = getSelectedTaiKhoan(2);
                if (manv != null) {
                    showInfo(manv);
                }
            }
        });
        addDocumentListener(txtTimKiem);

        refreshTable();
    }

    private void setDataToTable(ArrayList<TaiKhoan> data, MyTable table) {
        table.clear();
        for (TaiKhoan tk : data) {
            table.addRow(new String[]{
                tk.getTentk(),
                tk.getMk(),
                tk.getManv(),
                tk.getMaquyen(),
                });
        }
    }

    public void refreshTable() {
        taikhoanService.readDB();
        setDataToTable(taikhoanService.search("", "Tất cả", null, null), tbTaiKhoan);
    }

    public void refreshAll() {
        //refreshTable();
        txtTentk.setText("");
        txtMatkhau.setText("");
        txtMaquyen.setText("");
        txtManv.setText("");
    }

    public void showInfo(String manv) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (manv != null) {
            // show hình
            for (TaiKhoan tk : taikhoanService.getDstk()) {
                if (tk.getManv().equals(manv)) {
                    // show info
                    txtTentk.setText(tk.getTentk());
                    txtMatkhau.setText(tk.getMk());
                    
                    txtMaquyen.setText(tk.getMaquyen());
                    txtManv.setText(tk.getManv());

                    return;
                }
            }
        }
    }

    public String getSelectedTaiKhoan(int col) {
        int i = tbTaiKhoan.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbTaiKhoan.getTable().convertRowIndexToModel(i);
            return tbTaiKhoan.getModel().getValueAt(realI, col).toString();
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
        setDataToTable(taikhoanService.search(txtTimKiem.getText(), "Tất cả", null, null), tbTaiKhoan);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        hienThiTT = new javax.swing.JPanel();
        NhanVienInfo = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTentk = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtMatkhau = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaquyen = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        ThaoTac = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new rojerusan.RSMaterialButtonRectangle();
        jPanel2 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1200, 770));
        setMinimumSize(new java.awt.Dimension(1200, 770));
        setPreferredSize(new java.awt.Dimension(1200, 770));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 770));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 770));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 770));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(176, 196, 229));
        jPanel6.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NHÂN VIÊN");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));
        jPanel6.add(jLabel1);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        jPanel5.setMinimumSize(new java.awt.Dimension(1200, 500));
        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 500));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        hienThiTT.setBackground(new java.awt.Color(255, 255, 255));
        hienThiTT.setLayout(new java.awt.BorderLayout(0, 10));

        NhanVienInfo.setBackground(new java.awt.Color(255, 255, 255));
        NhanVienInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        NhanVienInfo.setToolTipText("Thông Tin Nhân Viên");
        NhanVienInfo.setMaximumSize(new java.awt.Dimension(600, 450));
        NhanVienInfo.setMinimumSize(new java.awt.Dimension(600, 450));
        NhanVienInfo.setPreferredSize(new java.awt.Dimension(800, 450));
        NhanVienInfo.setLayout(new java.awt.GridLayout(1, 0));

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel7.setName(""); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Tên tài khoản");
        jPanel7.add(jLabel3);

        txtTentk.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTentk.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTentk.setMaximumSize(new java.awt.Dimension(180, 50));
        txtTentk.setMinimumSize(new java.awt.Dimension(180, 50));
        txtTentk.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel7.add(txtTentk);

        jPanel9.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel10.setName(""); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setText("Mật khẩu");
        jPanel10.add(jLabel10);

        txtMatkhau.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtMatkhau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMatkhau.setMaximumSize(new java.awt.Dimension(180, 50));
        txtMatkhau.setMinimumSize(new java.awt.Dimension(180, 50));
        txtMatkhau.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel10.add(txtMatkhau);

        jPanel9.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel11.setName(""); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 60));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel11.setLayout(flowLayout1);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 0));
        jLabel11.setText("Mã nhân viên");
        jPanel11.add(jLabel11);

        txtManv.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtManv.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtManv.setMaximumSize(new java.awt.Dimension(180, 50));
        txtManv.setMinimumSize(new java.awt.Dimension(180, 50));
        txtManv.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel11.add(txtManv);

        jPanel9.add(jPanel11);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel13.setName(""); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Mã quyền");
        jPanel13.add(jLabel7);

        txtMaquyen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtMaquyen.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMaquyen.setMaximumSize(new java.awt.Dimension(180, 50));
        txtMaquyen.setMinimumSize(new java.awt.Dimension(180, 50));
        txtMaquyen.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel13.add(txtMaquyen);

        jPanel9.add(jPanel13);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel12.setName(""); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanel9.add(jPanel12);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel16.setName(""); // NOI18N
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanel9.add(jPanel16);

        jPanel8.add(jPanel9);

        NhanVienInfo.add(jPanel8);

        hienThiTT.add(NhanVienInfo, java.awt.BorderLayout.CENTER);

        ThaoTac.setBackground(new java.awt.Color(255, 255, 255));
        ThaoTac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        ThaoTac.setToolTipText("Thông Tin Nhân Viên");
        ThaoTac.setMaximumSize(new java.awt.Dimension(800, 130));
        ThaoTac.setMinimumSize(new java.awt.Dimension(800, 130));
        ThaoTac.setPreferredSize(new java.awt.Dimension(800, 130));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_add_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(140, 40));
        btnThem.setMinimumSize(new java.awt.Dimension(140, 40));
        btnThem.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        ThaoTac.add(btnThem);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(140, 40));
        btnXoa.setMinimumSize(new java.awt.Dimension(140, 40));
        btnXoa.setPreferredSize(new java.awt.Dimension(140, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        ThaoTac.add(btnXoa);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(140, 40));
        btnSua.setMinimumSize(new java.awt.Dimension(140, 40));
        btnSua.setPreferredSize(new java.awt.Dimension(140, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        ThaoTac.add(btnSua);

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setMaximumSize(new java.awt.Dimension(140, 40));
        btnLuu.setMinimumSize(new java.awt.Dimension(140, 40));
        btnLuu.setPreferredSize(new java.awt.Dimension(140, 40));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        ThaoTac.add(btnLuu);

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setMaximumSize(new java.awt.Dimension(140, 40));
        btnHuy.setMinimumSize(new java.awt.Dimension(140, 40));
        btnHuy.setPreferredSize(new java.awt.Dimension(140, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        ThaoTac.add(btnHuy);

        hienThiTT.add(ThaoTac, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(hienThiTT);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 0));
        jLabel9.setText("Tìm kiếm");
        jPanel4.add(jLabel9);

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTimKiem.setMaximumSize(new java.awt.Dimension(300, 60));
        txtTimKiem.setMinimumSize(new java.awt.Dimension(300, 60));
        txtTimKiem.setPreferredSize(new java.awt.Dimension(300, 60));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        jPanel4.add(txtTimKiem);

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMaximumSize(new java.awt.Dimension(140, 60));
        btnLamMoi.setMinimumSize(new java.awt.Dimension(140, 60));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(140, 60));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel4.add(btnLamMoi);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);
        tbTaiKhoan = new MyTable();
        tbTaiKhoan.setPreferredSize(new Dimension(600-250, 345));
        tbTaiKhoan.setHeaders(new String[]{"Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"});
        tbTaiKhoan.setColumnsWidth(new double[]{.1, .07, .1, .05});
        tbTaiKhoan.setAlignment(3, JLabel.RIGHT);
        tbTaiKhoan.setAlignment(2, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbTaiKhoan));

        jPanel5.add(jPanel3);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refreshTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbTaiKhoan.getModel();
        String manv = txtManv.getText().trim();
        if (manv.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Không thể thêm vào bảng, Mã Nhân viên chưa nhập!");
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(manv)) {
                JOptionPane.showMessageDialog(null, "Không thể thêm vào bảng, trùng Mã Nhân viên đã có sẵn!");
                return;
            }
        }

        String tentk = txtTentk.getText().trim();
        String matkhau = txtMatkhau.getText().trim();
        String maquyen = txtMaquyen.getText().trim();

        Object os[] = {tentk, matkhau, manv, maquyen};
        model.addRow(os);
        TK_them = new TaiKhoan(tentk, matkhau, manv, maquyen);
        taikhoanService.add(tentk, matkhau, manv, maquyen);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = -1;
        row = tbTaiKhoan.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn hàng!");
        } else {
            taikhoanService.delete(tbTaiKhoan.getTable().getValueAt(row, 0).toString());
            tbTaiKhoan.getModel().removeRow(row);
            JOptionPane.showMessageDialog(null, "Xoá hàng thành công!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
          // TODO add your handling code here:
        //DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        String tentk = txtTentk.getText().trim();
        String matkhau = txtMatkhau.getText().trim();
        String manv = txtManv.getText().trim();
        String maquyen = txtMaquyen.getText().trim();
        
        Object os[] = {tentk, matkhau, manv, maquyen};
        if (taikhoanService.update(tentk, matkhau, manv, maquyen)) {
            JOptionPane.showMessageDialog(null, "Sửa thành công !");
            refreshTable();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
          // TODO add your handling code here:
        if (TK_them == null) {
            JOptionPane.showMessageDialog(null, "Chưa có nhân viên nào mới thêm vào bảng. Lưu vào database thất bại.");
            return;
        }
        if (!taikhoanService.saveToDatabase(TK_them)) {
            JOptionPane.showMessageDialog(null, "Lưu vào Database thất bại!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Lưu vào Database thành công!");
        TK_them = null;
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
          // TODO add your handling code here:
        this.txtTentk.setText("");
        this.txtMatkhau.setText("");
        this.txtManv.setText("");
        this.txtMaquyen.setText("");

    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVienInfo;
    private javax.swing.JPanel ThaoTac;
    private javax.swing.JButton btnHuy;
    private rojerusan.RSMaterialButtonRectangle btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JPanel hienThiTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtMaquyen;
    private javax.swing.JTextField txtMatkhau;
    private javax.swing.JTextField txtTentk;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
