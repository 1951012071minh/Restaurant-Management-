/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author ANHMINH
 */
public class KhachHang {
    private int MaKH;
    private String TenKH;
    private String CMND;
    private String DiaChi;
    private String GioiTinh;
    private int MaAcc;
    private String SDT;

    

    public KhachHang() {
    }
    public String ToString()
    {
        return this.MaKH +this.TenKH +this.CMND +this.DiaChi +this.GioiTinh + this.MaAcc + this.SDT; 
    }
    public KhachHang(int maKH, String TenKH, String CMND, String DiaChi, String GioiTinh, int MaAcc, String SDT) {
        this.MaKH = maKH;
        this.TenKH = TenKH;
        this.CMND = CMND;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;
        this.MaAcc = MaAcc;
        this.SDT = SDT;
    }
    /**
     * @return the MaKH
     */
    public int getMaKH() {
        return MaKH;
    }

    /**
     * @param MaKH the MaKH to set
     */
    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    /**
     * @return the TenKH
     */
    public String getTenKH() {
        return TenKH;
    }

    /**
     * @param TenKH the TenKH to set
     */
    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
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
     * @return the DiaChi
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     * @param DiaChi the DiaChi to set
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     * @return the GioiTinh
     */
    public String getGioiTinh() {
        return GioiTinh;
    }

    /**
     * @param GioiTinh the GioiTinh to set
     */
    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    /**
     * @return the MaAcc
     */
    public int getMaAcc() {
        return MaAcc;
    }

    /**
     * @param MaAcc the MaAcc to set
     */
    public void setMaAcc(int MaAcc) {
        this.MaAcc = MaAcc;
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
}
