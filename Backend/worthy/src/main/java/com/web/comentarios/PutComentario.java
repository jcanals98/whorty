package com.web.comentarios;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;

public class PutComentario extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            Integer id = Integer.parseInt(request.getParameter("id"));
            String comentario = request.getParameter("comentario");
            String multimedia = request.getParameter("multimedia");

            String respuestaJson = db.putComentario(id, comentario, multimedia);
            
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