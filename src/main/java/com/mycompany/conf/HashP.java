/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.conf;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class HashP {
    public static String HashPassword (String password){
        String maHash="";
        char[] chuoi=password.toCharArray();
        Map<Character,Integer> myMap= new HashMap<>();
        //<Character,Integer> 
        //Character la key co the hieu la gia tri cua phan tu trong mang
        //Integer la value la so lan xuat hien trong mang
        for(char i: chuoi){
            if(myMap.containsKey(i)==false){
                //i chua xuat hien trong map 1 lan nao
                myMap.put(i, 1);
            }
            else{
                //i xuat hien truoc do va tang bien
                int soLanXuatHien=myMap.get(i);
                soLanXuatHien++;
                myMap.put(i, soLanXuatHien);
            }
        }
        for(Map.Entry entry : myMap.entrySet()){
            maHash+= entry.getKey() + String.valueOf(entry.getValue());
            if(maHash.charAt(maHash.length() - 1) == '1'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '!');
                maHash += "&*#";
            }
            if(maHash.charAt(maHash.length() - 1) == '2'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '&');
                maHash += "@#$";
            }
            if(maHash.charAt(maHash.length() - 1) == '3'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '@');
                maHash += "$@@";
            }
            if(maHash.charAt(maHash.length() - 1) == '4'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '(');
                maHash += "@$";
            }
            if(maHash.charAt(maHash.length() - 1) == '5'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), ')');
                maHash += "($@";
            }
            if(maHash.charAt(maHash.length() - 1) == '6'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '^');
                maHash += "#$*";
            }
            if(maHash.charAt(maHash.length() - 1) == '7'){
                maHash = maHash.replace(maHash.charAt(maHash.length() - 1), '#');
                maHash += "!#*";
            }
        } return maHash;
       
    }
}


