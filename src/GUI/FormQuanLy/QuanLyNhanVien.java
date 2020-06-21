/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy;


import BUS.NhanVienService;
import DTO.Model.NhanVien;
import GUI.Custom.MyTable;
import GUI.DangNhap;
import GUI.Custom.DateLabelFormatter;
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
import javax.swing.ButtonModel;
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
public class QuanLyNhanVien extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    NhanVienService nhanVienService;
    MyTable tbNhanVien;
    NhanVien NV_them;
    JDatePickerImpl datePicker;
    Path nhanVienImagePath;
    Path imageLocation;

    public QuanLyNhanVien() {
        //super("Quản Lí Nhân Viên");
        initComponents();
        imageLocation=null;
        
        if (!DangNhap.quyenLogin.getChiTietQuyen().contains("qlNhanVien")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);

        }
        URL url = this.getClass().getResource("/DTO/Assets/Employees");
        try {
            nhanVienImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
        setDataToTable(nhanVienService.search("", "Tất cả"), tbNhanVien);
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
                    } else if (nv.getGioiTinh().equalsIgnoreCase("nữ")) {
                        genderGroup.setSelected(radioNu.getModel(), true);
                    }
                    datePicker.getModel().setDate(nv.getNgaySinh().getYear(), nv.getNgaySinh().getMonthValue() - 1, nv.getNgaySinh().getDayOfMonth());
                    datePicker.getModel().setSelected(true);
                    txtSDT.setText(nv.getSDT());
                    txtDiaChi.setText(nv.getDiaChi());

                    // show hinh 
                    int w = lblImage.getWidth();
                    int h = lblImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Employees/" + nv.getHinhAnh()));
                    if (img==null) {
                        img = new ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_user_icon.png"));
                    }
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(imgScaled));
                    imageLocation = new File(nhanVienImagePath + System.getProperty("file.separator") + maNV + ".png").toPath();
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
        setDataToTable(nhanVienService.search(txtTimKiem.getText(), "Tất cả"), tbNhanVien);
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
        jPanel15 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        calendar = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        ThaoTac = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(176, 196, 229)));
        jPanel6.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel6.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 100));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_user_icon.png"))); // NOI18N
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImage.setMaximumSize(new java.awt.Dimension(240, 250));
        lblImage.setMinimumSize(new java.awt.Dimension(240, 350));
        lblImage.setPreferredSize(new java.awt.Dimension(240, 350));
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImageMousePressed(evt);
            }
        });
        jPanel15.add(lblImage);

        jPanel8.add(jPanel15);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel7.setName(""); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Mã NV");
        jPanel7.add(jLabel3);

        txtMa.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtMa.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMa.setMaximumSize(new java.awt.Dimension(180, 50));
        txtMa.setMinimumSize(new java.awt.Dimension(180, 50));
        txtMa.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel7.add(txtMa);

        jPanel9.add(jPanel7);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel10.setName(""); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setText("Họ Tên");
        jPanel10.add(jLabel10);

        txtTen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtTen.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTen.setMaximumSize(new java.awt.Dimension(180, 50));
        txtTen.setMinimumSize(new java.awt.Dimension(180, 50));
        txtTen.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel10.add(txtTen);

        jPanel9.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel11.setName(""); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 60));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel11.setLayout(flowLayout1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setText("Ngày sinh");
        jPanel11.add(jLabel4);

        calendar.setBackground(new java.awt.Color(255, 255, 255));
        calendar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        calendar.setMaximumSize(new java.awt.Dimension(180, 50));
        calendar.setMinimumSize(new java.awt.Dimension(180, 50));
        calendar.setPreferredSize(new java.awt.Dimension(180, 50));
        calendar.setLayout(new java.awt.CardLayout());
        jPanel11.add(calendar);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        //datePicker.setBounds(220,350,120,30);
        calendar.add(datePicker);

        jPanel9.add(jPanel11);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel13.setName(""); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("SĐT");
        jPanel13.add(jLabel7);

        txtSDT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtSDT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtSDT.setMaximumSize(new java.awt.Dimension(180, 50));
        txtSDT.setMinimumSize(new java.awt.Dimension(180, 50));
        txtSDT.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel13.add(txtSDT);

        jPanel9.add(jPanel13);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel12.setName(""); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("Giới tính");
        jPanel12.add(jLabel5);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel14.setMaximumSize(new java.awt.Dimension(180, 50));
        jPanel14.setMinimumSize(new java.awt.Dimension(180, 50));
        jPanel14.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        radioNam.setBackground(new java.awt.Color(255, 255, 255));
        genderGroup.add(radioNam);
        radioNam.setText("Nam");
        radioNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNamActionPerformed(evt);
            }
        });
        jPanel14.add(radioNam);

        radioNu.setBackground(new java.awt.Color(255, 255, 255));
        genderGroup.add(radioNu);
        radioNu.setText("Nữ");
        jPanel14.add(radioNu);

        jPanel12.add(jPanel14);

        jPanel9.add(jPanel12);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel16.setName(""); // NOI18N
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 0));
        jLabel8.setText("Địa chỉ");
        jPanel16.add(jLabel8);

        txtDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        txtDiaChi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtDiaChi.setMaximumSize(new java.awt.Dimension(180, 50));
        txtDiaChi.setMinimumSize(new java.awt.Dimension(180, 50));
        txtDiaChi.setPreferredSize(new java.awt.Dimension(180, 50));
        jPanel16.add(txtDiaChi);

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

        btnSua.setBackground(new java.awt.Color(3, 81, 145));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/pencil_icon.png"))); // NOI18N
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

        jButton1.setBackground(new java.awt.Color(3, 81, 145));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DTO/Assets/Icons/refresh_refresh.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);
        tbNhanVien = new MyTable();
        tbNhanVien.setPreferredSize(new Dimension(900-250, 345));
        tbNhanVien.setHeaders(new String[]{"Mã NV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Hình Ảnh"});
        tbNhanVien.setColumnsWidth(new double[]{.4, 1, .75, .4, .8, .7, 0.7});
        tbNhanVien.setAlignment(3, JLabel.RIGHT);
        tbNhanVien.setAlignment(4, JLabel.RIGHT);

        jPanel2.add(new JScrollPane(tbNhanVien));

        jPanel5.add(jPanel3);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMousePressed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        if (lblImage.getIcon() != null) {
            //
        }
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
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

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void radioNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNamActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (checkEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
            String maNV = txtMa.getText().trim();
            for (int i = 0; i < model.getRowCount(); i++) {
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
            LocalDate date = LocalDate.of(this.datePicker.getModel().getYear(), this.datePicker.getModel().getMonth() + 1, this.datePicker.getModel().getDay());
            String soDienThoai = txtSDT.getText().trim();
            String regexSDT = "^[0-9]{10}$";
            if (!soDienThoai.matches(regexSDT)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
                return;
            }
            String diaChi = txtDiaChi.getText().trim();

            if (imageLocation==null) {  
                try {
                    imageLocation = Paths.get(this.getClass().getResource("/DTO/Assets/Icons/empty_user_icon.png").toURI()).toFile().toPath();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String hinh = maNV + ".png";
            try {
                // copy anh vao assets/employees sau khi chon anh
                String targetPath = nhanVienImagePath + System.getProperty("file.separator") + hinh;
                File srcPath = new File(System.getProperty("user.dir") + "/src/DTO/Assets/Employees/" + hinh);

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
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }

            Object os[] = {maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh};
            model.addRow(os);
            NV_them = new NhanVien(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh);
            
            // Lưu vào database
            if (!nhanVienService.saveToDatabase(NV_them)) {
                JOptionPane.showMessageDialog(null, "Lưu vào Database thất bại!");

                String separate = System.getProperty("file.separator");
                File targetPath = new File(nhanVienImagePath + separate + NV_them.getMaNV() + ".png");
                File srcPath = new File(System.getProperty("user.dir")
                        + separate + "src" + separate + "com" + "qlchdt" + separate + "assets" + separate + "employees" + separate + NV_them.getMaNV() + ".png");
                if (targetPath.delete() && srcPath.delete()) {
                    System.out.println("Hình thêm đã được xóa.");
                }

                return;
            }
            else {
                nhanVienService.add(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh);
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
            }
            NV_them = null;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = -1;
        row = tbNhanVien.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể xoá vì bạn chưa chọn nhân viên!");
        } else {
            String tenHinhNVCanXoa = tbNhanVien.getTable().getValueAt(row, 6).toString().trim();
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên này?","Xóa nhân viên",JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                String separate = System.getProperty("file.separator");
                File targetPath = new File(nhanVienImagePath + separate + tenHinhNVCanXoa); // xóa dc
                File srcPath = new File(System.getProperty("user.dir")
                        + separate + "src" + separate + "DTO" + separate + "Assets" + separate + "Employees" + separate + tenHinhNVCanXoa); // xóa ko dc
                try {
                    Files.deleteIfExists(targetPath.toPath());
                    Files.deleteIfExists(srcPath.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }

                nhanVienService.delete(tbNhanVien.getTable().getValueAt(row, 0).toString());
                tbNhanVien.getModel().removeRow(row);
                JOptionPane.showMessageDialog(null, "Xoá nhân viên thành công!");
            }

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        //DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        int row = -1;
        row = tbNhanVien.getTable().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Không thể sửa vì bạn chưa chọn nhân viên!");
        }
        else {
            String maNV = txtMa.getText().trim();
            if (!this.nhanVienService.getDsnv().get(row).getMaNV().equals(maNV)) {
                JOptionPane.showMessageDialog(null, "Không thể sửa vì không tồn tại mã nhân viên bạn nhập!");
                return;
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
            LocalDate date = LocalDate.of(this.datePicker.getModel().getYear(), this.datePicker.getModel().getMonth() + 1, this.datePicker.getModel().getDay());
            String soDienThoai = txtSDT.getText().trim();
            String regexSDT = "^[0-9]{10}$";
            if (!soDienThoai.matches(regexSDT)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng định dạng 10 số! Mời nhập lại!");
                return;
            }
            String diaChi = txtDiaChi.getText().trim();
            String hinh = maNV + ".png";

            int confirmChange = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa nhân viên này?","Sửa nhân viên",JOptionPane.YES_NO_OPTION);
            if (confirmChange==JOptionPane.YES_OPTION) {
                try {
                    // copy anh vao assets/employees sau khi chon anh
                    String targetPath = nhanVienImagePath + System.getProperty("file.separator") + hinh;
                    File srcPath = new File(System.getProperty("user.dir") + "/src/DTO/Assets/Employees/" + hinh);

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
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }

                Object os[] = {maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh};
                if (nhanVienService.update(maNV, name, date, gioiTinh, soDienThoai, diaChi, hinh)) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công !");
                    refreshTable();
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.txtMa.setText("");
        this.txtTen.setText("");
        this.datePicker.getModel().setValue(null);
        this.genderGroup.clearSelection();
        this.txtSDT.setText("");
        this.txtDiaChi.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Icons/empty_user_icon.png"));
        imageLocation = null;
        this.lblImage.setIcon(img);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshTable();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private Boolean checkEmpty() {
        String maNV = txtMa.getText().trim();
        String tenNV = txtTen.getText().trim();
        ButtonModel gioiTinh = genderGroup.getSelection();
        String soDienThoai = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        Object ngaySinh = this.datePicker.getModel().getValue();
        
        if (maNV.equals("")) {
            return showErrorTx(txtMa, "Mã Nhân Viên không được để trống");
        } else if (tenNV.equals("")) {
            return showErrorTx(txtTen, "Tên Nhân Viên không được để trống");
        } else if (soDienThoai.equals("")) {
            return showErrorTx(txtSDT, "Số Điện Thoại không được để trống");
        } else if (diaChi.equals("")) {
            return showErrorTx(txtDiaChi, "Địa chỉ không được để trống");
        } else if (ngaySinh==null) {
            return showErrorTx(null, "Ngày sinh không được để trống");
        } else if (gioiTinh==null) {
            return showErrorTx(null, "Giới tính không được để trống");
        } else {
            
        }

        return true;
    }
    
    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        if(tx!=null) {
            tx.requestFocus();
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVienInfo;
    private javax.swing.JPanel ThaoTac;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel calendar;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JPanel hienThiTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
