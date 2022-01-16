/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.MonAn;
import com.mycompany.services.MonAnServices;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FThemThucAnController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField txt_MaMA;
    @FXML private TextField txt_TenMA;
    @FXML private ComboBox cb_Loai;
    @FXML private ComboBox cb_DonViTinh;
    @FXML private TextField txt_DonGia;
    @FXML private Button btn_Them;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList obDVT = FXCollections.observableArrayList("Dĩa","Tô","Xô","Phần","Chai","Thùng");
          cb_DonViTinh.setItems(obDVT);
          cb_DonViTinh.setValue("Dĩa");
        ObservableList obLoai = FXCollections.observableArrayList("Thức ăn","Thức uống");
          cb_Loai.setItems(obLoai);
          cb_Loai.setValue("Thức ăn");
    }
      public void addMonAnHandler (ActionEvent event) throws  SQLException, ParseException{
        MonAnServices mas = new MonAnServices();
        MonAn ma = mas.findMonAn(txt_TenMA.getText());
        if(txt_TenMA.getText().trim().equals("")|| txt_DonGia.getText().trim().equals("")){
                        Utils.getBox("Xin vui lòng nhập đúng và đủ dữ liệu", Alert.AlertType.WARNING).show();}
        else{
            int a = Integer.parseInt(txt_DonGia.getText());
            if(a <= 0)
                Utils.getBox("Xin vui lòng nhập số nguyên dương", Alert.AlertType.WARNING).show();
            else 
                if(ma == null){
                //thêm
                ma = new MonAn();
                ma.setMaMA(mas.getMaxMA());
                ma.setTenMA(this.txt_TenMA.getText());
                ma.setDonGia(BigDecimal.valueOf(Integer.parseInt(this.txt_DonGia.getText())));
                ma.setLoai((String)this.cb_Loai.getValue());
                ma.setDonViTinh((String)this.cb_DonViTinh.getValue());
                mas.addMonAnVaoDB(ma);
                Utils.getBox("Thêm món ăn thành công", Alert.AlertType.INFORMATION).show();
                this.txt_TenMA.clear();
                this.txt_DonGia.clear();
                }else
                    //cập nhật
                    if(ma.getIsDeleted() != null){
                        ma.setTenMA(this.txt_TenMA.getText());
                        ma.setDonGia(BigDecimal.valueOf(Integer.parseInt(this.txt_DonGia.getText())));
                        ma.setLoai((String)this.cb_Loai.getValue());
                        ma.setDonViTinh((String)this.cb_DonViTinh.getValue());
                        mas.addMonAnVaoDBIsDeleted(ma);
                        Utils.getBox("Thêm món ăn thành công", Alert.AlertType.INFORMATION).show();
                        this.txt_TenMA.clear();
                        this.txt_DonGia.clear();
                    }else{
                        Utils.getBox("Món ăn đã tồn tại", Alert.AlertType.WARNING).show();
                    }
        }
    }   
} 

