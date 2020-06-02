/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Gerenciamento.GerenciamentoUsuario;
import Objetos.Usuario;
import Telas.util.Validacoes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas Filipe
 */
public class TelaCadastroUsuarioController implements Initializable {

    @FXML
    private Button btEntrar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNomeMae;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidadeRes;
    @FXML
    private TextField txtUf;
    @FXML
    private TextField senha;
    @FXML
    private TextField confirmarSenha;
    @FXML
    private ChoiceBox<String> cbEsportePref;
    private ObservableList<String> obsListEsporte;
    @FXML
    private ChoiceBox<String> cbUnidadePref;
    private ObservableList<String> obsListUnidade;

    GerenciamentoUsuario gerUsuario = new GerenciamentoUsuario();
    @FXML
    private GridPane GPInformacoes;
    @FXML
    private Label LBLCPF;
    @FXML
    private Label LBLRG;
    @FXML
    private Label LBLEndereco;
    @FXML
    private Label LBLTelefone;
    @FXML
    private Label LBLEmail;
    @FXML
    private Label LBLEstadoCivil;
    @FXML
    private Label LBLNatal;
    @FXML
    private Label LBLGenero;
    @FXML
    private DatePicker dpDataNascimento;
    @FXML
    private Label LBLCPF2;
    @FXML
    private GridPane GPInformacoes1;
    @FXML
    private Label LBLCPF1;
    @FXML
    private Label LBLRG1;
    @FXML
    private Label LBLEndereco1;
    @FXML
    private Label LBLTelefone1;
    @FXML
    private Label LBLCPF21;
    @FXML
    private ChoiceBox<String> cbSexo;
    private ObservableList<String> obsListSexo;

    @FXML
    public void confirmarCadastro() throws Exception {
        Usuario usuario = new Usuario();

        boolean teste;
         {
            usuario.propNome = txtNome.getText();
            usuario.propSobrenome = txtSobrenome.getText();
            usuario.propEmail = txtEmail.getText();
           // usuario.propCidade = txtCidade.getText();
            //usuario.propPais = txtPais.getText();
            usuario.propCpf = txtCpf.getText();
           // Validacoes.validarCPF(usuario.propCpf);
            //usuario.propNomeMae = txtNomeMae.getText();
            usuario.propCep = txtCep.getText();
           // usuario.propEndereco = txtEndereco.getText();
            //usuario.propNumero = txtNumero.getText();
            //usuario.propComplemento = txtComplemento.getText();
            //usuario.propBairro = txtBairro.getText();
            //usuario.propCidadeRes = txtCidadeRes.getText();
            //usuario.propUf = txtUf.getText();
            usuario.propSenha = senha.getText();
            usuario.dtNascimento = dpDataNascimento.getValue();
            //usuario.propConfirmarSenha = confirmarSenha.getText();
            usuario.dtNascimento = dpDataNascimento.getValue();
            usuario.unidadePref = (String) cbUnidadePref.getValue();
            usuario.esportePref = (String) cbEsportePref.getValue();
            usuario.Sexo = cbSexo.getValue();
            //teste = Validacoes.validarCampoVazio(usuario.propNome, usuario.propEmail, usuario.propSenha);

        //} while (teste == true);
        gerUsuario.inserir(usuario);

        Stage stage = new Stage();

        Parent telaAluguel = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/TelaLogin.fxml"
                )
        );

        Scene scene = new Scene(telaAluguel);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage fecha = (Stage) btEntrar.getScene().getWindow();
        fecha.close();
        
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> listUnidade = new ArrayList<String>();
        listUnidade.add("BALNEARIO CARLOS JOEL NELLI");
        listUnidade.add("BALNEARIO GERALDO ALONSO");
        listUnidade.add("BALNEARIO JALISCO");
        listUnidade.add("BALNEARIO MARIO MORAES");
        listUnidade.add("BALNEARIO PRINCESA ISABEL");
        listUnidade.add("CEE ALFREDO IGNACIO TRINDADE");
        listUnidade.add("CEE AURELIO DE CAMPOS");
        listUnidade.add("CEE EDSON ARANTES DO NASCIMENTO");
        listUnidade.add("CEE FLAVIO CALABRESI CONTE");
        listUnidade.add("CEE GERALDO JOSE DE ALMEIDA");
        listUnidade.add("CEE JOERG BRUDER");
        listUnidade.add("CEE JOSE ERMIRIO DE MORAES");
        listUnidade.add("CEE LUIZ MARTINEZ");
        listUnidade.add("CEE MANE GARRINCHA");
        listUnidade.add("CEE OSWALDO BRANDAO");
        listUnidade.add("CEE RAUL TABAJARA");
        listUnidade.add("CEE RIYUSO OGAWA");
        listUnidade.add("CEE RUBENS PECCE LORDELLO");
        listUnidade.add("CEE SALIM FARAH MALUF");
        listUnidade.add("CEE SOLANGE NUNES BIBAS");
        listUnidade.add("CEE THOMAZ MAZZONI");
        listUnidade.add("CEE VICENTE ITALO FEOLA");
        listUnidade.add("CEL ANDRE VITAL RIBEIRO SOARES");
        listUnidade.add("CEL BRIGADEIRO EDUARDO GOMES");
        listUnidade.add("CEL BRIGADEIRO EDUARDO GOMES");
        listUnidade.add("CEL JOSE BONIFACIO");
        listUnidade.add("CEL JUSCELINO KUBITSCHEK");
        listUnidade.add("CEL MODELODROMO DO IBIRAPUERA");
        listUnidade.add("CEL PADRE  JOSE DE ANCHIETA");
        listUnidade.add("CEL PERUS");
        listUnidade.add("CEL TEOTONIO VILELA");
        listUnidade.add("CENTRO DE ESPORTES RADICAIS");
        listUnidade.add("CENTRO ESPORTIVO NAUTICO GUARAPIRANGA");
        listUnidade.add("CENTRO ESPORTIVO, RECREATIVO E EDUCATIVO DO TRABALHADOR");
        listUnidade.add("CENTRO ESPORTIVO TIETE");
        listUnidade.add("CENTRO OLIMPICO DE TREINAMENTO E PESQUISA");
        listUnidade.add("ESTADIO MUNICIPAL JACK MARIN");
        listUnidade.add("ESTADIO MUNICIPAL MIE NISHI");
        listUnidade.add("ESTADIO MUNICIPAL PAULO MACHADO DE CARVALHO");
        listUnidade.add("GINASIO ESPORTIVO DARCY REIS");
        listUnidade.add("MINI BALNEARIO IRMAOS PAOLILLO	");
        listUnidade.add("MINI BALNEARIO  MARECHAL ESPIRIDIAO ROSA");
        listUnidade.add("MINI BALNEARIO ANTONIO CARLOS DE ABREU SODRE");
        listUnidade.add("MINI BALNEARIO COMANDANTE GARCIA DAVILA");
        listUnidade.add("MINI BALNEARIO COMANDANTE GASTAO MOUTINHO");
        listUnidade.add("MINI BALNEARIO JOSE MARIA WHITAKER");
        listUnidade.add("MINI BALNEARIO MINISTRO SINESIO ROCHA");
        obsListUnidade = FXCollections.observableArrayList(listUnidade);
        cbUnidadePref.setItems(obsListUnidade);

        List<String> listEsporte = new ArrayList<String>();
        listEsporte.add("Futebol");
        listEsporte.add("Vôlei");
        listEsporte.add("Futsal");
        listEsporte.add("Tênis");
        listEsporte.add("Basquete");
        listEsporte.add("Atletismo");
        listEsporte.add("Tênis de mesa");
        listEsporte.add("Skate");
        listEsporte.add("Natação");
        listEsporte.add("Outros...");

        obsListEsporte = FXCollections.observableArrayList(listEsporte);
        cbEsportePref.setItems(obsListEsporte);

        List<String> listSexo = new ArrayList<String>();
        listSexo.add("Masculino");
        listSexo.add("Feminino");
        listSexo.add("Outros");
        obsListSexo = FXCollections.observableArrayList(listSexo);
        cbSexo.setItems(obsListSexo);

    }

    private void entrar(ActionEvent event) throws IOException {

    }
}
