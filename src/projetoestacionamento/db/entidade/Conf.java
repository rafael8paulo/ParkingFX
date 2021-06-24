package projetoestacionamento.db.entidade;

public class Conf {
    private double conf_valorhora;
    private double conf_valoradic;
    private double conf_carencia;

    public Conf(double conf_valorhora, double conf_valoradic, double conf_carencia) {
        this.conf_valorhora = conf_valorhora;
        this.conf_valoradic = conf_valoradic;
        this.conf_carencia = conf_carencia;
    }

    public double getConf_valorhora() {
        return conf_valorhora;
    }

    public void setConf_valorhora(double conf_valorhora) {
        this.conf_valorhora = conf_valorhora;
    }

    public double getConf_valoradic() {
        return conf_valoradic;
    }

    public void setConf_valoradic(double conf_valoradic) {
        this.conf_valoradic = conf_valoradic;
    }

    public double getConf_carencia() {
        return conf_carencia;
    }

    public void setConf_carencia(double conf_carencia) {
        this.conf_carencia = conf_carencia;
    }
    
    
    
}
