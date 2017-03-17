/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.Area_TO;
import com.app.models.Empresa_TO;
import com.app.models.EstadoInventario_TO;
import com.app.models.Inventario_TO;
import com.app.models.Marca_TO;
import com.app.models.Modelo_TO;
import com.app.models.Serie_TO;
import com.app.models.Servicio_TO;
import com.app.models.Ubicacion_TO;
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
public class InventarioController {

    Statement st;
    private Inventario_TO inventario;
    private Marca_TO marca;
    private Modelo_TO modelo;
    private Serie_TO serie;
    private Servicio_TO servicio;
    private Ubicacion_TO ubicacion;
    private Area_TO area;
    private EstadoInventario_TO estadoInventario;
    private List<Inventario_TO> inventarios;
    private List<Modelo_TO> modelos;
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
        modelo = new Modelo_TO();
        serie = new Serie_TO();
        servicio = new Servicio_TO();
        ubicacion = new Ubicacion_TO();
        area = new Area_TO();
        estadoInventario = new EstadoInventario_TO();
        inventario = new Inventario_TO();
        inventarios = new ArrayList<>();
        modelos = new ArrayList<>();
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
            servicios = servicioCT.consultarServicio();
            ubicaciones = ubicacionCT.consultarUbicacion();
            areas = areaCT.consultarArea();
            estados = estadoCT.consultarEstadoInventario();
            inventarios = this.consultarInventarios();
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

    public List<Modelo_TO> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo_TO> modelos) {
        this.modelos = modelos;
    }

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

    public Modelo_TO getModelo() {
        return modelo;
    }

    public void setModelo(Modelo_TO modelo) {
        this.modelo = modelo;
    }

    public Serie_TO getSerie() {
        return serie;
    }

    public void setSerie(Serie_TO serie) {
        this.serie = serie;
    }

    public Servicio_TO getServicio() {
        return servicio;
    }

    public void setServicio(Servicio_TO servicio) {
        this.servicio = servicio;
    }

    public Ubicacion_TO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion_TO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Area_TO getArea() {
        return area;
    }

    public void setArea(Area_TO area) {
        this.area = area;
    }

    public EstadoInventario_TO getEstadoInventario() {
        return estadoInventario;
    }

    public void setEstadoInventario(EstadoInventario_TO estadoInventario) {
        this.estadoInventario = estadoInventario;
    }

    public List<Inventario_TO> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario_TO> inventarios) {
        this.inventarios = inventarios;
    }

    public void consultarModelos(int idMarca) {

        try {
            modelos = modeloCT.consultarModelo(idMarca);
        } catch (Exception ex) {
            Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarSeries(int idModelo) {

        try {
            series = serieCT.consultarSerie(idModelo);
        } catch (Exception ex) {
            Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarInventario() throws Exception {

        InventarioController();
        int inv = 0;

        try {

            String sql = "INSERT INTO `biomedic`.`inventario` "
                    + " (`varEquipo`, `intIdMarca`, `intIdModelo`, `intIdSerie`, "
                    + "  `varNumInvent`, `intIdEstadoInv`, `intIdServicio`, "
                    + "   `intIdUbicacion`, `longfoto`, `intEmpresa`, `intArea`,  "
                    + "	`varObservaciones`)  "
                    + "	VALUES "
                    + "	 ('" + inventario.getEquipo() + "', '" + getMarca().getIdMarca() + "', "
                    + "'" + getModelo().getIdModelo() + "', '" + getSerie().getIdSerie() + "',"
                    + " '" + inventario.getNroInventario() + "', '" + getEstadoInventario().getIdEstado() + "', "
                    + "'" + getServicio().getIdServicio() + "',  "
                    + "	 '" + getUbicacion().getIdUbicacion() + "', '', "
                    + "'0', '" + getArea().getIdArea() + "', '" + inventario.getObservaciones() + "');";

            System.out.println("sql: " + sql);

            st.execute(sql);

            inv++;

            if (inv > 0) {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inventario registrado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage(null, message);

                marca = new Marca_TO();
                modelo = new Modelo_TO();
                serie = new Serie_TO();
                servicio = new Servicio_TO();
                ubicacion = new Ubicacion_TO();
                area = new Area_TO();
                estadoInventario = new EstadoInventario_TO();
                inventario = new Inventario_TO();

            }

        } catch (Exception e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

    }

    public List<Inventario_TO> consultarInventarios() throws Exception {

        InventarioController();

        List<Inventario_TO> invs = new ArrayList<>();

        try {

            String sql = "SELECT  i.`id`,  i.`varEquipo`,  m.varNombreMarca,  mo.varNombreModelo, "
                    + " s.varNumSerie,  i.`varNumInvent`, e.varEstadoInventario,  se.varNomServicio,"
                    + "  u.varNomUbicacion,  LEFT(i.`longfoto`, 256),  i.`intEmpresa`,  a.varNomArea,"
                    + "  i.`varObservaciones` "
                    + " FROM `inventario` as i, marca as m, modelo as mo, series as s,"
                    + " servicios as se, ubicacion as u, estadoinventario as e, area as a "
                    + " where i.intIdMarca = m.intIdMarca\n"
                    + "                     and i.intIdModelo = mo.intIdModelo\n"
                    + "                     and i.intIdSerie = s.intIdSeries\n"
                    + "                     and i.intIdServicio = se.intIdServicios\n"
                    + "                     and i.intIdUbicacion = u.intIdUbicacion\n"
                    + "                     and i.intIdEstadoInv = e.intId\n"
                    + "                     and i.intArea = a.intIdArea;";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                invs.add(new Inventario_TO(rs.getInt(1), rs.getString(2), new Marca_TO(0, rs.getString(3)), new Serie_TO(0, 0, rs.getString(4)), new Modelo_TO(0, 0, rs.getString(5)), rs.getString(6), new EstadoInventario_TO(0, rs.getString(7)), new Servicio_TO(0, rs.getString(8)), new Ubicacion_TO(0, rs.getString(9)), rs.getString(10), new Empresa_TO(), new Area_TO(0, rs.getString(12)), rs.getString(13)));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }
        return invs;
    }

}
