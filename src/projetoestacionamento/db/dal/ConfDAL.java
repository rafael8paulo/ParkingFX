package projetoestacionamento.db.dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.entidade.Conf;
import projetoestacionamento.db.util.Banco;


public class ConfDAL implements IDAL <Conf>{
     @Override
    public boolean insert(Conf entidade) {
        String sql;
        sql="INSERT INTO conf (conf_valorhora, conf_valoradic, conf_carencia) " +
             " VALUES ('#1', '#2', '#3') ";
            
            sql = sql.replace("#1", Double.toString(entidade.getConf_valorhora()));
            sql = sql.replace("#2", Double.toString(entidade.getConf_valoradic()));
            sql = sql.replace("#3", Double.toString(entidade.getConf_carencia()));            
            
            System.out.println(sql);
            
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Conf entidade) {
        String sql;
        sql="UPDATE conf SET conf_valorhora = '#1', conf_valoradic = '#2', conf_carencia = '#3' WHERE conf_valorhora = '#1'";
        
        sql = sql.replace("#1", Double.toString(entidade.getConf_valorhora()));
        sql = sql.replace("#2", Double.toString(entidade.getConf_valoradic()));
        sql = sql.replace("#3", Double.toString(entidade.getConf_carencia()));
        
        System.out.println(sql);
        
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Conf entidade) {
        String sql="DELETE FROM conf WHERE conf_valorhora = '"+entidade.getConf_valorhora()+"';";
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Conf get(int id) {
        Conf conf =null;
        ResultSet rs=Banco.getCon().consultar("SELECT * FROM veiculo WHERE conf_valorhora =  "+id);
        try{
            if(rs.next())
            {
                conf = new Conf(
                        rs.getDouble("conf_valorhora"), 
                        rs.getDouble("conf_valoradic"), 
                        rs.getDouble("conf_carencia")
                );
            }    
        }catch(Exception e){System.out.println(e);}
        return conf;
    }

    @Override
    public List<Conf> get(String filtro) {
        
        Conf conf =null;
        List<Conf> confs = new ArrayList();
        
        String sql="SELECT * FROM conf";        
        if (!filtro.isEmpty())
            sql+=" WHERE "+filtro;              
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try{
            while(rs.next())
                confs.add(
                    conf = new Conf(
                        rs.getDouble("conf_valorhora"), 
                        rs.getDouble("conf_valoradic"), 
                        rs.getDouble("conf_carencia")
                    )
                );            
        }catch(Exception e){
            System.out.println(e);
        }
                
        return confs;
    }
}
