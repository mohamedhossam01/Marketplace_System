package com.example.marketplace;

import java.io.IOException;

import com.example.marketplace.model.Customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {

    public static Stage mainStage;
    public static Customer mainCustomer;
    public static FXMLLoader fxmlLoader;

    private static void startView(String name){
        fxmlLoader = new FXMLLoader(ClientApp.class.getResource(name));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load(), 1000, 600);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public static void startLogInView(){
        startView("view/login-view.fxml");
    }

    public static void startSignUpView(){
        startView("view/signup-view.fxml");
    }

    public static void startCustomerView(){
        startView("view/costumer-home-view.fxml");
    }

    public static void startAdminView(){
        startView("view/admin-view.fxml");
    }

    public static void startCartView(){
        startView("view/cartAndSearch-view.fxml");
    }

    public static void startHistoryView(){
        startView("view/history-view.fxml");
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        startLogInView();
        stage.setTitle("Marketplace System");
        stage.show();
    }

    public static void main(String[] args) {
        // Server.main(args);
        launch();
    }

}
