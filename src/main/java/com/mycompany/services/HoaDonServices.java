/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.HoaDon;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ANHMINH
 */
public class HoaDonServices {
    public int getMaxHoaDon() throws SQLException{
        int maxID = 0 ;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT MAX(MaHD) FROM hoadon";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
                maxID = rs.getInt(1);  
        }
        return maxID + 1;
    }
    
    public void addHoaDon(HoaDon d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hoadon(MaHD, MaTiec, ThanhTien, NgayLap, TinhTrang)"
                    + " VALUES(?, ? , 0, CURDATE(), ?)");
            stm.setInt(1, getMaxHoaDon());
            stm.setInt(2, d.getMaTiec());
            stm.setString(3, "Chưa thanh toán!");
            stm.executeUpdate();
            PreparedStatement stm1 = conn.prepareStatement("call thanhTienHoaDon(?)");
            stm1.setInt(1, d.getMaTiec());
            stm1.executeUpdate();
        }
    }
    public HoaDon getHoaDon(int maTiec) throws SQLException{
        HoaDon d = new HoaDon() ;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM hoadon WHERE MaTiec = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, maTiec);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                d.setMaTiec(rs.getInt("MaTiec"));
                d.setMaHD(rs.getInt("MaHD"));
                d.setThanhTien(rs.getBigDecimal("ThanhTien"));
                d.setNgayLap(rs.getDate("NgayLap"));
                d.setTinhTrang(rs.getString("TinhTrang"));
            }
        }
        return d;
    }
    public List<HoaDon> getListHoaDon(Date d1, Date d2) throws SQLException
    {
        List<HoaDon> datTiecs = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM hoadon ";
            if(d1 != null && d2 != null)
                sql +=  " WHERE NgayLap >= ? AND NgayLap <= ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(d1 != null && d2 != null)
            {
                stm.setDate(1, (java.sql.Date) d1);
                stm.setDate(2, (java.sql.Date) d2);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                HoaDon d = new HoaDon();
                d.setMaTiec(rs.getInt("MaTiec"));
                d.setMaHD(rs.getInt("MaHD"));
                d.setTinhTrang(rs.getString("TinhTrang"));
                d.setNgayLap(rs.getDate("NgayLap"));
                d.setThanhTien(rs.getBigDecimal("ThanhTien"));
                datTiecs.add(d);
            }
        }
        return datTiecs;
    }
    public BigDecimal getDoanhThu(Date d1, Date d2) throws SQLException{
        BigDecimal d = new BigDecimal(BigInteger.ZERO);
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT SUM(ThanhTien) FROM hoadon ";
            if(d1 != null && d2 != null)
                sql +=  " WHERE NgayLap >= ? AND NgayLap <= ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(d1 != null && d2 != null)
            {
                stm.setDate(1, (java.sql.Date) d1);
                stm.setDate(2, (java.sql.Date) d2);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                d = rs.getBigDecimal(1);
            }
        }
        return d;
    }
    public void thanhToanHoaDon(int maTiec) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "Update hoadon SET TinhTrang = ? WHERE maTiec = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "Đã thanh toán");
            stm.setInt(2, maTiec);
            stm.executeUpdate(); 
        }
    }
}
