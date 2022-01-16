/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qlnhahang;

import com.mycompany.conf.HashP;
import com.mycompany.conf.Utils;
import com.mycompany.pojo.Account;
import com.mycompany.pojo.BoPhan;
import com.mycompany.pojo.NhanVien;
import com.mycompany.services.AccountServices;
import com.mycompany.services.BoPhanServices;
import com.mycompany.services.NhanVienServices;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/*
 * FXML Controller class
 *
 * @author ANHMINH
 */
public class FDangKyNhanVienController implements Initializable {

    @FXML private TextField txtTenNhanVien;
    @FXML private TextField txtSDT;
    @FXML private TextField txtSoCMND;
    @FXML private TextField txtChucVu;
    @FXML private TextField txtTenTK;
    @FXML private TextField txtMatKhau;
    @FXML private ComboBox<BoPhan> cbTenBP;
    @FXML private TextField txtXacNhanMK;
    @FXML private Button btnThoatDKNV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       BoPhanServices bs = new BoPhanServices();
       List<BoPhan> bp;
        try {
            bp = bs.getListBoPhan(null);
            this.cbTenBP.setItems(FXCollections.observableList(bp));
            this.cbTenBP.setValue(bp.get(1));
        } catch (SQLException ex) {
            Logger.getLogger(FDangKyNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
       cbTenBP.setValue(null);
    }   
    
    public void addNhanVienHandler(ActionEvent event) throws SQLException,ParseException{
            try{ 
                AccountServices as= new AccountServices();
                NhanVien k= new NhanVien();
                HashP mk= new HashP();
                String passHash="";
                Account a= new Account();
                NhanVienServices s= new NhanVienServices();

                if(txtTenNhanVien.getText().trim().equals("")
                        ||txtSDT.getText().trim().equals("")||txtSoCMND.getText().trim().equals("")
                        ||txtChucVu.getText().trim().equals("")||txtTenTK.getText().trim().equals("")
                        ||txtMatKhau.getText().trim().equals("")||txtXacNhanMK.getText().trim().equals("") || this.cbTenBP.getSelectionModel().getSelectedItem() == null){
                    throw new Exception("Không được để trống thông tin");
                }
                if(!txtMatKhau.getText().equals(txtXacNhanMK.getText()))
                    throw new Exception( "Mật khẩu và xác nhận mật khẩu phải trùng nhau");
                if(txtTenTK.getText().contains(" ") || txtTenTK.getText().length() < 6)
                    throw new Exception("Vui lòng nhập tên tài khoản ít nhất 6 kì tự và không có khoảng trắng!");
                if(txtMatKhau.getText().length()<6||txtMatKhau.getText().contains(" "))
                     throw new Exception("Vui lòng nhập mật khẩu ít nhất 6 kì tự và không có khoảng trắng!");
                if(parseInt(txtSDT.getText()) <= 0 || parseInt(txtSoCMND.getText()) <= 0)
                    throw new NumberFormatException();
                if(as.FindAccount(this.txtTenTK.getText()) != null)
                    throw new Exception("Tên tài khoản đã tồn tại");
                k.setMaNV(s.getMaxMaNhanVien());
                k.setTenNV(this.txtTenNhanVien.getText());
                k.setSDT(Integer.toString(parseInt(this.txtSDT.getText())));
                k.setCMND(Integer.toString(parseInt(this.txtSoCMND.getText())));
                k.setMaBP((this.cbTenBP.getSelectionModel().getSelectedItem().getMaBP()));
                k.setChucVu(this.txtChucVu.getText());
                k.setMaAccount(as.getMaAccount());

                passHash = mk.HashPassword(this.txtMatKhau.getText());

                a.setMaAccount(as.getMaAccount());
                a.setUsername(this.txtTenTK.getText());
                a.setPassWord(passHash);
                a.setTypeUser("NV");
                as.addAccount(a);
                s.addNhanVien(k);
                Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
                Stage stage = (Stage) btnThoatDKNV.getScene().getWindow();
                stage.close();
           }catch(NumberFormatException ex){
                 Utils.getBox("Vui lòng nhập đúng kiểu dữ liệu!", Alert.AlertType.INFORMATION).show();
           }catch(Exception ex){
               Utils.getBox(ex.getMessage(), Alert.AlertType.INFORMATION).show();
           }
    }
    public void btnThoatDKNV(ActionEvent event){
        Stage stage = (Stage) btnThoatDKNV.getScene().getWindow();
        stage.close();
    } 
    
}
