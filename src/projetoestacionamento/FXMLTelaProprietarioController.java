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
import projetoestacionamento.db.dal.ProprietarioDAL;
import projetoestacionamento.db.entidade.Proprietario;


public class FXMLTelaProprietarioController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btPesquisar;
    @FXML
    private Button btNovo;
    @FXML
    private TableView<Proprietario> tabela;
    @FXML
    private TableColumn<Proprietario, Integer> colId;
    @FXML
    private TableColumn<Proprietario, String> colNome;
    @FXML
    private TableColumn<Proprietario, String> colCpf;
    @FXML
    private TableColumn<Proprietario, String> colEnd;
    @FXML
    private TableColumn<Proprietario, String> colTel;
    @FXML
    private TableColumn<Proprietario, String> colEmail;    
    @FXML
    private TableColumn<Proprietario, String> colBairro;
    
    public static Proprietario prop = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("prop_cod"));
        colCpf.setCellValueFactory(new PropertyValueFactory("prop_cpf"));
        colNome.setCellValueFactory(new PropertyValueFactory("prop_nome"));
        colEnd.setCellValueFactory(new PropertyValueFactory("prop_rua"));
        colBairro.setCellValueFactory(new PropertyValueFactory("prop_bairro"));
        colTel.setCellValueFactory(new PropertyValueFactory("prop_fone"));
        colEmail.setCellValueFactory(new PropertyValueFactory("prop_email"));
        carregarTabela("");        
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtTxtPesquisa(ActionEvent event) {                       
    }

    @FXML
    private void evtBtPesquisar(ActionEvent event) {
        if(!txtPesquisa.getText().isEmpty())
        {                        
            carregarTabela(" prop_nome LIKE '%"+txtPesquisa.getText()+"%';");
        }else{
            carregarTabela("");
        }
    }

    @FXML
    private void evtBtNovo(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLCadProprietario.fxml")));
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atenção");
            alert.setContentText("Você realmente deseja apagar esse Registro?");
            if(alert.showAndWait().get()==ButtonType.OK)
            {  
               Proprietario n = tabela.getSelectionModel().getSelectedItem();               
               new ProprietarioDAL().delete(n);
            }
        } 
        carregarTabela("");
        
    }

    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        telaAlterar();        
    }

    @FXML
    private void evtCliqueTabela(MouseEvent event) throws IOException {
                
        if (event.getClickCount()==2)            
        {   
            telaAlterar();
        }
        
    }
    
    
    
    private void carregarTabela(String filtro){
        
        List<Proprietario> proprietario = new ProprietarioDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(proprietario));      
    }
    
    private void telaAlterar() throws IOException{
        prop = tabela.getSelectionModel().getSelectedItem();
                
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLConsultarProprietario.fxml")));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
            
        prop = null;
        carregarTabela("");
    }
    
}
