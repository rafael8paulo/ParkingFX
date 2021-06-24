package projetoestacionamento.db.dal;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.util.Banco;
import projetoestacionamento.db.entidade.Marcas;

public class MarcasDAL implements IDAL <Marcas>{

    @Override
    public boolean insert(Marcas entidade) {
        String sql;
        sql="INSERT INTO marca (mar_desc)" +
             " VALUES ('#1')";
        sql=sql.replace("#1", entidade.getMar_desc());
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Marcas entidade) {
        String sql;
        sql="UPDATE marca set mar_desc = '#1' WHERE mar_cod ="+entidade.getMar_cod();
        sql=sql.replace("#1", entidade.getMar_desc());
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Marcas entidade) {
        String sql="DELETE FROM marca WHERE mar_cod ="+entidade.getMar_cod();
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Marcas get(int id) {
        Marcas marca=null;
        ResultSet rs=Banco.getCon().consultar("SELECT * FROM marca WHERE mar_cod = "+id);
        try{
        if(rs.next())
            marca = new Marcas(id,rs.getString("mar_desc"));
        }catch(Exception e){System.out.println(e);}
        return marca;
    }

    @Override
    public List<Marcas> get(String filtro) {
        
        List<Marcas> marcas = new ArrayList();
        
        String sql="SELECT * FROM marca";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                marcas.add(
                    new Marcas(
                        rs.getInt("mar_cod"),
                        rs.getString("mar_desc")
                    ));     
            
        }catch(Exception e){
            System.out.println(e);
        }
        return marcas;
    }
    
}