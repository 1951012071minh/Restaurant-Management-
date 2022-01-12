/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Account;
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
    
    public KhachHang getKhachHangByAccount(String userName, String pass, String Loai) throws SQLException{
        KhachHang s = null;
        Account a = null;
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT MaAccount FROM account Where Username = ? AND PassWord = ? AND TypeUser = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, pass);  
            stm.setString(3, Loai);  
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                a = new Account();
                a.setMaAccount(rs.getInt(1));
            }
            String sql1 = "SELECT * FROM khachhang Where MaAcc = ?";
            PreparedStatement stm1 = conn.prepareStatement(sql1);
            stm1.setInt(1, a.getMaAccount());  
            ResultSet rs1 = stm1.executeQuery();
            while(rs1.next()){
                s = new KhachHang(rs1.getInt("MaKH"), rs1.getString("TenKH"),rs1.getString("CMND")
                        , rs1.getString("DiaChi"), rs1.getString("GioiTinh"), rs1.getInt("MaAcc"), rs1.getString("SDT"));
            }   
        }
        return s;
    }
    
}
