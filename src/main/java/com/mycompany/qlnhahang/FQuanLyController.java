/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.KhachHang;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.KhachHangServices;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class FQuanLyController implements Initializable {
    @FXML private TableView<KhachHang> tvKhachHang;
    @FXML private TextField txtMaTiecDDT;
    @FXML private TableView tvDatTiec;
    @FXML private TextField txtMaKHDDT;
    @FXML private TextField txtBuoiDDT;
    @FXML private TextField txtNgayDatDDT;
    @FXML private TextField txtNgayToChucDDT;
    @FXML private TextField txtTimKiemDDT;
    
    /**
     * Initializes the controller class.
     */    
        
    private void LoadTvKhachHangView(){
        
        TableColumn colMaKH = new TableColumn("Mã khách hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(60);  
        
        TableColumn colTenKH = new TableColumn("Tên khách hàng");
        colTenKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colTenKH.setPrefWidth(150); 
        
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("GioiTinh"));
        colGioiTinh.setPrefWidth(60);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(100);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(100);
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("DiaChi"));
        colDiaChi.setPrefWidth(100);
        
        TableColumn colMaAcc = new TableColumn("Mã acc");
        colMaAcc.setCellValueFactory(new PropertyValueFactory("MaAcc"));
        colMaAcc.setPrefWidth(150);
        this.tvKhachHang.getColumns().addAll(colMaKH, colTenKH, colGioiTinh,colCMND, colSDT, colDiaChi, colMaAcc);
    }

    private void LoadTvKhachHang (String kw) throws  SQLException{
        KhachHangServices s= new KhachHangServices();
        this.tvKhachHang.setItems(FXCollections.observableList(s.getListKhachHang(kw)));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        this.loadTabDDT();
    }
    
    public void loadTabDDT(){
        loadTvDatTiec();
        try {
            loadTvDatTiecData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tvDatTiec.setRowFactory((tv) -> {
            TableRow<DatTiec> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DatTiec rowData = row.getItem();
                    this.txtMaTiecDDT.setText(String.valueOf(rowData.getMaTiec()));
                    this.txtMaKHDDT.setText(String.valueOf(rowData.getMaKH()));
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    this.txtNgayDatDDT.setText(date.format(rowData.getNgayDat()));
                    this.txtNgayToChucDDT.setText(date.format(rowData.getNgayToChuc()));
                    this.txtBuoiDDT.setText(rowData.getBuoi());
                }
            });
            return row;
        });
        this.txtTimKiemDDT.textProperty().addListener((evt) -> {
            try {
                this.loadTvDatTiecData(this.txtTimKiemDDT.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void huyTiecHandler(ActionEvent event) throws SQLException, ParseException{
        DatTiecServices d = new DatTiecServices();
        d.delDatTiec(parseInt(this.txtMaTiecDDT.getText()));
        this.txtMaTiecDDT.clear();
        this.txtMaKHDDT.clear();
        this.txtNgayDatDDT.clear();
        this.txtNgayToChucDDT.clear();
        this.txtBuoiDDT.clear();
        this.loadTvDatTiecData(null);
    }
    
    private void loadTvDatTiec(){
        TableColumn colMaTiec = new TableColumn("Mã tiệc");
        colMaTiec.setCellValueFactory(new PropertyValueFactory("MaTiec"));
        colMaTiec.setPrefWidth(90);
        
        TableColumn colMaKH = new TableColumn("Mã khách hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(90);
        
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
        
        TableColumn colSLB = new TableColumn("Số lượng bàn");
        colSLB.setCellValueFactory(new PropertyValueFactory("SoLuongBan"));
        colSLB.setPrefWidth(150);
        
        TableColumn colSLK = new TableColumn("Số lượng khách");
        colSLK.setCellValueFactory(new PropertyValueFactory("SoLuongKhach"));
        colSLK.setPrefWidth(150);
        
        
        TableColumn colBuoi = new TableColumn("Buổi");
        colBuoi.setCellValueFactory(new PropertyValueFactory("Buoi"));
        colBuoi.setPrefWidth(150);
        this.tvDatTiec.getColumns().addAll(colMaTiec, colTenTiec, colMaSanh, colNgayDat, colNgayToChuc, colBuoi, colSLB, colSLK);
    }
    
    public void chiTietHandler(ActionEvent event) throws SQLException, ParseException, IOException{
        if(!"".equals(this.txtMaTiecDDT.getText())){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("FChiTietDatTiec.fxml"));
            Parent d = loader.load();
            Scene scene = new Scene(d);
            FChiTietDatTiecController controller = loader.getController();
            KhachHangServices ks = new KhachHangServices();
            controller.loadTabDatTiec(ks.getKhachHang(parseInt(this.txtMaKHDDT.getText())), parseInt(this.txtMaTiecDDT.getText()));
            stage.setScene(scene);
            stage.show();
        }
        else
            Utils.getBox("Vui lòng chọn 1 tiệc!", Alert.AlertType.INFORMATION).show();
    }
    private void loadTvDatTiecData(String ma) throws SQLException{
        DatTiecServices s = new DatTiecServices();
        this.tvDatTiec.setItems(FXCollections.observableList(s.getListDichVu(ma)));
    }
}
