package Telas.util;

import javafx.scene.control.TextField;

public class Restricoes {

    public static void setTextInt(TextField txt) {
        txt.textProperty().addListener((obs, valorAntigo, novoValor) -> {
            if (novoValor != null && !novoValor.matches("\\d*")) {
                txt.setText(valorAntigo);
            }
        });
    }

    public static void setTextLength(TextField txt, int max) {
        txt.textProperty().addListener((obs, valorAntigo, novoValor) -> {
            if (novoValor != null && novoValor.length() > max) {
                txt.setText(valorAntigo);
            }
        });
    }

    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, valorAntigo, novoValor) -> {
            if (novoValor != null && !novoValor.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(valorAntigo);
            }
        });
    }
}
