/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;


import com.qlchdt.model.NhanVien;
import com.qlchdt.service.NhanVienService;
import com.qlchdt.service.format.MyTable;
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
 * @author Administrator
 */
public class QuanLiNhanVien extends javax.swing.JFrame{
    DefaultTableModel defaultTableModel;
    NhanVienService nhanVienService;
    MyTable tbNhanVien;
    NhanVien NV_them;
    JDatePickerImpl datePicker;
    Path nhanVienImagePath;
    Path imageLocation;
            
    private void setDataToTable(ArrayList<NhanVien> data, MyTable table) {
        table.clear();
        for (NhanVien nv : data) {
            table.addRow(new String[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getNgaySinh().toString(),
                nv.getGioiTinh(),
                nv.getSDT(),
                nv.getDiaChi(),
                nv.getHinhAnh(),});
        }
    }
    public void refreshTable() {
        nhanVienService.readDB();
        setDataToTable(nhanVienService.search("", "Tất cả", null, null), tbNhanVien);
    }

    public void refreshAll() {
        //refreshTable();
        txtMa.setText("");
        txtTen.setText("");
        genderGroup.clearSelection();
        //txtNgaySinh.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
    }
    
    public void showInfo(String maNV) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (maNV != null) {
            // show hình
            for (NhanVien nv : nhanVienService.getDsnv()) {
                if (nv.getMaNV().equals(maNV)) {
                    // show info
                    txtMa.setText(nv.getMaNV());
                    txtTen.setText(nv.getTenNV());
                    if (nv.getGioiTinh().equalsIgnoreCase("nam")) {
                        genderGroup.setSelected(radioNam.getModel(), true);
                    }
                    else if (nv.getGioiTinh().equalsIgnoreCase("nữ")) {
                        genderGroup.setSelected(radioNu.getModel(), true);
                    }
                    datePicker.getModel().setDate(nv.getNgaySinh().getYear(), nv.getNgaySinh().getMonthValue()-1, nv.getNgaySinh().getDayOfMonth());
                    datePicker.getModel().setSelected(true);
                    txtSDT.setText(nv.getSDT());
                    txtDiaChi.setText(nv.getDiaChi());
                    
                    // show hinh 
                    int w = lblImage.getWidth();
                    int h = lblImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/com/qlchdt/assets/employees/"+nv.getHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblImage.setIcon(img);
                    lblImage.setIcon(new ImageIcon(imgScaled));
                    imageLocation = new File(nhanVienImagePath + System.getProperty("file.separator") + maNV +".png").toPath();
                    return;
                }
            }
        }
    }
    
    public String getSelectedNhanVien(int col) {
        int i = tbNhanVien.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbNhanVien.getTable().convertRowIndexToModel(i);
            return tbNhanVien.getModel().getValueAt(realI, col).toString();
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
        setDataToTable(nhanVienService.search(txtTimKiem.getText(), "Tất cả", null, null), tbNhanVien);
    }
    /**
     * Creates new form QuanLiNhanVien
     */
    public QuanLiNhanVien() {
        super("Quản Lí Nhân Viên");
        initComponents();
        
        URL url = this.getClass().getResource("/com/qlchdt/assets/employees");
        try {
            nhanVienImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        nhanVienService = new NhanVienService();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setDataToTable(nhanVienService.getDsnv(), tbNhanVien);
        tbNhanVien.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
            @Override
            public void mouseReleased(MouseEvent me) {
                String manv = getSelectedNhanVien(0);
                if (manv != null) {
                    showInfo(manv);
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
        jLabel4 = new javax.swing.JLabel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        NhanVienInfo = new javax.swing.JPanel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        calendar = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        ThaoTac = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        btnThem = new rojerusan.RSButtonIconI();
        btnSua = new rojerusan.RSButtonIconI();
        btnXoa = new rojerusan.RSButtonIconI();
        btnLuu = new rojerusan.RSButtonIconI();
        btnHuy = new rojerusan.RSButtonIconI();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new rojerusan.RSMaterialButtonRectangle();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 900));
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setPreferredSize(new java.awt.Dimension(1200, 900));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("NGƯỜI DÙNG");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 51));
        jLabel2.setText("QUẢN LÍ NHÂN VIÊN");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setText("...........................................................................................................................................................................................................................................");

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

        NhanVienInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        NhanVienInfo.setToolTipText("Thông Tin Nhân Viên");
        NhanVienInfo.setPreferredSize(new java.awt.Dimension(800, 285));

        txtMa.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        genderGroup.add(radioNam);
        radioNam.setText("Nam");
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });

        genderGroup.add(radioNu);
        radioNu.setText("Nữ");

        jLabel3.setText("Mã Nhân Viên");

        jLabel5.setText("Họ Tên");

        jLabel6.setText("Giới tính");

        jLabel7.setText("Ngày Sinh");

        jLabel8.setLabelFor(txtSDT);
        jLabel8.setText("Số Điện Thoại");

        jLabel11.setLabelFor(txtDiaChi);
        jLabel11.setText("Địa Chỉ");

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setText("THÔNG TIN NHÂN VIÊN");

        calendar.setLayout(new java.awt.CardLayout());

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/employees/empty-user.png"))); // NOI18N
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImageMousePressed(evt);
            }
        });

        javax.swing.GroupLayout NhanVienInfoLayout = new javax.swing.GroupLayout(NhanVienInfo);
        NhanVienInfo.setLayout(NhanVienInfoLayout);
        NhanVienInfoLayout.setHorizontalGroup(
            NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienInfoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NhanVienInfoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NhanVienInfoLayout.createSequentialGroup()
                                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, NhanVienInfoLayout.createSequentialGroup()
                                        .addComponent(radioNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radioNu))
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(NhanVienInfoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(37, 37, 37))
        );
        NhanVienInfoLayout.setVerticalGroup(
            NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienInfoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhanVienInfoLayout.createSequentialGroup()
                        .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(NhanVienInfoLayout.createSequentialGroup()
                                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NhanVienInfoLayout.createSequentialGroup()
                                        .addComponent(radioNu)
                                        .addGap(1, 1, 1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(radioNam)
                                        .addComponent(jLabel6)))))
                        .addGap(18, 18, 18)
                        .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NhanVienInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        //datePicker.setBounds(220,350,120,30);
        calendar.add(datePicker);

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

        btnLuu.setBackground(new java.awt.Color(204, 204, 204));
        btnLuu.setForeground(new java.awt.Color(0, 0, 0));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/qlchdt/assets/nv_save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setColorText(new java.awt.Color(0, 0, 0));
        btnLuu.setColorTextHover(new java.awt.Color(0, 102, 255));
        btnLuu.setOpaque(true);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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
                .addGap(15, 15, 15)
                .addGroup(ThaoTacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ThaoTacLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
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

        jPanel2.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(NhanVienInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                            .addComponent(ThaoTac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jLabel9)
                                .addGap(5, 5, 5)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NhanVienInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ThaoTac, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addGap(32, 32, 32))
        );

        tbNhanVien = new MyTable();
        tbNhanVien.setPreferredSize(new Dimension(900-250, 345));
        tbNhanVien.setHeaders(new String[]{"Mã NV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Hình Ảnh"});
        tbNhanVien.setColumnsWidth(new double[]{.4, 1, .75, .4, .8, .7, 0.7});
        tbNhanVien.setAlignment(3, JLabel.RIGHT);
        tbNhanVien.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbNhanVien));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = -1;
        row = tbNhanVien.getTable().getSelectedRow();
        if (row==-1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn hàng!");
        }
        else {
            String tenHinhNVCanXoa = tbNhanVien.getTable().getValueAt(row, 6).toString().trim();
            
            String separate = System.getProperty("file.separator");
            File targetPath = new File(nhanVienImagePath + separate + tenHinhNVCanXoa); // xóa dc
            File srcPath = new File(System.getProperty("user.dir")
                    +separate+"src"+separate+"com"+separate+"qlchdt"+separate+"assets"+separate+"employees"+separate+tenHinhNVCanXoa); // xóa ko dc
            try {
                Files.deleteIfExists(targetPath.toPath());
                Files.deleteIfExists(srcPath.toPath());
            } catch (IOException ex) {
                Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            nhanVienService.delete(tbNhanVien.getTable().getValueAt(row, 0).toString());
            tbNhanVien.getModel().removeRow(row);
            JOptionPane.showMessageDialog(null, "Xoá hàng thành công!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (NV_them==null) {
            JOptionPane.showMessageDialog(null, "Chưa có nhân viên nào mới thêm vào bảng. Lưu vào database thất bại.");
            return;
        }
        if (!nhanVienService.saveToDatabase(NV_them)) {
            JOptionPane.showMessageDialog(null, "Lưu vào Database thất bại!");
            
            String separate = System.getProperty("file.separator");
            File targetPath = new File(nhanVienImagePath + separate + NV_them.getMaNV()+".png");
            File srcPath = new File(System.getProperty("user.dir")
                    +separate+"src"+separate+"com"+"qlchdt"+separate+"assets"+separate+"employees"+separate+NV_them.getMaNV()+".png");
            if (targetPath.delete() && srcPath.delete()) {
                System.out.println("Hình thêm đã được xóa thành công.");
            }
            
            return;
        }
        JOptionPane.showMessageDialog(null, "Lưu vào Database thành công!");
        NV_them = null;
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.txtMa.setText("");
        this.txtTen.setText("");
        this.datePicker.getModel().setValue(null);
        this.genderGroup.clearSelection();
        this.txtSDT.setText("");
        this.txtDiaChi.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("/com/qlchdt/assets/employees/empty-user.png"));
        this.lblImage.setIcon(img);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        String maNV = txtMa.getText().trim();
        if (maNV.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Không thể thêm vào bảng, Mã Nhân viên chưa nhập!");
            return;
        }
        for (int i=0; i<model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(maNV)) {
                JOptionPane.showMessageDialog(null, "Không thể thêm vào bảng, trùng Mã Nhân viên đã có sẵn!");
                return;
            }
        }

        String name = txtTen.getText().trim();
        String gioiTinh = "";
        for (Enumeration<AbstractButton> buttons = genderGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gioiTinh = button.getText();
                break;
            }
        }
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.of(this.datePicker.getModel().getYear(), this.datePicker.getModel().getMonth()+1, this.datePicker.getModel().getDay());
        String soDienThoai = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String hinh = maNV+".png";
        
        try {
            // copy anh vao assets/employees sau khi chon anh
            String targetPath = nhanVienImagePath + System.getProperty("file.separator") + hinh;
            File srcPath = new File(System.getProperty("user.dir")+"/src/com/qlchdt/assets/employees/"+hinh);
            
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
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Object os[] = {maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh};
        model.addRow(os);
        NV_them = new NhanVien(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh);
        nhanVienService.add(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh);
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        refreshTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        //DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        String maNV = txtMa.getText().trim();
        String name = txtTen.getText().trim();
        String gioiTinh = "";
        for (Enumeration<AbstractButton> buttons = genderGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gioiTinh = button.getText();
                break;
            }
        }
        LocalDate date = LocalDate.of(this.datePicker.getModel().getYear(), this.datePicker.getModel().getMonth()+1, this.datePicker.getModel().getDay());
        String soDienThoai = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String hinh = this.imageLocation.getFileName().toString();
        
        try {
            // copy anh vao assets/employees sau khi chon anh
            String targetPath = nhanVienImagePath + System.getProperty("file.separator") + hinh;
            File srcPath = new File(System.getProperty("user.dir")+"/src/com/qlchdt/assets/employees/"+hinh);
            
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
            Logger.getLogger(QuanLiNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Object os[] = {maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh};
        if (nhanVienService.update(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh)) {
            JOptionPane.showMessageDialog(null, "Sửa thành công !");
            refreshTable();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNamActionPerformed

    private void lblImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMousePressed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        if (lblImage.getIcon()!=null) {
            //
        }
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: " +
            //        chooser.getSelectedFile().getName());
            
            imageLocation = chooser.getSelectedFile().toPath();
            
            int w = lblImage.getWidth();
            int h = lblImage.getHeight();
            ImageIcon img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblImage.setIcon(img);
            lblImage.setIcon(new ImageIcon(imgScaled));
        }
    }//GEN-LAST:event_lblImageMousePressed

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
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLiNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVienInfo;
    private javax.swing.JPanel ThaoTac;
    private rojerusan.RSButtonIconI btnHuy;
    private rojerusan.RSMaterialButtonRectangle btnLamMoi;
    private rojerusan.RSButtonIconI btnLuu;
    private rojerusan.RSButtonIconI btnSua;
    private rojerusan.RSButtonIconI btnThem;
    private rojerusan.RSButtonIconI btnXoa;
    private javax.swing.JPanel calendar;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImage;
    private rojerusan.RSPanelImage rSPanelImage1;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
