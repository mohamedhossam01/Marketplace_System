package com.example.marketplace.controller;

import com.example.marketplace.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class customerHomeController{

    @FXML
    public void initialize() {
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
    public void onDeposit() {
        String amountText = money_deposit.getText();

        if (amountText.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please enter an amount to be deposit");
            alert.show();
            return;
        }

        int amount = Integer.parseInt(amountText);
        App.mainCustomer.setCash(App.mainCustomer.getCash() + amount);
        deposit_cash.setText(String.valueOf(App.mainCustomer.getCash()));
    }

    @FXML
    public void onBack(){
        App.startLogInView();
    }

    @FXML
    public void onManageCart() {
        App.startCartView();
    }

    @FXML
    public void onHistory() {
        App.startHistoryView();
    }
}
