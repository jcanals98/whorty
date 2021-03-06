package com.web.usuariosRetos;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.web.DBController;


public class PostUR extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBController db = new DBController();
        PrintWriter out = response.getWriter();
       // setAccessControlHeaders(response);
        response.setHeader("Content-Type", "application/json; charset=UTF-8");

        try{
            db.iniciar();  

            Integer usuarios_id = Integer.parseInt(request.getParameter("usuarios_id"));
            Integer retos_id = Integer.parseInt(request.getParameter("retos_id"));
            String multimedia = request.getParameter("multimedia");

            String respuestaJson = db.postUR(usuarios_id, retos_id, multimedia);
            
            //out.println(db.respuestaServidor(response.getStatus()));
            out.println(respuestaJson);
            
        }
        catch (SQLException e) {
            System.out.println("Error de Conexion: " + e.getMessage());
        }
    }
    
    // @Override
    // protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     setAccessControlHeaders(resp);
    //     resp.setStatus(HttpServletResponse.SC_OK);
    // }

    // private void setAccessControlHeaders(HttpServletResponse resp) {
    //     resp.setHeader("Access-Control-Allow-Origin", "*");
    //     resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
    //     resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    // }
}