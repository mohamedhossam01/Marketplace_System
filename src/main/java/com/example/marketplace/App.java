package com.example.marketplace;

import java.io.IOException;

import com.example.marketplace.model.Customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage mainStage;
    public static Customer mainCustomer;

    private static void startView(String name){
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(name));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        mainStage.setScene(scene);
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

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        startLogInView();
        stage.setTitle("Marketplace System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
