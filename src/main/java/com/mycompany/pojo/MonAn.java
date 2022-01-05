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
public class MonAn {
    private int maMA;
    private String tenMA;
    private BigDecimal donGia;
    private String loai;
    private String donViTinh;
    
    /**
     * @return the maMA
     */
    public int getMaMA() {
        return maMA;
    }

    /**
     * @param maMA the maMA to set
     */
    public void setMaMA(int maMA) {
        this.maMA = maMA;
    }

    /**
     * @return the tenMA
     */
    public String getTenMA() {
        return tenMA;
    }

    /**
     * @param tenMA the tenMA to set
     */
    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    /**
     * @return the donGia
     */
    public BigDecimal getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the loai
     */
    public String getLoai() {
        return loai;
    }

    /**
     * @param loai the loai to set
     */
    public void setLoai(String loai) {
        this.loai = loai;
    }

    /**
     * @return the donViTinh
     */
    public String getDonViTinh() {
        return donViTinh;
    }

    /**
     * @param donViTinh the donViTinh to set
     */
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
}
