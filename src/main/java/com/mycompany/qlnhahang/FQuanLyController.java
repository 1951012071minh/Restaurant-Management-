/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.DichVu;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.MonAn;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.HoaDonServices;
import com.mycompany.services.KhachHangServices;
import com.mycompany.services.MonAnServices;
import com.mycompany.services.SanhServices;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
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
    
    
        //LenVo
     @FXML private TextField txt_MaSanh;
    @FXML private TextField txt_TenSanh;
    @FXML private TextField txt_Tang;
    @FXML private TextField txt_SucChua;
    @FXML private TextField txt_GiaTien;
    @FXML private TextField txt_TimKiemSanh;
//    @FXML private Button btn_CapNhatSanh;
//    @FXML private Button btn_XoaSanh;
    @FXML private TableView tbv_DanhSachSanh;
//    Món ăn
    @FXML private TextField txt_MaMA;
    @FXML private TextField txt_TenMA;
    @FXML private TextField txt_Loai;
    @FXML private TextField txt_DonViTinh;
    @FXML private TextField txt_DonGiaMonAn;
//    @FXML private Button btn_ThemMonAn;
//    @FXML private Button btn_CapNhatMonAn;
//    @FXML private Button btn_XoaMonAn;
    @FXML private TextField txt_TimKiemMonAn;
    @FXML private TableView tbv_DanhSachMonAn;
//    Dịch vụ
    @FXML private TextField txt_MaDV;
    @FXML private TextField txt_TenDV;
    @FXML private TextField txt_DonGiaDV;
//    @FXML private Button btn_ThemDichVu;
//    @FXML private Button btn_CapNhatDichVu;
//    @FXML private Button btn_XoaDichVu;
    @FXML private TextField txt_TimKiemDichVu;
    @FXML private TableView tbv_DanhSachDichVu;
   
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
        this.loadTabDichVu();
        this.loadTabMonAn();
        this.loadTabSanh();
        
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
        this.tvDatTiec.setItems(FXCollections.observableList(s.getListDatTiec(ma)));
    }
    private void loadTvHoaDonData(Date d1, Date d2) throws SQLException{
        HoaDonServices s = new HoaDonServices();
        this.tvHoaDon.setItems(FXCollections.observableList(s.getListDichVu(d1, d2)));
        this.txtDoanhThuHD.setText(String.valueOf(s.getDoanhThu(d1, d2)));
    }
    
    
    
    //LenVo
     //LenVo
    private void loadTvSanhView(){
         //Mã Sảnh
            TableColumn colMaSanh = new TableColumn("Mã sảnh");
            colMaSanh.setCellValueFactory(new PropertyValueFactory("MaSanh"));
            colMaSanh.setPrefWidth(100);   
        // Tên sảnh
            TableColumn colTenSanh = new TableColumn("Tên sảnh");
            colTenSanh.setCellValueFactory(new PropertyValueFactory("TenSanh"));
            colTenSanh.setPrefWidth(250); 
        //Tầng
            TableColumn colTang = new TableColumn("Tầng");
            colTang.setCellValueFactory(new PropertyValueFactory("Tang"));
            colTang.setPrefWidth(100); 
        //Sức chứa
            TableColumn colSucChua = new TableColumn("Sức chứa");
            colSucChua.setCellValueFactory(new PropertyValueFactory("SucChua"));
            colSucChua.setPrefWidth(105); 
        //Đơn giá
            TableColumn colDonGia = new TableColumn("Đơn giá");
            colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
            colDonGia.setPrefWidth(180);
            this.tbv_DanhSachSanh.getColumns().addAll(colMaSanh,colTenSanh,
                    colTang,colSucChua,colDonGia);
        }
      public void loadTvSanhData(String kw) throws SQLException{
         SanhServices s = new SanhServices();
         this.tbv_DanhSachSanh.setItems(FXCollections.observableList(s.getListSanh(kw)));
     }
      private void MouseClicktbvDanhSachSanh(){
              tbv_DanhSachSanh.setRowFactory((tbv) -> {
                  TableRow<Sanh> rowSanh = new TableRow<>();
                  rowSanh.setOnMouseClicked((event) -> {
                    if(event.getClickCount() != 0 && (!rowSanh.isEmpty())){
                      Sanh rowData = rowSanh.getItem();
                      this.txt_MaSanh.clear();
                      this.txt_MaSanh.appendText(String.valueOf(rowData.getMaSanh()));
                      this.txt_TenSanh.clear();
                      this.txt_TenSanh.appendText(String.valueOf(rowData.getTenSanh()));
                      this.txt_Tang.clear();
                      this.txt_Tang.appendText(String.valueOf(rowData.getTang()));
                      this.txt_SucChua.clear();
                      this.txt_SucChua.appendText(String.valueOf(rowData.getSucChua()));
                      this.txt_GiaTien.clear();
                      this.txt_GiaTien.appendText(String.valueOf(rowData.getDonGia()));
            };
        });
                  return rowSanh;
    });   
}
      private void loadTvThucAnView(){
//         Mã MA
            TableColumn colMaMA = new TableColumn("Mã món ăn");
            colMaMA.setCellValueFactory(new PropertyValueFactory("MaMA"));
            colMaMA.setPrefWidth(100);   
//         Tên món ăn
            TableColumn colTenMA = new TableColumn("Tên món ăn");
            colTenMA.setCellValueFactory(new PropertyValueFactory("TenMA"));
            colTenMA.setPrefWidth(250); 
//        Loại
            TableColumn colLoai = new TableColumn("Loại");
            colLoai.setCellValueFactory(new PropertyValueFactory("Loai"));
            colLoai.setPrefWidth(110); 
//        Đơn vị tính
            TableColumn colDonViTinh = new TableColumn("Đơn vị tính");
            colDonViTinh.setCellValueFactory(new PropertyValueFactory("DonViTinh"));
            colDonViTinh.setPrefWidth(110); 
//        Đơn giá
            TableColumn colDonGia = new TableColumn("Giá tiền");
            colDonGia.setCellValueFactory(new PropertyValueFactory("DonGia"));
            colDonGia.setPrefWidth(170);
            this.tbv_DanhSachMonAn.getColumns().addAll(colMaMA,colTenMA,
                    colLoai,colDonViTinh,colDonGia);
     }
     private void loadTvMonAnData(String kw) throws SQLException{
         MonAnServices ma = new MonAnServices();
         this.tbv_DanhSachMonAn.setItems(FXCollections.observableList(ma.getListMonAn(kw)));
     }
     private void MouseClicktbvDanhSachMonAn(){
              tbv_DanhSachMonAn.setRowFactory((tbv) -> {
                  TableRow<MonAn> rowMA = new TableRow<>();
                  rowMA.setOnMouseClicked((event) -> {
                    if(event.getClickCount() != 0 && (!rowMA.isEmpty())){
                      MonAn rowData = rowMA.getItem();
                      this.txt_MaMA.clear();
                      this.txt_MaMA.appendText(String.valueOf(rowData.getMaMA()));
                      this.txt_TenMA.clear();
                      this.txt_TenMA.appendText(String.valueOf(rowData.getTenMA()));
                      this.txt_Loai.clear();
                      this.txt_Loai.appendText(String.valueOf(rowData.getLoai()));
                      this.txt_DonViTinh.clear();
                      this.txt_DonViTinh.appendText(String.valueOf(rowData.getDonViTinh()));
                      this.txt_DonGiaMonAn.clear();
                      this.txt_DonGiaMonAn.appendText(String.valueOf(rowData.getDonGia()));
            };
        });
                  return rowMA;
    });   
}
     
     private void loadTvDichVuView(){
          //Mã dv
            TableColumn colMaDV = new TableColumn("Mã dịch vụ");
            colMaDV.setCellValueFactory(new PropertyValueFactory("MaDV"));
            colMaDV.setPrefWidth(100);   
//Tên dịch vụ
            TableColumn colTenDV = new TableColumn("Tên dịch vụ");
            colTenDV.setCellValueFactory(new PropertyValueFactory("tenDV"));
            colTenDV.setPrefWidth(300); 
//        Đơn giá
            TableColumn colDonGiaDV = new TableColumn("Đơn giá");
            colDonGiaDV.setCellValueFactory(new PropertyValueFactory("donGia"));
            colDonGiaDV.setPrefWidth(250);
            this.tbv_DanhSachDichVu.getColumns().addAll(colMaDV,colTenDV,colDonGiaDV);
     }
     private void loadTVDichVuData(String kw) throws SQLException{
         DichVuServices dv = new DichVuServices();
         this.tbv_DanhSachDichVu.setItems(FXCollections.observableList(dv.getListDichVu(kw)));
     }
     private void mouseClicktbvDanhSachDichVu(){
         tbv_DanhSachDichVu.setRowFactory((tbv) -> {
                  TableRow<DichVu> rowDV = new TableRow<>();
                  rowDV.setOnMouseClicked((event) -> {
                    if(event.getClickCount() != 0 && (!rowDV.isEmpty())){
                      DichVu rowData = rowDV.getItem();
                      this.txt_MaDV.clear();
                      this.txt_MaDV.appendText(String.valueOf(rowData.getMaDV()));
                      this.txt_TenDV.clear();
                      this.txt_TenDV.appendText(String.valueOf(rowData.getTenDV()));
                      this.txt_DonGiaDV.clear();
                      this.txt_DonGiaDV.appendText(String.valueOf(rowData.getDonGia()));
            };
        });
                  
                  return rowDV;
    });   
     }
     
     private void loadTabSanh(){
         this.loadTvSanhView();
         this.MouseClicktbvDanhSachSanh();
          try{
           this.loadTvSanhData(null);
       } catch(SQLException ex){
           Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null,ex);
       }
       this.txt_TimKiemSanh.textProperty().addListener((evt) -> {
            try {
                this.loadTvSanhData(this.txt_TimKiemSanh.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
     private void loadTabMonAn(){
         this.loadTvThucAnView();
         this.MouseClicktbvDanhSachMonAn();
         try{
           this.loadTvMonAnData(null);
       } catch(SQLException ex){
           Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null,ex);
       }
       this.txt_TimKiemMonAn.textProperty().addListener((evt) -> {
            try {
                this.loadTvMonAnData(this.txt_TimKiemMonAn.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
     private void loadTabDichVu(){
         this.loadTvDichVuView();
         this.mouseClicktbvDanhSachDichVu();
          try{
           this.loadTVDichVuData(null);
       } catch(SQLException ex){
           Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null,ex);
       }
       this.txt_TimKiemDichVu.textProperty().addListener((evt) -> {
            try {
                this.loadTVDichVuData(this.txt_TimKiemDichVu.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
     //Thêm Sảnh
     
    
     public void clickThemSanh(ActionEvent event) throws IOException, SQLException{
//       if(findSanh() == null)
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FThemSanh.fxml"));
        Scene scene= new Scene(fxmlLoader.load());
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm sảnh");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.loadTvSanhData(null);
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
     public void updateSanhHandler (ActionEvent event) throws  SQLException, ParseException{
        
        try {
            Sanh s = new Sanh();
            SanhServices ss = new SanhServices();
            s.setMaSanh(parseInt(txt_MaSanh.getText()));
            s.setTenSanh(this.txt_TenSanh.getText());
            s.setTang(parseInt(this.txt_Tang.getText()));
            s.setSucChua(parseInt(this.txt_SucChua.getText()));
            s.setDonGia(BigDecimal.valueOf((parseInt(this.txt_GiaTien.getText()))));
            try{
                ss.updateSanhVaoDB(s);
                this.txt_MaSanh.clear();
                this.txt_TenSanh.clear();
                this.txt_Tang.clear();
                this.txt_SucChua.clear();
                this.txt_GiaTien.clear();
                Utils.getBox("Cập nhật sảnh thành công", Alert.AlertType.INFORMATION).show();
            } catch(SQLException ex){
                Utils.getBox("Vui lòng chọn 1 sảnh để cập nhật", Alert.AlertType.WARNING).show();
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException numberFormatException) {
            Utils.getBox("Sai kiểu dữ liệu", Alert.AlertType.WARNING).show();
        }
        this.loadTvSanhData(null);
    }
     public void deleteSanhHandler(ActionEvent event) throws SQLException, ParseException{
         try{
             Sanh s = new Sanh();
             SanhServices ss = new SanhServices();
             s.setTenSanh(this.txt_TenSanh.getText());
             try{
                 ss.xoaSanh(s);
                 this.txt_MaSanh.clear();
                 this.txt_TenSanh.clear();
                 this.txt_Tang.clear();
                 this.txt_SucChua.clear();
                 this.txt_GiaTien.clear();
                 this.loadTvSanhData(null);
                 Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
             }catch (SQLException ex){
                 Utils.getBox("Sảnh đã được xóa!", Alert.AlertType.INFORMATION).show();
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }catch(NumberFormatException e){
                Utils.getBox("Xin nhập đúng kiểu dữ liệu!", Alert.AlertType.WARNING).show();
        }
    }

     public void clickThemMonAn(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FThemThucAn.fxml"));
        Scene scene= new Scene(fxmlLoader.load());
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm thức ăn");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.loadTvMonAnData(null);
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
 }
     public void updateMonAnHandler (ActionEvent event) throws  SQLException, ParseException{
        
        try {
            MonAn ma = new MonAn();
            MonAnServices mas = new MonAnServices();
            ma.setMaMA(parseInt(txt_MaMA.getText()));
            ma.setTenMA(this.txt_TenMA.getText());
            ma.setDonGia(BigDecimal.valueOf((parseInt(this.txt_DonGiaMonAn.getText()))));
            ma.setLoai(this.txt_Loai.getText());
            ma.setDonViTinh(this.txt_DonViTinh.getText());
            try{
                mas.updateMonAnVaoDB(ma);
                this.txt_MaMA.clear();
                this.txt_TenMA.clear();
                this.txt_DonGiaMonAn.clear();
                this.txt_Loai.clear();
                this.txt_DonViTinh.clear();
                Utils.getBox("Cập nhật món ăn thành công", Alert.AlertType.INFORMATION).show();
            } catch(SQLException ex){
                Utils.getBox("Vui lòng chọn 1 món ăn để cập nhật", Alert.AlertType.WARNING).show();
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException numberFormatException) {
            Utils.getBox("Sai kiểu dữ liệu", Alert.AlertType.WARNING).show();
        }
        this.loadTvMonAnData(null);
    }
     public void deleteMonAnHandler(ActionEvent event) throws SQLException, ParseException{
         try{
             MonAn ma = new MonAn();
             MonAnServices mas = new MonAnServices();
             ma.setTenMA(this.txt_TenMA.getText());
             try{
                 mas.xoaMonAn(ma);
                 this.txt_MaMA.clear();
                 this.txt_TenMA.clear();
                 this.txt_DonGiaMonAn.clear();
                 this.txt_Loai.clear();
                 this.txt_DonViTinh.clear();
                 this.loadTvMonAnData(null);
                 Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
             }catch (SQLException ex){
                 Utils.getBox("Món ăn đã được xóa!", Alert.AlertType.INFORMATION).show();
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }catch(NumberFormatException e){
                Utils.getBox("Xin nhập đúng kiểu dữ liệu!", Alert.AlertType.WARNING).show();
        }
    }
     
     public void clickThemDichVu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FThemDichVu.fxml"));
        Scene scene= new Scene(fxmlLoader.load());
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm dịch vụ");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.loadTVDichVuData(null);
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
     public void updateDichVuHandler (ActionEvent event) throws  SQLException, ParseException{
        try {
            DichVu dv = new DichVu();
            DichVuServices dvs = new DichVuServices();
            dv.setMaDV(parseInt(txt_MaDV.getText()));
            dv.setTenDV(this.txt_TenDV.getText());
            dv.setDonGia(BigDecimal.valueOf((parseInt(this.txt_DonGiaDV.getText()))));
            try{
                dvs.updateDichVuVaoDB(dv);
                this.txt_MaDV.clear();
                this.txt_TenDV.clear();
                this.txt_DonGiaDV.clear();
                Utils.getBox("Cập nhật dịch vụ thành công", Alert.AlertType.INFORMATION).show();
            } catch(SQLException ex){
                Utils.getBox("Vui lòng chọn 1 dịch vụ để cập nhật", Alert.AlertType.WARNING).show();
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException numberFormatException) {
            Utils.getBox("Sai kiểu dữ liệu", Alert.AlertType.WARNING).show();
        }
        this.loadTVDichVuData(null);
    }
     public void deleteDichVuHandler(ActionEvent event) throws SQLException, ParseException{
         try{
             DichVu dv = new DichVu();
             DichVuServices mas = new DichVuServices();
             dv.setTenDV(this.txt_TenDV.getText());
             try{
                 mas.xoaDichVu(dv);
                 this.txt_MaDV.clear();
                 this.txt_TenDV.clear();
                 this.txt_DonGiaDV.clear();
                 this.loadTVDichVuData(null);
                 Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
             }catch (SQLException ex){
                 Utils.getBox("Dịch vụ đã được xóa!", Alert.AlertType.INFORMATION).show();
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }catch(NumberFormatException e){
                Utils.getBox("Xin nhập đúng kiểu dữ liệu!", Alert.AlertType.WARNING).show();
        }
    }
}
