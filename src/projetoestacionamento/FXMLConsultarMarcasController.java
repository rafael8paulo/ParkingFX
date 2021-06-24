package projetoestacionamento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetoestacionamento.db.dal.MarcasDAL;
import projetoestacionamento.db.entidade.Marcas;

public class FXMLConsultarMarcasController implements Initializable {

    @FXML
    private Button btGravar;
    @FXML
    private Button btFechar;
    @FXML
    private Button btAlterar;
    @FXML
    private TextField txtDesc;
    @FXML
    private TextField txtCod;
    
    private Marcas mar = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mar =  FXMLTelaMarcasController.mar;
        
        txtCod.setText(String.valueOf(mar.getMar_cod()));
        txtDesc.setText(mar.getMar_desc());
        
        bloquearCampos();   
        
    }        
    
    @FXML
    private void evtBtGravar(ActionEvent event) {      
        mar.setMar_desc(txtDesc.getText());
        new MarcasDAL().update(mar);
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }
    
    public void bloquearCampos()
    {
        txtDesc.setDisable(true);   
        txtCod.setDisable(true);   
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
        txtDesc.setDisable(false);   
        //txtCod.setDisable(false);      
    }
    
}
