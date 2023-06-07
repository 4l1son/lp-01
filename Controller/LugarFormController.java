import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.model.Lugar;

public class LugarFormController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField enderecoField;

    private Lugar lugar; // Instância da classe Lugar para armazenar os dados preenchidos

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    @FXML
    public void salvarLugar() {
        String nome = nomeField.getText();
        String endereco = enderecoField.getText();

        lugar.setNome(nome);
        lugar.setEndereco(endereco);

        exibirMensagem("Lugar salvo com sucesso!");
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
