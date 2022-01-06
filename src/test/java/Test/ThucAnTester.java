/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ANHMINH
 */
public class ThucAnTester {
    private static Connection conn;
    
    @Test
    public void testQuanity() throws SQLException{
        conn = JdbcUtils.getConn();
        Statement  stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM khachhang");
        
        while(rs.next()){
            KhachHang s = new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"),rs.getString("CMND")
                        , rs.getString("DiaChi"), rs.getString("GioiTinh"), rs.getInt("MaAcc"), rs.getString("SDT"));
            System.out.println(s.ToString());
        }
        if(conn != null)
            conn.close();
    }
}
