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

import java.net.URL;
import java.util.ResourceBundle;

public class ChatView implements Initializable {
    @FXML
    BorderPane borderPane;

    @FXML
    private TextField messageTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private VBox messagesVBox;

    @FXML
    private ScrollPane scrollPane;

    ChatController controller;

    String name;
    Stage stage;
    String imageUrl;

    public void setImage(String path) {
        imageUrl = path;
        Image image = new Image(path);
        imageView.setImage(image);
    }

    public void setController(ChatController controller) {
        this.controller = controller;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addGradient();
        messageTextField.setOnAction(e -> enterText());
        scrollPane.vvalueProperty().bind(messagesVBox.heightProperty());
        messagesVBox.setFillWidth(true);
        scrollPane.setFitToWidth(true);
    }

    private void addGradient() {
        Color start = Color.rgb(179, 224, 255);
        Color end = Color.rgb(0, 138, 230);
        Stop[] stops = new Stop[]{new Stop(0, start), new Stop(1, end)};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill bf = new BackgroundFill(lg, CornerRadii.EMPTY, Insets.EMPTY);
        borderPane.setBackground(new Background(bf));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setResizable(true);
        messagesVBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.7));
        stage.setOnCloseRequest(e -> {
            controller.close();
            System.exit(1);
        });
    }

    public void setName(String name) {

        this.name = name;
    }

    public void enterText() {
        if (isValidMessage()) {
            Message message = new Message(name, messageTextField.getText().trim(), imageUrl);
            //String message = imageUrl + "$" + name + "$" + messageTextField.getText().trim();
            controller.sendMessage(message);
            messageTextField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty message");
            alert.setContentText("Please enter a non-empty message");
            alert.showAndWait();
        }
    }

    private boolean isValidMessage() {

        return !messageTextField.getText().trim().isEmpty();
    }

    public void addFormattedMessage(Message message) {
        Platform.runLater(() -> {
//            String[] parts = message.split("$", 3);
//            String imagePath = parts[0];
//            String sender = parts[1];
//            String content = parts[2];
//            System.out.println("full message : " + message);
//            System.out.println("sender : " + sender);
//            System.out.println("content : " + content);
//            System.out.println("imagePath : " + imagePath);
            Text text = new Text(String.format("%s: %s", message.sender, message.content));
            ImageView avatarImageView = new ImageView();
            HBox container = new HBox(avatarImageView, text);
            container.setSpacing(10);
            container.setFillHeight(true);
            if (this.name.equals(message.sender)) {
                avatarImageView.setImage(new Image(message.imagePath, 50, 50, true, true));
                container.setPadding(new Insets(0, 10, 0, 0));
                container.setStyle("-fx-background-color: #e6f2ff;");
                container.setAlignment(Pos.CENTER_RIGHT);
            } else {
                avatarImageView.setImage(new Image(message.imagePath, 50, 50, true, true));
                container.setPadding(new Insets(0, 10, 0, 10));
                container.setStyle("-fx-background-color: #e6ffee;");
                container.setAlignment(Pos.CENTER_LEFT);
            }
            messagesVBox.getChildren().add(container);
        });

    }
}
