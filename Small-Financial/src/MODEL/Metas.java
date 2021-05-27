package MODEL;
import java.sql.Date;



/**
 *
 * @author joao
 */
public class Metas {

 

    private int idMetas;
    private String descricao;
    private Date dataPrevista;
    private Date dataRealizacao;
    private double custoTotal;
    private String categoria;
    private String observacao;
    private double valorGuardado;
    private byte statusMeta;
    private double valorIdealPoupar;
    private int idUsuario;
    private double percent;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    
       /**
     * @return the idMetas
     */
    public int getIdMetas() {
        return idMetas;
    }

    /**
     * @param idMetas the idMetas to set
     */
    public void setIdMetas(int idMetas) {
        this.idMetas = idMetas;
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
     * @return the dataPrevista
     */
    public Date getDataPrevista() {
        return dataPrevista;
    }

    /**
     * @param dataPrevista the dataPrevista to set
     */
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    /**
     * @return the dataRealizacao
     */
    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    /**
     * @param dataRealizacao the dataRealizacao to set
     */
    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    /**
     * @return the custoTotal
     */
    public double getCustoTotal() {
        return custoTotal;
    }

    /**
     * @param custoTotal the custoTotal to set
     */
    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
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
     * @return the valorGuardado
     */
    public double getValorGuardado() {
        return valorGuardado;
    }

    /**
     * @param valorGuardado the valorGuardado to set
     */
    public void setValorGuardado(double valorGuardado) {
        this.valorGuardado = valorGuardado;
    }

    /**
     * @return the status
     */
    public byte isStatusMeta() {
        return statusMeta;
    }

    /**
     * @param status the status to set
     */
    public void setStatusMeta(byte statusMeta) {
        this.statusMeta = statusMeta;
    }

    /**
     * @return the valorIdealPoupar
     */
    public double getValorIdealPoupar() {
        return valorIdealPoupar;
    }

    /**
     * @param valorIdealPoupar the valorIdealPoupar to set
     */
    public void setValorIdealPoupar(double valorIdealPoupar) {
        this.valorIdealPoupar = valorIdealPoupar;
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
