/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.Sanh;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author ANHMINH
 */
public class DatTiecServices {
    public int getMaxDatTiec() throws SQLException{
        int maxID = 0 ;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT MAX(MaTiec) FROM dattiec";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
                maxID = rs.getInt(1);  
        }
        return maxID + 1;
    }


    public int addDatTiec(DatTiec d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO dattiec(MaTiec, TenTiec, MaKH, NgayDat, SoLuongBan, SoLuongKhach, MaSanh, NgayToChuc, Buoi)"
                    + " VALUES(?, ? , ?, CURDATE(), ?, ?, ?, ?, ?)");
            stm.setInt(1, d.getMaTiec());
            stm.setString(2, d.getTenTiec());
            stm.setInt(3, d.getMaKH());
            stm.setInt(4, d.getSoLuongBan());
            stm.setInt(5, d.getSoLuongKhach());
            stm.setInt(6, d.getMaSanh());
            stm.setDate(7, (Date) d.getNgayToChuc());
            stm.setString(8, d.getBuoi());       
            stm.executeUpdate();
            return 1;
        }
    }
    
    public void updateDatTiec(DatTiec d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("UPDATE dattiec\n" +
"           set TenTiec = ?, MaKH = ?, SoLuongBan = ?, SoLuongKhach = ?, MaSanh = ?, NgayToChuc = ?, Buoi = ?" +
"           where MaTiec = ?");
            stm.setString(1, d.getTenTiec());
            stm.setInt(2, d.getMaKH());
            stm.setInt(3, d.getSoLuongBan());
            stm.setInt(4, d.getSoLuongKhach());
            stm.setInt(5, d.getMaSanh());
            stm.setDate(6, (Date) d.getNgayToChuc());
            stm.setString(7, d.getBuoi());
            stm.setInt(8, d.getMaTiec());
            stm.executeUpdate();
            PreparedStatement stm1 = conn.prepareStatement("call thanhTienHoaDon(?)");
            stm1.setInt(1, d.getMaTiec());
            stm1.executeUpdate();
        }
    }
    public void delDatTiec(int maTiec) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("DELETE FROM dattiec\n" +
"           where MaTiec = ?");
            stm.setInt(1, maTiec);
            stm.executeUpdate();
        }
    }
    
    public DatTiec FindDatTiec(int maTiec) throws SQLException{
        DatTiec d = new DatTiec();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM dattiec WHERE MaTiec = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, maTiec);
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }
            else{
                d.setMaTiec(rs.getInt("MaTiec"));
                d.setMaSanh(rs.getInt("MaSanh"));
                d.setBuoi(rs.getString("Buoi"));
                d.setSoLuongBan(rs.getInt("SoLuongBan"));
                d.setNgayToChuc(rs.getDate("NgayToChuc"));
                d.setNgayDat(rs.getDate("NgayDat"));
                d.setSoLuongKhach(rs.getInt("SoLuongKhach"));
                d.setTenTiec(rs.getString("TenTiec"));
                d.setMaKH(rs.getInt("MaKH"));
            }
        }
        return d;
    }
    public List<DatTiec> getListDatTiecByKhachHang(int maKH) throws SQLException{
        List<DatTiec> l = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM dattiec WHERE MaKH = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, maKH);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                DatTiec d = new DatTiec();
                d.setMaTiec(rs.getInt("MaTiec"));
                d.setMaSanh(rs.getInt("MaSanh"));
                d.setBuoi(rs.getString("Buoi"));
                d.setSoLuongBan(rs.getInt("SoLuongBan"));
                d.setNgayToChuc(rs.getDate("NgayToChuc"));
                d.setNgayDat(rs.getDate("NgayDat"));
                d.setSoLuongKhach(rs.getInt("SoLuongKhach"));
                d.setTenTiec(rs.getString("TenTiec"));
                d.setMaKH(rs.getInt("MaKH"));
                l.add(d);
            }
        }
        return l;
    }
    public List<DatTiec> getListDatTiec(String kw) throws SQLException
    {
        List<DatTiec> datTiecs = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM dattiec ";
            if(kw != null && !kw.isEmpty())
                sql +=  " WHERE MaTiec like concat('%', ?, '%') OR TenTiec like concat('%', ?, '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(1, kw);
                stm.setString(2, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                DatTiec d = new DatTiec();
                d.setMaTiec(rs.getInt("MaTiec"));
                d.setMaSanh(rs.getInt("MaSanh"));
                d.setBuoi(rs.getString("Buoi"));
                d.setSoLuongBan(rs.getInt("SoLuongBan"));
                d.setNgayToChuc(rs.getDate("NgayToChuc"));
                d.setNgayDat(rs.getDate("NgayDat"));
                d.setSoLuongKhach(rs.getInt("SoLuongKhach"));
                d.setTenTiec(rs.getString("TenTiec"));
                d.setMaKH(rs.getInt("MaKH"));
                datTiecs.add(d);
            }
        }
        return datTiecs;
    }
    
    public int checkDatTiec(int maSanh, Date d, String Buoi) throws SQLException{
        int maTiec = 0;
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "(SELECT MaTiec FROM dattiec WHERE NgayToChuc = ? AND Buoi = ? AND MaSanh = ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, (java.sql.Date) d);
            stm.setString(2, Buoi);
            stm.setInt(3, maSanh);;      
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                maTiec = rs.getInt(1);
            }
        }
        return maTiec;
    }
    public int checkDatTiec(int MaKH) throws SQLException{
        int maTiec = 0;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "(SELECT MaTiec FROM dattiec WHERE NgayToChuc > curdate() AND MaKH = ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, MaKH);;      
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                maTiec = rs.getInt(1);
            }
        }
        return maTiec;
    }
}
