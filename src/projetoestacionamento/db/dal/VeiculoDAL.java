package projetoestacionamento.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.entidade.Veiculo;
import projetoestacionamento.db.util.Banco;


public class VeiculoDAL implements IDAL <Veiculo>{
     @Override
    public boolean insert(Veiculo entidade) {
        String sql;
        sql="INSERT INTO veiculo (vei_placa, mod_cod, vei_cor, prop_cod) " +
             " VALUES ('#1', #2, '#3', '#4') ";
            
            sql = sql.replace("#1", entidade.getVei_placa());
            sql = sql.replace("#2", Integer.toString(entidade.getMod_cod()));
            sql = sql.replace("#3", entidade.getVei_cor());
            sql = sql.replace("#4", Integer.toString(entidade.getProp_cod()));            
            
            System.out.println(sql);
            
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Veiculo entidade) {
        String sql;
        sql="UPDATE veiculo set vei_placa = '#1', mod_cod = '#2', vei_cor = '#3', prop_cod = '#4' WHERE vei_cod ="+entidade.getVei_cod();
        
        sql = sql.replace("#1", entidade.getVei_placa());
        sql = sql.replace("#2", Integer.toString(entidade.getMod_cod()));
        sql = sql.replace("#3", entidade.getVei_cor());
        sql = sql.replace("#4", Integer.toString(entidade.getProp_cod()));         
        
        System.out.println(sql);
        
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Veiculo entidade) {
        String sql="DELETE FROM veiculo WHERE vei_cod ="+entidade.getVei_cod();
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Veiculo get(int id) {
        Veiculo veiculo =null;
        ResultSet rs=Banco.getCon().consultar("SELECT * FROM veiculo WHERE vei_cod = "+id);
        try{
            if(rs.next())
            {
                veiculo = new Veiculo(
                    rs.getInt("vei_cod"), 
                        rs.getString("vei_placa"), 
                        rs.getInt("mod_cod"), 
                        rs.getString("vei_cor"), 
                        rs.getInt("prop_cod")         
                );
            }    
        }catch(Exception e){System.out.println(e);}
        return veiculo;
    }

    @Override
    public List<Veiculo> get(String filtro) {
        
        Veiculo veiculo =null;
        List<Veiculo> veiculos = new ArrayList();
        
        String sql="SELECT * FROM veiculo";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;                              
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                veiculos.add(
                    veiculo = new Veiculo(
                        rs.getInt("vei_cod"), 
                        rs.getString("vei_placa"), 
                        rs.getInt("mod_cod"), 
                        rs.getString("vei_cor"), 
                        rs.getInt("prop_cod")                       
                    )
                );            
        }catch(Exception e){
            System.out.println(e);
        }
                
        return veiculos;
    }
}
