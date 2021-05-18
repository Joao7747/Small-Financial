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
public class Siglas {
    private String Siglas;
    private String Descricao;

    public Siglas(String Siglas, String Descricao) {
        this.Siglas = Siglas;
        this.Descricao = Descricao;
    }
    
    

    public String getSiglas() {
        return Siglas;
    }

    public void setSiglas(String Siglas) {
        this.Siglas = Siglas;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    
    
}
