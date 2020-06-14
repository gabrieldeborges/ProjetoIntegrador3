package projetointegrador3;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ProjetoIntegrador3 extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent telaCadastro = FXMLLoader.load(
                getClass().getResource(
                        "/Telas/TelaLogin.fxml"
                )
        );
  
     Scene scene = new Scene(telaCadastro);
        stage.setScene(scene);
        stage.setTitle("Tela Cliente");
       
        stage.setResizable(false);
        
        stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
