package com.web.log;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.*;

import com.web.DBController;
import com.web.tablas.Empresa;

public class RegistroEmpresa extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Empresa empresa = new Empresa();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String identificacionFiscal = request.getParameter("identificacionFiscal");
            String descripcion = request.getParameter("descripcion");
            String ubicacion = request.getParameter("ubicacion");
            String latlng = request.getParameter("latlng");

            String respuestaJson = db.registroEmpresa(nombre, email, password, identificacionFiscal, descripcion, ubicacion, latlng);
            
            out.println(db.respuestaServidor(response.getStatus()));
            out.println(respuestaJson);
            
            out.println("<html>");
            out.println("<body>");
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}