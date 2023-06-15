package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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

public class VeiculoController implements Initializable {

    @FXML
    private TextField ano;

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<?, ?> col_ano;

    @FXML
    private TableColumn<?, ?> col_modelo;

    @FXML
    private TableColumn<?, ?> col_nome;

    @FXML
    private TextField modelo;

    @FXML
    private TextField nome;

    @FXML
    private TableView<?> table_lancamento;

    private ObservableList<Lancamento> tableData;
    
    @FXML
    void irLancar(ActionEvent event) {
    	String nomeText = nome.getText();
        String modeloText = modelo.getText();
        String anoText = ano.getText();

        // Criar um objeto Lancamento com os dados fornecidos
        Lancamento lancamento = new Lancamento(nomeText, modeloText, anoText);

        // Adicionar o objeto Lancamento à lista observável
        tableData.add(lancamento);

        // Limpar os campos de entrada de texto
        nome.clear();
        modelo.clear();
        ano.clear();

    }
    

	
	  private static class Lancamento {
	        private final SimpleStringProperty nome;
	        private final SimpleStringProperty modelo;
	        private final SimpleStringProperty ano;

	        public Lancamento(String nome, String modelo, String ano) {
	            this.nome = new SimpleStringProperty(nome);
	            this.modelo = new SimpleStringProperty(modelo);
	            this.ano = new SimpleStringProperty(ano);
	        }

	        public String getNome() {
	            return nome.get();
	        }

	        public String getModelo() {
	            return modelo.get();
	        }

	        public String getAno() {
	            return ano.get();
	        }
	    }
	  

		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			// Configurar as colunas da tabela
			col_nome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
	        col_modelo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModelo()));
	        col_ano.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAno()));
	        
	        // Criar uma lista observável para armazenar os dados da tabela
	        tableData = FXCollections.observableArrayList();

	        // Atribuir a lista observável à tabela
	        table_lancamento.setItems(tableData);
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
}
