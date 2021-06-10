package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;

public class GetRetosFechaLimite extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
    
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            String fechaLimite = request.getParameter("fechaLimite");
            String respuestaJson = db.getRetosFechaLimite(fechaLimite);
            
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