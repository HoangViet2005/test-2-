/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.utils;


import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;
import com.edusys.entity.NhanVien;
import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.HocVienDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.dao.NhanVienDAO;
import com.edusys.dao.ThongKeDAO;
import java.util.List;

public class testJdbc {
    public static void main(String[] args){
        // Thống kê điểm chuyên đề
        ThongKeDAO tkDAO = new ThongKeDAO();
        List<Object[]> list = tkDAO.getDiemChuyenDe();
        for (Object[] obj : list) {
            System.out.println("=>" + obj[0] + "-" + obj[1] + "-" + obj[2]);
        }
        // Kiểm tra sp_LuongNguoiHoc
        List<Object[]> luongNguoiHocList = tkDAO.getLuongNguoiHoc();
        System.out.println("Luong Nguoi Hoc:");
        for (Object[] obj : luongNguoiHocList) {
            System.out.println("Nam: " + obj[0] + ", SoLuong: " + obj[1] + ", DauTien: " + obj[2] + ", CuoiCung: " + obj[3]);
        }
        int year = 2024;
        List<Object[]> doanhThuList = tkDAO.getDoanhThu(year);
        System.out.println("Doanh Thu:");
        for (Object[] obj : doanhThuList) {
            System.out.println("ChuyenDe: " + obj[0] + ", SoKH: " + obj[1] + ", SoHV: " + obj[2] + ", DoanhThu: " + obj[3] + ", ThapNhat: " + obj[4] + ", CaoNhat: " + obj[5] + ", TrungBinh: " + obj[6]);
        }
        // Kiểm tra sp_BangDiem
        int maKH = 1;  // Thay đổi mã khóa học nếu cần
        List<Object[]> bangDiemList = tkDAO.getBangDiem(maKH);
        System.out.println("Bang Diem:");
        for (Object[] obj : bangDiemList) {
            System.out.println("MaNH: " + obj[0] + ", HoTen: " + obj[1] + ", Diem: " + obj[2]);
        }

        // Lấy tất cả nhân viên
        NhanVienDAO nhanVienDAO = new NhanVienDAO();
        List<NhanVien> nhanVienList = nhanVienDAO.selectAll();
        for (NhanVien nv : nhanVienList) {
            System.out.println("=>" + nv.toString());
        }

        // Lấy tất cả chuyên đề
        ChuyenDeDAO chuyenDeDAO = new ChuyenDeDAO();
        List<ChuyenDe> chuyenDeList = chuyenDeDAO.selectAll();
        for (ChuyenDe cd : chuyenDeList) {
            System.out.println("=>" + cd.toString());
        }

        // Lấy tất cả học viên
        HocVienDAO hocVienDAO = new HocVienDAO();
        List<HocVien> hocVienList = hocVienDAO.selectAll();
        for (HocVien hv : hocVienList) {
            System.out.println("=>" + hv.toString());
        }

        // Lấy tất cả khóa học
        KhoaHocDAO khoaHocDAO = new KhoaHocDAO();
        List<KhoaHoc> khoaHocList = khoaHocDAO.selectAll();
        for (KhoaHoc kh : khoaHocList) {
            System.out.println("=>" + kh.toString());
        }

        // Lấy tất cả người học
        NguoiHocDAO nguoiHocDAO = new NguoiHocDAO();
        List<NguoiHoc> nguoiHocList = nguoiHocDAO.selectAll();
        for (NguoiHoc nh : nguoiHocList) {
            System.out.println("=>" + nh.toString());
        }
    }
}

