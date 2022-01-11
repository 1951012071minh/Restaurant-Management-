/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.KhachHang;
import com.mycompany.services.KhachHangServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FGiaoDienKHController implements Initializable {
    @FXML ImageView imgDatTiec;
    @FXML ImageView imgThongTinDatTiec;
    @FXML Button a1;
    private KhachHang k;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        imgDatTiec.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FDatTiec.fxml"));
            try {
                Parent d = loader.load();
                Scene scene = new Scene(d);
                FDatTiecController controller = loader.getController();
                KhachHangServices ks = new KhachHangServices();
                k = ks.getKhachHang(2);
                controller.LoadTabDatTiec(k);
                stage.setScene(scene);
                stage.setOnCloseRequest((eh)->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setContentText("Bạn chưa thanh toán!\nVui lòng thanh toán nếu không tiệc sẽ bị hủy!");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK){
                        try {
                            controller.huyTiec();
                        } catch (SQLException ex) {
                            Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                        eh.consume();
            });
            } catch (IOException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
        });
        imgThongTinDatTiec.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FTiecDaDat.fxml"));
            try {
                Parent d = loader.load();
                Scene scene = new Scene(d);
                FTiecDaDatController controller = loader.getController();
                KhachHangServices ks = new KhachHangServices();
                k = ks.getKhachHang(1);
                controller.LoadTabDatTiec(k);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    public KhachHang getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(KhachHang k) {
        this.k = k;
    }
}

