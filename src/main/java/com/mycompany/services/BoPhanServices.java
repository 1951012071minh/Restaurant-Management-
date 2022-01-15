/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.BoPhan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANHMINH
 */
public class BoPhanServices {
    
   public List<BoPhan> getListBoPhan(String bp) throws SQLException {
       List<BoPhan> BoPhans = new ArrayList<>();
       try(Connection conn = JdbcUtils.getConn()){
           String sql = "SELECT * FROM bophan";
           if(bp != null && !bp.isEmpty())
               sql += " WHERE MaBP like concat('%', ? , '%') OR TenBP like concat('%', ? , '%')";
           PreparedStatement stm = conn.prepareStatement(sql);
           if(bp != null && !bp.isEmpty())
           {
               stm.setString(1, bp);
               stm.setString(2, bp);
           }
           ResultSet rs = stm.executeQuery();
           while(rs.next()){
               BoPhan b = new BoPhan(
                       rs.getInt("MaBP"), 
                       rs.getString("TenBP"),
                       rs.getInt("SoLuongNV"));
               BoPhans.add(b);
           }
       }
       return BoPhans;
   }
   
   public List<String> getListTen() throws SQLException {
       List<String> BoPhans = new ArrayList<>();
       try(Connection conn = JdbcUtils.getConn()){
           String sql = "SELECT * FROM bophan";
           PreparedStatement stm = conn.prepareStatement(sql);
           ResultSet rs = stm.executeQuery();
           while(rs.next()){
               BoPhans.add(rs.getString("TenBP"));
           }
       }
       return BoPhans;
   }
   
   public BoPhan getBP(int MaBP) throws SQLException{
        BoPhan s = new BoPhan();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM bophan Where MaBP = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, MaBP);  
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                s.setMaBP(rs.getInt("MaBP"));
                s.setSoLuongNV(rs.getInt("SoLuongNV"));
                s.setTenBP(rs.getString("TenBP"));
            }
        }
        return s;
    }
   
   public BoPhan getBP(String TenBP) throws SQLException{
        BoPhan s = new BoPhan();
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "SELECT * FROM bophan Where TenBP = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, TenBP);  
            ResultSet rs = stm.executeQuery();
            if(!rs.next()){
                return null;
            }else{
                s.setMaBP(rs.getInt("MaBP"));
                s.setSoLuongNV(rs.getInt("SoLuongNV"));
                s.setTenBP(rs.getString("TenBP"));
            }
        }
        return s;
    }

    public int getMaxMaBoPhan()throws SQLException {
      int maxID = 0;
      try(Connection conn = JdbcUtils.getConn()){
          String sql = "SELECT MAX(MaBP) FROM bophan";
          PreparedStatement stm = conn.prepareStatement(sql);
          ResultSet rs = stm.executeQuery();
          while(rs.next())
              maxID = rs.getInt(1);
      }
      return (maxID + 1);
   }

    public void addBoPhan(BoPhan n) throws SQLException{
        try(Connection conn=JdbcUtils.getConn())
        {
            PreparedStatement stm= conn.prepareStatement("INSERT INTO bophan(MaBP, TenBP)"
                    + "VALUES(?,?)");
            stm.setInt(1, n.getMaBP());           
            stm.setString(2, n.getTenBP());
            stm.executeUpdate();
        }
    }
    
    public void updateBoPhan(BoPhan n) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("UPDATE BoPhan\n"
                 + "set TenBP = ?, SoLuongNV = ? "
                 + "where MaBP = ?");
            stm.setString(1, n.getTenBP());
            stm.setInt(2, n.getSoLuongNV());
            stm.setInt(3, n.getMaBP());
            stm.executeUpdate();
        }
    }
    
    public void deleteBoPhan(int MaBP) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM bophan WHERE (MaBP = ?);";
            
            PreparedStatement stm2 = conn.prepareStatement(sql);
            PreparedStatement stm3 = conn.prepareStatement("call update_SoLuongNV()");
            stm2.setInt(1, MaBP);
            stm2.execute();
            stm3.executeUpdate();
        }
    }
}
