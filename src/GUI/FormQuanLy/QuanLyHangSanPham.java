/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;


import BUS.HangSanPhamService;
import BUS.SanPhamService;
import DTO.Model.HangSanPham;
import DTO.Model.SanPham;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.DangNhap;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QuanLyHangSanPham extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    SanPhamService sanphamService;
    HangSanPhamService hangSanPhamService;
    //HoaDonBanHangForm hdbh = new HoaDonBanHangForm();    
    MyTable tbHSP;
    MyTable tbSanPham;
    HangSanPham HSP_them;

    public QuanLyHangSanPham() {
        initComponents();
        // buttons
        if (!DangNhap.quyenLogin.getChiTietQuyen().contains("qlHangSanPham")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
//            btnSua.setEnabled(false);

        }
        hangSanPhamService = new HangSanPhamService();
        sanphamService = new SanPhamService();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setDataToTable1(this.hangSanPhamService.getDshsp(), tbHSP);
        setDataToTable(this.sanphamService.getDssp(), tbSanPham);
        tbHSP.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String mahsp = getSelectedSanPham(0);
                if (mahsp != null) {
                    showInfo(mahsp);
                }
            }
        });
        //addDocumentListener(txtTimKiem);

        refreshTable();
    }

    /* public QuanLySanPham() {

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
        //addDocumentListener(txTimKiem);
        refreshAll();
                 

    }*/
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

    private void setDataToTable1(ArrayList<HangSanPham> data, MyTable table) {
        table.clear();
        for (HangSanPham hsp : data) {

            table.addRow(new String[]{
                hsp.getMaHang(),
                hsp.getTenHang(),});

        }
    }

    public void refreshTable() {
        sanphamService.readDB();
        setDataToTable(sanphamService.search("", "Tất cả", -1, -1, -1, -1), tbSanPham);
        hangSanPhamService.readDB();
        setDataToTable1(hangSanPhamService.getDshsp(), tbHSP);
    }

    public void refreshAll() {
        refreshTable();
        txtMaHSP.setText("");
        txtTenHSP.setText("");

    }

    public void showInfo(String mahsp) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (mahsp != null) {
            // show hình
            for (HangSanPham hsp : hangSanPhamService.getDshsp()) {
                if (hsp.getMaHang().equals(mahsp)) {
                    // show info
                    txtMaHSP.setText(hsp.getMaHang());
                    txtTenHSP.setText(hsp.getTenHang());
                    return;
                }
            }
        }
    }

    public String getSelectedSanPham(int col) {
        int i = tbHSP.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbHSP.getTable().convertRowIndexToModel(i);
            return tbHSP.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    /* public void txSearchOnChange() {
        setDataToTable(sanphamService.search(txtTimKiem.getText(), "Tất cả",null,null), tbSanPham);
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
    }*/
 /* private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {                                        
               try {
                String masp = txtMaSP.getText();
                int soluong = Integer.parseInt(txtSoLuong.getText());
                if (soluong > 0) {
                    this._target.addChiTiet(masp, soluong);
                   // hdbh.refreshTable();
                    
                } else {
                    JOptionPane.showMessageDialog(txtSoLuong, "Số lượng phải là số dương!");
                    txtSoLuong.requestFocus();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(txtSoLuong, "Số lượng phải là số nguyên!");
                txtSoLuong.requestFocus();
            }
    }    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        hienThiTT = new javax.swing.JPanel();
        NhanVienInfo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtMaHSP = new javax.swing.JTextField();
        txtTenHSP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelHSP = new javax.swing.JPanel();
        ThaoTac = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1200, 770));
        setMinimumSize(new java.awt.Dimension(1200, 770));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1220, 770));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 770));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 770));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 770));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 196, 229)));
        jPanel6.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÃNG SẢN PHẨM");
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
        NhanVienInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        NhanVienInfo.setToolTipText("Hãng sản phẩm");
        NhanVienInfo.setMaximumSize(new java.awt.Dimension(600, 450));
        NhanVienInfo.setMinimumSize(new java.awt.Dimension(600, 450));
        NhanVienInfo.setPreferredSize(new java.awt.Dimension(800, 450));
        NhanVienInfo.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));

        txtMaHSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hãng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        txtMaHSP.setMaximumSize(new java.awt.Dimension(140, 60));
        txtMaHSP.setMinimumSize(new java.awt.Dimension(140, 60));
        txtMaHSP.setPreferredSize(new java.awt.Dimension(140, 60));
        txtMaHSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHSPActionPerformed(evt);
            }
        });
        jPanel4.add(txtMaHSP);

        txtTenHSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên hãng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        txtTenHSP.setMaximumSize(new java.awt.Dimension(140, 60));
        txtTenHSP.setMinimumSize(new java.awt.Dimension(140, 60));
        txtTenHSP.setPreferredSize(new java.awt.Dimension(140, 60));
        txtTenHSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHSPActionPerformed(evt);
            }
        });
        jPanel4.add(txtTenHSP);

        jButton1.setBackground(new java.awt.Color(3, 81, 145));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        NhanVienInfo.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        panelHSP.setLayout(new java.awt.CardLayout());
        NhanVienInfo.add(panelHSP, java.awt.BorderLayout.CENTER);
        tbHSP = new MyTable();
        tbHSP.setPreferredSize(new Dimension(500, 600));
        tbHSP.setHeaders(new String[]{"Mã HSP", "Hãng SP"});
        tbHSP.setColumnsWidth(new double[]{.1, .1});
        /*tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);*/
        panelHSP.add(new JScrollPane(tbHSP));

        hienThiTT.add(NhanVienInfo, java.awt.BorderLayout.CENTER);

        ThaoTac.setBackground(new java.awt.Color(255, 255, 255));
        ThaoTac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        ThaoTac.setToolTipText("Thông Tin Nhân Viên");
        ThaoTac.setMaximumSize(new java.awt.Dimension(800, 130));
        ThaoTac.setMinimumSize(new java.awt.Dimension(800, 130));
        ThaoTac.setPreferredSize(new java.awt.Dimension(800, 130));

        btnThem.setBackground(new java.awt.Color(3, 81, 145));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/add_icon.png"))); // NOI18N
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

        btnXoa.setBackground(new java.awt.Color(3, 81, 145));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/delete_icon.png"))); // NOI18N
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

        btnHuy.setBackground(new java.awt.Color(3, 81, 145));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/cancel_icon.png"))); // NOI18N
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
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);
        tbSanPham = new MyTable();
        tbSanPham.setPreferredSize(new Dimension(1200 - 500, 600));
        tbSanPham.setHeaders(new String[]{"Mã SP", "Hãng SP", "Tên", "Đơn giá(triệu)", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.2, .1, .3, .2, .1});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbSanPham));

        jPanel5.add(jPanel3);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbHSP.getModel();
        String mahsp = txtMaHSP.getText().trim();
        String tenhsp = txtTenHSP.getText().trim();
        
        if(mahsp.equals("") & tenhsp.equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần thêm!");
        }
        /*String gioiTinh = "";
        for (Enumeration<AbstractButton> buttons = genderGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gioiTinh = button.getText();
                break;
            }
        }
        String tensp = txtTenSP.getText().trim();
        String sluong= txtSoLuong.getText().toString();
        int soluong = Integer.parseInt(sluong);
        String soluong0 = String.valueOf(soluong);
        String dgia = txtDonGia.getText().toString();
        float dongia = Float.parseFloat(dgia);
        //NumberFormat df = new DecimalFormat("#,###"+"000 đ");
        //String dogia = df.format(dongia);
        String dogia = dongia+"00.000";
        String hinhanh = lblHinhAnh.getText().trim();*/

        Object os[] = {mahsp, tenhsp};
        model.addRow(os);
        HSP_them = new HangSanPham(mahsp, tenhsp);
        hangSanPhamService.add(mahsp, tenhsp);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = -1;
        row = tbHSP.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn hàng trong bảng hãng sản phẩm!");
        } else {
            String maHangChonDeXoa = tbHSP.getTable().getValueAt(row, 0).toString();
            hangSanPhamService.delete(maHangChonDeXoa);

            List<SanPham> listSP = sanphamService.getDssp();
            List<String> listSPCanXoa = new ArrayList<String>();
            // xoa xong duyet bi loi
            for (SanPham sanPham : listSP) {
                if (sanPham.getMaHSP().equalsIgnoreCase(maHangChonDeXoa)) {
                    listSPCanXoa.add(sanPham.getMaSP());
                }
            }
            for (String string : listSPCanXoa) {
                sanphamService.delete(string);
            }

            tbHSP.getModel().removeRow(row);
            JOptionPane.showMessageDialog(null, "Xoá hãng đã chọn thành công!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.txtMaHSP.setText("");
        this.txtTenHSP.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtMaHSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHSPActionPerformed

    }//GEN-LAST:event_txtMaHSPActionPerformed

    private void txtTenHSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         refreshTable();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVienInfo;
    private javax.swing.JPanel ThaoTac;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel hienThiTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel panelHSP;
    private javax.swing.JTextField txtMaHSP;
    private javax.swing.JTextField txtTenHSP;
    // End of variables declaration//GEN-END:variables
}
