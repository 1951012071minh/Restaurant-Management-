/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
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
        ResultSet rs = stm.executeQuery("SELECT * FROM monan");
        
        while(rs.next()){
            String name = rs.getString("TenMA");
            System.out.println(name);
        }
        if(conn != null)
            conn.close();
    }
}
