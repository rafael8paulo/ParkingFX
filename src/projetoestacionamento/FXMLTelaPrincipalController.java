package projetoestacionamento;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import projetoestacionamento.db.dal.AcessoDAL;
import projetoestacionamento.db.entidade.Acesso;
import projetoestacionamento.db.entidade.TelaPrincipal;
import projetoestacionamento.db.util.Banco;


public class FXMLTelaPrincipalController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btProprietario;
    @FXML
    private Button btVeiculo;
    @FXML
    private Button btMarca;
    @FXML
    private Button btModelo;
    @FXML
    private Button btTaxa;
    @FXML
    private JFXButton btEntrada;
    @FXML
    private TableView<TelaPrincipal> tabela;
    @FXML
    private TableColumn<TelaPrincipal, Integer> colId;
    @FXML
    private TableColumn<TelaPrincipal, LocalDateTime> colHrEntrada;
    @FXML
    private TableColumn<TelaPrincipal, String> colPlaca;
    @FXML
    private TableColumn<TelaPrincipal, String> colProp;

    public static TelaPrincipal vei = null;
    
    @FXML
    private JFXButton btRelFatu;
    @FXML
    private JFXButton btRelDemo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory("ac_cod"));
        colHrEntrada.setCellValueFactory(new PropertyValueFactory("ac_horaentrada"));
        colPlaca.setCellValueFactory(new PropertyValueFactory("vei_placa"));
        colProp.setCellValueFactory(new PropertyValueFactory("prop_nome"));
                        
        carregarTabela();
    }    

    @FXML
    private void evtBtFechar(ActionEvent event) {
        
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtProprietario(ActionEvent event) throws IOException {  
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaProprietario.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait(); 
        carregarTabela();
    }

    @FXML
    private void evtBtVeiculo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaVeiculo.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        carregarTabela();
    }

    @FXML
    private void evtBtMarca(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaMarcas.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();   
        carregarTabela();
    }

    @FXML
    private void evtBtModelo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaModelos.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();    
        carregarTabela();
    }

    @FXML
    private void evtBtTaxa(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaConf.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();  
        carregarTabela();
    }

    @FXML
    private void evtBtEntrada(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaEntradaDeVeiculos.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();   
        carregarTabela();
    }

    
    @FXML
    private void evtApagar(ActionEvent event) {
                
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atenção");
        alert.setContentText("Você realmente deseja apagar esse Registro?");
        if(alert.showAndWait().get()==ButtonType.OK)
        {  
           TelaPrincipal n = tabela.getSelectionModel().getSelectedItem(); 

           Acesso ac = new Acesso();
           ac.setAc_cod(n.getAc_cod());

           new AcessoDAL().delete(ac);
        }
        carregarTabela();
    }

    @FXML
    private void evtEncerrar(ActionEvent event) throws IOException {
        TelaEncerrar();
    }

    @FXML
    private void evtCliqueTabela(MouseEvent event) throws IOException {
        if (event.getClickCount()==2)
            TelaEncerrar();
    }
    
    private void TelaEncerrar() throws IOException{
        vei = tabela.getSelectionModel().getSelectedItem();
        
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLTelaSaidaDeVeiculos.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait(); 
        carregarTabela();
        
        vei = null;
    }                    
    
    @FXML
    private void evtBtRelFatu(ActionEvent event) throws ParseException, IOException {
                
        telaFiltro();
        Acesso ac = FXMLFiltroDataController.dt;
        String sql = "SELECT acesso.ac_cod, acesso.ac_horaentrada, acesso.ac_horasaida, prop.prop_nome, prop.prop_cpf, vei.vei_placa, md.mod_desc, mar.mar_desc, acesso.ac_valor FROM acesso INNER JOIN veiculo vei ON vei.vei_cod = acesso.vei_cod INNER JOIN proprietario prop ON prop.prop_cod = vei.prop_cod INNER JOIN modelo md ON md.mod_cod = vei.mod_cod INNER JOIN marca mar ON mar.mar_cod = md.mar_cod";
        
        String hrI = String.valueOf(ac.getAc_horaentrada());
        hrI = hrI.replace("T", " ");
        
        String hrF = String.valueOf(ac.getAc_horasaida());
        hrF = hrF.replace("T", " ");               
        
        sql += " WHERE acesso.ac_horaentrada >= '"+hrI+"' AND acesso.ac_horasaida <= '"+hrF+"'";
                        
        gerarRelatorio(sql, "relatorios/Rel_faturamento.jasper");         
    }
    
    @FXML
    private void evtBtRelDemo(ActionEvent event) throws IOException {
        
        telaFiltro();
        Acesso ac = FXMLFiltroDataController.dt;
        
        String sql = "SELECT acesso.ac_cod, acesso.ac_horaentrada, acesso.ac_horasaida, vei.vei_placa, prop.prop_nome, prop.prop_email, prop.prop_fone, prop.prop_cidade FROM acesso\n" +
                    "INNER JOIN veiculo vei ON vei.vei_cod = acesso.vei_cod\n" +
                    "INNER JOIN proprietario prop ON prop.prop_cod = vei.prop_cod ";
        
        String hrI = String.valueOf(ac.getAc_horaentrada());
        hrI = hrI.replace("T", " ");
        
        String hrF = String.valueOf(ac.getAc_horasaida());
        hrF = hrF.replace("T", " ");   
        
        sql += " WHERE acesso.ac_horaentrada >= '"+hrI+"' AND acesso.ac_horasaida <= '"+hrF+"'";
       
        gerarRelatorio(sql, "relatorios/Rel_demonstrativo.jasper");
    }
    
    private void telaFiltro() throws IOException{
        
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXMLFiltroData.fxml")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait(); 
        carregarTabela();
    }            
    
    private void gerarRelatorio(String sql, String relat)
    {
        try
        { 
            //sql para obter os dados para o relatorio
            ResultSet rs = Banco.getCon().consultar(sql);
            //implementação da interface JRDataSource para DataSource ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            //chamando o relatório
            String jasperPrint =          
                JasperFillManager.fillReportToFile(relat, null, jrRS);
            JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
            viewer.setTitle("Relatório de Alunos");//titulo do relatório
            viewer.setVisible(true);
        } catch (JRException erro)
        {  erro.printStackTrace(); }
    }
    
    public List<TelaPrincipal> get(String filtro) {
        
        TelaPrincipal tela =null;
        
        List<TelaPrincipal> confs = new ArrayList();
        
        String sql="SELECT ac_cod, acesso.ac_horaentrada, vei.vei_placa, prop.prop_nome  FROM acesso INNER JOIN veiculo vei ON vei.vei_cod = acesso.vei_cod INNER JOIN proprietario prop ON prop.prop_cod = vei.prop_cod WHERE ac_horasaida is null";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;              
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                confs.add(
                    tela = new TelaPrincipal(
                        rs.getInt("ac_cod"),
                        rs.getTimestamp("ac_horaentrada").toLocalDateTime(),
                        rs.getString("vei_placa"),
                        rs.getString("prop_nome")
                    )
                );            
        }catch(Exception e){
            System.out.println(e);
        }
                
        return confs;
    }
    private void carregarTabela(){
        List<TelaPrincipal> result = get("");
        tabela.setItems(FXCollections.observableArrayList(result));        
    }
    
}
