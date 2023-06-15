
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.MenuController;
import utils.ChangeScene;

public class main extends Application {
    @Override
    public void start(Stage stage) {
        Parent root = null;

        var resource = getClass().getResource("/crud/view/menu.fxml");

        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1000, 609);
        stage.setScene(scene);

        MenuController.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
