<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Corso</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<h1>Aggiungi/modifica corso</h1>

<form action="<c:url value='/corsi/salva'/>" method="post">
    <input type="hidden" name="id" value="${corso.id}" />

    <div class="mb-3">
        <label for="nome" class="form-label">Nome Corso</label>
        <input type="text" class="form-control" id="nome" name="nome" value="${corso.nome}" required />
    </div>

    <div class="mb-3">
        <label for="anno_accademico" class="form-label">Anno Accademico</label>
        <input type="number" class="form-control" id="anno_accademico" name="anno_accademico" value="${corso.anno_accademico}" required />
    </div>

    <div class="mb-3">
        <label for="id_docente" class="form-label">Docente</label>
        <select class="form-select" name="id_docente" id="id_docente" required>
            <option value="">-- Seleziona Docente --</option>
            <c:forEach var="docente" items="${docenti}">
                <option value="${docente.id}"
                    <c:if test="${corso.docente != null && docente.id == corso.docente.id}">selected</c:if>>
                    ${docente.nome} ${docente.cognome}
                </option>
            </c:forEach>
        </select>
    </div>
    <h5 class="mt-4">Discenti assegnati</h5>
        <div class="row">
            <c:forEach var="discente" items="${tuttiDiscenti}">
                <div class="col-md-6 col-lg-4 mb-2">
                    <div class="form-check">
                        <input class="form-check-input"
                               type="checkbox"
                               name="discenteIds"
                               value="${discente.id}"
                               id="discente-${discente.id}"
                               <c:if test="${corso.discenti != null && corso.discenti.contains(discente)}">checked</c:if> />
                        <label class="form-check-label" for="discente-${discente.id}">
                                ${discente.nome} ${discente.cognome}
                        </label>
                    </div>
                </div>
            </c:forEach>
        </div>
    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/corsi/lista'/>" class="btn btn-secondary">Annulla</a>
</form>

</body>
</html>
