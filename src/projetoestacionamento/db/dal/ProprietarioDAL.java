package projetoestacionamento.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.entidade.Proprietario;
import projetoestacionamento.db.util.Banco;


public class ProprietarioDAL implements IDAL <Proprietario>{
     @Override
    public boolean insert(Proprietario entidade) {
        String sql;
        sql="INSERT INTO proprietario (prop_cpf, prop_nome, prop_rua, prop_bairro, prop_cidade, prop_uf, prop_email, prop_fone) " +
             " VALUES ('#1', '#2', '#3', '#4', '#5', '#6', '#7', '#8') ";
            
            sql = sql.replace("#1", entidade.getProp_cpf());
            sql = sql.replace("#2", entidade.getProp_nome());
            sql = sql.replace("#3", entidade.getProp_rua());
            sql = sql.replace("#4", entidade.getProp_bairro());
            sql = sql.replace("#5", entidade.getProp_cidade());
            sql = sql.replace("#6", entidade.getProp_uf());
            sql = sql.replace("#7", entidade.getProp_email());
            sql = sql.replace("#8", entidade.getProp_fone());
            
            System.out.println(sql);
            
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Proprietario entidade) {
        String sql;
        sql="UPDATE proprietario set prop_cpf = '#1', prop_nome = '#2', prop_rua = '#3', prop_bairro = '#4', prop_cidade = '#5', prop_uf = '#6', prop_email = '#7', prop_fone = '#8' WHERE prop_cod ="+entidade.getProp_cod();
        
        sql = sql.replace("#1", entidade.getProp_cpf());
        sql = sql.replace("#2", entidade.getProp_nome());
        sql = sql.replace("#3", entidade.getProp_rua());
        sql = sql.replace("#4", entidade.getProp_bairro());
        sql = sql.replace("#5", entidade.getProp_cidade());
        sql = sql.replace("#6", entidade.getProp_uf());
        sql = sql.replace("#7", entidade.getProp_email());
        sql = sql.replace("#8", entidade.getProp_fone());
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Proprietario entidade) {
        String sql="DELETE FROM proprietario WHERE prop_cod ="+entidade.getProp_cod();
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Proprietario get(int id) {
        Proprietario proprietario =null;
        ResultSet rs=Banco.getCon().consultar("SELECT * FROM proprietario WHERE prop_cod = "+id);
        try{
            if(rs.next())
            {
                proprietario = new Proprietario(
                    rs.getInt("prop_cod"), 
                    rs.getString("prop_cpf"), 
                    rs.getString("prop_nome"), 
                    rs.getString("prop_rua"), 
                    rs.getString("prop_bairro"), 
                    rs.getString("prop_cidade"), 
                    rs.getString("prop_uf"), 
                    rs.getString("prop_email"), 
                    rs.getString("prop_fone")
                );
            }    
        }catch(Exception e){System.out.println(e);}
        return proprietario;
    }

    @Override
    public List<Proprietario> get(String filtro) {
        
        Proprietario proprietario =null;
        List<Proprietario> proprietarios = new ArrayList();
        
        String sql="SELECT * FROM proprietario";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;              
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                proprietarios.add(
                    proprietario = new Proprietario(
                        rs.getInt("prop_cod"), 
                        rs.getString("prop_cpf"), 
                        rs.getString("prop_nome"), 
                        rs.getString("prop_rua"), 
                        rs.getString("prop_bairro"), 
                        rs.getString("prop_cidade"), 
                        rs.getString("prop_uf"), 
                        rs.getString("prop_email"), 
                        rs.getString("prop_fone")
                    )
                );            
        }catch(Exception e){
            System.out.println(e);
        }
                
        return proprietarios;
    }
}
