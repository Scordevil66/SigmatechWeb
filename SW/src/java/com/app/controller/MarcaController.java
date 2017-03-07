/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.Ciudad_TO;
import com.app.models.Marca_TO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author user
 */
public class MarcaController {

    Statement st;
    private List<Marca_TO> marcas;

    public MarcaController() throws SQLException {
        this.st = ConexionSQL.conexion();
        marcas = new ArrayList<>();
    }

    public List<Marca_TO> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca_TO> marcas) {
        this.marcas = marcas;
    }

    @PostConstruct
    public void init() {

        try {
            marcas = this.consultarMarca();

        } catch (Exception ex) {
            Logger.getLogger(MarcaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Marca_TO> consultarMarca() throws Exception {

        List<Marca_TO> marcas = new ArrayList<>();

        try {

            String sql = "select  intIdMarca, varNombreMarca "
                    + "from marca;";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                marcas.add(new Marca_TO(rs.getInt(1), rs.getString(2)));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return marcas;

    }

    public Marca_TO consultarMarcaNombre(String nombre) throws Exception {

        Marca_TO marca = new Marca_TO();

        try {

            String sql = "select  intIdMarca, varNombreMarca "
                    + "from marca where varNombreMarca = '" + nombre.trim() + "';";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                marca = new Marca_TO(rs.getInt(1), rs.getString(2));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return marca;

    }
}
