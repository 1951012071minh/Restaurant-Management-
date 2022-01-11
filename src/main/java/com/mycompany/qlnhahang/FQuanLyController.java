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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
    @FXML private TableView tvHoaDon;
    @FXML private DatePicker dpFromHD;
    @FXML private DatePicker dpToHD;
    @FXML private RadioButton rbToanBoHD;
    @FXML private TextField txtDoanhThuHD;
    @FXML private TextField txtMaTiecHD;
    @FXML private TextField txtNgayLapHD;
    @FXML private TextField txtThanhTienHD;
    @FXML private TextField txtMaHD;
    @FXML private TextField txtTinhTrangHD;
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
        try {
            this.loadTabHD();
        } catch (SQLException ex) {
            Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    public void loadTabHD() throws SQLException{
        this.loadTvHoaDonView();
        this.loadTvHoaDonData(null, null);
        this.dpFromHD.setValue(LocalDate.now());
        this.dpToHD.setValue(LocalDate.now());
        this.rbToanBoHD.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if(rbToanBoHD.isSelected()){
                try {
                    loadTvHoaDonData(null, null);
                } catch (SQLException ex) {
                    Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                try {
                    loadTvHoaDonData(java.sql.Date.valueOf(dpFromHD.getValue()), java.sql.Date.valueOf(dpToHD.getValue()));
                } catch (SQLException ex) {
                    Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        this.dpFromHD.valueProperty().addListener((ov, oldValue, newValue) -> {
            if(!rbToanBoHD.isSelected())
                try {
                loadTvHoaDonData(java.sql.Date.valueOf(dpFromHD.getValue()), java.sql.Date.valueOf(dpToHD.getValue()));
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        this.dpToHD.valueProperty().addListener((ov, oldValue, newValue) -> {
            if(!rbToanBoHD.isSelected())
                try {
                loadTvHoaDonData(java.sql.Date.valueOf(dpFromHD.getValue()), java.sql.Date.valueOf(dpToHD.getValue()));
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        tvHoaDon.setRowFactory((tv) -> {
            TableRow<HoaDon> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    HoaDon rowData = row.getItem();
                    this.txtMaHD.setText(String.valueOf(rowData.getMaHD()));
                    this.txtMaTiecHD.setText(String.valueOf(rowData.getMaTiec()));
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    this.txtNgayLapHD.setText(date.format(rowData.getNgayLap()));
                    this.txtTinhTrangHD.setText(rowData.getTinhTrang());
                    this.txtThanhTienHD.setText(String.valueOf(rowData.getThanhTien()));
                }
            });
            return row;
        });
    }
    public void loadTabDDT(){
        loadTvDatTiecView();
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
        this.loadTvHoaDonData(null, null);
    }
    private void loadTvHoaDonView(){
        TableColumn colMaTiec = new TableColumn("Mã tiệc");
        colMaTiec.setCellValueFactory(new PropertyValueFactory("MaTiec"));
        colMaTiec.setPrefWidth(90);
        
        TableColumn colMaHD = new TableColumn("Mã hóa đơn");
        colMaHD.setCellValueFactory(new PropertyValueFactory("MaHD"));
        colMaHD.setPrefWidth(90);
        
        TableColumn colNgayLap = new TableColumn("Ngày lập");
        colNgayLap.setCellValueFactory(new PropertyValueFactory("NgayLap"));
        colNgayLap.setPrefWidth(250);
        
        TableColumn colThanhTien = new TableColumn("Thành tiền");
        colThanhTien.setCellValueFactory(new PropertyValueFactory("ThanhTien"));
        colThanhTien.setPrefWidth(100);
        
        TableColumn colTinhTrang = new TableColumn("Tình trạng");
        colTinhTrang.setCellValueFactory(new PropertyValueFactory("TinhTrang"));
        colTinhTrang.setPrefWidth(100);
        this.tvHoaDon.getColumns().addAll(colMaTiec, colMaHD, colNgayLap, colThanhTien, colTinhTrang);
    }
    
    private void loadTvDatTiecView(){
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
    private void loadTvHoaDonData(Date d1, Date d2) throws SQLException{
        HoaDonServices s = new HoaDonServices();
        this.tvHoaDon.setItems(FXCollections.observableList(s.getListDichVu(d1, d2)));
        this.txtDoanhThuHD.setText(String.valueOf(s.getDoanhThu(d1, d2)));
    }
}
