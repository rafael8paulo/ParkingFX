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
import javax.swing.JOptionPane;
import projetoestacionamento.db.dal.ConfDAL;
import projetoestacionamento.db.entidade.Conf;

/**
 * FXML Controller class
 *
 * @author Rafael
 */
public class FXMLCadConfController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btNovo;
    @FXML
    private Button btGravar;
    @FXML
    private TextField txtValorHora;
    @FXML
    private TextField txtValorAdicional;
    @FXML
    private TextField txtCarencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloquearCampos();        
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtNovo(ActionEvent event) {
        txtCarencia.setDisable(false);
        txtValorAdicional.setDisable(false);
        txtValorHora.setDisable(false);
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        if(!txtValorHora.getText().isEmpty()&& !txtValorAdicional.getText().isEmpty()&& !txtCarencia.getText().isEmpty())
        {
            Conf n = new Conf(Double.parseDouble(txtValorHora.getText()),Double.parseDouble(txtValorAdicional.getText()), Double.parseDouble(txtCarencia.getText()));
            new ConfDAL().insert(n);
        }else
            JOptionPane.showMessageDialog(null,"Preencha todos os campos para ser possivel a inclusao");
    }
    public void bloquearCampos()
    {
        txtCarencia.setDisable(true);
        txtValorAdicional.setDisable(true);
        txtValorHora.setDisable(true);
    }
}
