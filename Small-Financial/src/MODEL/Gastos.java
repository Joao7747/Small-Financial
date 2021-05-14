package MODEL;

import java.sql.Date;



/**
 *
 * @author joao
 */
public class Gastos {

 
    private int idGastos;
    private String categoria;
    private double preco;
    private Date dataGasto;
    private String observacao;
    private int idUsuario;

    
       /**
     * @return the idGastos
     */
    public int getIdGastos() {
        return idGastos;
    }

    /**
     * @param idGastos the idGastos to set
     */
    public void setIdGastos(int idGastos) {
        this.idGastos = idGastos;
    }

/**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the data
     */
    public Date getDataGasto() {
        return dataGasto;
    }

    /**
     * @param data the data to set
     */
    public void setDataGasto(Date dataGasto) {
        this.dataGasto = dataGasto;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the usuario_idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param usuario_idUsuario the usuario_idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
