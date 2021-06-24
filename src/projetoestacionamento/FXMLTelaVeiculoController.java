/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoestacionamento;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projetoestacionamento.db.dal.VeiculoDAL;
import projetoestacionamento.db.entidade.Veiculo;

public class FXMLTelaVeiculoController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btPesquisar;
    @FXML
    private Button btNovo;
    @FXML
    private TableView<Veiculo> tabela;
    @FXML
    private TableColumn<Veiculo, Integer> colId;
    @FXML
    private TableColumn<Veiculo, String> colPlaca;
    @FXML
    private TableColumn<Veiculo, Integer> colModelo;
    @FXML
    private TableColumn<Veiculo, Integer> colCor;
    @FXML
    private TableColumn<Veiculo, Integer> colProprietario;

    public static Veiculo vei = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("vei_cod"));
        colPlaca.setCellValueFactory(new PropertyValueFactory("vei_placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory("mod_cod"));
        colCor.setCellValueFactory(new PropertyValueFactory("vei_cor"));
        colProprietario.setCellValueFactory(new PropertyValueFactory("prop_cod"));
        carregarTabela("");
    }    

    private void carregarTabela(String filtro){
        List<Veiculo> veiculos = new VeiculoDAL().get(filtro);
        tabela.setItems(FXCollections.observableArrayList(veiculos));        
    }
    
    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtNovo(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLCadVeiculo.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();    
        carregarTabela("");
    }

    @FXML
    private void evtCliqueTabela(MouseEvent event) throws IOException {
        
        if (event.getClickCount()==2)            
        {   
            telaAlterar();
        }
        
    }

    @FXML
    private void evtApagar(ActionEvent event) {
        if (tabela.getSelectionModel().getSelectedIndex()>=0)
        {
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atenção");
            alert.setContentText("Você realmente deseja apagar esse veiculo?");
            if(alert.showAndWait().get()==ButtonType.OK)
            {  
               Veiculo n = tabela.getSelectionModel().getSelectedItem();               
               new VeiculoDAL().delete(n);
            }
        } 
        carregarTabela("");
    }

    @FXML
    private void evtTxtPesquisa(ActionEvent event) {
    }

    @FXML
    private void evtBtPesquisar(ActionEvent event) {
        if(!txtPesquisa.getText().isEmpty())
        {                        
            carregarTabela(" vei_placa LIKE '%"+txtPesquisa.getText()+"%';");
        }else{
            carregarTabela("");
        }
           
    }

    @FXML
    private void evtAlterar(ActionEvent event) throws IOException {
        if (tabela.getSelectionModel().getSelectedIndex()>=0)
        {
            telaAlterar();
        }  
    }
    
    
    private void telaAlterar() throws IOException{
        vei = tabela.getSelectionModel().getSelectedItem();
                
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLConsultarVeiculo.fxml")));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
            
        vei = null;
        carregarTabela("");
    }
    
}
