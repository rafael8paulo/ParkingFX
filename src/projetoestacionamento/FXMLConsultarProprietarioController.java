/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoestacionamento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static projetoestacionamento.FXMLTelaProprietarioController.prop;
import projetoestacionamento.db.dal.ProprietarioDAL;

public class FXMLConsultarProprietarioController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btGravar;
    @FXML
    private Button btAlterar;
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
    private TextField txtCodigo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prop = FXMLTelaProprietarioController.prop;
        
        txtBairro.setText(prop.getProp_bairro());
        txtCPF.setText(prop.getProp_cpf());
        txtNome.setText(prop.getProp_nome());
        txtRua.setText(prop.getProp_rua());
        txtRua.setText(prop.getProp_rua());
        txtEmail.setText(prop.getProp_email());
        txtUf.setText(prop.getProp_uf());
        txtCidade.setText(prop.getProp_cidade());
        txtCodigo.setText(String.valueOf(prop.getProp_cod()));
        txtTelefone.setText(prop.getProp_fone());
        bloquearCampos();
        
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
       prop.setProp_cpf(txtCPF.getText());
       prop.setProp_nome(txtNome.getText());
       prop.setProp_rua(txtRua.getText());
       prop.setProp_bairro(txtBairro.getText());
       prop.setProp_email(txtEmail.getText());
       prop.setProp_uf(txtUf.getText());
       prop.setProp_cidade(txtCidade.getText());
       prop.setProp_fone(txtTelefone.getText());
       
       new ProprietarioDAL().update(prop);
               
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
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
        txtCodigo.setDisable(true);
        
    }
    
}
