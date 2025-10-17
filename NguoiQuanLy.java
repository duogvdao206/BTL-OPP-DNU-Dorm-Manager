import java.util.ArrayList;
import java.util.List;

class NguoiQuanLy extends Nguoi {
    private String chucVu;
    private List<SinhVien> danhSachSinhVien = new ArrayList<>();

    private ArrayList<Phong> danhSachPhong = new ArrayList<>();

    // danh sách hóa đơn & yêu cầu
    private ArrayList<HoaDon> tatCaHoaDon = new ArrayList<>();
    private ArrayList<YeuCauBaoTri> tatCaYeuCau = new ArrayList<>();

    // chi phí mặc định
    private double tienNhaMoiThang = 1000000;
    private double giaDienMoiKwh = 3000;   // giá mỗi kWh
    private double giaNuocMoiM3 = 15000;  // giá mỗi m3

    public NguoiQuanLy(String id, String hoTen, String email, String matKhau, String soDienThoai, String chucVu) {
        super(id, hoTen, email, matKhau, soDienThoai);
        this.chucVu = chucVu;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println(Color.PURPLE + "🧑‍💼 [Quản lý] " + hoTen + " - " + chucVu + Color.RESET);
    }

    public void themSinhVien(SinhVien sv) {
        danhSachSinhVien.add(sv);
        System.out.println(Color.GREEN + "✅ Đã thêm sinh viên: " + sv.getHoTen() + Color.RESET);
    }

    public void xoaSinhVien(String id) {
        for (SinhVien sv : new ArrayList<>(danhSachSinhVien)) {
            if (sv.getId().equals(id)) {
                danhSachSinhVien.remove(sv);
                System.out.println(Color.RED + "❌ Đã xóa sinh viên có ID: " + id + Color.RESET);
                return;
            }
        }
        System.out.println(Color.YELLOW + "⚠️ Không tìm thấy sinh viên có ID: " + id + Color.RESET);
    }

    public void xemDanhSachSinhVien() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println(Color.YELLOW + "📭 Chưa có sinh viên nào trong hệ thống." + Color.RESET);
            return;
        }
        System.out.println(Color.BLUE + "\n📋 Danh sách sinh viên:" + Color.RESET);
        for (SinhVien sv : danhSachSinhVien) {
            sv.hienThiThongTin();
        }
    }

    public List<SinhVien> getDanhSachSinhVien() {
        return danhSachSinhVien;
    }

    public void themPhong(Phong p) {
        danhSachPhong.add(p);
        System.out.println("✅ Đã thêm phòng " + p.getTenPhong());
    }

    public void xoaPhong(String maPhong) {
        danhSachPhong.removeIf(p -> p.getMaPhong().equals(maPhong));
        System.out.println("✅ Đã xóa phòng có mã " + maPhong);
    }

    public void xemDanhSachPhong() {
        System.out.println("===== DANH SÁCH PHÒNG =====");
        if (danhSachPhong.isEmpty()) {
            System.out.println("(Chưa có phòng nào)");
            return;
        }
        for (Phong p : danhSachPhong) {
            p.hienThiThongTin();
        }
    }

    public ArrayList<Phong> getDanhSachPhong() {
        return danhSachPhong;
    }

    // tìm sinh viên theo email và mật khẩu
    public SinhVien timSinhVien(String email, String matKhau) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getEmail().equalsIgnoreCase(email) && sv.getMatKhau().equals(matKhau)) {
                return sv;
            }
        }
        return null;
    }

    // tìm phòng theo mã
    public Phong timPhong(String maPhong) {
        for (Phong p : danhSachPhong) {
            if (p.getMaPhong().equalsIgnoreCase(maPhong)) {
                return p;
            }
        }
        return null;
    }

    public void capNhatChiPhi(double nha, double dien, double nuoc) {
        this.tienNhaMoiThang = nha;
        this.giaDienMoiKwh = dien;
        this.giaNuocMoiM3 = nuoc;
        System.out.println(Color.GREEN + "✅ Đã cập nhật chi phí hàng tháng!" + Color.RESET);
    }

    public void guiThongBaoChoPhong(Phong p, String noiDung) {
        p.nhanThongBao("📢 " + noiDung);
        System.out.println(Color.BLUE + "📨 Đã gửi thông báo đến phòng " + p.getTenPhong() + Color.RESET);
    }

    public double tinhTienPhongHangThang(Phong p, int soKwh, int soM3) {
        return tienNhaMoiThang + (soKwh * giaDienMoiKwh) + (soM3 * giaNuocMoiM3);
    }

    // ===== HÓA ĐƠN =====
    public HoaDon taoHoaDon(Phong p, int soKwh, int soM3, String thang) {
        String ma = "HD" + (tatCaHoaDon.size() + 1) + "_" + System.currentTimeMillis();
        HoaDon hd = new HoaDon(ma, p, soKwh, soM3, tienNhaMoiThang, giaDienMoiKwh, giaNuocMoiM3, thang);
        tatCaHoaDon.add(hd);
        // gắn hóa đơn tới từng sinh viên trong phòng (nếu cần)
        for (SinhVien sv : p.getDanhSachSV()) {
            sv.themHoaDon(hd);
        }
        System.out.println(Color.GREEN + "✅ Đã tạo hóa đơn " + ma + " cho phòng " + p.getTenPhong() + Color.RESET);
        // gửi thông báo tới phòng
        guiThongBaoChoPhong(p, "💰 Hóa đơn tháng " + thang + ": " + hd.getTongTien() + " VND (Mã: " + ma + ")");
        return hd;
    }

    public ArrayList<HoaDon> getTatCaHoaDon() {
        return tatCaHoaDon;
    }

    // ===== YÊU CẦU BẢO TRÌ =====
    public void nhanYeuCau(YeuCauBaoTri yc) {
        tatCaYeuCau.add(yc);
        System.out.println(Color.BLUE + "📨 Quản lý nhận yêu cầu: " + yc.getId() + Color.RESET);
    }

    public ArrayList<YeuCauBaoTri> getTatCaYeuCau() {
        return tatCaYeuCau;
    }

    public void xemTatCaYeuCau() {
        if (tatCaYeuCau.isEmpty()) {
            System.out.println("📭 Chưa có yêu cầu bảo trì nào.");
            return;
        }
        System.out.println("\n===== DANH SÁCH YÊU CẦU BẢO TRÌ =====");
        for (YeuCauBaoTri yc : tatCaYeuCau) {
            System.out.println(yc);
        }
    }

    public void capNhatTrangThaiYeuCau(String id, String trangThai) {
        for (YeuCauBaoTri yc : tatCaYeuCau) {
            if (yc.getId().equals(id)) {
                yc.setTrangThai(trangThai);
                System.out.println(Color.GREEN + "✅ Cập nhật trạng thái yêu cầu " + id + " -> " + trangThai + Color.RESET);
                return;
            }
        }
        System.out.println(Color.YELLOW + "❌ Không tìm thấy yêu cầu " + id + Color.RESET);
    }

    // gửi nhắc thanh toán cho tất cả phòng chưa thanh toán
    public void guiNhacNo() {
        for (Phong p : danhSachPhong) {
            if (!p.isDaThanhToan()) {
                guiThongBaoChoPhong(p, "⚠️ Nhắc: phòng chưa thanh toán tiền tháng này. Vui lòng thanh toán sớm.");
            }
        }
        System.out.println(Color.BLUE + "📨 Đã gửi nhắc thanh toán tới các phòng chưa thanh toán." + Color.RESET);
    }
}
