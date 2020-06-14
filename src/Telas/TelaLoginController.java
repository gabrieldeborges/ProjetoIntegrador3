/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Gerenciamento.Exec;
import static Gerenciamento.GerenciamentoOperacao.verificaFeedback;
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
    
    public static int idEquip;
    
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
        //entra nesse if se usuario existir
        if (entrar != -1) { 
                
            
            int equip = verificaFeedback(entrar);

            if(equip != -1){
                idEquip = equip;
		idPassa = entrar;
               // Exec.idUser = entrar;
		//TelaHomeController.setId(entrar);
                     
                
                FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/Telas/TelaFeedback.fxml"));
		Parent root1 = loader2.load();
                
		Stage stage2 = new Stage();
		Scene scene2 = new Scene(root1);
                stage2.setResizable(false);
                stage2.setTitle("Avaliação");
		stage2.setScene(scene2);
		stage2.show();
                
                
                
        Stage fecha = (Stage) btEntrar.getScene().getWindow();
        fecha.close();
    
                }else{ 
        
		idPassa = entrar;
               // Exec.idUser = entrar;
		//TelaHomeController.setId(entrar);
                //System.out.println("to mandando"+entrar);
		          System.out.println("Tela 1 " + idPassa);
                 FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Telas/TelaHome.fxml"));
		Parent root = loader.load();
                
		Stage stage = new Stage();
		Scene scene = new Scene(root);
                stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
        Stage fecha = (Stage) btCadastrar.getScene().getWindow();
        fecha.close();
        
      
            }
            }else{ Alertas.mostrarAlertas("Usuário incorreto", "Usuário não encontrado!",
                             "Verifique o login e senha e tente novamente", Alert.AlertType.ERROR);
            
           }
            
}
}
