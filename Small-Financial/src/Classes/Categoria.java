/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author arthu
 */
public class Categoria {
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return getCategoria();
    }
    
    
}
