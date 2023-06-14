package utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangeScene implements Initializable {

    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    
    public void irCadastroCR() {
    	changeScene("/view/Cr/CadastroCR.fxml");
    }
    
    public void irVisualizacaoCR() {
    	changeScene("/view/Cr/VisualizacaoCR.fxml");
    }

    public void irAtribuicaoCR() {
    	changeScene("/view/CrUsuario/GerenciamentoCRProjeto.fxml");
    }

    public void irCadastroUsuario() {
    	changeScene("/view/Usuario/CadastroUsuario.fxml");
    }
    
    public void irVisualizacaoUsuario() {
    	changeScene("/view/Usuario/VisualizacaoUsuario.fxml");
    }
    
    private void changeScene(String fxml) {
        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource(fxml));
            currentStage.getScene().setRoot(scene);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}