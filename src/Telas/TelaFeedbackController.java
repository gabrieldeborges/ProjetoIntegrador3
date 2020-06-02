package Telas;

import static Gerenciamento.GerenciamentoOperacao.mandaAvaliacao;
import static Gerenciamento.GerenciamentoOperacao.trazModalidade;
import static Telas.TelaLoginController.idPassa;
import Telas.util.Alertas;
import com.sun.javafx.css.Style;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.plaf.ColorUIResource;
/**
 * FXML Controller class
 *
 * @author Lucas Filipe
 */
public class TelaFeedbackController implements Initializable {

    int avalia = 0;
    
    private Button btEntrar;
    @FXML
    private Text lblVindo;
    @FXML
    private Text lblTipoLocal;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
     @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button btnEntrar;
    @FXML
    private Text retornaDia;
    @FXML
    private Label trazModalidade;
    
    public void salvarFeedbackRuim() {
        Alertas.mostrarAlertas("Feedback salvo", "Pedimos desculpa, estamos buscando melhorar nossos serviços!",
                 "Agradecemos e volte sempre!", Alert.AlertType.CONFIRMATION);
    }
    
    public void salvarFeedbackNormal() {
        Alertas.mostrarAlertas("Feedback salvo", "Estamos buscando melhorar nossos serviços!",
                 "Agradecemos e volte sempre!", Alert.AlertType.CONFIRMATION);
          
    }
    
    public void salvarFeedbackBom() {
        Alertas.mostrarAlertas("Feedback salvo", "Ficamos felizes que gostou!",
                 "Agradecemos e volte sempre!", Alert.AlertType.CONFIRMATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            trazModalidade.setText(trazModalidade(TelaLoginController.idEquip));
            
            
        } catch (Exception ex) {
            Logger.getLogger(TelaFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

     @FXML
    private void entrar(ActionEvent event) throws IOException, Exception {
        
         mandaAvaliacao(TelaLoginController.idEquip, avalia);
         
             
                 FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Telas/TelaHome.fxml"));
		Parent root = loader.load();
                
		Stage stage = new Stage();
		Scene scene = new Scene(root);
                stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
                Stage fecha = (Stage) btnEntrar.getScene().getWindow();
                fecha.close();
    }

    @FXML
    private void trocaCor1(MouseEvent event) {
        
    }

    @FXML
    private void guardaCor1(ActionEvent event) {
       avalia = 1;
    }

    @FXML
    private void trocaCor2(MouseEvent event) {
    }

    @FXML
    private void guardaCor2(ActionEvent event) {
        avalia = 2;
        
    }

    @FXML
    private void trocaCor3(MouseEvent event) {
        
    }

    @FXML
    private void guardaCor3(ActionEvent event) {
        avalia = 3;
    }

    @FXML
    private void trocaCor4(MouseEvent event) {
    }

    @FXML
    private void guardaCor4(ActionEvent event) {
        avalia = 4;
    }

    @FXML
    private void trocaCor5(MouseEvent event) {
    }

    @FXML
    private void guardaCor5(ActionEvent event) {
        avalia = 5;
    }
}
