/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatMonAn;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.DichVu;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.MonAn;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.KhachHangServices;
import com.mycompany.services.MonAnServices;
import com.mycompany.services.SanhServices;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FDatTiecController implements Initializable {
    @FXML private TableView<MonAn> tvThucAn;
    @FXML private TableView<Sanh> tvSanh;
    @FXML private TextField txtMaDV;
    @FXML private TableView<DichVu> tvDichVu;
    @FXML private TableView<KhachHang> tvDatMonAn;
    @FXML private TextField txtTimKiemMA;
    @FXML private TextField txtTimKiemDV;
    @FXML private TextField txtTimKiemSanh;
    @FXML private TextField txtMaTiec;
    @FXML private TextField txtTongSoDV;
    @FXML private TextField txtThanhTienDV;
    @FXML private TextField txtDonGiaDV;
    @FXML private TextField txtDonGiaSanh;
    @FXML private TextField txtThanhTienMA;
    @FXML private TextField txtTongSoMA;
    @FXML private TextField txtMaKH;
    @FXML private TextField txtSoLuongKhach;
    @FXML private TextField txtTenTiec;
    @FXML private TextField txtMaMA;
    @FXML private TextField txtSoBan;
    @FXML private TextField txtNgayDat;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtMaSanh;
    @FXML private TextField txtTenSanh;
    @FXML private TextField txtSucChua;
    @FXML private TextField txtTang;
    @FXML private TextField txtGiaThue;
    @FXML private ComboBox cbBuoi;
    @FXML private DatePicker dpNgayDat;
    @FXML private Tab tabDatSanh;
    @FXML private Tab tabDatDV;
    @FXML private Tab tabDatMA;
    @FXML private Tab tabThanhToan;
    private boolean flag = false;
    private int maTiec;
    private KhachHang khachHang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.LoadTabDatTiec();
            this.LoadTabDatDichVu();
            this.LoadTabDatMonAn();
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }    
    private void loadTvThucAnView(){
        TableColumn colMaMA = new TableColumn("Mã món");
        colMaMA.setCellValueFactory(new PropertyValueFactory("MaMA"));
        colMaMA.setPrefWidth(90);
        
        TableColumn colTenMA = new TableColumn("Tên món");
        colTenMA.setCellValueFactory(new PropertyValueFactory("TenMA"));
        colTenMA.setPrefWidth(250);
        
        TableColumn colLoai = new TableColumn("Loai");
        colLoai.setCellValueFactory(new PropertyValueFactory("Loai"));
        colLoai.setPrefWidth(100);
        
        TableColumn colDonViTinh = new TableColumn("Đơn vị tính");
        colDonViTinh.setCellValueFactory(new PropertyValueFactory("DonViTinh"));
        colDonViTinh.setPrefWidth(100);
        
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
        colDonGia.setPrefWidth(150);
        this.tvThucAn.getColumns().addAll(colMaMA, colTenMA, colLoai, colDonViTinh, colDonGia);
    }
    private void loadTvSanhView(){
        TableColumn colMaSanh = new TableColumn("Mã sảnh");
        colMaSanh.setCellValueFactory(new PropertyValueFactory("MaSanh"));
        colMaSanh.setPrefWidth(100);
       
        TableColumn colTenSanh = new TableColumn("Tên sảnh");
        colTenSanh.setCellValueFactory(new PropertyValueFactory("TenSanh"));
        colTenSanh.setPrefWidth(250);
        
        TableColumn colTang = new TableColumn("Tầng");
        colTang.setCellValueFactory(new PropertyValueFactory("Tang"));
        colTang.setPrefWidth(100);
        
        TableColumn colSucChua = new TableColumn("Sức chứa");
        colSucChua.setCellValueFactory(new PropertyValueFactory("SucChua"));
        colSucChua.setPrefWidth(100);
        
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
        colDonGia.setPrefWidth(150);
        this.tvSanh.getColumns().addAll(colMaSanh, colTenSanh, colTang, colSucChua, colDonGia);
    }
    private void loadTvDichVuView(){
        TableColumn colMaSanh = new TableColumn("Mã dịch vụ");
        colMaSanh.setCellValueFactory(new PropertyValueFactory("MaDV"));
        colMaSanh.setPrefWidth(150);
       
        TableColumn colTenSanh = new TableColumn("Tên dịch vụ");
        colTenSanh.setCellValueFactory(new PropertyValueFactory("TenDV"));
        colTenSanh.setPrefWidth(350);
   
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
        colDonGia.setPrefWidth(180);
        this.tvDichVu.getColumns().addAll(colMaSanh, colTenSanh, colDonGia);
    }
    
    private void loadTvSanhData(String kw) throws SQLException{
        SanhServices s = new SanhServices();
        this.tvSanh.setItems(FXCollections.observableList(s.getListSanh(kw)));
    }
    private void loadTvThucAnData(String kw) throws SQLException{
        MonAnServices s = new MonAnServices();
        
        this.tvThucAn.setItems(FXCollections.observableList(s.getListMonAn(kw)));
    }
    private void loadTvDichVuData(String kw) throws SQLException{
        DichVuServices s = new DichVuServices();
        
        this.tvDichVu.setItems(FXCollections.observableList(s.getListDichVu(kw)));
    }
    private void MouseClickTvDichVu()
    {
        tvDichVu.setRowFactory((tv) -> {
            TableRow<DichVu> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DichVu rowData = row.getItem();
                    this.txtMaDV.clear();
                    this.txtMaDV.appendText(String.valueOf(rowData.getMaDV()));
                    this.txtDonGiaDV.clear();
                    this.txtDonGiaDV.appendText(String.valueOf(rowData.getDonGia()));
                }
            });
            return row;
        });
    }
    private void MouseClickTvThucAn()
    {
        tvThucAn.setRowFactory((tv) -> {
            TableRow<MonAn> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    MonAn rowData = row.getItem();
                    this.txtMaMA.clear();
                    this.txtMaMA.appendText(String.valueOf(rowData.getMaMA()));
                }
            });
            return row;
        });
    }
    private void MouseClickTvSanh()
    {
        tvSanh.setRowFactory((tv) -> {
            TableRow<Sanh> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    Sanh rowData = row.getItem();
                    this.txtMaSanh.clear();
                    this.txtMaSanh.appendText(String.valueOf(rowData.getMaSanh()));
                    this.txtTenSanh.clear();
                    this.txtTenSanh.appendText(String.valueOf(rowData.getTenSanh()));
                    this.txtTang.clear();
                    this.txtTang.appendText(String.valueOf(rowData.getTang()));
                    this.txtSucChua.clear();
                    this.txtSucChua.appendText(String.valueOf(rowData.getSucChua()));
                    this.txtDonGiaSanh.clear();
                    this.txtDonGiaSanh.appendText(String.valueOf(rowData.getDonGia()));
                }
            });
            return row;
        });
    }

    private void LoadTabDatTiec() throws SQLException{
        DatTiecServices s = new DatTiecServices();
        KhachHangServices k = new KhachHangServices();
        this.khachHang = k.getKhachHang(1);
        this.txtTenKH.appendText(khachHang.getTenKH());
        this.txtMaKH.appendText(Integer.toString(khachHang.getMaKH()));
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        this.txtNgayDat.appendText(d.format(date));
        this.maTiec = s.getMaxDatTiec();
        this.txtMaTiec.appendText(Integer.toString(maTiec));
        ObservableList a =  FXCollections.observableArrayList("Sáng", "Tối");     
        cbBuoi.setItems(a);
        this.loadTvSanhView();
        this.MouseClickTvSanh();
        try {
            this.loadTvSanhData(null);     
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTimKiemSanh.textProperty().addListener((evt) -> {
            try {
                this.loadTvSanhData(this.txtTimKiemSanh.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    private void LoadTabDatDichVu(){
        this.loadTvDichVuView();
        this.MouseClickTvDichVu();
        try {
            this.loadTvDichVuData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTimKiemDV.textProperty().addListener((evt) -> {
            try {
                this.loadTvDichVuData(this.txtTimKiemDV.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void LoadTabDatMonAn(){
        this.loadTvThucAnView();  
        this.MouseClickTvThucAn();
        try {
            this.loadTvThucAnData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }              
        this.txtTimKiemMA.textProperty().addListener((evt) -> {
            try {
                this.loadTvThucAnData(this.txtTimKiemMA.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void LoadTabThanhToan(){
        
    }
    public void addDatTiecHandler(ActionEvent event) throws SQLException, ParseException{
        try{
            DatTiec d = new DatTiec();
            d.setMaTiec(maTiec);
            d.setMaKH(khachHang.getMaKH());
            d.setTenTiec(this.txtTenTiec.getText());
            d.setSoLuongKhach(parseInt(this.txtSoLuongKhach.getText()));
            d.setSoLuongBan(parseInt(this.txtSoBan.getText()));
            d.setBuoi((String) cbBuoi.getValue());
            d.setMaSanh(parseInt(this.txtMaSanh.getText()));
            d.setNgayToChuc(java.sql.Date.valueOf(dpNgayDat.getValue()));
            DatTiecServices s = new DatTiecServices();
            try{
                s.addDatTiec(d);
                Utils.getBox("Thêm thành công!", Alert.AlertType.INFORMATION).show();
            }catch(SQLException ex){
                Utils.getBox("Tiệc đã được thêm!", Alert.AlertType.INFORMATION).show();
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }catch(NumberFormatException ex){
            Utils.getBox("Vui lòng đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
        }
    }

    /**
     * @return the khachHang
     */
    public KhachHang getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     */
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
