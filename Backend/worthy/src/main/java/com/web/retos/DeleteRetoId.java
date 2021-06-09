package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.web.DBController;
import com.web.tablas.*;
import com.google.gson.*;

public class DeleteRetoId extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
    
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            Integer id = Integer.parseInt(request.getParameter("id"))  ;
            String respuestaJson = db.deleteRetoId(id);
            
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