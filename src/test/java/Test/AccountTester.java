/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.services.AccountServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Admin
 */
public class AccountTester {
    AccountServices accountSV;
    int maAccount = 1;
    
    @Test
    public void testQuantity() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM account");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next()){
        String name = rs.getString("Username");
            s.add(name);
        System.out.println(name);
        }
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(), kq.size());
        if(conn != null)
            conn.close();
    }
    
    @Test
    public void Test2() throws SQLException{
        AccountServices s = new AccountServices();
        Assertions.assertNotNull(s.FindAccount("Hnguyen"));
        Assertions.assertNull(s.FindAccount("huynhnguyen"));
    }
}
