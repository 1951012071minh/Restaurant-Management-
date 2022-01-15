/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Account;
import com.mycompany.pojo.NhanVien;
import com.mycompany.services.AccountServices;
import com.mycompany.services.BoPhanServices;
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

import com.mycompany.services.NhanVienServices;
/**
 *
 * @author Admin
 */
public class NhanVienTester {
    
    NhanVienServices nhanVienSV;
    AccountServices accountSV;
    BoPhanServices boPhanSV;
    int maAccount = 10;
    int maNhanVien = 5;

    
    //Test thêm nhân viên
    @Test
    public void test1() throws SQLException{
        nhanVienSV = new NhanVienServices();
        accountSV = new AccountServices();
        boPhanSV = new BoPhanServices();
        NhanVien nv = new NhanVien();
        Account ac = new Account();
        nv.setMaNV(maNhanVien);
        nv.setTenNV("Nguyễn Văn A");
        nv.setSDT("12345678");
        nv.setCMND("1234585");
        nv.setMaBP(4);
        nv.setChucVu("Phucvu");
        nv.setLuong(null);
        nv.setMaAccount(10);
        
        ac.setMaAccount(10);
        ac.setUsername("nvanA");
        ac.setPassWord("123456");
        ac.setTypeUser("NV");
        
        int a = nhanVienSV.getListNhanVien(null).size();
        int b = accountSV.getListAccount().size();
        int c = boPhanSV.getBP(4).getSoLuongNV();
        accountSV.addAccount(ac);
        nhanVienSV.addNhanVien(nv);
        Assertions.assertNotNull(nhanVienSV.getNhanVien(5));
        Assertions.assertEquals(c + 1, boPhanSV.getBP(4).getSoLuongNV());
        Assertions.assertEquals(a + 1, nhanVienSV.getListNhanVien(null).size());
        Assertions.assertEquals(b + 1, accountSV.getListAccount().size());
    }
    
    //Test cập nhật nhân viên
    @Test
    public void test2() throws SQLException{
        nhanVienSV = new NhanVienServices();
        boPhanSV = new BoPhanServices();
        NhanVien nv = nhanVienSV.getNhanVien(maNhanVien);
        int ma = nv.getMaBP();
        int a = boPhanSV.getBP(ma).getSoLuongNV();
        nv.setTenNV("Sangdien");
        nv.setMaBP(3);
        int b = boPhanSV.getBP(3).getSoLuongNV();
        nhanVienSV.updateNhanVien(nv);
        Assertions.assertEquals("Sangdien", nhanVienSV.getNhanVien(maNhanVien).getTenNV());
        Assertions.assertEquals(a - 1, boPhanSV.getBP(ma).getSoLuongNV());
        Assertions.assertEquals(b + 1, boPhanSV.getBP(3).getSoLuongNV());
    }
    
    //Test xoá nhân viên
    @Test
    public void test3() throws SQLException{
        accountSV = new AccountServices();
        nhanVienSV = new NhanVienServices();
        boPhanSV = new BoPhanServices();
        NhanVien nv = nhanVienSV.getNhanVien(maNhanVien);
        int ma = nv.getMaBP();
        int a = nhanVienSV.getListNhanVien(null).size();
        int b = boPhanSV.getBP(ma).getSoLuongNV();
        accountSV.delAccount(nv.getMaAccount());
        
        Assertions.assertNull(nhanVienSV.getNhanVien(5));
        Assertions.assertEquals(a - 1, nhanVienSV.getListNhanVien(null).size());
        Assertions.assertEquals(b - 1, boPhanSV.getBP(ma).getSoLuongNV());
    }
    
    
}