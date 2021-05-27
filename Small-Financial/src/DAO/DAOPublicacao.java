package DAO;

import MODEL.Publicacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DAOPublicacao implements DAOGenerica<Publicacao> {

    private ConexaoBanco conexao;

    public DAOPublicacao() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Publicacao publicacao) {
        String sql = "INSERT INTO Publicacao (Titulo, Conteudo , Autor, "
                + "DataPublicacao) VALUES (?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, publicacao.getTitulo());
                sentenca.setString(2, publicacao.getConteudo());
                sentenca.setString(3, publicacao.getAutor());
                sentenca.setDate(4, publicacao.getDataPublicacao());
                

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Publicacao publicacao) {
        String sql = "UPDATE Publicacao SET Titulo = ? , Conteudo = ? , Autor = ?, "
                + "DataPublicacao = ?  WHERE idPublicacao = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, publicacao.getTitulo());
                sentenca.setString(2, publicacao.getConteudo());
                sentenca.setString(3, publicacao.getAutor());
                sentenca.setDate(4, publicacao.getDataPublicacao());
                sentenca.setInt(5,publicacao.getIdPublicacao());

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
        String sql = "DELETE FROM Publicacao WHERE idPublicacao = ?";

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
    public ArrayList<Publicacao> consultar(int id)
    {
    ArrayList<Publicacao> listaPublicacao = new ArrayList<Publicacao>();
    
        String sql = "SELECT * FROM Publicacao WHERE idPublicacao = ?";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                sentenca.setInt(1, id);
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Publicacao publicacao = new Publicacao();
                    
                    publicacao.setIdPublicacao(resultadoSentenca.getInt("idPublicacao"));
                    publicacao.setTitulo(resultadoSentenca.getString("Titulo"));
                    publicacao.setConteudo(resultadoSentenca.getString("Conteudo"));
                    publicacao.setAutor(resultadoSentenca.getString("Autor"));
                    publicacao.setDataPublicacao(resultadoSentenca.getDate("DataPublicacao"));
                    
                    listaPublicacao.add(publicacao);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaPublicacao;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }

}
