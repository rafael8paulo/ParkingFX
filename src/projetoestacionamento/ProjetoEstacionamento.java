package projetoestacionamento;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projetoestacionamento.db.util.Banco;

public class ProjetoEstacionamento extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if(!Banco.conectar())
        {
            System.out.println("Problemas ao acessar o banco "+Banco.getCon().getMensagemErro());
            Platform.exit();
        }
        else
            launch(args);
    }
    
}
