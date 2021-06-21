package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import com.web.DBController;

public class PostReto extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        String respuestaJson = "";
        
        try{
            db.iniciar();  

            Integer id_creador = Integer.parseInt(request.getParameter("id_creador"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String tecnologias = request.getParameter("tecnologias");
            Integer participantesMax = Integer.parseInt(request.getParameter("participantesMax"));
            Integer participantes = Integer.parseInt(request.getParameter("participantes"));
            Integer nivell = Integer.parseInt(request.getParameter("nivell"));

            // Comprueba si el reto requiere de un archivo y llama la funcion que tiene archivo o la que no
            if(!request.getParameter("multimedia").equals("")){
                String multimedia = request.getParameter("multimedia");
                respuestaJson = db.postReto(id_creador, nombre, descripcion, tecnologias, participantesMax, participantes, multimedia, nivell);
            }else{
                respuestaJson = db.postReto(id_creador, nombre, descripcion, tecnologias, participantesMax, participantes, nivell);
            }
            
            out.println(respuestaJson);
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}