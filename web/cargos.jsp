<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.model.Cargo"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Cargos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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
                        <a class="nav-link" href="DepartamentoServlet?accion=listar">Departamentos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="CargoServlet?accion=listar">Cargos</a>
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

    <%
        Cargo cargoEditar = (Cargo) request.getAttribute("cargoEditar");
    %>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Gestión de Cargos</h2>

        <div class="card shadow-sm mb-5 border-primary">
            <div class="card-header bg-primary text-white fw-bold">
                <%= (cargoEditar != null) ? "Editar Cargo" : "Nuevo Cargo" %>
            </div>
            <div class="card-body">
                <form action="CargoServlet" method="post">
                    
                    <input type="hidden" name="idCargo" value="<%= (cargoEditar != null) ? cargoEditar.getIdCargo() : "" %>">

                    <div class="mb-3">
                        <label class="form-label text-muted text-sm">Cargo</label>
                        <input type="text" name="cargo" class="form-control" required
                               value="<%= (cargoEditar != null) ? cargoEditar.getCargo() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label text-muted text-sm">Descripción</label>
                        <textarea name="descripcionCargo" class="form-control" rows="4"><%= (cargoEditar != null) ? cargoEditar.getDescripcionCargo() : "" %></textarea>
                    </div>

                    <div class="form-check mb-3">
                        <input type="checkbox" name="jefatura" class="form-check-input" id="jefatura"
                               <%= (cargoEditar != null && cargoEditar.isJefatura()) ? "checked" : "" %>>
                        <label class="form-check-label" for="jefatura">Es jefatura</label>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= (cargoEditar != null) ? "Actualizar" : "Guardar" %>
                    </button>

                    <a href="CargoServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>

        <div class="card shadow-sm border-dark mb-5">
            <div class="card-header bg-dark text-white fw-bold">
                Lista de Cargos
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-dark text-secondary">
                        <tr>
                            <th>ID</th>
                            <th>Cargo</th>
                            <th>Descripción</th>
                            <th>Jefatura</th>
                            <th style="width: 170px;">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Cargo> lista = (List<Cargo>) request.getAttribute("listaCargos");

                        if (lista != null && !lista.isEmpty()) {
                            for (Cargo c : lista) {
                    %>
                        <tr>
                            <td><%= c.getIdCargo() %></td>
                            <td><%= c.getCargo() %></td>
                            <td><%= c.getDescripcionCargo() %></td>
                            <td><%= c.isJefatura() ? "Sí" : "No" %></td>
                            <td>
                                <a href="CargoServlet?accion=editar&id=<%= c.getIdCargo() %>" class="btn btn-warning btn-sm">Editar</a>
                                <a href="CargoServlet?accion=eliminar&id=<%= c.getIdCargo() %>" 
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('¿Deseas eliminar este cargo?')">Eliminar</a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="5" class="text-center">No hay cargos registrados.</td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>