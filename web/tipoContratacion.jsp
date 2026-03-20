<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.model.TipoContratacion"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Tipos de Contratación</title>
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
                        <a class="nav-link" href="DepartamentoServlet?accion=listar">Departamentos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CargoServlet?accion=listar">Cargos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="TipoContratacionServlet?accion=listar">Tipo Contratación</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ContratacionServlet?accion=listar">Contrataciones</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Gestión de Tipos de Contratación</h2>

        <%
            TipoContratacion tipoEditar = (TipoContratacion) request.getAttribute("tipoEditar");
            String error = (String) request.getAttribute("error");
        %>

        <% if (error != null) { %>
            <div class="alert alert-danger"><%= error %></div>
        <% } %>

        <div class="card shadow-sm mb-5 border-primary">
            <div class="card-header bg-primary text-white fw-bold">
                <%= (tipoEditar != null) ? "Editar Tipo de Contratación" : "Nuevo Tipo de Contratación" %>
            </div>
            <div class="card-body">
                <form action="TipoContratacionServlet" method="post" id="miFormulario">
                    <input type="hidden" name="accion" value="guardar">
                    <input type="hidden" name="idTipoContratacion"
                           value="<%= (tipoEditar != null) ? tipoEditar.getIdTipoContratacion() : "" %>">

                    <div class="mb-3">
                        <label class="form-label text-muted text-sm">Tipo de Contratación</label>
                        <input type="text" class="form-control" name="tipoContratacion" id="tipoContratacion"
                               maxlength="100"
                               value="<%= (tipoEditar != null) ? tipoEditar.getTipoContratacion() : "" %>"
                               placeholder="Ej: Tiempo completo, Por hora...">
                        <div id="msgError" class="text-danger" style="display:none;">Este campo es obligatorio.</div>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <%= (tipoEditar != null) ? "Actualizar" : "Guardar" %>
                    </button>
                    <a href="TipoContratacionServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>

        <div class="card shadow-sm border-dark mb-5">
            <div class="card-header bg-dark text-white fw-bold">
                Lista de Tipos de Contratación
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped mb-0">
                    <thead class="table-dark text-secondary">
                        <tr>
                            <th>ID</th>
                            <th>Tipo de Contratación</th>
                            <th style="width: 160px;">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        List<TipoContratacion> lista = (List<TipoContratacion>) request.getAttribute("listaTipos");
                        if (lista != null && !lista.isEmpty()) {
                            for (TipoContratacion tc : lista) {
                    %>
                        <tr>
                            <td><%= tc.getIdTipoContratacion() %></td>
                            <td><%= tc.getTipoContratacion() %></td>
                            <td>
                                <a href="TipoContratacionServlet?accion=editar&id=<%= tc.getIdTipoContratacion() %>"
                                   class="btn btn-warning btn-sm">Editar</a>
                                <a href="TipoContratacionServlet?accion=eliminar&id=<%= tc.getIdTipoContratacion() %>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('¿Seguro que desea eliminar este registro?')">Eliminar</a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="3" class="text-center">No hay tipos de contratación registrados.</td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById("miFormulario").addEventListener("submit", function(e) {
            var campo = document.getElementById("tipoContratacion").value.trim();
            if (campo === "") {
                e.preventDefault();
                document.getElementById("msgError").style.display = "block";
            }
        });
    </script>
</body>
</html>