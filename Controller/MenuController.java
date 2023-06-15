package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    @FXML
    private Label homeText;

    private static Stage currentStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeText.setText("Bem-vindo!"); // Inicializa o texto do rótulo
    }

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    void irCadastroViagem(MouseEvent event) {
        changeScene("/crud/view/cadastros/viagem.fxml");
    }

    @FXML
    void irCadastroPessoa(MouseEvent event) {
        changeScene("/crud/view/cadastros/pessoa.fxml");
    }

    @FXML
    public void irCadastroVeiculo(MouseEvent event) {
        changeScene("/crud/view/cadastros/veiculo.fxml");
    }

    void changeScene(String fxml) {
        Parent scene;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            scene = loader.load();

            // Obtém o controlador da nova cena
            Initializable controller = loader.getController();
            if (controller instanceof MenuController) {
                ((MenuController) controller).setStage(currentStage);
            }

            if (currentStage != null) {
                currentStage.setScene(new Scene(scene));
                currentStage.show();
            } else {
                System.err.println("Stage is not set.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void irLogar() {
        // Obtém o estágio atual
        Stage stage = (Stage) homeText.getScene().getWindow();

        if (stage != null) {
            setStage(stage);
            changeScene("/crud/view/menu.fxml");
        } else {
            System.err.println("Stage is not set.");
        }
    }

    @FXML
    public void irPagina1(MouseEvent event) {
        // Obtém o estágio atual
        Stage stage = (Stage) homeText.getScene().getWindow();

        if (stage != null) {
            setStage(stage);
            changeScene("/crud/view/pagina1.fxml");
        } else {
            System.err.println("Stage is not set.");
        }
    }

    @FXML
    public void irPagina2(MouseEvent event) {
        // Obtém o estágio atual
        Stage stage = (Stage) homeText.getScene().getWindow();

        if (stage != null) {
            setStage(stage);
            changeScene("/crud/view/pagina2.fxml");
        } else {
            System.err.println("Stage is not set.");
        }
    }
}
