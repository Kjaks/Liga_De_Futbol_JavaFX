module com.karolis.futbolfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.karolis.futbolfx to javafx.fxml;
    exports com.karolis.futbolfx;
}
