/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.TipoUsuario_TO;
import com.app.models.Usuario_TO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
public class UsuarioController {
    
    Statement st;
    private Usuario_TO usuario;
    private List<Usuario_TO> usuarios;
    
    public void UsuarioController() throws SQLException {
        this.st = ConexionSQL.conexion();
    }
    
     public UsuarioController() throws SQLException {
      
         usuario = new Usuario_TO();
         usuarios = new ArrayList<>();

    }

    @PostConstruct
    public void init() {

        try {
            usuarios = this.consultarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario_TO getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario_TO usuario) {
        this.usuario = usuario;
    }

    public List<Usuario_TO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario_TO> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    public void registrarUsuario() throws Exception {

        UsuarioController();
        int inv = 0;

        try {

            String sql = "INSERT INTO `biomedic`.`usuarios` (`varNomUsuario`, `varCorreo`, `varTelefono`, "
                    + "`varUsuarioAcceso`, `varContassena`, `intIdTipoUsuario`) VALUES "
                    + "('"+usuario.getNombreUsuario()+"', '"+usuario.getCorreo()+"', '"+usuario.getTelefono()+"', "
                    + "'"+usuario.getUsuarioAcceso()+"', '"+usuario.getContrasena()+"', '1');";

            System.out.println("sql: " + sql);

            st.execute(sql);

            inv++;

            if (inv > 0) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inventario registrado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage(null, message);

                usuario = new Usuario_TO();

            }

        } catch (Exception e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

    }
    
     public List<Usuario_TO> consultarUsuarios() throws Exception {

        UsuarioController();

        List<Usuario_TO> invs = new ArrayList<>();

        try {

            String sql = "SELECT  intIdUsuario, varNomUsuario, varCorreo, varTelefono,varUsuarioAcceso, varContassena, intIdTipoUsuario"
                    + " from usuarios  ;";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                invs.add(new Usuario_TO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new TipoUsuario_TO(rs.getInt(7), "")));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }
        return invs;
    }

    
}
