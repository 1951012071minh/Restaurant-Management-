/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Sanh;
import java.math.BigDecimal;
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
public class SanhServices {
    public List<Sanh> getListSanh(String kw) throws SQLException
    {
        List<Sanh> Sanhs = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM sanh WHERE isnull(isDeleted)";
            if(kw != null && !kw.isEmpty())
                sql +=  " AND (MaSanh like concat('%', ?, '%') OR TenSanh like concat('%', ?, '%')) ";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(1, kw);
                stm.setString(2, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Sanh s = new Sanh();
                s.setMaSanh(rs.getInt("MaSanh"));
                s.setTenSanh(rs.getString("TenSanh"));
                s.setTang(rs.getInt("Tang"));
                s.setSucChua(rs.getInt("SucChua"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
                Sanhs.add(s);
            }
        }
        return Sanhs;
    }
     public List<Sanh> getListSanhByDate(String kw, Date d, String Buoi) throws SQLException
    {
        List<Sanh> Sanhs = new ArrayList<>();
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM sanh WHERE isnull(isDeleted) AND MaSanh not in " +
                        "(SELECT MaSanh FROM dattiec WHERE NgayToChuc = ? AND Buoi = ?)";
            if(kw != null && !kw.isEmpty())
                sql +=  " AND (MaSanh like concat('%', ?, '%') OR TenSanh like concat('%', ?, '%'))";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, (java.sql.Date) d);
            stm.setString(2, Buoi);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(3, kw);
                stm.setString(4, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Sanh s = new Sanh();
                s.setMaSanh(rs.getInt("MaSanh"));
                s.setTenSanh(rs.getString("TenSanh"));
                s.setTang(rs.getInt("Tang"));
                s.setSucChua(rs.getInt("SucChua"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
                Sanhs.add(s);
            }
        }
        return Sanhs;
    }
    public int getMaxSanh() throws SQLException {
        int maxSanh = 0;
        try(Connection conn = JdbcUtils.getConn()){
                String sql = "SELECT MAX(MaSanh) FROM Sanh";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                maxSanh = rs.getInt(1);
            }
        return (maxSanh + 1);
        }
    }
    
    public void addSanhVaoDB(Sanh s) throws  SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm= conn.prepareStatement("INSERT INTO  Sanh(MaSanh, TenSanh,Tang,SucChua,DonGia)" + "VALUES(?,?,?,?,?)");
            stm.setInt(1, s.getMaSanh());
            stm.setString(2, s.getTenSanh());
            stm.setInt(3, s.getTang());
            stm.setInt(4, s.getSucChua());
            stm.setBigDecimal(5, s.getDonGia());
            stm.executeUpdate();
        }catch(SQLException ex){

        }
    }
    public void updateSanhVaoDB(Sanh s) throws  SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm= conn.prepareStatement("UPDATE Sanh\n"
                    + "set TenSanh = ?, Tang = ?, SucChua = ?, DonGia = ? "
                    + "where MaSanh = ?");
            stm.setString(1, s.getTenSanh());
            stm.setInt(2, s.getTang());
            stm.setInt(3, s.getSucChua());
            stm.setBigDecimal(4, s.getDonGia());
            stm.setInt(5, s.getMaSanh());
            stm.executeUpdate();
        }
    }
    public void addSanhVaoDBIsDeleted(Sanh s) throws  SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm = conn.prepareStatement("UPDATE Sanh\n"
                        + "set TenSanh = ?, Tang = ?, SucChua = ?, DonGia = ?, isDeleted = null "
                        + "where MaSanh = ?");
                stm.setString(1, s.getTenSanh());
                stm.setInt(2, s.getTang());
                stm.setInt(3, s.getSucChua());
                stm.setBigDecimal(4, s.getDonGia());
                stm.setInt(5, s.getMaSanh());
                stm.executeUpdate();
            }
        }
    public void xoaSanh(Sanh s) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE Sanh \n SET isDeleted = curDate()\n"
                    + "WHERE MaSanh = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, s.getMaSanh());
            stm.executeUpdate();
        }
    }
      
    public Sanh findSanh(String kw) throws SQLException{
        Sanh s = new Sanh();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM Sanh WHERE TenSanh = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }
            else{
                s.setMaSanh(rs.getInt("MaSanh"));
                s.setTenSanh(rs.getString("TenSanh"));
                s.setTang(rs.getInt("Tang"));
                s.setSucChua(rs.getInt("SucChua"));
                s.setIsDeleted(rs.getDate("isDeleted"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
            }
        }
        return s;
    }
}
