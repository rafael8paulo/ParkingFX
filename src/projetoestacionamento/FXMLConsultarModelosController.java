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
import static projetoestacionamento.FXMLTelaModelosController.mod;
import projetoestacionamento.db.dal.MarcasDAL;
import projetoestacionamento.db.dal.ModelosDAL;
import projetoestacionamento.db.entidade.Marcas;

public class FXMLConsultarModelosController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtDesc;
    @FXML
    private ComboBox<Marcas> cbMarcas;
    @FXML
    private TextField txtCodigo;
    
    private ObservableList<Marcas> obsMarcas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mod = FXMLTelaModelosController.mod;        
        Marcas mar =  new MarcasDAL().get(mod.getMar_cod());
        
        txtCodigo.setText(String.valueOf(mod.getMar_cod()));
        txtDesc.setText(mod.getMod_desc());        
        bloquearCampos();   
        
        carregarCbMar("");
        cbMarcas.setValue(mar);
    }    
    
    
    
    public void bloquearCampos()
    {
        txtDesc.setDisable(true);   
        txtCodigo.setDisable(true);   
        cbMarcas.setDisable(true);
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtbtAlterar(ActionEvent event) {
        txtDesc.setDisable(false);  
        cbMarcas.setDisable(false);
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        Marcas n = cbMarcas.getSelectionModel().getSelectedItem();
        
        
        mod.setMar_cod(n.getMar_cod());
        mod.setMod_desc(txtDesc.getText());
        
        new ModelosDAL().update(mod);
        
    }

    @FXML
    private void evtKeyPressedMar(KeyEvent event) {
    }

    private void carregarCbMar(String filtro){
        List<Marcas> marcas = new MarcasDAL().get(filtro);
        
        obsMarcas = FXCollections.observableArrayList(marcas);
        
        cbMarcas.setItems(obsMarcas);                  
    }
    
}
