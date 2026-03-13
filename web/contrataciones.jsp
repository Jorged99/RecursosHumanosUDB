<%-- 
    Document   : contrataciones
    Created on : Mar 12, 2026, 10:54:39 PM
    Author     : neftali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Gestión de Contrataciones - Recursos Humanos</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">

<h2 class="text-center mb-4">Módulo de Contrataciones</h2>

<!-- Mensaje de error -->
<c:if test="${not empty error}">
<div class="alert alert-danger">${error}</div>
</c:if>

<!-- Mensaje de éxito -->
<c:if test="${not empty mensaje}">
<div class="alert alert-success">${mensaje}</div>
</c:if>

<!-- FORMULARIO -->

<div class="card mb-4">

<div class="card-header bg-primary text-white">
Nueva Contratación
</div>

<div class="card-body">

<form action="ContratacionServlet?accion=agregar" method="POST" class="row g-3">

<div class="col-md-4">
<label class="form-label">Empleado ID</label>
<input type="number" name="empleado" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Departamento ID</label>
<input type="number" name="departamento" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Cargo ID</label>
<input type="number" name="cargo" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Tipo Contratación ID</label>
<input type="number" name="tipo" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Fecha de Contratación</label>
<input type="date" name="fecha" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Salario</label>
<input type="number" step="0.01" name="salario" class="form-control" required>
</div>

<div class="col-md-4">
<label class="form-label">Estado</label>
<select name="estado" class="form-select">
<option value="true">Activo</option>
<option value="false">Inactivo</option>
</select>
</div>

<div class="col-12">
<button type="submit" class="btn btn-success">
Guardar Contratación
</button>
</div>

</form>

</div>
</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>