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
public class Sanh {
      
    private BigDecimal DonGia;
    private int MaSanh;
    private int SucChua;
    private String Tang;
    private String TenSanh;

    public Sanh() {
    }

    
    
    

    /**
     * @return the DonGia
     */
    public BigDecimal getDonGia() {
        return DonGia;
    }

    /**
     * @param DonGia the DonGia to set
     */
    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    /**
     * @return the MaSanh
     */
    public int getMaSanh() {
        return MaSanh;
    }

    /**
     * @param MaSanh the MaSanh to set
     */
    public void setMaSanh(int MaSanh) {
        this.MaSanh = MaSanh;
    }

    /**
     * @return the SucChua
     */
    public int getSucChua() {
        return SucChua;
    }

    /**
     * @param SucChua the SucChua to set
     */
    public void setSucChua(int SucChua) {
        this.SucChua = SucChua;
    }

    /**
     * @return the Tang
     */
    public String getTang() {
        return Tang;
    }

    /**
     * @param Tang the Tang to set
     */
    public void setTang(String Tang) {
        this.Tang = Tang;
    }

    /**
     * @return the TenSanh
     */
    public String getTenSanh() {
        return TenSanh;
    }

    /**
     * @param TenSanh the TenSanh to set
     */
    public void setTenSanh(String TenSanh) {
        this.TenSanh = TenSanh;
    }
   
}
