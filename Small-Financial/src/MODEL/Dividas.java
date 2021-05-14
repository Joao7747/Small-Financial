package MODEL;

import java.sql.Date;



/**
 *
 * @author joao
 */
public class Dividas {

    private int idDividas;
    private String categoria;
    private double valor;
    private Date vencimento;
    private String descricao;
    private boolean parcelado;
    private int numeroParcelas;
    private boolean fixa;
    private String observacao;
    private int idUsuario;

    /**
     * @return the idDividas
     */
    public int getIdDividas() {
        return idDividas;
    }

    /**
     * @param idDividas the idDividas to set
     */
    public void setIdDividas(int idDividas) {
        this.idDividas = idDividas;
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
     * @return the vencimento
     */
    public Date getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the parcelado
     */
    public boolean isParcelado() {
        return parcelado;
    }

    /**
     * @param parcelado the parcelado to set
     */
    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    /**
     * @return the numeroParcelas
     */
    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    /**
     * @param numeroParcelas the numeroParcelas to set
     */
    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    /**
     * @return the fixa
     */
    public boolean isFixa() {
        return fixa;
    }

    /**
     * @param fixa the fixa to set
     */
    public void setFixa(boolean fixa) {
        this.fixa = fixa;
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
