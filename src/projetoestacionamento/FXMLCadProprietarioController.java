package projetoestacionamento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import projetoestacionamento.db.dal.ProprietarioDAL;
import projetoestacionamento.db.entidade.Proprietario;


public class FXMLCadProprietarioController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtUf;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btNovo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloquearCampos();
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        if(!txtCPF.getText().isEmpty() && !txtNome.getText().isEmpty() && !txtRua.getText().isEmpty() && !txtBairro.getText().isEmpty() && !txtCidade.getText().isEmpty() && !txtUf.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtTelefone.getText().isEmpty())
        {
            Proprietario n = new Proprietario(
                    0,
                    txtCPF.getText(),
                    txtNome.getText(),
                    txtRua.getText(),
                    txtBairro.getText(),
                    txtCidade.getText(),
                    txtUf.getText(),
                    txtEmail.getText(),
                    txtTelefone.getText()
            );
            new ProprietarioDAL().insert(n);
        }else
            JOptionPane.showMessageDialog(null,"Prencha todos os campos obrigatorios para ser possivel a inclusão");
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        txtCPF.setDisable(false);
        txtNome.setDisable(false);
        txtRua.setDisable(false);
        txtBairro.setDisable(false);
        txtCidade.setDisable(false);
        txtUf.setDisable(false);
        txtEmail.setDisable(false);
        txtTelefone.setDisable(false);
    }
    
    public void bloquearCampos()
    {
        txtCPF.setDisable(true);
        txtNome.setDisable(true);
        txtRua.setDisable(true);
        txtBairro.setDisable(true);
        txtCidade.setDisable(true);
        txtUf.setDisable(true);
        txtEmail.setDisable(true);
        txtTelefone.setDisable(true);
    }
    
}
