import java.util.ArrayList;

class BaoCao {
    // Thống kê số phòng theo trạng thái
    public static void thongKePhong(NguoiQuanLy ql) {
        int tong = 0;
        int conTrong = 0, day = 0, dangSua = 0;
        for (Phong p : ql.getDanhSachPhong()) {
            tong++;
            String tt = p.getTrangThai();
            if ("Đầy".equalsIgnoreCase(tt)) day++;
            else if ("Đang sửa chữa".equalsIgnoreCase(tt)) dangSua++;
            else conTrong++;
        }
        System.out.println("\n===== BÁO CÁO PHÒNG =====");
        System.out.println("Tổng phòng: " + tong);
        System.out.println("Còn trống: " + conTrong);
        System.out.println("Đầy: " + day);
        System.out.println("Đang sửa chữa: " + dangSua);
    }

    // Thống kê thanh toán
    public static void thongKeThanhToan(NguoiQuanLy ql) {
        double tongThu = 0;
        int soChuaTT = 0;
        ArrayList<HoaDon> tatCa = ql.getTatCaHoaDon();
        for (HoaDon hd : tatCa) {
            if (hd.isDaThanhToan()) tongThu += hd.getTongTien();
            else soChuaTT++;
        }
        System.out.println("\n===== BÁO CÁO THANH TOÁN =====");
        System.out.println("Tổng thu: " + tongThu + " VND");
        System.out.println("Số hóa đơn chưa thanh toán: " + soChuaTT);
    }

    // Thống kê yêu cầu bảo trì
    public static void thongKeBaoTri(NguoiQuanLy ql) {
        int tong = ql.getTatCaYeuCau().size();
        int chua = 0, dang = 0, xong = 0;
        for (YeuCauBaoTri yc : ql.getTatCaYeuCau()) {
            String tt = yc.getTrangThai();
            if ("Chưa xử lý".equalsIgnoreCase(tt)) chua++;
            else if ("Đang xử lý".equalsIgnoreCase(tt)) dang++;
            else xong++;
        }
        System.out.println("\n===== BÁO CÁO BẢO TRÌ =====");
        System.out.println("Tổng yêu cầu: " + tong);
        System.out.println("Chưa xử lý: " + chua);
        System.out.println("Đang xử lý: " + dang);
        System.out.println("Hoàn thành: " + xong);
    }
}
