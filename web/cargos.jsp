<%@page import="java.util.List"%>
<%@page import="com.udb.model.Cargo"%>

<html>
<head>
<title>Módulo Cargos</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body class="container mt-4">

<h2>Gestión de Cargos</h2>

<form action="CargoServlet" method="post">

<div class="mb-3">
<label>Cargo</label>
<input type="text" name="cargo" class="form-control" required>
</div>

<div class="mb-3">
<label>Descripción</label>
<textarea name="descripcionCargo" class="form-control"></textarea>
</div>

<div class="form-check mb-3">
<input type="checkbox" name="jefatura" class="form-check-input">
<label class="form-check-label">Es jefatura</label>
</div>

<button class="btn btn-primary">Guardar</button>

</form>

<hr>

<table class="table table-bordered">

<tr>
<th>ID</th>
<th>Cargo</th>
<th>Descripción</th>
<th>Jefatura</th>
<th>Acción</th>
</tr>

<%
List<Cargo> lista = (List<Cargo>) request.getAttribute("listaCargos");

if(lista != null){

for(Cargo c : lista){
%>

<tr>
<td><%=c.getIdCargo()%></td>
<td><%=c.getCargo()%></td>
<td><%=c.getDescripcionCargo()%></td>
<td><%=c.isJefatura() ? "Sí":"No"%></td>
<td>
<a href="CargoServlet?accion=eliminar&id=<%=c.getIdCargo()%>" class="btn btn-danger btn-sm">Eliminar</a>
</td>
</tr>

<%
}
}
%>

</table>

</body>
</html>