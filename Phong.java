import java.util.ArrayList;
import java.util.List;

class Phong {
    private String maPhong;
    private String tenPhong;
    private int sucChua; // số sinh viên tối đa
    private ArrayList<SinhVien> danhSachSV = new ArrayList<>();

    // trạng thái phòng: "Còn trống", "Đang sửa chữa", "Đầy"
    private String trangThai = "Còn trống";

    private List<String> thongBao = new ArrayList<>();
    private boolean daThanhToan = false;

    public Phong(String maPhong, String tenPhong, int sucChua) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.sucChua = sucChua;
    }

    public String getMaPhong() { return maPhong; }
    public String getTenPhong() { return tenPhong; }
    public int getSucChua() { return sucChua; }
    public int getSoLuongHienTai() { return danhSachSV.size(); }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public void themSinhVien(SinhVien sv) {
        if (!"Còn trống".equalsIgnoreCase(trangThai) && !"".equals(trangThai)) {
            System.out.println(Color.RED + "❌ Phòng đang không cho đăng ký (trạng thái: " + trangThai + ")" + Color.RESET);
            return;
        }
        if (danhSachSV.size() < sucChua) {
            danhSachSV.add(sv);
            System.out.println(Color.GREEN + "✅ Sinh viên " + sv.getHoTen() + " đã được thêm vào phòng " + tenPhong + Color.RESET);
            if (danhSachSV.size() == sucChua) {
                trangThai = "Đầy";
            }
        } else {
            System.out.println(Color.RED + "❌ Phòng " + tenPhong + " đã đầy (" + sucChua + " người)!" + Color.RESET);
        }
    }

    public void hienThiThongTin() {
        System.out.println("🏠 Mã phòng: " + maPhong + " | Tên: " + tenPhong +
                " | Sức chứa: " + sucChua +
                " | Đang ở: " + danhSachSV.size() +
                " | Trạng thái: " + trangThai);
    }

    public void nhanThongBao(String tb) {
        thongBao.add(tb);
    }

    public void xemThongBao() {
        System.out.println(Color.YELLOW + "\n📬 Thông báo của phòng " + tenPhong + ":" + Color.RESET);
        if (thongBao.isEmpty()) {
            System.out.println("(Không có thông báo mới)");
        } else {
            for (String tb : thongBao) {
                System.out.println("- " + tb);
            }
        }
    }

    public void xacNhanThanhToan() {
        daThanhToan = true;
        System.out.println(Color.GREEN + "💰 Phòng " + tenPhong + " đã thanh toán xong!" + Color.RESET);
    }

    public boolean isDaThanhToan() { return daThanhToan; }

    public void xoaSinhVien(SinhVien sv) {
        if (danhSachSV.contains(sv)) {
            danhSachSV.remove(sv);
            System.out.println(Color.YELLOW + "⚠️ Đã xóa sinh viên " + sv.getHoTen() + " khỏi phòng " + tenPhong + Color.RESET);
            if (danhSachSV.size() < sucChua) {
                if (!"Đang sửa chữa".equalsIgnoreCase(trangThai))
                    trangThai = "Còn trống";
            }
        } else {
            System.out.println(Color.RED + "❌ Sinh viên này không có trong phòng!" + Color.RESET);
        }
    }

    public boolean hasSinhVien(SinhVien sv) {
        return danhSachSV.contains(sv);
    }

    public List<SinhVien> getDanhSachSV() {
        return danhSachSV;
    }
}
