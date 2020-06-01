/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Objetos.DetalheObj;
import Telas.util.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 *
 * @author Lucas Filipe
 */
public class TelaAluguelController implements Initializable {

      DetalheObj d = new DetalheObj();
       
      
      public static String modalidade;
    
    @FXML
    private Button btCampoFut;
    @FXML
    private Button btEspacoGate;
    @FXML
    private Button btPiscina;
    @FXML
    private Button btEspacoPet;
    @FXML
    private Button btCampoSociety;
    @FXML
    private Button btPistaSkt;
    @FXML
    private Button btQuadra;

      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void entrar(ActionEvent event) throws IOException {
        
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
        
        Stage fecha = (Stage) btQuadra.getScene().getWindow();
        fecha.close();
    }

    @FXML
    private void DetalheCampo(MouseEvent event) throws IOException {
      
       
    }

   
    

    
    @FXML
    private void DetalheGateball(KeyEvent event) {
        
    }

    @FXML
    private void DetalhePiscina(MouseEvent event) {
    }

    @FXML
    private void DetalhePet(MouseEvent event) {
    }

    @FXML
    private void DetalheSociety(MouseEvent event) {
        
    }

    @FXML
    private void DetalheSkate(MouseEvent event) {
        
    }

    @FXML
    private void DetalheQuadra(MouseEvent event) {
        
    }

    public DetalheObj getObjeto() {
      System.out.println(d.getCaminho_imag());
      return d; //To change body of generated methods, choose Tools | Templates.
        
    
    }

    private void DetalheCampo(KeyEvent event) {

        
    }

    @FXML
    private void abrirDetalheCampo(MouseEvent event) throws IOException {
        
               
       d.setTexto("Campos de futebol da prefeitura disponiveis para você jogar uma pealda com a galera!"); 
       
       d.setCaminho_imag("/Imagens/unnamed.jpg");
       
       d.setLocal1("ETEC");
       d.setLocal2("ESCOLA");
       d.setLocal3("OLKOK");
       d.setLocal4("Não seiii");
     
      
         Stage stage = new Stage();
       
       Parent detalhesTela = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/Detalhe.fxml"
                )
        );
        
        Scene scene = new Scene(detalhesTela);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
         
        TelaDetalhesController c = new TelaDetalhesController();
        c.PopularTela(d);
       
       }

    @FXML
    private void AluguelCampo(ActionEvent event) throws IOException {
         Stage stage = new Stage();
       
       Parent confirma = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/TelaAluguelConfirmacao.fxml"
                )
        );
        
       
        modalidade = "CAMPO DE FUTEBOL";

        Scene scene = new Scene(confirma);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        
    }

    @FXML
    private void AluguelGateball(ActionEvent event) {
    }
    

               
             
}
