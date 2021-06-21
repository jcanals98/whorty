package com.web.posts;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import com.web.DBController;

public class PostNewPost extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        String respuestaJson = "";
        
        try{
            db.iniciar();  


            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            Integer usuarios_id = (request.getParameter("usuarios_id").equals(""))?0:Integer.parseInt(request.getParameter("usuarios_id"));
            Integer empresas_id = (request.getParameter("empresas_id").equals(""))?0:Integer.parseInt(request.getParameter("empresas_id"));


            // Comprueba si el reto requiere de un archivo y llama la funcion que tiene archivo o la que no
            if(!request.getParameter("multimedia").equals("")){
                String multimedia = request.getParameter("multimedia");
                respuestaJson = db.postNewPost(titulo, descripcion, usuarios_id, empresas_id, multimedia);
            }else{
                respuestaJson = db.postNewPost(titulo, descripcion, usuarios_id, empresas_id);
            }
            
            out.println(respuestaJson);
        
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}