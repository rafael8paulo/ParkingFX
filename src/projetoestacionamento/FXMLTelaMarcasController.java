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
import projetoestacionamento.db.dal.MarcasDAL;
import projetoestacionamento.db.entidade.Marcas;

public class FXMLTelaMarcasController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btPesquisar;
    @FXML
    private Button btNovo;
    @FXML
    private TableView<Marcas> tabela;
    @FXML
    private TableColumn<Marcas, Integer> colCod;
    @FXML
    private TableColumn<Marcas, String> colNome;

    public static Marcas mar = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory("mar_cod"));
        colNome.setCellValueFactory(new PropertyValueFactory("mar_desc"));
        carregarTabela("");
    }    
    
    private void carregarTabela(String filtro){
        List<Marcas> marcas = new MarcasDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(marcas));        
    }
    
    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtPesquisar(ActionEvent event) {
        if(!txtPesquisa.getText().isEmpty())
        {                        
            carregarTabela(" mar_desc LIKE '%"+txtPesquisa.getText()+"%';");
        }else{
            carregarTabela("");
        }
    }

    @FXML
    private void evtBtNovo(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLCadMarcas.fxml")));
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
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atenção");
            alert.setContentText("Você realmente deseja apagar essa Marca?");
            if(alert.showAndWait().get()==ButtonType.OK)
            {  
               Marcas n = tabela.getSelectionModel().getSelectedItem();               
               new MarcasDAL().delete(n);
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
            telaAlterar();
        
        
    }

    private void telaAlterar() throws IOException{
        mar = tabela.getSelectionModel().getSelectedItem();
                
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLConsultarMarcas.fxml")));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
            
        mar = null;
        carregarTabela("");
    }
}
