package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Conexao.Factory;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import DAO.VeiculoDAO;
import DAO.ViagemDAO;
public class VeiculoController implements Initializable {
    @FXML
    private TextField nome;

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<Lancamento, String> col_nome;

    @FXML
    private TableColumn<Lancamento, String> col_id;

    @FXML
    private TableColumn<Lancamento, String> col_ano;

    @FXML
    private TableColumn<Lancamento, String> col_porte;

    @FXML
    private TextField porte;

    @FXML
    private TextField ano;

    @FXML
    private TableView<Lancamento> table_lancamento;

    private ObservableList<Lancamento> tableData;

    private Connection con;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Factory factory = new Factory();
        con = factory.getConnection();

        // Configurar as colunas da tabela
        col_nome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        col_porte.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPorte()));
        col_ano.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAno()));

        // Criar uma lista observável para armazenar os dados da tabela
        tableData = FXCollections.observableArrayList();

        try {
            // Carregar os dados do banco para a lista observável
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM veiculo");
            while (rs.next()) {
                String nomeText = rs.getString("nome");
                String porteText = rs.getString("porte");
                String anoText = rs.getString("ano");
                Lancamento lancamento = new Lancamento(nomeText, porteText, anoText);
                tableData.add(lancamento);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao carregar lançamentos: " + e.getMessage());
        }

        // Atribuir a lista observável à tabela
        table_lancamento.setItems(tableData);
    }

    // Classe Lancamento que representa uma linha de dados
    private static class Lancamento {
        private final SimpleStringProperty nome;
        private final SimpleStringProperty porte;
        private final SimpleStringProperty ano;

        public Lancamento(String nome, String porte, String ano) {
            this.nome = new SimpleStringProperty(nome);
            this.porte = new SimpleStringProperty(porte);
            this.ano = new SimpleStringProperty(ano);
        }

        public String getNome() {
            return nome.get();
        }

        public String getPorte() {
            return porte.get();
        }

        public String getAno() {
            return ano.get();
        }

        public void setNome(String nomeText) {
            nome.set(nomeText);
        }

        public void setPorte(String porteText) {
            porte.set(porteText);
        }

        public void setAno(String anoText) {
            ano.set(anoText);
        }
    }

    @FXML
    public void irMenu() {
        // Carregar o arquivo FXML do menu
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/crud/view/menu.fxml"));
            Parent root = loader.load();

            // Obter o controlador do menu
            MenuController menuController = loader.getController();

            // Obter o estágio atual
            Stage stage = (Stage) btn_lancar.getScene().getWindow();

            // Configurar o estágio e exibir o menu
            if (stage != null) {
                menuController.setStage(stage);
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.err.println("Stage is not set.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    void irLancar(ActionEvent event) throws SQLException {
        String nomeText = nome.getText();
        String porteText = porte.getText();
        String anoText = ano.getText();

        VeiculoDAO veiculo = new VeiculoDAO(con);
		 
		veiculo.salvar(nomeText, porteText, anoText);;

		// Criar um objeto Lancamento com os dados fornecidos
		Lancamento lancamento = new Lancamento(nomeText, porteText, anoText);
		
		// Adicionar o objeto Lancamento à lista observável
		tableData.add(lancamento);

		// Limpar os campos de entrada de texto
		nome.clear();
		porte.clear();
		ano.clear();
    }

    


    
    @FXML
    void excluirLancamento(ActionEvent event) throws SQLException {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            // Nenhum item selecionado, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhum item selecionado.");
            return;
        }

        VeiculoDAO veiculo = new VeiculoDAO(con);
		veiculo.excluir(selecionado.getAno());

		// Remover o objeto selecionado da lista observável
		tableData.remove(selecionado);

		// Limpar os campos de entrada de texto
		nome.clear();
		porte.clear();
		ano.clear();
    }

    @FXML
    void atualizarLancamento(ActionEvent event) throws SQLException {
        // Verificar se uma pessoa da tabela está selecionada
        Lancamento selecionada = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            // Nenhuma pessoa selecionada, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhuma pessoa selecionada.");
            return;
        }

        // Obter os novos valores dos campos de entrada de texto
		String novoNome = nome.getText();
		String novoAno = ano.getText();
		String novoPorte = porte.getText();
		VeiculoDAO veiculo = new VeiculoDAO(con);


		// Atualizar os valores da pessoa selecionada na tabela
		selecionada.setNome(novoNome);
		selecionada.setAno(novoAno);
		selecionada.setPorte(novoPorte);

		// Atualizar a tabela para refletir as alterações
		table_lancamento.refresh();

		// Limpar os campos de entrada de texto
		nome.clear();
		ano.clear();
		porte.clear();
    }

}
