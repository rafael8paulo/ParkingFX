package projetoestacionamento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import projetoestacionamento.db.dal.MarcasDAL;
import projetoestacionamento.db.entidade.Marcas;


public class FXMLCadMarcasController implements Initializable {

    @FXML
    private Button btNovo;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtDesc;
    @FXML
    private Button btFechar;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        bloquearCampos();
    }    

    @FXML
    private void evtBtNovo(ActionEvent event) {
        txtDesc.setDisable(false);
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        if(!txtDesc.getText().isEmpty())
        {
            Marcas n = new Marcas(0, txtDesc.getText());
            new MarcasDAL().insert(n);
        }else
            JOptionPane.showMessageDialog(null,"Digite algo para ser possivel a inclusão");
    }

    @FXML
    private void evtTxtDesc(ActionEvent event) {
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }
    
    public void bloquearCampos()
    {
        txtDesc.setDisable(true);
        
    }
}
