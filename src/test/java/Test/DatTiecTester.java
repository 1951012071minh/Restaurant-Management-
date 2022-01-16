/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.pojo.DatDichVu;
import com.mycompany.pojo.DatMonAn;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.HoaDon;
import com.mycompany.services.AccountServices;
import com.mycompany.services.DatDichVuServices;
import com.mycompany.services.DatMonAnServices;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.HoaDonServices;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ANHMINH
 */
public class DatTiecTester {
    DatTiecServices datTiecSV;
    HoaDonServices hoaDonSV;
    DatMonAnServices datMonAnSV;
    DatDichVuServices datDichVuSV;
    DichVuServices dichVuSV;
    int maTiec = 20;
    @Test
    //Test đặt tiệc
    public void Test01() throws SQLException{
        datTiecSV = new DatTiecServices();
        hoaDonSV = new HoaDonServices();
        DatTiec d = new DatTiec();
        d.setMaTiec(maTiec);
        d.setMaKH(2);
        d.setNgayToChuc(java.sql.Date.valueOf(LocalDate.now()));
        d.setNgayDat(java.sql.Date.valueOf(LocalDate.now()));
        d.setSoLuongBan(10);
        d.setSoLuongKhach(100);
        d.setTenTiec("Tiệc cưới");
        d.setMaSanh(1);
        d.setBuoi("Sáng");
        int a = datTiecSV.getListDatTiec(null).size();
        datTiecSV.addDatTiec(d);
        HoaDon h = new HoaDon();
        h.setMaTiec(maTiec);
        hoaDonSV.addHoaDon(h);
        Assertions.assertEquals(a + 1, datTiecSV.getListDatTiec(null).size());
    }
    @Test
    //Test đặt món và tự động cập nhật tiền hóa đơn!
    public void Test02() throws SQLException{
        hoaDonSV = new HoaDonServices();
        HoaDon h = hoaDonSV.getHoaDon(maTiec);
        datMonAnSV = new DatMonAnServices();
        DatMonAn d = new DatMonAn();
        d.setMaMA(1);
        d.setMaTiec(maTiec);
        d.setSoLuong(10);
        int a = datMonAnSV.getListDatMonAn(maTiec).size();
        datMonAnSV.addDatMonAn(d);
        Assertions.assertNotNull(datMonAnSV.getDatMonAn(maTiec, 1));
        Assertions.assertEquals(a + 1, datMonAnSV.getListDatMonAn(maTiec).size());
        Assertions.assertEquals(10, datMonAnSV.getDatMonAn(maTiec, 1).getSoLuong());
        Assertions.assertEquals(datMonAnSV.getDatMonAn(maTiec, 1).getThanhTien().add(h.getThanhTien()), hoaDonSV.getHoaDon(maTiec).getThanhTien());
    }
    @Test
    //Test update món
    public void Test03() throws SQLException{
        datMonAnSV = new DatMonAnServices();
        DatMonAn d = datMonAnSV.getDatMonAn(maTiec, 1);
        d.setSoLuong(5);
        datMonAnSV.updateDatMonAn(d);
        Assertions.assertEquals(5, datMonAnSV.getDatMonAn(maTiec, 1).getSoLuong());
    }
    
    @Test
    //Test xóa món ăn và cập nhật tiền trong hóa đơn
    public void Test04() throws SQLException{
        hoaDonSV = new HoaDonServices();
        HoaDon h = hoaDonSV.getHoaDon(maTiec);
        datMonAnSV = new DatMonAnServices();
        DatMonAn d = datMonAnSV.getDatMonAn(maTiec, 1);
        int a = datMonAnSV.getListDatMonAn(maTiec).size();
        BigDecimal a1 = h.getThanhTien().subtract(d.getThanhTien());
        datMonAnSV.xoaDatMonAn(d);
        Assertions.assertNull(datMonAnSV.getDatMonAn(maTiec, 1));
        Assertions.assertEquals(a - 1, datMonAnSV.getListDatMonAn(maTiec).size());
        Assertions.assertEquals(a1 , hoaDonSV.getHoaDon(maTiec).getThanhTien());
    }
    @Test
    //Test đặt dịch vụ
    public void Test05() throws SQLException{
        datDichVuSV = new DatDichVuServices();
        DatDichVu d = new DatDichVu();
        d.setMaDV(1);
        d.setMaTiec(maTiec);
        int a = datDichVuSV.getListDichVuDat(maTiec).size();
        datDichVuSV.addDatDichVu(d);
        Assertions.assertNotNull(datDichVuSV.getDatDV(maTiec, 1));
        Assertions.assertEquals(a + 1, datDichVuSV.getListDichVuDat(maTiec).size());
    }
    
    @Test
    //Test xóa dịch vụ đã đặt
    public void Test06() throws SQLException{
        datDichVuSV = new DatDichVuServices();
        DatDichVu d = datDichVuSV.getDatDV(maTiec, 1);
        int a = datDichVuSV.getListDichVuDat(maTiec).size();
        datDichVuSV.xoaDatDichVu(d);
        Assertions.assertNull(datDichVuSV.getDatDV(maTiec, 1));
        Assertions.assertEquals(a - 1, datDichVuSV.getListDichVuDat(maTiec).size());
    }
    //Update thông tin đặt tiệc
    @Test
    public void Test07() throws SQLException{
        datTiecSV = new DatTiecServices();
        DatTiec d = datTiecSV.FindDatTiec(maTiec);
        d.setBuoi("Tối");
        datTiecSV.updateDatTiec(d);
        Assertions.assertEquals("Tối", datTiecSV.FindDatTiec(maTiec).getBuoi()); 
    }
    @Test
    public void Test08() throws SQLException{
        datTiecSV = new DatTiecServices();
        Assertions.assertNotNull(datTiecSV.FindDatTiec(20));
    }
    @Test
    public void Test09() throws SQLException{
        datTiecSV = new DatTiecServices();
        hoaDonSV = new HoaDonServices();
        DatTiec d = new DatTiec();
        d.setMaTiec(datTiecSV.getMaxDatTiec());
        d.setMaKH(3);
        d.setNgayToChuc(java.sql.Date.valueOf(LocalDate.now()));
        d.setNgayDat(java.sql.Date.valueOf(LocalDate.now()));
        d.setSoLuongBan(10);
        d.setSoLuongKhach(100);
        d.setTenTiec("Tiệc cưới");
        d.setMaSanh(1);
        d.setBuoi("Tối");
        int a = datTiecSV.getListDatTiec(null).size();
        if(datTiecSV.checkDatTiec(1, (Date) d.getNgayDat(), d.getBuoi()) == 0){
            datTiecSV.addDatTiec(d);
            HoaDon h = new HoaDon();
            h.setMaTiec(d.getMaTiec());
            hoaDonSV.addHoaDon(h);
        }
        Assertions.assertEquals(a, datTiecSV.getListDatTiec(null).size());
    }
    //Xóa 1 tiệc!
    @Test
    public void Test10() throws SQLException{
        datTiecSV = new DatTiecServices();
        int a = datTiecSV.getListDatTiec(null).size();
        datTiecSV.delDatTiec(maTiec);
        Assertions.assertEquals(a - 1, datTiecSV.getListDatTiec(null).size()); 
    }
}
