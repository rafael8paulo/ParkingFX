package projetoestacionamento.db.dal;

import java.util.List;

public interface IDAL <T>{
    public boolean insert(T entidade);
    public boolean update(T entidade);
    public boolean delete(T entidade);
    public T get(int id);
    public List<T> get(String filtro);    
}
