/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
