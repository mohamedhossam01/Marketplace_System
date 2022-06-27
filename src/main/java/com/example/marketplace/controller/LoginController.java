package com.example.marketplace.controller;

import com.example.marketplace.App;
import com.example.marketplace.dao.CustomerDao;
import com.example.marketplace.dao.CustomerDaoJdbc;
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
    protected void onSignInButtonClick() {
        String userEmail = email.getText();
        CustomerDao dao = new CustomerDaoJdbc();
        Customer customer = dao.findCustomerByEmail(userEmail);
        String customerPassword = dao.hashPassword(password.getText());

        if(customer == null || !customer.getPassword().equals(customerPassword)){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Wrong email or password");
            alert.show();
            return;
        }

        App.mainCustomer = customer;
        App.startCustomerView();
    }

    @FXML
    protected void onSignUpButtonClick(){
        App.startSignUpView();
    }

}
