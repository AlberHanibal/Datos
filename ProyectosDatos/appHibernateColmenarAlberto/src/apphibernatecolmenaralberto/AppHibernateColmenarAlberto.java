package apphibernatecolmenaralberto;

import java.util.List;
import javax.swing.table.DefaultTableModel;
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
    
    public void borrarVuelo(String codVuelo) {
        Session session = sesion.openSession();
        Transaction transaction = session.beginTransaction();
        Vuelos vuelo = (Vuelos) session.load(Vuelos.class, codVuelo);
        session.delete(vuelo);
        transaction.commit();
        session.close();
    }
    
    public DefaultTableModel consultarVuelos(String destino, String procedencia) {
        DefaultTableModel tabla = new DefaultTableModel();
        Session session = sesion.openSession();
        String consulta = "from Vuelos ";
        
        if (!destino.equals("") && (!procedencia.equals(""))) {
            consulta += "where destino = '" + destino + "'and procedencia = '" + procedencia + "'";
        } else if (!destino.equals("")) {
            consulta += "where destino = '" + destino + "'";
        } else if (!procedencia.equals("")) {
            consulta += "where procedencia = '" + procedencia + "'";
        }
        Query q = session.createQuery(consulta);
        List <Vuelos> listaVuelo = q.list();
        
        tabla.addColumn("Código");
        tabla.addColumn("HoraSalida");
        tabla.addColumn("Destino");
        tabla.addColumn("Procedencia");
        tabla.addColumn("PlazasFumador");
        tabla.addColumn("PlazasNoFumador");
        tabla.addColumn("PlazasTurista");
        tabla.addColumn("PlazasPrimera");
        Object fila[] = new Object[8];
        
        for (Vuelos vuelo : listaVuelo) {
            fila[0] = vuelo.getCodVuelo();
            fila[1] = vuelo.getHoraSalida();
            fila[2] = vuelo.getDestino();
            fila[3] = vuelo.getProcedencia();
            fila[4] = vuelo.getPlazasFumador();
            fila[5] = vuelo.getPlazasNoFumador();
            fila[6] = vuelo.getPlazasTurista();
            fila[7] = vuelo.getPlazasPrimera();
            tabla.addRow(fila);
        }
        return tabla;
    }
}
