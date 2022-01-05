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
public class Account {
    private int MaAccount;
    private String PassWord;
    private String TypeUser;
    private String Username;

    public Account() {
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
     * @return the PassWord
     */
    public String getPassWord() {
        return PassWord;
    }

    /**
     * @param PassWord the PassWord to set
     */
    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    /**
     * @return the TypeUser
     */
    public String getTypeUser() {
        return TypeUser;
    }

    /**
     * @param TypeUser the TypeUser to set
     */
    public void setTypeUser(String TypeUser) {
        this.TypeUser = TypeUser;
    }

    /**
     * @return the Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username the Username to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }
    
}
