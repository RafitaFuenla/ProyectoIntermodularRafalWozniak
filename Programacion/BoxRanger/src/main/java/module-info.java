module com.boxranger.boxranger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.boxranger.boxranger to javafx.fxml;
    exports com.boxranger.boxranger;
}