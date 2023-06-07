import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import teste.model.Veiculo;

public class VeiculoFormController {

    @FXML
    private TextField modeloField;

    @FXML
    private TextField marcaField;

    private Veiculo veiculo; // Instância da classe Veiculo para armazenar os dados preenchidos

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @FXML
    public void salvarVeiculo() {
        String modelo = modeloField.getText();
        String marca = marcaField.getText();

        veiculo.setModelo(modelo);
        veiculo.setMarca(marca);

        exibirMensagem("Veículo salvo com sucesso!");
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
