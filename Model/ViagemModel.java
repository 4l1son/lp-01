package Model;

import javafx.beans.property.SimpleStringProperty;

public class ViagemModel {
        private final SimpleStringProperty inicio;
        private final SimpleStringProperty fim;
        private final SimpleStringProperty destino;

        public ViagemModel(String inicio, String fim, String destino) {
            this.inicio = new SimpleStringProperty(inicio);
            this.fim = new SimpleStringProperty(fim);
            this.destino = new SimpleStringProperty(destino);
        }

        public String getInicio() {
            return inicio.get();
        }

        public String getFim() {
            return fim.get();
        }

        public String getDestino() {
            return destino.get();
        }

        public void setInicio(String inicioText) {
            inicio.set(inicioText);
        }

        public void setFim(String fimText) {
            fim.set(fimText);
        }

        public void setDestino(String destinoText) {
            destino.set(destinoText);
        }
    }
