package com.web.usuariosRetos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;


public class PutUR extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();

        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            Integer usuarios_id = Integer.parseInt(request.getParameter("usuarios_id"));
            Integer retos_id = Integer.parseInt(request.getParameter("retos_id"));
            String multimedia = request.getParameter("multimedia");

            String respuestaJson = db.putUR(usuarios_id, retos_id, multimedia);
            
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