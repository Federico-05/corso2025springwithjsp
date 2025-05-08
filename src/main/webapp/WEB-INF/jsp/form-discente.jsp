<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Discente</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">
<h1>Discente</h1>

<form action="<c:url value='/discenti'/>" method="post">
    <input type="hidden" name="id" value="${discente.id}" />

    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" name="nome" id="nome" class="form-control" value="${discente.nome}" required>
    </div>
    <div class="mb-3">
        <label for="cognome" class="form-label">Cognome</label>
        <input type="text" name="cognome" id="cognome" class="form-control" value="${discente.cognome}" required>
    </div>
    <div class="mb-3">
        <label for="matricola" class="form-label">matricola</label>
        <input type="number" name="matricola" id="matricola" class="form-control" value="${discente.matricola}" required>
    </div>
     <div class="mb-3">
            <label for="eta" class="form-label">eta</label>
            <input type="number" name="eta" id="eta" class="form-control" value="${discente.eta}" required>
     </div>
     <div class="mb-3">
             <label for="c" class="form-label">Citta Di Residenza</label>
             <input type="text" name="CittaResidenza" id="CittaResidenza" class="form-control" value="${discente.cittaresidenza}" required>
     </div>
    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/discenti/lista'/>" class="btn btn-secondary">Annulla</a>
</form>
</body>
</html>
