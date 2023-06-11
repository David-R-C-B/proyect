package clasesymetodos;

import java.sql.Date;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import proyect.Conexion;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Rutina {

    public Rutina() {
    }

    public Rutina(Integer id, Integer id_entrenador, Integer id_atleta, String tipo, Date fecha_asignacion, String comentario) {
        this.id = id;
        this.id_entrenador = id_entrenador;
        this.id_atleta = id_atleta;
        this.tipo = tipo;
        this.fecha_asignacion = fecha_asignacion;
        this.comentario = comentario;
    }

    private Integer id;
    private Integer id_entrenador;
    private Integer id_atleta;
    private String tipo;
    private Date fecha_asignacion;
    private String comentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_entrenador() {
        return id_entrenador;
    }

    public void setId_entrenador(Integer id_entrenador) {
        this.id_entrenador = id_entrenador;
    }

    public Integer getId_atleta() {
        return id_atleta;
    }

    public void setId_atleta(Integer id_atleta) {
        this.id_atleta = id_atleta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_asignacion() {
        return fecha_asignacion;
    }

    public void setFecha_asignacion(Date fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void insertarRutina(TextField idAtleta, TextField idEntrenador, TextField tipo, DatePicker fecha, TextArea comentario) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para insertar los datos en la tabla rutina
        String sql = "INSERT INTO rutina(id_entrenador, id_atleta, tipo, fecha_asignacion, comentario) VALUES (?, ?, ?, ?, ?)";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setInt(1, Integer.parseInt(idEntrenador.getText()));
            ps.setInt(2, Integer.parseInt(idAtleta.getText()));
            ps.setString(3, tipo.getText());
            ps.setDate(4, Date.valueOf(fecha.getValue()));
            ps.setString(5, comentario.getText());

            // Ejecuta la consulta
            ps.executeUpdate();

            // Muestra un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje");
            alert.setHeaderText(null);
            alert.setContentText("Rutina agregada con éxito");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    
}
