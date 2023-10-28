module com.example.temaiss {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.temaiss to javafx.fxml;
    exports com.example.temaiss;
}