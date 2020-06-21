/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormThemSua;

import BUS.SanPhamService;
import DTO.Model.SanPham;
import GUI.Custom.HuyButton;
import GUI.Custom.MyTable;
import GUI.Custom.PriceFormatter;
import GUI.Custom.SuaButton;
import GUI.Custom.ThemButton;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class ThemSuaSanPham extends javax.swing.JFrame {
    Path sanphamImagePath;
    Path imageLocation;
    String type;
    SanPhamService sps = new SanPhamService();
    SanPham spSua;

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    HuyButton btnHuy = new HuyButton();

    public ThemSuaSanPham(String _type, String _masp) {
        this.type = _type;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // không thể tắt khi ấn vào X
        
        URL url = this.getClass().getResource("/DTO/Assets/Products");
        
        try {
            sanphamImagePath = Paths.get(url.toURI()).toFile().toPath();
            //System.out.println(nhanVienImagePath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ThemSuaSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm sản phẩm");

          
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa sản phẩm");
            for (SanPham sp : sps.getDssp()) {
                if (sp.getMaSP().equals(_masp)) {
                    this.spSua = sp;
                }
            }
            if (this.spSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy sản phẩm");
                this.dispose();
            }

            txtMaSP1.setText(this.spSua.getMaSP());
            txtMaHSP1.setText(this.spSua.getMaHSP());
            txtDonGia1.setText(Double.toString(spSua.getDonGia()));  // cho dễ sửa
            txtTenSP1.setText(this.spSua.getTenSP());
            txtSoLuong1.setText(Integer.toString(spSua.getSoLuong()));
            
            int w = lblHinhAnh1.getWidth();
            int h = lblHinhAnh1.getHeight();
            // nhớ sửa đường dẫn employees thành phones
            ImageIcon img = new ImageIcon(getClass().getResource("/DTO/Assets/Products/"+spSua.getFileNameHinhAnh()));
            Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblHinhAnh1.setIcon(img);
            lblHinhAnh1.setIcon(new ImageIcon(imgScaled));
            String hinhAnhGoc = spSua.getFileNameHinhAnh().toString();
            imageLocation = new File(sanphamImagePath + System.getProperty("file.separator") + hinhAnhGoc).toPath();

            //txTentk.setEditable(false);

            
            plButton.add(btnSua);
        }
        
      
        plButton.add(btnHuy);

        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        this.setVisible(true);

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
   
    
    public boolean validateForm(){
        if (txtMaSP1.getText().isEmpty()||txtMaHSP1.getText().isEmpty()||txtTenSP1.getText().isEmpty()||txtSoLuong1.getText().isEmpty()||txtDonGia1.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private void btnThemMouseClicked() {

        if(validateForm()){
            String masp = txtMaSP1.getText();
            String mahsp = txtMaHSP1.getText();
            if (!masp.substring(0,2).equals(mahsp)) {
                JOptionPane.showMessageDialog(null, "Mã sản phẩm và Hãng sản phẩm phải trùng 2 kí tự đầu! Mời nhập lại!");
                return;
            }
            String tensp = txtTenSP1.getText();
            String sluong= txtSoLuong1.getText();
            
            // Check isNumber
            int soluong=-1;
            try {
                soluong = Integer.parseInt(sluong);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số lượng phải nhập số nguyên! Mời bạn nhập lại!");
                return; // stop right here, you 
            }
            float dongia=-1;
            try {
                String dgia = txtDonGia1.getText();
                dongia = Float.parseFloat(dgia);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Đơn giá phải nhập số! Mời bạn nhập lại!");
                return; // stop right here, you you 
            }
            // check negative
            if (soluong<1 || dongia<=0) {
                JOptionPane.showMessageDialog(null, "Số lượng và đơn giá phải đều là số dương! Mời nhập lại!");
                return; // stop right here, you you you
            }
            
            if (imageLocation==null) {
                try {
                    imageLocation = Paths.get(this.getClass().getResource("/DTO/Assets/Icons/empty_product_icon.png").toURI()).toFile().toPath();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(ThemSuaSanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String hinhanh = imageLocation.getFileName().toString();
            try {
                // copy anh vao assets/employees sau khi chon anh
                String targetPath = sanphamImagePath + System.getProperty("file.separator") + hinhanh;

                //File srcPath = new File(System.getProperty("sp.dir")+"/DTO/Assets/Products/"+hinhanh);
                
                File srcPath = new File(System.getProperty("user.dir")+"/src/DTO/Assets/Products/"+hinhanh);

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
                Logger.getLogger(ThemSuaSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (sps.add(masp, mahsp, tensp, dongia, soluong, hinhanh)) {  // ok thêm và lưu ok
                JOptionPane.showMessageDialog(this, "Thêm thành công ");
                this.dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        this.dispose();
    }

    private void btnSuaMouseClicked() {

        String masp = txtMaSP1.getText().trim().toUpperCase();
        String mahsp = txtMaHSP1.getText().trim().toUpperCase();
        if (!masp.substring(0,2).equals(mahsp)) {
            JOptionPane.showMessageDialog(null, "Mã sản phẩm và Hãng sản phẩm phải trùng 2 kí tự đầu! Mời nhập lại!");
            return;
        }
        String tensp = txtTenSP1.getText().trim();
        String sluong= txtSoLuong1.getText();
        
        // Check isNumber
        int soluong=-1;
        try {
            soluong = Integer.parseInt(sluong);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số lượng phải nhập số nguyên! Mời bạn nhập lại!");
            return; // stop right here, you 
        }
        float dongia=-1;
        try {
            String dgia = txtDonGia1.getText();
            dongia = Float.parseFloat(dgia);
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Đơn giá phải nhập số! Mời bạn nhập lại!");
            return; // stop right here, you you 
        }
        // check negative
        if (soluong<1 || dongia<=0) {
            JOptionPane.showMessageDialog(null, "Số lượng và đơn giá phải đều là số dương! Mời nhập lại!");
            return; // stop right here, you you you
        }

        String hinhanh = imageLocation.getFileName().toString();
        try {
            // copy anh vao assets/products sau khi chon anh
            String targetPath = sanphamImagePath + System.getProperty("file.separator") + hinhanh;

           // File srcPath = new File(System.getProperty("sp.dir")+"/DTO/Assets/Products/"+hinhanh);
            File srcPath = new File(System.getProperty("user.dir")+"/src/DTO/Assets/Products/"+hinhanh);

            
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
            spSua = new SanPham(masp, mahsp, tensp, dongia, soluong, hinhanh);  // cái sua đễ chỗ thêm haizzz
            if (sps.update(masp, mahsp, tensp, dongia, soluong, hinhanh)) {
                JOptionPane.showMessageDialog(null, "Sửa thành công !");
                this.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThemSuaSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* private Boolean checkEmpty() {
        String tentk = txTentk.getText();
        String matkhau = txMatKhau.getText();
        String maquyen = txMaQuyen.getText();
        String manv = txMaNV.getText();

        if (manv.trim().equals("")) {
            return showErrorTx(txMaNV, "Mã nhân viên không được để trống");

        } else if (tentk.trim().equals("")) {
            return showErrorTx(txTentk, "Tên tài khoản không được để trống");

        } else if (matkhau.trim().equals("")) {
            return showErrorTx(txMatKhau, "Mật khẩu không được để trống");

        } else if (maquyen.trim().equals("")) {
            return showErrorTx(txMaQuyen, "Mã quyền không được để trống");
        }

        return true;
    }*/

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plButton = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaSP1 = new javax.swing.JTextField();
        txtMaHSP1 = new javax.swing.JTextField();
        txtTenSP1 = new javax.swing.JTextField();
        txtSoLuong1 = new javax.swing.JTextField();
        txtDonGia1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblHinhAnh1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        plButton.setBackground(new java.awt.Color(255, 255, 255));
        plButton.setPreferredSize(new java.awt.Dimension(576, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 102, 0))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Mã Sản Phẩm");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Hãng Sản Phẩm");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Tên Sản Phẩm");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Số Lượng");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Đơn Giá (triệu)");

        txtMaSP1.setBackground(new java.awt.Color(204, 204, 204));

        txtMaHSP1.setBackground(new java.awt.Color(204, 204, 204));

        txtTenSP1.setBackground(new java.awt.Color(204, 204, 204));

        txtSoLuong1.setBackground(new java.awt.Color(204, 204, 204));

        txtDonGia1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSoLuong1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                        .addComponent(txtTenSP1)
                        .addComponent(txtMaHSP1)
                        .addComponent(txtMaSP1))
                    .addComponent(txtDonGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblHinhAnh1.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhAnh1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblHinhAnh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnh1MouseClicked(evt);
            }
        });
        jPanel2.add(lblHinhAnh1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(527, 527, 527)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(plButton, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(plButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhAnh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnh1MouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        if (lblHinhAnh1.getIcon()!=null) {
            //
        }
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file: " +
                //        chooser.getSelectedFile().getName());

            imageLocation = chooser.getSelectedFile().toPath(); 
            
            int w = lblHinhAnh1.getWidth();
            int h = lblHinhAnh1.getHeight();
            ImageIcon img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblHinhAnh1.setIcon(img);
            lblHinhAnh1.setIcon(new ImageIcon(imgScaled));
        }
    }//GEN-LAST:event_lblHinhAnh1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblHinhAnh1;
    private javax.swing.JPanel plButton;
    private javax.swing.JTextField txtDonGia1;
    private javax.swing.JTextField txtMaHSP1;
    private javax.swing.JTextField txtMaSP1;
    private javax.swing.JTextField txtSoLuong1;
    private javax.swing.JTextField txtTenSP1;
    // End of variables declaration//GEN-END:variables
}
