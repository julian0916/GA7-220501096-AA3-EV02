import javax.persistence.*;
import java.util.List;

public class BarberoCRUD {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("BarberoPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Insertar un nuevo barbero
        //insertarBarbero(entityManager, "Andres", "Cejas");

        // Leer todos los barberos
        System.out.println("Listado de barberos:");
        listarBarberos(entityManager);

        // Actualizar el nombre de un barbero
        //actualizarBarbero(entityManager, 6, "Carlos");

        // Eliminar un barbero
        //eliminarBarbero(entityManager, 6);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void insertarBarbero(EntityManager entityManager, String nombre, String especialidad) {
        entityManager.getTransaction().begin();
        Barbero barbero = new Barbero();
        barbero.setNombre(nombre);
        barbero.setEspecialidad(especialidad);
        entityManager.persist(barbero);
        entityManager.getTransaction().commit();
        System.out.println("Barbero insertado correctamente.");
    }

    public static void listarBarberos(EntityManager entityManager) {
        TypedQuery<Barbero> query = entityManager.createQuery("SELECT b FROM Barbero b", Barbero.class);
        List<Barbero> barberos = query.getResultList();
        for (Barbero barbero : barberos) {
            System.out.println(barbero.getId() + " - " + barbero.getNombre() + " - " + barbero.getEspecialidad());
        }
    }

    public static void actualizarBarbero(EntityManager entityManager, int id, String nuevoNombre) {
        entityManager.getTransaction().begin();
        Barbero barbero = entityManager.find(Barbero.class, id);
        if (barbero != null) {
            barbero.setNombre(nuevoNombre);
            entityManager.getTransaction().commit();
            System.out.println("Barbero actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún barbero con el ID especificado.");
        }
    }

    public static void eliminarBarbero(EntityManager entityManager, int id) {
        entityManager.getTransaction().begin();
        Barbero barbero = entityManager.find(Barbero.class, id);
        if (barbero != null) {
            entityManager.remove(barbero);
            entityManager.getTransaction().commit();
            System.out.println("Barbero eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún barbero con el ID especificado.");
        }
    }
}
