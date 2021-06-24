
package projetoestacionamento.db.util;

// design pattern Singleton
public class Banco {
    static private Conexao conexao=new Conexao();

    private Banco() {
    }
    
    static public boolean conectar()
    {
        String url="jdbc:postgresql://localhost/"; 
        return conexao.conectar(url, "veiculos", "postgres", "teste");
    }

    static public Conexao getCon() {
        return conexao;
    }       
}
