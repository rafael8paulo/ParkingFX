/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import static projetoestacionamento.FXMLTelaVeiculoController.vei;
import projetoestacionamento.db.dal.ModelosDAL;
import projetoestacionamento.db.dal.ProprietarioDAL;
import projetoestacionamento.db.dal.VeiculoDAL;
import projetoestacionamento.db.entidade.Modelos;
import projetoestacionamento.db.entidade.Proprietario;



public class FXMLConsultarVeiculoControlle implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btGravar;
    @FXML
    private Button btAlterar;
    @FXML
    private ComboBox<Modelos> cbModelos;
    @FXML
    private ComboBox<Proprietario> cbProp;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtCodigo;

    private ObservableList<Modelos> obsModelos;
    private ObservableList<Proprietario> obsProps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bloquearCampos();
        vei = FXMLTelaVeiculoController.vei;        
        
        Modelos mod = new ModelosDAL().get(vei.getMod_cod());
        Proprietario prop = new ProprietarioDAL().get(vei.getProp_cod());
        
        cbModelos.setValue(mod);
        cbProp.setValue(prop);
        
        txtCodigo.setText(String.valueOf(vei.getVei_cod()));
        txtPlaca.setText(vei.getVei_placa());
        txtCor.setText(vei.getVei_cor());
        
        carregarCbModelos("");
        carregarCbProp("");
        
    }    


    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtGravar(ActionEvent event) {
        
        Modelos mod = cbModelos.getSelectionModel().getSelectedItem();
        Proprietario prop = cbProp.getSelectionModel().getSelectedItem();
        
        vei.setProp_cod(prop.getProp_cod());
        vei.setMod_cod(mod.getMod_cod());
        vei.setVei_placa(txtPlaca.getText());
        vei.setVei_cor(txtCor.getText());        
        new VeiculoDAL().update(vei);
    }

    @FXML
    private void evtAlterar(ActionEvent event) {
        txtCor.setDisable(false);   
        txtCodigo.setDisable(false);   
        txtPlaca.setDisable(false);
        cbModelos.setDisable(false);
        cbProp.setDisable(false);
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
    public void bloquearCampos()
    {
        txtCor.setDisable(true);   
        txtCodigo.setDisable(true);   
        txtPlaca.setDisable(true);
        cbModelos.setDisable(true);
        cbProp.setDisable(true);
    }
}
