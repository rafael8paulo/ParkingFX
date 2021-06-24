package projetoestacionamento;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import projetoestacionamento.db.dal.ModelosDAL;
import projetoestacionamento.db.dal.ProprietarioDAL;
import projetoestacionamento.db.dal.VeiculoDAL;
import projetoestacionamento.db.entidade.Modelos;
import projetoestacionamento.db.entidade.Proprietario;
import projetoestacionamento.db.entidade.Veiculo;

public class FXMLCadVeiculoController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btGravar;
    @FXML
    private Button btNovo;
    @FXML
    private ComboBox<Modelos> cbModelos;
        
    @FXML
    private ComboBox<Proprietario> cbProp;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtCor;
    
    private ObservableList<Modelos> obsModelos;
    private ObservableList<Proprietario> obsProps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCbModelos("");
        carregarCbProp("");
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        
        Modelos nMod = cbModelos.getSelectionModel().getSelectedItem();
        Proprietario nProp = cbProp.getSelectionModel().getSelectedItem();        
        
        if(!txtCor.getText().isEmpty() && !txtPlaca.getText().isEmpty() && cbModelos.getSelectionModel().getSelectedItem() != null && cbProp.getSelectionModel().getSelectedItem() != null)
        {            
            Veiculo n = new Veiculo(0, txtPlaca.getText(), nMod.getMod_cod(), txtCor.getText(), nProp.getProp_cod());
            new VeiculoDAL().insert(n);
        }else{            
             JOptionPane.showMessageDialog(null,"Digite as informações obrigatorias para ser possivel a inclusão");
        }
        carregarCbModelos("");
        carregarCbProp("");
        
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
    }
    
    
    private void carregarCbModelos(String filtro){
        List<Modelos> modelos = new ModelosDAL().get(filtro);
        
        obsModelos = FXCollections.observableArrayList(modelos);
        
        cbModelos.setItems(obsModelos);                  
    }
    
    private void carregarCbProp(String filtro){
        List<Proprietario> props = new ProprietarioDAL().get(filtro);
        
        obsProps = FXCollections.observableArrayList(props);
        
        cbProp.setItems(obsProps);                  
    }

    @FXML
    private void evtKeyPressedMod(KeyEvent event) {                           
        String filtro = "mod_desc LIKE '%"+event.getText()+"';";
        carregarCbModelos(filtro);       
    }

    @FXML
    private void evtKeyPressedProp(KeyEvent event) {
        String filtro = "prop_nome LIKE '%"+event.getText()+"';";
        carregarCbProp(filtro);
    }
}
