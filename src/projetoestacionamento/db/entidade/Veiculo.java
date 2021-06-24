package projetoestacionamento.db.entidade;

public class Veiculo {
    private int vei_cod;
    private String vei_placa;
    private int mod_cod;
    private String vei_cor;
    private int prop_cod;

    public Veiculo(int vei_cod, String vei_placa, int mod_cod, String vei_cor, int prop_cod) {
        this.vei_cod = vei_cod;
        this.vei_placa = vei_placa;
        this.mod_cod = mod_cod;
        this.vei_cor = vei_cor;
        this.prop_cod = prop_cod;
    }

    public int getVei_cod() {
        return vei_cod;
    }

    public void setVei_cod(int vei_cod) {
        this.vei_cod = vei_cod;
    }

    public String getVei_placa() {
        return vei_placa;
    }

    public void setVei_placa(String vei_placa) {
        this.vei_placa = vei_placa;
    }

    public int getMod_cod() {
        return mod_cod;
    }

    public void setMod_cod(int mod_cod) {
        this.mod_cod = mod_cod;
    }

    public String getVei_cor() {
        return vei_cor;
    }

    public void setVei_cor(String vei_cor) {
        this.vei_cor = vei_cor;
    }

    public int getProp_cod() {
        return prop_cod;
    }

    public void setProp_cod(int prop_cod) {
        this.prop_cod = prop_cod;
    }
    
    
    
    
}
