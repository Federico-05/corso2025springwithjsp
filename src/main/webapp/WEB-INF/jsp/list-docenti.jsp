<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Docenti</title>
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

<h1 class="display-5 text-center text-info mb-3">Elenco Docenti</h1>

<div class="d-flex justify-content-center mb-4">
    <a class="btn btn-info text-white fw-semibold" href="<c:url value='/docenti/nuovo'/>">
        <i class="bi bi-person-plus"></i> Nuovo Docente
    </a>
</div>

<table class="table table-striped table-hover table-bordered align-middle shadow-sm rounded">
    <thead class="table-info text-center">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${docenti}">
        <tr>
            <td class="text-center">${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>
            <td class="text-center">
                <a class="btn btn-sm btn-outline-info rounded-pill me-1" href="<c:url value='/docenti/${d.id}/edit'/>">
                    <i class="bi bi-pencil-square"></i> Modifica
                </a>
                <a class="btn btn-sm btn-outline-danger rounded-pill" href="<c:url value='/docenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro di voler eliminare questo docente?')">
                    <i class="bi bi-trash"></i> Elimina
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
