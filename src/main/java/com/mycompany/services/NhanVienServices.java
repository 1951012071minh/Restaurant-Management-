/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.NhanVien;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ANHMINH
 */
public class NhanVienServices {
    
    
    public int getMaxMaNhanVien()throws SQLException {
      int maxID = 0;
      try(Connection conn = JdbcUtils.getConn()){
          String sql = "SELECT ifnull(MAX(MaNV),0) FROM nhanvien";
          PreparedStatement stm = conn.prepareStatement(sql);
          ResultSet rs = stm.executeQuery();
          while(rs.next())
              maxID = rs.getInt(1);
      }
      return maxID + 1;
   }
     
    public void addNhanVien(NhanVien n) throws SQLException{
        try(Connection conn=JdbcUtils.getConn())
        {
            PreparedStatement stm= conn.prepareStatement("INSERT INTO nhanvien(MaNV, TenNV, SDT, CMND, MaBP, ChucVu, Luong, MaAccount)"
                    + "VALUES(?,?,?,?,?,?,?,?)");
            PreparedStatement stm1 = conn.prepareStatement("call update_SoLuongNV()");
            stm.setInt(1, n.getMaNV());           
            stm.setString(2, n.getTenNV());
            stm.setString(3, n.getSDT());
            stm.setString(4, n.getCMND());
            stm.setInt(5, n.getMaBP());
            stm.setString(6, n.getChucVu());
            stm.setBigDecimal(7, n.getLuong());
            stm.setInt(8, n.getMaAccount());
            stm.executeUpdate();
            stm1.executeUpdate();
        }
    }
    
    public void updateNhanVien(NhanVien n) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            
            PreparedStatement stm = conn.prepareStatement("UPDATE NhanVien\n"
                 + "set TenNV = ?, SDT = ?, CMND = ?, MaBP = ?, ChucVu = ?, Luong = ?, MaAccount = ? "
                 + "where MaNV = ?");
            PreparedStatement stm1 = conn.prepareStatement("call update_SoLuongNV()");
            stm.setString(1, n.getTenNV());
            stm.setString(2, n.getSDT());
            stm.setString(3, n.getCMND());
            stm.setInt(4, n.getMaBP());
            stm.setString(5, n.getChucVu());
            stm.setBigDecimal(6, n.getLuong());
            stm.setInt(7, n.getMaAccount());
            stm.setInt(8, n.getMaNV());
            stm.executeUpdate();
            stm1.executeUpdate();
        }
    }
    
    public List<NhanVien> getListNhanVien(String kw) throws SQLException{
        List<NhanVien> nhanViens = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT n.* FROM nhanvien n INNER JOIN bophan b ON n.MaBP = b.MaBP ";
            if(kw != null && !kw.isEmpty()){
                sql += " WHERE MaNV like concat('%', ? ,'%') OR  TenNV like concat('%', ? ,'%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty()){
                stm.setString(1, kw);
                stm.setString(2, kw);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               NhanVien n = new NhanVien(
                       rs.getInt("MaNV"), 
                       rs.getString("TenNV"), 
                       rs.getString("SDT"), 
                       rs.getString("CMND"), 
                       rs.getInt("MaBP"), 
                       rs.getString("ChucVu"), 
                       rs.getBigDecimal("Luong"),
                       rs.getInt("MaAccount"));
               nhanViens.add(n);
           }
       }
       return nhanViens;
   }
    
    public NhanVien getNhanVien(int maNV) throws SQLException{
        NhanVien s;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM nhanvien Where MaNV = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, Integer.toString(maNV));  
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                s = new NhanVien(
                       rs.getInt("MaNV"), 
                       rs.getString("TenNV"), 
                       rs.getString("SDT"), 
                       rs.getString("CMND"), 
                       rs.getInt("MaBP"), 
                       rs.getString("ChucVu"), 
                       rs.getBigDecimal("Luong"),
                       rs.getInt("MaAccount"));
            }
        }
        return s;
    }
        
    public List<NhanVien> getNhanVienBP(int maBP) throws SQLException 
    {
        List<NhanVien> nhanViens = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM nhanvien Where MaBP = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, Integer.toString(maBP));  
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                NhanVien s = new NhanVien(rs.getInt("MaNV"),rs.getString("TenNV"),rs.getString("SDT"),rs.getString("ChucVu"));
                nhanViens.add(s);
            }
            return nhanViens;
        }
    }
}
