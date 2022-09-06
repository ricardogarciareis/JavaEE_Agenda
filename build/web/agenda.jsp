<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
    /*
    for(JavaBeans contato : lista){
        out.print("[" + contato.getIdcon() + ", ");
        out.print(contato.getNome() + ", ");
        out.print(contato.getTelefone() + ", ");
        out.print(contato.getTelemovel() + ", ");
        out.print(contato.getEmail() + "]" + "<br>");
    }*/
%>

<!DOCTYPE html>
<html lang="pt-pt">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Contatos</title>
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Agenda de Contatos</h1>
        <a href="novo.html" class="btn-azul">Novo Contato</a>
        <a href="report" class="btn-vermelho">Relatório</a> <!-- chamada da action /report -->
        <table id="tabela">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Telemóvel</th>
                    <th>E-mail</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <%for(JavaBeans contato : lista){ %>
                    <tr>
                        <td><%=contato.getIdcon()%></td>
                        <td><%=contato.getNome()%></td>
                        <td><%=contato.getTelefone()%></td>
                        <td><%=contato.getTelemovel()%></td>
                        <td><%=contato.getEmail()%></td>
                        <td>
                            <a href="select?idcon=<%=contato.getIdcon()%>" class="btn-azul">Editar</a> <!-- chamada da action /select -->
                            <a href="javascript: confirmar(<%=contato.getIdcon()%>)" class="btn-vermelho">Eliminar</a> <!-- chamada da action /delete com confirmar() -->
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
        <script src="scripts/confirmador.js"></script>
    </body>
</html>
