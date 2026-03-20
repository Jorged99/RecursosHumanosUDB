<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Módulo de Contrataciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold" href="index.html">RRHH UDB</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.html">Inicio</a>
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
        <h2 class="text-center mb-4">Gestión de Contrataciones</h2>

        <div class="card shadow-sm mb-5 border-primary">
            <div class="card-header bg-primary text-white fw-bold">
                Registrar Nueva Contratación
            </div>
            <div class="card-body">
                <form action="ContratacionServlet?accion=agregar" method="POST">
                    <div class="row mb-3">
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Empleado</label>
                            <select name="idEmpleado" class="form-select" required>
                                <option value="" disabled selected>Seleccione...</option>
                                <c:forEach items="${listaEmpleado}" var="e">
                                    <option value="${e.idEmpleado}">${e.nombrePersona}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Departamento</label>
                            <select name="idDepartamento" class="form-select" required>
                                <option value="" disabled selected>Seleccione...</option>
                                <c:forEach items="${listaDepartamentos}" var="d">
                                    <option value="${d.idDepartamento}">${d.nombreDepartamento}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Cargo</label>
                            <select name="idCargo" class="form-select" required>
                                <option value="" disabled selected>Seleccione...</option>
                                <c:forEach items="${listaCargos}" var="c">
                                    <option value="${c.idCargo}">${c.cargo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Tipo Contrato</label>
                            <select name="idTipoContratacion" class="form-select" required>
                                <option value="" disabled selected>Seleccione...</option>
                                <c:forEach items="${listaTipos}" var="t">
                                    <option value="${t.idTipoContratacion}">${t.tipoContratacion}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Fecha de Contrato</label>
                            <input type="date" name="fechaContratacion" class="form-control" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label text-muted text-sm">Salario Mensual ($)</label>
                            <input type="number" step="0.01" name="salario" class="form-control" placeholder="Ej. 800.50" required>
                        </div>
                        <div class="col-md-6 d-flex align-items-end">
                            <button type="submit" class="btn btn-success w-100">Registrar Contratación</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow-sm border-dark mb-5">
            <div class="card-header bg-dark text-white fw-bold">
                Listado Oficial de Contrataciones
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped align-middle mb-0 text-center">
                    <thead class="table-dark text-secondary">
                        <tr>
                            <th>ID</th>
                            <th>Empleado</th>
                            <th>Departamento</th>
                            <th>Cargo</th>
                            <th>Modalidad</th>
                            <th>Fecha</th>
                            <th>Salario</th>
                            <th>Estado</th>
                            <th style="width: 70px;">🗑️</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaContrataciones}" var="c">
                            <tr>
                                <td>${c.idContratacion}</td>
                                <td>${c.empleado.nombrePersona}</td>
                                <td>${c.departamento.nombreDepartamento}</td>
                                <td>${c.cargo.cargo}</td>
                                <td>${c.tipoContratacion.tipoContratacion}</td>
                                <td>${c.fechaContracion}</td>
                                <td class="text-success fw-bold">$ ${c.salario}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${c.estado}">
                                            <span class="badge bg-success">Activo</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">Inactivo</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <a href="ContratacionServlet?accion=eliminar&id=${c.idContratacion}"
                                       class="btn btn-sm btn-outline-danger"
                                       title="Eliminar"
                                       onclick="return confirm('¿Deseas eliminar esta contratación?')">
                                        🗑️
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>