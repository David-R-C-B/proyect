package clasesymetodos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.Conexion;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Usuario {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String contraseña;
    private String tarjetaDeAcceso;
    private String ocupacion;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTarjetaDeAcceso() {
        return tarjetaDeAcceso;
    }

    public void setTarjetaDeAcceso(String tarjetaDeAcceso) {
        this.tarjetaDeAcceso = tarjetaDeAcceso;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public void mostrarDatos(TableView<Usuario> tabla) {
        //esto limpia las columnas para que se puedan ingresar nuevas 
        tabla.getColumns().clear();

        // Crear las columnas de la tabla
        TableColumn<Usuario, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> columnaNombres = new TableColumn<>("Nombres");
        columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));

        TableColumn<Usuario, String> columnaApellidos = new TableColumn<>("Apellidos");
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Usuario, String> columnaOcupacion = new TableColumn<>("Ocupacion");
        columnaOcupacion.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));

        // Agregar las columnas a la tabla
        tabla.getColumns().addAll(columnaId, columnaNombres, columnaApellidos, columnaOcupacion);

        // Crear la lista de datos para la tabla
        ObservableList<Usuario> datos = FXCollections.observableArrayList();

        // Crear la consulta SQL para obtener los datos de la tabla enlace
        String sql = "SELECT Usuario.id,\n"
                + "       Usuario.nombres,\n"
                + "       Usuario.apellidos,\n"
                + "       Usuario.ocupacion\n"
                + "FROM Usuario\n"
                + "JOIN Atleta ON Usuario.id = Atleta.id\n"
                + "UNION\n"
                + "SELECT Usuario.id,\n"
                + "       Usuario.nombres,\n"
                + "       Usuario.apellidos,\n"
                + "       Usuario.ocupacion\n"
                + "FROM Usuario\n"
                + "JOIN Entrenador ON Usuario.id = Entrenador.id\n"
                + "UNION\n"
                + "SELECT Usuario.id,\n"
                + "       Usuario.nombres,\n"
                + "       Usuario.apellidos,\n"
                + "       Usuario.ocupacion\n"
                + "FROM Usuario\n"
                + "JOIN Nutriologo ON Usuario.id = Nutriologo.id\n"
                + "ORDER BY ocupacion;";

        // Obtener la conexión a la base de datos utilizando la clase Conexion
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.EstablecerConeccion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            // Recorrer los resultados y agregar los objetos Enlace a la lista de datos
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setOcupacion(rs.getString(4));
                datos.add(usuario);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }

        // Establecer los datos en la tabla
        tabla.setItems(datos);
    }

    public void seleccionarFila(TableView<Usuario> atnuen,  TextField idenlaceatleta, TextField idenlaceentrenador,
            TextField idenlacenutriologo, TextField nombreatleta, TextField nombreentrenador, TextField nombrenutriologo) {
        // Obtener la fila seleccionada
        Usuario usuario = atnuen.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó una fila
        if (usuario != null) {
            

            // Verificar la ocupación del usuario y establecer los valores de los campos de texto correspondientes
            if (usuario.getOcupacion().equals("Atleta")) {
                idenlaceatleta.setText(usuario.getId().toString());
                nombreatleta.setText(usuario.getNombres() + " " + usuario.getApellidos());
            } else if (usuario.getOcupacion().equals("Entrenador")) {
                idenlaceentrenador.setText(usuario.getId().toString());
                nombreentrenador.setText(usuario.getNombres() + " " + usuario.getApellidos());
            } else if (usuario.getOcupacion().equals("Nutriólogo")) {
                idenlacenutriologo.setText(usuario.getId().toString());
                nombrenutriologo.setText(usuario.getNombres() + " " + usuario.getApellidos());
            }
        }
    }

}

