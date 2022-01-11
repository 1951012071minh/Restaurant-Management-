/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DichVu;
import com.mycompany.services.DichVuServices;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FThemDichVuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField txt_MaDV;
    @FXML private TextField txt_TenDV;
    @FXML private TextField txt_DonGia;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void addDichVuHandler (ActionEvent event) throws  SQLException, ParseException{
        DichVuServices dvs = new DichVuServices();
        DichVu dv = dvs.findDichVu(txt_TenDV.getText());
        if(dv.getMaDV() == 0){
            //thêm mới
            dv.setMaDV(dvs.getMaxDV());
            dv.setTenDV(this.txt_TenDV.getText());
            dv.setDonGia(BigDecimal.valueOf((Integer.parseInt(this.txt_DonGia.getText()))));
            dvs.addDichVuVaoDB(dv);
            Utils.getBox("Thêm dịch vụ thành công", Alert.AlertType.INFORMATION).show();
            this.txt_TenDV.clear();
            this.txt_DonGia.clear();
        }else {
            //thêm khi đã xóa mềm
            if(dv.getIsDeleted() != null){
                dv.setTenDV(this.txt_TenDV.getText());
                dv.setDonGia(BigDecimal.valueOf((Integer.parseInt(this.txt_DonGia.getText()))));
                dvs.addDichVuVaoDBIsDeleted(dv);
                Utils.getBox("Thêm dịch vụ thành công", Alert.AlertType.INFORMATION).show();
                this.txt_TenDV.clear();
                this.txt_DonGia.clear();
            }else {
                Utils.getBox("Dịch vụ đã tồn tại", Alert.AlertType.WARNING).show();
        }
      }
    }
}
