/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.HashP;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Account;
import com.mycompany.pojo.KhachHang;
import com.mycompany.services.AccountServices;
import com.mycompany.services.KhachHangServices;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;



public class KhachHangTester {
    private static Connection conn;
    KhachHangServices KHSV;
    AccountServices ACSV;
    HashP HP;
    @Test
    public void testUnique() throws SQLException{
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM account");
        ResultSet rs = stm.executeQuery();
        List<String> s = new ArrayList<>();
        while(rs.next()){
         String name = rs.getString("Username");
            s.add(name);
        }
        Set<String> kq = new HashSet<>(s);
        Assertions.assertEquals(s.size(), kq.size());
        if(conn != null)
            conn.close();
    
    }
//    
    @Test 
    public void testDangNhap()throws  SQLException{
        HP= new HashP();
        ACSV= new AccountServices();
        String us="anhminh0710";
        String p=HP.HashPassword("anhminh0710");
        Assertions.assertTrue(ACSV.CheckLogin(us, p));
        
    }
           
   
    @Test//add khach hang
    public void test1() throws SQLException{
        KHSV = new KhachHangServices();
         HashP hp= new HashP();
        ACSV= new AccountServices();
        KhachHang kh = new KhachHang();
        Account ac = new Account();
        ac.setMaAccount(ACSV.getMaAccount());
        ac.setTypeUser("KH");
        ac.setPassWord(hp.HashPassword("1234"));
        ac.setUsername("nnhatsang");
        kh.setMaKH(KHSV.getMaxKhachHang());
        kh.setTenKH("Sang");
        kh.setGioiTinh("nam");        
        kh.setDiaChi("Biên Hoà");
        kh.setCMND("62862691");
        kh.setSDT("0794024972");
        kh.setMaAcc(ac.getMaAccount());
        int b = KHSV.getListKhachHang(null).size();
        int a = ACSV.getListAccount().size();
        ACSV.addAccount(ac);
        KHSV.addKhachHang(kh);    
        Assertions.assertEquals(a+1, ACSV.getListAccount().size());
        Assertions.assertEquals( b+1, KHSV.getListKhachHang(null).size());
        
    }
   
    @Test
    //Cập nhật khách hàng
       public void test2() throws SQLException{
        KHSV = new KhachHangServices();
        ACSV = new AccountServices();
        KhachHang kh=KHSV.getKhachHangByAccount("nnhatsang");
        kh.setCMND("123456789");
        KHSV.updateKhachhang(kh);
        Assertions.assertEquals("123456789",KHSV.getKhachHangByAccount("nnhatsang").getCMND());
    }
    @Test 
    //xoa khach hang bang account
    public void test3() throws SQLException{
        ACSV = new AccountServices();
        KHSV = new KhachHangServices();
        int a = KHSV.getListKhachHang(null).size();
        ACSV.delAccount(KHSV.getKhachHangByAccount("nnhatsang").getMaAcc());
        Assertions.assertEquals(a - 1, KHSV.getListKhachHang(null).size()); 
    }
}
