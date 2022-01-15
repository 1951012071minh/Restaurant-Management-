/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DatMonAn;
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
public class DatMonAnServices {
        public void addDatMonAn(DatMonAn d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO datmonan(MaTiec, MaMA, SoLuong, ThanhTien)\n" +
                " VALUES(?, ?, ?, (SELECT DonGia FROM monan WHERE MaMA = ?) * ?)");
            PreparedStatement stm1 = conn.prepareStatement(
                "call thanhTienHoaDon(?)");
            stm.setInt(1, d.getMaTiec());
            stm.setInt(2, d.getMaMA());
            stm.setInt(3, d.getSoLuong());
            stm.setInt(4, d.getMaMA());
            stm.setInt(5, d.getSoLuong());
            stm1.setInt(1, d.getMaTiec());
            stm.executeUpdate();
            stm1.executeUpdate();
        }
    }
    public void xoaDatMonAn(DatMonAn d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("delete from datmonan \n" +
"		WHERE MaMA = ? AND MaTiec = ?");
            PreparedStatement stm1 = conn.prepareStatement(
                "call thanhTienHoaDon(?)");
            stm.setInt(1, d.getMaMA());
            stm.setInt(2, d.getMaTiec());
            stm1.setInt(1,  d.getMaTiec());
            stm.executeUpdate();
            stm1.executeUpdate();
        }
    }
    public void updateDatMonAn(DatMonAn d) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("UPDATE datmonan\n" +
            " set SoLuong = ?, ThanhTien = ((SELECT DonGia FROM monan WHERE MaMA = ?) * ?)" +
            " where MaTiec = ? AND MaMA = ?");
            stm.setInt(1, d.getSoLuong());
            stm.setInt(2, d.getMaMA());
            stm.setInt(3, d.getSoLuong());
            stm.setInt(4, d.getMaTiec());
            stm.setInt(5, d.getMaMA());
            stm.executeUpdate();
            PreparedStatement stm1 = conn.prepareStatement("call thanhTienHoaDon(?)");
            stm1.setInt(1, d.getMaTiec());
            stm1.executeUpdate();
        }
    }
    
    public int getTongMonAn(int maTiec) throws SQLException{
        int soLuong = 0 ;
        try(Connection conn = JdbcUtils.getConn()){ 
            PreparedStatement stm = conn.prepareStatement("SELECT IFNULL(Count(*), 0) FROM datmonan WHERE MaTiec = ?");
            stm.setInt(1, maTiec);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
                soLuong = rs.getInt(1);  
        }
        return soLuong;
    }
    public BigDecimal getThanhTienMonAn(int maTiec) throws SQLException{
        BigDecimal thanhTien = BigDecimal.ZERO ;
        try(Connection conn = JdbcUtils.getConn()){ 
            PreparedStatement stm = conn.prepareStatement("SELECT IFNULL(SUM(ThanhTien), 0) FROM datmonan WHERE MaTiec = ?");
            stm.setInt(1, maTiec);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
                thanhTien = rs.getBigDecimal(1);  
        }
        return thanhTien;
    }
    public List<DatMonAn> getListDatMonAn(int maTiec) throws SQLException
    {
        List<DatMonAn> DatMonAns = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM datmonan WHERE MaTiec = ?");
            stm.setInt(1, maTiec);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                DatMonAn s = new DatMonAn();
                s.setMaMA(rs.getInt("MaMA"));
                s.setMaTiec(rs.getInt("MaTiec"));
                s.setSoLuong(rs.getInt("SoLuong"));
                s.setThanhTien(rs.getBigDecimal("ThanhTien"));
                DatMonAns.add(s);
            }
        }
        return DatMonAns;
    }
    public DatMonAn getDatMonAn(int maTiec, int maMA) throws SQLException
    {
        DatMonAn s = new DatMonAn();
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM datmonan WHERE MaTiec = ? AND maMA = ?");
            stm.setInt(1, maTiec);
            stm.setInt(2, maMA);
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }
            else{
                s.setMaMA(rs.getInt("MaMA"));
                s.setMaTiec(rs.getInt("MaTiec"));
                s.setSoLuong(rs.getInt("SoLuong"));
                s.setThanhTien(rs.getBigDecimal("ThanhTien"));
            }
        }
        return s;
    }
}
