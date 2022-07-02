package com.example.marketplace.controller;

import com.example.marketplace.ClientApp;
import com.example.marketplace.dao.CustomerDao;
import com.example.marketplace.dao.CustomerDaoJdbc;
import com.example.marketplace.dao.Jdbc;
import com.example.marketplace.model.Customer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField ipTF;

    @FXML
    protected void onSignInButtonClick() {
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please type your email and password");
            alert.show();
            return;
        }
        String userEmail = email.getText();
        CustomerDao dao = new CustomerDaoJdbc();
        Customer customer = dao.findCustomerByEmail(userEmail);
        String customerPassword = dao.hashPassword(password.getText());

        if (customer == null || !customer.getPassword().equals(customerPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Wrong email or password");
            alert.show();
            return;
        }

        ClientApp.mainCustomer = customer;
        ClientApp.startCustomerView();
    }

    @FXML
    protected void onSignUpButtonClick() {
        ClientApp.startSignUpView();
    }

    @FXML
    protected void onSetDBIp() {
        Jdbc.setIP(ipTF.getText());
    }
}
