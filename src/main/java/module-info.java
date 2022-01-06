module com.mycompany.qlnhahang {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.qlnhahang to javafx.fxml;
    exports com.mycompany.qlnhahang;
    exports com.mycompany.conf;
    exports com.mycompany.pojo;
}
