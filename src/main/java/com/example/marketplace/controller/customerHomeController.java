package com.example.marketplace.controller;

import com.example.marketplace.ClientApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class customerHomeController{

    @FXML
    public void initialize() {
        name.setText(ClientApp.mainCustomer.getFullName());
        email.setText(ClientApp.mainCustomer.getEmail());
        deposit_cash.setText(String.valueOf(ClientApp.mainCustomer.getCash()));
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
        ClientApp.mainCustomer.setCash(ClientApp.mainCustomer.getCash() + amount);
        deposit_cash.setText(String.valueOf(ClientApp.mainCustomer.getCash()));
    }

    @FXML
    public void onBack(){
        ClientApp.startLogInView();
    }

    @FXML
    public void onManageCart() {
        ClientApp.startCartView();
    }

    @FXML
    public void onHistory() {
        ClientApp.startHistoryView();
    }
}
