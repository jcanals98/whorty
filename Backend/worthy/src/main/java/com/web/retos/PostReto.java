package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.*;
import com.web.DBController;
import com.web.tablas.*;

public class PostReto extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        String respuestaJson = "";
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            Integer id_creador = Integer.parseInt(request.getParameter("id_creador"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String tecnologias = request.getParameter("tecnologias");
            Integer participantesMax = Integer.parseInt(request.getParameter("participantesMax"));
            Integer participantes = Integer.parseInt(request.getParameter("participantes"));
            Integer nivell = Integer.parseInt(request.getParameter("nivell"));
            String fecha_limite = request.getParameter("fecha_limite");

            // Comprueba si el reto requiere de un archivo y llama la funcion que tiene archivo o la que no
            if(!request.getParameter("multimedia_id").equals("")){
                Integer multimedia_id = Integer.parseInt(request.getParameter("multimedia_id"));
                respuestaJson = db.postReto(id_creador, nombre, descripcion, tecnologias, participantesMax, participantes, multimedia_id, nivell, fecha_limite);
            }else{
                respuestaJson = db.postReto(id_creador, nombre, descripcion, tecnologias, participantesMax, participantes, nivell, fecha_limite);
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