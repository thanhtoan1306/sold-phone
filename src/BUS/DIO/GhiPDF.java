/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS.DIO;

import GUI.Custom.PriceFormatter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import BUS.ChiTietHoaDonService;
import BUS.ChiTietPhieuNhapService;
import BUS.HoaDonService;
import BUS.KhachHangService;
import BUS.KhuyenMaiService;
import BUS.NhaCungCapService;
import BUS.NhanVienService;
import BUS.PhieuNhapService;
import BUS.SanPhamService;
import DTO.Model.ChiTietHoaDon;
import DTO.Model.ChiTietPhieuNhap;
import DTO.Model.HoaDon;
import DTO.Model.KhuyenMai;
import DTO.Model.PhieuNhap;
import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class GhiPDF {

    Document document;
    FileOutputStream file;
    Font fontData;
    Font fontTitle;
    Font fontHeader;

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public GhiPDF() {
        try {
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL, BaseColor.BLUE);
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(GhiPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy đường dẫn file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
//            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
            ex.printStackTrace();
        }
    }

    public void writeObject(String[] data) {
        Paragraph pdfData = new Paragraph();
        for (int i = 0; i < data.length; i++) {
            pdfData.add(data[i] + "  ");
        }
        try {
            document.add(pdfData);
        } catch (DocumentException ex) {
            Logger.getLogger(GhiPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeTable(JTable _table) {
        PdfPTable pdfTable = new PdfPTable(_table.getColumnCount());
        for (int i = 0; i < _table.getRowCount(); i++) {
            for (int j = 0; j < _table.getColumnCount(); j++) {
                String data = String.valueOf(_table.getValueAt(i, j));
                PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
                pdfTable.addCell(cell);
            }
        }
        try {
            document.add(pdfTable);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Không gọi được document !");
        }
    }


    private String getFile() {
        fd.setFile("united.pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    public void writeHoaDon(String mahd) {
        String url = "";
        KhuyenMai khuyenMai = new KhuyenMai();
        try {
            fd.setTitle("In hóa đơn");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            setTitle("Thông tin hóa đơn");
            //Hien thong tin cua hoa don hien tai
            HoaDonService qlhd = new HoaDonService();
            KhachHangService qlkh = new KhachHangService();
            NhanVienService qlnv = new NhanVienService();
            KhuyenMaiService qlkm = new KhuyenMaiService();
            SanPhamService qlsp = new SanPhamService();
            ChiTietHoaDonService qlcthd = new ChiTietHoaDonService();
            HoaDon hd = qlhd.getHoaDon(mahd);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Hóa đơn: " + String.valueOf(hd.getMaHoaDon()), fontData));

            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Khách hàng: " + qlkh.getKhachHang(hd.getMaKhachHang()).getTenKH() + "  -  " + hd.getMaKhachHang()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(hd.getNgayLap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + qlnv.getNhanVien(hd.getMaNhanVien()).getTenNV() + "  -  " + hd.getMaNhanVien()));
            para2.add(glue);
            para2.add("Giờ lập: " + String.valueOf(hd.getGioLap()));

            Paragraph paraKhuyenMai = new Paragraph();
            paraKhuyenMai.setPaddingTop(30);
            paraKhuyenMai.setFont(fontData);
            paraKhuyenMai.add("Khuyến mãi: " + qlkm.getKhuyenMai(hd.getMaKhuyenMai()).getTenKM());

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(paraKhuyenMai);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(5);
            float tongKhuyenMai = 0;
            float tongThanhTien = 0;
            float tongTienSauKhuyenMai = 0;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (ChiTietHoaDon cthd : qlcthd.getAllChiTiet(mahd)) {

                String ma = cthd.getMaSanPham();
                String ten = qlsp.getSanPham(cthd.getMaSanPham()).getTenSP();
                String gia = PriceFormatter.format(cthd.getDonGia());
                String soluong = String.valueOf(cthd.getSoLuong());
                String thanhtien = PriceFormatter.format(cthd.getDonGia() * cthd.getSoLuong());

                pdfTable.addCell(new PdfPCell(new Phrase(ma, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(gia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soluong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhtien, fontData)));

                tongThanhTien += cthd.getDonGia() * cthd.getSoLuong();
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);
            khuyenMai = qlkm.getKhuyenMai(hd.getMaKhuyenMai());
            
            if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongThanhTien) {
                tongKhuyenMai = tongThanhTien * khuyenMai.getPhanTramKM() / 100;
                tongTienSauKhuyenMai = tongThanhTien - tongKhuyenMai;                             
            } else {
                tongTienSauKhuyenMai = tongThanhTien;
            }

            //tongKhuyenMai = tongThanhTien - hd.getTongTien();

            Paragraph paraTongThanhTien = new Paragraph(new Phrase("Tổng thành tiền: " + PriceFormatter.format(tongThanhTien), fontData));
            paraTongThanhTien.setIndentationLeft(300);
            document.add(paraTongThanhTien);
            Paragraph paraTongKhuyenMai = new Paragraph(new Phrase("Tổng khuyến mãi: " + PriceFormatter.format(tongKhuyenMai), fontData));
            paraTongKhuyenMai.setIndentationLeft(300);
            document.add(paraTongKhuyenMai);
            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(tongTienSauKhuyenMai), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();

            JOptionPane.showMessageDialog(null, "Ghi file pdf thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }

    public void writePhieuNhap(String mapn) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            setTitle("Thông tin phiếu nhập");

            PhieuNhapService qlpnBUS = new PhieuNhapService();
            ChiTietPhieuNhapService qlctpnBUS = new ChiTietPhieuNhapService();
            SanPhamService qlspBUS = new SanPhamService();
            NhaCungCapService qlnccBUS = new NhaCungCapService();
            NhanVienService qlnvBUS = new NhanVienService();

            PhieuNhap pn = qlpnBUS.getPhieuNhap(mapn);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Phiếu nhập: " + String.valueOf(pn.getMaPN()), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Nhà cung cấp: " + qlnccBUS.getNhaCungCap(pn.getMaNCC()).getTenNCC() + "  -  " + pn.getMaNCC()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(pn.getNgayNhap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            //para2.add(String.valueOf("Nhân viên: " + qlnvBUS.getNhanVien(pn.getMaNV()).getTenNV() + "  -  " + pn.getMaNV()));
            para2.add("Nhân viên: ");
            para2.add(glue);
            para2.add("Giờ lập: " + String.valueOf(pn.getGioNhap()));

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(5);
            PdfPCell cell;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                pdfTable.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table
            for (ChiTietPhieuNhap ctpn : qlctpnBUS.getAllChiTiet(mapn)) {
                pdfTable.addCell(new PdfPCell(new Phrase(ctpn.getMaSP(), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(qlspBUS.getSanPham(ctpn.getMaSP()).getTenSP(), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(PriceFormatter.format(ctpn.getDonGia()), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ctpn.getsLuong()), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(PriceFormatter.format(ctpn.getDonGia() * ctpn.getsLuong()), fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(pn.getTongTien()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }

    public void closeFile() {
        document.close();
    }
}
