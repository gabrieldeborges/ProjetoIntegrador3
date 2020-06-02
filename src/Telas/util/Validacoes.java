/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas.util;

import java.util.InputMismatchException;
import javafx.scene.control.Alert;

/**
 *
 * @author Lucas Filipe
 */
public class Validacoes {

    public static boolean validarCampoVazio(String nome, String email, String senha) {

        if ((nome == null) || (email == null) || (senha == null)) {
            Alertas.mostrarAlertas("Erro", "Seus dados não foram inseridos por algum motivo",
                    "Tente novamente", Alert.AlertType.ERROR);
            return false;
        }
        else return true;
    }

    public static boolean validarCPF(String cpf) {
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int soma, i, r, numero, peso;

        try {

            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {

                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (soma * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            soma = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                soma = (int) (cpf.charAt(i) - 48);
                soma = soma + (soma * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    
    public static void validarDisponibilidade(){
    
        
    }
    
}
