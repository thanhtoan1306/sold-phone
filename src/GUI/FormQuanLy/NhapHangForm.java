/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;


import BUS.ChiTietHoaDonService;
import BUS.ChiTietPhieuNhapService;
import BUS.HangSanPhamService;
import BUS.HoaDonService;
import BUS.KhuyenMaiService;
import BUS.NhanVienService;
import BUS.PhieuNhapService;
import BUS.SanPhamService;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import BUS.DIO.GhiPDF;
import BUS.NhaCungCapService;
import DTO.Model.ChiTietPhieuNhap;
import DTO.Model.NhaCungCap;
import DTO.Model.NhanVien;
import DTO.Model.PhieuNhap;
import DTO.Model.SanPham;
import GUI.FormChon.ChonNhaCungCap;
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
public class NhapHangForm extends FormHang {

    ChiTietPhieuNhapService qlctpn = new ChiTietPhieuNhapService();
    PhieuNhapService qlpn = new PhieuNhapService();
    SanPhamService qlsp = new SanPhamService();
    NhaCungCapService qlncc = new NhaCungCapService();
    NhanVienService qlnv = new NhanVienService();

    NhanVien nhanVien;
    NhaCungCap nhacungcap;

    MyTable tbChiTietPhieuNhap;

    ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();

    public NhapHangForm() {

        initComponents();

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaPN.setFont(f);
        txNV.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txNCC.setFont(f);
        txMaPN.setFont(f);
        txTongTien.setFont(f);

        txMaPN.setText(qlpn.getNextID());
        // set Text
        if (DangNhap.nhanVienLogin != null) {
            nhanVien = DangNhap.nhanVienLogin;
            txNV.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");            
        }


        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
                String date = LocalDate.now().toString();
                txGioLap.setText(time);
                txNgayLap.setText(date);
                if (txNCC.getText().equals("")
                        || dsctpn.isEmpty()) {
                    btnNhapHang.setEnabled(false);
                } else {
                    btnNhapHang.setEnabled(true);
                }
            }
        };
        new Timer(delay, taskPerformer).start();

        // set editable
        txMaPN.setEditable(false);
        txNV.setEditable(false);
        txNCC.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);

        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnNhapHangOnClick() {

        PhieuNhap pn = new PhieuNhap(
                txMaPN.getText(),
                nhacungcap.getMaNCC(),
                nhanVien.getMaNV(),
                LocalDate.parse(txNgayLap.getText()),
                LocalTime.parse(txGioLap.getText()),
                Float.parseFloat(txTongTien.getText()));
        qlpn.add(pn);

        for (ChiTietPhieuNhap ct : dsctpn) {
            qlctpn.add(ct);
        }

        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Nhập hàng thành công, bạn có muốn IN PHIẾU NHẬP?", "Thành công",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            new GhiPDF().writePhieuNhap(txMaPN.getText());
        }
        txMaPN.setText(qlpn.getNextID()); // lấy mã cho phiếu nhập mới
        clear();

        this.target.refreshAll();
    }

//    public void refreshTable() {
//        qlctpn.readDB();
//        setDataToTable(qlctpn.search("", "Tất cả", -1, -1, -1, -1), tbChiTietPhieuNhap);
//    }
    private void btnXoaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        if (i >= 0 && i < dsctpn.size()) {
            dsctpn.remove(i);
            setDataToTable(dsctpn, tbChiTietPhieuNhap);
        }
    }

    private void btnSuaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        if (i >= 0 && i < dsctpn.size()) {
            ChiTietPhieuNhap ct = dsctpn.get(i);
            this.target.showInfo(ct.getMaSP(), ct.getsLuong());

            dsctpn.remove(i);
            setDataToTable(dsctpn, tbChiTietPhieuNhap);
        }
    }

    public void clear() {
        txNCC.setText("");
        txTongTien.setText("");
        dsctpn.clear();
        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }

    @Override
    public void addChiTiet(String masp, int soluong) {

        SanPham sp = qlsp.getSanPham(masp);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaSP().equals(sp.getMaSP())) {
                int tongSoLuong = soluong + ctpn.getsLuong();
                ctpn.setsLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(qlpn.getNextID(), masp, soluong, sp.getDonGia());
            dsctpn.add(ctpn);
        }

        // cập nhật lại table
        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }

    public void setDataToTable(ArrayList<ChiTietPhieuNhap> arr, MyTable t) {
        t.clear();
        float tongtien = 0;
        int stt = 1;
        for (ChiTietPhieuNhap ctpn : arr) {
            String masp = ctpn.getMaSP();
            SanPham sp = qlsp.getSanPham(masp);
            String tensp = sp.getTenSP();
            int soluong = ctpn.getsLuong();
            float dongia = ctpn.getDonGia();
            float thanhtien = soluong * dongia;

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
        txTongTien.setText(String.valueOf(tongtien));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txMaPN = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txNCC = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        txNV = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txGioLap = new javax.swing.JTextField();
        txNgayLap = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnNhapHang = new javax.swing.JButton();

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

        txMaPN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txMaPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txMaPN.setMinimumSize(new java.awt.Dimension(16, 45));
        txMaPN.setPreferredSize(new java.awt.Dimension(16, 45));
        txMaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaPNActionPerformed(evt);
            }
        });
        jPanel3.add(txMaPN);

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
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        txNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txNCC.setMinimumSize(new java.awt.Dimension(200, 45));
        txNCC.setPreferredSize(new java.awt.Dimension(200, 45));
        jPanel5.add(txNCC);

        btnChonKH.setBackground(new java.awt.Color(3, 81, 145));
        btnChonKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonKH.setForeground(new java.awt.Color(255, 255, 255));
        btnChonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/supplier_icon.png"))); // NOI18N
        btnChonKH.setText("Tìm nhà cung cấp");
        btnChonKH.setToolTipText("");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });
        jPanel5.add(btnChonKH);

        jPanel7.add(jPanel5);

        txNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel7.add(txNV);

        jPanel4.add(jPanel7);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txGioLap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txGioLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txGioLap);

        txNgayLap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txNgayLap);

        jPanel9.add(jPanel10);

        jPanel4.add(jPanel9);

        add(jPanel4);

        jPanel1.setLayout(new java.awt.CardLayout());
        add(jPanel1);
        tbChiTietPhieuNhap = new MyTable();
        tbChiTietPhieuNhap.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietPhieuNhap.setPreferredSize(new Dimension(600, 600));
        tbChiTietPhieuNhap.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(1, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(3, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(4, JLabel.RIGHT);
        tbChiTietPhieuNhap.setAlignment(5, JLabel.RIGHT);

        jPanel1.add(new JScrollPane(tbChiTietPhieuNhap));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnXoa.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/delete_icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.setPreferredSize(new java.awt.Dimension(120, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoa);

        btnSua.setBackground(new java.awt.Color(3, 81, 145));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(120, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel6.add(btnSua);

        btnLamMoi.setBackground(new java.awt.Color(3, 81, 145));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setPreferredSize(new java.awt.Dimension(150, 40));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel6.add(btnLamMoi);

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

        btnNhapHang.setBackground(new java.awt.Color(255, 204, 0));
        btnNhapHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/dollar_icon.png"))); // NOI18N
        btnNhapHang.setText("NHẬP HÀNG");
        btnNhapHang.setMaximumSize(new java.awt.Dimension(191, 57));
        btnNhapHang.setMinimumSize(new java.awt.Dimension(191, 57));
        btnNhapHang.setPreferredSize(new java.awt.Dimension(191, 57));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        jPanel2.add(btnNhapHang);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        btnNhapHangOnClick();
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnSuaOnClick();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnXoaOnClick();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txMaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaPNActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        ChonNhaCungCap ckh = new ChonNhaCungCap(txNCC);
        ckh.setVisible(true);
        ckh.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String mancc = txNCC.getText();
                nhacungcap = qlncc.getNhaCungCap(mancc);
                if (nhacungcap != null) {
                    txNCC.setText(nhacungcap.getTenNCC() + " (" + nhacungcap.getMaNCC() + ")");
                }
            }
        });
    }//GEN-LAST:event_btnChonKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnSua;
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
    private javax.swing.JTextField txMaPN;
    private javax.swing.JTextField txNCC;
    private javax.swing.JTextField txNV;
    private javax.swing.JTextField txNgayLap;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration//GEN-END:variables
}
