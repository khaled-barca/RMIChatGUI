package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    Stage stage;
    String imageURL;
    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button enterButton;

    @FXML
    private Button choosePCButton;

    @FXML
    private Rectangle backgroundRect;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enterButton.setOnAction(e -> openChat());
        imageURL = getClass().getResource("chat.png").toExternalForm();
        choosePCButton.setOnAction(e -> chooseImage());
        addGradient();
    }

    private void addGradient() {
        Color start = Color.rgb(179, 224, 255);
        Color end = Color.rgb(0, 138, 230);
        Stop[] stops = new Stop[]{new Stop(0, start), new Stop(1, end)};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        backgroundRect.setFill(lg);
    }

    private void chooseImage() {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(stage);
        imageURL = "file:" + selectedFile.getAbsolutePath();
        imageView.setImage(new Image(imageURL));
    }

    private void openChat() {
        if (isValidName()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
                Parent root = loader.load();
                ChatView view = loader.getController();
                //view.connect();
                view.setName(nameTextField.getText().trim());
                view.setImage(imageURL);
                view.setStage(stage);
                ClientImpl client = new ClientImpl();
                new ChatController(client, view);
                stage.setScene(new Scene(root, 600, 700));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid name");
            alert.setContentText("Please enter a valid name");
            alert.showAndWait();
        }

    }

    private boolean isValidName() {
        String name = nameTextField.getText().trim();
        return name.length() >= 2;
    }
}
