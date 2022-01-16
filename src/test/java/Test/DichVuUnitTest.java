/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DichVu;
import com.mycompany.services.DichVuServices;
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
public class DichVuUnitTest {
    DichVuServices dvs;
    
    @Test
     public void TestUniqueTenDV() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM DichVu");
//                + "WHERE isnull(isDeleted)");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next())
        {
         String name = rs.getString("TenDV");
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
        dvs = new DichVuServices();
        DichVu dv = new DichVu();
        dv.setMaDV(dvs.getMaxDV());
        dv.setTenDV("Vũ điệu cồng chiêng");
        dv.setDonGia(BigDecimal.valueOf(5000000));
        int a = dvs.getListDichVu(null).size();
        dvs.addDichVuVaoDB(dv);
        Assertions.assertEquals(a + 1,dvs.getListDichVu(null).size());
    }
    @Test
    //sửa
        public void test3() throws SQLException{
            dvs = new DichVuServices();
            DichVu dv = dvs.findDichVu("Vũ điệu cồng chiêng");
            dv.setDonGia(BigDecimal.valueOf(3000000));
            dvs.updateDichVuVaoDB(dv);
            Assertions.assertEquals(BigDecimal.valueOf(3000000), dvs.findDichVu("Vũ điệu cồng chiêng").getDonGia()); 
    }
    @Test
    //xóa dịch vụ
        public void test4() throws SQLException{
            dvs = new DichVuServices();
            DichVu dv = dvs.findDichVu("Vũ điệu cồng chiêng");
            Assertions.assertNull(dvs.findDichVu("Vũ điệu cồng chiêng").getIsDeleted());
            int a = dvs.getListDichVu(null).size();
            dvs.xoaDichVu(dv);
            Assertions.assertEquals(a - 1, dvs.getListDichVu(null).size());
            Assertions.assertNotNull(dvs.findDichVu("Vũ điệu cồng chiêng").getIsDeleted());
    }
    @Test
        public void TestGetDichVuByTenDV() throws SQLException{
            dvs = new DichVuServices();
            Assertions.assertNotNull(dvs.findDichVu("Karaoke"));
            Assertions.assertNull(dvs.findDichVu("Thảm đỏ"));
        }
}
