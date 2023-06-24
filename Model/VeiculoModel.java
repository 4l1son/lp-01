package Model;

import javafx.beans.property.SimpleStringProperty;

public class VeiculoModel {
        private final SimpleStringProperty nome;
        private final SimpleStringProperty porte;
        private final SimpleStringProperty ano;

        public VeiculoModel(String nome, String porte, String ano) {
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

