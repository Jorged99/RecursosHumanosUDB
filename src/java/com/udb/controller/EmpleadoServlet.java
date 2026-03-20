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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarEmpleados(request, response);
                break;

            case "editar":
                editarEmpleado(request, response);
                break;

            case "eliminar":
                eliminarEmpleado(request, response);
                break;

            default:
                listarEmpleados(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idStr = request.getParameter("idEmpleado");
            String dui = request.getParameter("dui");
            String nombre = request.getParameter("nombre");
            String usuario = request.getParameter("usuario");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");

            if (dui == null || dui.length() != 9) {
                request.setAttribute("error", "DUI inválido. Debe contener 9 dígitos.");
                listarEmpleados(request, response);
                return;
            }

            Empleado emp = new Empleado();
            emp.setNumeroDui(dui);
            emp.setNombrePersona(nombre);
            emp.setUsuario(usuario);
            emp.setNumeroTelefono(telefono);
            emp.setCorreoInstitucional(correo);
            emp.setFechaNacimiento(Date.valueOf(fechaNacimientoStr));

            if (idStr == null || idStr.isEmpty()) {
                empleadoDAO.insertar(emp);
            } else {
                emp.setIdEmpleado(Integer.parseInt(idStr));
                empleadoDAO.actualizar(emp);
            }

            response.sendRedirect("EmpleadoServlet?accion=listar");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Formato de fecha inválido.");
            listarEmpleados(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al guardar empleado", e);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Empleado> lista = empleadoDAO.listar();
        request.setAttribute("listaEmpleados", lista);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Empleado emp = empleadoDAO.obtenerPorId(id);

        request.setAttribute("empleadoEditar", emp);

        List<Empleado> lista = empleadoDAO.listar();
        request.setAttribute("listaEmpleados", lista);

        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.eliminar(id);

        response.sendRedirect("EmpleadoServlet?accion=listar");
    }
}