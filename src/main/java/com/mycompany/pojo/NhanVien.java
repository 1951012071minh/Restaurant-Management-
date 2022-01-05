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
public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private int SDT;
    private int CMND;
    private int maBoPhan;
    private String chucVu;
    private BigDecimal luong;
    private int maAccount;
    public NhanVien(){
        
    }

    /**
     * @return the maNhanVien
     */
    public int getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    /**
     * @return the SDT
     */
    public int getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the CMND
     */
    public int getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    /**
     * @return the maBoPhan
     */
    public int getMaBoPhan() {
        return maBoPhan;
    }

    /**
     * @param maBoPhan the maBoPhan to set
     */
    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    /**
     * @return the chucVu
     */
    public String getChucVu() {
        return chucVu;
    }

    /**
     * @param chucVu the chucVu to set
     */
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    /**
     * @return the luong
     */
    public BigDecimal getLuong() {
        return luong;
    }

    /**
     * @param luong the luong to set
     */
    public void setLuong(BigDecimal luong) {
        this.luong = luong;
    }

    /**
     * @return the maAccount
     */
    public int getMaAccount() {
        return maAccount;
    }

    /**
     * @param maAccount the maAccount to set
     */
    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }
}
