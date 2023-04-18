package com.toysocialnetworkgui.controller;

import com.toysocialnetworkgui.domain.User;
import com.toysocialnetworkgui.service.*;
import com.toysocialnetworkgui.utils.MyAlert;
import com.toysocialnetworkgui.utils.PasswordEncryptor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController {
    private Stage window;

    private Facade facade;
    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPassword;

    private UserAuth databaseAuthService;
    private UserAuth googleAuthService;

    public void initialize(Facade facade, Stage primaryStage) {
        this.facade = facade;
        this.window = primaryStage;
        this.databaseAuthService = new DefaultUserAuth(facade);
        googleAuthService = new AuthAdapter(new ThirdPartyAuth());
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        boolean isAuthenticated = databaseAuthService.login(textFieldEmail.getText(), textFieldPassword.getText());
        if(isAuthenticated) {
            User loggedUser = this.facade.getUser(textFieldEmail.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loggedScene.fxml"));
            Parent root = loader.load();
            LoggedSceneController controller = loader.getController();
            controller.initialize(facade, loggedUser, window);
            Scene scene = new Scene(root);
            window.setScene(scene);
        }
        else {
            MyAlert.StartAlert("Error", "Wrong email or password", Alert.AlertType.ERROR);
        }
    }


    @FXML
    protected void onGoogleButtonClick() throws IOException {
        boolean isAuthenticated = googleAuthService.login("stef@gmail.com", "123456");
        if(isAuthenticated) {
            User loggedUser = this.facade.getUser("stef@gmail.com");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loggedScene.fxml"));
            Parent root = loader.load();
            LoggedSceneController controller = loader.getController();
            controller.initialize(facade, loggedUser, window);
            Scene scene = new Scene(root);
            window.setScene(scene);
        }
        else {
            MyAlert.StartAlert("Error", "Wrong email or password", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onSignUpClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createAccount.fxml"));
        Parent root = loader.load();
        CreateAccountController controller = loader.getController();
        controller.initialize(facade, window);

        window.getScene().setRoot(root);
    }
}