package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

//ctrl+shift+i

/**
 * @version 1.0
 * @author Ricardo Reis
 */

@WebServlet(
        urlPatterns = {"/controller", "/main", "/insert", "/select", "/update", "/delete", "/report"}
)
public class Controller extends HttpServlet {
    
    /** The serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** Criação do objeto dao */
    DAO dao = new DAO();

    /** Criação do objeto contato */
    JavaBeans contato = new JavaBeans();

    /** Construtor */
    public Controller(){
        super();
    }
    
    /**
     * Método doGet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
        
    }

    /**
     * Processador de Requests
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/

        //Teste de conexão à base de dados
        //dao.testeConexao();
        
        String action = request.getServletPath();
        System.out.println("Acao Requerida\n------------------------------------");
        System.out.println(action);
        
        switch (action) {
            case "/main":
                contatos(request, response);
                break;
            case "/insert":
                adicionarContato(request, response);
                break;
            case "/select":
                listarContato(request, response);
                break;
            case "/update":
                editarContato(request, response);
                break;
            case "/delete":
                removerContato(request, response);
                break;
            case "/report":
                gerarRelatorio(request, response);
                break;
            default:
                response.sendRedirect("index.html");
                break;
        }
    }

    //Métodos

    /**
     * Listar Contatos
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.sendRedirect("agenda.jsp");
        
        //Criação de objeto que receberá os dados JavaBeans
        ArrayList<JavaBeans> lista = dao.listarContatos();
        /*
        for(JavaBeans contato : lista){
            System.out.printf("| %4s | %-20s | %9s | %9s | %30s |%n", 
                    contato.getIdcon(),
                    contato.getNome(), 
                    contato.getTelefone(), 
                    contato.getTelemovel(),
                    contato.getEmail()
            );
        }*/
        
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }
    
    /**
     * Criar Novo Contato
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Teste de receção dos dados do formulário
        //System.out.println("Novo Contato\n--------------------------------------");
        //System.out.println("Nome: " + request.getParameter("tbxNome"));
        //System.out.println("Telefone: " + request.getParameter("tbxTelefone"));
        //System.out.println("Telemovel: " + request.getParameter("tbxTelemovel"));
        //System.out.println("E-mail: " + request.getParameter("tbxEmail"));
        
        //Set das propriedades do objeto contato
        contato.setNome(request.getParameter("tbxNome"));
        contato.setTelefone(request.getParameter("tbxTelefone"));
        contato.setTelemovel(request.getParameter("tbxTelemovel"));
        contato.setEmail(request.getParameter("tbxEmail"));
        
        //Teste da obtenção das propriedades do objeto contato
        //System.out.println("Novo Contato\n--------------------------------------");
        //System.out.println("Nome: " + contato.getNome());
        //System.out.println("Telefone: " + contato.getTelefone());
        //System.out.println("Telemovel: " + contato.getTelemovel());
        //System.out.println("E-mail: " + contato.getEmail());
        
        //Invocar o método inserirContato() com passagem do objeto contato
        dao.inserirContato(contato);
        
        response.sendRedirect("main");
    }
    
    /**
     * Mostrar Dados do Contato
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Receção do id do contato que será editado
        String idcon = request.getParameter("idcon");
        
        //Teste da seleção do id do contato
        //System.out.println("Id do Contato a ser editado\n-----------------------");
        //System.out.println("ID: " + idcon);
        
        //Set da propriedade id do objeto contato
        contato.setIdcon(idcon);
        
        //Invocar o método selecionarContato() com passagem do objeto contato
        dao.selecionarContato(contato);
        
        //Teste da obtenção das propriedades do objeto contato
        //System.out.println("Contato Selecionado\n-------------------------------");
        //System.out.println("ID: " + contato.getIdcon());
        //System.out.println("Nome: " + contato.getNome());
        //System.out.println("Telefone: " + contato.getTelefone());
        //System.out.println("Telemovel: " + contato.getTelemovel());
        //System.out.println("E-mail: " + contato.getEmail());
        
        //Envio do conteúdo do objeto contato para os campos do formulário
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("telefone", contato.getTelefone());
        request.setAttribute("telemovel", contato.getTelemovel());
        request.setAttribute("email", contato.getEmail());
        
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }

    /**
     * Editar Dados do Contato
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Teste de receção dos dados do formulário
        //System.out.println("Editar Contato\n------------------------------------");
        //System.out.println("ID: " + request.getParameter("tbxIdcon"));
        //System.out.println("Nome: " + request.getParameter("tbxNome"));
        //System.out.println("Telefone: " + request.getParameter("tbxTelefone"));
        //System.out.println("Telemovel: " + request.getParameter("tbxTelemovel"));
        //System.out.println("E-mail: " + request.getParameter("tbxEmail"));
        
        //Set das propriedades do objeto contato
        contato.setIdcon(request.getParameter("tbxIdcon"));
        contato.setNome(request.getParameter("tbxNome"));
        contato.setTelefone(request.getParameter("tbxTelefone"));
        contato.setTelemovel(request.getParameter("tbxTelemovel"));
        contato.setEmail(request.getParameter("tbxEmail"));
        
        //Invocar o método alterarContato() com passagem do objeto contato
        dao.alterarContato(contato);
        
        response.sendRedirect("main");
    }

    /**
     * Remover Contato
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void removerContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Receção do id do contato que será removido
        String idcon = request.getParameter("idcon");
        
        //Teste de receção dos dados do formulário
        //System.out.println("Remover Contato\n-----------------------------------");
        //System.out.println("ID: " + idcon);
        
        //Set das propriedades do objeto contato
        contato.setIdcon(idcon);
        
        //Invocar o método alterarContato() com passagem do objeto contato
        dao.apagarContato(contato);
        
        response.sendRedirect("main");
    }

    /**
     * Gerar Relatório em PDF
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Document documento = new Document();
        try{
            response.setContentType("application/pdf");                                     //Tipo de Documento
            response.addHeader("Content-Disposition", "inline; filename='contatos.pdf'");   //Nome do Documento
            PdfWriter.getInstance(documento, response.getOutputStream());
            documento.open();
            documento.add(new Paragraph("Lista de Contatos"));          //Criar parágrafo com texto
            documento.add(new Paragraph(" "));                          //Criar parágrafo sem texto
            PdfPTable tabela = new PdfPTable(5);                        //Criar objeto tabela com 5 colunas
            PdfPCell col1 = new PdfPCell(new Paragraph("ID"));          //Criar cabeçalho da primeira coluna
            PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));        //Criar cabeçalho da segunda coluna
            PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));    //Criar cabeçalho da terceira coluna
            PdfPCell col4 = new PdfPCell(new Paragraph("Telemóvel"));   //Criar cabeçalho da quarta coluna
            PdfPCell col5 = new PdfPCell(new Paragraph("E-mail"));      //Criar cabeçalho da quinta coluna
            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);
            tabela.addCell(col4);
            tabela.addCell(col5);
            
            ArrayList<JavaBeans> lista = dao.listarContatos();
            for (JavaBeans linha : lista) {
                tabela.addCell(linha.getIdcon());
                tabela.addCell(linha.getNome());
                tabela.addCell(linha.getTelefone());
                tabela.addCell(linha.getTelemovel());
                tabela.addCell(linha.getEmail());
            }
            
            documento.add(tabela);
        }
        catch (DocumentException | IOException e) {
            System.out.println("Excecao na geracao de relatorio\n-------------------");
            System.out.println("Mensagem: " + e.getMessage());
        }
        finally{
            documento.close();
        }
    }
    
}
