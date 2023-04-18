package com.toysocialnetworkgui;

import com.toysocialnetworkgui.controller.LoginSceneController;
import com.toysocialnetworkgui.domain.network.Network;
import com.toysocialnetworkgui.repository.db.*;
import com.toysocialnetworkgui.service.*;
import com.toysocialnetworkgui.utils.CONSTANTS;
import com.toysocialnetworkgui.validator.FriendshipValidator;
import com.toysocialnetworkgui.validator.ConversationParticipantValidator;
import com.toysocialnetworkgui.validator.MessageValidator;
import com.toysocialnetworkgui.validator.UserValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ToySocialNetworkApp extends Application {

    Scene loginScene;
    LoginSceneController loginSceneController;
    Service service;

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLogin = new FXMLLoader(ToySocialNetworkApp.class.getResource("controller/loginScene.fxml"));
        initialize();

        loginScene = new Scene(fxmlLogin.load());
        loginSceneController = fxmlLogin.getController();
        loginSceneController.initialize(service, primaryStage);
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        primaryStage.setUserData(exec);
        primaryStage.setOnCloseRequest(event ->
                ((ScheduledExecutorService)primaryStage.getUserData()).shutdown());

        primaryStage.setResizable(false);
        primaryStage.setTitle("Big Blana Society");
        primaryStage.getIcons().add(new Image("E:\\Anul3Sem2\\DesignPatterns\\ToySocialNetworkGUI\\src\\main\\resources\\images\\logo_small.png"));

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        // TODO
        //  - Design Patterns
        //  - MVC (x)
        //  - Singleton (x)
        //  - Observer (x)
        //  - Builder (x)
        //  - Factory?
        //  - Strategy
    }

    private void initialize() {
        // USER
        UserDbRepo uRepo = UserDbRepo.getInstance();
        UserService uSrv = new UserService(uRepo);
        FriendshipDbRepo fRepo = FriendshipDbRepo.getInstance();

        FriendshipRequestDbRepo friendshipRequestRepo = FriendshipRequestDbRepo.getInstance();
        FriendshipService fSrv = new FriendshipService(fRepo, friendshipRequestRepo);
        // CONVERSATION
        ConversationDbRepo cRepo =ConversationDbRepo.getInstance();
        MessageDbRepo mRepo = MessageDbRepo.getInstance();
        MessageService mSrv = new MessageService(mRepo);
        ConversationParticipantDbRepo crRepo = ConversationParticipantDbRepo.getInstance();
        ConversationService mrSrv = new ConversationService(cRepo, crRepo);
        Network network = new Network(uRepo, fRepo);

        /// EVENTS
        EventDbRepo eventRepo = EventDbRepo.getInstance();
        EventsSubscriptionDbRepo eventsSubscriptionRepo = EventsSubscriptionDbRepo.getInstance();
        EventService eventService = new EventService(eventRepo, eventsSubscriptionRepo);
        this.service = new Service(uSrv, fSrv, mSrv, mrSrv, network, eventService);
    }
}