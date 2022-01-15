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
    private int MaNV;
    private String TenNV;
    private String SDT;
    private String CMND;
    private int MaBP;
    private String ChucVu;
    private BigDecimal Luong;
    private int MaAccount;

    public NhanVien() {
    }
    
    public NhanVien(int MaNV,String TenNV, String SDT, String ChucVu) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SDT = SDT;
        this.ChucVu = ChucVu;
    }
    
    public NhanVien(int MaNV, String TenNV, String SDT, String CMND, int MaBP, String ChucVu, BigDecimal Luong, int MaAccount) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SDT = SDT;
        this.CMND = CMND;
        this.MaBP = MaBP;
        this.ChucVu = ChucVu;
        this.Luong = Luong;
        this.MaAccount = MaAccount;
    }
    /**
     * @return the MaNV
     */
    public int getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * @return the TenNV
     */
    public String getTenNV() {
        return TenNV;
    }

    /**
     * @param TenNV the TenNV to set
     */
    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the CMND
     */
    public String getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    /**
     * @return the ChucVu
     */
    public String getChucVu() {
        return ChucVu;
    }

    /**
     * @param ChucVu the ChucVu to set
     */
    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    /**
     * @return the Luong
     */
    public BigDecimal getLuong() {
        return Luong;
    }

    /**
     * @param Luong the Luong to set
     */
    public void setLuong(BigDecimal Luong) {
        this.Luong = Luong;
    }

    /**
     * @return the MaAccount
     */
    public int getMaAccount() {
        return MaAccount;
    }

    /**
     * @param MaAccount the MaAccount to set
     */
    public void setMaAccount(int MaAccount) {
        this.MaAccount = MaAccount;
    }

    /**
     * @return the MaBP
     */
    public int getMaBP() {
        return MaBP;
    }

    /**
     * @param MaBP the MaBP to set
     */
    public void setMaBP(int MaBP) {
        this.MaBP = MaBP;
    }
    
    
}
