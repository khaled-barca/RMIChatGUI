package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ChatController {

    ClientImpl client;
    ChatView chatView;

    public ChatController(ClientImpl client, ChatView view) {
        this.client = client;
        chatView = view;
        client.setController(this);
        view.setController(this);
    }

    public void setClient(ClientImpl client) {

        this.client = client;
    }

    public void setView(ChatView view) {
        chatView = view;
    }


    public void sendMessage(Message message) {
        try {
            client.sendMsg(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void displayMessage(Message message) {
        chatView.addFormattedMessage(message);
    }

    public void close() {
        try {
            client.closeConnection();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
