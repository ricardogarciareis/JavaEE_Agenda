<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-pt">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agenda de Contatos</title>
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Editar Contato</h1>
        <form name="frmContato" action="update"> <!-- chamada da action /update com onclick e execução da validar() -->
            <table>
                <tr>
                    <td><input type="text" name="tbxIdcon" placeholder="Id" class="tbx-caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="tbxNome" placeholder="Nome" class="tbx-caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="tbxTelefone" placeholder="200 000 000" class="tbx-caixa2" value="<%out.print(request.getAttribute("telefone"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="tbxTelemovel" placeholder="90 0000000" class="tbx-caixa2" value="<%out.print(request.getAttribute("telemovel"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="tbxEmail" placeholder="E-mail" class="tbx-caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
                </tr>
            </table>
            <input type="button" value="Salvar" class="btn-azul" onclick="validar()">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>
