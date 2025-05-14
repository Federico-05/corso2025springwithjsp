<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">Gestione</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item mx-2">
                    <a class="btn btn-primary mb-3" href="<c:url value='/discenti/lista'/>">Discenti</a>
                </li>
                <li class="nav-item mx-2">
                    <a class="btn btn-primary mb-3" href="<c:url value='/docenti/lista'/>">Docenti</a>
                </li>
                <li class="nav-item mx-2">
                    <a class="btn btn-primary mb-3" href="<c:url value='/corsi/lista'/>">Corsi</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h1>Elenco Corsi</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo corso</a>
<a class="btn btn-success mb-3" href="/corsi/lista?filter=asc">Ordina per nome (A-Z)</a>
<a class="btn btn-danger mb-3" href="/corsi/lista?filter=desc">Ordina per nome (Z-A)</a>



<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Docente</th>
        <th>Discenti Iscritti</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${corsi}">
        <tr>
            <td>${c.id}</td>
            <td>${c.nome}</td>
            <td>${c.docente.nome} ${c.docente.cognome}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty c.discenti}">
                        <c:forEach var="d" items="${c.discenti}">
                            <div>${d.nome} ${d.cognome}</div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <span class="text-muted">Nessun discente</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${c.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/corsi/${c.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
