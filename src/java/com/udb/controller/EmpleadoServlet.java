package com.udb.controller;

import com.udb.dao.EmpleadoDAO;
import com.udb.model.Empleado;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Método unificado para peticiones GET y POST.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null) { accion = "listar"; }

        try {
            switch (accion) {
                case "listar":
                    listarEmpleados(request, response);
                    break;
                case "agregar":
                    agregarEmpleado(request, response);
                    break;
                default:
                    listarEmpleados(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error procesando solicitud", e);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleado> lista = empleadoDAO.listarEmpleados();
        request.setAttribute("listaEmpleados", lista); // Enviamos la lista a la vista
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void agregarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validaciones del lado del servidor
            String dui = request.getParameter("dui");
            if(dui == null || dui.length() != 9) {
                request.setAttribute("error", "DUI inválido.");
                listarEmpleados(request, response);
                return;
            }

            Empleado emp = new Empleado();
            emp.setNumeroDui(dui);
            emp.setNombrePersona(request.getParameter("nombre"));
            emp.setUsuario(request.getParameter("usuario"));
            emp.setNumeroTelefono(request.getParameter("telefono"));
            emp.setCorreoInstitucional(request.getParameter("correo"));
            emp.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));

            empleadoDAO.agregarEmpleado(emp);
            response.sendRedirect("EmpleadoServlet?accion=listar"); // Redirección tras POST exitoso (PRG pattern)
        } catch (IllegalArgumentException e) {
             request.setAttribute("error", "Formato de fecha inválido.");
             listarEmpleados(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}