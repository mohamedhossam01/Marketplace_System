package com.example.marketplace.controller;

import com.example.marketplace.App;
import com.example.marketplace.dao.CustomerDao;
import com.example.marketplace.dao.CustomerDaoJdbc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpController {
    
    @FXML
    TextField name;

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    protected void onSignUpButtonClick(){
       CustomerDao dao = new CustomerDaoJdbc();
       boolean b = dao.insertNewCustomer(email.getText(), password.getText(), name.getText());
       if(!b){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("Wrong data please try again");
           alert.show();
       }
       else{
           App.startLogInView();
       }
    }

    @FXML
    protected void onCloseButtonClick(){
        App.startLogInView();
    }

}