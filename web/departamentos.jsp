<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.model.Departamento"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Departamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
                        <a class="nav-link" href="EmpleadoServlet?accion=listar">Empleados</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="DepartamentoServlet?accion=listar">Departamentos</a>
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
        <h2 class="text-center mb-4">Gestión de Departamentos</h2>

        <%
            Departamento departamentoEditar = (Departamento) request.getAttribute("departamentoEditar");
        %>

        <div class="card shadow-sm mb-5 border-primary">
            <div class="card-header bg-primary text-white fw-bold">
                <%= (departamentoEditar != null) ? "Editar Departamento" : "Nuevo Departamento" %>
            </div>
            <div class="card-body">
                <form action="DepartamentoServlet" method="post">
                    <input type="hidden" name="idDepartamento"
                           value="<%= (departamentoEditar != null) ? departamentoEditar.getIdDepartamento() : "" %>">

                    <div class="mb-3">
                        <label class="form-label text-muted text-sm">Nombre del Departamento</label>
                        <input type="text" class="form-control" name="nombreDepartamento" required
                               value="<%= (departamentoEditar != null) ? departamentoEditar.getNombreDepartamento() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label text-muted text-sm">Descripción</label>
                        <textarea class="form-control" name="descripcionDepartamento" rows="4"><%= (departamentoEditar != null) ? departamentoEditar.getDescripcionDepartamento() : "" %></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= (departamentoEditar != null) ? "Actualizar" : "Guardar" %>
                    </button>
                    <a href="DepartamentoServlet?accion=listar" class="btn btn-secondary">Limpiar</a>
                </form>
            </div>
        </div>

        <div class="card shadow-sm border-dark mb-5">
            <div class="card-header bg-dark text-white fw-bold">
                Lista de Departamentos
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-dark text-secondary">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th style="width: 160px;">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos");
                        if (lista != null && !lista.isEmpty()) {
                            for (Departamento d : lista) {
                    %>
                        <tr>
                            <td><%= d.getIdDepartamento() %></td>
                            <td><%= d.getNombreDepartamento() %></td>
                            <td><%= d.getDescripcionDepartamento() %></td>
                            <td>
                                <a href="DepartamentoServlet?accion=editar&id=<%= d.getIdDepartamento() %>" class="btn btn-warning btn-sm">Editar</a>
                                <a href="DepartamentoServlet?accion=eliminar&id=<%= d.getIdDepartamento() %>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('¿Deseas eliminar este departamento?')">Eliminar</a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="4" class="text-center">No hay departamentos registrados.</td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>