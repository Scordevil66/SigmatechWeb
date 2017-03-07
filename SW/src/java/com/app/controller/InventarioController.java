/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.Area_TO;
import com.app.models.EstadoInventario_TO;
import com.app.models.Inventario_TO;
import com.app.models.Marca_TO;
import com.app.models.Modelo_TO;
import com.app.models.Serie_TO;
import com.app.models.Servicio_TO;
import com.app.models.Ubicacion_TO;
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
public class InventarioController {

    Statement st;
    private Inventario_TO inventario;
    private Marca_TO marca;
    private List<Marca_TO> marcas;
//    private List<Modelo_TO> modelos;
    private List<Serie_TO> series;
    private List<Servicio_TO> servicios;
    private List<Ubicacion_TO> ubicaciones;
    private List<Area_TO> areas;
    private List<EstadoInventario_TO> estados;

    protected MarcaController marcaCT;
    protected ModeloController modeloCT;
    protected SerieController serieCT;
    protected ServicioController servicioCT;
    protected UbicacionController ubicacionCT;
    protected AreaController areaCT;
    protected EstadoController estadoCT;

    public void InventarioController() throws SQLException {
        this.st = ConexionSQL.conexion();
    }

    public InventarioController() throws SQLException {
        marca = new Marca_TO();
        inventario = new Inventario_TO();
        marcas = new ArrayList<>();
//        modelos = new ArrayList<>();
        series = new ArrayList<>();
        servicios = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        areas = new ArrayList<>();
        estados = new ArrayList<>();
        marcaCT = new MarcaController();
        modeloCT = new ModeloController();
        serieCT = new SerieController();
        servicioCT = new ServicioController();
        ubicacionCT = new UbicacionController();
        areaCT = new AreaController();
        estadoCT = new EstadoController();

    }

    @PostConstruct
    public void init() {

        try {
            marcas = marcaCT.consultarMarca();
            servicios = servicioCT.consultarServicio();
            ubicaciones = ubicacionCT.consultarUbicacion();
            areas = areaCT.consultarArea();
            estados = estadoCT.consultarEstadoInventario();
        } catch (Exception ex) {
            Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Inventario_TO getInventario() {
        return inventario;
    }

    public void setInventario(Inventario_TO inventario) {
        this.inventario = inventario;
    }

    public List<Marca_TO> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca_TO> marcas) {
        this.marcas = marcas;
    }
//
//    public List<Modelo_TO> getModelos() {
//        return modelos;
//    }
//
//    public void setModelos(List<Modelo_TO> modelos) {
//        this.modelos = modelos;
//    }

    public List<Serie_TO> getSeries() {
        return series;
    }

    public void setSeries(List<Serie_TO> series) {
        this.series = series;
    }

    public List<Servicio_TO> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio_TO> servicios) {
        this.servicios = servicios;
    }

    public List<Ubicacion_TO> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion_TO> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public List<Area_TO> getAreas() {
        return areas;
    }

    public void setAreas(List<Area_TO> areas) {
        this.areas = areas;
    }

    public List<EstadoInventario_TO> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoInventario_TO> estados) {
        this.estados = estados;
    }

    public Marca_TO getMarca() {
        return marca;
    }

    public void setMarca(Marca_TO marca) {
        this.marca = marca;
    }

//    public void consultarModelos(int idMarca) {
//
//        try {
//            modelos = modeloCT.consultarModelo(idMarca);
//        } catch (Exception ex) {
//            Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public int registrarInventario(Inventario_TO inventario) throws Exception {

        InventarioController();
        int inv = 0;

        try {

            String sql = "INSERT INTO `biomedic`.`inventario` "
                    + " (`varEquipo`, `intIdMarca`, `intIdModelo`, `intIdSerie`, "
                    + "  `varNumInvent`, `intIdEstadoInv`, `intIdServicio`, "
                    + "   `intIdUbicacion`, `longfoto`, `intEmpresa`, `intArea`,  "
                    + "	`varObservaciones`)  "
                    + "	VALUES "
                    + "	 ('" + inventario.getEquipo() + "', '" + inventario.getMarca().getIdMarca() + "', "
                    + "'" + inventario.getModelo().getIdModelo() + "', '" + inventario.getSerie().getIdSerie() + "',"
                    + " '" + inventario.getNroInventario() + "', '" + inventario.getEstadoInventario().getIdEstado() + "', "
                    + "'" + inventario.getServicio().getIdServicio() + "',  "
                    + "	 '" + inventario.getUbicacion().getIdUbicacion() + "', '', "
                    + "'0', '" + inventario.getArea().getIdArea() + "', '" + inventario.getObservaciones() + "');";

            System.out.println("sql: " + sql);

            st.execute(sql);

            inv++;

        } catch (Exception e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return inv;

    }

}
