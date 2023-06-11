package clasesymetodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.Conexion;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Seguimiento {

    public Seguimiento() {
    }

    public Seguimiento(Integer id, Integer idatleta, Integer idnutriologo, Integer identrenador, Integer idrutina, Integer iddieta) {
        this.id = id;
        this.idatleta = idatleta;
        this.idnutriologo = idnutriologo;
        this.identrenador = identrenador;
        this.idrutina = idrutina;
        this.iddieta = iddieta;
    }

    private Integer id;
    private Integer idatleta;
    private Integer idnutriologo;
    private Integer identrenador;
    private Integer idrutina;
    private Integer iddieta;
    private String nombres_atleta;
    private String apellidos_atleta;
    private String nombres_entrenador;
    private String apellidos_entrenador;
    private String tipo_rutina;
    private LocalDate fecha_asignacion_rutina;
    private String comentario_rutina;
    private String nombres_nutriologo;
    private String apellidos_nutriologo;
    private String tipo_dieta;
    private LocalDate fecha_asignacion_dieta;
    private String comentario_dieta;

    public String getNombres_atleta() {
        return nombres_atleta;
    }

    public void setNombres_atleta(String nombres_atleta) {
        this.nombres_atleta = nombres_atleta;
    }

    public String getApellidos_atleta() {
        return apellidos_atleta;
    }

    public void setApellidos_atleta(String apellidos_atleta) {
        this.apellidos_atleta = apellidos_atleta;
    }

    public String getNombres_entrenador() {
        return nombres_entrenador;
    }

    public void setNombres_entrenador(String nombres_entrenador) {
        this.nombres_entrenador = nombres_entrenador;
    }

    public String getApellidos_entrenador() {
        return apellidos_entrenador;
    }

    public void setApellidos_entrenador(String apellidos_entrenador) {
        this.apellidos_entrenador = apellidos_entrenador;
    }

    public String getTipo_rutina() {
        return tipo_rutina;
    }

    public void setTipo_rutina(String tipo_rutina) {
        this.tipo_rutina = tipo_rutina;
    }

    public LocalDate getFecha_asignacion_rutina() {
        return fecha_asignacion_rutina;
    }

    public void setFecha_asignacion_rutina(LocalDate fecha_asignacion_rutina) {
        this.fecha_asignacion_rutina = fecha_asignacion_rutina;
    }

    public String getComentario_rutina() {
        return comentario_rutina;
    }

    public void setComentario_rutina(String comentario_rutina) {
        this.comentario_rutina = comentario_rutina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdatleta() {
        return idatleta;
    }

    public void setIdatleta(Integer idatleta) {
        this.idatleta = idatleta;
    }

    public Integer getIdnutriologo() {
        return idnutriologo;
    }

    public void setIdnutriologo(Integer idnutriologo) {
        this.idnutriologo = idnutriologo;
    }

    public Integer getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(Integer identrenador) {
        this.identrenador = identrenador;
    }

    public Integer getIdrutina() {
        return idrutina;
    }

    public void setIdrutina(Integer idrutina) {
        this.idrutina = idrutina;
    }

    public Integer getIddieta() {
        return iddieta;
    }

    public void setIddieta(Integer iddieta) {
        this.iddieta = iddieta;
    }

    public String getNombres_nutriologo() {
        return nombres_nutriologo;
    }

    public void setNombres_nutriologo(String nombres_nutriologo) {
        this.nombres_nutriologo = nombres_nutriologo;
    }

    public String getApellidos_nutriologo() {
        return apellidos_nutriologo;
    }

    public void setApellidos_nutriologo(String apellidos_nutriologo) {
        this.apellidos_nutriologo = apellidos_nutriologo;
    }

    public String getTipo_dieta() {
        return tipo_dieta;
    }

    public void setTipo_dieta(String tipo_dieta) {
        this.tipo_dieta = tipo_dieta;
    }

    public LocalDate getFecha_asignacion_dieta() {
        return fecha_asignacion_dieta;
    }

    public void setFecha_asignacion_dieta(LocalDate fecha_asignacion_dieta) {
        this.fecha_asignacion_dieta = fecha_asignacion_dieta;
    }

    public String getComentario_dieta() {
        return comentario_dieta;
    }

    public void setComentario_dieta(String comentario_dieta) {
        this.comentario_dieta = comentario_dieta;
    }
    
    

    public void mostrarDatosr(TableView<Seguimiento> table, Entrenador en) {
        table.getColumns().clear();
        // Crea las columnas para el TableView
        TableColumn<Seguimiento, String> idAtletaCol = new TableColumn<Seguimiento, String>("ID Atleta");
        idAtletaCol.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));
        TableColumn<Seguimiento, String> nombresAtletaCol = new TableColumn<Seguimiento, String>("Nombres Atleta");
        nombresAtletaCol.setCellValueFactory(new PropertyValueFactory<>("nombres_atleta"));
        TableColumn<Seguimiento, String> apellidosAtletaCol = new TableColumn<Seguimiento, String>("Apellidos Atleta");
        apellidosAtletaCol.setCellValueFactory(new PropertyValueFactory<>("apellidos_atleta"));
        TableColumn<Seguimiento, String> idEntrenadorCol = new TableColumn<Seguimiento, String>("ID Entrenador");
        idEntrenadorCol.setCellValueFactory(new PropertyValueFactory<>("id_entrenador"));
        TableColumn<Seguimiento, String> nombresEntrenadorCol = new TableColumn<Seguimiento, String>("Nombres Entrenador");
        nombresEntrenadorCol.setCellValueFactory(new PropertyValueFactory<>("nombres_entrenador"));
        TableColumn<Seguimiento, String> apellidosEntrenadorCol = new TableColumn<Seguimiento, String>("Apellidos Entrenador");
        apellidosEntrenadorCol.setCellValueFactory(new PropertyValueFactory<>("apellidos_entrenador"));
        TableColumn<Seguimiento, String> idRutinaCol = new TableColumn<Seguimiento, String>("ID Rutina");
        idRutinaCol.setCellValueFactory(new PropertyValueFactory<>("id_rutina"));
        TableColumn<Seguimiento, String> tipoRutinaCol = new TableColumn<Seguimiento, String>("Tipo Rutina");
        tipoRutinaCol.setCellValueFactory(new PropertyValueFactory<>("tipo_rutina"));
        TableColumn<Seguimiento, LocalDate> fechaAsignacionRutinaCol = new TableColumn<Seguimiento, LocalDate>("Fecha Asignación Rutina");
        fechaAsignacionRutinaCol.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion_rutina"));
        TableColumn<Seguimiento, String> comentarioRutinaCol = new TableColumn<Seguimiento, String>("Comentario Rutina");
        comentarioRutinaCol.setCellValueFactory(new PropertyValueFactory<>("comentario_rutina"));

        table.getColumns().addAll(idAtletaCol, nombresAtletaCol, apellidosAtletaCol, idEntrenadorCol, nombresEntrenadorCol,
                apellidosEntrenadorCol, idRutinaCol, tipoRutinaCol, fechaAsignacionRutinaCol,
                comentarioRutinaCol);

        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Obtiene el nombre y la contraseña del nutriólogo
        String nombre = en.getNombres();
        String contraseña = en.getContraseña();

        // Crea la consulta SQL
        String query = "SELECT s.id_atleta, ua.nombres , ua.apellidos,\n"
                + "s.id_entrenador, ue.nombres , ue.apellidos ,\n"
                + "s.id_rutina, r.tipo, r.fecha_asignacion, r.comentario\n"
                + "FROM Seguimiento s\n"
                + "JOIN Atleta a ON s.id_atleta = a.id\n"
                + "JOIN Entrenador e ON s.id_entrenador = e.id\n"
                + "JOIN Usuario ua ON a.id = ua.id\n"
                + "JOIN Usuario ue ON e.id = ue.id\n"
                + "JOIN Rutina r ON s.id_rutina = r.id\n"
                + "where ue.id=(SELECT id FROM Usuario WHERE nombres =? AND (contraseña = ? OR tarjetadeacceso =?));";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(query);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            // Ejecuta la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            // Agrega los resultados al TableView
            while (rs.next()) {
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setIdatleta(rs.getInt("id_atleta"));
                seguimiento.setNombres_atleta(rs.getString("nombres_atleta"));
                seguimiento.setApellidos_atleta(rs.getString("apellidos_atleta"));
                seguimiento.setIdentrenador(rs.getInt("id_entrenador"));
                seguimiento.setNombres_entrenador(rs.getString("nombres_entrenador"));
                seguimiento.setApellidos_entrenador(rs.getString("apellidos_entrenador"));
                seguimiento.setIdrutina(rs.getInt("id_rutina"));
                seguimiento.setTipo_rutina(rs.getString("tipo_rutina"));
                seguimiento.setFecha_asignacion_rutina(rs.getDate("fecha_asignacion_rutina").toLocalDate());
                seguimiento.setComentario_rutina(rs.getString("comentario_rutina"));
                table.getItems().add(seguimiento);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void mostrarDatos(TableView<Seguimiento> table, Nutriologo nu) {
        
        table.getColumns().clear();
        // Crea las columnas para el TableView
        TableColumn<Seguimiento, String> idAtletaCol = new TableColumn<Seguimiento, String>("ID Atleta");
        idAtletaCol.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));
        TableColumn<Seguimiento, String> nombresAtletaCol = new TableColumn<Seguimiento, String>("Nombres Atleta");
        nombresAtletaCol.setCellValueFactory(new PropertyValueFactory<>("nombres_atleta"));
        TableColumn<Seguimiento, String> apellidosAtletaCol = new TableColumn<Seguimiento, String>("Apellidos Atleta");
        apellidosAtletaCol.setCellValueFactory(new PropertyValueFactory<>("apellidos_atleta"));
        TableColumn<Seguimiento, String> idNutriologoCol = new TableColumn<Seguimiento, String>("ID Nutriologo");
        idNutriologoCol.setCellValueFactory(new PropertyValueFactory<>("id_nutriologo"));
        TableColumn<Seguimiento, String> nombresNutriologoCol = new TableColumn<Seguimiento, String>("Nombres Nutriologo");
        nombresNutriologoCol.setCellValueFactory(new PropertyValueFactory<>("nombres_nutriologo"));
        TableColumn<Seguimiento, String> apellidosNutriologoCol = new TableColumn<Seguimiento, String>("Apellidos Nutriologo");
        apellidosNutriologoCol.setCellValueFactory(new PropertyValueFactory<>("apellidos_nutriologo"));
        TableColumn<Seguimiento, String> idDietaCol = new TableColumn<Seguimiento, String>("ID Dieta");
        idDietaCol.setCellValueFactory(new PropertyValueFactory<>("id_dieta"));
        TableColumn<Seguimiento, String> tipoDietaCol = new TableColumn<Seguimiento, String>("Tipo Dieta");
        tipoDietaCol.setCellValueFactory(new PropertyValueFactory<>("tipo_dieta"));
        TableColumn<Seguimiento, LocalDate> fechaAsignacionDietaCol = new TableColumn<Seguimiento, LocalDate>("Fecha Asignación Dieta");
        fechaAsignacionDietaCol.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion_dieta"));
        TableColumn<Seguimiento, String> comentarioDietaCol = new TableColumn<Seguimiento, String>("Comentario Dieta");
        comentarioDietaCol.setCellValueFactory(new PropertyValueFactory<>("comentario_dieta"));

        table.getColumns().addAll(idAtletaCol, nombresAtletaCol, apellidosAtletaCol, idNutriologoCol, nombresNutriologoCol,
                apellidosNutriologoCol, idDietaCol, tipoDietaCol, fechaAsignacionDietaCol,
                comentarioDietaCol);

        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Obtiene el nombre y la contraseña del nutriólogo
        String nombre = nu.getNombres();
        String contraseña = nu.getContraseña();

        // Crea la consulta SQL
        String query = "SELECT s.id_atleta, ua.nombres AS nombres_atleta, ua.apellidos AS apellidos_atleta,\n" +
                        "       s.id_nutriologo, un.nombres AS nombres_nutriologo, un.apellidos AS apellidos_nutriologo,\n" +
                        "       s.id_dieta, d.tipo, d.fecha_asignacion, d.comentario\n" +
                        "FROM Seguimiento s\n" +
                        "JOIN Atleta a ON s.id_atleta = a.id\n" +
                        "JOIN Nutriologo n ON s.id_nutriologo = n.id\n" +
                        "JOIN Usuario ua ON a.id = ua.id\n" +
                        "JOIN Usuario un ON n.id = un.id\n" +
                        "JOIN Dieta d ON s.id_dieta = d.id\n" +
                        "where un.id=(SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso =?));";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(query);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            // Ejecuta la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            // Agrega los resultados al TableView
            while (rs.next()) {
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setIdatleta(rs.getInt("id_atleta"));
                seguimiento.setNombres_atleta(rs.getString("nombres_atleta"));
                seguimiento.setApellidos_atleta(rs.getString("apellidos_atleta"));
                seguimiento.setIdnutriologo(rs.getInt("id_nutriologo"));
                seguimiento.setNombres_nutriologo(rs.getString("nombres_nutriologo"));
                seguimiento.setApellidos_nutriologo(rs.getString("apellidos_nutriologo"));
                seguimiento.setIddieta(rs.getInt("id_dieta"));
                seguimiento.setTipo_dieta(rs.getString("tipo"));
                seguimiento.setFecha_asignacion_dieta(rs.getDate("fecha_asignacion").toLocalDate());
                seguimiento.setComentario_dieta(rs.getString("comentario"));
                table.getItems().add(seguimiento);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }
    
    
}
