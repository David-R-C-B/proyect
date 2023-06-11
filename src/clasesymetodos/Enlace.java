package clasesymetodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Enlace {

    public Enlace() {
    }

    private Integer id;
    private Integer identrenador;
    private String nombreentrenador;
    private String apellidoentrenador;
    private Integer idnutriologo;
    private String nombrenutriologo;
    private String apellidonutriologo;
    private Integer idatleta;
    private String nombreatleta;
    private String apellidoatleta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(Integer identrenador) {
        this.identrenador = identrenador;
    }

    public Integer getIdnutriologo() {
        return idnutriologo;
    }

    public void setIdnutriologo(Integer idnutriologo) {
        this.idnutriologo = idnutriologo;
    }

    public Integer getIdatleta() {
        return idatleta;
    }

    public void setIdatleta(Integer idatleta) {
        this.idatleta = idatleta;
    }

    public String getNombreentrenador() {
        return nombreentrenador;
    }

    public void setNombreentrenador(String nombreentrenador) {
        this.nombreentrenador = nombreentrenador;
    }

    public String getApellidoentrenador() {
        return apellidoentrenador;
    }

    public void setApellidoentrenador(String apellidoentrenador) {
        this.apellidoentrenador = apellidoentrenador;
    }

    public String getNombrenutriologo() {
        return nombrenutriologo;
    }

    public void setNombrenutriologo(String nombrenutriologo) {
        this.nombrenutriologo = nombrenutriologo;
    }

    public String getApellidonutriologo() {
        return apellidonutriologo;
    }

    public void setApellidonutriologo(String apellidonutriologo) {
        this.apellidonutriologo = apellidonutriologo;
    }

    public String getNombreatleta() {
        return nombreatleta;
    }

    public void setNombreatleta(String nombreatleta) {
        this.nombreatleta = nombreatleta;
    }

    public String getApellidoatleta() {
        return apellidoatleta;
    }

    public void setApellidoatleta(String apellidoatleta) {
        this.apellidoatleta = apellidoatleta;
    }

    public void agregarunion(TextField idatleta, TextField identrenador, TextField idnutriologo) {
        // Obtener los valores de los TextFields
        String idAtleta = idatleta.getText();
        String idEntrenador = identrenador.getText();
        String idNutriologo = idnutriologo.getText();

        // Crear la consulta SQL para insertar los datos en la tabla enlace
        String sql = "INSERT INTO enlace (id_atleta, id_entrenador, id_nutriologo) VALUES (?, ?, ?)";

        // Obtener la conexión a la base de datos utilizando la clase Conexion
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.EstablecerConeccion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros de la consulta
            pstmt.setInt(1, Integer.parseInt(idAtleta));
            pstmt.setInt(2, Integer.parseInt(idEntrenador));
            pstmt.setInt(3, Integer.parseInt(idNutriologo));

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void editarunion(TextField id, TextField idatleta, TextField identrenador, TextField idnutriologo) {
        // Obtener los valores de los TextFields
        String idEnlace = id.getText();
        String idAtleta = idatleta.getText();
        String idEntrenador = identrenador.getText();
        String idNutriologo = idnutriologo.getText();

        // Crear la consulta SQL para actualizar los datos en la tabla enlace
        String sql = "UPDATE enlace SET id_atleta = ?, id_entrenador = ?, id_nutriologo = ? WHERE id = ?";

        // Obtener la conexión a la base de datos utilizando la clase Conexion
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.EstablecerConeccion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros de la consulta
            pstmt.setInt(1, Integer.parseInt(idAtleta));
            pstmt.setInt(2, Integer.parseInt(idEntrenador));
            pstmt.setInt(3, Integer.parseInt(idNutriologo));
            pstmt.setInt(4, Integer.parseInt(idEnlace));

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void eliminarunion(TextField id) {
        // Obtener los valores de los TextFields
        String idEnlace = id.getText();

        // Crear la consulta SQL para actualizar los datos en la tabla enlace
        String sql = "DELETE FROM enlace\n"
                + "WHERE id=?;";

        // Obtener la conexión a la base de datos utilizando la clase Conexion
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.EstablecerConeccion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros de la consulta
            pstmt.setInt(1, Integer.parseInt(idEnlace));

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void mostrarDatos(TableView<Enlace> tabla) {
        //esto limpia las columnas para que se puedan ingresar nuevas 
        tabla.getColumns().clear();

        // Crear las columnas de la tabla
        TableColumn<Enlace, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Enlace, Integer> columnaIdAtleta = new TableColumn<>("ID Atleta");
        columnaIdAtleta.setCellValueFactory(new PropertyValueFactory<>("idatleta"));

        TableColumn<Enlace, String> columnaNombreAtleta = new TableColumn<>("Nombre Atleta");
        columnaNombreAtleta.setCellValueFactory(new PropertyValueFactory<>("nombreatleta"));

        TableColumn<Enlace, String> columnaApellidoAtleta = new TableColumn<>("Apellido Atleta");
        columnaApellidoAtleta.setCellValueFactory(new PropertyValueFactory<>("apellidoatleta"));

        TableColumn<Enlace, Integer> columnaIdEntrenador = new TableColumn<>("ID Entrenador");
        columnaIdEntrenador.setCellValueFactory(new PropertyValueFactory<>("identrenador"));

        TableColumn<Enlace, String> columnaNombreEntrenador = new TableColumn<>("Nombre Entrenador");
        columnaNombreEntrenador.setCellValueFactory(new PropertyValueFactory<>("nombreentrenador"));

        TableColumn<Enlace, String> columnaApellidoEntrenador = new TableColumn<>("Apellido Entrenador");
        columnaApellidoEntrenador.setCellValueFactory(new PropertyValueFactory<>("apellidoentrenador"));

        TableColumn<Enlace, Integer> columnaIdNutriologo = new TableColumn<>("ID Nutriologo");
        columnaIdNutriologo.setCellValueFactory(new PropertyValueFactory<>("idnutriologo"));

        TableColumn<Enlace, String> columnaNombreNutriologo = new TableColumn<>("Nombre Nutriologo");
        columnaNombreNutriologo.setCellValueFactory(new PropertyValueFactory<>("nombrenutriologo"));

        TableColumn<Enlace, String> columnaApellidoNutriologo = new TableColumn<>("Apellido Nutriologo");
        columnaApellidoNutriologo.setCellValueFactory(new PropertyValueFactory<>("apellidonutriologo"));

        // Agregar las columnas a la tabla
        tabla.getColumns().addAll(columnaId, columnaIdAtleta, columnaNombreAtleta, columnaApellidoAtleta,
                columnaIdEntrenador, columnaNombreEntrenador, columnaApellidoEntrenador,
                columnaIdNutriologo, columnaNombreNutriologo, columnaApellidoNutriologo);

        // Crear la lista de datos para la tabla
        ObservableList<Enlace> datos = FXCollections.observableArrayList();

        // Crear la consulta SQL para obtener los datos de la tabla enlace
        String sql = "SELECT enlace.id,"
                + "Atleta.id,"
                + "UsuarioAtleta.nombres,"
                + "UsuarioAtleta.apellidos,"
                + "Entrenador.id,"
                + "UsuarioEntrenador.nombres,"
                + "UsuarioEntrenador.apellidos,"
                + "Nutriologo.id,"
                + "UsuarioNutriologo.nombres,"
                + "UsuarioNutriologo.apellidos "
                + "FROM enlace "
                + "JOIN Atleta ON enlace.id_atleta = Atleta.id "
                + "JOIN Entrenador ON enlace.id_entrenador = Entrenador.id "
                + "JOIN Nutriologo ON enlace.id_nutriologo = Nutriologo.id "
                + "JOIN Usuario AS UsuarioAtleta ON Atleta.id = UsuarioAtleta.id "
                + "JOIN Usuario AS UsuarioEntrenador ON Entrenador.id = UsuarioEntrenador.id "
                + "JOIN Usuario AS UsuarioNutriologo ON Nutriologo.id = UsuarioNutriologo.id;";

        // Obtener la conexión a la base de datos utilizando la clase Conexion
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.EstablecerConeccion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            // Recorrer los resultados y agregar los objetos Enlace a la lista de datos
            while (rs.next()) {
                Enlace enlace = new Enlace();
                enlace.setId(rs.getInt(1));
                enlace.setIdatleta(rs.getInt(2));
                enlace.setNombreatleta(rs.getString(3));
                enlace.setApellidoatleta(rs.getString(4));
                enlace.setIdentrenador(rs.getInt(5));
                enlace.setNombreentrenador(rs.getString(6));
                enlace.setApellidoentrenador(rs.getString(7));
                enlace.setIdnutriologo(rs.getInt(8));
                enlace.setNombrenutriologo(rs.getString(9));
                enlace.setApellidonutriologo(rs.getString(10));
                datos.add(enlace);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
            System.out.println("aaaa");
        }

        // Establecer los datos en la tabla
        tabla.setItems(datos);
    }

    public void seleccionarFila(TableView<Enlace> tabla, TextField idenlace, TextField idenlaceatleta, TextField idenlaceentrenador,
            TextField idenlacenutriologo, TextField nombreatleta, TextField nombreentrenador, TextField nombrenutriologo) {
        // Obtener la fila seleccionada
        Enlace enlace = tabla.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó una fila
        if (enlace != null) {
            // Establecer los valores de los campos de texto con los datos de la fila seleccionada
            idenlace.setText(enlace.getId().toString());
            idenlaceatleta.setText(enlace.getIdatleta().toString());
            idenlaceentrenador.setText(enlace.getIdentrenador().toString());
            idenlacenutriologo.setText(enlace.getIdnutriologo().toString());

            // Concatenar el nombre y el apellido y establecer el resultado en el campo de texto correspondiente
            nombreatleta.setText(enlace.getNombreatleta() + " " + enlace.getApellidoatleta());
            nombreentrenador.setText(enlace.getNombreentrenador() + " " + enlace.getApellidoentrenador());
            nombrenutriologo.setText(enlace.getNombrenutriologo() + " " + enlace.getApellidonutriologo());
        }
    }
}
