/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.util.Date;



/**
 *
 * @author ANHMINH
 */
public class DatTiec {
    private int maTiec;
    private String tenTiec;
    private int maSanh;
    private int maKH;
    private Date ngayDat;
    private Date ngayToChuc;
    private int soLuongBan;
    private int soLuongKhach;
    private String buoi;
    public DatTiec(){
        
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
     * @return the tenTiec
     */
    public String getTenTiec() {
        return tenTiec;
    }

    /**
     * @param tenTiec the tenTiec to set
     */
    public void setTenTiec(String tenTiec) {
        this.tenTiec = tenTiec;
    }

    /**
     * @return the maSanh
     */
    public int getMaSanh() {
        return maSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(int maSanh) {
        this.maSanh = maSanh;
    }

    /**
     * @return the maKH
     */
    public int getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the ngayToChuc
     */
    public Date getNgayToChuc() {
        return ngayToChuc;
    }

    /**
     * @param ngayToChuc the ngayToChuc to set
     */
    public void setNgayToChuc(Date ngayToChuc) {
        this.ngayToChuc = ngayToChuc;
    }

    /**
     * @return the soLuongBan
     */
    public int getSoLuongBan() {
        return soLuongBan;
    }

    /**
     * @param noLuongBan the soLuongBan to set
     */
    public void setSoLuongBan(int noLuongBan) {
        this.soLuongBan = noLuongBan;
    }

    /**
     * @return the soLuongKhach
     */
    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    /**
     * @param soLuongKhach the soLuongKhach to set
     */
    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

    /**
     * @return the buoi
     */
    public String getBuoi() {
        return buoi;
    }

    /**
     * @param buoi the buoi to set
     */
    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }
}
