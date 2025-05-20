<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light container py-4">

<nav class="navbar navbar-expand-lg navbar-light bg-info rounded shadow-sm mb-4">
    <div class="container-fluid">
        <a class="navbar-brand text-white fw-bold" href="#">Academy 2025</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto gap-2">
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<c:url value='/docenti/lista'/>">
                        <i class="bi bi-person-lines-fill"></i> Docenti
                    </a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<c:url value='/discenti/lista'/>">
                        <i class="bi bi-person-badge"></i> Discenti
                    </a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="<c:url value='/corsi/lista'/>">
                        <i class="bi bi-book"></i> Corsi
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h1 class="display-5 text-center text-info mb-3">Elenco Corsi</h1>
<div class="d-flex justify-content-center mb-4">
    <a class="btn btn-info text-white fw-semibold" href="<c:url value='/corsi/nuovo'/>">
        <i class="bi bi-plus-circle"></i> Nuovo Corso
    </a>
</div>

<table class="table table-striped table-hover table-bordered align-middle shadow-sm rounded">
    <thead class="table-info text-center">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Anno Accademico</th>
        <th>ID Docente</th>
        <th>Nome e Cognome Docente</th>
        <th>Nome e Cognome Discenti</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${corsi}">
        <tr>
            <td class="text-center">${c.id}</td>
            <td>${c.nome}</td>
            <td class="text-center">${c.annoAccademico}</td>
            <td class="text-center">${c.docenteId}</td>
            <td>
                <c:choose>
                    <c:when test="${docentiMap[c.docenteId] != null}">
                        ${docentiMap[c.docenteId].nome} ${docentiMap[c.docenteId].cognome}
                    </c:when>
                    <c:otherwise>
                        <em class="text-muted">Non assegnato</em>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <c:choose>
                    <c:when test="${not empty c.discentiIds}">
                        <c:forEach var="discentiId" items="${c.discentiIds}">
                            <c:choose>
                                <c:when test="${discentiMap[discentiId] != null}">
                                    ${discentiMap[discentiId].nome} ${discentiMap[discentiId].cognome} <br />
                                </c:when>
                                <c:otherwise>
                                    <em class="text-muted">Non assegnato</em>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <em class="text-muted">Non assegnato</em>
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="text-center">
                <a class="btn btn-sm btn-outline-info rounded-pill me-1" href="<c:url value='/corsi/${c.id}/edit'/>">
                    <i class="bi bi-pencil-square"></i> Modifica
                </a>
                <a class="btn btn-sm btn-outline-danger rounded-pill" href="<c:url value='/corsi/${c.id}/delete'/>"
                   onclick="return confirm('Sei sicuro di voler eliminare questo corso?')">
                    <i class="bi bi-trash"></i> Elimina
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
