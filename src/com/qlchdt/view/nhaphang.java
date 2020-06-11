/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.QuanLy;

import com.qlchdt.model.ChiTietHoaDon;
import com.qlchdt.model.HoaDon;
import com.qlchdt.model.NhaCungCap;
import com.qlchdt.model.KhuyenMai;
import com.qlchdt.model.NhanVien;
import com.qlchdt.model.SanPham;
import com.qlchdt.service.ChiTietHoaDonService;
import com.qlchdt.service.HangSanPhamService;
import com.qlchdt.service.HoaDonService;
import com.qlchdt.service.NhaCungCapService;
import com.qlchdt.service.KhuyenMaiService;
import com.qlchdt.service.NhanVienService;
import com.qlchdt.service.SanPhamService;
import com.qlchdt.service.format.MyTable;
import com.qlchdt.service.format.PriceFormatter;
import com.qlchdt.service.format.WritePDF;
import com.qlchdt.view.Chon.ChonNhaCungCap;
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
public class nhaphang extends FormHang {

    SanPhamService qlsp = new SanPhamService();
    NhaCungCapService qlncc = new NhaCungCapService();
    NhanVienService qlnv = new NhanVienService();
    HoaDonService qlhd = new HoaDonService();
    ChiTietHoaDonService qlcthd = new ChiTietHoaDonService();
    KhuyenMaiService qlkm = new KhuyenMaiService();

    NhanVien nhanVien;
    NhaCungCap nhacungcap;
    KhuyenMai khuyenMai;

    MyTable tbChiTietHoaDon;

    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

    public nhaphang() {

        initComponents();

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaHD.setFont(f);
        txNV.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txNhaCC.setFont(f);
        txMaHD.setFont(f);
        txTongTien.setFont(f);

        txMaHD.setText(qlhd.getNextID());

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
                if (txNhaCC.getText().equals("")
                        || dscthd.isEmpty()) {
                    btnThanhToan.setEnabled(false);
                } else {
                    btnThanhToan.setEnabled(true);
                }
            }
        };
        new Timer(delay, taskPerformer).start();

        txNV.setText("E02");
        // set editable
        txMaHD.setEditable(false);
        txNV.setEditable(false);
        txNhaCC.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);

        setDataToTable(dscthd, tbChiTietHoaDon);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnThanhToanOnClick() {

        HoaDon hd = new HoaDon(
                txMaHD.getText(),
                "E02",
                nhacungcap.getMaNCC(),
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
            new WritePDF().writeHoaDon(txMaHD.getText());
        }
        txMaHD.setText(qlhd.getNextID());
        clear();
        this.target.refreshAll();
    }

    public void refreshTable() {
        qlcthd.readDB();
        setDataToTable(qlcthd.search("", "Tất cả", -1, -1, -1, -1), tbChiTietHoaDon);
    }

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
        txNhaCC.setText("");
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
            float dongia = cthd.getDonGia();
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
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txMaHD = new javax.swing.JTextField();
        txTongTien = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txNhaCC = new javax.swing.JTextField();
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
        btnThanhToan = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(652, 777));
        setPreferredSize(new java.awt.Dimension(652, 800));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(640, 200));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

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
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txNhaCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNhaCC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txNhaCC.setMinimumSize(new java.awt.Dimension(200, 45));
        txNhaCC.setPreferredSize(new java.awt.Dimension(200, 45));
        txNhaCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNhaCCActionPerformed(evt);
            }
        });
        jPanel5.add(txNhaCC);
        txNhaCC.getAccessibleContext().setAccessibleName("Nhà Cung Cấp");

        btnChonKH.setBackground(new java.awt.Color(153, 153, 255));
        btnChonKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_circled_user_male_30px.png"))); // NOI18N
        btnChonKH.setText("Chọn nhà cung cấp");
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
        txGioLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ Lập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txGioLap);

        txNgayLap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel10.add(txNgayLap);

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

        btnXoa.setBackground(new java.awt.Color(255, 102, 102));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.setPreferredSize(new java.awt.Dimension(120, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoa);

        btnSua.setBackground(new java.awt.Color(153, 153, 153));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_support_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(120, 40));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel6.add(btnSua);

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 255));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_refresh_30px.png"))); // NOI18N
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
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png"))); // NOI18N
        jButton1.setText("HỦY");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel2.add(jButton1);

        btnThanhToan.setBackground(new java.awt.Color(255, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_us_dollar_30px.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.setPreferredSize(new java.awt.Dimension(180, 40));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(btnThanhToan);

        add(jPanel2);
    }// </editor-fold>                        

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {                                          
        ChonNhaCungCap ckh = new ChonNhaCungCap(txNhaCC);
        ckh.setVisible(true);
        ckh.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                String mancc = txNhaCC.getText();
               nhacungcap = qlncc.getNhaCungCap(mancc);
                if (nhacungcap != null) {
                    txNhaCC.setText(nhacungcap.getTenNCC()+ " (" + nhacungcap.getMaNCC()+ ")");
                }
            }
        });
    }                                         

    private void txMaHDActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {                                             
        btnThanhToanOnClick();
    }                                            

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {                                          
         setDataToTable(dscthd, tbChiTietHoaDon);
    }                                         

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {                                       
          btnSuaOnClick();
    }                                      

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {                                       
         btnXoaOnClick();
    }                                      

    private void txNhaCCActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
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
    private javax.swing.JTextField txMaHD;
    private javax.swing.JTextField txNV;
    private javax.swing.JTextField txNgayLap;
    private javax.swing.JTextField txNhaCC;
    private javax.swing.JTextField txTongTien;
    // End of variables declaration                   
}
