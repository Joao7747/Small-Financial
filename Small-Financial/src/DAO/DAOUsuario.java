package DAO;

import MODEL.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class DAOUsuario implements DAOGenerica<Usuario> {

    private ConexaoBanco conexao;

    public DAOUsuario() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (Nome, Email , Senha, "
                + "Saldo, Verif_ADM) VALUES (?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, usuario.getNome());
                sentenca.setString(2, usuario.getEmail());
                sentenca.setString(3, usuario.getSenha());
                sentenca.setDouble(4, usuario.getSaldo());
                sentenca.setBoolean(5, usuario.getVerif_ADM());
                

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Usuario usuario) {
        String sql = "UPDATE Usuario SET Nome = ? , Email = ? , Senha = ?, "
                + "Saldo = ?,Verif_ADM = ? WHERE idUsuario = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, usuario.getNome());
                sentenca.setString(2, usuario.getEmail());
                sentenca.setString(3, usuario.getSenha());
                sentenca.setDouble(4, usuario.getSaldo());
                sentenca.setBoolean(5, usuario.getVerif_ADM());
                sentenca.setInt(6, usuario.getIdUsuario());

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
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";

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
    public ArrayList<Usuario> consultar()
    {
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    
        String sql = "SELECT * FROM Usuario ORDER BY idUsuario";
        
        try
        {
            if(this.conexao.conectar())
            {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
                
                
                ResultSet resultadoSentenca = sentenca.executeQuery();

                
                while(resultadoSentenca.next()) 
                {
                    
                    Usuario usuario = new Usuario();
                    
                    usuario.setIdUsuario(resultadoSentenca.getInt("idUsuario"));
                    usuario.setNome(resultadoSentenca.getString("Nome"));
                    usuario.setEmail(resultadoSentenca.getString("Email"));
                    usuario.setSenha(resultadoSentenca.getString("Senha"));
                    usuario.setSaldo(resultadoSentenca.getDouble("Saldo"));
                    usuario.setVerif_ADM(resultadoSentenca.getBoolean("Verif_ADM"));
                   
                    listaUsuario.add(usuario);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }
            
            return listaUsuario;
        }
        catch(SQLException ex)
        {
           throw new RuntimeException(ex);
        }
    
    
    
    }
    public void UsuarioLogado(String email)
    {
        String sql = email;
//        try
//        {
//            if(this.conexao.conectar())
//            {
//                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);
//                
//                
//                ResultSet resultadoSentenca = sentenca.executeQuery();
//                System.out.println(resultadoSentenca);
//
//                sentenca.close();
//                this.conexao.getConnection().close();
//            }
//        }
//        catch(SQLException ex)
//        {
//           throw new RuntimeException(ex);
//        }

    }

}
