package com.udb.controller;

import com.udb.dao.CargoDAO;
import com.udb.model.Cargo;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CargoServlet", urlPatterns = {"/CargoServlet"})
public class CargoServlet extends HttpServlet {

    private CargoDAO dao = new CargoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarCargos(request, response);
                break;

            case "editar":
                editarCargo(request, response);
                break;

            case "eliminar":
                eliminarCargo(request, response);
                break;

            default:
                listarCargos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("idCargo");
        String nombreCargo = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcionCargo");
        boolean jefatura = request.getParameter("jefatura") != null;

        Cargo c = new Cargo();
        c.setCargo(nombreCargo);
        c.setDescripcionCargo(descripcion);
        c.setJefatura(jefatura);

        if (idStr == null || idStr.isEmpty()) {
            dao.insertar(c);
        } else {
            c.setIdCargo(Integer.parseInt(idStr));
            dao.actualizar(c);
        }

        response.sendRedirect("CargoServlet?accion=listar");
    }

    private void listarCargos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cargo> lista = dao.listar();
        request.setAttribute("listaCargos", lista);
        request.getRequestDispatcher("cargos.jsp").forward(request, response);
    }

    private void editarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Cargo cargoEditar = dao.obtenerPorId(id);

        request.setAttribute("cargoEditar", cargoEditar);

        List<Cargo> lista = dao.listar();
        request.setAttribute("listaCargos", lista);

        request.getRequestDispatcher("cargos.jsp").forward(request, response);
    }

    private void eliminarCargo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);

        response.sendRedirect("CargoServlet?accion=listar");
    }
}