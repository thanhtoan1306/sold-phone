/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;


import BUS.KhuyenMaiService;
import BUS.DIO.NhapExcel;
import BUS.DIO.XuatExcel;
import DTO.Model.KhuyenMai;
import GUI.DangNhap;
import GUI.FormHienThi.HienThiKhuyenMai;
import GUI.FormThemSua.ThemSuaKhachHang;
import GUI.FormThemSua.ThemSuaKhuyenMai;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class QuanLyKhuyenMai extends javax.swing.JPanel {

      HienThiKhuyenMai formHienThi = new HienThiKhuyenMai();
      
    
    
    public QuanLyKhuyenMai() {
        initComponents();
             if (!DangNhap.quyenLogin.getChiTietQuyen().contains("qlKhuyenMai")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnKetThuc.setEnabled(false);
            btnNhapExcel.setEnabled(false);

        }
        plKhuyenMai.add(formHienThi);
        
    }

      private void btnSuaMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {
            ThemSuaKhuyenMai suakm = new ThemSuaKhuyenMai("Sửa", makm);

            
            suakm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa khuyến mãi " + makm,
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new KhuyenMaiService().delete(makm);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để xóa");
        }
    }

    private void btnKetThucMouseClicked() {
        String makm = formHienThi.getSelectedRow(1);
        if (makm != null) {

            // check xem khuyến mãi có đang diễn ra ko
            String trangthai = new KhuyenMaiService().getKhuyenMai(makm).getTrangThai();
            Boolean dangDienRa = trangthai.equals("Đang diễn ra");

            if (!dangDienRa) {
                JOptionPane.showMessageDialog(this, "Không thể dừng khuyến mãi " + trangthai);
                return;
            }

            // check đồng ý kết thúc
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn dừng khuyến mãi " + makm
                    + " ? Ngày kết thúc Khuyến mãi sẽ được dời về hôm nay",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                KhuyenMaiService qlkm = new KhuyenMaiService();
                KhuyenMai km = qlkm.getKhuyenMai(makm);
                qlkm.update(km.getMaKM(), km.getTenKM(), km.getDieuKienKM(), km.getPhanTramKM(), km.getNgayBD(), LocalDate.now());

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn khuyến mãi nào để dừng");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaKhuyenMai themkh = new ThemSuaKhuyenMai("Thêm", "");
        themkh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        plKhuyenMai = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 900));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new java.awt.BorderLayout());

        plKhuyenMai.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(695, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(1108, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        btnThem.setBackground(new java.awt.Color(3, 81, 145));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/add_icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(150, 50));
        btnThem.setMinimumSize(new java.awt.Dimension(150, 50));
        btnThem.setPreferredSize(new java.awt.Dimension(150, 50));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnXoa.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/delete_icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(150, 50));
        btnXoa.setMinimumSize(new java.awt.Dimension(150, 50));
        btnXoa.setPreferredSize(new java.awt.Dimension(150, 50));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        btnSua.setBackground(new java.awt.Color(3, 81, 145));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(150, 50));
        btnSua.setMinimumSize(new java.awt.Dimension(150, 50));
        btnSua.setPreferredSize(new java.awt.Dimension(150, 50));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnKetThuc.setBackground(new java.awt.Color(3, 81, 145));
        btnKetThuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/cancel_icon.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setMaximumSize(new java.awt.Dimension(150, 50));
        btnKetThuc.setMinimumSize(new java.awt.Dimension(150, 50));
        btnKetThuc.setPreferredSize(new java.awt.Dimension(150, 50));
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jPanel1.add(btnKetThuc);

        btnNhapExcel.setBackground(new java.awt.Color(3, 81, 145));
        btnNhapExcel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNhapExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/excel_icon.png"))); // NOI18N
        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.setMaximumSize(new java.awt.Dimension(150, 50));
        btnNhapExcel.setMinimumSize(new java.awt.Dimension(150, 50));
        btnNhapExcel.setPreferredSize(new java.awt.Dimension(150, 50));
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhapExcel);

        btnXuatExcel.setBackground(new java.awt.Color(3, 81, 145));
        btnXuatExcel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/excel_icon.png"))); // NOI18N
        btnXuatExcel.setText("Xuất Excel");
        btnXuatExcel.setMaximumSize(new java.awt.Dimension(150, 50));
        btnXuatExcel.setMinimumSize(new java.awt.Dimension(150, 50));
        btnXuatExcel.setPreferredSize(new java.awt.Dimension(150, 50));
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnXuatExcel);

        plKhuyenMai.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(plKhuyenMai, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 196, 229)));
        jPanel3.setMaximumSize(new java.awt.Dimension(1200, 50));
        jPanel3.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHUYẾN MÃI");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));
        jPanel3.add(jLabel1);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
       btnXoaMouseClicked();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
       btnKetThucMouseClicked();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        new NhapExcel().docFileExcelKhuyenMai();
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        new XuatExcel().xuatFileExcelKhuyenMai();
    }//GEN-LAST:event_btnXuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel plKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
