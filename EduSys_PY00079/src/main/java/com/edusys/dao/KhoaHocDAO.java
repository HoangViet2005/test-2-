package com.edusys.dao;



import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JdpcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KhoaHocDAO extends Dao<KhoaHoc, Integer> {

    String INSERT_SQL = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=?, NgayTao=? WHERE MaKH=?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";
    String SELECT_BY_MA_CD_SQL = "SELECT * FROM KhoaHoc WHERE MaCD=?";

    @Override
    public void insert(KhoaHoc entity) {
        JdpcHelper.update(INSERT_SQL,
            entity.getMaCD(),
            entity.getHocPhi(),
            entity.getThoiLuong(),
            entity.getNgayKG(),
            entity.getGhiChu(),
            entity.getMaNV(),
            entity.getNgayTao()
        );
    }

    @Override
    public void update(KhoaHoc entity) {
        JdpcHelper.update(UPDATE_SQL,
            entity.getMaCD(),
            entity.getHocPhi(),
            entity.getThoiLuong(),
            entity.getNgayKG(),
            entity.getGhiChu(),
            entity.getMaNV(),
            entity.getNgayTao(),
            entity.getMaKH() 
        );
    }

    @Override
    public void delete(Integer id) {
        JdpcHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhoaHoc selectByID(Integer id) {
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdpcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String maCD) {
        return selectBySql(SELECT_BY_MA_CD_SQL, maCD);
    }

    public List<Integer> selectYears() {
        String SQL = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdpcHelper.query(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectKhoaHocByChuyenDe(String maCD) {
        return selectBySql(SELECT_BY_MA_CD_SQL, maCD);
    }
}
