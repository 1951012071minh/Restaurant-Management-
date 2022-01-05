/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;

/**
 *
 * @author ANHMINH
 */
public class DatMonAn {
    private int maTiec;
    private int maMA;
    private int soLuong;
    private BigDecimal thanhTien;
    public DatMonAn(){
        
    }

    /**
     * @return the maTiec
     */
    public int getMaTiec() {
        return maTiec;
    }

    /**
     * @param MaTiec the maTiec to set
     */
    public void setMaTiec(int MaTiec) {
        this.maTiec = MaTiec;
    }

    /**
     * @return the maMA
     */
    public int getMaMA() {
        return maMA;
    }

    /**
     * @param MaMA the maMA to set
     */
    public void setMaMA(int MaMA) {
        this.maMA = MaMA;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
}
