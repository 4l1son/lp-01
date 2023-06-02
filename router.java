import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mostrarTelaAgendamentoViagem();
        primaryStage.show();
    }

    public void mostrarTelaAgendamentoViagem() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Agendamento_para_viagem.fxml"));
        primaryStage.setScene(new Scene(root));
    }

    public void mostrarTelaCadastroPessoa() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Cadastro_pessoa.fxml"));
        primaryStage.setScene(new Scene(root));
    }

    public void mostrarTelaCadastroViagem() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Cadastro_viagem.fxml"));
        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
