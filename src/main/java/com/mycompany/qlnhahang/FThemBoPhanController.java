/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.BoPhan;
import com.mycompany.services.BoPhanServices;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FThemBoPhanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField txtTenBoPhan;
    @FXML private Button btnThoatThemBP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addBoPhanHandler(ActionEvent event)throws SQLException, ParseException {
      try{
          BoPhan b = new BoPhan();
          BoPhanServices s = new BoPhanServices();
          b.setMaBP(s.getMaxMaBoPhan());
          b.setTenBP(this.txtTenBoPhan.getText());
          b.setSoLuongNV(0);
          try {
             s.addBoPhan(b);
             Utils.getBox("Thêm thành công!", Alert.AlertType.INFORMATION).show();
          } catch (SQLException ex) {
             Utils.getBox("Bộ phận đã tồn tại!", Alert.AlertType.INFORMATION).show();            
             Logger.getLogger(FThemBoPhanController.class.getName()).log(Level.SEVERE, null, ex);
            }
      }catch(NumberFormatException ex){
          Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
      }
    }
    
    public void btnThoatThemBP(ActionEvent event){
        Stage stage = (Stage) btnThoatThemBP.getScene().getWindow();
        stage.close();
    }
}