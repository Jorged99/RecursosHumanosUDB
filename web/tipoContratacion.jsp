<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.model.TipoContratacion"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tipos de Contratacion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <h2 class="mb-4">Gestión de Tipos de Contratación</h2>

    <%
        TipoContratacion tipoEditar = (TipoContratacion) request.getAttribute("tipoEditar");
        String error = (String) request.getAttribute("error");
    %>

    <%-- mensaje de error si hay validacion fallida --%>
    <% if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <%-- formulario para agregar o editar --%>
    <div class="card mb-4">
        <div class="card-header">
            <%= (tipoEditar != null) ? "Editar Tipo de Contratación" : "Nuevo Tipo de Contratación" %>
        </div>
        <div class="card-body">
            <form action="TipoContratacionServlet" method="post" id="miFormulario">
                <input type="hidden" name="accion" value="guardar">
                <input type="hidden" name="idTipoContratacion"
                       value="<%= (tipoEditar != null) ? tipoEditar.getIdTipoContratacion() : "" %>">

                <div class="mb-3">
                    <label class="form-label">Tipo de Contratación</label>
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

    <%-- tabla con los registros --%>
    <div class="card">
        <div class="card-header">Lista de Tipos de Contratación</div>
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tipo de Contratación</th>
                        <th>Acciones</th>
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
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // validacion en el cliente antes de enviar el formulario
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
