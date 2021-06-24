package projetoestacionamento.db.entidade;

import java.time.LocalDateTime;

public class Acesso {
    private int ac_cod;
    private int vei_cod;
    private LocalDateTime ac_horaentrada;
    private LocalDateTime ac_horasaida;
    private double ac_valor;

    public Acesso(int ac_cod, int vei_cod, LocalDateTime ac_horaentrada, LocalDateTime ac_horasaida, double ac_valor) {
        this.ac_cod = ac_cod;
        this.vei_cod = vei_cod;
        this.ac_horaentrada = ac_horaentrada;
        this.ac_horasaida = ac_horasaida;
        this.ac_valor = ac_valor;
    }

    public Acesso(int vei_cod, LocalDateTime ac_horaentrada, LocalDateTime ac_horasaida, double ac_valor) {
        this.vei_cod = vei_cod;
        this.ac_horaentrada = ac_horaentrada;
        this.ac_horasaida = ac_horasaida;
        this.ac_valor = ac_valor;
    }
    
    
    
    public Acesso() {
    }

    public int getAc_cod() {
        return ac_cod;
    }

    public void setAc_cod(int ac_cod) {
        this.ac_cod = ac_cod;
    }

    public int getVei_cod() {
        return vei_cod;
    }

    public void setVei_cod(int vei_cod) {
        this.vei_cod = vei_cod;
    }

    public LocalDateTime getAc_horaentrada() {
        return ac_horaentrada;
    }

    public void setAc_horaentrada(LocalDateTime ac_horaentrada) {
        this.ac_horaentrada = ac_horaentrada;
    }

    public LocalDateTime getAc_horasaida() {
        return ac_horasaida;
    }

    public void setAc_horasaida(LocalDateTime ac_horasaida) {
        this.ac_horasaida = ac_horasaida;
    }

    public double getAc_valor() {
        return ac_valor;
    }

    public void setAc_valor(double ac_valor) {
        this.ac_valor = ac_valor;
    }
       
}
