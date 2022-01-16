/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.BoPhan;
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

import com.mycompany.services.BoPhanServices;


/**
 *
 * @author Admin
 */
public class BoPhanTester {
    BoPhanServices boPhanSV;
    int maBoPhan = 5;
    
    @Test
    public void TestUniqueTenBP() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM bophan");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next()){
        String name = rs.getString("TenBP");
            s.add(name);
        System.out.println(name);
        }
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(), kq.size());
        if(conn != null)
            conn.close();
    }
    
    //Test thêm bộ phận
    @Test
    public void test1() throws SQLException{
        boPhanSV = new BoPhanServices();
        BoPhan bp = new BoPhan();
        bp.setMaBP(5);
        bp.setTenBP("Bồi bàn");
        bp.setSoLuongNV(0);
        int a = boPhanSV.getListBoPhan(null).size();
        boPhanSV.addBoPhan(bp);
        Assertions.assertEquals(a + 1, boPhanSV.getListBoPhan(null).size());
    }
    
    //Test cập nhật bô phận
    @Test
    public void test2() throws SQLException{
        boPhanSV = new BoPhanServices();
        BoPhan bp = boPhanSV.getBP(maBoPhan);
        bp.setTenBP("abc");
        boPhanSV.updateBoPhan(bp);
        Assertions.assertEquals("abc", boPhanSV.getBP(maBoPhan).getTenBP());
    }
    
    //Test Xoá bộ phận
    @Test
    public void test3() throws SQLException{
       boPhanSV = new BoPhanServices();
       int a = boPhanSV.getListBoPhan(null).size();
       boPhanSV.deleteBoPhan(maBoPhan);
       Assertions.assertEquals(a - 1, boPhanSV.getListBoPhan(null).size());
    }
    

}