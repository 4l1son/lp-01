package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class LoginController {
    @FXML
    private Button btnField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField senhaField;

    @FXML
    private void irLogin(ActionEvent event) {
        var usuario = nomeField.getText();
        var senha = senhaField.getText();

        if (usuario.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Usuário não informado. Por favor, informe seu usuário!");
            alert.showAndWait();
        } else if (senha.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Senha não informada. Por favor, informe sua senha!");
            alert.showAndWait();
        } else {
            if (usuario.equals("teste") && senha.equals("teste")) {
                Stage currentStage = (Stage) btnField.getScene().getWindow();
                MenuController menuController = new MenuController();
                menuController.setStage(currentStage);
                menuController.irLogar();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Usuário ou senha inválidos. Tente novamente!");
                alert.showAndWait();
            }
        }
    }
}
