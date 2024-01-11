/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpavueloscolmenaralberto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DAM2A-03
 */
@Embeddable
public class PasajerosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUM")
    private int num;
    @Basic(optional = false)
    @Column(name = "COD_VUELO")
    private String codVuelo;

    public PasajerosPK() {
    }

    public PasajerosPK(int num, String codVuelo) {
        this.num = num;
        this.codVuelo = codVuelo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(String codVuelo) {
        this.codVuelo = codVuelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) num;
        hash += (codVuelo != null ? codVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasajerosPK)) {
            return false;
        }
        PasajerosPK other = (PasajerosPK) object;
        if (this.num != other.num) {
            return false;
        }
        if ((this.codVuelo == null && other.codVuelo != null) || (this.codVuelo != null && !this.codVuelo.equals(other.codVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenjpavueloscolmenaralberto.PasajerosPK[ num=" + num + ", codVuelo=" + codVuelo + " ]";
    }
    
}
