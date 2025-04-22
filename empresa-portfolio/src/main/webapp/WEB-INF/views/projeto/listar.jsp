<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Projetos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Lista de Projetos</h2>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <a href="${pageContext.request.contextPath}/projetos/novo" class="btn btn-primary mb-3">Novo Projeto</a>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Gerente</th>
                        <th>Status</th>
                        <th>Risco</th>
                        <th>Início</th>
                        <th>Previsão Fim</th>
                        <th>Data Fim Real</th>
                        <th>Orçamento</th>
                        <th>Descrição</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${projetos}" var="projeto">
                        <tr>
                            <td>${projeto.id}</td>
                            <td>${projeto.nome}</td>
                            <td>${projeto.gerente.nome}</td>
                            <td>${projeto.status}</td>
                            <td>${projeto.risco}</td>
                            <td>${projeto.dataInicio}</td>
                            <td>${projeto.dataPrevisaoFim}</td>
                            <td>${projeto.dataFim}</td>
                            <td>R$ ${projeto.orcamento}</td>
                            <td>${projeto.descricao}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/projetos/editar/${projeto.id}" class="btn btn-sm btn-warning">Editar</a>
                                <a href="${pageContext.request.contextPath}/projetos/excluir/${projeto.id}" class="btn btn-sm btn-danger">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>