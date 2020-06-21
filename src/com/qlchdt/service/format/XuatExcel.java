/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service.format;

import com.qlchdt.model.ChiTietHoaDon;
import com.qlchdt.model.HoaDon;
import com.qlchdt.model.KhachHang;
import com.qlchdt.model.KhuyenMai;
import com.qlchdt.model.NhaCungCap;
import com.qlchdt.service.ChiTietHoaDonService;
import com.qlchdt.service.HoaDonService;
import com.qlchdt.service.KhachHangService;
import com.qlchdt.service.KhuyenMaiService;
import com.qlchdt.service.NhaCungCapService;
import com.qlchdt.service.NhanVienService;
import com.qlchdt.service.SanPhamService;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author User
 */

public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private String getFile() {
        fd.setFile("excel.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
        // Xuất file Excel Khách hàng
    public void xuatFileExcelKhachHang() {
        
        fd.setTitle("Xuất dữ liệu khách hàng ra excel");
        
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khách hàng");

            KhachHangService qlkh = new KhachHangService();
            ArrayList<KhachHang> list = qlkh.getDskh();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(2, CellType.STRING).setCellValue("Tên khách hàng");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");            

            for (KhachHang kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(kh.getMaKH());
                row.createCell(2, CellType.STRING).setCellValue(kh.getTenKH());
                row.createCell(3, CellType.STRING).setCellValue(kh.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(kh.getSDT());                
            }
            
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi excel thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     // Xuất file Excel Khuyến mãi
    public void xuatFileExcelKhuyenMai() {
        fd.setTitle("Xuất dữ liệu khuyến mãi ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khuyến mãi");

            KhuyenMaiService qlkmBUS = new KhuyenMaiService();
            ArrayList<KhuyenMai> list = qlkmBUS.getDskm();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khuyến mãi");
            row.createCell(2, CellType.STRING).setCellValue("Tên khuyến mãi");
            row.createCell(3, CellType.NUMERIC).setCellValue("Điều kiện");
            row.createCell(4, CellType.NUMERIC).setCellValue("Phần trăm");
            row.createCell(5, CellType.STRING).setCellValue("Ngày bắt đầu");
            row.createCell(6, CellType.STRING).setCellValue("Ngày kết thúc");

            for (KhuyenMai km : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(km.getMaKM());
                row.createCell(2, CellType.STRING).setCellValue(km.getTenKM());
                row.createCell(3, CellType.NUMERIC).setCellValue(km.getDieuKienKM());
                row.createCell(4, CellType.NUMERIC).setCellValue(km.getPhanTramKM());
                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(km.getNgayBD()));
                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(km.getNgayKT()));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
            // Xuất file Excel Nhà cung cấp
    public void xuatFileExcelNhaCungCap() {
        
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");

            NhaCungCapService qlncc = new NhaCungCapService();
            ArrayList<NhaCungCap> list = qlncc.getDsncc();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhà cung cấp");
            row.createCell(2, CellType.STRING).setCellValue("Tên nhà cung cấp");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");            

            for (NhaCungCap ncc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(ncc.getMaNCC());
                row.createCell(2, CellType.STRING).setCellValue(ncc.getTenNCC());
                row.createCell(3, CellType.STRING).setCellValue(ncc.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(ncc.getSDT());                
            }
            
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi excel thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
        // Xuất file Excel Hóa đơn
    public void xuatFileExcelHoaDon() {
        fd.setTitle("Xuất dữ liệu hóa đơn ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");

            HoaDonService qlhdBUS = new HoaDonService();
            ChiTietHoaDonService qlcthdBUS = new ChiTietHoaDonService();
            NhanVienService qlnvBUS = new NhanVienService();
            KhachHangService qlkhBUS = new KhachHangService();
            KhuyenMaiService qlkmBUS = new KhuyenMaiService();
            SanPhamService qlspBUS = new SanPhamService();
            ArrayList<HoaDon> list = qlhdBUS.getDshd();

            int rownum = 0;
            int sttHoaDon = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(2, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(4, CellType.STRING).setCellValue("Mã khuyến mãi");
            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
            row.createCell(6, CellType.STRING).setCellValue("Giờ lập");
            row.createCell(7, CellType.STRING).setCellValue("Tổng tiền");

            row.createCell(8, CellType.STRING).setCellValue("Sản phẩm");
            row.createCell(9, CellType.STRING).setCellValue("Số lượng");
            row.createCell(10, CellType.STRING).setCellValue("Đơn giá");
            row.createCell(11, CellType.STRING).setCellValue("Thành tiền");

            for (HoaDon hd : list) {
                rownum++;
                sttHoaDon++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
                row.createCell(1, CellType.STRING).setCellValue(hd.getMaHoaDon());
                row.createCell(2, CellType.STRING).setCellValue(hd.getMaNhanVien() + " - " + qlnvBUS.getNhanVien(hd.getMaNhanVien()).getTenNV());
                row.createCell(3, CellType.STRING).setCellValue(hd.getMaKhachHang() + " - " + qlkhBUS.getKhachHang(hd.getMaKhachHang()).getTenKH());
                row.createCell(4, CellType.STRING).setCellValue(hd.getMaKhuyenMai() + " - " + qlkmBUS.getKhuyenMai(hd.getMaKhuyenMai()).getTenKM());
                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(hd.getNgayLap()));
                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(hd.getGioLap()));
                row.createCell(7, CellType.NUMERIC).setCellValue(hd.getTongTien());

                for (ChiTietHoaDon cthd : qlcthdBUS.getAllChiTiet(hd.getMaHoaDon())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    String masp = cthd.getMaSanPham();

                    row.createCell(8, CellType.STRING).setCellValue(masp + " - " + qlspBUS.getSanPham(masp).getTenSP());
                    row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getSoLuong());
                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getDonGia());
                    row.createCell(11, CellType.NUMERIC).setCellValue(cthd.getDonGia() * cthd.getSoLuong());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
