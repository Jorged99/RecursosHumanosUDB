package com.udb.controller;

import com.udb.dao.DepartamentoDAO;
import com.udb.model.Departamento;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DepartamentoServlet", urlPatterns = {"/DepartamentoServlet"})
public class DepartamentoServlet extends HttpServlet {

    DepartamentoDAO dao = new DepartamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {
            List<Departamento> lista = dao.listar();
            request.setAttribute("listaDepartamentos", lista);
            request.getRequestDispatcher("departamentos.jsp").forward(request, response);

        } else if (accion.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Departamento departamento = dao.obtenerPorId(id);
            request.setAttribute("departamentoEditar", departamento);

            List<Departamento> lista = dao.listar();
            request.setAttribute("listaDepartamentos", lista);

            request.getRequestDispatcher("departamentos.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("DepartamentoServlet?accion=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("idDepartamento");
        String nombre = request.getParameter("nombreDepartamento");
        String descripcion = request.getParameter("descripcionDepartamento");

        Departamento departamento = new Departamento();
        departamento.setNombreDepartamento(nombre);
        departamento.setDescripcionDepartamento(descripcion);

        if (idStr == null || idStr.isEmpty()) {
            dao.insertar(departamento);
        } else {
            departamento.setIdDepartamento(Integer.parseInt(idStr));
            dao.actualizar(departamento);
        }

        response.sendRedirect("DepartamentoServlet?accion=listar");
    }
}