/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatDichVu;
import com.mycompany.pojo.DatMonAn;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.DichVu;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.MonAn;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.DatDichVuServices;
import com.mycompany.services.DatMonAnServices;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.HoaDonServices;
import com.mycompany.services.KhachHangServices;
import com.mycompany.services.MonAnServices;
import java.io.IOException;
import com.mycompany.services.SanhServices;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
//import //.Context;
/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FDatTiecController implements Initializable {
    //tabDatTiec
    @FXML private TextField txtTenTiec;
    @FXML private TableView<Sanh> tvSanh;
    @FXML private TextField txtTimKiemSanh; 
    @FXML private TextField txtMaTiec;
    @FXML private TextField txtDonGiaSanh;
    @FXML private TextField txtSoLuongKhach;
    @FXML private TextField txtMaKH;
    @FXML private TextField txtSoBan;
    @FXML private TextField txtNgayDat;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtMaSanh;
    @FXML private TextField txtTenSanh;
    @FXML private TextField txtSucChua;
    @FXML private TextField txtTang;
    @FXML private ComboBox cbBuoi;
    @FXML private DatePicker dpNgayDat;
    @FXML private RadioButton rdSanh;

    //tabDatMon
    @FXML private TableView<MonAn> tvThucAn;
    @FXML private TableView tvDatMonAn;
    @FXML private TextField txtTimKiemMA;
    @FXML private TextField txtThanhTienMA;
    @FXML private TextField txtTongSoMA;
    @FXML private TextField txtMaMA;
    @FXML private TextField txtSoLuong;
    
    //tabDichVu
    @FXML private TextField txtMaDV;
    @FXML private TableView<DichVu> tvDichVu;
    @FXML private TextField txtTimKiemDV;  
    @FXML private TextField txtTongSoDV;
    @FXML private TextField txtThanhTienDV;
    @FXML private TextField txtDonGiaDV;
    @FXML private TableView tvDichVuDat;
    
    //tabThanhToan
    @FXML private TextField txt_TongTien;
    @FXML private TableView tvDatMon;
    @FXML private TableView tvDatDV;
    @FXML private TextField txtMaKHTT;
    @FXML private TextField txtTenKHTT;
    @FXML private TextField txtSDT;
    @FXML private TextField txtCMND;
    @FXML private TextField txtMaSanhTT;
    @FXML private TextField txtSoLuongKhachTT;
    @FXML private TextField txtSLBTT;
    @FXML private TextField txtNgayDatTT;
    @FXML private TextField txtNgayToChuc;
    @FXML private TextField txtBuoi;
    
    @FXML private TabPane tab;
    @FXML private Tab tab4;
    public boolean flag = false;
    private int maTiec;
    private KhachHang khachHang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            this.LoadTabDatDichVu();
            this.LoadTabDatMonAn();
            this.LoadTabThanhToan();
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    //tabDatTiec
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
    
    private void loadTvSanhData() throws SQLException{
        SanhServices a1 = new SanhServices();
        if(!rdSanh.isSelected()){
                try {
                    this.tvSanh.setItems(FXCollections.observableList(a1.getListSanh(this.txtTimKiemSanh.getText().trim())));
                } catch (SQLException ex) {
                    Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                try { 
                    this.tvSanh.setItems(FXCollections.observableList(a1.getListSanhByDate(this.txtTimKiemSanh.getText().trim(), java.sql.Date.valueOf(dpNgayDat.getValue()), (String) cbBuoi.getValue()))); 
                } catch (SQLException ex) {
                    Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void MouseClickTvSanh(){
        tvSanh.setRowFactory((tv) -> {
            TableRow<Sanh> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    Sanh rowData = row.getItem();
                    this.txtMaSanh.setText(String.valueOf(rowData.getMaSanh()));
                    this.txtTenSanh.setText(String.valueOf(rowData.getTenSanh()));
                    this.txtTang.setText(String.valueOf(rowData.getTang()));
                    this.txtSucChua.setText(String.valueOf(rowData.getSucChua()));
                    this.txtDonGiaSanh.setText(String.valueOf(rowData.getDonGia()));
                }
            });
            return row;
        });
    }
    
    public void LoadTabDatTiec(KhachHang k) throws SQLException{
        DatTiecServices s = new DatTiecServices();
        this.khachHang = k;
        this.txtTenKH.setText(khachHang.getTenKH());
        this.txtMaKH.setText(Integer.toString(khachHang.getMaKH()));
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now().plusDays(7))){
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;");
                }
            }
        };
        this.txtNgayDat.setText(d.format(date));
        this.dpNgayDat.setDayCellFactory(dayCellFactory);
        this.dpNgayDat.setValue(LocalDate.now().plusDays(7));
        this.maTiec = s.getMaxDatTiec();
        this.txtMaTiec.setText(Integer.toString(maTiec));
        ObservableList a =  FXCollections.observableArrayList("Sáng", "Tối");     
        cbBuoi.setItems(a);
        cbBuoi.setValue(a.get(0));
        this.loadTvSanhView();
        this.MouseClickTvSanh();
        try {
            this.loadTvSanhData();
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTimKiemSanh.textProperty().addListener((evt) -> {
            try {
                this.loadTvSanhData();
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.rdSanh.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            try {
                this.loadTvSanhData();
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.dpNgayDat.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                this.loadTvSanhData();
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.cbBuoi.valueProperty().addListener((cl)->{
            try {
                this.loadTvSanhData();
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void btnQuayLai(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Bạn chưa thanh toán!");   
            alert.setContentText("Vui lòng thanh toán nếu không tiệc sẽ bị hủy!\nBạn có muốn tiếp tục?");
            Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    this.huyTiec();
                }
                else{
                    return;
                }
            }
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FGiaoDienKH.fxml"));
            try {
                Parent d = loader.load();
                Scene scene = new Scene(d);
                FGiaoDienKHController controller = loader.getController();
                controller.setK(this.khachHang);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    public void btnThanhToan(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            HoaDonServices h = new HoaDonServices();
            h.thanhToanHoaDon(maTiec);
            Utils.getBox("Cám ơn quý khách đã đặt tiệc!", Alert.AlertType.INFORMATION).show();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FGiaoDienKH.fxml"));
            try {
                Parent d = loader.load();
                Scene scene = new Scene(d);
                FGiaoDienKHController controller = loader.getController();
                controller.setK(this.khachHang);
                stage.setScene(scene);
                stage.setOnCloseRequest(eh -> {
                });
            } catch (IOException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Utils.getBox("Bạn vui lòng điền thông tin đặt tiệc!", Alert.AlertType.INFORMATION).show();
        }
    }
    public void addDatTiecHandler(ActionEvent event) throws SQLException, ParseException{
        try{
            DatTiec d = new DatTiec();
            d.setMaTiec(maTiec);
            d.setMaKH(khachHang.getMaKH());
            d.setTenTiec(this.txtTenTiec.getText());
            if(txtMaSanh.getText().trim().equals(""))
                throw new Exception("Vui lòng chọn 1 sảnh");
            if(this.txtSoLuongKhach.getText().trim().equals("") 
                    || this.txtSoBan.getText().trim().equals("") 
                    ||this.txtTenTiec.getText().trim().equals(""))
                throw new Exception("Vui lòng điền đầy đủ thông tin");
            if(parseInt(txtSoLuongKhach.getText()) <= 0 || parseInt(txtSoBan.getText()) <= 0 )
                    throw new NumberFormatException();
            d.setSoLuongKhach(parseInt(this.txtSoLuongKhach.getText()));
            d.setSoLuongBan(parseInt(this.txtSoBan.getText()));
            d.setBuoi((String) cbBuoi.getValue());
            d.setMaSanh(parseInt(this.txtMaSanh.getText()));
            d.setNgayToChuc(java.sql.Date.valueOf(dpNgayDat.getValue()));
            DatTiecServices s = new DatTiecServices();
            if(s.checkDatTiec(d.getMaSanh(), (Date) d.getNgayToChuc(), d.getBuoi()) == 0){
                if(parseInt(this.txtSoLuongKhach.getText()) > parseInt(this.txtSucChua.getText())){
                    throw new Exception("Số lượng khách không được vượt quá sức chứa!");
                }if(parseInt(this.txtSoBan.getText()) > (parseInt(this.txtSucChua.getText()) / 10)){
                    throw new Exception("Số lượng bàn không được vượt quá (sức chứa / 10)!");
                }
                HoaDon h = new HoaDon();
                HoaDonServices hs = new HoaDonServices();
                h.setMaHD(hs.getMaxHoaDon());
                h.setMaTiec(maTiec);
                s.addDatTiec(d);
                hs.addHoaDon(h);
                flag = true;
                Utils.getBox("Thêm thành công!", Alert.AlertType.INFORMATION).show();
            }
            else if(s.checkDatTiec(d.getMaSanh(), (Date) d.getNgayToChuc(), d.getBuoi()) == maTiec){
                s.updateDatTiec(d);
                Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
            }
            else{
                Utils.getBox("Vào khung giờ này sảnh đã có người đặt!", Alert.AlertType.INFORMATION).show();
            }
        }catch(NumberFormatException ex){
            Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
        }catch(Exception ex){
            Utils.getBox(ex.getMessage(), Alert.AlertType.INFORMATION).show();
        }
    }
    
    
    
    //tabDatMon
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
    
    private void loadTvDatMonAnView(){
        TableColumn colMaMA = new TableColumn("Mã món ăn");
        colMaMA.setCellValueFactory(new PropertyValueFactory("MaMA"));
        colMaMA.setPrefWidth(100);
       
        TableColumn colTenMA = new TableColumn("Số lượng");
        colTenMA.setCellValueFactory(new PropertyValueFactory("SoLuong"));
        colTenMA.setPrefWidth(100);
   
        TableColumn colThanhTien = new TableColumn("Thành tiền");
        colThanhTien.setCellValueFactory(new PropertyValueFactory("ThanhTien"));
        colThanhTien.setPrefWidth(160);
        this.tvDatMonAn.getColumns().addAll(colMaMA, colTenMA, colThanhTien);
    }
    
    private void loadTvDatMonAnData(int ma) throws SQLException{
        DatMonAnServices s = new DatMonAnServices();
        this.tvDatMonAn.setItems(FXCollections.observableList(s.getListDatMonAn(ma)));
    }
    
    private void MouseClickTvThucAn(){
        tvThucAn.setRowFactory((tv) -> {
            TableRow<MonAn> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    MonAn rowData = row.getItem();
                    this.txtMaMA.setText(String.valueOf(rowData.getMaMA()));
                }
            });
            return row;
        });
    }
    
    private void MouseClickTvDatMonAn(){
        tvDatMonAn.setRowFactory((tv) -> {
            TableRow<DatMonAn> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DatMonAn rowData = row.getItem();;
                    this.txtMaMA.setText(String.valueOf(rowData.getMaMA()));
                    this.txtSoLuong.setText(String.valueOf(rowData.getSoLuong()));
                }
            });
            return row;
        });
    }
    
    private void loadTvThucAnData(String kw) throws SQLException{
        MonAnServices s = new MonAnServices();
        
        this.tvThucAn.setItems(FXCollections.observableList(s.getListMonAn(kw)));
    }
    
    private void LoadTabDatMonAn(){
        this.loadTvThucAnView();  
        this.MouseClickTvThucAn();
        MouseClickTvDatMonAn();
        this.loadTvDatMonAnView();
        try {
            this.loadTvThucAnData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }              
        this.txtTimKiemMA.textProperty().addListener((evt) -> {
            try {
                this.loadTvThucAnData(this.txtTimKiemMA.getText().trim());
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void addMonAnHandler(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            try{
                if(!"".equals(txtMaMA.getText().trim())){
                    if("".equals(txtSoLuong.getText().trim()))
                        throw new Exception("Vui lòng nhập số lượng món ăn");
                    if(parseInt(txtSoLuong.getText()) > 100)
                        throw new Exception("Số lượng món ăn không được vượt quá 100");
                    DatMonAn d = new DatMonAn();
                    d.setMaMA(parseInt(txtMaMA.getText()));
                    d.setMaTiec(maTiec);
                    if(parseInt(txtSoLuong.getText()) <= 0){
                        throw new NumberFormatException();
                    }
                    d.setSoLuong(parseInt(txtSoLuong.getText()));
                    DatMonAnServices s = new DatMonAnServices();
                    if(s.getDatMonAn(maTiec, d.getMaMA()) == null){
                        s.addDatMonAn(d);
                        this.txtTongSoMA.setText(Integer.toString(s.getTongMonAn(maTiec)));
                        this.txtMaMA.clear();
                        this.txtSoLuong.clear();
                        this.txtThanhTienMA.setText(s.getThanhTienMonAn(maTiec).toString());
                        this.loadTvDatMonAnData(maTiec);
                        Utils.getBox("Thêm thành công!", Alert.AlertType.INFORMATION).show();
                    }
                    else
                      Utils.getBox("Món ăn đã có trong thực đơn của bạn!", Alert.AlertType.INFORMATION).show();
                }else
                    throw new Exception("Vui lòng chọn 1 món ăn");    
            }catch(NumberFormatException ex){
                Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
            }
            catch(Exception ex1){
                Utils.getBox(ex1.getMessage(), Alert.AlertType.INFORMATION).show();
            }
        }
        else{
            Utils.getBox("Vui lòng điền thông tin đặt tiệc trước!", Alert.AlertType.INFORMATION).show();
        }
    }
    
    public void xoaMonAnHandler(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            try{
                DatMonAnServices s = new DatMonAnServices();
                DatMonAn d = new DatMonAn();
                d.setMaMA(parseInt(txtMaMA.getText()));
                d.setMaTiec(maTiec);
                if(s.getDatMonAn(maTiec, d.getMaMA()) != null){
                            s.xoaDatMonAn(d);
                            this.txtTongSoMA.clear();
                            this.txtTongSoMA.setText(Integer.toString(s.getTongMonAn(maTiec)));
                            this.txtMaMA.clear();
                            this.txtSoLuong.clear();
                            this.txtThanhTienMA.setText(s.getThanhTienMonAn(maTiec).toString());
                            this.loadTvDatMonAnData(maTiec);
                            Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
                }
                else{
                    Utils.getBox("Món ăn này không nằm trong thực đơn của bạn!", Alert.AlertType.INFORMATION).show();
                }
            }catch(NumberFormatException ex){
                Utils.getBox("Vui lòng chọn 1 món để xóa!", Alert.AlertType.INFORMATION).show();
            }
        }
        else{
                Utils.getBox("Vui lòng điền thông tin đặt tiệc trước!", Alert.AlertType.INFORMATION).show();
            }
    }
    
    public void updateMonAnHandler(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            try{
                DatMonAn d = new DatMonAn();
                if("".equals(txtMaMA.getText().trim()))
                    throw new Exception("Vui lòng chọn 1 món để cập nhật");
                if(parseInt(txtSoLuong.getText()) <= 0){
                        throw new NumberFormatException();
                }
                if(parseInt(txtSoLuong.getText()) > 100)
                        throw new Exception("Số lượng món ăn không được vượt quá 100");
                d.setMaMA(parseInt(txtMaMA.getText()));
                d.setMaTiec(maTiec);
                d.setSoLuong(parseInt(txtSoLuong.getText()));
                DatMonAnServices s = new DatMonAnServices();
                if(s.getDatMonAn(maTiec, d.getMaMA()) != null){
                    if("".equals(txtSoLuong.getText().trim()))
                        throw new Exception("Vui lòng nhập số lượng món ăn");
                    s.updateDatMonAn(d);
                    this.txtMaMA.clear();
                    this.txtSoLuong.clear();
                    this.txtThanhTienMA.setText(s.getThanhTienMonAn(maTiec).toString());
                    this.loadTvDatMonAnData(maTiec);
                    Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
                    }
                else{
                    Utils.getBox("Món ăn này không nằm trong thực đơn của bạn!", Alert.AlertType.INFORMATION).show();
                }
            }catch(NumberFormatException ex){
                Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
            }
            catch(Exception ex){
                Utils.getBox(ex.getMessage(), Alert.AlertType.INFORMATION).show();
            }
        }
        else{
                Utils.getBox("Vui lòng điền thông tin đặt tiệc trước!", Alert.AlertType.INFORMATION).show();
            }
    }
    
    public void huyTiec() throws SQLException, ParseException{
        if(flag == true){
            DatTiecServices d = new DatTiecServices();
            d.delDatTiec(this.maTiec);
        }
    }
    
    //tabDatDV
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
    
    private void loadTvDichVuDatView(){
        TableColumn colMaDV = new TableColumn("Mã dịch vụ");
        colMaDV.setCellValueFactory(new PropertyValueFactory("MaDV"));
        colMaDV.setPrefWidth(70);
       
        TableColumn colTenDV = new TableColumn("Tên dịch vụ");
        colTenDV.setCellValueFactory(new PropertyValueFactory("TenDV"));
        colTenDV.setPrefWidth(170);
   
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
        colDonGia.setPrefWidth(90);
        this.tvDichVuDat.getColumns().addAll(colMaDV, colTenDV, colDonGia);
    }
    
    private void loadTvDichVuData(String kw) throws SQLException{
        DichVuServices s = new DichVuServices();
        
        this.tvDichVu.setItems(FXCollections.observableList(s.getListDichVu(kw)));
    }
    
    private void loadTvDichVuDatData(int ma) throws SQLException{
        DatDichVuServices s = new DatDichVuServices();
        this.tvDichVuDat.setItems(FXCollections.observableList(s.getListDichVuDat(ma)));
    }
    
    private void MouseClickTvDichVu(){
        tvDichVu.setRowFactory((tv) -> {
            TableRow<DichVu> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DichVu rowData = row.getItem();
                    this.txtMaDV.setText(String.valueOf(rowData.getMaDV()));
                    this.txtDonGiaDV.setText(String.valueOf(rowData.getDonGia()));
                }
            });
            return row;
        });
    }
    
    private void MouseClickTvDichVuDat(){
        tvDichVuDat.setRowFactory((tv) -> {
            TableRow<DichVu> row = new TableRow<>();
            row.setOnMouseClicked((event) -> {
                if(event.getClickCount() != 0 && (!row.isEmpty())){
                    DichVu rowData = row.getItem();
                    this.txtMaDV.setText(String.valueOf(rowData.getMaDV()));
                    this.txtDonGiaDV.setText(String.valueOf(rowData.getDonGia()));
                }
            });
            return row;
        });
    }
    
    private void LoadTabDatDichVu(){
        this.loadTvDichVuView();
        this.MouseClickTvDichVu();
        MouseClickTvDichVuDat();
        this.loadTvDichVuDatView();
        try {
            this.loadTvDichVuData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTimKiemDV.textProperty().addListener((evt) -> {
            try {
                this.loadTvDichVuData(this.txtTimKiemDV.getText().trim());
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void addDichVuHandler(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            try{
                DatDichVu d = new DatDichVu();
                d.setMaDV(parseInt(txtMaDV.getText()));
                d.setMaTiec(maTiec); 
                DatDichVuServices s = new DatDichVuServices();
                if(s.getDatDV(maTiec, d.getMaDV()) == null){
                        s.addDatDichVu(d);
                        this.txtTongSoDV.setText(Integer.toString(s.getTongDichVu(maTiec)));
                        this.txtMaDV.clear();
                        this.txtDonGiaDV.clear();
                        this.txtThanhTienDV.setText(s.getThanhTienDichVu(maTiec).toString());
                        this.loadTvDichVuDatData(maTiec);
                        Utils.getBox("Thêm thành công!", Alert.AlertType.INFORMATION).show();
                }else{
                     Utils.getBox("Dịch vụ đã có trong danh sách dịch vụ của bạn!", Alert.AlertType.INFORMATION).show();
                }
            }catch(NumberFormatException ex){
            Utils.getBox("Vui lòng chọn 1 dịch vụ để thêm!", Alert.AlertType.INFORMATION).show();
            }
        }
        else{
             Utils.getBox("Vui lòng điền thông tin đặt tiệc trước!", Alert.AlertType.INFORMATION).show();
        }
    }
    
    public void xoaDichVuHandler(ActionEvent event) throws SQLException, ParseException{
        if(flag == true){
            try{
            DatDichVu d = new DatDichVu();
            d.setMaDV(parseInt(txtMaDV.getText()));
            d.setMaTiec(maTiec);
            DatDichVuServices s = new DatDichVuServices();
            if(s.getDatDV(maTiec, d.getMaDV()) != null){
                    s.xoaDatDichVu(d);
                    this.txtTongSoDV.setText(Integer.toString(s.getTongDichVu(maTiec)));
                    this.txtMaDV.clear();
                    this.txtDonGiaDV.clear();
                    this.txtThanhTienDV.setText(s.getThanhTienDichVu(maTiec).toString());
                    this.loadTvDichVuDatData(maTiec);
                    Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
                }else{
                    Utils.getBox("Dịch vụ này không nằm trong danh sách dịch vụ của bạn!", Alert.AlertType.INFORMATION).show();
                }  
            }catch(NumberFormatException ex){
                Utils.getBox("Vui lòng chọn 1 dịch vụ để xóa", Alert.AlertType.INFORMATION).show();
            }
        }
        else{
                Utils.getBox("Vui lòng điền thông tin đặt tiệc trước!", Alert.AlertType.INFORMATION).show();
            }
    }
    
    
    
    //tabThanhToan
    private void LoadTabThanhToan() throws SQLException{
        this.loadTvDatMonAnTTView();
        this.loadTvDichVuDatTTView();
        this.tab.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if("tab4".equals(this.tab.getSelectionModel().selectedItemProperty().get().getId()))
            try {
                this.txtMaKHTT.setText(Integer.toString(khachHang.getMaKH()));
                this.txtTenKHTT.setText(khachHang.getTenKH());
                this.txtCMND.setText(khachHang.getCMND());
                this.txtSDT.setText(khachHang.getSDT());
                if(flag == true){
                    DatTiecServices ds = new DatTiecServices();
                    DatTiec d = ds.FindDatTiec(maTiec);
                    HoaDonServices hs = new HoaDonServices();
                    HoaDon h = hs.getHoaDon(maTiec);
                    this.txtMaSanhTT.setText(Integer.toString(d.getMaSanh()));
                    this.txtSoLuongKhachTT.setText(Integer.toString(d.getSoLuongKhach()));
                    this.txtSLBTT.setText(Integer.toString(d.getSoLuongBan()));
                    this.txtBuoi.setText(d.getBuoi());
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    this.txtNgayDatTT.setText(date.format(d.getNgayDat()));
                    this.txtNgayToChuc.setText(date.format(d.getNgayToChuc()));
                    this.txt_TongTien.setText(h.getThanhTien().toString());
                    this.loadTvDatMonAnTTData(maTiec);
                    this.loadTvDichVuDatDataTT(maTiec);     
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FDatTiecController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    });
    }
    private void loadTvDatMonAnTTView(){
        TableColumn colMaMA = new TableColumn("Mã món ăn");
        colMaMA.setCellValueFactory(new PropertyValueFactory("MaMA"));
        colMaMA.setPrefWidth(100);
       
        TableColumn colTenMA = new TableColumn("Số lượng");
        colTenMA.setCellValueFactory(new PropertyValueFactory("SoLuong"));
        colTenMA.setPrefWidth(100);
   
        TableColumn colThanhTien = new TableColumn("Thành tiền");
        colThanhTien.setCellValueFactory(new PropertyValueFactory("ThanhTien"));
        colThanhTien.setPrefWidth(160);
        this.tvDatMon.getColumns().addAll(colMaMA, colTenMA, colThanhTien);
    }
    
    private void loadTvDatMonAnTTData(int ma) throws SQLException{
        DatMonAnServices s = new DatMonAnServices();
        this.tvDatMon.setItems(FXCollections.observableList(s.getListDatMonAn(ma)));
    }
    
    private void loadTvDichVuDatTTView(){
        TableColumn colMaDV = new TableColumn("Mã dịch vụ");
        colMaDV.setCellValueFactory(new PropertyValueFactory("MaDV"));
        colMaDV.setPrefWidth(70);
       
        TableColumn colTenDV = new TableColumn("Tên dịch vụ");
        colTenDV.setCellValueFactory(new PropertyValueFactory("TenDV"));
        colTenDV.setPrefWidth(170);
   
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
        colDonGia.setPrefWidth(90);
        this.tvDatDV.getColumns().addAll(colMaDV, colTenDV, colDonGia);
    }
    
    private void loadTvDichVuDatDataTT(int ma) throws SQLException{
        DatDichVuServices s = new DatDichVuServices();
        this.tvDatDV.setItems(FXCollections.observableList(s.getListDichVuDat(ma)));
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

    /**
     * @return the maKH
     */
}
