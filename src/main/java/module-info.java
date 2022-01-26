module com.mycompany.sistemcatatpoinkeaktifan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires jasperreports;
    requires java.desktop;
    requires java.logging;
   //requires mysql.connector.java;
    requires java.base;

    opens com.mycompany.sistemcatatpoinkeaktifan to javafx.fxml;
    exports com.mycompany.sistemcatatpoinkeaktifan;
    
}
