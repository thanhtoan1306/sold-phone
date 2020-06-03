/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;

import com.qlchdt.dao.QuanLySanPhamDao;
import com.qlchdt.model.NhanVien;
import com.qlchdt.model.SanPham;
import com.qlchdt.service.HangSanPhamService;
import com.qlchdt.service.NhanVienService;
import com.qlchdt.service.SanPhamService;
import com.qlchdt.service.format.MyTable;
import com.qlchdt.service.format.PriceFormatter;
import com.qlchdt.view.QuanLy.HoaDonBanHangForm;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

/**
 *
 * @author Administrator
 */
public class QuanLySP extends javax.swing.JFrame{
   SanPhamService sanphamService;
    HangSanPhamService hangSanPhamService = new HangSanPhamService();
    HoaDonBanHangForm hdbh = new HoaDonBanHangForm();    
    MyTable tbSanPham;
    SanPham SP_them;
    Path sanphamImagePath;
    Path imageLocation;
    //FormHang _target = new FormHang();
   

   /*public QuanLySP() {

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
    
    public boolean validateForm(){
        if (txtMaSP.getText().isEmpty()||txtMaHSP.getText().isEmpty()||txtTenSP.getText().isEmpty()||txtSoLuong.getText().isEmpty()||txtDonGia.getText().isEmpty()){
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
                    txtMaSP1.setText(sp.getMaSP());
                    txtTenSP1.setText(sp.getTenSP());
                    txtMaHSP1.setText(sp.getMaHSP());
                    txtDonGia1.setText(PriceFormatter.format(sp.getDonGia()));
                    txtSoLuong1.setText(Integer.toString(sp.getSoLuong()));
                    int w = lblHinhAnh.getWidth();
                    int h = lblHinhAnh.getHeight();
                    // nhớ sửa đường dẫn employees thành phones
                    ImageIcon img = new ImageIcon(getClass().getResource("/com/qlchdt/assets/phones/"+sp.getFileNameHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblHinhAnh.setIcon(img);
                    lblHinhAnh.setIcon(new ImageIcon(imgScaled));
                    imageLocation = new File(sanphamImagePath + System.getProperty("file.separator") + masp +".png").toPath();
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
    
    public void txSearchOnChange() {
        setDataToTable(sanphamService.search(txtTimKiem.getText(), "Tất cả",-1,-1,-1,-1), tbSanPham);
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
    
    public QuanLySP() {
        super("Quản Lí Sản Phẩm");
        initComponents();
        
        URL url = this.getClass().getResource("/com/qlchdt/assets/phones");
        try {
            sanphamImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLySP.class.getName()).log(Level.SEVERE, null, ex);
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
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        ThaoTac = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        btnThem = new rojerusan.RSButtonIconI();
        btnSua = new rojerusan.RSButtonIconI();
        btnXoa = new rojerusan.RSButtonIconI();
        btnHuy = new rojerusan.RSButtonIconI();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new rojerusan.RSMaterialButtonRectangle();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtMaHSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        txtMaSP1 = new javax.swing.JTextField();
        txtMaHSP1 = new javax.swing.JTextField();
        txtTenSP1 = new javax.swing.JTextField();
        txtSoLuong1 = new javax.swing.JTextField();
        txtDonGia1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("NGƯỜI DÙNG");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("QUẢN LÝ SẢN PHẨM");

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/user_icon_2.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        ThaoTac.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        ThaoTac.setToolTipText("Thông Tin Nhân Viên");
        ThaoTac.setPreferredSize(new java.awt.Dimension(800, 285));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel21.setText("THAO TÁC");

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/nv_add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setColorText(new java.awt.Color(0, 0, 0));
        btnThem.setColorTextHover(new java.awt.Color(0, 102, 255));
        btnThem.setOpaque(true);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setForeground(new java.awt.Color(0, 0, 0));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/nv_change.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setColorText(new java.awt.Color(0, 0, 0));
        btnSua.setColorTextHover(new java.awt.Color(0, 102, 255));
        btnSua.setOpaque(true);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/nv_xoa.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setColorText(new java.awt.Color(0, 0, 0));
        btnXoa.setColorTextHover(new java.awt.Color(0, 102, 255));
        btnXoa.setOpaque(true);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(204, 204, 204));
        btnHuy.setForeground(new java.awt.Color(0, 0, 0));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/nv_cancel.png"))); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.setColorText(new java.awt.Color(0, 0, 0));
        btnHuy.setColorTextHover(new java.awt.Color(0, 102, 255));
        btnHuy.setOpaque(true);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThaoTacLayout = new javax.swing.GroupLayout(ThaoTac);
        ThaoTac.setLayout(ThaoTacLayout);
        ThaoTacLayout.setHorizontalGroup(
            ThaoTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThaoTacLayout.createSequentialGroup()
                .addGroup(ThaoTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThaoTacLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThaoTacLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        ThaoTacLayout.setVerticalGroup(
            ThaoTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThaoTacLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThaoTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 0));
        jLabel9.setText("Tìm kiếm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 204, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Mã Sản Phẩm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Hãng Sản Phẩm");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Tên Sản Phẩm");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số Lượng");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Đơn Giá (triệu)");

        txtMaSP.setBackground(new java.awt.Color(204, 204, 204));

        txtMaHSP.setBackground(new java.awt.Color(204, 204, 204));

        txtTenSP.setBackground(new java.awt.Color(204, 204, 204));

        txtSoLuong.setBackground(new java.awt.Color(204, 204, 204));

        txtDonGia.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(txtTenSP)
                    .addComponent(txtMaHSP)
                    .addComponent(txtMaSP)
                    .addComponent(txtDonGia))
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblHinhAnh.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        txtMaSP1.setEditable(false);
        txtMaSP1.setBackground(new java.awt.Color(222, 222, 222));
        txtMaSP1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 102, 0))); // NOI18N
        txtMaSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSP1ActionPerformed(evt);
            }
        });

        txtMaHSP1.setEditable(false);
        txtMaHSP1.setBackground(new java.awt.Color(222, 222, 222));
        txtMaHSP1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hãng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 102, 0))); // NOI18N
        txtMaHSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHSP1ActionPerformed(evt);
            }
        });

        txtTenSP1.setEditable(false);
        txtTenSP1.setBackground(new java.awt.Color(222, 222, 222));
        txtTenSP1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 102, 0))); // NOI18N
        txtTenSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSP1ActionPerformed(evt);
            }
        });

        txtSoLuong1.setBackground(new java.awt.Color(222, 222, 222));
        txtSoLuong1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 102, 0))); // NOI18N

        txtDonGia1.setEditable(false);
        txtDonGia1.setBackground(new java.awt.Color(222, 222, 222));
        txtDonGia1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(255, 102, 0))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(txtMaSP1)
                    .addComponent(txtDonGia1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaHSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDonGia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        tbSanPham = new MyTable();
        tbSanPham.setPreferredSize(new Dimension(1200 - 500, 600));
        tbSanPham.setHeaders(new String[]{"Mã SP", "Hãng SP", "Tên", "Đơn giá(triệu)", "Số lượng"});
        tbSanPham.setColumnsWidth(new double[]{.2, .1, .3, .2, .1,.2});
        tbSanPham.setAlignment(3, JLabel.RIGHT);
        tbSanPham.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbSanPham));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(150, 150, 150)
                        .addComponent(jLabel9)
                        .addGap(5, 5, 5)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.getAccessibleContext().setAccessibleName("Thông tin sản phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = -1;
        row = tbSanPham.getTable().getSelectedRow();
        if (row==-1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn hàng!");
        }
        else {
             /*String tenHinhSPCanXoa = tbSanPham.getTable().getValueAt(row, 5).toString().trim();
            
            String separate = System.getProperty("file.separator");
            File targetPath = new File(sanphamImagePath + separate + tenHinhSPCanXoa); // xóa dc
            File srcPath = new File(System.getProperty("user.dir")
                    +separate+"src"+separate+"com"+separate+"qlchdt"+separate+"assets"+separate+"phones"+separate+tenHinhSPCanXoa); // xóa ko dc
            try {
                Files.deleteIfExists(targetPath.toPath());
                Files.deleteIfExists(srcPath.toPath());
            } catch (IOException ex) {
                Logger.getLogger(QuanLySP.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            sanphamService.delete(tbSanPham.getTable().getValueAt(row, 0).toString());
            tbSanPham.getModel().removeRow(row);
            JOptionPane.showMessageDialog(null, "Xoá hàng hiện chọn thành công!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.txtMaSP.setText("");
        this.txtMaHSP.setText("");
        this.txtTenSP.setText("");
        this.txtSoLuong.setText("");
        this.txtDonGia.setText("");
      
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(validateForm()){
            DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
            String masp = txtMaSP.getText().trim();
            String mahsp = txtMaHSP.getText().trim();
            String gioiTinh = "";
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
            String hinhanh = masp+".png";
            try {
                // copy anh vao assets/employees sau khi chon anh
                String targetPath = sanphamImagePath + System.getProperty("file.separator") + hinhanh;
                File srcPath = new File(System.getProperty("user.dir")+"/src/com/qlchdt/assets/phones/"+hinhanh);

                Files.copy(imageLocation, Paths.get(targetPath), REPLACE_EXISTING);     // build path
                Files.copy(imageLocation, Paths.get(srcPath.toString()), REPLACE_EXISTING);     // src path
                /*
                InputStream is = this.getClass().getResourceAsStream("/com/qlchdt/assets/employees/"+hinh);
                OutputStream outStream = new FileOutputStream(new File("D:\\haha.png"));
                byte[] buffer = new byte[1024];
                int length;
                // copy the file content in bytes
                while ((length = is.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                */
            } catch (IOException ex) {
                Logger.getLogger(QuanLySP.class.getName()).log(Level.SEVERE, null, ex);
            }


            Object os[] = {masp, mahsp, tensp, dogia, soluong0, hinhanh};
            model.addRow(os);
            SP_them = new SanPham(masp, mahsp, tensp, dongia, soluong, hinhanh);
            sanphamService.add(masp, mahsp, tensp, dongia, soluong, hinhanh);
            JOptionPane.showMessageDialog(this, "Thêm thành công ");
            }
        else{
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refreshTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        String masp = txtMaSP.getText().trim();
        String mahsp = txtMaHSP.getText().trim();
        String tensp = txtTenSP.getText().trim();
        String sluong= txtSoLuong.getText();
        int soluong = Integer.parseInt(sluong);
        String soluong0 = String.valueOf(soluong);
        String dgia = txtDonGia.getText();
        float dongia = Float.parseFloat(dgia);
        String dogia = dongia+"00.000";
        String hinhanh = this.imageLocation.getFileName().toString();
        try {
            // copy anh vao assets/employees sau khi chon anh
            String targetPath = sanphamImagePath + System.getProperty("file.separator") + hinhanh;
            File srcPath = new File(System.getProperty("user.dir")+"/src/com/qlchdt/assets/phones/"+hinhanh);
            
            Files.copy(imageLocation, Paths.get(targetPath), REPLACE_EXISTING);     // build path
            Files.copy(imageLocation, Paths.get(srcPath.toString()), REPLACE_EXISTING);     // src path
            /*
            InputStream is = this.getClass().getResourceAsStream("/com/qlchdt/assets/employees/"+hinh);
            OutputStream outStream = new FileOutputStream(new File("D:\\haha.png"));
            byte[] buffer = new byte[1024];
            int length;
            // copy the file content in bytes
            while ((length = is.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            */
        } catch (IOException ex) {
            Logger.getLogger(QuanLySP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        Object os[] = {masp, mahsp, tensp, dogia, soluong0,hinhanh};
        if (sanphamService.update(masp, mahsp, tensp, dongia, soluong, hinhanh)) {
            JOptionPane.showMessageDialog(null, "Sửa thành công !");
            refreshTable();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtMaSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSP1ActionPerformed

    private void txtMaHSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHSP1ActionPerformed

    private void txtTenSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSP1ActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        if (lblHinhAnh.getIcon()!=null) {
            //
        }
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: " +
            //        chooser.getSelectedFile().getName());
            
            imageLocation = chooser.getSelectedFile().toPath();
            
            int w = lblHinhAnh.getWidth();
            int h = lblHinhAnh.getHeight();
            ImageIcon img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblHinhAnh.setIcon(img);
            lblHinhAnh.setIcon(new ImageIcon(imgScaled));
        }
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLySP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThaoTac;
    private rojerusan.RSButtonIconI btnHuy;
    private rojerusan.RSMaterialButtonRectangle btnLamMoi;
    private rojerusan.RSButtonIconI btnSua;
    private rojerusan.RSButtonIconI btnThem;
    private rojerusan.RSButtonIconI btnXoa;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblHinhAnh;
    private rojerusan.RSPanelImage rSPanelImage1;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDonGia1;
    private javax.swing.JTextField txtMaHSP;
    private javax.swing.JTextField txtMaHSP1;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSP1;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuong1;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenSP1;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
