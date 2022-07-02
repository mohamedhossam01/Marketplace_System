package com.example.marketplace.controller;

import com.example.marketplace.ServerLogic.Server;
import com.example.marketplace.dao.AdminDaoJdbc;
import com.example.marketplace.dao.Jdbc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AdminController {

    private AdminDaoJdbc currentAdmin;

    @FXML
    private TextField ipTF;

    public void initialize() {
        currentAdmin = new AdminDaoJdbc();
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

    @FXML
    protected void onSetDBIp() {
        Jdbc.setIP(ipTF.getText());
    }

    @FXML
    protected void onStartSocket() {
        Server.startNow();
    }
}
