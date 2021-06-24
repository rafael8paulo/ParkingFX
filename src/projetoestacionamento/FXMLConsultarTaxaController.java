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
import static projetoestacionamento.FXMLTelaConfController.conf;
import projetoestacionamento.db.dal.ConfDAL;

public class FXMLConsultarTaxaController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtValorHora;
    @FXML
    private TextField txtValorAdicional;
    @FXML
    private TextField txtCarencia;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conf = FXMLTelaConfController.conf;
        
        bloquearCampos();
        
        txtCarencia.setText(String.valueOf(conf.getConf_carencia()));
        txtValorAdicional.setText(String.valueOf(conf.getConf_valoradic()));
        txtValorHora.setText(String.valueOf(conf.getConf_valorhora()));
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtAlterar(ActionEvent event) {
        txtCarencia.setDisable(false);
        txtValorAdicional.setDisable(false);
        txtValorHora.setDisable(false);
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        conf.setConf_carencia(Double.valueOf(txtCarencia.getText()));
        conf.setConf_valoradic(Double.valueOf(txtValorAdicional.getText()));
        conf.setConf_valorhora(Double.valueOf(txtValorHora.getText()));
        new ConfDAL().update(conf);
    }
    public void bloquearCampos()
    {        
        txtCarencia.setDisable(true);
        txtValorAdicional.setDisable(true);
        txtValorHora.setDisable(true);
    }
}
