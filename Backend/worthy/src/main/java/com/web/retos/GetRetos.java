package com.web.retos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;
import com.web.tablas.*;

public class GetRetos extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    Reto reto = new Reto();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();

        try{
           db.iniciar();   
   
           out.println(db.getRetos());
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}