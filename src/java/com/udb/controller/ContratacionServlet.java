package com.udb.controller;

import com.udb.dao.*;
import com.udb.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ContratacionServlet", urlPatterns = {"/ContratacionServlet"})
public class ContratacionServlet extends HttpServlet {

    private ContratacionDAO contratacionDAO = new ContratacionDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private DepartamentoDAO deptoDAO = new DepartamentoDAO();
    private CargoDAO cargoDAO = new CargoDAO();
    private TipoContratacion1 tipoDAO = new TipoContratacion1();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        if (accion.equals("listar")) {
            request.setAttribute("listaContrataciones", contratacionDAO.listar());
            request.setAttribute("listaEmpleado", empleadoDAO.listarEmpleados());
            request.setAttribute("listaDepartamentos", deptoDAO.listar());
            request.setAttribute("listaCargos", cargoDAO.listar());
            request.setAttribute("listaTipos", tipoDAO.listar());

            request.getRequestDispatcher("contrataciones.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            contratacionDAO.eliminar(id);
            response.sendRedirect("ContratacionServlet?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("agregar")) {
            Contratacion c = new Contratacion();

            Empleado emp = new Empleado();
            emp.setIdEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
            c.setEmpleado(emp);

            Departamento depto = new Departamento();
            depto.setIdDepartamento(Integer.parseInt(request.getParameter("idDepartamento")));
            c.setDepartamento(depto);

            Cargo cargo = new Cargo();
            cargo.setIdCargo(Integer.parseInt(request.getParameter("idCargo")));
            c.setCargo(cargo);

            TipoContratacion tipo = new TipoContratacion();
            tipo.setIdTipoContratacion(Integer.parseInt(request.getParameter("idTipoContratacion")));
            c.setTipoContratacion(tipo);

            c.setFechaContracion(Date.valueOf(request.getParameter("fechaContratacion")));
            c.setSalario(Double.parseDouble(request.getParameter("salario")));
            c.setEstado(true);

            contratacionDAO.agregar(c);
            response.sendRedirect("ContratacionServlet?accion=listar");
        } else {
            response.sendRedirect("ContratacionServlet?accion=listar");
        }
    }
}