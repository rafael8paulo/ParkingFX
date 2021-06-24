package projetoestacionamento;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projetoestacionamento.db.dal.ModelosDAL;
import projetoestacionamento.db.entidade.Marcas;
import projetoestacionamento.db.entidade.Modelos;


public class FXMLTelaModelosController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btPesquisar;
    @FXML
    private Button btNovo;
    @FXML
    private TableView<Modelos> tabela;
    @FXML
    private TableColumn<Modelos, Integer> colIdMod;
    @FXML
    private TableColumn<Modelos, String> colNomeMod;
    @FXML
    private TableColumn<Marcas, Integer> colIdMar;

    public static Modelos mod = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIdMod.setCellValueFactory(new PropertyValueFactory("mod_cod"));
        colNomeMod.setCellValueFactory(new PropertyValueFactory("mod_desc"));
        colIdMar.setCellValueFactory(new PropertyValueFactory("mar_cod"));

        carregarTabela("");
    }    

    private void carregarTabela(String filtro){
        
        List<Modelos> modelos = new ModelosDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(modelos));                          
    }
    
    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }


    @FXML
    private void evtBtPesquisar(ActionEvent event) {
        
        if(!txtPesquisa.getText().isEmpty())
        {                        
            carregarTabela(" mod_desc LIKE '%"+txtPesquisa.getText()+"%';");
        }else{
            carregarTabela("");
        }
        
    }

    @FXML
    private void evtBtNovo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLCadModelos.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        carregarTabela("");
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        
        if (tabela.getSelectionModel().getSelectedIndex()>=0)
        {
            Alert alert =new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Atenção");
            alert.setContentText("Você realmente deseja apagar esse Modelo?");
            if(alert.showAndWait().get()==ButtonType.OK)
            {  
               Modelos n = tabela.getSelectionModel().getSelectedItem();               
               new ModelosDAL().delete(n);
            }
        } 
        carregarTabela("");
        
    }

    @FXML
    private void evtCliqueTabela(MouseEvent event) throws IOException {
                       
        if (event.getClickCount()==2)          
            telaAlterar();                        
    }

    @FXML
    private void evtTxtPesquisa(ActionEvent event) {
    }

    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        telaAlterar();
    }
    private void telaAlterar() throws IOException{
        mod = tabela.getSelectionModel().getSelectedItem();
                
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLConsultarModelos.fxml")));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
            
        mod = null;
        carregarTabela("");
    }
}
