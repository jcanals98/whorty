package com.web.comentarios;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import com.web.DBController;

public class PostComentario extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        String respuestaJson = "";
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            String comentario = request.getParameter("comentario");
            Integer posts_id = Integer.parseInt(request.getParameter("posts_id"));
            Integer usuarios_id = (request.getParameter("usuarios_id").equals(""))?0:Integer.parseInt(request.getParameter("usuarios_id"));
            Integer empresas_id = (request.getParameter("empresas_id").equals(""))?0:Integer.parseInt(request.getParameter("empresas_id"));
            String fecha_creacion = request.getParameter("fecha_creacion");

            // Comprueba si el reto requiere de un archivo y llama la funcion que tiene archivo o la que no
            if(!request.getParameter("multimedia").equals("")){
                String multimedia = request.getParameter("multimedia");
                respuestaJson = db.postComentario(comentario, posts_id, usuarios_id, empresas_id, multimedia, fecha_creacion);
            }else{
                respuestaJson = db.postComentario(comentario, posts_id, usuarios_id, empresas_id, fecha_creacion);
            }
            
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

