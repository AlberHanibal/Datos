package apphibernatecolmenaralberto;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author DAM2A-03
 */
public class AppHibernateColmenarAlberto {

    private SessionFactory sesion;
    
    public AppHibernateColmenarAlberto() {
        sesion = SessionFactoryUtil.getSessionFactory();
    }
    
    public void insertarVuelo(String codVuelo, String horaSalida, String destino, 
            String procedencia, Integer plazasFumador, Integer plazasNoFumador, 
            Integer plazasTurista, Integer plazasPrimera) {
        
        Session session = sesion.openSession();
        Transaction transaction = session.beginTransaction();
        Vuelos vuelo = new Vuelos(codVuelo, horaSalida, destino, procedencia, plazasFumador, 
                plazasNoFumador, plazasTurista, plazasPrimera);
        
        session.save(vuelo);
        transaction.commit();
        session.close();
    }
    
    public List obtenerListaVuelos() {
        Session session = sesion.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from Vuelos");
        List <Vuelos> listaVuelo = q.list();
        session.close();
        return listaVuelo;
    }
    
    public Vuelos obtenerVuelo(String codVuelo) {
        Session session = sesion.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from Vuelos where codVuelo = '" + codVuelo + "'");
        Vuelos vuelo = (Vuelos) q.uniqueResult();
        return vuelo;
    }
    
    public void modificarVuelo(String codVuelo, String horaSalida, String destino, 
            String procedencia, Integer plazasFumador, Integer plazasNoFumador, 
            Integer plazasTurista, Integer plazasPrimera) {
        
        Session session = sesion.openSession();
        Transaction transaction = session.beginTransaction();
        
        Vuelos vuelo = (Vuelos) session.load(Vuelos.class, codVuelo);
        vuelo.setDestino(destino);
        vuelo.setHoraSalida(horaSalida);
        vuelo.setProcedencia(procedencia);
        vuelo.setPlazasFumador(plazasFumador);
        vuelo.setPlazasNoFumador(plazasNoFumador);
        vuelo.setPlazasTurista(plazasTurista);
        vuelo.setPlazasPrimera(plazasPrimera);
        
        session.update(vuelo);       
        session.save(vuelo);
        transaction.commit();
        session.close();
    }
}
