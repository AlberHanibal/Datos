package com.mycompany.mavenjpavueloscolmenaralberto;

import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.IllegalOrphanException;
import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM2A-03
 */
public class MavenJpaVuelosColmenarAlberto {
    
    public static void main(String[] args) {
        
        VuelosJpaController control = new VuelosJpaController();
        PasajerosJpaController controlPasajeros = new PasajerosJpaController();
        /*
        System.out.println("Total de vuelos: " + control.getVuelosCount());
        
        Vuelos v = control.findVuelos("AV-DC-347");
        System.out.println(v.toString());
        
        List<Vuelos> lista = new ArrayList();
        lista = control.findVuelosEntities();
        for (Vuelos vuelo : lista) {
            System.out.println(vuelo.toString());
        }
        
        Vuelos vuelo = new Vuelos("HGQ-QWE", "19;20", "MONGOLIA", "CHINA", 200, 100, 200, 150, new ArrayList<Pasajeros>());
        try {
            control.create(vuelo);
        } catch (Exception ex) {
            Logger.getLogger(MavenJpaVuelosColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Vuelos v1 = control.findVuelos("HGQ-QWE");
        Pasajeros p1 = new Pasajeros(new PasajerosPK(1, "HGQ-QWE"), "TU", "NO", v1);
        try {
            controlPasajeros.create(p1);
        } catch (Exception ex) {
            Logger.getLogger(MavenJpaVuelosColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Vuelos vueloABorrar = control.findVuelos("HGQ-QWE");
        if (!vueloABorrar.getPasajerosCollection().isEmpty()) {
            for (Pasajeros pasajero : vueloABorrar.getPasajerosCollection()) {
                try {
                    controlPasajeros.destroy(pasajero.getPasajerosPK());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(MavenJpaVuelosColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                control.destroy("HGQ-QWE");
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(MavenJpaVuelosColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(MavenJpaVuelosColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        Vuelos vueloAModificar = control.findVuelos("AV-DC-347");
        control.modificarVuelo(vueloAModificar, "procedencia", "TARRAGONA");
        
    }
}
