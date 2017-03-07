/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.Modelo_TO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ModeloController {

    Statement st;
    private List<Modelo_TO> modelos;

    public void ModeloController() throws SQLException {
        this.st = ConexionSQL.conexion();

    }

    public ModeloController() {
        modelos = new ArrayList<>();
    }

    public List<Modelo_TO> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo_TO> modelos) {
        this.modelos = modelos;
    }

    public void consultarModelos(int idMarca) {

        try {

            modelos = this.consultarModelo(idMarca);
        } catch (Exception ex) {
            Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Modelo_TO> consultarModelo(int idMarca) throws Exception {

        ModeloController();

        List<Modelo_TO> models = new ArrayList<>();

        try {

            String sql = "select  intIdModelo, intIdMarca, varNombreModelo "
                    + "from modelo where intIdMarca = '" + idMarca + "';";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                models.add(new Modelo_TO(rs.getInt(1), rs.getInt(2), rs.getString(3)));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return models;

    }

    public Modelo_TO consultarModeloNombre(String nombre) throws Exception {

        Modelo_TO modelo = new Modelo_TO();

        try {

            String sql = "select  intIdModelo,intIdMarca,  varNombreModelo  "
                    + "from modelo where varNombreModelo = '" + nombre.trim() + "';";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                modelo = new Modelo_TO(rs.getInt(1), rs.getInt(2), rs.getString(3));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return modelo;

    }

}
