/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.SanhServices;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author LenVo
 */
public class SanhUnitTest {
    SanhServices ssv;
    
    @Test
    public void testUniqueTenSanh() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM Sanh");
//                + "WHERE isnull(isDeleted)");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next())
        {
         String name = rs.getString("TenSanh");
         s.add(name);
        }
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(),kq.size());
        if(conn != null)
            conn.close(); 
    }
    @Test
    //thêm
    public void test2() throws SQLException{
        ssv = new SanhServices();
        Sanh s = new Sanh();
        s.setMaSanh(ssv.getMaxSanh());
        s.setTenSanh("Sảnh J");
        s.setTang(9);
        s.setSucChua(70);
        s.setDonGia(BigDecimal.valueOf(5000000));
        int a = ssv.getListSanh(null).size();
        ssv.addSanhVaoDB(s);
        Assertions.assertEquals(a + 1,ssv.getListSanh(null).size());
    }
    
    @Test
    //cập nhật
        public void test3() throws SQLException{
            ssv = new SanhServices();
            Sanh s = ssv.findSanh("Sảnh J");
            s.setTang(12);
            ssv.updateSanhVaoDB(s);
            Assertions.assertEquals(12, ssv.findSanh("Sảnh J").getTang()); 
    }
    @Test
    //xóa
        public void test4() throws SQLException{
            ssv = new SanhServices();
            Sanh s = ssv.findSanh("Sảnh J");
            int a = ssv.getListSanh(null).size();
            ssv.xoaSanh(s);
            Assertions.assertEquals(a - 1, ssv.getListSanh(null).size()); 
    }
      @Test
      //test tên sảnh
        public void Test5() throws SQLException{
            ssv = new SanhServices();
            Assertions.assertNotNull(ssv.findSanh("Sảnh A1"));
            Assertions.assertNull(ssv.findSanh("Sảnh A1111"));
        }
}
