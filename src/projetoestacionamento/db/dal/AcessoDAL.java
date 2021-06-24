package projetoestacionamento.db.dal;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import projetoestacionamento.db.entidade.Acesso;
import projetoestacionamento.db.util.Banco;


public class AcessoDAL implements IDAL <Acesso>{
    @Override
    public boolean insert(Acesso entidade) {
        String sql;
        sql="INSERT INTO acesso (vei_cod, ac_horaentrada, ac_valor) " +
             " VALUES (#1, '#2', '#3') ";
            
            sql = sql.replace("#1", String.valueOf(entidade.getVei_cod()));                            
            sql = sql.replace("#2", String.valueOf(entidade.getAc_horaentrada()));            
            sql = sql.replace("#3", String.valueOf(entidade.getAc_valor()));            
            
            System.out.println(sql);
            
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean update(Acesso entidade) {
        String sql;
        sql="UPDATE acesso SET ac_horasaida = '#1', ac_valor = '#2' WHERE ac_cod ="+entidade.getAc_cod();
        
        sql = sql.replace("#1", String.valueOf(entidade.getAc_horasaida()));
        sql = sql.replace("#2", String.valueOf(entidade.getAc_valor()));                
        
        System.out.println(sql);
        
        return Banco.getCon().manipular(sql);
    }

    @Override
    public boolean delete(Acesso entidade) {
        String sql="DELETE FROM acesso WHERE ac_cod ="+entidade.getAc_cod();
        return Banco.getCon().manipular(sql);
    }

    @Override
    public Acesso get(int id) {
        
        LocalDateTime hs=null;
        
        Acesso acesso =null;
        ResultSet rs=Banco.getCon().consultar("SELECT * FROM acesso WHERE ac_cod = "+id);
        try{
            if(rs.next())
            {
                if ( rs.getTimestamp("ac_horasaida")!=null)
                    hs=rs.getTimestamp("ac_horasaida").toLocalDateTime();
                acesso = new Acesso(
                        id,                        
                        rs.getTimestamp("ac_horaentrada").toLocalDateTime(),
                        hs,
                        rs.getDouble("ac_valor")
                );
            }    
        }catch(Exception e){System.out.println(e);}
        return acesso;
    }

    @Override
    public List<Acesso> get(String filtro) {
        
        List <Acesso> regs = new ArrayList();
        String sql = "select * from acesso";
        
        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }
        LocalDateTime he,hs;
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while (rs.next()) {
                he=hs=null;
                if ( rs.getTimestamp("ac_horaentrada")!=null)
                    he=rs.getTimestamp("ac_horaentrada").toLocalDateTime();
                if ( rs.getTimestamp("ac_horasaida")!=null)
                    hs=rs.getTimestamp("ac_horasaida").toLocalDateTime();
                
                Acesso reg;
                reg = new Acesso(
                        rs.getInt("ac_cod"),                        
                        he,
                        hs,
                        rs.getDouble("ac_valor")
                );
                regs.add(reg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return regs;
    }
}
