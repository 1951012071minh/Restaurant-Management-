/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.BoPhan;
import com.mycompany.pojo.DatTiec;
import com.mycompany.pojo.DichVu;
import com.mycompany.pojo.HoaDon;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.MonAn;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.Sanh;
import com.mycompany.services.AccountServices;
import com.mycompany.services.BoPhanServices;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.HoaDonServices;
import com.mycompany.services.KhachHangServices;
import com.mycompany.services.MonAnServices;
import com.mycompany.services.NhanVienServices;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FQuanLyController implements Initializable {
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
    
    //BoPhan
    @FXML private TableView<BoPhan> tvBoPhan;
    @FXML private TextField txtMaBoPhan;
    @FXML private TextField txtTenBoPhan;
    @FXML private TextField txtSoLuongBP;
    @FXML private TextField txtTimKiemBP;
    @FXML private TableView tvNVBP;
    //NhanVien
    @FXML private TableView<NhanVien> tvNhanVien;
    @FXML private TextField txtMaNhanVien;
    @FXML private TextField txtTenNhanVien;
    @FXML private TextField txtCMND;
    @FXML private TextField txtSDT;
    @FXML private TextField txtChucVu;
    @FXML private TextField txtLuong;
    @FXML private TextField txtMaAccount;
    @FXML private TextField txtMaBP;
    @FXML private TextField txtTmKiemNV;
    @FXML private ComboBox cbTenBP_BP;
    
    
    //Sang
    @FXML private TableView<KhachHang> tvKhachHang;
    @FXML private TextField tfMaKH_KH;
    @FXML private TextField tfTenKH_KH;
    @FXML private TextField tfCMND;
    @FXML private TextField tfGT;
    @FXML private TextField tfSDT;
    @FXML private TextField tfDiaChi;
    @FXML private TextField tfTimKiemKH;
   
    
    /**
     * Initializes the controller class.
     */    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //TODO
            this.loadTabDDT();
            this.loadTabHD();
            this.loadTabDichVu();
            this.loadTabMonAn();
            this.loadTabSanh();
            this.LoadTabKhachHang();
        } catch (SQLException ex) {
            Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //BoPhan
        try{
            this.LoadTabBoPhan();
        }catch(SQLException ex){
            Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //NhanVien
        try{
            this.LoadTabNhanVien();
        }catch(SQLException ex){
            Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadTvNhanVienBPView();
        
}
    public void loadTabHD() throws SQLException{
        this.loadTvHoaDonView();
        this.loadTvHoaDonData(null, null);
        this.dpFromHD.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(LocalDate.now())){
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;");
                }
            }
        };
        final Callback<DatePicker, DateCell> dayCellFactory1 = (final DatePicker datePicker) -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(dpFromHD.getValue()) || item.isAfter(LocalDate.now())){
                        setDisable(true);
                        setStyle("-fx-background-color: #EEEEEE;");
                    }
                }
            };
        this.dpToHD.setDayCellFactory(dayCellFactory1);
        this.dpFromHD.setDayCellFactory(dayCellFactory);
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
            dpToHD.setDayCellFactory(dayCellFactory1);
        });
        this.dpToHD.valueProperty().addListener((ov, oldValue, newValue) -> {
            if(!rbToanBoHD.isSelected())
                try {
                loadTvHoaDonData(java.sql.Date.valueOf(dpFromHD.getValue()), java.sql.Date.valueOf(dpToHD.getValue()));
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            final Callback<DatePicker, DateCell> dayCellFactory0 = (final DatePicker datePicker) -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isAfter(dpToHD.getValue()) || item.isAfter(LocalDate.now())){
                        setDisable(true);
                        setStyle("-fx-background-color: #EEEEEE;");
                    }
                }
            };
            dpFromHD.setDayCellFactory(dayCellFactory0);
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
                this.loadTvDatTiecData(this.txtTimKiemDDT.getText().trim());
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
        this.tvHoaDon.setItems(FXCollections.observableList(s.getListHoaDon(d1, d2)));
        this.txtDoanhThuHD.setText(String.valueOf(s.getDoanhThu(d1, d2)));
    }
    
    
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
                this.loadTvSanhData(this.txt_TimKiemSanh.getText().trim());
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
                this.loadTvMonAnData(this.txt_TimKiemMonAn.getText().trim());
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
                this.loadTVDichVuData(this.txt_TimKiemDichVu.getText().trim());
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     }
     //Thêm Sảnh
     
    
     public void clickThemSanh(ActionEvent event) throws IOException, SQLException{
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
            Utils.getBox("Sai kiểu dữ liệu hoặc chưa điền dữ liệu cần cập nhật", Alert.AlertType.WARNING).show();
        }
        this.loadTvSanhData(null);
    }
     public void deleteSanhHandler(ActionEvent event) throws SQLException, ParseException{
          try{
             Sanh s = new Sanh();
             SanhServices ss = new SanhServices();
             s.setMaSanh(parseInt(txt_MaSanh.getText()));
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
                Utils.getBox("Xin chọn sảnh để xóa", Alert.AlertType.WARNING).show();
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
            Utils.getBox("Sai kiểu dữ liệu hoặc chưa điền dữ liệu cần cập nhật", Alert.AlertType.WARNING).show();
        }
        this.loadTvMonAnData(null);
    }
     public void deleteMonAnHandler(ActionEvent event) throws SQLException, ParseException{
         try{
             MonAn ma = new MonAn();
             MonAnServices mas = new MonAnServices();
             ma.setMaMA(parseInt(txt_MaMA.getText()));
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
                Utils.getBox("Xin chọn món ăn cần xóa!", Alert.AlertType.WARNING).show();
        }
    }
     public void clickThemDichVu(ActionEvent event) throws IOException{
         FXMLLoader fxml = new FXMLLoader(App.class.getResource("FThemDichVu.fxml"));
         Scene sc = new Scene(fxml.load());
         Stage stg = new Stage();
         stg.setScene(sc);
         stg.setTitle("Thêm dịch vụ");
         stg.show();
         stg.setOnCloseRequest((eh) ->{
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
            Utils.getBox("Sai kiểu dữ liệu hoặc chưa điền dữ liệu cần cập nhật", Alert.AlertType.WARNING).show();
        }
        this.loadTVDichVuData(null);
    }
     public void deleteDichVuHandler(ActionEvent event) throws SQLException, ParseException{
        try{
             DichVu dv = new DichVu();
             DichVuServices mas = new DichVuServices();
             dv.setMaDV(parseInt(txt_MaDV.getText()));
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
                Utils.getBox("Xin chọn dịch vụ để xóa!", Alert.AlertType.WARNING).show();
        }
    }
     
     
     
     
     //Nguyen
      private void loadTvNhanVienView(){
        
        TableColumn colmaNhanVien = new TableColumn("Mã nhân viên");
        colmaNhanVien.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colmaNhanVien.setPrefWidth(100);
        
        TableColumn coltenNhanVien = new TableColumn("Tên nhân viên");
        coltenNhanVien.setCellValueFactory(new PropertyValueFactory("TenNV"));
        coltenNhanVien.setPrefWidth(100);
        
        TableColumn colCMND = new TableColumn("Số CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(70);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(70);
        
        TableColumn colchucVu = new TableColumn("Chức vụ");
        colchucVu.setCellValueFactory(new PropertyValueFactory("ChucVu"));
        colchucVu.setPrefWidth(100);
        
        TableColumn colluong = new TableColumn("Lương");
        colluong.setCellValueFactory(new PropertyValueFactory("Luong"));
        colluong.setPrefWidth(100);
        
        TableColumn coltenBoPhan = new TableColumn("Mã bộ phận");
        coltenBoPhan.setCellValueFactory(new PropertyValueFactory("MaBP"));
        coltenBoPhan.setPrefWidth(100);
        
        TableColumn colmaAccount = new TableColumn("Mã Account");
        colmaAccount.setCellValueFactory(new PropertyValueFactory("MaAccount"));
        colmaAccount.setPrefWidth(100);
        
        this.tvNhanVien.getColumns().addAll(colmaNhanVien, coltenNhanVien, colCMND, colSDT, colchucVu, colluong, coltenBoPhan, colmaAccount);
    }
    
    private void loadTvNhanVienData(String nv) throws SQLException{
        NhanVienServices n = new NhanVienServices();
        this.tvNhanVien.setItems(FXCollections.observableList(n.getListNhanVien(nv)));
    }
    
    private void MouseClickTvNhanVien()
    {
        BoPhanServices Bp = new BoPhanServices();
        tvNhanVien.setRowFactory((tv)-> {
            TableRow<NhanVien> row = new TableRow<>();
            row.setOnMouseClicked((event)->{
                if(event.getClickCount()!=0 &&(!row.isEmpty())){
                    NhanVien rowData=row.getItem();
                    this.txtMaNhanVien.setText((String.valueOf(rowData.getMaNV())));
                    this.txtTenNhanVien.setText((String.valueOf(rowData.getTenNV())));
                    this.txtCMND.setText((String.valueOf(rowData.getCMND())));
                    this.txtSDT.setText((String.valueOf(rowData.getSDT())));
                    this.txtChucVu.setText((String.valueOf(rowData.getChucVu())));
                    this.txtLuong.setText((String.valueOf(rowData.getLuong())));
                    try {
                        this.cbTenBP_BP.setValue(Bp.getBP(rowData.getMaBP()));
                    } catch (SQLException ex) {
                        Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.txtMaAccount.setText((String.valueOf(rowData.getMaAccount())));
                }
            });
            return row;
        });
    }
    
    private void LoadTabNhanVien() throws SQLException{
        BoPhanServices Bp = new BoPhanServices();
        this.cbTenBP_BP.setItems(FXCollections.observableList(Bp.getListTen()));
        this.loadTvNhanVienView();
        this.MouseClickTvNhanVien();
        try {
            this.loadTvNhanVienData(null);            
        } catch (SQLException ex) {
             Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
        }

         this.txtTmKiemNV.textProperty().addListener((evt)->{
         try{
             this.loadTvNhanVienData(this.txtTmKiemNV.getText().trim());
         }catch(SQLException ex){
             Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);      
        }        
        });
    }
    
    public void deleteNhanVienHandler(ActionEvent event) throws IOException, SQLException {
        if(!"".equals(txtMaNhanVien.getText().trim()))
        {
            AccountServices s = new AccountServices();
            NhanVienServices nvs = new NhanVienServices();
            NhanVien a = nvs.getNhanVien(parseInt(txtMaNhanVien.getText()));
            try {
                s.delAccount(a.getMaAccount());
                this.loadTvBoPhanData(null);
                this.txtMaNhanVien.clear();
                this.txtTenNhanVien.clear();
                this.txtSDT.clear();
                this.txtCMND.clear();
                this.txtChucVu.clear();
                this.txtLuong.clear();
                this.txtMaAccount.clear();
                Utils.getBox("Xoá nhân viên thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Xoá nhân viên không thành công!!!", Alert.AlertType.WARNING).show();
        }
            this.loadTvNhanVienData(null);
        }
        else
            Utils.getBox("Vui lòng chọn 1 nhân viên để xóa!!!", Alert.AlertType.WARNING).show();
    }
    
    public void deleteBoPhanHandler(ActionEvent event) throws IOException, SQLException {
        if(!"".equals(txtMaBoPhan.getText().trim()))
        {
            BoPhanServices bs = new BoPhanServices();
            BoPhan bp = bs.getBP(parseInt(txtMaBoPhan.getText()));
            try {
                bs.deleteBoPhan(bp.getMaBP());
                this.loadTvBoPhanData(null);
                this.txtMaBoPhan.clear();                                       
                this.txtTenBoPhan.clear();
                this.txtSoLuongBP.clear();
                Utils.getBox("Xoá bộ phận thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Xoá bộ phận không thành công!!!", Alert.AlertType.WARNING).show();
        }
        }
        else
            Utils.getBox("Vui lòng chọn 1 bộ phận để xóa!!!", Alert.AlertType.WARNING).show();
    }

    private void loadTvBoPhanView(){
        TableColumn colmaBoPhan = new TableColumn("Mã bộ phận");
        colmaBoPhan.setCellValueFactory(new PropertyValueFactory("MaBP"));
        colmaBoPhan.setPrefWidth(250);
        
        TableColumn coltenBoPhan = new TableColumn("Tên bộ phận");
        coltenBoPhan.setCellValueFactory(new PropertyValueFactory("TenBP"));
        coltenBoPhan.setPrefWidth(250);
     
        TableColumn colsoLuong = new TableColumn("Số lượng");
        colsoLuong.setCellValueFactory(new PropertyValueFactory("SoLuongNV"));
        colsoLuong.setPrefWidth(250);
        this.tvBoPhan.getColumns().addAll(colmaBoPhan, coltenBoPhan, colsoLuong);
    }

    private void loadTvBoPhanData(String bp) throws SQLException{
        BoPhanServices s= new BoPhanServices();
        this.tvBoPhan.setItems(FXCollections.observableList(s.getListBoPhan(bp)));
    }
    
    private void LoadTabBoPhan() throws SQLException{
        this.loadTvBoPhanView();
        this.MouseClickTvBoPhan();
            try {
                this.loadTvBoPhanData(null);            
            } catch (SQLException ex) {
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
             this.txtTimKiemBP.textProperty().addListener((evt) -> {
             try{
                 this.loadTvBoPhanData(this.txtTimKiemBP.getText().trim());
             }catch(Exception ex){
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);      
            }        
        });
    }
    
    private void MouseClickTvBoPhan(){
        tvBoPhan.setRowFactory((tv)-> {
            TableRow<BoPhan> row =new TableRow<>();
            row.setOnMouseClicked((event)->{
                if(event.getClickCount()!=0 &&(!row.isEmpty())){
                    BoPhan rowData=row.getItem();
                    this.txtMaBoPhan.clear();                                       
                    this.txtTenBoPhan.clear();
                    this.txtSoLuongBP.clear();
                    this.txtMaBoPhan.appendText((String.valueOf(rowData.getMaBP())));                          
                    this.txtTenBoPhan.appendText((String.valueOf(rowData.getTenBP())));
                    this.txtSoLuongBP.appendText(String.valueOf(rowData.getSoLuongNV()));
                    try {
                        this.loadTvNVBP(rowData.getMaBP());
                    } catch (SQLException ex) {
                        Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
    }
    
    public void btnThemBoPhan(ActionEvent event)throws IOException, SQLException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FThemBoPhan.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm Bộ Phận");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.loadTvBoPhanData(null);
                this.txtMaBoPhan.clear();                                       
                this.txtTenBoPhan.clear();
                this.txtSoLuongBP.clear();
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loadTvBoPhanData(null);
    }
    
     public void btnThemNhanVien (ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FDangKyNhanVien.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm Nhân Viên");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.loadTvNhanVienData(null);
                this.loadTvBoPhanData(null);
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
     
     public void updateNhanVienHandler(ActionEvent event) throws SQLException, ParseException{
         BoPhanServices Bp = new BoPhanServices();
         if(!(txtTenNhanVien.getText().trim().equals("")||txtSDT.getText().trim().equals("")||txtCMND.getText().trim().equals("")||txtChucVu.getText().trim().equals("")||txtLuong.getText().trim().equals("")||
              txtMaAccount.getText().trim().equals(""))){
             try{
             NhanVien nv = new NhanVien();
             NhanVienServices ns = new NhanVienServices();
             nv.setMaNV(parseInt(this.txtMaNhanVien.getText()));
             nv.setTenNV(this.txtTenNhanVien.getText());
             nv.setSDT(this.txtSDT.getText());
             nv.setCMND(this.txtCMND.getText());
             nv.setMaBP(Bp.getBP(this.cbTenBP_BP.getValue().toString()).getMaBP());
             nv.setChucVu(this.txtChucVu.getText());
             nv.setLuong(BigDecimal.valueOf((parseInt(this.txtLuong.getText()))));
             nv.setMaAccount(parseInt(this.txtMaAccount.getText()));
             ns.updateNhanVien(nv);
                 this.loadTvBoPhanData(null);
                 this.txtMaNhanVien.clear();
                 this.txtTenNhanVien.clear();
                 this.txtSDT.clear();
                 this.txtCMND.clear();
                 this.txtChucVu.clear();
                 this.txtLuong.clear();
                 this.txtMaAccount.clear();
                 Utils.getBox("Cập nhật nhân viên thành công", Alert.AlertType.INFORMATION).show();
                 this.loadTvNhanVienData(null);
             
         }catch(NumberFormatException numberFormatException){
             Utils.getBox("Kiểu dữ liệu không đúng", Alert.AlertType.WARNING).show();
         }
         }
         else{
             Utils.getBox("Chưa chọn nhân viên cần cập nhật!", Alert.AlertType.INFORMATION).show();
         }
         
     }
     
     public void updateBoPhanHandler(ActionEvent event) throws SQLException, ParseException{
         try{
             BoPhan bp = new BoPhan();
             BoPhanServices ns = new BoPhanServices();
             bp.setMaBP(parseInt(this.txtMaBoPhan.getText()));
             bp.setTenBP(this.txtTenBoPhan.getText());
             bp.setSoLuongNV(parseInt(this.txtSoLuongBP.getText()));
             try{
                 ns.updateBoPhan(bp);
                 this.txtMaBoPhan.clear();
                 this.txtTenBoPhan.clear();
                 this.txtSoLuongBP.clear();
                 Utils.getBox("Cập nhật bộ phận thành công", Alert.AlertType.INFORMATION).show();
             }catch(SQLException ex){
                 Utils.getBox("Vui lòng chọn 1 bộ phận để cập nhật", Alert.AlertType.WARNING).show();
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }catch(NumberFormatException numberFormatException){
             Utils.getBox("Kiểu dữ liệu không đúng", Alert.AlertType.WARNING).show();
         }
         this.loadTvBoPhanData(null);
     }
     
     
     private void loadTvNVBP(int maBP) throws SQLException{
        NhanVienServices s= new NhanVienServices();
        this.tvNVBP.setItems(FXCollections.observableList(s.getNhanVienBP(maBP)));
    }
    private void loadTvNhanVienBPView(){
        TableColumn colMaNV = new TableColumn("Mã NV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(50);
        
        TableColumn colTenNV = new TableColumn("Tên nhân viên");
        colTenNV.setCellValueFactory(new PropertyValueFactory("TenNV"));
        colTenNV.setPrefWidth(170);
        
        TableColumn colSdt = new TableColumn("SDT");
        colSdt.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSdt.setPrefWidth(100);
        
        TableColumn colCV = new TableColumn("Chức vụ");
        colCV.setCellValueFactory(new PropertyValueFactory("ChucVu"));
        colCV.setPrefWidth(100);
        
        this.tvNVBP.getColumns().addAll(colMaNV, colTenNV, colSdt, colCV);
    }
    
    private void LoadTvKhachHangView(){
        
        TableColumn colMaKH = new TableColumn("Mã khách hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(100);  
        
        TableColumn colTenKH = new TableColumn("Tên khách hàng");
        colTenKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colTenKH.setPrefWidth(140); 
        
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("GioiTinh"));
        colGioiTinh.setPrefWidth(70);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(90);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(90);
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("DiaChi"));
        colDiaChi.setPrefWidth(158);
        
        TableColumn colMaAcc = new TableColumn("Mã Account");
        colMaAcc.setCellValueFactory(new PropertyValueFactory("MaAcc"));
        colMaAcc.setPrefWidth(80);
        this.tvKhachHang.getColumns().addAll(colMaKH, colTenKH, colGioiTinh,colCMND, colSDT, colDiaChi, colMaAcc);
        
    }
    
     private void LoadTvKhachHangData (String kw) throws  SQLException{
        KhachHangServices s= new KhachHangServices();
        this.tvKhachHang.setItems(FXCollections.observableList(s.getListKhachHang(kw)));
    }
    /**
     * Initializes the controller class.
     */    
        
    

    private void LoadTvKhachHang (String kw) throws  SQLException{
        KhachHangServices s= new KhachHangServices();
        this.tvKhachHang.setItems(FXCollections.observableList(s.getListKhachHang(kw)));
    }
    
    private void MouseClickTvKhachHang()
    {
        tvKhachHang.setRowFactory((tv)-> {
            TableRow<KhachHang> row =new TableRow<>();
            row.setOnMouseClicked((event)->{
                if(event.getClickCount()!=0 &&(!row.isEmpty())){
                    KhachHang rowData=row.getItem();
                    this.tfMaKH_KH.clear();                                       
                    this.tfTenKH_KH.clear();
                    this.tfGT.clear();
                    this.tfSDT.clear();
                    this.tfCMND.clear();
                    this.tfDiaChi.clear();
                    
                    this.tfMaKH_KH.appendText((String.valueOf(rowData.getMaKH())));                          
                    this.tfTenKH_KH.appendText((String.valueOf(rowData.getTenKH())));
                    this.tfGT.appendText((String.valueOf(rowData.getGioiTinh())));
                    this.tfCMND.appendText((String.valueOf(rowData.getCMND())));
                    this.tfSDT.appendText((String.valueOf(rowData.getSDT())));     
                    this.tfDiaChi.appendText((String.valueOf(rowData.getDiaChi())));                   
                }
            });
            return row;
        });
    }
    
     private void LoadTabKhachHang () throws  SQLException{
             this.LoadTvKhachHangView();
            this.MouseClickTvKhachHang();
            try {
                this.LoadTvKhachHangData(null);            
            } catch (SQLException ex) {
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
             this.tfTimKiemKH.textProperty().addListener((evt)->{
             try{
                 this.LoadTvKhachHangData(this.tfTimKiemKH.getText().trim());
             }catch(Exception ex){
                 Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);      
            }        
        });
            
            
        }

    public void btnThemKhachHang (ActionEvent event)throws IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FDangKyKhachHang.fxml"));
        
        Scene scene= new Scene(fxmlLoader.load());
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.setTitle("Thêm Khách Hàng");
        stage.show();
        stage.setOnCloseRequest((eh) -> {
            try {
                this.LoadTvKhachHangData(null);
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    

    public void xoaKhachHang(ActionEvent event) throws SQLException{
       KhachHangServices k = new KhachHangServices();
       AccountServices s = new AccountServices();
       if(!(tfGT.getText().equals("")||tfDiaChi.getText().trim().equals("")||tfTenKH_KH.getText().trim().equals("")||tfSDT.getText().trim().equals("")||tfCMND.getText().trim().equals("")))
       {
       KhachHang kh = k.getKhachHang(parseInt(this.tfMaKH_KH.getText()));
       
            
            try {
                    s.delAccount(kh.getMaAcc());
                    this.tfMaKH_KH.clear();                                       
                    this.tfTenKH_KH.clear();
                    this.tfGT.clear();
                    this.tfSDT.clear();
                    this.tfCMND.clear();
                    this.tfDiaChi.clear();
                Utils.getBox("Xoá thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(FQuanLyController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Xoá không thành công!!!", Alert.AlertType.WARNING).show();
                               
            }
            this.LoadTvKhachHangData(null);
    }
       else
       {
               Utils.getBox("Chưa chọn khách hàng để xoá!", Alert.AlertType.WARNING).show();
               }
         }
    
 

    


    
   
    
    public void updateKhachHanHandler (ActionEvent event) throws SQLException, ParseException{
       if(!(tfGT.getText().equals("")||tfDiaChi.getText().trim().equals("")||tfTenKH_KH.getText().trim().equals("")||tfSDT.getText().trim().equals("")||tfCMND.getText().trim().equals("")))
       { try{
           
        KhachHang d = new KhachHang();
        KhachHangServices ks=new KhachHangServices();
        d.setMaKH(parseInt(this.tfMaKH_KH.getText()));
        d.setGioiTinh(this.tfGT.getText());
        d.setTenKH(this.tfTenKH_KH.getText());
        d.setDiaChi(this.tfDiaChi.getText());
        d.setSDT(Integer.toString(parseInt(this.tfSDT.getText())));
        d.setCMND(Integer.toString(parseInt(this.tfCMND.getText())));        
                ks.updateKhachhang(d);
                
                this.tfGT.clear();
                this.tfTenKH_KH.clear(); 
                this.tfDiaChi.clear();
                this.tfSDT.clear();
                this.tfCMND.clear();
                this.tfMaKH_KH.clear();    
                Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
                this.LoadTvKhachHangData(null);

            
//                Utils.getBox("Dữ liệu cập nhật chưa được thay đổi!!", Alert.AlertType.INFORMATION).show();
               
            
        }catch(NumberFormatException ex){
            Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();  
        }}
       else{
           Utils.getBox("Chưa chọn khách hàng cần cập nhật!", Alert.AlertType.INFORMATION).show();
       }
    }
}
