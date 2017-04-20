/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.conexion.ConexionSQL;
import com.app.models.HojaDeVida_TO;
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
public class HojaDeVidaController {

    Statement st;
    private HojaDeVida_TO hojaDeVida;
    private List<HojaDeVida_TO> hojasDeVida;

    public void InventarioController() throws SQLException {
        this.st = ConexionSQL.conexion();
    }

    public HojaDeVidaController() throws SQLException {
        hojaDeVida = new HojaDeVida_TO();
        hojasDeVida = new ArrayList<>();
    }

    @PostConstruct
    public void init() 
    {

        try {
            hojasDeVida = this.consultarHojasDeVida();
        } catch (Exception ex) {
            Logger.getLogger(HojaDeVidaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Getter and Setters
    public HojaDeVida_TO getHojaDeVida() {
        return hojaDeVida;
    }

    public void setHojaDeVida(HojaDeVida_TO hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }

    public List<HojaDeVida_TO> getHojasDeVida() {
        return hojasDeVida;
    }

    public void setHojasDeVida(List<HojaDeVida_TO> hojasDeVida) {
        this.hojasDeVida = hojasDeVida;
    }

    public int registrarHojaDeVida(HojaDeVida_TO hojaDeVida) throws Exception {

        InventarioController();
        int hoja = 0;

        try {

            String sql = "INSERT INTO `biomedic`.`hojadevida` "
                    + " (`direccion`, `telefono`, `email`, `codigoEquipo`, `rs`,"
                    + " `codigoPrestado`, `sede`, `distintivo`, `serie`, `invActivo`, "
                    + " `equipo`, `marca`, `modelo`, `tipo`, `servicio`, `ubicacion`,"
                    + "  `tipoEquipo`, `formaAdquisicion`, `documentoAdquisicion`, "
                    + " `fechaCompra`, `actaRecibido`, `instalacion`, `inicioOperacion`,"
                    + "  `vencGarantia`, `fabricacion`, `costo`, `vidaUtil`, `proveedor`, "
                    + " `telProveedor`, `correoProveedor`, `representante`, `telRepresentante`,"
                    + "  `correoRepresentante`, `fabricante`, `telFabricante`, `pasFabricante`, "
                    + " `fuenteAlimentacion`, `tecPredominante`, `voltajeMax`, `voltajeMin`, "
                    + " `corrienteMax`, `corrienteMin`, `potencia`, `frencuencia`, `presion`, "
                    + " `velocidad`, `peso`, `temperatura`, `otros`, `rangoVoltaje`, `rangoCorriente`, "
                    + " `rangoPotencia`, `frecuencia2`, `rangoPresion`, `rangoVelocidad`, `rangoTemperatura`, "
                    + " `peso2`, `rangoHumedad`, `otrasRecomendaciones`, `manuales`, `planos`, "
                    + " `clasificacionBiomedica`, `clasificacionRiesgo`, `periodicidadMantenimiento`, "
                    + " `requiereCalibracion`, `periodicidadCalibracion`, `registroSanitario`, "
                    + " `permisoComercializacion`, `registroImportacion`, `factura`, `ingresoAlmacen`, "
                    + " `actaReciboSatisfacionPrestador`, `protocoloMantenimientoPreventivo`, "
                    + " `cronogramaMantenimientoGarantia`, `guiaRapidaOperacion`, `actaReciboSatisfacionOperador`, "
                    + " `recomendacionesFrabricanteUsoAccesoriosConsumibles`, `recomendacionesFabricanteCalibracion`,"
                    + "  `estimativoCostoAccesoriosConsumibles`)"
                    + " VALUES ('" + hojaDeVida.getDireccion() + "', '" + hojaDeVida.getTelefono() + "', '" + hojaDeVida.getEmail() + "', '" + hojaDeVida.getCodigoEquipo() + "', "
                    + " '" + hojaDeVida.getRs() + "', '" + hojaDeVida.getCodigoPrestado() + "', '" + hojaDeVida.getSede() + "',"
                    + "  '" + hojaDeVida.getDistintivo() + "', '" + hojaDeVida.getSerie() + "', '" + hojaDeVida.getInvActivo() + "',"
                    + "  '" + hojaDeVida.getEquipo() + "', '" + hojaDeVida.getMarca() + "', '" + hojaDeVida.getModelo() + "', "
                    + " '" + hojaDeVida.getTipo() + "', '" + hojaDeVida.getServicio() + "', '" + hojaDeVida.getUbicacion() + "', "
                    + " '" + hojaDeVida.getTipoEquipo() + "', '" + hojaDeVida.getFormaAdquisicion() + "', '" + hojaDeVida.getDocumentoAdquisicion() + "', "
                    + " '" + hojaDeVida.getFechaCompra() + "', '" + hojaDeVida.getActaRecibido() + "', '" + hojaDeVida.getInstalacion() + "',"
                    + "  '" + hojaDeVida.getInicioOperacion() + "', '" + hojaDeVida.getVencGarantia() + "', '" + hojaDeVida.getFabricacion() + "', "
                    + " '" + hojaDeVida.getCosto() + "', '" + hojaDeVida.getVidaUtil() + "', '" + hojaDeVida.getProveedor() + "', "
                    + " '" + hojaDeVida.getTelProveedor() + "', '" + hojaDeVida.getCorreoProveedor() + "', '" + hojaDeVida.getRepresentante() + "', "
                    + " '" + hojaDeVida.getTelRepresentante() + "', '" + hojaDeVida.getCorreoRepresentante() + "', '" + hojaDeVida.getFabricante() + "', "
                    + " '" + hojaDeVida.getTelFabricante() + "', '" + hojaDeVida.getPasFabricante() + "', "
                    + " '" + hojaDeVida.getFuenteAlimentacion() + "', '" + hojaDeVida.getTecPredominante() + "', '" + hojaDeVida.getVoltajeMax() + "', "
                    + " '" + hojaDeVida.getVoltajeMin() + "', '" + hojaDeVida.getCorrienteMax() + "', '" + hojaDeVida.getCorrienteMin() + "', "
                    + " '" + hojaDeVida.getPotencia() + "', '" + hojaDeVida.getFrencuencia() + "', '" + hojaDeVida.getPresion() + "', "
                    + " '" + hojaDeVida.getVelocidad() + "', '" + hojaDeVida.getPeso() + "', '" + hojaDeVida.getTemperatura() + "', "
                    + " '" + hojaDeVida.getOtros() + "', '" + hojaDeVida.getRangoVoltaje() + "', '" + hojaDeVida.getRangoCorriente() + "',"
                    + "  '" + hojaDeVida.getRangoPotencia() + "', '" + hojaDeVida.getFrecuencia2() + "', '" + hojaDeVida.getRangoPresion() + "',"
                    + "  '" + hojaDeVida.getRangoVelocidad() + "', '" + hojaDeVida.getRangoTemperatura() + "', '" + hojaDeVida.getPeso2() + "', "
                    + " '" + hojaDeVida.getRangoHumedad() + "', '" + hojaDeVida.getOtrasRecomendaciones() + "', '" + hojaDeVida.getManuales() + "', "
                    + " '" + hojaDeVida.getPlanos() + "', '" + hojaDeVida.getClasificacionBiomedica() + "', '" + hojaDeVida.getClasificacionRiesgo() + "',"
                    + "  '" + hojaDeVida.getPeriodicidadMantenimiento() + "', '" + hojaDeVida.getRequiereCalibracion() + "', '" + hojaDeVida.getPeriodicidadCalibracion() + "', "
                    + " '" + hojaDeVida.getRegistroSanitario() + "', '" + hojaDeVida.getPermisoComercializacion() + "', '" + hojaDeVida.getRegistroImportacion() + "', "
                    + " '" + hojaDeVida.getFactura() + "', '" + hojaDeVida.getIngresoAlmacen() + "', '" + hojaDeVida.getActaReciboSatisfacionPrestador() + "',"
                    + " '" + hojaDeVida.getProtocoloMantenimientoPreventivo() + "', '" + hojaDeVida.getCronogramaMantenimientoGarantia() + "', '" + hojaDeVida.getGuiaRapidaOperacion() + "',"
                    + " '" + hojaDeVida.getActaReciboSatisfacionOperador() + "', "
                    + "  '" + hojaDeVida.getRecomendacionesFrabricanteUsoAccesoriosConsumibles() + "', '" + hojaDeVida.getRecomendacionesFabricanteCalibracion() + "', '" + hojaDeVida.getEstimativoCostoAccesoriosConsumibles() + "');";

            System.out.println("sql: " + sql);

            st.execute(sql);

            hoja++;

        } catch (Exception e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }

        return hoja;

    }

    public List<HojaDeVida_TO> consultarHojasDeVida() throws Exception {

        InventarioController();

        List<HojaDeVida_TO> invs = new ArrayList<>();

        try {

            String sql = " Select idHoraDeVida,direccion,telefono,email,codigoEquipo,rs,codigoPrestado,sede,distintivo,serie,invActivo,\n"
                    + " equipo,marca,modelo,tipo,servicio,ubicacion,tipoEquipo,formaAdquisicion,\n"
                    + " documentoAdquisicion,fechaCompra,actaRecibido,instalacion,inicioOperacion,vencGarantia,fabricacion,costo,vidaUtil,\n"
                    + " proveedor,telProveedor,correoProveedor,representante,telRepresentante,correoRepresentante,fabricante,telFabricante,\n"
                    + " pasFabricante,fuenteAlimentacion,tecPredominante,voltajeMax,voltajeMin,corrienteMax,corrienteMin,potencia,frencuencia,presion,\n"
                    + " velocidad,peso,temperatura,otros,rangoVoltaje,rangoCorriente,rangoPotencia,frecuencia2,rangoPresion,\n"
                    + " rangoVelocidad,otrasRecomendaciones,rangoTemperatura,peso2,rangoHumedad,manuales,planos,clasificacionBiomedica,clasificacionRiesgo,periodicidadMantenimiento,\n"
                    + " requiereCalibracion,periodicidadCalibracion,registroSanitario,permisoComercializacion,registroImportacion,factura,ingresoAlmacen,actaReciboSatisfacionPrestador,protocoloMantenimientoPreventivo,\n"
                    + " cronogramaMantenimientoGarantia,guiaRapidaOperacion,actaReciboSatisfacionOperador,recomendacionesFrabricanteUsoAccesoriosConsumibles,recomendacionesFabricanteCalibracion,estimativoCostoAccesoriosConsumibles\n"
                    + " from hojadevida;";

            ResultSet rs = null;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                invs.add(new HojaDeVida_TO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34), rs.getString(35), rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), rs.getString(40), rs.getString(41), rs.getString(42), rs.getString(43), rs.getString(44), rs.getString(45), rs.getString(46), rs.getString(47), rs.getString(48), rs.getString(49), rs.getString(50), rs.getString(51), rs.getString(52), rs.getString(53), rs.getString(54), rs.getString(55), rs.getString(56), rs.getString(57), rs.getString(58), rs.getString(59), rs.getString(60), rs.getString(61), rs.getString(62), rs.getString(63), rs.getString(64), rs.getString(65), rs.getString(66), rs.getString(67), rs.getString(68), rs.getString(69), rs.getString(70), rs.getString(71), rs.getString(72), rs.getString(73), rs.getString(74), rs.getString(75), rs.getString(76), rs.getString(77), rs.getString(78), rs.getString(79)));

            }

        } catch (SQLException e) {

            throw e;

        } finally {
            ConexionSQL.CerrarConexion();
        }
        return invs;
    }

}
