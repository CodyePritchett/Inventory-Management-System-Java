module com.example.schoolprojectjava1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.schoolprojectjava1 to javafx.fxml;
    exports com.example.schoolprojectjava1;
}