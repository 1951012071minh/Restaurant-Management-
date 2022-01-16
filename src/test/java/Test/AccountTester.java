/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Account;
import com.mycompany.services.AccountServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void testUsername() throws SQLException{
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
    public void testGetAccountByUserName() throws SQLException{
        AccountServices s = new AccountServices();
        Assertions.assertNotNull(s.FindAccount("Hnguyen"));
        Assertions.assertNull(s.FindAccount("huynhnguyen"));
    }
    @Test// ADD accounnt
    public void testAddAcc()throws SQLException{
        Account ac= new Account();
        AccountServices ACSV= new AccountServices();
        Connection conn= JdbcUtils.getConn();
        Statement stm= conn.createStatement();
        ac.setMaAccount(ACSV.getMaAccount());
        ac.setPassWord("22222");
        ac.setUsername("nnhatsang12");
        ac.setTypeUser("NV");
        
        int a=ACSV.getListAccount().size();
        ACSV.addAccount(ac);
        Assertions.assertEquals(a+1, ACSV.getListAccount().size());   
    }
}
