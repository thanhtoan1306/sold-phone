/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;


import BUS.ChiTietHoaDonService;
import BUS.HangSanPhamService;
import BUS.HoaDonService;
import BUS.KhachHangService;
import BUS.KhuyenMaiService;
import BUS.NhanVienService;
import BUS.SanPhamService;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import BUS.DIO.GhiPDF;
import DTO.Model.ChiTietHoaDon;
import DTO.Model.HoaDon;
import DTO.Model.KhachHang;
import DTO.Model.KhuyenMai;
import DTO.Model.NhanVien;
import DTO.Model.SanPham;
import GUI.FormChon.ChonKhachHang;
import GUI.FormChon.ChonKhuyenMai;
import GUI.DangNhap;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class HoaDonBanHangForm extends FormHang {

    SanPhamService qlsp = new SanPhamService();
    KhachHangService qlkh = new KhachHangService();
    NhanVienService qlnv = new NhanVienService();
    HoaDonService qlhd = new HoaDonService();
    ChiTietHoaDonService qlcthd = new ChiTietHoaDonService();
    KhuyenMaiService qlkm = new KhuyenMaiService();

    NhanVien nhanVien;
    KhachHang khachHang;
    KhuyenMai khuyenMai;

    MyTable tbChiTietHoaDon;

    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

    public HoaDonBanHangForm() {

        initComponents();

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaHD.setFont(f);
        txNV.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txKH.setFont(f);
        txMaHD.setFont(f);
        txTongTien.setFont(f);

        txMaHD.setText(qlhd.getNextID());
             
          // set Text
        if (DangNhap.nhanVienLogin != null) {
            nhanVien = DangNhap.nhanVienLogin;
            txNV.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");
        }

        //timer.scheduleAtFixedRate(new UpdateTimeTask(), 0, 1000);
//        txNgayLap.setText(LocalDate.now().toString());
//        txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
                String date = LocalDate.now().toString();
                txGioLap.setText(time);
                txNgayLap.setText(date);
                if (txKH.getText().equals("")
                        || txKhuyenMai.getText().equals("")
                        || dscthd.isEmpty()) {
                    btnThanhToan.setEnabled(false);
                } else {
                    btnThanhToan.setEnabled(true);
                }
            }
        };
        new Timer(delay, taskPerformer).start();

        
        // set editable
        txMaHD.setEditable(false);
        txNV.setEditable(false);
        txKH.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);
        txKhuyenMai.setEditable(false);
   

        setDataToTable(dscthd, tbChiTietHoaDon);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnThanhToanOnClick() {

        HoaDon hd = new HoaDon(
                txMaHD.getText(),
                nhanVien.getMaNV(),
                khachHang.getMaKH(),
                khuyenMai.getMaKM(),
                LocalDate.parse(txNgayLap.getText()),
                LocalTime.parse(txGioLap.getText()),
                Float.parseFloat(txTongTien.getText()));
        qlhd.add(hd);
    //qlhd.update(hd);

        for (ChiTietHoaDon ct : dscthd) {
            qlcthd.add(ct);
        }

        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Thanh toán thành công, bạn có muốn IN HÓA ĐƠN?", "Thành công",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            new GhiPDF().writeHoaDon(txMaHD.getText());
        }
        txMaHD.setText(qlhd.getNextID());
        clear();
        this.target.refreshAll();
    }

//    public void refreshTable() {
//        qlcthd.readDB();
//        setDataToTable(qlcthd.search("", "Tất cả", -1, -1, -1, -1), tbChiTietHoaDon);
//    }

    private void btnXoaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        if (i >= 0 && i < dscthd.size()) {
            dscthd.remove(i);
            setDataToTable(dscthd, tbChiTietHoaDon);
        }
    }

    private void btnSuaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        if (i >= 0 && i < dscthd.size()) {
            ChiTietHoaDon ct = dscthd.get(i);
            // showInfo(ct.getMaSanPham(), ct.getSoLuong());

            dscthd.remove(i);
            setDataToTable(dscthd, tbChiTietHoaDon);
        }
    }

    public void clear() {
        txKH.setText("");
        txKhuyenMai.setText("");
        txTongTien.setText("");
        dscthd.clear();
        setDataToTable(dscthd, tbChiTietHoaDon);
    }

    @Override
    public void addChiTiet(String masp, int soluong) {

        SanPham sp = qlsp.getSanPham(masp);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietHoaDon cthd : dscthd) {
            if (cthd.getMaSanPham().equals(sp.getMaSP())) {
                int tongSoLuong = soluong + cthd.getSoLuong();
                if (tongSoLuong > sp.getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoLuong() + ")");
                    return;
                }
                cthd.setSoLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
                 
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            if (soluong > sp.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoLuong() + ")");
                return;
            }
            dscthd.add(new ChiTietHoaDon(qlhd.getNextID(), masp, soluong, sp.getDonGia()));
           

        }
        
         // cập nhật lại table
            setDataToTable(dscthd, tbChiTietHoaDon);
    }

    public void setDataToTable(ArrayList<ChiTietHoaDon> arr, MyTable t) {
        t.clear();
        float tongtien = 0;
        int stt = 1;
        for (ChiTietHoaDon cthd : arr) {
            String masp = cthd.getMaSanPham();
            SanPham sp = qlsp.getSanPham(masp);
            String tensp = sp.getTenSP();
            int soluong = cthd.getSoLuong();
            double dongia = cthd.getDonGia();
            double thanhtien = soluong * dongia;

            t.addRow(new String[]{
                String.valueOf(stt),
                masp,
                tensp,
                String.valueOf(soluong),
                PriceFormatter.format(dongia),
                PriceFormatter.format(thanhtien)
            });
            stt++;
            tongtien += thanhtien;
        }

        // check khuyến mãi
        t.addRow(new String[]{"", "", "", "", "", ""});
        t.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
        if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongtien) {
            float giaTriKhuyenMai = tongtien * khuyenMai.getPhanTramKM() / 100;
            float tongTienSauKhuyenMai = tongtien - giaTriKhuyenMai;
            t.addRow(new String[]{"", "", "", "", "Khuyến mãi", PriceFormatter.format(-giaTriKhuyenMai)});
            t.addRow(new String[]{"", "", "", "", "Còn lại", PriceFormatter.format(tongTienSauKhuyenMai)});
            txTongTien.setText(String.valueOf(tongTienSauKhuyenMai));
        } else {
            txTongTien.setText(String.valueOf(tongtien));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txMaHD = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        txNV = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txGioLap = new javax.swing.JTextField();
        txNgayLap = new javax.swing.JTextField();
        txKhuyenMai = new javax.swing.JTextField();
        btnChonKM = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(652, 777));
        setPreferredSize(new java.awt.Dimension(652, 800));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(640, 200));
        jPanel4.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(52, 50));
        jPanel3.setPreferredSize(new java.awt.Dimension(546, 50));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txMaHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txMaHD.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaHD.setMinimumSize(new java.awt.Dimension(16, 45));
        txMaHD.setPreferredSize(new java.awt.Dimension(16, 45));
        txMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaHDActionPerformed(evt);
            }
        });
        jPanel3.add(txMaHD);

        txTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tiên thanh toán(triệu)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txTongTien.setMinimumSize(new java.awt.Dimension(16, 45));
        txTongTien.setPreferredSize(new java.awt.Dimension(16, 45));
        jPanel3.add(txTongTien);

        jPanel4.add(jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 44));
        jPanel5.setLayout(new java.awt.BorderLayout());

        txKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txKH.setMaximumSize(new java.awt.Dimension(150, 60));
        txKH.setMinimumSize(new java.awt.Dimension(150, 60));
        txKH.setPreferredSize(new java.awt.Dimension(150, 60));
        jPanel5.add(txKH, java.awt.BorderLayout.CENTER);

        btnChonKH.setBackground(new java.awt.Color(3, 81, 145));
        btnChonKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonKH.setForeground(new java.awt.Color(255, 255, 255));
        btnChonKH.setText("Tìm khách hàng");
        btnChonKH.setMaximumSize(new java.awt.Dimension(140, 40));
        btnChonKH.setMinimumSize(new java.awt.Dimension(140, 40));
        btnChonKH.setPreferredSize(new java.awt.Dimension(140, 40));
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });
        jPanel5.add(btnChonKH, java.awt.BorderLayout.LINE_END);

        jPanel7.add(jPanel5);

        txNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel7.add(txNV);

        jPanel4.add(jPanel7);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txGioLap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txGioLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ Lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txGioLap);

        txNgayLap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txNgayLap);

        txKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txKhuyenMai.setPreferredSize(new java.awt.Dimension(200, 45));
        txKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel10.add(txKhuyenMai);

        btnChonKM.setBackground(new java.awt.Color(3, 81, 145));
        btnChonKM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonKM.setForeground(new java.awt.Color(255, 255, 255));
        btnChonKM.setText("Thêm khuyến mãi");
        btnChonKM.setMaximumSize(new java.awt.Dimension(140, 40));
        btnChonKM.setMinimumSize(new java.awt.Dimension(140, 40));
        btnChonKM.setPreferredSize(new java.awt.Dimension(140, 40));
        btnChonKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKMActionPerformed(evt);
            }
        });
        jPanel10.add(btnChonKM);

        jPanel9.add(jPanel10);

        jPanel4.add(jPanel9);

        add(jPanel4);

        jPanel1.setLayout(new java.awt.CardLayout());
        add(jPanel1);
        tbChiTietHoaDon = new MyTable();
        tbChiTietHoaDon.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietHoaDon.setPreferredSize(new Dimension(600, 600));
        tbChiTietHoaDon.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietHoaDon.setAlignment(0, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(1, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(3, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(4, JLabel.RIGHT);
        tbChiTietHoaDon.setAlignment(5, JLabel.RIGHT);

        jPanel1.add(new JScrollPane(tbChiTietHoaDon));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnXoa.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.setPreferredSize(new java.awt.Dimension(120, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoa);

        add(jPanel6);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 15));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/cancel_icon.png"))); // NOI18N
        jButton1.setText("HỦY");
        jButton1.setMaximumSize(new java.awt.Dimension(191, 57));
        jButton1.setMinimumSize(new java.awt.Dimension(191, 57));
        jButton1.setPreferredSize(new java.awt.Dimension(191, 57));
        jPanel2.add(jButton1);

        btnThanhToan.setBackground(new java.awt.Color(255, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/dollar_icon.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.setMaximumSize(new java.awt.Dimension(191, 57));
        btnThanhToan.setMinimumSize(new java.awt.Dimension(191, 57));
        btnThanhToan.setPreferredSize(new java.awt.Dimension(191, 57));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(btnThanhToan);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        ChonKhachHang ckh = new ChonKhachHang(txKH);
        ckh.setVisible(true);
        ckh.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String makh = txKH.getText();
                khachHang = qlkh.getKhachHang(makh);
                if (khachHang != null) {
                    txKH.setText(khachHang.getTenKH() + " (" + khachHang.getMaKH() + ")");
                }
            }
        });
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void txMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaHDActionPerformed

    private void btnChonKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKMActionPerformed
        ChonKhuyenMai ckm = new ChonKhuyenMai(txKhuyenMai);

        ckm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String makm = txKhuyenMai.getText();
                khuyenMai = qlkm.getKhuyenMai(makm);
                if (khuyenMai != null) {
                    if (!khuyenMai.getTrangThai().equals("Đang diễn ra")) {
                        JOptionPane.showMessageDialog(txKhuyenMai, "Khuyến mãi hiện " + khuyenMai.getTrangThai());
                        txKhuyenMai.setText(""); // xóa mã trong textfield
                        return;
                    }
                    txKhuyenMai.setText(khuyenMai.getTenKM() + " (" + khuyenMai.getMaKM() + ")");

                    // cập nhật lại table
                    setDataToTable(dscthd, tbChiTietHoaDon);
                }
            }
        });
    }//GEN-LAST:event_btnChonKMActionPerformed

    private void txKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txKhuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txKhuyenMaiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        btnThanhToanOnClick();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
         btnXoaOnClick();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnChonKM;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField txGioLap;
    private javax.swing.JTextField txKH;
    private javax.swing.JTextField txKhuyenMai;
    private javax.swing.JTextField txMaHD;
    private javax.swing.JTextField txNV;
    private javax.swing.JTextField txNgayLap;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
