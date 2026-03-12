<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.model.Departamento"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Módulo Departamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Gestión de Departamentos</h2>

    <%
        Departamento departamentoEditar = (Departamento) request.getAttribute("departamentoEditar");
    %>

    <div class="card mb-4">
        <div class="card-header">
            <%= (departamentoEditar != null) ? "Editar Departamento" : "Nuevo Departamento" %>
        </div>
        <div class="card-body">
            <form action="DepartamentoServlet" method="post">
                <input type="hidden" name="idDepartamento"
                       value="<%= (departamentoEditar != null) ? departamentoEditar.getIdDepartamento() : "" %>">

                <div class="mb-3">
                    <label class="form-label">Nombre del Departamento</label>
                    <input type="text" class="form-control" name="nombreDepartamento" required
                           value="<%= (departamentoEditar != null) ? departamentoEditar.getNombreDepartamento() : "" %>">
                </div>

                <div class="mb-3">
                    <label class="form-label">Descripción</label>
                    <textarea class="form-control" name="descripcionDepartamento" rows="3"><%= (departamentoEditar != null) ? departamentoEditar.getDescripcionDepartamento() : "" %></textarea>
                </div>

                <button type="submit" class="btn btn-primary">
                    <%= (departamentoEditar != null) ? "Actualizar" : "Guardar" %>
                </button>

                <a href="DepartamentoServlet?accion=listar" class="btn btn-secondary">Limpiar</a>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-header">Lista de Departamentos</div>
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos");
                    if (lista != null) {
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
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>