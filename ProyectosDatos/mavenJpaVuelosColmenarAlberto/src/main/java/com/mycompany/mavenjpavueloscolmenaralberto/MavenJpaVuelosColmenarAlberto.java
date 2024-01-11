package com.mycompany.mavenjpavueloscolmenaralberto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM2A-03
 */
public class MavenJpaVuelosColmenarAlberto {
    
    public static void main(String[] args) {
        VuelosJpaController control = new VuelosJpaController();
        System.out.println("Total de vuelos: " + control.getVuelosCount());
        
        Vuelos v = control.findVuelos("AV-DC-347");
        System.out.println(v.toString());
        
        List<Vuelos> lista = new ArrayList();
        lista = control.findVuelosEntities();
        for (Vuelos vuelo : lista) {
            System.out.println(vuelo.toString());
        }
    }
}
