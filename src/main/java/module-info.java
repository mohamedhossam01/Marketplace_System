module com.example.marketplace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.marketplace to javafx.fxml;
    opens com.example.marketplace.controller to javafx.fxml;
    exports com.example.marketplace;
}