class YeuCauBaoTri {
    private String id;
    private SinhVien nguoiGui;
    private Phong phong;
    private String noiDung;
    private String trangThai; // "Chưa xử lý", "Đang xử lý", "Hoàn thành"

    public YeuCauBaoTri(String id, SinhVien nguoiGui, Phong phong, String noiDung) {
        this.id = id;
        this.nguoiGui = nguoiGui;
        this.phong = phong;
        this.noiDung = noiDung;
        this.trangThai = "Chưa xử lý";
    }

    public String getId() { return id; }
    public SinhVien getNguoiGui() { return nguoiGui; }
    public Phong getPhong() { return phong; }
    public String getNoiDung() { return noiDung; }
    public String getTrangThai() { return trangThai; }

    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "YC: " + id + " | SV: " + nguoiGui.getHoTen() + " | Phòng: " + (phong!=null?phong.getTenPhong():"Chưa") +
                " | Nội dung: " + noiDung + " | Trạng thái: " + trangThai;
    }
}
