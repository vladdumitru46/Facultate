module com.example.schelet {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.schelet to javafx.fxml;
    exports org.example.schelet;
}