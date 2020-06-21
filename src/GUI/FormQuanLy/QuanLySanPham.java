/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;

import BUS.HangSanPhamService;
import BUS.SanPhamService;
import DTO.Model.SanPham;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.DangNhap;
import GUI.FormThemSua.ThemSuaSanPham;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class QuanLySanPham extends javax.swing.JPanel {

    SanPhamService sanphamService;
    HangSanPhamService hangSanPhamService = new HangSanPhamService();
    //HoaDonBanHangForm hdbh = new HoaDonBanHangForm();    
    MyTable tbSanPham;
    SanPham SP_them;
    Path sanphamImagePath;
    Path imageLocation;

    public QuanLySanPham() {
        initComponents();

        // buttons
        if (!DangNhap.quyenLogin.getChiTietQuyen().contains("qlSanPham")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSuaSP.setEnabled(false);
        }

        URL url = this.getClass().getResource("/DTO/Assets/Products");
        try {
            sanphamImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        sanphamService = new SanPhamService();
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setDataToTable(sanphamService.getDssp(), tbSanPham);
        tbSanPham.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String masp = getSelectedSanPham(0);
                if (masp != null) {
                    showInfo(masp);
                }
            }
        });
        addDocumentListener(txtTimKiem);

        refreshTable();
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
            //sp.getFileNameHinhAnh();

        }
    }

    public void refreshTable() {
        sanphamService.readDB();
        setDataToTable(sanphamService.search("", "Tất cả", -1, -1, -1, -1), tbSanPham);
    }

    public void refreshAll() {
        refreshTable();
        txtMaSP.setText("");
        txtMaHSP.setText("");
        txtTenSP.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        lblHinhAnh.setIcon(null);
    }

    public boolean validateForm() {
        if (txtMaSP.getText().isEmpty() || txtMaHSP.getText().isEmpty() || txtTenSP.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtDonGia.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public void showInfo(String masp) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (masp != null) {
            // show hình
            for (SanPham sp : sanphamService.getDssp()) {
                if (sp.getMaSP().equals(masp)) {
                    // show info

                    /*int w = lblHinhAnh.getWidth();
                    int h = lblHinhAnh.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/com/qlchdt/assets/phones/"+sp.getFileNameHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblHinhAnh.setIcon(img);
                    lblHinhAnh.setIcon(new ImageIcon(imgScaled));*/
                    String loai = hangSanPhamService.getHangSanPham(sp.getMaHSP()).getTenHang();
                    txtMaSP.setText(sp.getMaSP());
                    txtTenSP.setText(sp.getTenSP());
                    txtMaHSP.setText(loai + " - " + sp.getMaHSP());
                    txtDonGia.setText(PriceFormatter.format(sp.getDonGia()));
                    txtSoLuong.setText(Integer.toString(sp.getSoLuong()));
                    int w = lblHinhAnh.getWidth();
                    int h = lblHinhAnh.getHeight();
                    // nhớ sửa đường dẫn employees thành phones
                    ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Products/" + sp.getFileNameHinhAnh()));
                    if (img==null) {
                        img = new ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_product_icon.png"));
                    }
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblHinhAnh.setIcon(img);
                    lblHinhAnh.setIcon(new ImageIcon(imgScaled));
                    imageLocation = new File(sanphamImagePath + System.getProperty("file.separator") + masp + ".png").toPath();
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

    public String getSelectedRow(int col) {
        int i = tbSanPham.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbSanPham.getTable().convertRowIndexToModel(i);
            return tbSanPham.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public void txSearchOnChange() {
        setDataToTable(sanphamService.search(txtTimKiem.getText(), "Tất cả", -1, -1, -1, -1), tbSanPham);
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

    private void btnSuaMouseClicked() {
        int row = -1;
        String masp = null;
        row = tbSanPham.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể sửa vì bạn chưa chọn sản phẩm!");
        } else {
            masp = tbSanPham.getTable().getValueAt(row, 0).toString();  // lấy mã sp từ hàng dg chọn trong bảng
        }
        if (masp != null) {
            int confirmChange = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa sản phẩm này?", "Sửa sản phẩm",JOptionPane.YES_NO_OPTION);
            if (confirmChange == JOptionPane.YES_OPTION) {
                ThemSuaSanPham suasp = new ThemSuaSanPham("Sửa", masp);

                // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
                suasp.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        refreshAll();
                        refreshTable();
                    }
                });
            }
        }
    }

    private void btnXoaMouseClicked() {
        int row = -1;
        row = tbSanPham.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn sản phẩm!");
        } else { 
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm này?", "Xóa sản phẩm",JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                // ok ok không xóa hình, oops, chắc xóa dc
                
                String tenHinhSPCanXoa = this.sanphamService.getDssp().get(row).getFileNameHinhAnh();
                String separate = System.getProperty("file.separator");
                File targetPath = new File(sanphamImagePath + separate + tenHinhSPCanXoa); // xóa dc
                File srcPath = new File(System.getProperty("user.dir")
                        + separate + "src" + separate + "DTO" + separate + "Assets" + separate + "Products" + separate + tenHinhSPCanXoa);
                try {
                    Files.deleteIfExists(targetPath.toPath());
                    Files.deleteIfExists(srcPath.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
         
                sanphamService.delete(tbSanPham.getTable().getValueAt(row, 0).toString());
                tbSanPham.getModel().removeRow(row);
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thành công!");
            }
        }
    }

    private void btnThemMouseClicked() {
        //QuanLySanPham qlsp = new QuanLySanPham();
        ThemSuaSanPham themsp = new ThemSuaSanPham("Thêm", "");
        themsp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                refreshAll();
                refreshTable();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        plSanPham = new javax.swing.JPanel();
        ThaoTac = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        txtMaHSP = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        txtDonGia = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txtSoLuong = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1200, 770));
        setPreferredSize(new java.awt.Dimension(1200, 770));
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SẢN PHẨM");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));
        jPanel6.add(jLabel1);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(176, 196, 229))); // NOI18N
        jPanel5.setMinimumSize(new java.awt.Dimension(1200, 500));
        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 500));
        jPanel5.setLayout(new java.awt.BorderLayout());

        plSanPham.setMaximumSize(new java.awt.Dimension(1200, 400));
        plSanPham.setMinimumSize(new java.awt.Dimension(1200, 400));
        plSanPham.setPreferredSize(new java.awt.Dimension(1200, 400));
        plSanPham.setLayout(new java.awt.CardLayout());
        jPanel5.add(plSanPham, java.awt.BorderLayout.CENTER);
        tbSanPham = new MyTable();
        tbSanPham.setPreferredSize(new Dimension(1200 - 500, 600));
        tbSanPham.setHeaders(new String[]{"Mã SP", "Hãng SP", "Tên", "Đơn giá(triệu)", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.2, .1, .3, .2, .1,.2});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);

        jPanel5.add(new JScrollPane(tbSanPham));

        ThaoTac.setBackground(new java.awt.Color(255, 255, 255));
        ThaoTac.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thao tác", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N
        ThaoTac.setToolTipText("Thông Tin Nhân Viên");
        ThaoTac.setMaximumSize(new java.awt.Dimension(1200, 150));
        ThaoTac.setMinimumSize(new java.awt.Dimension(1200, 150));
        ThaoTac.setPreferredSize(new java.awt.Dimension(1200, 150));
        ThaoTac.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(100, 120));
        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(585, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(585, 150));

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
        jPanel2.add(btnThem);

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
        jPanel2.add(btnXoa);

        btnSuaSP.setBackground(new java.awt.Color(3, 81, 145));
        btnSuaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.setMaximumSize(new java.awt.Dimension(150, 50));
        btnSuaSP.setMinimumSize(new java.awt.Dimension(150, 50));
        btnSuaSP.setPreferredSize(new java.awt.Dimension(150, 50));
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });
        jPanel2.add(btnSuaSP);

        btnHuy.setBackground(new java.awt.Color(3, 81, 145));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/cancel_icon.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setMaximumSize(new java.awt.Dimension(150, 50));
        btnHuy.setMinimumSize(new java.awt.Dimension(150, 50));
        btnHuy.setPreferredSize(new java.awt.Dimension(150, 50));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel2.add(btnHuy);

        jPanel4.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 0));
        jLabel9.setText("Tìm kiếm");
        jPanel3.add(jLabel9);

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTimKiem.setMaximumSize(new java.awt.Dimension(300, 45));
        txtTimKiem.setMinimumSize(new java.awt.Dimension(300, 45));
        txtTimKiem.setPreferredSize(new java.awt.Dimension(300, 45));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        jPanel3.add(txtTimKiem);

        btnLamMoi.setBackground(new java.awt.Color(3, 81, 145));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setMaximumSize(new java.awt.Dimension(150, 50));
        btnLamMoi.setMinimumSize(new java.awt.Dimension(150, 50));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(150, 50));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnLamMoi);

        jPanel4.add(jPanel3);

        ThaoTac.add(jPanel4);

        jPanel5.add(ThaoTac, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_product_icon.png"))); // NOI18N
        lblHinhAnh.setPreferredSize(new java.awt.Dimension(200, 240));
        jPanel10.add(lblHinhAnh);

        jPanel11.add(jPanel10);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.Y_AXIS));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        txtMaSP.setEditable(false);
        txtMaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });
        jPanel12.add(txtMaSP);

        txtMaHSP.setEditable(false);
        txtMaHSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaHSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel12.add(txtMaHSP);

        jPanel8.add(jPanel12);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 20, 40));

        txtDonGia.setEditable(false);
        txtDonGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel15.add(txtDonGia);

        txtTenSP.setEditable(false);
        txtTenSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel15.add(txtTenSP);

        jPanel8.add(jPanel15);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        txtSoLuong.setEditable(false);
        txtSoLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel7.add(txtSoLuong);

        jPanel8.add(jPanel7);

        jPanel11.add(jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1176, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel5.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThemMouseClicked();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnXoaMouseClicked();

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        btnSuaMouseClicked();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.txtMaSP.setText("");
        this.txtMaHSP.setText("");
        this.txtTenSP.setText("");
        this.txtSoLuong.setText("");
        this.txtDonGia.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_product_icon.png"));
        this.lblHinhAnh.setIcon(img);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refreshTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThaoTac;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JPanel plSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
