class Nguoi {
    protected String id;
    protected String hoTen;
    protected String email;
    protected String matKhau;
    protected String soDienThoai;

    public Nguoi(String id, String hoTen, String email, String matKhau, String soDienThoai) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
    }

    public String getId() { return id; }
    public String getHoTen() { return hoTen; }
    public String getEmail() { return email; }
    public String getMatKhau() { return matKhau; }
    public String getSoDienThoai() { return soDienThoai; }

    public void hienThiThongTin() {
        System.out.println("👤 " + hoTen + " (" + email + ")");
    }
    public boolean dangNhap(String emailNhap, String matKhauNhap) {
        return this.email.equals(emailNhap) && this.matKhau.equals(matKhauNhap);
    }
}
