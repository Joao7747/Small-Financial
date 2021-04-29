/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MODEL;

/**
 *
 * @author joao
 */
public class Video {

    private int idVideo;
    private String descricao;
    private String link;

    
    /**
     * @return the idVideo
     */
    public int getIdVideo() {
        return idVideo;
    }

    /**
     * @param idVideo the idVideo to set
     */
    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
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


}
