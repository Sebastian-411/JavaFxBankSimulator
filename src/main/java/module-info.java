module com.example.labjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labjavafx to javafx.fxml;
    exports com.example.labjavafx;
    exports com.example.labjavafx.model;
    opens com.example.labjavafx.model to javafx.fxml;
}