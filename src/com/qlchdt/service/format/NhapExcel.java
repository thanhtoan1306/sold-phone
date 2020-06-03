/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.service.format;

import com.qlchdt.model.KhachHang;
import com.qlchdt.service.KhachHangService;
import com.qlchdt.view.custom.LuaChonGhiExcel;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author User
 */
public class NhapExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);

    public NhapExcel() {

    }

    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    //Đọc file excel Khách hàng
    public void docFileExcelKhachhang() {
        fd.setTitle("Nhập dữ liệu khách hàng từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String diachi = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();

                    KhachHangService qlkhBUS = new KhachHangService();
                    KhachHang khOLD = qlkhBUS.getKhachHang(ma);

                    if (khOLD != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Tên", "Địa chỉ", "SDT"});
                            mtb.addRow(new String[]{
                                "Cũ:", khOLD.getMaKH(),
                                khOLD.getTenKH(),
                                khOLD.getDiaChi(),
                                khOLD.getSDT(),});
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten, diachi, sdt});

                            LuaChonGhiExcel mop = new LuaChonGhiExcel(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            qlkhBUS.update(ma, ten, diachi, sdt);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        KhachHang kh = new KhachHang(ma, ten, diachi, sdt);
                        qlkhBUS.add(kh);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

}
