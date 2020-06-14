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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

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
        //dtData.se
        
        
         colunaUnidade.setCellValueFactory(
                new PropertyValueFactory("Unidade")
        );
        colunaBairro.setCellValueFactory(
                new PropertyValueFactory("Bairro")
        );
        
             colunaAvaliacao.setCellFactory(t -> new TableCell<Unidade, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Unidade b = ((Unidade) getTableRow().getItem()); // Pega o livro da linha
                    System.out.println("it" + item);
                    System.out.println("|B " + b);
                    Rating r = null;
                    if (item == null && b == null) {
                        r = makeRating(0);
                    } else {
                        r = makeRating(item == null
                                ? b.getAvaliacao().doubleValue() // Atualiza o Rating com o valor do livro inicialmente
                                : item); // Atualiza o Rating com o valor do evento
                    }

                    r.ratingProperty().addListener((v, o, n) -> {
                        // Listner para atualizar a avaliação do livro quando o Rating for altarado 
                        b.setAvaliacao(n.doubleValue());

                        // Seleciona a linha que está sendo alterada
                        tblUnidades.getSelectionModel().select(getTableRow().getIndex());

                        // Aqui deve ser definida a ação de salvar o livro com o valor atualizado no BD
                        System.out.println("SALVAR NO BD --> " + b.toString());
                    });
                    // Mostra o Rating na coluna
                    setGraphic(r);
                }
            }

        });
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
   
        if (txtCEPMANDA.getText().equals(null) && TelaAluguelController.modalidade != null){
        
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
                        List resultados = GerenciamentoOperacao.listar(txtCEPMANDA.getText(), TelaAluguelController.modalidade);
                         //Se há resultados, atualiza a tabela
                         if (resultados != null) {
                         tblUnidades.setItems(
                        FXCollections.observableArrayList(resultados)
                    
           );
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

       
        
    }else{
             Alertas.mostrarAlertas("Erro", "CEP não preenchido",
                 "Favor preencher o campo e realizar a busca novamente", Alert.AlertType.ERROR);
        
        
        }
    }
    
        public static Rating makeRating(double rate) {
        Rating rating = new Rating();
        rating.setOrientation(Orientation.HORIZONTAL);
        rating.setUpdateOnHover(false);
        rating.setPartialRating(false);
        rating.autosize();
        //rating.setDisable(true);
        //rating.setDisable(true);
        
        rating.setRating(rate);
        return rating;

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
       
        
         Stage fecha = (Stage) btConfirmar.getScene().getWindow();
        fecha.close();
        
        
        }
        else{
             Alertas.mostrarAlertas("Alerta", "Informações não preenchidas",
                 "Por favor selecione uma unidade na tabela e selcione uma data!", Alert.AlertType.INFORMATION);
        }
        
        
    }
}
