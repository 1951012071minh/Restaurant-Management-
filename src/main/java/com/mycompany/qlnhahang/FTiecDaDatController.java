/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.HoaDonServices;
import com.mycompany.services.KhachHangServices;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FTiecDaDatController implements Initializable {
    @FXML private TextField txtTenKHTT;
    @FXML private TextField txtSDT;
    @FXML private TextField txtCMND;
    @FXML private TextField txtMaTiec;
    @FXML private TableView tvDatTiec;
    private KhachHang khachHang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void LoadTabDatTiec(KhachHang khachHang) throws SQLException{
        this.khachHang = khachHang;
        this.txtTenKHTT.setText(khachHang.getTenKH());
        this.txtCMND.setText(khachHang.getCMND());
        this.txtSDT.setText(khachHang.getSDT());
        loadTvDatTiec();
        loadTvDatTiecData(khachHang.getMaKH());
        tvDatTiec.setRowFactory((tv) -> {
            TableRow<DatTiec> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DatTiec rowData = row.getItem();
                    this.txtMaTiec.setText(String.valueOf(rowData.getMaTiec()));
                }
            });
            return row;
        });
    }
    private void loadTvDatTiec(){
        TableColumn colMaTiec = new TableColumn("Mã tiệc");
        colMaTiec.setCellValueFactory(new PropertyValueFactory("MaTiec"));
        colMaTiec.setPrefWidth(90);
        
        TableColumn colTenTiec = new TableColumn("Tên tiệc");
        colTenTiec.setCellValueFactory(new PropertyValueFactory("TenTiec"));
        colTenTiec.setPrefWidth(250);
        
        TableColumn colMaSanh = new TableColumn("Mã sảnh");
        colMaSanh.setCellValueFactory(new PropertyValueFactory("MaSanh"));
        colMaSanh.setPrefWidth(100);
        
        TableColumn colNgayDat = new TableColumn("Ngày đặt");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("NgayDat"));
        colNgayDat.setPrefWidth(100);
        
        TableColumn colNgayToChuc = new TableColumn("Ngày tổ chức");
        colNgayToChuc.setCellValueFactory(new PropertyValueFactory("NgayToChuc"));
        colNgayToChuc.setPrefWidth(150);
        
        TableColumn colBuoi = new TableColumn("Buổi");
        colBuoi.setCellValueFactory(new PropertyValueFactory("Buoi"));
        colBuoi.setPrefWidth(150);
        this.tvDatTiec.getColumns().addAll(colMaTiec, colTenTiec, colMaSanh, colNgayDat, colNgayToChuc, colBuoi);
    }
    private void loadTvDatTiecData(int ma) throws SQLException{
        DatTiecServices s = new DatTiecServices();
        this.tvDatTiec.setItems(FXCollections.observableList(s.getListDatTiecByKhachHang(ma)));
    }
    
    public void chiTietHandler(ActionEvent event) throws SQLException, ParseException, IOException{
        if(!"".equals(this.txtMaTiec.getText())){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("FChiTietDatTiec.fxml"));
            Parent d = loader.load();
            Scene scene = new Scene(d);
            FChiTietDatTiecController controller = loader.getController();;
            controller.loadTabDatTiec(this.khachHang, parseInt(this.txtMaTiec.getText()));
            stage.setScene(scene);
            stage.show();
        }
        else
            Utils.getBox("Vui lòng chọn 1 tiệc!", Alert.AlertType.INFORMATION).show();
    }
    public void quayLaiHandler(ActionEvent event) throws SQLException, ParseException, IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FGiaoDienKH.fxml"));
        Parent d = loader.load();
        Scene scene = new Scene(d);
        FGiaoDienKHController controller = loader.getController();;
        controller.setK(this.khachHang);
        stage.setScene(scene);
    }
    public void huyTiecHandler(ActionEvent event) throws SQLException, ParseException{
        if(!"".equals(this.txtMaTiec.getText())){
            DatTiecServices d = new DatTiecServices();
            d.delDatTiec(parseInt(this.txtMaTiec.getText()));
            this.txtMaTiec.clear();
            this.loadTvDatTiecData(khachHang.getMaKH());
        }
        else
            Utils.getBox("Vui lòng chọn 1 tiệc!", Alert.AlertType.INFORMATION).show();
    }
}
