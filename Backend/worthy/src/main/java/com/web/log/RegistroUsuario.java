package com.web.log;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.*;

import com.web.DBController;
import com.web.tablas.Usuario;

public class RegistroUsuario extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String ubicacion = request.getParameter("ubicacion");

            String respuestaJson = db.registroUsuario(username, password, nombre, apellidos, dni, email, telefono, ubicacion);
            
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