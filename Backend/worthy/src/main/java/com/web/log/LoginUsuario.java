package com.web.log;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.web.DBController;
import com.web.tablas.*;
import com.google.gson.*;

public class LoginUsuario extends HttpServlet {
    Usuario usuario = new Usuario();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  

            out.println("<html>");
            out.println("<body>");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username.isEmpty() || password.isEmpty()){
                out.println(db.respuestaServidor(400));
            }else{
                String respuestaJson = db.loginUsuario(username, password);
                if(respuestaJson.length() <= 5){
                    out.println("El username y/o password son erroneos");
                }else{
                    out.println(db.respuestaServidor(response.getStatus()));
                    out.println(respuestaJson);
                }
            }
            
            out.println("<html>");
            out.println("<body>");
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}