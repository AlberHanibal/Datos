/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpavueloscolmenaralberto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DAM2A-03
 */
@Entity
@Table(name = "vuelos")
@NamedQueries({
    @NamedQuery(name = "Vuelos.findAll", query = "SELECT v FROM Vuelos v"),
    @NamedQuery(name = "Vuelos.findByCodVuelo", query = "SELECT v FROM Vuelos v WHERE v.codVuelo = :codVuelo"),
    @NamedQuery(name = "Vuelos.findByHoraSalida", query = "SELECT v FROM Vuelos v WHERE v.horaSalida = :horaSalida"),
    @NamedQuery(name = "Vuelos.findByDestino", query = "SELECT v FROM Vuelos v WHERE v.destino = :destino"),
    @NamedQuery(name = "Vuelos.findByProcedencia", query = "SELECT v FROM Vuelos v WHERE v.procedencia = :procedencia"),
    @NamedQuery(name = "Vuelos.findByPlazasFumador", query = "SELECT v FROM Vuelos v WHERE v.plazasFumador = :plazasFumador"),
    @NamedQuery(name = "Vuelos.findByPlazasNoFumador", query = "SELECT v FROM Vuelos v WHERE v.plazasNoFumador = :plazasNoFumador"),
    @NamedQuery(name = "Vuelos.findByPlazasTurista", query = "SELECT v FROM Vuelos v WHERE v.plazasTurista = :plazasTurista"),
    @NamedQuery(name = "Vuelos.findByPlazasPrimera", query = "SELECT v FROM Vuelos v WHERE v.plazasPrimera = :plazasPrimera")})
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_VUELO")
    private String codVuelo;
    @Column(name = "HORA_SALIDA")
    private String horaSalida;
    @Column(name = "DESTINO")
    private String destino;
    @Column(name = "PROCEDENCIA")
    private String procedencia;
    @Column(name = "PLAZAS_FUMADOR")
    private Integer plazasFumador;
    @Column(name = "PLAZAS_NO_FUMADOR")
    private Integer plazasNoFumador;
    @Column(name = "PLAZAS_TURISTA")
    private Integer plazasTurista;
    @Column(name = "PLAZAS_PRIMERA")
    private Integer plazasPrimera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vuelos")
    private Collection<Pasajeros> pasajerosCollection;

    public Vuelos() {
    }

    public Vuelos(String codVuelo, String horaSalida, String destino, String procedencia, Integer plazasFumador, Integer plazasNoFumador, Integer plazasTurista, Integer plazasPrimera, Collection<Pasajeros> pasajerosCollection) {
        this.codVuelo = codVuelo;
        this.horaSalida = horaSalida;
        this.destino = destino;
        this.procedencia = procedencia;
        this.plazasFumador = plazasFumador;
        this.plazasNoFumador = plazasNoFumador;
        this.plazasTurista = plazasTurista;
        this.plazasPrimera = plazasPrimera;
        this.pasajerosCollection = pasajerosCollection;
    }
    
    

    public Vuelos(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    public String getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public Integer getPlazasFumador() {
        return plazasFumador;
    }

    public void setPlazasFumador(Integer plazasFumador) {
        this.plazasFumador = plazasFumador;
    }

    public Integer getPlazasNoFumador() {
        return plazasNoFumador;
    }

    public void setPlazasNoFumador(Integer plazasNoFumador) {
        this.plazasNoFumador = plazasNoFumador;
    }

    public Integer getPlazasTurista() {
        return plazasTurista;
    }

    public void setPlazasTurista(Integer plazasTurista) {
        this.plazasTurista = plazasTurista;
    }

    public Integer getPlazasPrimera() {
        return plazasPrimera;
    }

    public void setPlazasPrimera(Integer plazasPrimera) {
        this.plazasPrimera = plazasPrimera;
    }

    public Collection<Pasajeros> getPasajerosCollection() {
        return pasajerosCollection;
    }

    public void setPasajerosCollection(Collection<Pasajeros> pasajerosCollection) {
        this.pasajerosCollection = pasajerosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVuelo != null ? codVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.codVuelo == null && other.codVuelo != null) || (this.codVuelo != null && !this.codVuelo.equals(other.codVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Código: %s%n"
                            + "Hora de salida: %s%n"
                            + "Destino: %s%n"
                            + "Procedencia: %s%n", codVuelo, horaSalida, destino, procedencia);
    }
    
}
