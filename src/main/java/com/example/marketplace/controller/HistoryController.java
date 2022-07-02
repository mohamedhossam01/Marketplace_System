package com.example.marketplace.controller;

import com.example.marketplace.ClientApp;
import com.example.marketplace.dao.ItemDaoJdbc;
import com.example.marketplace.model.Item;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HistoryController {

    @FXML
    VBox historyVBox = new VBox();

    @FXML
    void initialize() {
        ItemDaoJdbc dao = new ItemDaoJdbc();

        ArrayList<Item> itemsHistory = dao.getHistoryFor(ClientApp.mainCustomer.getId());
        for (int i = 0; i < itemsHistory.size(); i++) {
            Item item = itemsHistory.get(i);
            Label itemInfo = new Label("name: " + item.getName() + "\n" + "price: " + item.getPrice());

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefWidth(940);
            borderPane.setLeft(itemInfo);

            HBox hb = new HBox();
            hb.getChildren().add(borderPane);
            hb.setSpacing(10);
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setMinHeight(50);
            hb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");

            historyVBox.getChildren().add(hb);
        }
    }

    @FXML
    public void onBack() {
        ClientApp.startCustomerView();
    }
}
