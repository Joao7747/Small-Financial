package DAO;

import MODEL.Curso_Online;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import telassmallfinancial.MenuCursosController;

public class DAOCurso_Online implements DAOGenerica<Curso_Online> {

    private ConexaoBanco conexao;

    public DAOCurso_Online() {
        this.conexao = new ConexaoBanco();
    }

    @Override
    public void inserir(Curso_Online curso_Online) {
        String sql = "INSERT INTO Curso_Online (Nome, Descricao , Data_Limite, "
                + "Link, Status_Curso_Online) VALUES (?,?,?,?,?)";

        try {

            if (this.conexao.conectar()) {

                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, curso_Online.getNome());
                sentenca.setString(2, curso_Online.getDescricao());
                sentenca.setDate(3, curso_Online.getDataLimite());
                sentenca.setString(4, curso_Online.getLink());
                sentenca.setBoolean(5, curso_Online.isStatusCursoOnline());

                sentenca.execute();
                sentenca.close();
                this.conexao.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void alterar(Curso_Online curso_Online) {
        String sql = "UPDATE Curso_Online SET Nome = ? , Descricao = ? , Data_Limite = ?, "
                + "Link = ?, Status_Curso_Online = ? WHERE idCurso_Online = ?";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                sentenca.setString(1, curso_Online.getNome());
                sentenca.setString(2, curso_Online.getDescricao());
                sentenca.setDate(3, curso_Online.getDataLimite());
                sentenca.setString(4, curso_Online.getLink());
                sentenca.setBoolean(5, curso_Online.isStatusCursoOnline());
                sentenca.setInt(6, curso_Online.getIdCurso_Online());

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
        String sql = "DELETE FROM Curso_Online WHERE idCurso_Online = ?";

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
    public ArrayList<Curso_Online> consultar() {
        ArrayList<Curso_Online> listaCurso_Online = new ArrayList<Curso_Online>();

        String sql = "SELECT * FROM Curso_Online ORDER BY idCurso_Online";

        try {
            if (this.conexao.conectar()) {
                PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                ResultSet resultadoSentenca = sentenca.executeQuery();

                while (resultadoSentenca.next()) {

                    Curso_Online curso_Online = new Curso_Online();

                    curso_Online.setIdCurso_Online(resultadoSentenca.getInt("idCurso_Online"));
                    curso_Online.setNome(resultadoSentenca.getString("Nome"));
                    curso_Online.setDescricao(resultadoSentenca.getString("Descricao"));
                    curso_Online.setDataLimite(resultadoSentenca.getDate("Data_Limite"));
                    curso_Online.setLink(resultadoSentenca.getString("Link"));
                    curso_Online.setStatusCursoOnline(resultadoSentenca.getBoolean("Status_Curso_Online"));

                    listaCurso_Online.add(curso_Online);
                }

                sentenca.close();
                this.conexao.getConnection().close();
            }

            return listaCurso_Online;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public ArrayList<Curso_Online> buscaParametrizada() {

        ArrayList<Curso_Online> listaparam = new ArrayList<Curso_Online>();

        MenuCursosController menu = new MenuCursosController();

        if (menu.txtPesquisaCurso.equals("Id")) {
            String sql = "SELECT * FROM Curso_Online WHERE idCurso_Online = ?";

            try {
                if (this.conexao.conectar()) {
                    PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                    ResultSet resultadoSentenca = sentenca.executeQuery();

                    while (resultadoSentenca.next()) {

                        Curso_Online curso_Online = new Curso_Online();

                        curso_Online.setIdCurso_Online(resultadoSentenca.getInt("idCurso_Online"));
                        curso_Online.setNome(resultadoSentenca.getString("Nome"));
                        curso_Online.setDescricao(resultadoSentenca.getString("Descricao"));
                        curso_Online.setDataLimite(resultadoSentenca.getDate("Data_Limite"));
                        curso_Online.setLink(resultadoSentenca.getString("Link"));
                        curso_Online.setStatusCursoOnline(resultadoSentenca.getBoolean("Status_Curso_Online"));

                        listaparam.add(curso_Online);
                    }

                    sentenca.close();
                    this.conexao.getConnection().close();
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (menu.txtPesquisaCurso.equals("Nome")) {
            String sql = "SELECT * FROM Curso_Online WHERE Nome LIKE %?% ";

            try {
                if (this.conexao.conectar()) {
                    PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                    ResultSet resultadoSentenca = sentenca.executeQuery();

                    while (resultadoSentenca.next()) {

                        Curso_Online curso_Online = new Curso_Online();

                        curso_Online.setIdCurso_Online(resultadoSentenca.getInt("idCurso_Online"));
                        curso_Online.setNome(resultadoSentenca.getString("Nome"));
                        curso_Online.setDescricao(resultadoSentenca.getString("Descricao"));
                        curso_Online.setDataLimite(resultadoSentenca.getDate("Data_Limite"));
                        curso_Online.setLink(resultadoSentenca.getString("Link"));
                        curso_Online.setStatusCursoOnline(resultadoSentenca.getBoolean("Status_Curso_Online"));

                        listaparam.add(curso_Online);
                    }

                    sentenca.close();
                    this.conexao.getConnection().close();
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (menu.txtPesquisaCurso.equals("Descrição")) {
            String sql = "SELECT * FROM Curso_Online WHERE Descricao LIKE %?% ";

            try {
                if (this.conexao.conectar()) {
                    PreparedStatement sentenca = this.conexao.getConnection().prepareStatement(sql);

                    ResultSet resultadoSentenca = sentenca.executeQuery();

                    while (resultadoSentenca.next()) {

                        Curso_Online curso_Online = new Curso_Online();

                        curso_Online.setIdCurso_Online(resultadoSentenca.getInt("idCurso_Online"));
                        curso_Online.setNome(resultadoSentenca.getString("Nome"));
                        curso_Online.setDescricao(resultadoSentenca.getString("Descricao"));
                        curso_Online.setDataLimite(resultadoSentenca.getDate("Data_Limite"));
                        curso_Online.setLink(resultadoSentenca.getString("Link"));
                        curso_Online.setStatusCursoOnline(resultadoSentenca.getBoolean("Status_Curso_Online"));

                        listaparam.add(curso_Online);
                    }

                    sentenca.close();
                    this.conexao.getConnection().close();
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

        return listaparam;

    }

}
