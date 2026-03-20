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

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold" href="index.html">RRHH UDB</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="EmpleadoServlet?accion=listar">Empleados</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="DepartamentoServlet?accion=listar">Departamentos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CargoServlet?accion=listar">Cargos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="TipoContratacionServlet?accion=listar">Tipo Contratación</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ContratacionServlet?accion=listar">Contrataciones</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Módulo de Empleados</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <c:set var="empEdit" value="${empleadoEditar}" />

        <div class="card shadow-sm mb-5 border-primary">
            <div class="card-header bg-primary text-white fw-bold">
                <c:choose>
                    <c:when test="${not empty empEdit}">Editar Empleado</c:when>
                    <c:otherwise>Nuevo Empleado</c:otherwise>
                </c:choose>
            </div>
            <div class="card-body">
                <form action="EmpleadoServlet" method="POST" class="row g-3">
                    
                    <input type="hidden" name="idEmpleado" value="${empEdit.idEmpleado}">

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">DUI (sin guiones)</label>
                        <input type="text" name="dui" class="form-control"
                               pattern="\d{9}" title="Debe contener 9 dígitos" required
                               value="${empEdit.numeroDui}">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">Nombre Completo</label>
                        <input type="text" name="nombre" class="form-control" required
                               value="${empEdit.nombrePersona}">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">Usuario</label>
                        <input type="text" name="usuario" class="form-control" required
                               value="${empEdit.usuario}">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">Teléfono</label>
                        <input type="text" name="telefono" class="form-control"
                               pattern="\d{8,9}" required
                               value="${empEdit.numeroTelefono}">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">Correo Institucional</label>
                        <input type="email" name="correo" class="form-control" required
                               value="${empEdit.correoInstitucional}">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label text-muted text-sm">Fecha de Nacimiento</label>
                        <input type="date" name="fechaNacimiento" class="form-control" required
                               value="${empEdit.fechaNacimiento}">
                    </div>

                    <div class="col-12">
                        <c:choose>
                            <c:when test="${not empty empEdit}">
                                <button type="submit" class="btn btn-primary">Actualizar Empleado</button>
                                <a href="EmpleadoServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-success">Guardar Empleado</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow-sm border-dark mb-5">
            <div class="card-header bg-dark text-white fw-bold">
                Lista de Empleados
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-dark text-secondary">
                        <tr>
                            <th>ID</th>
                            <th>DUI</th>
                            <th>Nombre</th>
                            <th>Usuario</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Nacimiento</th>
                            <th style="width: 160px;">Acciones</th>
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
                                <td>
                                    <a href="EmpleadoServlet?accion=editar&id=${emp.idEmpleado}" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="EmpleadoServlet?accion=eliminar&id=${emp.idEmpleado}" 
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('¿Deseas eliminar este empleado?')">
                                       Eliminar
                                    </a>
                                </td>
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