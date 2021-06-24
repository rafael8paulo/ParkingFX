package projetoestacionamento.db.entidade;

public class Proprietario {
    private int prop_cod;
    private String prop_cpf; 
    private String prop_nome;
    private String prop_rua;
    private String prop_bairro;
    private String  prop_cidade;
    private String  prop_uf;
    private String  prop_email;
    private String  prop_fone;

    public Proprietario(int prop_cod, String prop_cpf, String prop_nome, String prop_rua, String prop_bairro, String prop_cidade, String prop_uf, String prop_email, String prop_fone) {
        this.prop_cod = prop_cod;
        this.prop_cpf = prop_cpf;
        this.prop_nome = prop_nome;
        this.prop_rua = prop_rua;
        this.prop_bairro = prop_bairro;
        this.prop_cidade = prop_cidade;
        this.prop_uf = prop_uf;
        this.prop_email = prop_email;
        this.prop_fone = prop_fone;
    }

    public int getProp_cod() {
        return prop_cod;
    }

    public void setProp_cod(int prop_cod) {
        this.prop_cod = prop_cod;
    }

    public String getProp_cpf() {
        return prop_cpf;
    }

    public void setProp_cpf(String prop_cpf) {
        this.prop_cpf = prop_cpf;
    }
    

    public String getProp_nome() {
        return prop_nome;
    }

    public void setProp_nome(String prop_nome) {
        this.prop_nome = prop_nome;
    }

    public String getProp_rua() {
        return prop_rua;
    }

    public void setProp_rua(String prop_rua) {
        this.prop_rua = prop_rua;
    }

    public String getProp_bairro() {
        return prop_bairro;
    }

    public void setProp_bairro(String prop_bairro) {
        this.prop_bairro = prop_bairro;
    }

    public String getProp_cidade() {
        return prop_cidade;
    }

    public void setProp_cidade(String prop_cidade) {
        this.prop_cidade = prop_cidade;
    }

    public String getProp_uf() {
        return prop_uf;
    }

    public void setProp_uf(String prop_uf) {
        this.prop_uf = prop_uf;
    }

    public String getProp_email() {
        return prop_email;
    }

    public void setProp_email(String prop_email) {
        this.prop_email = prop_email;
    }

    public String getProp_fone() {
        return prop_fone;
    }

    public void setProp_fone(String prop_fone) {
        this.prop_fone = prop_fone;
    }
    
    @Override
    public String toString() {
        return getProp_nome();
    }
}
