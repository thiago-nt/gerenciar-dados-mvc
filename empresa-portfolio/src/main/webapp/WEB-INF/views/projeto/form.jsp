<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${projeto.id == null ? "Novo Projeto" : "Editar Projeto"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">${projeto.id == null ? "Novo Projeto" : "Editar Projeto"}</h2>
    <form:form method="post" modelAttribute="projeto" action="${pageContext.request.contextPath}/projetos/salvar" cssClass="needs-validation" novalidate="true">
        <form:hidden path="id"/>
        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <form:input path="nome" cssClass="form-control" id="nome"/>
        </div>
        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição</label>
            <form:textarea path="descricao" cssClass="form-control" id="descricao" rows="3"/>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="dataInicio" class="form-label">Data de Início</label>
                <form:input path="dataInicio" type="date" cssClass="form-control" id="dataInicio"/>
            </div>
            <div class="col-md-6 mb-3">
                <label for="dataFim" class="form-label">Data de Fim</label>
                <form:input path="dataFim" type="date" cssClass="form-control" id="dataFim"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="orcamento" class="form-label">Orçamento Total</label>
                <form:input path="orcamento" type="number" step="0.01" cssClass="form-control" id="orcamento"/>
            </div>
            <div class="col-md-6 mb-3">
                <label for="dataPrevisaoFim" class="form-label">Previsão de Término</label>
                <form:input path="dataPrevisaoFim" type="date" cssClass="form-control" id="dataPrevisaoFim"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <form:select path="status" cssClass="form-select" id="status">
                <form:option value="" label="Selecione o status"/>
                <form:option value="Planejado" />
                <form:option value="Iniciado" />
                <form:option value="Em Andamento" />
                <form:option value="Encerrado" />
            </form:select>
        </div>
        <div class="mb-4">
            <label for="gerente" class="form-label">Gerente</label>
            <form:select path="gerente.id" cssClass="form-select" id="gerente">
                <form:option value="" label="Selecione o gerente"/>
                <c:forEach var="g" items="${gerentes}">
                    <form:option value="${g.id}" label="${g.nome}"/>
                </c:forEach>
            </form:select>
        </div>
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="${pageContext.request.contextPath}/projetos" class="btn btn-secondary">Cancelar</a>
        </div>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>