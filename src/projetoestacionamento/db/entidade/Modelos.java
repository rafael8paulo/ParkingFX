
package projetoestacionamento.db.entidade;

public class Modelos {
    private int mod_cod;
    private String mod_desc;
    private int mar_cod;

    public Modelos(int mod_cod, String mod_desc, int mar_cod) {
        this.mod_cod = mod_cod;
        this.mod_desc = mod_desc;
        this.mar_cod = mar_cod;
    }

    public int getMod_cod() {
        return mod_cod;
    }

    public void setMod_cod(int mod_cod) {
        this.mod_cod = mod_cod;
    }

    public String getMod_desc() {
        return mod_desc;
    }

    public void setMod_desc(String mod_desc) {
        this.mod_desc = mod_desc;
    }

    @Override
    public String toString() {
        return this.getMod_desc();
    }

    public int getMar_cod() {
        return mar_cod;
    }

    public void setMar_cod(int mar_cod) {
        this.mar_cod = mar_cod;
    }

    
}
