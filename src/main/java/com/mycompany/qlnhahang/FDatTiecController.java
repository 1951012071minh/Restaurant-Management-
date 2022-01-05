/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.pojo.MonAn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FDatTiecController implements Initializable {
    @FXML TableView<MonAn> tvThucAn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTvThucAn();
    }    
    private void loadTvThucAn(){
        TableColumn colMaMA = new TableColumn("Mã món ăn");
        colMaMA.setCellValueFactory(new PropertyValueFactory("MaMA"));
        colMaMA.setPrefWidth(200);
        this.tvThucAn.getColumns().addAll(colMaMA);
    }
}
