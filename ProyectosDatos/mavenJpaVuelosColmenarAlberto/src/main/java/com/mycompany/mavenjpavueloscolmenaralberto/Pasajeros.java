/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpavueloscolmenaralberto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DAM2A-03
 */
@Entity
@Table(name = "pasajeros")
@NamedQueries({
    @NamedQuery(name = "Pasajeros.findAll", query = "SELECT p FROM Pasajeros p"),
    @NamedQuery(name = "Pasajeros.findByNum", query = "SELECT p FROM Pasajeros p WHERE p.pasajerosPK.num = :num"),
    @NamedQuery(name = "Pasajeros.findByCodVuelo", query = "SELECT p FROM Pasajeros p WHERE p.pasajerosPK.codVuelo = :codVuelo"),
    @NamedQuery(name = "Pasajeros.findByTipoPlaza", query = "SELECT p FROM Pasajeros p WHERE p.tipoPlaza = :tipoPlaza"),
    @NamedQuery(name = "Pasajeros.findByFumador", query = "SELECT p FROM Pasajeros p WHERE p.fumador = :fumador")})
public class Pasajeros implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PasajerosPK pasajerosPK;
    @Column(name = "TIPO_PLAZA")
    private String tipoPlaza;
    @Column(name = "FUMADOR")
    private String fumador;
    @JoinColumn(name = "COD_VUELO", referencedColumnName = "COD_VUELO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vuelos vuelos;

    public Pasajeros() {
    }

    public Pasajeros(PasajerosPK pasajerosPK, String tipoPlaza, String fumador, Vuelos vuelos) {
        this.pasajerosPK = pasajerosPK;
        this.tipoPlaza = tipoPlaza;
        this.fumador = fumador;
        this.vuelos = vuelos;
    }

    public Pasajeros(PasajerosPK pasajerosPK) {
        this.pasajerosPK = pasajerosPK;
    }

    public Pasajeros(int num, String codVuelo) {
        this.pasajerosPK = new PasajerosPK(num, codVuelo);
    }

    public PasajerosPK getPasajerosPK() {
        return pasajerosPK;
    }

    public void setPasajerosPK(PasajerosPK pasajerosPK) {
        this.pasajerosPK = pasajerosPK;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public void setTipoPlaza(String tipoPlaza) {
        this.tipoPlaza = tipoPlaza;
    }

    public String getFumador() {
        return fumador;
    }

    public void setFumador(String fumador) {
        this.fumador = fumador;
    }

    public Vuelos getVuelos() {
        return vuelos;
    }

    public void setVuelos(Vuelos vuelos) {
        this.vuelos = vuelos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasajerosPK != null ? pasajerosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasajeros)) {
            return false;
        }
        Pasajeros other = (Pasajeros) object;
        if ((this.pasajerosPK == null && other.pasajerosPK != null) || (this.pasajerosPK != null && !this.pasajerosPK.equals(other.pasajerosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenjpavueloscolmenaralberto.Pasajeros[ pasajerosPK=" + pasajerosPK + " ]";
    }
    
}
