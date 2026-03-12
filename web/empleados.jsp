<%-- 
    Document   : empleados
    Created on : 8 mar 2026, 09:50:20
    Author     : jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Empleados - Recursos Humanos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Módulo de Empleados</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="card mb-4">
        <div class="card-header bg-primary text-white">Nuevo Empleado</div>
        <div class="card-body">
            <form action="EmpleadoServlet?accion=agregar" method="POST" class="row g-3">
                <div class="col-md-4">
                    <label>DUI (Sin guiones)</label>
                    <input type="text" name="dui" class="form-control" pattern="\d{9}" title="Debe contener 9 dígitos" required>
                </div>
                <div class="col-md-4">
                    <label>Nombre Completo</label>
                    <input type="text" name="nombre" class="form-control" required>
                </div>
                <div class="col-md-4">
                    <label>Usuario</label>
                    <input type="text" name="usuario" class="form-control" required>
                </div>
                <div class="col-md-4">
                    <label>Teléfono</label>
                    <input type="text" name="telefono" class="form-control" pattern="\d{8,9}" required>
                </div>
                <div class="col-md-4">
                    <label>Correo Institucional</label>
                    <input type="email" name="correo" class="form-control" required>
                </div>
                <div class="col-md-4">
                    <label>Fecha de Nacimiento</label>
                    <input type="date" name="fechaNacimiento" class="form-control" required>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-success">Guardar Empleado</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-header bg-dark text-white">Lista de Empleados</div>
        <div class="card-body">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DUI</th>
                        <th>Nombre</th>
                        <th>Usuario</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Nacimiento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${listaEmpleados}">
                        <tr>
                            <td>${emp.idEmpleado}</td>
                            <td>${emp.numeroDui}</td>
                            <td>${emp.nombrePersona}</td>
                            <td>${emp.usuario}</td>
                            <td>${emp.numeroTelefono}</td>
                            <td>${emp.correoInstitucional}</td>
                            <td>${emp.fechaNacimiento}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>