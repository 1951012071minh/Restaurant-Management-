/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.MonAn;
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
public class MonAnServices {
    public List<MonAn> getListMonAn(String kw) throws SQLException
    {
        List<MonAn> MonAns = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM monan WHERE isnull(isDeleted)";
            if(kw != null && !kw.isEmpty())
                sql +=  " AND (MaMA like concat('%', ?, '%') OR TenMA like concat('%', ?, '%')) ";
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
            {
                stm.setString(1, kw);
                stm.setString(2, kw);
            }        
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                MonAn s = new MonAn();
                s.setMaMA(rs.getInt("MaMA"));
                s.setTenMA(rs.getString("TenMA"));
                s.setLoai(rs.getString("Loai"));
                s.setDonViTinh(rs.getString("DonViTinh"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
                MonAns.add(s);
            }
        }
        return MonAns;
    }

    public int getMaxMA() throws SQLException{
            int maxMA = 0;
            try(Connection conn = JdbcUtils.getConn()){
                String sql = "SELECT MAX(MaMA) FROM MonAn";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                maxMA = rs.getInt(1);
            }
            return (maxMA + 1);
        }
    }
    public MonAn findMonAn(String kw) throws SQLException{
        MonAn ma = new MonAn();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM MonAn WHERE TenMA = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
           if(!rs.next()){
                return null;
            }
            else{
                ma.setMaMA(rs.getInt("MaMA"));
                ma.setTenMA(rs.getString("TenMA"));
                ma.setDonGia(rs.getBigDecimal("DonGia"));
                ma.setLoai(rs.getString("Loai"));
                ma.setDonViTinh(rs.getString("DonViTinh"));
                ma.setIsDeleted(rs.getDate("isDeleted"));
            }
        }
        return ma;
    }
    public void addMonAnVaoDB(MonAn ma) throws  SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm= conn.prepareStatement("INSERT INTO MonAn(MaMA, TenMA,DonGia,Loai,DonViTinh)" + "VALUES(?,?,?,?,?)");
                stm.setInt(1, ma.getMaMA());
                stm.setString(2, ma.getTenMA());
                stm.setBigDecimal(3, ma.getDonGia());
                stm.setString(4, ma.getLoai());
                stm.setString(5, ma.getDonViTinh());
                stm.executeUpdate();
            }
        }
    public void updateMonAnVaoDB(MonAn ma) throws  SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                PreparedStatement stm= conn.prepareStatement("UPDATE MonAn\n"
                        + "set TenMA = ?, DonGia = ?, Loai = ?, DonViTinh = ? "
                        + "where MaMA = ?");
                stm.setString(1, ma.getTenMA());
                stm.setBigDecimal(2, ma.getDonGia());
                stm.setString(3, ma.getLoai());
                stm.setString(4, ma.getDonViTinh());
                stm.setInt(5, ma.getMaMA());
                stm.executeUpdate();
            }
        }
    public void addMonAnVaoDBIsDeleted(MonAn ma) throws  SQLException{
                try(Connection conn = JdbcUtils.getConn()){
                    String sql = "UPDATE monan\n"
                            + "set TenMA = ?, DonGia = ?, Loai = ?, DonViTinh = ?, isDeleted = null "
                            + "where MaMA = ?";
                    PreparedStatement stm= conn.prepareStatement(sql);
                    stm.setString(1, ma.getTenMA());
                    stm.setBigDecimal(2, ma.getDonGia());
                    stm.setString(3, ma.getLoai());
                    stm.setString(4, ma.getDonViTinh());
                    stm.setInt(5, ma.getMaMA());
                    stm.executeUpdate();
                }
            }
    public void xoaMonAn(MonAn ma) throws SQLException{
            try(Connection conn = JdbcUtils.getConn()){
                String sql = "UPDATE MonAn \n SET isDeleted = curDate()\n"
                        + "WHERE MaMA = ?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, ma.getMaMA());
                stm.executeUpdate();
            }
        }
}
