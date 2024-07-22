package com.edusys.dao;



import com.edusys.entity.NhanVien;
import com.edusys.utils.JdpcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends Dao<NhanVien, String> {
    
    String INSERT_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";

    @Override
    public void insert(NhanVien entiTy) {
        JdpcHelper.update(INSERT_SQL, 
                entiTy.getMaNV(),
                entiTy.getMatKhau(),
                entiTy.getHoTen(),
                entiTy.isVaiTro());
    }

    @Override
    public void update(NhanVien entiTy) {
        JdpcHelper.update(UPDATE_SQL,
                entiTy.getMatKhau(),
                entiTy.getHoTen(),
                entiTy.isVaiTro(),
                entiTy.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdpcHelper.update(DELETE_SQL,id);

    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdpcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
