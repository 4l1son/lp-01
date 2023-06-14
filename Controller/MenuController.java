package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    @FXML
    private Label homeText;
    
    private Stage currentStage; // Utilize a referência para o Stage fornecida externamente
    
    public void setStage(Stage stage) {
        this.currentStage = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            
            currentStage.setScene(new Scene(scene));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void irLogar() {
        if (currentStage != null) {
            changeScene("/crud/view/menu.fxml");
        } else {
            System.err.println("Stage is not set.");
        }
    }
}
