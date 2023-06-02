import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViagemController {

    // ... declaração dos campos e métodos existentes ...

    private void cadastrarViagem() {
        String veiculo = veiculoTextField.getText();
        String origem = origemTextField.getText();
        String destino = destinoTextField.getText();

        // Estabelecer conexão com o banco de dados
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "usuario", "senha")) {

            // Preparar a declaração SQL para a inserção
            String sql = "INSERT INTO viagens (veiculo, origem, destino) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, veiculo);
            statement.setString(2, origem);
            statement.setString(3, destino);

            // Executar a inserção
            statement.executeUpdate();

            // Imprimir mensagem de sucesso
            System.out.println("Viagem cadastrada com sucesso!");

            // Limpar os campos após o cadastro
            veiculoTextField.clear();
            origemTextField.clear();
            destinoTextField.clear();

        } catch (SQLException e) {
            // Tratar qualquer erro ocorrido na conexão ou na execução da inserção
            e.printStackTrace();
        }
    }
}
