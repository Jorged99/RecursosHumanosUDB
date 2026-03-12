package com.udb.controller;

import com.udb.dao.CargoDAO;
import com.udb.model.Cargo;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "CargoServlet", urlPatterns = {"/CargoServlet"})
public class CargoServlet extends HttpServlet {

    CargoDAO dao = new CargoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {

            List<Cargo> lista = dao.listar();
            request.setAttribute("listaCargos", lista);
            request.getRequestDispatcher("cargos.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);

            response.sendRedirect("CargoServlet?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cargoNombre = request.getParameter("cargo");
        String descripcion = request.getParameter("descripcionCargo");
        boolean jefatura = request.getParameter("jefatura") != null;

        Cargo c = new Cargo();
        c.setCargo(cargoNombre);
        c.setDescripcionCargo(descripcion);
        c.setJefatura(jefatura);

        dao.insertar(c);

        response.sendRedirect("CargoServlet?accion=listar");
    }
}