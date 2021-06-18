package com.web;

import javax.servlet.http.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.web.tablas.*;
import com.google.gson.*;

public class DBController extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost/db_worthy?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);
    Connection con = null;
    Statement stmt = null;
    Usuario usuario = new Usuario();
    Empresa empresa = new Empresa();
    Reto reto = new Reto();
    Comentario comentario = new Comentario();
    Post post = new Post();
    UsuariosRetos usre = new UsuariosRetos();
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

        String a="{\"error\": true}";
        
        try {
            
        stmt.setString(1, (username==null)?"":username);
        stmt.setString(2, (password==null)?"":password);
        stmt.setString(3, (nombre==null)?"":nombre);
        stmt.setString(4, (apellidos==null)?"":apellidos);
        stmt.setString(5, (dni==null)?"":dni);
        stmt.setString(6, (email==null)?"":email);
        stmt.setString(7, (telefono==null)?"":telefono);
        stmt.setString(8, (ubicacion==null)?"":ubicacion);

        stmt.executeUpdate();  
        a = "{\"username\" : \"" + username + "\", \"password\" : \"" + password + "\", \"nombre\" : \"" + nombre + "\", \"apellidos\" : \"" + apellidos + 
        "\", \"dni\" : \"" + dni +  "\", \"email\" : \"" + email +  "\", \"telefono\" : \"" + telefono +"\", \"ubicacion\" : \"" + ubicacion +"\"}";
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return a;
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
        "SET `nombre` = '" + empresaNew.getNombre() + "', `email` = '" + empresaNew.getEmail() + "', `password` = '" + empresaNew.getPassword() + "'," + 
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
        ArrayList<Reto> retosList = new ArrayList<Reto>();

        try {
            while(rs.next()){
                    reto = new Reto();
                    reto.setId(rs.getInt("id"));
                    reto.setId_Creador(rs.getInt("id_Creador"));
                    reto.setNombre(rs.getString("nombre"));
                    reto.setDescripcion(rs.getString("descripcion"));
                    reto.setTecnologias(rs.getString("tecnologias"));
                    reto.setParticipantesMax(rs.getInt("participantesMax"));
                    reto.setParticipantes(rs.getInt("participantes"));
                    reto.setMultimedia(rs.getString("multimedia"));
                    reto.setNivel(rs.getInt("nivel"));
                    reto.setFecha_limite(rs.getString("fecha_limite"));
        
                    retosList.add(reto);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }

    public String getRetoId(Integer id) throws SQLException{
        String SQL = "SELECT * FROM retos WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setMultimedia(rs.getString("multimedia"));
                reto.setNivel(rs.getInt("nivel"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(reto);
    }

    public String getRetosNivel(Integer nivel) throws SQLException{
        String SQL = "SELECT * FROM retos WHERE nivel='" + nivel + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Reto> retosList = new ArrayList<Reto>();

        try {
            while(rs.next()){
                reto = new Reto();
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setMultimedia(rs.getString("multimedia"));
                reto.setNivel(rs.getInt("nivel"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                
                retosList.add(reto);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }

    public String getRetosTecnologia(String tecnologia) throws SQLException{
        String SQL = "SELECT * FROM retos WHERE tecnologias='" + tecnologia + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Reto> retosList = new ArrayList<Reto>();

        try {
            while(rs.next()){
                reto = new Reto();
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setMultimedia(rs.getString("multimedia"));
                reto.setNivel(rs.getInt("nivel"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                
                retosList.add(reto);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }

    public String getRetosNombre(String nombre) throws SQLException{
        String SQL = "SELECT * FROM retos WHERE nombre like '%" + nombre + "%' ORDER BY nombre Asc";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Reto> retosList = new ArrayList<Reto>();

        try {
            while(rs.next()){
                reto = new Reto();
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setMultimedia(rs.getString("multimedia"));
                reto.setNivel(rs.getInt("nivel"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                
                retosList.add(reto);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }
    
    public String getRetosFechaLimite(String fechaLimite) throws SQLException{
        DateTimeFormatter fechaActual = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String SQL = "SELECT * FROM retos WHERE fecha_limite BETWEEN '" + fechaActual.format(LocalDateTime.now()) + "' AND '" +  fechaLimite + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Reto> retosList = new ArrayList<Reto>();
        
        try {
            while(rs.next()){
                reto = new Reto();
                reto.setId(rs.getInt("id"));
                reto.setId_Creador(rs.getInt("id_Creador"));
                reto.setNombre(rs.getString("nombre"));
                reto.setDescripcion(rs.getString("descripcion"));
                reto.setTecnologias(rs.getString("tecnologias"));
                reto.setParticipantesMax(rs.getInt("participantesMax"));
                reto.setParticipantes(rs.getInt("participantes"));
                reto.setMultimedia(rs.getString("multimedia"));
                reto.setNivel(rs.getInt("nivel"));
                reto.setFecha_limite(rs.getString("fecha_limite"));
                
                retosList.add(reto);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(retosList);
    }


    //////////////// POST ////////////////

    // Crea un reto con un archivo multimedia
    public String postReto(Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, String multimedia, Integer nivel, String fecha_limite) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("INSERT INTO `retos`" +
        " (`id_creador`, `nombre`, `descripcion`, `tecnologias`, `participantesMax`, `participantes`, `multimedia`, `nivel`, `fecha_limite`)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

        
        stmt.setInt(1, id_creador);
        stmt.setString(2, (nombre==null)?"":nombre);
        stmt.setString(3, (descripcion==null)?"":descripcion);
        stmt.setString(4, (tecnologias==null)?"":tecnologias);
        stmt.setInt(5, (participantesMax==null)?0:participantesMax);
        stmt.setInt(6, (participantes==null)?0:participantes);
        stmt.setString(7, (multimedia==null)?"":multimedia);
        stmt.setInt(8, (nivel==null)?null:nivel);
        stmt.setString(9, (fecha_limite==null)?null:fecha_limite);

        stmt.executeUpdate();  
        return "El reto se ha registrado correctamente!";
    }
    
    // Crea un reto sin un archivo multimedia
    public String postReto(Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, Integer nivel, String fecha_limite) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("INSERT INTO `retos`" +
        " (`id_creador`, `nombre`, `descripcion`, `tecnologias`, `participantesMax`, `participantes`, `nivel`, `fecha_limite`)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

        
        stmt.setInt(1, id_creador);
        stmt.setString(2, (nombre==null)?"":nombre);
        stmt.setString(3, (descripcion==null)?"":descripcion);
        stmt.setString(4, (tecnologias==null)?"":tecnologias);
        stmt.setInt(5, (participantesMax==null)?0:participantesMax);
        stmt.setInt(6, (participantes==null)?0:participantes);
        stmt.setInt(7, (nivel==null)?null:nivel);
        stmt.setString(8, (fecha_limite==null)?null:fecha_limite);

        stmt.executeUpdate();  
        return "El reto se ha registrado correctamente!";
    }

    //////////////// PUT ////////////////
    
    public String putReto(Integer id, Integer id_creador, String nombre, String descripcion, String tecnologias, Integer participantesMax, Integer participantes, String multimedia, Integer nivel, String fecha_limite) throws SQLException{
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
                retoOld.setMultimedia(rs.getString("multimedia"));
                retoOld.setNivel(rs.getInt("nivel"));
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
        retoNew.setMultimedia((multimedia==null)?retoOld.getMultimedia():multimedia);
        retoNew.setNivel((nivel==null)?retoOld.getNivel():nivel);
        retoNew.setFecha_limite((fecha_limite==null)?retoOld.getFecha_limite():fecha_limite);

        PreparedStatement stmt=con.prepareStatement("UPDATE `retos`" + 
        "SET `id_creador` = '" + retoNew.getId_Creador() + "', `nombre` = '" + retoNew.getNombre() + "', `descripcion` = '" + retoNew.getDescripcion() + "',"+
        "`tecnologias` = '" + retoNew.getTecnologias() + "', `participantesMax` = '" + retoNew.getParticipantesMax() + "', `participantes` = '" + retoNew.getParticipantes() + "', " +
        "`multimedia` = '" + retoNew.getMultimedia() + "', `nivel` = '" + retoNew.getNivel() + "', `fecha_limite` = '" + retoNew.getFecha_limite() + "' WHERE `id` = " + id + ";");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el reto!";
    }

    //////////////// DELETE ////////////////

    public String deleteRetoId(Integer id) throws SQLException{
        String SQL = "DELETE  FROM retos WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "El reto se ha eliminado correctamente!";
    }


    //////////////////////////////// Comentario ////////////////////////////////

    //////////////// GET ////////////////

    public String getComentariosPost(Integer id) throws SQLException{
        String SQL = "SELECT * FROM comentarios WHERE posts_id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Comentario> comentariosList = new ArrayList<Comentario>();

        try {
            while(rs.next()){
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setPosts_id(rs.getInt("posts_id"));
                comentario.setUsuario_id(rs.getInt("usuarios_id"));
                comentario.setUsuario_id(rs.getInt("empresas_id"));
                comentario.setMultimedia(rs.getString("multimedia"));
                comentario.setFecha_creacion(rs.getString("fecha_creacion"));
                
                comentariosList.add(comentario);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(comentariosList);
    }

    public String getComentariosUsuario(Integer id) throws SQLException{
        String SQL = "SELECT * FROM comentarios WHERE usuarios_id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Comentario> comentariosList = new ArrayList<Comentario>();

        try {
            while(rs.next()){
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setPosts_id(rs.getInt("posts_id"));
                comentario.setUsuario_id(rs.getInt("usuarios_id"));
                comentario.setMultimedia(rs.getString("multimedia"));
                comentario.setFecha_creacion(rs.getString("fecha_creacion"));
                
                comentariosList.add(comentario);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(comentariosList);
    }

    public String getComentariosEmpresa(Integer id) throws SQLException{
        String SQL = "SELECT * FROM comentarios WHERE empresas_id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Comentario> comentariosList = new ArrayList<Comentario>();

        try {
            while(rs.next()){
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setPosts_id(rs.getInt("posts_id"));
                comentario.setUsuario_id(rs.getInt("empresas_id"));
                comentario.setMultimedia(rs.getString("multimedia"));
                comentario.setFecha_creacion(rs.getString("fecha_creacion"));
                
                comentariosList.add(comentario);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(comentariosList);
    }


    //////////////// POST ////////////////

    // Crea un comentario con un archivo multimedia
    public String postComentario(String comentario, Integer posts_id, Integer usuarios_id, Integer empresas_id, String multimedia, String fecha_creacion) throws SQLException{
        if(usuarios_id==0){
            // Crea un comentario como una empresa
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `comentarios`" +
            " (`comentario`, `posts_id`, `empresas_id`, `multimedia`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");

            stmt.setString(1, comentario);
            stmt.setInt(2, posts_id);
            stmt.setInt(3, (empresas_id==null)?null:empresas_id);
            stmt.setString(4, (multimedia==null)?"":multimedia);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El comentario se ha registrado correctamente!";
        }else{
            // Crea un comentario como un usuario
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `comentarios`" +
            " (`comentario`, `posts_id`, `usuarios_id`,  `multimedia`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");
    
            System.out.println("usuario");
            System.out.println(stmt);

            
            stmt.setString(1, comentario);
            stmt.setInt(2, posts_id);
            stmt.setInt(3, (usuarios_id==null)?null:usuarios_id);
            stmt.setString(4, (multimedia==null)?"":multimedia);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El cometario se ha registrado correctamente!";
        }
    }

    // Crea un comentario sin un archivo multimedia
    public String postComentario(String comentario, Integer posts_id, Integer usuarios_id, Integer empresas_id, String fecha_creacion) throws SQLException{
        if(usuarios_id==0){
            // Crea un comentario como una empresa
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `comentarios`" +
            " (`comentario`, `posts_id`, `empresas_id`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");

            stmt.setString(1, comentario);
            stmt.setInt(2, posts_id);
            stmt.setInt(3, (empresas_id==null)?null:empresas_id);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }else{
            // Crea un comentario como un usuario
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `comentarios`" +
            " (`comentario`, `posts_id`, `usuarios_id`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");
    
            System.out.println("usuario");
            System.out.println(stmt);

            
            stmt.setString(1, comentario);
            stmt.setInt(2, posts_id);
            stmt.setInt(3, (usuarios_id==null)?null:usuarios_id);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }
    }

    //////////////// PUT ////////////////

    public String putComentario(Integer id, String comentario, String multimedia) throws SQLException{
        Comentario comentarioNew = new Comentario();
        Comentario comentarioOld = new Comentario();
        String SQL = "SELECT * FROM comentarios WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                comentarioOld.setComentario(rs.getString("comentario"));
                comentarioOld.setMultimedia(rs.getString("multimedia"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        comentarioNew.setComentario((comentario==null)?comentarioOld.getComentario():comentario);
        comentarioNew.setMultimedia((multimedia==null)?comentarioOld.getMultimedia():multimedia);

        PreparedStatement stmt=con.prepareStatement("UPDATE `comentarios`" + 
        "SET `comentario` = '" + comentarioNew.getComentario() +  "', `multimedia` = '" + comentarioNew.getMultimedia() + 
        "' WHERE `id` = " + id + ";");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el comentario!";
    }

    //////////////// DELETE ////////////////

    public String deleteComentario(Integer id) throws SQLException{
        String SQL = "DELETE  FROM comentarios WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "El comentario se ha eliminado correctamente!";
    }



    //////////////////////////////// Post ////////////////////////////////

    //////////////// GET ////////////////

    public String getPosts() throws SQLException {
        String SQL = "SELECT * FROM posts ";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<Post> postsList = new ArrayList<Post>();

        try {
            while(rs.next()){
                post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitulo(rs.getString("titulo"));
                post.setDescripcion(rs.getString("descripcion"));
                post.setUsuario_id(rs.getInt("usuarios_id"));
                post.setEmpresa_id(rs.getInt("empresas_id"));
                post.setMultimedia(rs.getString("multimedia"));
                post.setFecha_creacion(rs.getString("fecha_creacion"));
    
                    postsList.add(post);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(postsList);
    }

    public String getPostId(Integer id) throws SQLException{
        String SQL = "SELECT * FROM posts WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                post.setId(rs.getInt("id"));
                post.setTitulo(rs.getString("titulo"));
                post.setDescripcion(rs.getString("descripcion"));
                post.setUsuario_id(rs.getInt("usuarios_id"));
                post.setEmpresa_id(rs.getInt("empresas_id"));
                post.setMultimedia(rs.getString("multimedia"));
                post.setFecha_creacion(rs.getString("fecha_creacion"));
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(post);
    }


    //////////////// POST ////////////////

    // Crea un comentario con un archivo multimedia
    public String postNewPost(String titulo, String descripcion, Integer usuarios_id, Integer empresas_id, String multimedia, String fecha_creacion) throws SQLException{
        if(usuarios_id==0){
            // Crea un comentario como una empresa
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `posts`" +
            " (`titulo`, `descripcion`, `empresas_id`, `multimedia`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");

            stmt.setString(1, titulo);
            stmt.setString(2, descripcion);
            stmt.setInt(3, (empresas_id==null)?null:empresas_id);
            stmt.setString(4, (multimedia==null)?"":multimedia);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }else{
            // Crea un comentario como un usuario
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `posts`" +
            " (`titulo`, `descripcion`, `usuarios_id`, `multimedia`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?, ?);");
    
            System.out.println("usuario");
            System.out.println(stmt);

            
            stmt.setString(1, titulo);
            stmt.setString(2, descripcion);
            stmt.setInt(3, (usuarios_id==null)?null:usuarios_id);
            stmt.setString(4, (multimedia==null)?"":multimedia);
            stmt.setString(5, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }
    }

    // Crea un comentario sin un archivo multimedia
    public String postNewPost(String titulo, String descripcion, Integer usuarios_id, Integer empresas_id, String fecha_creacion) throws SQLException{
        if(usuarios_id==0){
            // Crea un comentario como una empresa
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `posts`" +
            " (`titulo`, `descripcion`, `empresas_id`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?);");

            stmt.setString(1, titulo);
            stmt.setString(2, descripcion);
            stmt.setInt(3, (empresas_id==null)?null:empresas_id);
            stmt.setString(4, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }else{
            // Crea un comentario como un usuario
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `posts`" +
            " (`titulo`, `descripcion`, `usuarios_id`, `fecha_creacion`)" +
            " VALUES (?, ?, ?, ?);");
    
            System.out.println("usuario");
            System.out.println(stmt);

            
            stmt.setString(1, titulo);
            stmt.setString(2, descripcion);
            stmt.setInt(3, (usuarios_id==null)?null:usuarios_id);
            stmt.setString(4, (fecha_creacion==null)?"":fecha_creacion);
    
            stmt.executeUpdate();  
            return "El post se ha registrado correctamente!";
        }
    }

    //////////////// PUT ////////////////

    public String putPost(Integer id, String titulo, String descripcion, String multimedia) throws SQLException{
        Post postNew = new Post();
        Post postOld = new Post();
        String SQL = "SELECT * FROM posts WHERE id='" + id + "'";
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                postOld.setTitulo(rs.getString("titulo"));
                postOld.setDescripcion(rs.getString("descripcion"));
                postOld.setMultimedia(rs.getString("multimedia"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        postNew.setTitulo((titulo==null)?postOld.getTitulo():titulo);
        postNew.setDescripcion((descripcion==null)?postOld.getDescripcion():descripcion);
        postNew.setMultimedia((multimedia==null)?postOld.getMultimedia():multimedia);

        PreparedStatement stmt=con.prepareStatement("UPDATE `posts`" + 
        "SET `titulo` = '" + postNew.getTitulo() +  "', `descripcion` = '" + postNew.getDescripcion() +  "', `multimedia` = '" + postNew.getMultimedia() + 
        "' WHERE `id` = " + id + ";");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el post!";
    }


    //////////////// DELETE ////////////////

    public String deletePost(Integer id) throws SQLException{
        String SQL = "DELETE  FROM posts WHERE id='" + id + "'";
        
        stmt.executeUpdate(SQL);

        return "El posts se ha eliminado correctamente!";
    }


    //////////////////////////////// UsuariosRetos ////////////////////////////////

    //////////////// GET ////////////////

    // Obtiene los UsuariosRetos
    public String getURUsuarios() throws SQLException{
        String SQL = "SELECT * FROM usuarios_has_retos";
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<UsuariosRetos> usresList = new ArrayList<UsuariosRetos>();

        try {
            while(rs.next()){
                usre = new UsuariosRetos();
                usre.setUsuarios_id(rs.getInt("usuarios_id"));
                usre.setRetos_id(rs.getInt("retos_id"));
                usre.setMultimedia(rs.getString("multimedia"));

                usresList.add(usre);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(usresList);
    }

    // Obtiene los retos que tiene un usuario
    public String getURbyUsuarios(Integer usuarios_id) throws SQLException{
        String SQL = "SELECT * FROM usuarios_has_retos WHERE usuarios_id = " + usuarios_id;
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<UsuariosRetos> usresList = new ArrayList<UsuariosRetos>();

        try {
            while(rs.next()){
                usre = new UsuariosRetos();
                usre.setUsuarios_id(rs.getInt("usuarios_id"));
                usre.setRetos_id(rs.getInt("retos_id"));
                usre.setMultimedia(rs.getString("multimedia"));

                usresList.add(usre);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(usresList);
    }

    // Obtiene los usuarios que tiene un reto
    public String getURbyReto(Integer retos_id) throws SQLException{
        String SQL = "SELECT * FROM usuarios_has_retos WHERE retos_id = " + retos_id;
        ResultSet rs = stmt.executeQuery(SQL);
        ArrayList<UsuariosRetos> usresList = new ArrayList<UsuariosRetos>();

        try {
            while(rs.next()){
                usre = new UsuariosRetos();
                usre.setUsuarios_id(rs.getInt("usuarios_id"));
                usre.setRetos_id(rs.getInt("retos_id"));
                usre.setMultimedia(rs.getString("multimedia"));

                usresList.add(usre);
                }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        return gson.toJson(usresList);
    }

    //////////////// POST ////////////////

    public String postUR(Integer usuarios_id, Integer retos_id, String multimedia) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("INSERT INTO `usuarios_has_retos`" +
        " (`usuarios_id`, `retos_id`, `multimedia`)" +
        " VALUES (?, ?, ?);");

        String a="{\"error\": true}";

        try {
            stmt.setInt(1, usuarios_id);
            stmt.setInt(2, retos_id);
            stmt.setString(3, multimedia);

            stmt.executeUpdate();

             a= "{\"usuarios_id\": "+ usuarios_id+ ", \"retos_id\": "+retos_id+", \"multimedia\": "+multimedia+"}";

        } catch (Exception e) {
            //TODO: handle exception
        }

        return a;
    }


    //////////////// PUT ////////////////

    public String putUR(Integer usuarios_id, Integer retos_id, String multimedia) throws SQLException{
        UsuariosRetos usreNew = new UsuariosRetos();
        UsuariosRetos usreOld = new UsuariosRetos();
        String SQL = "SELECT * FROM usuarios_has_retos WHERE usuarios_id=" + usuarios_id + " AND retos_id = " + retos_id;
        ResultSet rs = stmt.executeQuery(SQL);

        try {
            while(rs.next()){
                usreOld.setMultimedia(rs.getString("multimedia"));
            }
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }

        usreNew.setMultimedia((multimedia==null)?usreOld.getMultimedia():multimedia);

        PreparedStatement stmt=con.prepareStatement("UPDATE `usuarios_has_retos`" + 
        "SET `multimedia` = '" + usreNew.getMultimedia() + 
        "' WHERE usuarios_id=" + usuarios_id + " AND retos_id = " + retos_id + ";");  

        stmt.executeUpdate();  
        
        return "Se ha modificado correctamente el usuario reto!";
    }


    //////////////// DELETE ////////////////



    //////////////////////////////// EXTRAS ////////////////////////////////

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