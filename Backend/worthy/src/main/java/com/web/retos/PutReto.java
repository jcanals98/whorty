package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;

public class PutReto extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  

            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer id_creador = Integer.parseInt(request.getParameter("id_creador"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String tecnologias = request.getParameter("tecnologias");
            Integer participantesMax = Integer.parseInt(request.getParameter("participantesMax"));
            Integer participantes = Integer.parseInt(request.getParameter("participantes"));
            String multimedia = request.getParameter("multimedia");
            Integer nivell = Integer.parseInt(request.getParameter("nivell"));
            String fecha_limite = request.getParameter("fecha_limite");

            System.out.println("ola"+fecha_limite);

            String respuestaJson = db.putReto(id, id_creador, nombre, descripcion, tecnologias, participantesMax, participantes, multimedia, nivell, fecha_limite);
            
            out.println(respuestaJson);
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}