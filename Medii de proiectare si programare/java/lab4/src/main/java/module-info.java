module com.example.temampp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;


    opens com.example.temampp to javafx.fxml;
    opens com.example.temampp.domains to javafx.base;
    exports com.example.temampp;
}