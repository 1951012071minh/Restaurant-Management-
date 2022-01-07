/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatTiec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void addDatTiec(DatTiec d) throws SQLException{
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
}
