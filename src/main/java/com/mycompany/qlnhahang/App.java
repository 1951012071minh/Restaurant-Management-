package com.mycompany.qlnhahang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FDangNhap.fxml"));
            
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                Logger.getLogger(FGiaoDienKHController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage= new Stage();
            stage.setScene(scene);
            stage.show();
    }



    public static void main(String[] args) {
        launch();
        

}}