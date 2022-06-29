package com.example.marketplace.controller;

import com.example.marketplace.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class customerHomeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(App.mainCustomer.getFullName());
        email.setText(App.mainCustomer.getEmail());
        deposit_cash.setText(String.valueOf(App.mainCustomer.getCash()));
    }

    @FXML
    TextArea name = new TextArea();

    @FXML
    TextArea email = new TextArea();

    @FXML
    TextArea deposit_cash = new TextArea();

    @FXML
    TextField money_deposit = new TextField();

    @FXML
    public void onDeposit(ActionEvent e) {
        int amount = Integer.parseInt(money_deposit.getText());
        App.mainCustomer.setCash(App.mainCustomer.getCash() + amount);
        deposit_cash.setText(String.valueOf(App.mainCustomer.getCash()));
    }

    @FXML
    public void onBack(ActionEvent e){
        App.startLogInView();
    }
}
