module com.boxranger.boxranger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens com.boxranger.boxranger to javafx.fxml;
    exports com.boxranger.boxranger;
    opens com.boxranger.boxranger.controller to javafx.fxml;
    exports com.boxranger.boxranger.controller;
}