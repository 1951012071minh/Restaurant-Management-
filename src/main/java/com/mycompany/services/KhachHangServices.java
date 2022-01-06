/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.KhachHang;
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
public class KhachHangServices {
    public List<KhachHang> getListKhachHang(String kw) throws SQLException
    {
        List<KhachHang> KhachHangs = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM khachhang";
            if(kw != null && !kw.isEmpty())
                sql +=  " WHERE MaKH like concat('%', ?, '%') OR TenKH like concat('%', ?, '%') ";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(1, kw);
                stm.setString(2, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                KhachHang s = new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"),rs.getString("CMND")
                        , rs.getString("DiaChi"), rs.getString("GioiTinh"), rs.getInt("MaAcc"), rs.getString("SDT"));
                KhachHangs.add(s);
            }
        }
        return KhachHangs;
    }
    public KhachHang getKhachHang(int maKH) throws SQLException{
        KhachHang s = null;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM khachhang Where MaKH = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, Integer.toString(maKH));  
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s = new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"),rs.getString("CMND")
                        , rs.getString("DiaChi"), rs.getString("GioiTinh"), rs.getInt("MaAcc"), rs.getString("SDT"));
            }
        }
        return s;
    }
}
