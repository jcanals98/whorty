package com.web.comentarios;
import javax.servlet.*;
import javax.servlet.http.*;

import com.web.DBController;

import java.io.*;
import java.sql.*;

public class GetComentariosEmpresa extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
    
        try{
            db.iniciar();  
            Integer id = Integer.parseInt(request.getParameter("id"))  ;
            out.println(db.getComentariosEmpresa(id));
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}