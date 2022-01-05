/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author ANHMINH
 */
public class HoaDon {
    private int maHD;
    private int maTiec;
    private BigDecimal thanhTien;
    private String tinhTrang;
    private Date NgayLap;
    public HoaDon(){
        
    }

    /**
     * @return the maHD
     */
    public int getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the maTiec
     */
    public int getMaTiec() {
        return maTiec;
    }

    /**
     * @param maTiec the maTiec to set
     */
    public void setMaTiec(int maTiec) {
        this.maTiec = maTiec;
    }

    /**
     * @return the thanhTien
     */
    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    /**
     * @return the NgayLap
     */
    public Date getNgayLap() {
        return NgayLap;
    }

    /**
     * @param NgayLap the NgayLap to set
     */
    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }
}
