public class HoaDon {
    private String maHoaDon;
    private Phong phong;
    private int soKwh;
    private int soM3;
    private double tienNha;
    private double tienDien;
    private double tienNuoc;
    private double tongTien;
    private boolean daThanhToan = false;
    private String thang; // ví dụ "2025-10"

    public HoaDon(String maHoaDon, Phong phong, int soKwh, int soM3, double tienNha, double giaDien, double giaNuoc, String thang) {
        this.maHoaDon = maHoaDon;
        this.phong = phong;
        this.soKwh = soKwh;
        this.soM3 = soM3;
        this.tienNha = tienNha;
        this.tienDien = soKwh * giaDien;
        this.tienNuoc = soM3 * giaNuoc;
        this.tongTien = tienNha + this.tienDien + this.tienNuoc;
        this.thang = thang;
    }

    public String getMaHoaDon() { return maHoaDon; }
    public Phong getPhong() { return phong; }
    public double getTongTien() { return tongTien; }
    public boolean isDaThanhToan() { return daThanhToan; }
    public String getThang() { return thang; }

    public void thanhToan() {
        daThanhToan = true;
        if (phong != null) phong.xacNhanThanhToan();
    }

    @Override
    public String toString() {
        return "Hóa đơn " + maHoaDon + " | Phòng: " + (phong != null ? phong.getTenPhong() : "N/A") +
                " | Tháng: " + thang + " | Tổng: " + tongTien + " VND" +
                " | Đã trả: " + (daThanhToan ? "Có" : "Chưa");
    }

    
    public void capNhatChiPhi(double tienNha, int soKwh, int soM3) {
        this.tienNha = tienNha;
        this.soKwh = soKwh;
        this.soM3 = soM3;
    }
    public double tinhTongTien() {
    return tienNha + soKwh * 3500 + soM3 * 15000;
    }


}
