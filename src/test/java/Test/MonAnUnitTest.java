/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.MonAn;
import com.mycompany.services.MonAnServices;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author LenVo
 */
public class MonAnUnitTest {
    MonAnServices mas;
    MonAn ma = new MonAn();
    int maMA = 100;
    
    @Test
    public void test1() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM MonAn");
//                + "WHERE isnull(isDeleted)");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next())
        {
         String name = rs.getString("TenMA");
         s.add(name);
        }
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(),kq.size());
        if(conn != null)
            conn.close(); 
    }
    @Test
        public void test2() throws SQLException{
        mas = new MonAnServices();
        MonAn ma = new MonAn();
        ma.setMaMA(maMA);
        ma.setTenMA("Ốc bươu nhồi thịt");
        ma.setLoai("Thực phẩm");
        ma.setDonViTinh("Phần");
        ma.setDonGia(BigDecimal.valueOf(300000));
        int a = mas.getListMonAn(null).size();
        mas.addMonAnVaoDB(ma);
        Assertions.assertEquals(a + 1,mas.getListMonAn(null).size());
    }
    @Test
        public void test3() throws SQLException{
            mas = new MonAnServices();
            MonAn ma = mas.findMonAn("Ốc bươu nhồi thịt");
            ma.setTenMA("Ốc huyết");
            mas.updateMonAnVaoDB(ma);
            Assertions.assertNull(mas.findMonAn("Ốc bươu nhồi thịt"));
        }
    @Test
        public void test4() throws SQLException{
            mas = new MonAnServices();
            MonAn ma = mas.findMonAn("Sting"); 
            int a = mas.getListMonAn(null).size();
            mas.xoaMonAn(ma);
            Assertions.assertEquals(a - 1, mas.getListMonAn(null).size()); 
    }
    @Test
      //test tên sảnh
        public void Test5() throws SQLException{
            mas = new MonAnServices();
            Assertions.assertNull(mas.findMonAn("Hàu nướng phô mai"));
        }
}
