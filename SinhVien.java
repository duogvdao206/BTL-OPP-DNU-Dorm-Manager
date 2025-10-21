import java.util.ArrayList;
import java.util.List;

class SinhVien extends Nguoi {
    private List<String> lichSuHoatDong = new ArrayList<>();
    private Phong phongDangO;
    // lưu mã phòng (khi đọc file)
    private String maPhongDangKy = "N/A";
    // danh sách hóa đơn liên quan tới sinh viên (tham chiếu)
    private List<HoaDon> hoaDons = new ArrayList<>();

    public SinhVien(String id, String hoTen, String email, String matKhau, String soDienThoai) {
        super(id, hoTen, email, matKhau, soDienThoai);
    }

    // constructor hỗ trợ đọc file có thêm maPhong
    public SinhVien(String id, String hoTen, String email, String matKhau, String soDienThoai, String maPhong) {
        this(id, hoTen, email, matKhau, soDienThoai);
        if (maPhong != null && !maPhong.trim().isEmpty()) this.maPhongDangKy = maPhong;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println(Color.CYAN + "🎓 [Sinh viên] " + hoTen + " (" + email + ")" + Color.RESET);
        System.out.println("📞 SĐT: " + soDienThoai);
        System.out.println("🆔 Mã SV: " + id);
        if (phongDangO != null) {
            System.out.println("🏠 Phòng: " + phongDangO.getTenPhong() + " (" + phongDangO.getMaPhong() + ")");
        } else {
            System.out.println("🏠 Phòng: Chưa đăng ký");
        }
    }

    public boolean dangNhap(String emailNhap, String matKhauNhap) {
        return this.email.equals(emailNhap) && this.matKhau.equals(matKhauNhap);
    }

    public void setMatKhau(String matKhauMoi) {
        this.matKhau = matKhauMoi;
    }

    public void capNhatThongTin(String ten, String emailMoi) {
        this.hoTen = ten;
        this.email = emailMoi;
        System.out.println(Color.GREEN + "✅ Thông tin sinh viên đã được cập nhật!" + Color.RESET);
    }

    public void xemLichSuHoatDong() {
        System.out.println(Color.YELLOW + "📜 Lịch sử hoạt động của sinh viên " + hoTen + ":" + Color.RESET);
        if (lichSuHoatDong.isEmpty()) {
            System.out.println("Chưa có hoạt động nào.");
        } else {
            for (String hd : lichSuHoatDong) {
                System.out.println("- " + hd);
            }
        }
    }

    public void themHoatDong(String hd) {
        lichSuHoatDong.add(hd);
    }

    public void dangKyPhong(Phong p) {
        if (phongDangO == null) {
            p.themSinhVien(this);
            if (p.hasSinhVien(this)) {
                phongDangO = p;
                maPhongDangKy = p.getMaPhong();
                themHoatDong("Đăng ký phòng " + p.getTenPhong());
                System.out.println(Color.GREEN + "✅ Đăng ký phòng " + p.getTenPhong() + " thành công!" + Color.RESET);
            }
        } else {
            System.out.println(Color.RED + "❌ Bạn đã đăng ký phòng " + phongDangO.getTenPhong() + " rồi! Hãy hủy trước khi chọn phòng khác." + Color.RESET);
        }
    }

    public void xemPhongDangO() {
        if (phongDangO != null) {
            System.out.println("🏠 Bạn đang ở phòng: " + phongDangO.getTenPhong());
        } else {
            System.out.println("⚠️ Bạn chưa đăng ký phòng nào!");
        }
    }

    public void xemThongBaoPhong() {
        if (phongDangO != null) {
            phongDangO.xemThongBao();
        } else {
            System.out.println("⚠️ Bạn chưa ở phòng nào nên không có thông báo.");
        }
    }

    public void thanhToan() {
        if (phongDangO != null) {
            if (!phongDangO.isDaThanhToan()) {
                phongDangO.xacNhanThanhToan();
                themHoatDong("Thanh toán tiền phòng " + phongDangO.getTenPhong());
            } else {
                System.out.println("✅ Bạn đã thanh toán tháng này rồi!");
            }
        } else {
            System.out.println("⚠️ Bạn chưa đăng ký phòng, không thể thanh toán!");
        }
    }

    public void huyDangKyPhong() {
        if (phongDangO != null) {
            Phong old = phongDangO;
            phongDangO.xoaSinhVien(this);
            themHoatDong("Hủy đăng ký phòng " + old.getTenPhong());
            System.out.println(Color.RED + "❌ Bạn đã hủy đăng ký phòng " + old.getTenPhong() + Color.RESET);
            phongDangO = null;
            maPhongDangKy = "N/A";
        } else {
            System.out.println(Color.YELLOW + "⚠️ Bạn chưa đăng ký phòng nào để hủy!" + Color.RESET);
        }
    }

    // ===== TÍCH HỢP HÓA ĐƠN =====
    public void themHoaDon(HoaDon hd) {
        hoaDons.add(hd);
    }

    public void xemHoaDon() {
        if (hoaDons.isEmpty()) {
            System.out.println("📭 Không có hóa đơn nào liên quan tới bạn.");
            return;
        }
        System.out.println(Color.BLUE + "\n📄 Hóa đơn của " + hoTen + ":" + Color.RESET);
        for (HoaDon hd : hoaDons) {
            System.out.println(hd);
        }
    }

    // ===== YÊU CẦU BẢO TRÌ =====
    public void guiYeuCauBaoTri(NguoiQuanLy ql, String noiDung) {
        YeuCauBaoTri yc = new YeuCauBaoTri("YC" + System.currentTimeMillis(), this, (phongDangO != null ? phongDangO : null), noiDung);
        ql.nhanYeuCau(yc);
        themHoatDong("Gửi yêu cầu bảo trì: " + noiDung);
        System.out.println(Color.GREEN + "✅ Đã gửi yêu cầu bảo trì tới quản lý." + Color.RESET);
    }

    public Phong getPhongDangO() {
        return phongDangO;
    }
    // trả về mã phòng (lưu khi đọc file)
    public String getMaPhong() {
        return maPhongDangKy;
    }

    // những getter tương thích
    public String getId() { return id; }
    public String getHoTen() { return hoTen; }
    public String getEmail() { return email; }
    public String getMatKhau() { return matKhau; }
    public String getSoDienThoai() { return soDienThoai; }
}   
