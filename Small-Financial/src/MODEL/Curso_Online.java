package MODEL;

import java.sql.Date;



/**
 *
 * @author Dell
 */
public class Curso_Online {

    
    private int idCurso_Online;
    private String nome;
    private String descricao;
    private Date dataLimite;
    private String link;
    private boolean statusCursoOnline;
    /**
     * @return the idCurso_Online
     */
    public int getIdCurso_Online() {
        return idCurso_Online;
    }

    /**
     * @param idCurso_Online the idCurso_Online to set
     */
    public void setIdCurso_Online(int idCurso_Online) {
        this.idCurso_Online = idCurso_Online;
    }

/**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the data
     */
    public Date getDataLimite() {
        return dataLimite;
    }

    /**
     * @param data the data to set
     */
    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the status
     */
    public boolean isStatusCursoOnline() {
        return statusCursoOnline;
    }

    /**
     * @param status the status to set
     */
    public void setStatusCursoOnline(boolean statusCursoOnline) {
        this.statusCursoOnline = statusCursoOnline;
    }
    

}
