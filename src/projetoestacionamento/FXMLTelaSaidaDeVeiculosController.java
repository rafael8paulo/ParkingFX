package projetoestacionamento;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.math.RoundingMode;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import projetoestacionamento.db.dal.AcessoDAL;
import projetoestacionamento.db.dal.ConfDAL;
import projetoestacionamento.db.entidade.Acesso;
import projetoestacionamento.db.entidade.Conf;
import projetoestacionamento.db.entidade.TelaPrincipal;

public class FXMLTelaSaidaDeVeiculosController implements Initializable {

    @FXML
    private Button btFechar;
    @FXML
    private JFXTextField txtPlaca;
    @FXML
    private JFXDatePicker dpEntrada;
    @FXML
    private JFXTimePicker tpEntrada;
    @FXML
    private Button btEncerrar;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXTextField txtProp;
    
    private TelaPrincipal vei = FXMLTelaPrincipalController.vei;
    
    private double valor;
    
    
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        
        LocalDate dt = LocalDate.of(vei.getAc_horaentrada().getYear(), vei.getAc_horaentrada().getMonth(), vei.getAc_horaentrada().getDayOfMonth());
        LocalTime tp = LocalTime.of(vei.getAc_horaentrada().getHour(), vei.getAc_horaentrada().getMinute(), vei.getAc_horaentrada().getSecond());                
        
        dpEntrada.setValue(dt);
        tpEntrada.setValue(tp);
        
        txtPlaca.setText(vei.getVei_placa());
        txtProp.setText(vei.getProp_nome());
        
        

        Period period = getPeriod(LocalDateTime.now(), vei.getAc_horaentrada());
        long time[] = getTime(LocalDateTime.now(), vei.getAc_horaentrada());                      
        
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        
        this.valor = calculaValor(period, time);
        
        txtTotal.setText(decimalFormat.format(valor));
        
        bloquearCampos();
    }    
    
    private double calculaValor(Period period, long time[]){
        List <Conf> taxa = new ConfDAL().get("");
        
        double valor = 0;
        double vlmin = taxa.get(0).getConf_valorhora() /60;
        int hrs = 0;                        
        
        int day = Math.abs(period.getDays());       
        
        if(time[0] <= 1 && time[1] <= taxa.get(0).getConf_carencia())
            valor = taxa.get(0).getConf_valorhora();
        else{
            hrs = day*24;               
            valor = hrs * taxa.get(0).getConf_valoradic();
            valor += (time[0] * taxa.get(0).getConf_valoradic());
            valor += vlmin * time[1];
        }        
        return valor;
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        btFechar.getScene().getWindow().hide();
    }

    @FXML
    private void evtBtEncerrar(ActionEvent event) {
        
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atenção");
        alert.setContentText("Você realmente deseja encerrar esse Registro?"+"\n"+"Valor: "+valor);             
        
        if(alert.showAndWait().get()==ButtonType.OK)
        {              
            Acesso a = new Acesso();
            a.setAc_cod(vei.getAc_cod());
            LocalDateTime dtFecha = LocalDateTime.now();
            a.setAc_horasaida(LocalDateTime.now());
            a.setAc_horasaida(dtFecha);
            a.setAc_valor(valor);            
            
            new AcessoDAL().update(a);
        }
        
    }
    
    private void bloquearCampos(){
        txtPlaca.setDisable(true);
        txtProp.setDisable(true);
        txtTotal.setDisable(true);
        tpEntrada.setDisable(true);
        dpEntrada.setDisable(true);        
    }
    
    
    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }
    
    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }
    
}
