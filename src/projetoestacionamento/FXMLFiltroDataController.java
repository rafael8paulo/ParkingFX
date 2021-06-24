package projetoestacionamento;



import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import projetoestacionamento.db.entidade.Acesso;


public class FXMLFiltroDataController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private Button btFiltrar;
    @FXML
    private JFXDatePicker dtInicio;
    @FXML
    private JFXDatePicker dtFinal;
    
    public static Acesso dt = null;
    @FXML
    private JFXTimePicker tpInicio;
    @FXML
    private JFXTimePicker tpFim;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dtInicio.setValue(LocalDate.now());
        dtFinal.setValue(LocalDate.now());
        tpInicio.setValue(LocalTime.now());          
        tpFim.setValue(LocalTime.now());          
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtFiltrar(ActionEvent event) {
        
        LocalDateTime ldi=null, ldf=null;        
        
        ldi = LocalDateTime.of(
                dtInicio.getValue().getYear(),
                dtInicio.getValue().getMonth(),
                dtInicio.getValue().getDayOfMonth(),
                tpInicio.getValue().getHour(),
                tpInicio.getValue().getMinute()
        );
        ldf = LocalDateTime.of(
                dtFinal.getValue().getYear(),
                dtFinal.getValue().getMonth(),
                dtFinal.getValue().getDayOfMonth(),
                tpFim.getValue().getHour(),
                tpFim.getValue().getMinute()
        );
       
        Acesso ac = new Acesso(0, ldi, ldf, 0);
        
        dt = ac;
        
        btFiltrar.getScene().getWindow().hide();
        
    }

    
}
