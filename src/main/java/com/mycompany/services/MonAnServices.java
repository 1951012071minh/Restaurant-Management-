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
            String sql = "SELECT * FROM monan";
            if(kw != null && !kw.isEmpty())
                sql +=  " WHERE MaMA like concat('%', ?, '%') OR TenMA like concat('%', ?, '%') ";
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
}
