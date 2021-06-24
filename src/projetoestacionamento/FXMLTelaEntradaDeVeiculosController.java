package projetoestacionamento;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import projetoestacionamento.db.dal.AcessoDAL;
import projetoestacionamento.db.dal.VeiculoDAL;
import projetoestacionamento.db.entidade.Acesso;
import projetoestacionamento.db.entidade.Veiculo;


public class FXMLTelaEntradaDeVeiculosController implements Initializable {

    @FXML
    private Button btGravar;
    @FXML
    private Button btFechar;
    @FXML
    private JFXDatePicker dpEntrada;
    @FXML
    private JFXTimePicker tpEntrada;
    @FXML
    private JFXTextField txtPlaca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpEntrada.setValue(LocalDate.now());
        tpEntrada.setValue(LocalTime.now());                
    }    

    @FXML
    private void evtBtGravar(ActionEvent event) {        
        
        int i=0;
        LocalDateTime ldt=null;        
        
        ldt = LocalDateTime.of(
                dpEntrada.getValue().getYear(),
                dpEntrada.getValue().getMonth(),
                dpEntrada.getValue().getDayOfMonth(),
                tpEntrada.getValue().getHour(),
                tpEntrada.getValue().getMinute()
        );
              
        List<Veiculo> lVei = new VeiculoDAL().get("vei_placa LIKE '"+txtPlaca.getText()+"';");                                        
               
        if(lVei.isEmpty())
            JOptionPane.showMessageDialog(null,"Veiculo não localizado no sistema");
        else
        {
            Acesso ac = new Acesso(0, lVei.get(i).getVei_cod(), ldt, null, 0);
            new AcessoDAL().insert(ac);
        }
        
        
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }
    
}
