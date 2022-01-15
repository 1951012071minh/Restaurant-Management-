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
public class BoPhan {
    private int MaBP;
    private String TenBP;
    private int SoLuongNV;
    public BoPhan(){
    
    }

    @Override
    public String toString() {
        return this.TenBP;
    }

    
    public BoPhan(int MaBP, String TenBP, int SoLuongNV) {
        this.MaBP = MaBP;
        this.TenBP = TenBP;
        this.SoLuongNV = SoLuongNV;
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

    /**
     * @return the TenBP
     */
    public String getTenBP() {
        return TenBP;
    }

    /**
     * @param TenBP the TenBP to set
     */
    public void setTenBP(String TenBP) {
        this.TenBP = TenBP;
    }

    /**
     * @return the SoLuongNV
     */
    public int getSoLuongNV() {
        return SoLuongNV;
    }

    /**
     * @param SoLuongNV the SoLuongNV to set
     */
    public void setSoLuongNV(int SoLuongNV) {
        this.SoLuongNV = SoLuongNV;
    }
    
}
