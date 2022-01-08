/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Sanh;
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
                s.setTang(rs.getString("Tang"));
                s.setSucChua(rs.getInt("SucChua"));
                s.setDonGia(rs.getBigDecimal("DonGia"));
                Sanhs.add(s);
            }
        }
        return Sanhs;
    }

}
