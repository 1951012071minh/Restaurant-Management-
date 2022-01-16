package com.mycompany.qlnhahang;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.conf.Utils;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.SanhServices;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FThemSanhController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField txt_MaSanh;
    @FXML private TextField txt_TenSanh;
    @FXML private TextField txt_Tang;
    @FXML private TextField txt_SucChua;
    @FXML private TextField txt_GiaTien;
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
        
    
       
    }
    public void addSanhHandler (ActionEvent event) throws  SQLException, ParseException{
        SanhServices ss = new SanhServices();
        Sanh s = ss.findSanh(txt_TenSanh.getText());
        
            if(txt_TenSanh.getText().trim().equals("")|| txt_Tang.getText().trim().equals("")
                    || txt_SucChua.getText().trim().equals("") || txt_GiaTien.getText().trim().equals("")){
                        Utils.getBox("Xin vui lòng nhập đúng và đủ dữ liệu", Alert.AlertType.WARNING).show();}
            else{
                int a = Integer.parseInt(txt_Tang.getText());
                int b = Integer.parseInt(txt_SucChua.getText());
                int c = Integer.parseInt(txt_GiaTien.getText());
                if(a <= 0 || b <= 0 || c <= 0)
                    Utils.getBox("Xin vui lòng nhập số nguyên dương", Alert.AlertType.WARNING).show();
                else if(s == null){
                    //thêm mới
                    s = new Sanh();
                    s.setMaSanh(ss.getMaxSanh());
                    s.setTenSanh(this.txt_TenSanh.getText());
                    s.setTang(parseInt(this.txt_Tang.getText()));
                    s.setSucChua(parseInt(this.txt_SucChua.getText()));
                    s.setDonGia(BigDecimal.valueOf((Integer.parseInt(this.txt_GiaTien.getText()))));
                    ss.addSanhVaoDB(s);
                    Utils.getBox("Thêm sảnh thành công", Alert.AlertType.INFORMATION).show();
                    this.txt_TenSanh.clear();
                    this.txt_Tang.clear();
                    this.txt_SucChua.clear();
                    this.txt_GiaTien.clear();
                } else
                if(s.getIsDeleted() != null){
                    // cập nhật
                    s.setTenSanh(this.txt_TenSanh.getText());
                    s.setTang(parseInt(this.txt_Tang.getText()));
                    s.setSucChua(parseInt(this.txt_SucChua.getText()));
                    s.setDonGia(BigDecimal.valueOf((Integer.parseInt(this.txt_GiaTien.getText()))));
                    ss.addSanhVaoDBIsDeleted(s);
                    Utils.getBox("Thêm sảnh thành công", Alert.AlertType.INFORMATION).show();
                    this.txt_TenSanh.clear();
                    this.txt_Tang.clear();
                    this.txt_SucChua.clear();
                    this.txt_GiaTien.clear();
                }
                else{
                    //báo tồn tại
                    Utils.getBox("Sảnh đã tồn tại", Alert.AlertType.WARNING).show();
        }
    }
}
}
