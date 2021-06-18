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
        //setAccessControlHeaders(response);
       // response.setHeader("Content-Type", "application/json; charset=UTF-8");

        try{
           db.iniciar();   
   
           out.println(db.getRetos());
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
/*
     // for Preflight
     @Override
     protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         setAccessControlHeaders(resp);
         resp.setStatus(HttpServletResponse.SC_OK);
     }
 
     private void setAccessControlHeaders(HttpServletResponse resp) {
         resp.setHeader("Access-Control-Allow-Origin", "*");
         resp.setHeader("Access-Control-Allow-Methods", "GET");
     }*/
}