/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DichVu;
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
public class DichVuServices {
    public List<DichVu> getListDichVu(String kw) throws SQLException
    {
        List<DichVu> DichVus = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM dichvu WHERE isnull(isDeleted)";
            if(kw != null && !kw.isEmpty())
                sql +=  " AND (MaDV like concat('%', ?, '%') OR TenDV like concat('%', ?, '%')) ";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(1, kw);
                stm.setString(2, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                DichVu s = new DichVu();
                s.setMaDV(rs.getInt("MaDV"));
                s.setTenDV(rs.getString("TenDV"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
                DichVus.add(s);
            }
        }
        return DichVus;
    }
    
        public int getMaxDV() throws SQLException{
            int maxDV = 0;
            try(Connection conn = JdbcUtils.getConn()){
                String sql = "SELECT MAX(MaDV) FROM DichVu";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                maxDV = rs.getInt(1);
            }
            return (maxDV + 1);
        }
    }
    public DichVu findDichVu(String kw) throws SQLException{
        DichVu d = new DichVu();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM dichvu WHERE TenDV = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
           if(!rs.next()){
                return null;
            }
            else{
                d.setMaDV(rs.getInt("MaDV"));
                d.setTenDV(rs.getString("TenDV"));
                d.setDonGia(rs.getBigDecimal("DonGia"));
                d.setIsDeleted(rs.getDate("isDeleted"));
            }
        }
        return d;
    }
     public void addDichVuVaoDB(DichVu dv) throws  SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm= conn.prepareStatement("INSERT INTO DichVu(MaDV, TenDV,DonGia)" + "VALUES(?,?,?)");
                stm.setInt(1, dv.getMaDV());
                stm.setString(2, dv.getTenDV());
                stm.setBigDecimal(3, dv.getDonGia());
                stm.executeUpdate();
            }
    }
    public void addDichVuVaoDBIsDeleted(DichVu dv) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "UPDATE dichvu \n"
                        + "set TenDV = ?, DonGia = ?, isDeleted = null "
                        + " where MaDV = ?";
            PreparedStatement stm= conn.prepareStatement(sql);
            stm.setString(1, dv.getTenDV());
            stm.setBigDecimal(2, dv.getDonGia());
            stm.setInt(3, dv.getMaDV());
            stm.executeUpdate();
        }
    }
     public void updateDichVuVaoDB(DichVu dv) throws  SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm= conn.prepareStatement("UPDATE dichvu\n"
                        + "set TenDV = ?, DonGia = ? "
                        + "where MaDV = ?");
                stm.setString(1, dv.getTenDV());
                stm.setBigDecimal(2, dv.getDonGia());
                stm.setInt(3, dv.getMaDV());
                stm.executeUpdate();
            }
        }
      public void xoaDichVu(DichVu dv) throws SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                String sql = "UPDATE DichVu \n SET isDeleted = curDate()\n"
                        + "WHERE MaDV = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, dv.getMaDV());
                stm.executeUpdate();
            }
        }
}
