package com.udb.controller;

import com.udb.dao.TipoContratacion1;
import com.udb.model.TipoContratacion;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TipoContratacionServlet", urlPatterns = {"/TipoContratacionServlet"})
public class TipoContratacionServlet extends HttpServlet {

    TipoContratacion1 dao = new TipoContratacion1();

    // processRequest maneja tanto GET como POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                List<TipoContratacion> lista = dao.listar();
                request.setAttribute("listaTipos", lista);
                request.getRequestDispatcher("tipoContratacion.jsp").forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                TipoContratacion tc = dao.obtenerPorId(id);
                request.setAttribute("tipoEditar", tc);
                List<TipoContratacion> lista2 = dao.listar();
                request.setAttribute("listaTipos", lista2);
                request.getRequestDispatcher("tipoContratacion.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("TipoContratacionServlet?accion=listar");
                break;

            case "guardar":
                String idStr = request.getParameter("idTipoContratacion");
                String tipo = request.getParameter("tipoContratacion");

                // validacion del campo
                if (tipo == null || tipo.trim().isEmpty()) {
                    request.setAttribute("error", "El campo no puede estar vacio.");
                    request.setAttribute("listaTipos", dao.listar());
                    request.getRequestDispatcher("tipoContratacion.jsp").forward(request, response);
                    return;
                }

                TipoContratacion nuevo = new TipoContratacion();
                nuevo.setTipoContratacion(tipo.trim());

                if (idStr == null || idStr.isEmpty()) {
                    dao.insertar(nuevo);
                } else {
                    nuevo.setIdTipoContratacion(Integer.parseInt(idStr));
                    dao.actualizar(nuevo);
                }
                response.sendRedirect("TipoContratacionServlet?accion=listar");
                break;

            default:
                response.sendRedirect("TipoContratacionServlet?accion=listar");
        }
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}