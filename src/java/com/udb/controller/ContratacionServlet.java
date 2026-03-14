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
    // Instanciamos los DAOs de las tablas independientes para llenar los Selects
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
            // Mandar la tabla principal
            request.setAttribute("listaContrataciones", contratacionDAO.listar());
            
            // Mandar los catálogos para llenar los menús desplegables (<select>)
            request.setAttribute("listaEmpleado", empleadoDAO.listarEmpleados());
            request.setAttribute("listaDepartamentos", deptoDAO.listar());
            request.setAttribute("listaCargos", cargoDAO.listar());
            request.setAttribute("listaTipos", tipoDAO.listar());
            
            request.getRequestDispatcher("contrataciones.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("agregar")) {
            Contratacion c = new Contratacion();
            
            // 1. Armar los objetos internos solo con su ID
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
            
            // 2. Setear los campos propios de la contratación
            c.setFechaContracion(Date.valueOf(request.getParameter("fechaContratacion")));
            c.setSalario(Double.parseDouble(request.getParameter("salario")));
            c.setEstado(true); // Estado Activo por defecto
            
            // 3. Guardar y recargar la página
            contratacionDAO.agregar(c);
            response.sendRedirect("ContratacionServlet?accion=listar");
        }
    }
}