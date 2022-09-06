package model;

/**
 * @version 1.0
 * @author Ricardo Reis
 */
public class JavaBeans {
    
    //Propriedades

    /** The idcon */
    private String idcon;

    /** The nome */
    private String nome;

    /** The telefone */
    private String telefone;

    /** The telemovel */
    private String telemovel;

    /** The email */
    private String email;

    
    /**
     * Construtor 1
     */
    public JavaBeans() {
        super();
    }

    /**
     * Construtor 2
     * @param idcon
     * @param nome
     * @param telefone
     * @param telemovel
     * @param email
     */
    public JavaBeans(String idcon, String nome, String telefone, String telemovel, String email) {
        super();
        this.idcon = idcon;
        this.nome = nome;
        this.telefone = telefone;
        this.telemovel = telemovel;
        this.email = email;
    }
    
    //Getters

    /**
     * idcon Getter
     * @return
     */
    public String getIdcon() {
        return idcon;
    }

    /**
     * nome Getter
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * telefone Getter
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * telemovel Getter
     * @return
     */
    public String getTelemovel() {
        return telemovel;
    }

    /**
     * email Getter
     * @return
     */
    public String getEmail() {
        return email;
    }

    //Setters

    /**
     * idcon Setter
     * @param idcon
     */
    public void setIdcon(String idcon) {
        this.idcon = idcon;
    }

    /**
     * nome Setter
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * telefone Setter
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * telemovel Setter
     * @param telemovel
     */
    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    /**
     * email Setter
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
