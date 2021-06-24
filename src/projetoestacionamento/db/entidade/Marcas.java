
package projetoestacionamento.db.entidade;


public class Marcas {
    private int mar_cod;
    private String mar_desc;

    public Marcas(int mar_cod, String mar_desc) {
        this.mar_cod = mar_cod;
        this.mar_desc = mar_desc;
    }

    public Marcas() {
    }

    
    
    public int getMar_cod() {
        return mar_cod;
    }

    public void setMar_cod(int mar_cod) {
        this.mar_cod = mar_cod;
    }

    public String getMar_desc() {
        return mar_desc;
    }

    public void setMar_desc(String mar_desc) {
        this.mar_desc = mar_desc;
    }

    @Override
    public String toString() {
        return this.getMar_desc();
    }

    
}
