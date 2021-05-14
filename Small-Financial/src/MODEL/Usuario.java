package MODEL;


public class Usuario {

   
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private double saldo;
    private boolean verif_ADM;
    

     /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @param senha the senha to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param senha the senha to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getVerif_ADM() {
        return verif_ADM;
    }

    /**
     * @param senha the senha to set
     */
    public void setVerif_ADM(boolean verif_ADM) {
        this.verif_ADM = verif_ADM;
    }
  
    
}
