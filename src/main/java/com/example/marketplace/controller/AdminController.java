package com.example.marketplace.controller;

import com.example.marketplace.App;
import com.example.marketplace.dao.AdminDao;
import com.example.marketplace.dao.AdminDaoJdbc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

    AdminDaoJdbc currentAdmin;

    public void initialize() {
        currentAdmin = new AdminDaoJdbc();
    }

    @FXML
    public void onBack() {
        App.startLogInView();
    }

    @FXML
    TextField numberOfRegisteredAccounts = new TextField();

    @FXML
    TextField numberOfItemsSold = new TextField();

    @FXML
    TextField numberOfAvailableItems = new TextField();

    @FXML
    public void OnGenerateReport(ActionEvent e) {
        numberOfRegisteredAccounts.setText(String.valueOf(currentAdmin.getNumberOfRegisteredAccounts()));
        numberOfItemsSold.setText(String.valueOf(currentAdmin.getNumberOfSoldItems()));
        numberOfAvailableItems.setText(String.valueOf(currentAdmin.getNumberOfAvailableItems()));
    }
}
