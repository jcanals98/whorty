package com.web.log;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;
import com.web.tablas.Empresa;

public class LoginEmpresa extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Empresa empresa = new Empresa();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
        
        try{
            db.iniciar();  
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if(email.isEmpty() || password.isEmpty()){
                out.println(db.respuestaServidor(400));
            }else{
                String respuestaJson = db.loginEmpresa(email, password);
                if(respuestaJson.length() <= 5){
                    out.println("El email y/o password son erroneos");
                }else{
                    out.println(respuestaJson);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
}