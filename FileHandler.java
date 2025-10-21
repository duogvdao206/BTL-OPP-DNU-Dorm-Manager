import java.io.*;
import java.util.*;

public class FileHandler {

    // ====== GHI DỮ LIỆU QUẢN LÝ RA FILE ======
    public static void ghiDuLieuQuanLy(NguoiQuanLy ql, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(ql.getId() + "," + ql.getHoTen() + "," + ql.getEmail() + "," +
                     ql.getMatKhau() + "," + ql.getSoDienThoai() + "," + ql.getChucVu());
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi dữ liệu quản lý: " + e.getMessage());
        }
    }

    // ====== ĐỌC DỮ LIỆU QUẢN LÝ TỪ FILE ======
    public static ArrayList<NguoiQuanLy> docDanhSachQuanLy(String filePath) {
    ArrayList<NguoiQuanLy> dsQL = new ArrayList<>();
    File f = new File(filePath);
    if (!f.exists()) return dsQL;

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                dsQL.add(new NguoiQuanLy(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
            }
        }
    } catch (IOException e) {
        System.out.println("❌ Lỗi đọc dữ liệu quản lý: " + e.getMessage());
    }

    return dsQL;
}



    // ====== GHI DANH SÁCH SINH VIÊN RA FILE ======
    public static void ghiDanhSachSinhVien(ArrayList<SinhVien> ds, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (SinhVien sv : ds) {
                bw.write(sv.getId() + "," + sv.getHoTen() + "," + sv.getEmail() + "," +
                         sv.getMatKhau() + "," + sv.getSoDienThoai() + "," + sv.getMaPhong());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi danh sách sinh viên: " + e.getMessage());
        }
    }

    // ====== ĐỌC DANH SÁCH SINH VIÊN TỪ FILE ======
    public static ArrayList<SinhVien> docDanhSachSinhVien(String filePath) {
        ArrayList<SinhVien> ds = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return ds;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    ds.add(new SinhVien(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc danh sách sinh viên: " + e.getMessage());
        }
        return ds;
    }

    // ====== GHI DANH SÁCH PHÒNG ======
    public static void ghiDanhSachPhong(ArrayList<Phong> ds, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Phong p : ds) {
                bw.write(p.getMaPhong() + "," + p.getTenPhong() + "," + p.getSucChua() + "," + p.getTrangThai());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi danh sách phòng: " + e.getMessage());
        }
    }

    // ====== ĐỌC DANH SÁCH PHÒNG ======
    public static ArrayList<Phong> docDanhSachPhong(String filePath) {
        ArrayList<Phong> ds = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return ds;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    ds.add(new Phong(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc danh sách phòng: " + e.getMessage());
        }
        return ds;
    }

    // ====== GHI DANH SÁCH HÓA ĐƠN ======
    public static void ghiDanhSachHoaDon(ArrayList<HoaDon> ds, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (HoaDon hd : ds) {
                bw.write(hd.getMaHoaDon() + "," + (hd.getPhong() != null ? hd.getPhong().getMaPhong() : "N/A") + "," +
                         hd.getThang() + "," + hd.getTongTien() + "," + (hd.isDaThanhToan() ? "1" : "0"));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi danh sách hóa đơn: " + e.getMessage());
        }
    }

    // ====== GHI DANH SÁCH YÊU CẦU BẢO TRÌ ======
    public static void ghiDanhSachYeuCau(ArrayList<YeuCauBaoTri> ds, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (YeuCauBaoTri yc : ds) {
                bw.write(yc.getId() + "," + yc.getNguoiGui().getId() + "," +
                         (yc.getPhong() != null ? yc.getPhong().getMaPhong() : "N/A") + "," +
                         yc.getNoiDung() + "," + yc.getTrangThai());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi danh sách yêu cầu bảo trì: " + e.getMessage());
        }
    }
}
