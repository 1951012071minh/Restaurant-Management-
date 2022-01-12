package com.mycompany.qlnhahang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FGiaoDienKH.fxml"));
            
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