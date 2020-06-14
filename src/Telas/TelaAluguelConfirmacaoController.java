/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Gerenciamento.GerenciamentoOperacao;
import Gerenciamento.Unidade;
import Objetos.Operacao;
import Telas.util.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Lucas Filipe
 */
public class TelaAluguelConfirmacaoController implements Initializable {

    String tipoEquipamento;
    
    private ComboBox<String> cbUnidade;
    private ObservableList<String> obsListUnidade;
    @FXML
    private DatePicker dtData;
    @FXML
    private Button btConfirmar;

    GerenciamentoOperacao gerenOperacao = new GerenciamentoOperacao();
    @FXML
    private TableColumn<Unidade, String> colunaUnidade;
    @FXML
    private TableColumn<Unidade, String> colunaBairro;
    @FXML
    private TableColumn<Unidade, Integer> colunaAvaliacao;
    @FXML
    private TableColumn<Unidade, String> distancia;
    @FXML
    private TableView<Unidade> tblUnidades;
    @FXML
    private TextField txtCEPMANDA;
    @FXML
    private TableColumn<Unidade, Integer> colunaID;

    public void confirmacao() throws Exception {
   
    }

    
    public void setTipo(String tipo){
        
        tipoEquipamento = tipo;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         colunaUnidade.setCellValueFactory(
                new PropertyValueFactory("Unidade")
        );
        colunaBairro.setCellValueFactory(
                new PropertyValueFactory("Bairro")
        );
        
        colunaAvaliacao.setCellValueFactory(
                new PropertyValueFactory("avaliacao")
        );
        distancia.setCellValueFactory(
                new PropertyValueFactory("distancia")
        );
        colunaID.setCellValueFactory(
                new PropertyValueFactory("id")
        );
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
    private void buscarDistancia(ActionEvent event) throws Exception {
        tblUnidades.getItems().clear();

        //Obtém os resultados de pesquisa do mock
        
        if (txtCEPMANDA.getText()!=null ){
        
            List resultados = null;

        // Carrega o FXML
        FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("/Telas/SplashCarregar.fxml"));
        StackPane splashPane = splashLoader.load();

        // Cria a Janela do Splash
        // Define como transparente para que não apareça decoração de janela (maximizar, minimizar)
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        final Scene scene = new Scene(splashPane);
        scene.setFill(Color.TRANSPARENT); // Define que a cor do painel root seja transparente para que dê o efeito de sombra
        splashStage.setScene(scene);

        // Cria o serviço para rodar alguma tarefa em background enquanto o splash é mostrado (no caso somente um delay de 10s)
        Service<Boolean> splashService = new Service<Boolean>() {

            // Mostra o splash quando o serviço for iniciado
            @Override
            public void start() {
                super.start();
                splashStage.show(); // mostra a janela
            }

            // Simulação de uma tarefa pesada 
            @Override
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        List resultados = GerenciamentoOperacao.listar(txtCEPMANDA.getText(), "PISCINA");
                         //Se há resultados, atualiza a tabela
                         if (resultados != null) {
                         tblUnidades.setItems(
                        FXCollections.observableArrayList(resultados)
                    
                    
            );
        }else{
             Alertas.mostrarAlertas("Erro", "CEP não preenchido",
                 "Favor preencher o campo e realizar a busca novamente", Alert.AlertType.ERROR);
        
        
        }
                 
                        return true;
                    }
                };
            }

            // Quando a tarefa for finalizada fecha o splash e mostra a tela principal
            @Override
            protected void succeeded() {
                splashStage.close();  // Fecha o splash
                try {
                    // Chama a tela principal
                } catch (Exception ex) {
                }
            }
        };

        splashService.start();

       
        
    }
    }
    @FXML
    private void realizaAluguel(ActionEvent event) throws Exception {
             Operacao operacao = new Operacao();
        
        Unidade unidades = tblUnidades.getSelectionModel().getSelectedItem();
        
        if(unidades != null && dtData.getValue()!= null){
            
            
        operacao.iduser = TelaLoginController.idPassa;
        operacao.idEquip = unidades.getId();
        operacao.data = dtData.getValue();
        GerenciamentoOperacao.AdicionaAluguel(operacao);
        Alertas.mostrarAlertas("Parabéns!", "Aluguel foi feito com sucesso!",
                 "Sua unidade foi alugada e estará disponível no dia "+dtData.getValue()+ " Favor trazer os seus documentos atualizados para a utilização do espaço.", Alert.AlertType.CONFIRMATION);
       
        }
        else{
             Alertas.mostrarAlertas("Alerta", "Informações não preenchidas",
                 "Por favor selecione uma unidade na tabela e selcione uma data!", Alert.AlertType.INFORMATION);
        }
        
        
    }
}
