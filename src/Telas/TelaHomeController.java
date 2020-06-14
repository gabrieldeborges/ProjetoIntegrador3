/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Gerenciamento.GerenciamentoOperacao;
import Gerenciamento.GerenciamentoUsuario;
import Objetos.Ativos;
import Telas.util.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import projetointegrador3.ProjetoIntegrador3;


/**
 * FXML Controller class
 *
 * @author Lucas Filipe
 */
public class TelaHomeController implements Initializable {

    public static int id = TelaLoginController.idPassa;
    
    
    private Button btConfirmar;
    @FXML
    private TableView<Ativos> tblAtivos;
    @FXML
    private Text lblBemVindo;
    @FXML
    private TableColumn<Ativos, String> colunaEspaço;
    @FXML
    private TableColumn<Ativos, String> tbunidade;
    @FXML
    private TableColumn<Ativos, String> colunaData;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        try {
           String bemV = lblBemVindo.getText();
            System.out.println(TelaLoginController.idPassa);
           bemV += " " + GerenciamentoUsuario.RetornaUser(TelaLoginController.idPassa)+ "!";
           lblBemVindo.setText(bemV);
        
           //try colacando botão dentro de table
           
            colunaEspaço.setCellValueFactory(
                new PropertyValueFactory("Espaco")
        );
        tbunidade.setCellValueFactory(
                new PropertyValueFactory("unidade")
        );
        
        colunaData.setCellValueFactory(
                new PropertyValueFactory("DataAluguel")
        );
     
         
           
        
            List resultados = GerenciamentoOperacao.listarPorUser(TelaLoginController.idPassa);

        //Se há resultados, atualiza a tabela
        if (resultados != null) {
            tblAtivos.setItems(
                    FXCollections.observableArrayList(resultados)
            );
        }

     
        } catch (Exception ex) {
            Logger.getLogger(TelaHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        teste();
    }    
    
    
    
    
    
    public static void setId(int ide){
        id = ide;
    }
    
    public void teste(){
        System.out.println("esta no controller"+id);
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
        
        Stage fecha = (Stage) btConfirmar.getScene().getWindow();
        fecha.close();
    }

    @FXML
    private void novoAluguel(MouseEvent event) throws IOException {
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
        stage.setTitle("Realizar novo aluguel");
  
        
    }

    private void testeacrtion(ActionEvent event) {
        teste();
    }
}
