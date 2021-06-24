package projetoestacionamento.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.entidade.Modelos;
import projetoestacionamento.db.util.Banco;

public class ModelosDAL implements IDAL <Modelos> {
    
    @Override
    public boolean insert(Modelos entidade) {
        String sql;
        sql="INSERT INTO modelo (mar_cod, mod_desc) " +
             " VALUES ('#1', '#2') ";
        
        sql = sql.replace("#1", String.valueOf(entidade.getMar_cod()));
        sql = sql.replace("#2", entidade.getMod_desc());
        
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Modelos entidade) {
        String sql;
        sql="UPDATE modelo set mod_desc = '#1', mar_cod = #2 WHERE mod_cod ="+entidade.getMod_cod();
        sql=sql.replace("#1", entidade.getMod_desc());
        sql=sql.replace("#2", String.valueOf(entidade.getMar_cod()));
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Modelos entidade) {
        String sql="DELETE FROM modelo WHERE mod_cod ="+entidade.getMod_cod();
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Modelos get(int id) {
        Modelos modelo =null;
        String sql = "SELECT * FROM modelo WHERE mod_cod = "+id;
        ResultSet rs=Banco.getCon().consultar(sql);
        System.out.println(sql);
        try{
            if(rs.next())
            {
                modelo = new Modelos(rs.getInt("mod_cod"), rs.getString("mod_desc"), rs.getInt("mar_cod"));
            }    
        }catch(Exception e){System.out.println(e);}
        return modelo;
    }

    @Override
    public List<Modelos> get(String filtro) {
        
        Modelos modelo =null;
        List<Modelos> modelos = new ArrayList();
        
        String sql="SELECT * FROM modelo";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                modelos.add(
                    modelo = new Modelos(rs.getInt("mod_cod"), rs.getString("mod_desc"), rs.getInt("mar_cod"))
                );            
        }catch(Exception e){
            System.out.println(e);
        }
        return modelos;
    }
}
