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
import projetoestacionamento.db.dal.MarcasDAL;
import projetoestacionamento.db.dal.ModelosDAL;
import projetoestacionamento.db.entidade.Marcas;
import projetoestacionamento.db.entidade.Modelos;

public class FXMLCadModelosController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btNovo;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtDesc;
    @FXML
    private ComboBox<Marcas> cbMarcas;
    
    private ObservableList<Marcas> obsMarcas;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloquearCampos();
        carregarCbProp("");
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        txtDesc.setDisable(false);
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        
        Marcas n = cbMarcas.getSelectionModel().getSelectedItem();
        
        if(!txtDesc.getText().isEmpty() && cbMarcas.getSelectionModel().getSelectedItem() != null)
        {
            Modelos nMod = new Modelos(0, txtDesc.getText(), n.getMar_cod());
            new ModelosDAL().insert(nMod);
        }else
            JOptionPane.showMessageDialog(null,"Digite algo para ser possivel a inclusão");              
    }

    @FXML
    private void evtTxtDesc(ActionEvent event) {
    }

    @FXML
    private void evtKeyPressedMar(KeyEvent event) {
        String filtro = "mar_desc LIKE '%"+event.getText()+"%';";
        System.out.println(event.getText());
        carregarCbProp(filtro);        
    }

    public void bloquearCampos()
    {
        txtDesc.setDisable(true);
        
    }
    
    private void carregarCbProp(String filtro){
        List<Marcas> marcas = new MarcasDAL().get(filtro);
        
        obsMarcas = FXCollections.observableArrayList(marcas);
        
        cbMarcas.setItems(obsMarcas);                  
    }
}
