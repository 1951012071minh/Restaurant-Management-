/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.SanhServices;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ANHMINH
 */
public class ThucAnTester {
    @Test
    public void testQuanity() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM sanh");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next()){
         String name = rs.getString("TenSanh");
            s.add(name);
        }
        
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(), 3);
        if(conn != null)
            conn.close();
    }
    @Test
    public void testGetSanhData() throws SQLException{
        SanhServices ss = new SanhServices();
        Sanh s = new Sanh();
            s.setMaSanh(1);
            s.setTenSanh("Minh");
            s.setTang(2);
            s.setSucChua(100);
            s.setDonGia(BigDecimal.valueOf(100));
        ss.updateSanhVaoDB(s);
        Assertions.assertEquals(ss.findSanh("Minh").getMaSanh(), 1);
    }
}
