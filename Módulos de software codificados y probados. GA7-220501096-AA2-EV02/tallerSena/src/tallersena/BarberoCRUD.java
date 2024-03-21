package tallersena;

import java.sql.*;

public class BarberoCRUD {
    // Cambiar estos valores según la configuración de tu base de datos
    static final String URL = "jdbc:mysql://localhost:3306/pruebaSena";
    static final String USER = "root";
    static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insertar un nuevo barbero
            insertarBarbero(conn, "Alberto", "Cejas");

            // Actualizar el nombre de un barbero
            //actualizarBarbero(conn, 1, "Daniel");

            // Eliminar un barbero
            //eliminarBarbero(conn, 3);
            
            // Leer todos los barberos
            System.out.println("Listado de barberos:");
            listarBarberos(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarBarbero(Connection conn, String nombre, String especialidad) throws SQLException {
        String sql = "INSERT INTO barbero (nombre, especialidad) VALUES (?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nombre);
            st.setString(2, especialidad);
            st.executeUpdate();
            System.out.println("Barbero insertado correctamente.");
        }
    }

    public static void listarBarberos(Connection conn) throws SQLException {
        String sql = "SELECT id, nombre, especialidad FROM barbero";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                        rs.getString("nombre") + " - " +
                        rs.getString("especialidad"));
            }
        }
    }

    public static void actualizarBarbero(Connection conn, int id, String nuevoNombre) throws SQLException {
        String sql = "UPDATE barbero SET nombre = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nuevoNombre);
            st.setInt(2, id);
            int filasActualizadas = st.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Barbero actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún barbero con el ID especificado.");
            }
        }
    }

    public static void eliminarBarbero(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM barbero WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int filasEliminadas = st.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Barbero eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún barbero con el ID especificado.");
            }
        }
    }
}
