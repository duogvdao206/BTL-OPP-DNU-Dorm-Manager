import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /// KHỞI TẠO QUẢN LÝ THỦ CÔNG
        ArrayList<NguoiQuanLy> danhSachQuanLy = new ArrayList<>();

        // Quản Lý 1
        NguoiQuanLy quanLy1 = new NguoiQuanLy(
                "QL01",
                "Nguyễn Văn A",
                "quanly01@gmail.com",
                "123",
                "0909123456",
                "Quản lý ký túc xá"
        );
        danhSachQuanLy.add(quanLy1);

        //Quản Lý 2
        NguoiQuanLy quanLy2 = new NguoiQuanLy(
                "QL02",
                "Nguyễn Văn B",
                "quanly02@gmail.com",
                "456",
                "0909123456",
                "Quản lý ký túc xá"
        );
        danhSachQuanLy.add(quanLy2);

        ArrayList<SinhVien> dsSV = FileHandler.docDanhSachSinhVien("sinhvien.txt");
        ArrayList<Phong> dsPhong = FileHandler.docDanhSachPhong("phong.txt");

        //Gán danh sách sinh viên và phòng cho tất cả quản lý
        for (NguoiQuanLy ql : danhSachQuanLy) {
            for (SinhVien sv : dsSV) {
                ql.themSinhVien(sv);
        }
            for (Phong p : dsPhong) {
                ql.themPhong(p);
        }
    }

    // TẠO DỮ LIỆU MẪU NẾU CẦN
    if (dsSV.isEmpty()) {
        SinhVien sv1 = new SinhVien("SV01", "Trần Văn B", "b@gmail.com", "111", "0909888777");
        SinhVien sv2 = new SinhVien("SV02", "Lê Thị C", "c@gmail.com", "222", "0911222333");
    for (NguoiQuanLy ql : danhSachQuanLy) {
        ql.themSinhVien(sv1);
        ql.themSinhVien(sv2);
    }
    }

    if (dsPhong.isEmpty()) {
        Phong p1 = new Phong("P01", "Phòng A1", 4);
        Phong p2 = new Phong("P02", "Phòng A2", 3);
    for (NguoiQuanLy ql : danhSachQuanLy) {
        ql.themPhong(p1);
        ql.themPhong(p2);
        // tạo 1 hóa đơn mẫu
        ql.taoHoaDon(p1, 100, 5, "2025-10");
    }
}


        int chon;
        do {
            System.out.println(Color.BOLD + Color.BLUE + "\n===== MENU CHÍNH =====" + Color.RESET);
            System.out.println(Color.CYAN + "1. Đăng nhập Quản lý" + Color.RESET);
            System.out.println(Color.GREEN + "2. Đăng nhập Sinh viên" + Color.RESET);
            System.out.println(Color.RED + "0. Thoát" + Color.RESET);
            System.out.print(Color.YELLOW + "Chọn: " + Color.RESET);
            chon = readInt(sc);

            switch (chon) {
                // ====== ĐĂNG NHẬP QUẢN LÝ ======
                // ====== ĐĂNG NHẬP QUẢN LÝ ======
                case 1:
                    System.out.print("Nhập email: ");
                    String emailQL = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String mkQL = sc.nextLine();

                    NguoiQuanLy quanLyDangNhap = null;
                    for (NguoiQuanLy ql : danhSachQuanLy) {
                        if (ql.dangNhap(emailQL, mkQL)) {
                            quanLyDangNhap = ql;
                            break;
                    }
                    }

                    if (quanLyDangNhap !=null){
                        System.out.println(Color.GREEN + "✅ Đăng nhập thành công!" + Color.RESET);
                        quanLyDangNhap.hienThiThongTin();

                        int chonQL;
                        do {
                            System.out.println(Color.PURPLE + "\n----- MENU QUẢN LÝ -----" + Color.RESET);
                            System.out.println("1. Xem danh sách sinh viên");
                            System.out.println("2. Thêm sinh viên");
                            System.out.println("3. Xóa sinh viên");
                            System.out.println("4. Quản lý phòng");
                            System.out.println("5. Cập nhật chi phí hàng tháng");
                            System.out.println("6. Gửi thông báo tiền phòng / tạo hóa đơn");
                            System.out.println("7. Xem danh sách phòng và trạng thái thanh toán");
                            System.out.println("8. Quản lý yêu cầu bảo trì");
                            System.out.println("9. Báo cáo & thống kê");
                            System.out.println("10. Gửi nhắc thanh toán");
                            System.out.println("0. Đăng xuất");
                            System.out.print("Chọn: ");
                            chonQL = readInt(sc);

                            switch (chonQL) {
                                case 1: quanLyDangNhap.xemDanhSachSinhVien(); break;
                                    
                                case 2:
                                    System.out.print("Mã SV: ");
                                    String maSV = sc.nextLine();
                                    System.out.print("Họ tên: ");
                                    String tenSV = sc.nextLine();
                                    System.out.print("Email: ");
                                    String emailSV = sc.nextLine();
                                    System.out.print("Mật khẩu: ");
                                    String mkSV = sc.nextLine();
                                    System.out.print("SĐT: ");
                                    String sdtSV = sc.nextLine();
                                    quanLyDangNhap.themSinhVien(new SinhVien(maSV, tenSV, emailSV, mkSV, sdtSV));
                                    //Lưu file ngay sau khi thêm
                                    FileHandler.ghiDanhSachSinhVien(quanLyDangNhap.getDanhSachSinhVien(), "sinhvien.txt");
                                    break;

                                case 3:
                                    System.out.print("Nhập mã SV cần xóa: ");
                                    String idXoa = sc.nextLine();
                                    quanLyDangNhap.xoaSinhVien(idXoa);
                                    FileHandler.ghiDanhSachSinhVien(quanLyDangNhap.getDanhSachSinhVien(), "sinhvien.txt");
                                    break;
                                case 4:
                                    int chonPhong;
                                    do {
                                        System.out.println("\n--- QUẢN LÝ PHÒNG ---");
                                        System.out.println("1. Xem danh sách phòng");
                                        System.out.println("2. Thêm phòng");
                                        System.out.println("3. Xóa phòng");
                                        System.out.println("4. Cập nhật trạng thái phòng");
                                        System.out.println("0. Quay lại");
                                        System.out.print("Chọn: ");
                                        chonPhong = readInt(sc);

                                        switch (chonPhong) {
                                            case 1:
                                                quanLyDangNhap.xemDanhSachPhong(); break;
                                            case 2:
                                                System.out.print("Mã phòng: ");
                                                String maPhong = sc.nextLine();
                                                System.out.print("Tên phòng: ");
                                                String tenPhong = sc.nextLine();
                                                System.out.print("Sức chứa: ");
                                                int sucChua = readInt(sc);
                                                quanLyDangNhap.themPhong(new Phong(maPhong, tenPhong, sucChua));
                                                FileHandler.ghiDanhSachPhong(quanLyDangNhap.getDanhSachPhong(), "phong.txt");
                                                break;

                                            case 3:
                                                System.out.print("Nhập mã phòng cần xóa: ");
                                                String mpXoa = sc.nextLine();
                                                quanLyDangNhap.xoaPhong(mpXoa);
                                                FileHandler.ghiDanhSachPhong(quanLyDangNhap.getDanhSachPhong(), "phong.txt");
                                                break;

                                            case 4:
                                                System.out.print("Nhập mã phòng cần cập nhật trạng thái: ");
                                                String mp = sc.nextLine();
                                                Phong p = quanLyDangNhap.timPhong(mp);
                                                if (p != null) {
                                                    System.out.print("Nhập trạng thái mới (Còn trống / Đầy / Đang sửa chữa): ");
                                                    String tt = sc.nextLine();
                                                    p.setTrangThai(tt);
                                                    FileHandler.ghiDanhSachPhong(quanLyDangNhap.getDanhSachPhong(), "phong.txt");
                                                    System.out.println(Color.GREEN + "✅ Đã cập nhật trạng thái phòng." + Color.RESET);
                                                } else {
                                                    System.out.println("❌ Không tìm thấy phòng!");
                                                }
                                                break;
                                        }
                                    } while (chonPhong != 0);
                                    break;

                                case 5:
                                    System.out.print("Nhập tiền nhà cố định mỗi tháng: ");
                                    double tienNha = readDouble(sc);
                                    System.out.print("Nhập giá điện mỗi kWh: ");
                                    double tienDien = readDouble(sc);
                                    System.out.print("Nhập giá nước mỗi m3: ");
                                    double tienNuoc = readDouble(sc);
                                    quanLyDangNhap.capNhatChiPhi(tienNha, tienDien, tienNuoc);
                                    break;

                                case 6:
                                    System.out.print("Nhập mã phòng để tạo hóa đơn: ");
                                    String maPhongThongBao = sc.nextLine();
                                    Phong phongTB = quanLyDangNhap.timPhong(maPhongThongBao);
                                    if (phongTB != null) {
                                        System.out.print("Nhập số điện (kWh): ");
                                        int soDien = readInt(sc);
                                        System.out.print("Nhập số nước (m3): ");
                                        int soNuoc = readInt(sc);
                                        System.out.print("Nhập tháng (ví dụ 2025-10): ");
                                        String thang = sc.nextLine();
                                        quanLyDangNhap.taoHoaDon(phongTB, soDien, soNuoc, thang);
                                        FileHandler.ghiDanhSachHoaDon(quanLyDangNhap.getTatCaHoaDon(), "hoadon.txt");
                                    } else {
                                        System.out.println("❌ Không tìm thấy phòng!");
                                    }
                                    break;

                                case 7:
                                    quanLyDangNhap.xemDanhSachPhong();
                                    break;

                                case 8:
                                    //QUẢN LÝ YÊU CẦU BẢO TRÌ
                                    System.out.println("\n--- QUẢN LÝ YÊU CẦU BẢO TRÌ ---");
                                    System.out.println("1. Xem tất cả yêu cầu");
                                    System.out.println("2. Cập nhật trạng thái yêu cầu");
                                    System.out.println("0. Quay lại");
                                    System.out.print("Chọn: ");
                                    int c = readInt(sc);
                                    sc.nextLine();
                                    if (c == 1) {
                                        quanLyDangNhap.xemTatCaYeuCau();
                                    } else if (c == 2) {
                                        System.out.print("Nhập mã yêu cầu: ");
                                        String idyc = sc.nextLine();
                                        System.out.print("Nhập trạng thái mới (Chưa xử lý / Đang xử lý / Hoàn thành): ");
                                        String tt = sc.nextLine();
                                        quanLyDangNhap.capNhatTrangThaiYeuCau(idyc, tt);
                                        FileHandler.ghiDanhSachYeuCau(quanLyDangNhap.getTatCaYeuCau(), "baotri.txt");
                                    }
                                    break;

                                case 9:
                                    BaoCao.thongKePhong(quanLyDangNhap);
                                    BaoCao.thongKeThanhToan(quanLyDangNhap);
                                    BaoCao.thongKeBaoTri(quanLyDangNhap);
                                    break;

                                case 10:
                                    quanLyDangNhap.guiNhacNo();
                                    break;
                            }
                        } while (chonQL != 0);
                    } else {
                        System.out.println(Color.RED + "❌ Sai tài khoản hoặc mật khẩu!" + Color.RESET);
                    }
                    break;

                // ====== ĐĂNG NHẬP SINH VIÊN ======
                case 2:
                    System.out.print("Nhập email: ");
                    String emailSV = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String mkSV = sc.nextLine();

                    SinhVien svDangNhap = null;
                    for (NguoiQuanLy ql : danhSachQuanLy) {
                        svDangNhap = ql.timSinhVien(emailSV, mkSV);
                    if (svDangNhap != null) break;
                    }

                    if (svDangNhap != null) {
                        System.out.println(Color.GREEN + "✅ Đăng nhập thành công!" + Color.RESET);
                        svDangNhap.hienThiThongTin();

                        int chonSV;
                        do {
                            System.out.println(Color.BLUE + "\n----- MENU SINH VIÊN -----" + Color.RESET);
                            System.out.println("1. Xem thông tin cá nhân");
                            System.out.println("2. Đổi mật khẩu");
                            System.out.println("3. Xem lịch sử hoạt động");
                            System.out.println("4. Đăng ký phòng ký túc xá");
                            System.out.println("5. Hủy đăng ký phòng");
                            System.out.println("6. Xem thông báo phòng");
                            System.out.println("7. Thanh toán tiền phòng");
                            System.out.println("8. Xem hóa đơn của tôi");
                            System.out.println("9. Gửi yêu cầu bảo trì");
                            System.out.println("0. Đăng xuất");
                            System.out.print("Chọn: ");
                            chonSV = readInt(sc);

                            switch (chonSV) {
                                case 1: svDangNhap.hienThiThongTin(); break;
                                case 2:
                                    System.out.print("Nhập mật khẩu mới: ");
                                    String mkMoi = sc.nextLine();
                                    svDangNhap.setMatKhau(mkMoi);
                                    svDangNhap.themHoatDong("Đổi mật khẩu");
                                    FileHandler.ghiDanhSachSinhVien(danhSachQuanLy.get(0).getDanhSachSinhVien(), "sinhvien.txt"); // lưu
                                    System.out.println(Color.GREEN + "✅ Đổi mật khẩu thành công!" + Color.RESET);
                                    break;
                                case 3: svDangNhap.xemLichSuHoatDong(); break;
                                case 4:
                                    for (NguoiQuanLy ql : danhSachQuanLy) ql.xemDanhSachPhong();
                                    System.out.print("Nhập mã phòng muốn đăng ký: ");
                                    String maPhongChon = sc.nextLine();
                                    Phong phongChon = null;
                                    for (NguoiQuanLy ql : danhSachQuanLy) {
                                        phongChon = ql.timPhong(maPhongChon);
                                        if (phongChon != null) {
                                            svDangNhap.dangKyPhong(phongChon);
                                            FileHandler.ghiDanhSachSinhVien(ql.getDanhSachSinhVien(), "sinhvien.txt");
                                            break;
                                        }
                                        }
                    if (phongChon == null) System.out.println(Color.RED + "❌ Không tìm thấy phòng!" + Color.RESET);
                    break;
                                case 5:
                                    svDangNhap.huyDangKyPhong();
                                    for (NguoiQuanLy ql : danhSachQuanLy) {
                                        FileHandler.ghiDanhSachSinhVien(ql.getDanhSachSinhVien(), "sinhvien.txt");
                                    }
                                    break;
                                case 6: svDangNhap.xemThongBaoPhong(); break;
                                case 7:
                                    svDangNhap.thanhToan();
                                    for (NguoiQuanLy ql : danhSachQuanLy) {
                                    FileHandler.ghiDanhSachHoaDon(ql.getTatCaHoaDon(), "hoadon.txt");
                                    }
                                    break;
                                case 8: svDangNhap.xemHoaDon(); break; 
                                case 9:
                                    System.out.print("Nhập nội dung yêu cầu bảo trì: ");
                                    String noiDung = sc.nextLine();
                                    for (NguoiQuanLy ql : danhSachQuanLy) {
                                    svDangNhap.guiYeuCauBaoTri(ql, noiDung);
                                    FileHandler.ghiDanhSachYeuCau(ql.getTatCaYeuCau(), "baotri.txt");
                                    }
                                    break;
                            }
                        } while (chonSV != 0);
                    } else {
                        System.out.println(Color.RED + "❌ Sai thông tin đăng nhập!" + Color.RESET);
                    }
                    break;


            }

        } while (chon != 0);

        sc.close();
    }

    // helper đọc số an toàn
    private static int readInt(Scanner sc) {
        while (true) {
            try {
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.print("Vui lòng nhập số: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            try {
                String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (Exception e) {
                System.out.print("Vui lòng nhập số (decimal): ");
            }
        }
    }
}