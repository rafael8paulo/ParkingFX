package projetoestacionamento.db.entidade;

import java.time.LocalDateTime;

public class TelaPrincipal {

   
    private int ac_cod;
    private LocalDateTime ac_horaentrada;
    private String vei_placa;
    private String prop_nome;

    public TelaPrincipal(int ac_cod, LocalDateTime ac_horaentrada, String vei_placa, String prop_nome) {
        this.ac_cod = ac_cod;
        this.ac_horaentrada = ac_horaentrada;
        this.vei_placa = vei_placa;
        this.prop_nome = prop_nome;
    }
    
        
    public int getAc_cod() {
        return ac_cod;
    }

    public void setAc_cod(int ac_cod) {
        this.ac_cod = ac_cod;
    }

    public LocalDateTime getAc_horaentrada() {
        return ac_horaentrada;
    }

    public void setAc_horaentrada(LocalDateTime ac_horaentrada) {
        this.ac_horaentrada = ac_horaentrada;
    }

    public String getVei_placa() {
        return vei_placa;
    }

    public void setVei_placa(String vei_placa) {
        this.vei_placa = vei_placa;
    }

    public String getProp_nome() {
        return prop_nome;
    }

    public void setProp_nome(String prop_nome) {
        this.prop_nome = prop_nome;
    }
    
    
    
    
}
