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
import com.mycompany.services.DatDichVuServices;
import com.mycompany.services.DatMonAnServices;
import com.mycompany.services.DatTiecServices;
import com.mycompany.services.DichVuServices;
import com.mycompany.services.HoaDonServices;
import com.mycompany.services.MonAnServices;
import com.mycompany.services.SanhServices;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class FChiTietDatTiecController implements Initializable {
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
    @FXML private TextField txtMaTiec;
    @FXML private Button btn_QuayLai;
    
 
    private KhachHang khachHang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void loadTabDatTiec(KhachHang khachHang, int maTiec) throws SQLException{
        this.loadTvDatMonAnTTView();
        this.loadTvDichVuDatTTView();
        this.txtMaTiec.setText(Integer.toString(maTiec));
        this.txtMaKHTT.setText(Integer.toString(khachHang.getMaKH()));
        this.txtTenKHTT.setText(khachHang.getTenKH());
        this.txtCMND.setText(khachHang.getCMND());
        this.txtSDT.setText(khachHang.getSDT());
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
    public void closeButtonAction(ActionEvent event){
        Stage stage = (Stage) btn_QuayLai.getScene().getWindow();
        stage.close();
    }
    
    
   
}
