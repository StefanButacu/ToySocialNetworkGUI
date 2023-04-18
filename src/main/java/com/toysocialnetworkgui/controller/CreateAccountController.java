package com.toysocialnetworkgui.controller;

import com.toysocialnetworkgui.repository.RepoException;
import com.toysocialnetworkgui.repository.db.DbException;
import com.toysocialnetworkgui.service.Facade;
import com.toysocialnetworkgui.utils.MyAlert;
import com.toysocialnetworkgui.validator.ValidatorException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;

public class CreateAccountController {
    private Stage window;
    private Facade facade;

    @FXML
    private Button buttonSubmit;

    @FXML
    private Button buttonCancel;

    @FXML
    public TextField textFieldFirstname;
    @FXML
    public TextField textFieldLastname;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public PasswordField textFieldPasswordConfirmation;
    ValidationSupport validationSupport;

    public void initialize(Facade service, Stage window) {
        this.facade = service;
        this.window = window;
        // TODO
        //  Need to ADD this as VM argument --add-opens=javafx.graphics/javafx.scene=org.controlsfx.controls
        validationSupport = new ValidationSupport();
        validationSupport.registerValidator(textFieldFirstname, Validator.createEmptyValidator("Field is required"));
        validationSupport.registerValidator(textFieldLastname, Validator.createEmptyValidator("Field is required"));
        validationSupport.registerValidator(textFieldEmail, Validator.createEmptyValidator("Field is required"));
        validationSupport.registerValidator(textFieldPassword, Validator.createEmptyValidator("Field is required"));
        validationSupport.registerValidator(textFieldPasswordConfirmation, Validator.createEmptyValidator("Field is required"));
    }

    @FXML
    protected void onSubmitButtonClick() {
        if (!textFieldPassword.getText().equals(textFieldPasswordConfirmation.getText())){
            MyAlert.StartAlert("Error", "Passwords do not match", Alert.AlertType.WARNING);
        }
        try {
            facade.addUser(textFieldFirstname.getText(), textFieldLastname.getText(), textFieldEmail.getText(), textFieldPassword.getText());
            showLoginScene();
       } catch (RepoException | ValidatorException | DbException | IOException e) {
            MyAlert.StartAlert("Error", e.getMessage(), Alert.AlertType.WARNING);
       }
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        showLoginScene();
    }

    @FXML
    protected void showLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginScene.fxml"));
        Parent root = loader.load();
        LoginSceneController controller = loader.getController();
        controller.initialize(facade, window);
        window.getScene().setRoot(root);
    }
}
