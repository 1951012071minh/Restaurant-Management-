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
}
