package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @version 1.0
 * @author Ricardo Reis
 */
public class DAO {
    
    //Parâmetros de Conexão

    /** The driver */
    private final String driver = "com.mysql.cj.jdbc.Driver";

    /** The ip */
    private final String ip = "192.168.1.50";

    /** The port */
    private final String port = "3306";

    /** The db */
    private final String db = "dbagenda";

    /** The url */
    private final String url = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?"
            + "useTimezone=true&serverTimezone=UTC";

    /** The user */
    private final String user = "admin";

    /** The password */
    private final String password = "Xpto123_";
    
    
    /**
     * Método de Conexão
     * @return
     */
    private Connection conectar() {
        Connection con; 
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Excecao no metodo conectar()\n----------------------");
            System.out.println("Erro de conexao a base de dados: " + e);
            return null;
        }
    }
    
    /**
     * Método para Teste de Conexão
     */
    public void testeConexao() {
        try(Connection con = conectar();){
            //Connection con = conectar();
            System.out.println(con);
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Excecao no metodo testeConexao()\n------------------");
            System.out.println("Erro de conexao a base de dados: " + e);
        }
    }
    
    /**
     * CRUD - CREATE
     * @param contato
     */
    public void inserirContato(JavaBeans contato) {
        String sqlCmd = "INSERT INTO contatos (nome, telefone, telemovel, email) VALUES (?,?,?,?);"; 

        try(Connection con = conectar();){
            //Connection con = conectar();
            
            PreparedStatement pst = con.prepareStatement(sqlCmd);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getTelefone());
            pst.setString(3, contato.getTelemovel());
            pst.setString(4, contato.getEmail());
            pst.executeUpdate();
            
            con.close();
        }
        catch (SQLException e) {
            while (e != null) {
                
                System.out.println("Excecao no metodo inserirContato()\n----------------");
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("SQLEstado: " + e.getSQLState());
                System.out.println("Codigo do erro: " + e.getErrorCode());
                System.out.println(" ");
                e = e.getNextException();
            }
        }
    }
    
    /**
     * CRUD - READ
     * @return
     */
    public ArrayList<JavaBeans> listarContatos() {
        ArrayList<JavaBeans> contatos = new ArrayList<>();
        String sqlCmd = "SELECT * FROM contatos ORDER BY nome;";
        
        try(Connection con = conectar();){
            //Connection con = conectar();
            
            PreparedStatement pst = con.prepareStatement(sqlCmd);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String idcon = rs.getString(1);
                String nome = rs.getString(2);
                String telefone = rs.getString(3);
                String telemovel = rs.getString(4);
                String email = rs.getString(5);
                
                contatos.add(new JavaBeans(idcon, nome, telefone, telemovel, email));
            }
            con.close();
            return contatos;
        }
        catch (SQLException e) {
            while (e != null) {
                System.out.println("Excecao no metodo listarContato()\n-----------------");
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("SQLEstado: " + e.getSQLState());
                System.out.println("Codigo do erro: " + e.getErrorCode());
                System.out.println(" ");
                e = e.getNextException();
            }
            return null;
        }
    }
    
    /**
     * CRUD - UPDATE (SELECT)
     * @param contato
     */
    public void selecionarContato(JavaBeans contato) {
        String sqlCmd = "SELECT * FROM contatos WHERE idcon=?;";
        
        try(Connection con = conectar();){
            //Connection con = conectar();
            
            PreparedStatement pst = con.prepareStatement(sqlCmd);
            pst.setString(1, contato.getIdcon());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                contato.setIdcon(rs.getString(1));
                contato.setNome(rs.getString(2));
                contato.setTelefone(rs.getString(3));
                contato.setTelemovel(rs.getString(4));
                contato.setEmail(rs.getString(5));
            }
            
            con.close();
        }
        catch (SQLException e) {
            while (e != null) {
                System.out.println("Excecao no metodo selecionarContato()\n-------------");
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("SQLEstado: " + e.getSQLState());
                System.out.println("Codigo do erro: " + e.getErrorCode());
                System.out.println(" ");
                e = e.getNextException();
            }
        }
    }
    
    /**
     * CRUD - UPDATE (UPDATE)
     * @param contato
     */
    public void alterarContato(JavaBeans contato) {
        String sqlCmd = "UPDATE contatos SET nome=?, telefone=?, telemovel=?, email=? WHERE idcon=?;"; 
        
        try(Connection con = conectar();){
            //Connection con = conectar();
            
            PreparedStatement pst = con.prepareStatement(sqlCmd);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getTelefone());
            pst.setString(3, contato.getTelemovel());
            pst.setString(4, contato.getEmail());
            pst.setString(5, contato.getIdcon());
            pst.executeUpdate();
            
            con.close();
        }
        catch (SQLException e) {
            while (e != null) {
                System.out.println("Excecao no metodo alterarContato()\n----------------");
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("SQLEstado: " + e.getSQLState());
                System.out.println("Codigo do erro: " + e.getErrorCode());
                System.out.println(" ");
                e = e.getNextException();
            }
        }
    }
    
    /**
     * CRUD - DELETE
     * @param contato
     */
    public void apagarContato(JavaBeans contato) {
        String sqlCmd = "DELETE FROM contatos WHERE idcon=?;";
        String sqlCmd2 = "SELECT MAX(idcon) FROM contatos;";
        String sqlCmd3 = "ALTER TABLE contatos AUTO_INCREMENT = ";
        
        try(Connection con = conectar();){
            //Connection con = conectar();
            
            PreparedStatement pst = con.prepareStatement(sqlCmd);
            pst.setString(1, contato.getIdcon());
            pst.executeUpdate();
            
            pst = con.prepareStatement(sqlCmd2);
            ResultSet rs = pst.executeQuery();
            int ultId = 0;
            while(rs.next()){
                ultId = rs.getInt(1);
            }
            System.out.println("Maximo ID: " + ultId);
            
            Statement stm = con.createStatement();
            stm.execute(sqlCmd3 + ultId);
            
            con.close();
        }
        catch (SQLException e) {
            while (e != null) {
                System.out.println("Excecao no metodo apagarContato()\n-----------------");
                System.out.println("Mensagem: " + e.getMessage());
                System.out.println("SQLEstado: " + e.getSQLState());
                System.out.println("Codigo do erro: " + e.getErrorCode());
                System.out.println(" ");
                e = e.getNextException();
            }
        }
    }
    
}
