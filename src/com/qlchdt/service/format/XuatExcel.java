/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service.format;

import com.qlchdt.model.KhachHang;
import com.qlchdt.model.KhuyenMai;
import com.qlchdt.service.KhachHangService;
import com.qlchdt.service.KhuyenMaiService;
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

}
