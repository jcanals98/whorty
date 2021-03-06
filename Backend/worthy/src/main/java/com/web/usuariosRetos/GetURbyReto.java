package com.web.usuariosRetos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;
import com.web.tablas.*;

public class GetURbyReto extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    Reto reto = new Reto();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();

        try{
            db.iniciar();  
            Integer retos_id = Integer.parseInt(request.getParameter("retos_id"));
            out.println(db.getURbyReto(retos_id));
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}