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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ViagemController implements Initializable {

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<Lancamento, String> col_fim;

    @FXML
    private TableColumn<Lancamento, String> col_id;

    @FXML
    private TableColumn<Lancamento, String> col_inicio;

    @FXML
    private TableColumn<Lancamento, String> col_justificativa;

    @FXML
    private TableColumn<Lancamento, String> col_motivo;

    @FXML
    private TextField destino;

    @FXML
    private TextField fim;

    @FXML
    private TextField inicio;

    @FXML
    private TableView<Lancamento> table_lancamento;

    private ObservableList<Lancamento> tableData;

    @FXML
    void irLancar(ActionEvent event) {
        String destinoText = destino.getText();
        String fimText = fim.getText();
        String inicioText = inicio.getText();

        // Criar um objeto Lancamento com os dados fornecidos
        Lancamento lancamento = new Lancamento(destinoText, fimText, inicioText);

        // Adicionar o objeto Lancamento à lista observável
        tableData.add(lancamento);

        // Limpar os campos de entrada de texto
        destino.clear();
        fim.clear();
        inicio.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar as colunas da tabela
        col_justificativa.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestino()));
        col_inicio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInicio()));
        col_fim.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFim()));

        // Criar uma lista observável para armazenar os dados da tabela
        tableData = FXCollections.observableArrayList();

        // Atribuir a lista observável à tabela
        table_lancamento.setItems(tableData);
    }
    

    // Classe Lancamento que representa uma linha de dados
    private static class Lancamento {
        private final SimpleStringProperty destino;
        private final SimpleStringProperty fim;
        private final SimpleStringProperty inicio;

        public Lancamento(String destino, String fim, String inicio) {
            this.destino = new SimpleStringProperty(destino);
            this.fim = new SimpleStringProperty(fim);
            this.inicio = new SimpleStringProperty(inicio);
        }

        public String getDestino() {
            return destino.get();
        }

        public String getFim() {
            return fim.get();
        }

        public String getInicio() {
            return inicio.get();
        }

        public void setDestino(String novoDestino) {
            destino.set(novoDestino);
        }

        public void setFim(String novoFim) {
            fim.set(novoFim);
        }

        public void setInicio(String novoInicio) {
            inicio.set(novoInicio);
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
    void excluirLancamento(ActionEvent event) {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = (Lancamento) table_lancamento.getSelectionModel().getSelectedItem();
        

        // Exibir uma mensagem de confirmação para o usuário
       
      
            // Remover o objeto selecionado da lista observável
            tableData.remove(selecionado);

            // Limpar os campos de entrada de texto
            destino.clear();
            fim.clear();
            inicio.clear();

            
    }
   
    @FXML
    void atualizarLancamento(ActionEvent event) {
        // Verificar se um item da tabela está selecionado
        Lancamento selecionado = table_lancamento.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            // Nenhum item selecionado, exibir uma mensagem de erro ou aviso
            System.out.println("Nenhum item selecionado.");
            return;
        }

        // Obter os novos valores dos campos de entrada de texto
        String novoDestino = destino.getText();
        String novoFim = fim.getText();
        String novoInicio = inicio.getText();

        // Atualizar os valores do lançamento selecionado
        selecionado.setDestino(novoDestino);
        selecionado.setFim(novoFim);
        selecionado.setInicio(novoInicio);

        // Atualizar a tabela para refletir as alterações
        table_lancamento.refresh();

        // Limpar os campos de entrada de texto
        destino.clear();
        fim.clear();
        inicio.clear();
    }
   




}


