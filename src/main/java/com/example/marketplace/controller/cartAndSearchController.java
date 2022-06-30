package com.example.marketplace.controller;

import com.example.marketplace.App;
import com.example.marketplace.dao.ItemDaoJdbc;
import com.example.marketplace.model.Customer;
import com.example.marketplace.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class cartAndSearchController {
    //public Customer customer = App.mainCustomer;

    ItemDaoJdbc globalItemDao;

    @FXML
    VBox cartVBox = new VBox();

    @FXML
    VBox searchVBox = new VBox();

    @FXML
    TextField nameTF = new TextField();

    @FXML
    TextField categoryTF = new TextField();

    @FXML
    Button addButton;

    @FXML
    Button removeButton;

    ArrayList<Item> itemsAvailable;
    ArrayList<Item> itemsInCart;

    @FXML
    void initialize() {

        // update the ds
        globalItemDao = new ItemDaoJdbc();
        itemsAvailable = globalItemDao.getAvailableItems();
        itemsInCart = globalItemDao.getCartContentsFor(App.mainCustomer.getId());

        updateView();
    }

    void updateView() {
        // clearing the windows
        searchVBox.getChildren().clear();
        cartVBox.getChildren().clear();

        // show available items
        for (int i = 0; i < itemsAvailable.size(); i++) {
            Item item = itemsAvailable.get(i);
            Label itemInfo = new Label("name: " + item.getName() + "\n" + "price: " + item.getPrice());

            addButton = new Button();
            addButton.setText("Add");
            addButton.setStyle("-fx-font-size: 15");

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefWidth(470);
            borderPane.setLeft(itemInfo);
            borderPane.setRight(addButton);

            // action in add item to the cart
            addButton.setOnAction(e -> {
                Button bt = (Button) e.getSource();
                HBox hb = (HBox) bt.getParent().getParent();
                int x = searchVBox.getChildren().indexOf(hb);

                itemsInCart.add(itemsAvailable.get(x));

                globalItemDao.setToAdded(App.mainCustomer.getId(), itemsAvailable.get(x).getId(),
                        true);

                itemsAvailable.remove(x);
                updateView();
            });

            HBox hb = new HBox();
            hb.getChildren().add(borderPane);
            hb.setSpacing(10);
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setMinHeight(50);
            hb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");

            searchVBox.getChildren().add(hb);
        }

        // show items in cart
        for (int i = 0; i < itemsInCart.size(); i++) {
            Item item = itemsInCart.get(i);
            Label itemInfo = new Label("name: " + item.getName() + "\n" + "price: " + item.getPrice());

            removeButton = new Button();
            removeButton.setText("Remove");
            removeButton.setStyle("-fx-font-size: 15");

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefWidth(470);
            borderPane.setLeft(itemInfo);
            borderPane.setRight(removeButton);

            // action in remove item from the cart
            removeButton.setOnAction(e -> {
                Button bt = (Button) e.getSource();
                HBox hb = (HBox) bt.getParent().getParent();
                int x = cartVBox.getChildren().indexOf(hb);

                itemsAvailable.add(itemsInCart.get(x));

                globalItemDao.setToAdded(App.mainCustomer.getId(), itemsInCart.get(x).getId(),
                        false);

                itemsInCart.remove(x);
                updateView();
            });

            HBox hb = new HBox();
            hb.getChildren().add(borderPane);
            hb.setSpacing(10);
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.setMinHeight(50);
            hb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");

            cartVBox.getChildren().add(hb);
        }
    }

    @FXML
    public void onSearch(){
        String nameToSearch = nameTF.getText();
        String categoryToSearch = categoryTF.getText();

        if (!nameToSearch.isEmpty() && !categoryToSearch.isEmpty()) {
            itemsAvailable = globalItemDao.findItemByNameAndCategory(nameToSearch, categoryToSearch);
        } else if (!nameToSearch.isEmpty()) {
            itemsAvailable = globalItemDao.findItemByName(nameToSearch);
        } else if (!categoryToSearch.isEmpty()){
            itemsAvailable = globalItemDao.findItemByCategory(categoryToSearch);
        } else {
            itemsAvailable = globalItemDao.getAvailableItems();
        }

        updateView();
    }

    @FXML
    public void onCheckout(ActionEvent e) {
        Customer customer = App.mainCustomer;
        int sum = 0;
        for (Item item : itemsInCart) {
            sum += item.getPrice();
        }
        if (sum > customer.getCash()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No enough balance to buy items");
            alert.show();
        } else {
            customer.setCash(customer.getCash() - sum);

            for (Item item : itemsInCart) {
                globalItemDao.setToPaid(customer.getId(), item.getId());
            }
            itemsInCart = new ArrayList<>();
        }

        updateView();
    }

    @FXML
    public void onBack(ActionEvent e) {
        App.startCustomerView();
    }
}