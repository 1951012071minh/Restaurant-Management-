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
public class DichVu {
    /**
     * @return the maDV
     */
    public int getMaDV() {
        return maDV;
    }

    /**
     * @param maDV the maDV to set
     */
    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    /**
     * @return the tenDV
     */
    public String getTenDV() {
        return tenDV;
    }

    /**
     * @param tenDV the tenDV to set
     */
    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
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
    private int maDV;
    private String tenDV;
    private BigDecimal donGia;
    private Date isDeleted;

    /**
     * @return the isDeleted
     */
    public Date getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(Date isDeleted) {
        this.isDeleted = isDeleted;
    }
}
