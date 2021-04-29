package MODEL;

import java.sql.Date;

/**
 *
 * @author joao
 */
public class Ganhos {

  
    private int idGanhos;
    private String categoria;
    private double valor;
    private Date dataGanho;
    private String observacao;
    private int idUsuario;

    
      /**
     * @return the idGanhos
     */
    public int getIdGanhos() {
        return idGanhos;
    }

    /**
     * @param idGanhos the idGanhos to set
     */
    public void setIdGanhos(int idGanhos) {
        this.idGanhos = idGanhos;
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
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the data
     */
    public Date getDataGanho() {
        return dataGanho;
    }

    /**
     * @param data the data to set
     */
    public void setDataGanho(Date dataGanho) {
        this.dataGanho = dataGanho;
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
