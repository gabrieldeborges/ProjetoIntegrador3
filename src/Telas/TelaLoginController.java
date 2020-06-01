/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Gerenciamento.Exec;
import Gerenciamento.GerenciamentoUsuario;
import Telas.util.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable {
    
    public static int idPassa;
    
    @FXML
    private Button btEntrar;
    @FXML
    private Button btCadastrar;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Metodo para logar
    @FXML
    private void mudarTela() throws IOException {

        Stage stage = new Stage();

        Parent telaAluguel = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/TelaAluguel.fxml"
                )
        );

        Scene scene = new Scene(telaAluguel);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage fecha = (Stage) btEntrar.getScene().getWindow();
        fecha.close();
    }

    //Metodo para o botão de se cadastrar
    @FXML
    private void cadastrar() throws IOException {

        Stage stage = new Stage();

        Parent telaCadasto = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/CadastroUsuario.fxml"
                )
        );

        Scene scene = new Scene(telaCadasto);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage fecha = (Stage) btCadastrar.getScene().getWindow();
        fecha.close();

    }

    @FXML
    private void verificacaoUsuario() throws IOException, Exception {

        //Buscar no banco o usuario
        String user = txtUser.getText();
        String senha = pass.getText();

        int entrar = GerenciamentoUsuario.verificar_login(user, senha);
        
        if (entrar != -1) { 
                
             Parent telaCadastro = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/TelaLogin.fxml"
                )
        );
  
               
		idPassa = entrar;
                Exec.idUser = entrar;
		//TelaHomeController.setId(entrar);
                //System.out.println("to mandando"+entrar);
		          System.out.println("Tela 1 " + idPassa);
                 FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Telas/TelaHome.fxml"));
		Parent root = loader.load();
                
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
        Stage fecha = (Stage) btCadastrar.getScene().getWindow();
        fecha.close();
        
       
        
        
    }else{
            Alertas.mostrarAlertas("Usuário incorreto", "Usuário não encontrado!",
                 "Verifique o login e senha e tente novamente", Alert.AlertType.ERROR);
        }
}
}
