
package DAO;

import java.util.ArrayList;


public interface DAOGenerica<ObjetoGenerico> {
    
    public void inserir(ObjetoGenerico objt);
    
    public void alterar(ObjetoGenerico objt);
    
    public void excluir(int id);
    
    public ArrayList<ObjetoGenerico> consultar();
    
}
