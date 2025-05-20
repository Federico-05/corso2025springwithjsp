<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${corso.id == null ? 'Nuovo Corso' : 'Modifica Corso'}</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">
        <img src="/images/logo.jfif" alt="Logo" style="height: 40px; margin-right: 10px;">
        <a class="navbar-brand" href="#">Academy 2025</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/docenti/lista'/>">Docenti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/discenti/lista'/>">Discenti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/corsi/lista'/>">Corsi</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h1>${corso.id == null ? 'Nuovo Corso' : 'Modifica Corso'}</h1>

<div class="card">
    <div class="card-body">

        <c:choose>
            <c:when test="${corso.id == null}">
                <c:url var="actionUrl" value="/corsi/salva" />
            </c:when>
            <c:otherwise>
                <c:url var="actionUrl" value="/corsi/${corso.id}/salva" />
            </c:otherwise>
        </c:choose>

        <form:form action="${actionUrl}" method="post" modelAttribute="corso">
            <form:input path="id" type="hidden" />

            <!-- Nome del Corso -->
            <div class="mb-3">
                <form:label path="nome" class="form-label">Nome del Corso:</form:label>
                <form:input path="nome" class="form-control" required="true" />
                <form:errors path="nome" cssClass="text-danger" />
            </div>

            <!-- Anno Accademico -->
            <div class="mb-3">
                <form:label path="annoAccademico" class="form-label">Anno Accademico:</form:label>
                <form:input path="annoAccademico" type="number" min="2020" max="2030" class="form-control" required="true" />
                <form:errors path="annoAccademico" cssClass="text-danger" />
            </div>

            <!-- Scelta del Docente -->
            <div class="mb-3">
                <form:label path="docenteId" class="form-label">Docente:</form:label>
                <form:select path="docenteId" class="form-select" required="true">
                    <form:option value="" label="-- Seleziona un docente --" />
                    <c:forEach var="docente" items="${docenti}">
                        <form:option value="${docente.id}" label="${docente.nome} ${docente.cognome}" />
                    </c:forEach>
                </form:select>
                <form:errors path="docenteId" cssClass="text-danger" />
            </div>

            <!-- Tabella con checkbox per selezionare i Discenti -->
            <div class="mb-3">
                <label class="form-label">Discenti:</label>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 40px;">Seleziona</th>
                            <th>Nome</th>
                            <th>Cognome</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="discente" items="${discenti}">
                            <tr>
                                <td class="text-center">
                                    <input type="checkbox" name="discentiIds" value="${discente.id}"
                                    <c:if test="${corso.discenti != null && corso.discenti.contains(discente)}">checked</c:if> />
                                </td>
                                <td>${discente.nome}</td>
                                <td>${discente.cognome}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Salva</button>
                <a href="<c:url value='/corsi/lista'/>" class="btn btn-secondary">Annulla</a>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
