package com.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Date;  

import com.web.tablas.Usuario;
import com.web.tablas.Empresa;
import com.web.tablas.Reto;
import com.google.gson.*;
import org.json.*;

import javax.servlet.*;
import javax.servlet.http.*;
//import jdk.nashorn.internal.ir.Request;

public class DBController extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost/db_worthy?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);
    Connection con=null;
    Statement stmt=null;
    Usuario usuario = new Usuario();
    Empresa empresa = new Empresa();
    Reto reto = new Reto();
    Gson gson = new Gson();
    

    public void iniciar() throws SQLException{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        stmt = con.createStatement();
        stmt.executeQuery("USE db_worthy");
    }

    //////////////////////////////// Usuarios ////////////////////////////////

    //////////////// GET ////////////////

    public String loginUsuario(String username, String password) throws SQLException {
        String SQL = "SELECT * FROM usuarios WHERE username='" + username + 
                    "' AND password='" + password + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setDni(rs.getString("dni"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setUbicacion(rs.getString("ubicacion"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(usuario);
    }

    public String getUsuarioId(Integer id) throws SQLException{
        String SQL = "SELECT * FROM usuarios WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setDni(rs.getString("dni"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setUbicacion(rs.getString("ubicacion"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(usuario);
    }
   
    //////////////// POST ////////////////
   
    public String registroUsuario(String username, String password, String nombre, String apellidos, String dni, String email, String telefono, String ubicacion) throws SQLException{
        PreparedStatement stmt=con.prepareStatement("INSERT INTO `usuarios` " +
        "(`username`, `password`, `nombre`, `apellidos`, `dni`, `email`, `telefono`, `ubicacion`)" +
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");  

        stmt.setString(1, (username==null)?"":username);
        stmt.setString(2, (password==null)?"":password);
        stmt.setString(3, (nombre==null)?"":nombre);
        stmt.setString(4, (apellidos==null)?"":apellidos);
        stmt.setString(5, (dni==null)?"":dni);
        stmt.setString(6, (email==null)?"":email);
        stmt.setString(7, (telefono==null)?"":telefono);
        stmt.setString(8, (ubicacion==null)?"":ubicacion);

        stmt.executeUpdate();  
        return "El usuario se ha creado correctamente!";
    }

    //////////////// PUT ////////////////
    
    public String putUsuario(Integer id, String username, String password, String nombre, String apellidos, String dni, String email, String telefono, String ubicacion) throws SQLException{
        Usuario userOld = new Usuario();
        Usuario userNew = new Usuario();
        String SQL = "SELECT * FROM usuarios WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                    userOld.setId(rs.getInt("id"));
                    userOld.setUsername(rs.getString("username"));
                    userOld.setPassword(rs.getString("password"));
                    userOld.setNombre(rs.getString("nombre"));
                    userOld.setApellidos(rs.getString("apellidos"));
                    userOld.setDni(rs.getString("dni"));
                    userOld.setEmail(rs.getString("email"));
                    userOld.setTelefono(rs.getString("telefono"));
                    userOld.setUbicacion(rs.getString("ubicacion"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        userNew.setUsername((username==null)?userOld.getUsername():username);
        userNew.setPassword((password==null)?userOld.getPassword():password);
        userNew.setNombre((nombre==null)?userOld.getNombre():nombre);
        userNew.setApellidos((apellidos==null)?userOld.getApellidos():apellidos);
        userNew.setDni((dni==null)?userOld.getDni():dni);
        userNew.setEmail((email==null)?userOld.getEmail():email);
        userNew.setTelefono((telefono==null)?userOld.getTelefono():telefono);
        userNew.setUbicacion((ubicacion==null)?userOld.getUbicacion():ubicacion);

        PreparedStatement stmt=con.prepareStatement("UPDATE `usuarios`" + 
        "SET `username` = '" + userNew.getUsername() + "', `password` = '" + userNew.getPassword() + "', `nombre` = '" + userNew.getNombre() + "', `apellidos` = '" + userNew.getApellidos() + "'," +
        " `dni` = '" + userNew.getDni() + "', `email` = '" + userNew.getEmail() + "', `telefono` = '" + userNew.getTelefono() + "', " +
        " `ubicacion` = '" + userNew.getUbicacion() + "' " +
        "WHERE (`id` = '" + id + "');");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el usuario!";
    }

    //////////////// DELETE ////////////////

    public String deleteUsuarioId(Integer id) throws SQLException{
        String SQL = "DELETE FROM usuarios WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "El usuario se ha eliminado correctamente!";
    }

    //////////////////////////////// Empresa ////////////////////////////////

    //////////////// GET ////////////////

    public String loginEmpresa(String email, String password) throws SQLException {
        String SQL = "SELECT * FROM empresas WHERE email='" + email + 
                    "' AND password='" + password + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        
        try {
            while(rs.next()){
                    empresa.setId(rs.getInt("id"));
                    empresa.setNombre(rs.getString("nombre"));
                    empresa.setEmail(rs.getString("email"));
                    empresa.setPassword(rs.getString("password"));
                    empresa.setIdentificacionFiscal(rs.getString("identificacionFiscal"));
                    empresa.setDescripcion(rs.getString("descripcion"));
                    empresa.setUbicacion(rs.getString("ubicacion"));
                    empresa.setLatlng(rs.getString("latlng"));
        
                    System.out.println(empresa);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(empresa);
    }

    public String getEmpresaId(Integer id) throws SQLException{
        String SQL = "SELECT * FROM empresas WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                    empresa.setId(rs.getInt("id"));
                    empresa.setNombre(rs.getString("nombre"));
                    empresa.setEmail(rs.getString("email"));
                    empresa.setPassword(rs.getString("password"));
                    empresa.setIdentificacionFiscal(rs.getString("identificacionFiscal"));
                    empresa.setDescripcion(rs.getString("descripcion"));
                    empresa.setUbicacion(rs.getString("ubicacion"));
                    empresa.setLatlng(rs.getString("latlng"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(empresa);
    }

    //////////////// POST ////////////////

    public String registroEmpresa(String nombre, String email, String password, String identificacionFiscal, String descripcion, String ubicacion, String latlng) throws SQLException{
        PreparedStatement stmt=con.prepareStatement("INSERT INTO `empresas` " +
        "(`nombre`, `email`, `password`, `identificacionFiscal`, `descripcion`, `ubicacion`, `latlng`)" +
        "VALUES(?, ?, ?, ?, ?, ?, ?)");  

        stmt.setString(1, (nombre==null)?"":nombre);
        stmt.setString(2, (email==null)?"":email);
        stmt.setString(3, (password==null)?"":password);
        stmt.setString(4, (identificacionFiscal==null)?"":identificacionFiscal);
        stmt.setString(5, (descripcion==null)?"":descripcion);
        stmt.setString(6, (ubicacion==null)?"":ubicacion);
        stmt.setString(7, (latlng==null)?"":latlng);

        stmt.executeUpdate();  
        return "La empresa se ha registrado correctamente!";
    }

    //////////////// PUT ////////////////
    
    public String putEmpresa(Integer id, String nombre, String email, String password, String identificacionFiscal, String descripcion, String ubicacion, String latlng) throws SQLException{
        Empresa empresaOld = new Empresa();
        Empresa empresaNew = new Empresa();
        String SQL = "SELECT * FROM empresas WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                empresaOld.setId(rs.getInt("id"));
                empresaOld.setNombre(rs.getString("nombre"));
                empresaOld.setEmail(rs.getString("email"));
                empresaOld.setPassword(rs.getString("password"));
                empresaOld.setIdentificacionFiscal(rs.getString("identificacionFiscal"));
                empresaOld.setDescripcion(rs.getString("descripcion"));
                empresaOld.setUbicacion(rs.getString("ubicacion"));
                empresaOld.setLatlng(rs.getString("latlng"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        empresaNew.setNombre((nombre==null)?empresaOld.getNombre():nombre);
        empresaNew.setEmail((email==null)?empresaOld.getEmail():email);
        empresaNew.setPassword((password==null)?empresaOld.getPassword():password);
        empresaNew.setIdentificacionFiscal((identificacionFiscal==null)?empresaOld.getIdentificacionFiscal():identificacionFiscal);
        empresaNew.setDescripcion((descripcion==null)?empresaOld.getDescripcion():descripcion);
        empresaNew.setUbicacion((ubicacion==null)?empresaOld.getUbicacion():ubicacion);
        empresaNew.setLatlng((latlng==null)?empresaOld.getLatlng():latlng);

        PreparedStatement stmt=con.prepareStatement("UPDATE `empresas`" + 
        "SET `nombre` = '" + empresaNew.getNombre() + "', `email` = '" + empresaNew.getNombre() + "', `password` = '" + empresaNew.getPassword() + "'," + 
        " `identificacionFiscal` = '" + empresaNew.getIdentificacionFiscal() + "',`descripcion` = '" + empresaNew.getDescripcion() + "',`ubicacion` = '" + empresaNew.getUbicacion() + "', " +
        " `latlng` = '" + empresaNew.getLatlng() + "' " +
        "WHERE (`id` = '" + id + "');");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente la empresa!";
    }

    //////////////// DELETE ////////////////

    public String deleteEmpresaId(Integer id) throws SQLException{
        String SQL = "DELETE FROM empresas WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "La empresa se ha eliminado correctamente!";
    }

    //////////////////////////////// Reto ////////////////////////////////

    //////////////// GET ////////////////

    public String getRetos() throws SQLException {
        String SQL = "SELECT * FROM retos ";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<String> retosList = new ArrayList<String>();

        try {
            while(rs.next()){
                    reto.setId(rs.getInt("id"));
                    reto.setId_Creador(rs.getInt("id_Creador"));
                    reto.setNombre(rs.getString("nombre"));
                    reto.setDescripcion(rs.getString("descripcion"));
                    reto.setTecnologias(rs.getString("tecnologias"));
                    reto.setParticipantesMax(rs.getInt("participantesMax"));
                    reto.setParticipantes(rs.getInt("participantes"));
                    reto.setParticipantes(rs.getInt("multimedia_id"));
                    reto.setNivell(rs.getInt("nivell"));
                    reto.setFecha_limite(rs.getString("fecha_limite"));
        
                    retosList.add(gson.toJson(reto));
                }
                System.out.println(retosList);
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }

    public String getRetoId(Integer id) throws SQLException{
        String SQL = "SELECT * FROM retos WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        System.out.println(SQL);
        System.out.println(stmt);
        try {
            while(rs.next()){
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setParticipantes(rs.getInt("multimedia_id"));
                reto.setNivell(rs.getInt("nivell"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(reto);
    }

    public String getRetoNivell(Integer id) throws SQLException{

        return null;
    }


    //////////////// POST ////////////////

    // Crea un reto con un archivo multimedia
    public String postReto(Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, Integer multimedia_id, Integer nivell, String fecha_limite) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("INSERT INTO `retos`" +
        " (`id_creador`, `nombre`, `descripcion`, `tecnologias`, `participantesMax`, `participantes`, `multimedia_id`, `nivell`, `fecha_limite`)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

        
        stmt.setInt(1, id_creador);
        stmt.setString(2, (nombre==null)?"":nombre);
        stmt.setString(3, (descripcion==null)?"":descripcion);
        stmt.setString(4, (tecnologias==null)?"":tecnologias);
        stmt.setInt(5, (participantesMax==null)?0:participantesMax);
        stmt.setInt(6, (participantes==null)?0:participantes);
        stmt.setInt(7, (multimedia_id==null)?null:multimedia_id);
        stmt.setInt(8, (nivell==null)?null:nivell);
        stmt.setString(9, (fecha_limite==null)?null:fecha_limite);

        stmt.executeUpdate();  
        return "El reto se ha registrado correctamente!";
    }
    
    // Crea un reto sin un archivo multimedia
    public String postReto(Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, Integer nivell, String fecha_limite) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("INSERT INTO `retos`" +
        " (`id_creador`, `nombre`, `descripcion`, `tecnologias`, `participantesMax`, `participantes`, `nivell`, `fecha_limite`)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

        
        stmt.setInt(1, id_creador);
        stmt.setString(2, (nombre==null)?"":nombre);
        stmt.setString(3, (descripcion==null)?"":descripcion);
        stmt.setString(4, (tecnologias==null)?"":tecnologias);
        stmt.setInt(5, (participantesMax==null)?0:participantesMax);
        stmt.setInt(6, (participantes==null)?0:participantes);
        stmt.setInt(7, (nivell==null)?null:nivell);
        stmt.setString(8, (fecha_limite==null)?null:fecha_limite);

        stmt.executeUpdate();  
        return "El reto se ha registrado correctamente!";
    }

    //////////////// PUT ////////////////
    
    public String putReto(Integer id, Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, Integer multimedia_id, Integer nivell, String fecha_limite) throws SQLException{
        Reto retoNew = new Reto();
        Reto retoOld = new Reto();
        String SQL = "SELECT * FROM retos WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                retoOld.setId_Creador(rs.getInt("id_creador"));
                retoOld.setNombre(rs.getString("nombre"));
                retoOld.setDescripcion(rs.getString("descripcion"));
                retoOld.setTecnologias(rs.getString("tecnologias"));
                retoOld.setParticipantesMax(rs.getInt("participantesMax"));
                retoOld.setParticipantes(rs.getInt("participantes"));
                retoOld.setMultimedia_id(rs.getInt("multimedia_id"));
                retoOld.setNivell(rs.getInt("nivell"));
                retoOld.setFecha_limite(rs.getString("fecha_limite"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        retoNew.setId_Creador((id_creador==null)?retoOld.getId_Creador():id_creador);
        retoNew.setNombre((nombre==null)?retoOld.getNombre():nombre);
        retoNew.setDescripcion((descripcion==null)?retoOld.getDescripcion():descripcion);
        retoNew.setTecnologias((tecnologias==null)?retoOld.getTecnologias():tecnologias);
        retoNew.setParticipantesMax((participantesMax==null)?retoOld.getParticipantesMax():participantesMax);
        retoNew.setParticipantes((participantes==null)?retoOld.getParticipantes():participantes);
        retoNew.setMultimedia_id((multimedia_id==null)?retoOld.getMultimedia_id():multimedia_id);
        retoNew.setNivell((nivell==null)?retoOld.getNivell():nivell);
        retoNew.setFecha_limite((fecha_limite==null)?retoOld.getFecha_limite():fecha_limite);

        PreparedStatement stmt=con.prepareStatement("UPDATE `retos`" + 
        "SET `id_creador` = '" + retoNew.getId_Creador() + "', `nombre` = '" + retoNew.getNombre() + "', `descripcion` = '" + retoNew.getDescripcion() + "',"+
        "`tecnologias` = '" + retoNew.getTecnologias() + "', `participantesMax` = '" + retoNew.getParticipantesMax() + "', `participantes` = '" + retoNew.getParticipantes() + "', " +
        "`nivell` = '" + retoNew.getNivell() + "', `fecha_limite` = '" + retoNew.getFecha_limite() + "' WHERE `id` = " + id + ";");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el reto!";
    }

    //////////////// DELETE ////////////////

    public String deleteRetoId(Integer id) throws SQLException{
        String SQL = "DELETE  FROM retos WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "El reto se ha eliminado correctamente!";
    }



    //////////////////////////////// EXTRAS ////////////////////////////////

    public ArrayList<String> leerUrl(HttpServletRequest request){
        Enumeration<String> numeracion = request.getParameterNames();
        ArrayList<String> datos = new ArrayList<String>();

        while (numeracion.hasMoreElements()) {
            String name = (String) numeracion.nextElement();
            String values[] = request.getParameterValues(name);
            if (values != null) {
                datos.add(values[0]);
            }
        }

        return datos;
    }

    public ArrayList<String> leerUrlUsuario(HttpServletRequest request){
        Enumeration<String> numeracion = request.getParameterNames();
        ArrayList<String> datos = new ArrayList<String>();

        while (numeracion.hasMoreElements()) {
            String name = (String) numeracion.nextElement();
            String values[] = request.getParameterValues(name);
            if (values != null) {
                datos.add(values[0]);
            }
        }

        System.out.println(datos);
        return datos;
    }

    public String respuestaServidor(Integer statusCode){
        String respuesta="Hay algun tipo de error inesperado...";

        if(statusCode==200){
            respuesta = "La solicitud se ha realizado correctamente.";
        }else if(statusCode==400){
            respuesta = "El servidor no pudo entender la solicitud debido a una sintaxis no valida.";
        }else if(statusCode==404){
            respuesta = "El servidor no puede encontrar el recurso solicitado.";
        }else if(statusCode==500){
            respuesta = "El servidor se ha encontrado con una situación que no sabe cómo manejar.";
        }

        return respuesta;
    }

    

}