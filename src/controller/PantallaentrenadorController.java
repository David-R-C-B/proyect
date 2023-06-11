package controller;

import clasesymetodos.Atleta;
import clasesymetodos.Entrenador;
import clasesymetodos.Rutina;
import clasesymetodos.Seguimiento;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import proyect.Conexion;

/**
 * FXML Controller class
 *
 * @author David Ronald Calle Blanco
 */
public class PantallaentrenadorController implements Initializable {

    Rutina objetorutina = new Rutina();
    Seguimiento objetoseguimiento = new Seguimiento();
    

    @FXML
    private Label bienvenidoLabe;

    @FXML
    private TableView<Atleta> tabla;
    
    @FXML
    private TableView<Seguimiento> segui;

    @FXML
    private TableView<Rutina> tabla1;

    @FXML
    private AnchorPane ruti;
    
    @FXML
    private AnchorPane seguimiento;

    @FXML
    private TextField idEntrenador;

    @FXML
    private TextField idAtleta;

    @FXML
    private TextField tipo;

    @FXML
    private DatePicker fecha;

    @FXML
    private TextArea comentario;

    @FXML
    private ScrollPane rutiatleta;

    @FXML
    private ScrollPane atletaasignado;

    private Entrenador en;

    public PantallaentrenadorController() {
    }

    public PantallaentrenadorController(Entrenador en) {
        this.en = en;
    }

    // Setter para el entrenador
    public void setEntrenador(Entrenador en) {
        this.en = en;
    }

    public void mostrarDatosEnTabla1(TableView<Rutina> tabla1, Entrenador en) {
        tabla1.getItems().clear();
        tabla1.getColumns().clear();

        // Define las columnas de la tabla
        TableColumn<Rutina, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla1.getColumns().add(idColumn);

        TableColumn<Rutina, Integer> ideentrenadorcol = new TableColumn<>("Id Entrenador");
        ideentrenadorcol.setCellValueFactory(new PropertyValueFactory<>("id_entrenador"));
        tabla1.getColumns().add(ideentrenadorcol);

        TableColumn<Rutina, Integer> idatletacol = new TableColumn<>("Id Atleta");
        idatletacol.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));
        tabla1.getColumns().add(idatletacol);

        TableColumn<Rutina, String> rutinacol = new TableColumn<>("Tipo de Rutina");
        rutinacol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabla1.getColumns().add(rutinacol);

        TableColumn<Rutina, Date> fechacol = new TableColumn<>("Fecha de Asignacion");
        fechacol.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion"));
        tabla1.getColumns().add(fechacol);

        TableColumn<Rutina, Integer> comentariocol = new TableColumn<>("Comentario");
        comentariocol.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        tabla1.getColumns().add(comentariocol);

        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Obtiene el nombre y la contraseña del entrenador
        String nombre = en.getNombres();
        String contraseña = en.getContraseña();

        // Crea la consulta SQL para obtener los datos de los atletas de la base de datos
        String sql = "select id,id_entrenador,id_atleta,tipo,fecha_asignacion,comentario\n"
                + "from rutina where id_entrenador = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso = ?));";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            // Ejecuta la consulta y obtiene los resultados
            ResultSet rs = ps.executeQuery();

            // Crea una lista para almacenar los atletas
            ObservableList<Rutina> rutinas = FXCollections.observableArrayList();

            // Recorre los resultados y crea objetos Atleta con los datos
            while (rs.next()) {
                Rutina rutina = new Rutina();
                rutina.setId(rs.getInt("id"));
                rutina.setId_entrenador(rs.getInt("id_entrenador"));
                rutina.setId_atleta(rs.getInt("id_atleta"));
                rutina.setTipo(rs.getString("tipo"));
                rutina.setFecha_asignacion(rs.getDate("fecha_asignacion"));
                rutina.setComentario(rs.getString("comentario"));
                // Agrega el objeto Atleta a la lista
                rutinas.add(rutina);
            }

            // Muestra los datos en la tabla
            tabla1.setItems(rutinas);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void mostrarDatosEnTabla(TableView<Atleta> tabla, Entrenador en) {
        tabla.getItems().clear();
        tabla.getColumns().clear();

        // Define las columnas de la tabla
        TableColumn<Atleta, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla.getColumns().add(idColumn);

        TableColumn<Atleta, String> nombresColumn = new TableColumn<>("Nombres");
        nombresColumn.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tabla.getColumns().add(nombresColumn);

        TableColumn<Atleta, String> apellidosColumn = new TableColumn<>("Apellidos");
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tabla.getColumns().add(apellidosColumn);

        TableColumn<Atleta, String> ocupacionColumn = new TableColumn<>("Ocupación");
        ocupacionColumn.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        tabla.getColumns().add(ocupacionColumn);

        TableColumn<Atleta, Integer> tamañoColumn = new TableColumn<>("Tamaño (cm)");
        tamañoColumn.setCellValueFactory(new PropertyValueFactory<>("tamaño"));
        tabla.getColumns().add(tamañoColumn);

        TableColumn<Atleta, Integer> pesoColumn = new TableColumn<>("Peso (g)");
        pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));
        tabla.getColumns().add(pesoColumn);

        TableColumn<Atleta, Integer> edadColumn = new TableColumn<>("Edad");
        edadColumn.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tabla.getColumns().add(edadColumn);

        TableColumn<Atleta, String> categoriaColumn = new TableColumn<>("Categoría");
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tabla.getColumns().add(categoriaColumn);

        TableColumn<Atleta, String> disiplinaColumn = new TableColumn<>("Disiplina");
        disiplinaColumn.setCellValueFactory(new PropertyValueFactory<>("disiplina"));
        tabla.getColumns().add(disiplinaColumn);

        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Obtiene el nombre y la contraseña del entrenador
        String nombre = en.getNombres();
        String contraseña = en.getContraseña();

        // Crea la consulta SQL para obtener los datos de los atletas de la base de datos
        String sql = "SELECT m.usuario_id,m.nombres,m.apellidos,m.ocupacion,m.tamaño_centimetros,m.peso_gramos,"
                + "m.atleta_edad,m.categoria,m.disiplina "
                + "FROM mostrar_atletas() m "
                + "WHERE m.usuario_id=(SELECT a.id "
                + "FROM enlace e "
                + "JOIN Atleta a ON e.id_atleta = a.id "
                + "JOIN Nutriologo n ON e.id_nutriologo = n.id "
                + "JOIN Entrenador t ON e.id_entrenador = t.id "
                + "JOIN Usuario ua ON a.id = ua.id "
                + "JOIN Usuario un ON n.id = un.id "
                + "JOIN Usuario ut ON t.id = ut.id "
                + "WHERE ut.id = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso = ?))LIMIT 1)"
                + "ORDER BY m.usuario_id;";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            // Ejecuta la consulta y obtiene los resultados
            ResultSet rs = ps.executeQuery();

            // Crea una lista para almacenar los atletas
            ObservableList<Atleta> atletas = FXCollections.observableArrayList();

            // Recorre los resultados y crea objetos Atleta con los datos
            while (rs.next()) {
                Atleta atleta = new Atleta();
                atleta.setId(rs.getInt("usuario_id"));
                atleta.setNombres(rs.getString("nombres"));
                atleta.setApellidos(rs.getString("apellidos"));
                atleta.setOcupacion(rs.getString("ocupacion"));
                atleta.setTamaño(rs.getInt("tamaño_centimetros"));
                atleta.setPeso(rs.getInt("peso_gramos"));
                atleta.setEdad(rs.getInt("atleta_edad"));
                atleta.setCategoria(rs.getString("categoria"));
                atleta.setDisiplina(rs.getString("disiplina"));

                // Agrega el objeto Atleta a la lista
                atletas.add(atleta);
            }

            // Muestra los datos en la tabla
            tabla.setItems(atletas);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void mostrarIdEntrenador(TextField idEntrenador, Entrenador en) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();
        // Obtiene el nombre y la contraseña del entrenador
        String nombre = en.getNombres();
        String contraseña = en.getContraseña();

        // Crea la consulta SQL para obtener el ID del usuario
        String sql = "SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso = ?)";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            // Ejecuta la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            // Verifica si hay un resultado
            if (rs.next()) {
                // Obtiene el ID del usuario
                int id = rs.getInt("id");

                // Muestra el ID en el TextField
                idEntrenador.setText(String.valueOf(id));
            } else {
                // Si no hay resultados, muestra un mensaje de error
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensaje de error");
                alert.setHeaderText(null);
                alert.setContentText("No se encontró ningún usuario con ese nombre y contraseña");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void mostrarIdAtleta(TableView<Atleta> tabla, TextField idAtleta) {
        // Agrega un listener para detectar cambios en la selección de la tabla
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Obtiene el atleta seleccionado
                Atleta atletaSeleccionado = tabla.getSelectionModel().getSelectedItem();

                // Muestra el ID del atleta en el TextField
                idAtleta.setText(String.valueOf(atletaSeleccionado.getId()));
            }
        });
    }

    
    //accion para menubar/rutina
    @FXML
    private void rutina(ActionEvent event) throws SQLException {
        ruti.setVisible(true);
        seguimiento.setVisible(false);
        mostrarDatosEnTabla(tabla, en);
        mostrarIdEntrenador(idEntrenador, en);
        mostrarDatosEnTabla1(tabla1, en);
    }
    
    //accion para menubar/seguimiento
    @FXML
    private void seguimiento(ActionEvent event) throws SQLException {
        ruti.setVisible(false);
        seguimiento.setVisible(true);
        objetoseguimiento.mostrarDatosr(segui, en);
    }

    @FXML
    private void agregar(ActionEvent event) throws SQLException {
        objetorutina.insertarRutina(idAtleta, idEntrenador, tipo, fecha, comentario);
        mostrarDatosEnTabla(tabla, en);
        mostrarDatosEnTabla1(tabla1, en);
        mostrarIdEntrenador(idEntrenador, en);
    }

    @FXML
    private void verrutinas(ActionEvent event) throws SQLException {
        rutiatleta.setVisible(true);
        atletaasignado.setVisible(false);

    }

    @FXML
    private void veratleta(ActionEvent event) throws SQLException {
        rutiatleta.setVisible(false);
        atletaasignado.setVisible(true);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (en != null) {
            String nom = en.getNombres();
            bienvenidoLabe.setText("Bienvenido " + nom);
        } else {
            // manejar el caso en que admin sea nulo
            bienvenidoLabe.setText("Bienvenido ");
        }

        mostrarIdAtleta(tabla, idAtleta);

    }

}
