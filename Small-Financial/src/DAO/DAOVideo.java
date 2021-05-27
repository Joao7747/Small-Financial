package DAO;

import MODEL.Video;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOVideo implements DAOGenerica<Video> {

    private ConexaoBanco conexao;

    public DAOVideo() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Video video) {
        String sql = "INSERT INTO Video (Descricao, Link) VALUES (?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, video.getDescricao());
                sentenca.setString(2, video.getLink());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Video video) {
        String sql = "UPDATE Video SET Descricao = ? , Link = ? WHERE idVideo = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, video.getDescricao());
                sentenca.setString(2, video.getLink());
                sentenca.setInt(3, video.getIdVideo());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Video WHERE idVideo = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setInt(1, id);

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Video> consultar(int id)
    {
    ArrayList<Video> listaVideo = new ArrayList<Video>();
    
        String sql = "SELECT * FROM Video WHERE idVideo = ?";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                sentenca.setInt(1, id);
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Video video = new Video();
                    
                    video.setIdVideo(resultadoSentenca.getInt("idVideo"));
                    video.setDescricao(resultadoSentenca.getString("Descricao"));
                    video.setLink(resultadoSentenca.getString("Link"));
                    
                    listaVideo.add(video);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaVideo;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
