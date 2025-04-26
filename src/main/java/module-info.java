module com.example.cwdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cwdemo to javafx.fxml;
    exports com.example.cwdemo;
    exports com.example.cwdemo.service;
    opens com.example.cwdemo.service to javafx.fxml;
    exports com.example.cwdemo.controller;
    opens com.example.cwdemo.controller to javafx.fxml;
    exports com.example.cwdemo.model;
    opens com.example.cwdemo.model to javafx.fxml;
}