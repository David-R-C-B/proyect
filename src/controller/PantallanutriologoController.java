package controller;

import clasesymetodos.Atleta;
import clasesymetodos.Dietas;
import clasesymetodos.Nutriologo;
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
import javafx.scene.layout.VBox;
import proyect.CaloriasOptimas;
import proyect.Conexion;

/**
 * FXML Controller class
 *
 * @author David Ronald Calle Blanco
 */
public class PantallanutriologoController implements Initializable {

    Dietas objetodieta = new Dietas();
    Seguimiento objetoseguimiento = new Seguimiento();

    @FXML
    private Label bienvenidoLabe;

    @FXML
    private TextField tx1;

    @FXML
    private TextField tx2;

    @FXML
    private TextField tx3;

    @FXML
    private TextField tx4;

    @FXML
    private TextField tx5;

    @FXML
    private TextField tx6;

    @FXML
    private TextField tx7;

    @FXML
    private TextField resultado;

    @FXML
    private Label sistemadeecuaciones;

    @FXML
    private TextField idNutriologo;

    @FXML
    private TextField idAtleta;

    @FXML
    private TextField tipo;

    @FXML
    private DatePicker fecha;

    @FXML
    private TextArea comentario;

    @FXML
    private ScrollPane dietiatleta;

    @FXML
    private ScrollPane atletaasignado;

    @FXML
    private TableView<Atleta> tabla;

    @FXML
    private TableView<Dietas> tabla1;
    
    @FXML
    private TableView<Seguimiento> table;

    @FXML
    private VBox AnalisisNumerico;
    
    @FXML
    private AnchorPane seguimiento;

    @FXML
    private AnchorPane dieti;

    private Nutriologo nu;

    public PantallanutriologoController() {
    }

    public PantallanutriologoController(Nutriologo nu) {
        this.nu = nu;
    }

    // Setter para el nutriolgo
    public void setNutriologo(Nutriologo nu) {
        this.nu = nu;
    }

    //menubar /analisis numerico
    @FXML
    private void analisisnum(ActionEvent event) {
        AnalisisNumerico.setVisible(true);
        dieti.setVisible(false);
        seguimiento.setVisible(false);
    }

    //menubar /dietas
    @FXML
    private void dieta(ActionEvent event) {
        AnalisisNumerico.setVisible(false);
        dieti.setVisible(true);
        seguimiento.setVisible(false);
        mostrarDatosEnTabla1(tabla1, nu);
        mostrarDatosEnTabla(tabla, nu);
        mostrarIdNutriolgo(idNutriologo, nu);
    }
    

    //accion del boton agregar
    @FXML
    private void agregar(ActionEvent event) {
        objetodieta.insertarDieta(idAtleta, idNutriologo, tipo, fecha, comentario);
        mostrarDatosEnTabla(tabla, nu);
        mostrarDatosEnTabla1(tabla1, nu);
    }

    @FXML
    private void verdietas(ActionEvent event) throws SQLException {
        dietiatleta.setVisible(true);
        atletaasignado.setVisible(false);
    }

    @FXML
    private void veratleta(ActionEvent event) throws SQLException {
        dietiatleta.setVisible(false);
        atletaasignado.setVisible(true);
    }

    //accion para menubar/seguimiento
    @FXML
    private void seguimiento(ActionEvent event) throws SQLException {
        AnalisisNumerico.setVisible(false);
        dieti.setVisible(false);
        seguimiento.setVisible(true);
        table.getColumns().clear();
        objetoseguimiento.mostrarDatos(table, nu);
        
    }

    public void mostrarIdNutriolgo(TextField idnutrilogo, Nutriologo nu) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();
        // Obtiene el nombre y la contraseña del entrenador
        String nombre = nu.getNombres();
        String contraseña = nu.getContraseña();

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
                idnutrilogo.setText(String.valueOf(id));
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

    public void mostrarDatosEnTabla1(TableView<Dietas> tabla1, Nutriologo nu) {
        tabla1.getItems().clear();
        tabla1.getColumns().clear();

        // Define las columnas de la tabla
        TableColumn<Dietas, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabla1.getColumns().add(idColumn);

        TableColumn<Dietas, Integer> ideentrenadorcol = new TableColumn<>("Id Nutriologo");
        ideentrenadorcol.setCellValueFactory(new PropertyValueFactory<>("id_nutriologo"));
        tabla1.getColumns().add(ideentrenadorcol);

        TableColumn<Dietas, Integer> idatletacol = new TableColumn<>("Id Atleta");
        idatletacol.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));
        tabla1.getColumns().add(idatletacol);

        TableColumn<Dietas, String> rutinacol = new TableColumn<>("Tipo de Rutina");
        rutinacol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabla1.getColumns().add(rutinacol);

        TableColumn<Dietas, Date> fechacol = new TableColumn<>("Fecha de Asignacion");
        fechacol.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion"));
        tabla1.getColumns().add(fechacol);

        TableColumn<Dietas, Integer> comentariocol = new TableColumn<>("Comentario");
        comentariocol.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        tabla1.getColumns().add(comentariocol);

        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Obtiene el nombre y la contraseña del entrenador
        String nombre = nu.getNombres();
        String contraseña = nu.getContraseña();

        // Crea la consulta SQL para obtener los datos de los atletas de la base de datos
        String sql = "select id,id_nutriologo,id_atleta,tipo,fecha_asignacion,comentario\n"
                + "from Dieta where id_nutriologo = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso = ?));";

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
            ObservableList<Dietas> rutinas = FXCollections.observableArrayList();

            // Recorre los resultados y crea objetos Atleta con los datos
            while (rs.next()) {
                Dietas rutina = new Dietas();
                rutina.setId(rs.getInt("id"));
                rutina.setId_nutriologo(rs.getInt("id_nutriologo"));
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

    public void mostrarDatosEnTabla(TableView<Atleta> tabla, Nutriologo nu) {
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
        String nombre = nu.getNombres();
        String contraseña = nu.getContraseña();

        // Crea la consulta SQL para obtener los datos de los atletas de la base de datos
        String sql = "SELECT m.usuario_id,m.nombres,m.apellidos,m.ocupacion,m.tamaño_centimetros,m.peso_gramos,m.atleta_edad,m.categoria,m.disiplina \n"
                + "FROM mostrar_atletas() m \n"
                + "WHERE m.usuario_id=(SELECT a.id \n"
                + "FROM enlace e \n"
                + "JOIN Atleta a ON e.id_atleta = a.id \n"
                + "JOIN Nutriologo n ON e.id_nutriologo = n.id \n"
                + "JOIN Entrenador t ON e.id_entrenador = t.id\n"
                + "JOIN Usuario ua ON a.id = ua.id \n"
                + "JOIN Usuario un ON n.id = un.id \n"
                + "JOIN Usuario ut ON t.id = ut.id \n"
                + "WHERE un.id = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso =?))LIMIT 1)\n"
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

    @FXML
    private void calcularcalorias(ActionEvent event) {
        try {
            double a = Double.parseDouble(tx1.getText());
            double b = Double.parseDouble(tx2.getText());
            double c = Double.parseDouble(tx3.getText());
            double x0 = Double.parseDouble(tx4.getText());
            double y0 = Double.parseDouble(tx5.getText());
            double z0 = Double.parseDouble(tx6.getText());
            double valorObjetivo = Double.parseDouble(tx7.getText());

            CaloriasOptimas caloriasOptimas = new CaloriasOptimas(a, b, c, valorObjetivo);
            double[] cantidadesOptimas = caloriasOptimas.newtonRaphson(x0, y0, z0, 100, 1e-6);
            resultado.setText("Cantidades óptimas: 1er alimento = " + cantidadesOptimas[0] + ", 2do alimento = " + cantidadesOptimas[1] + ", 3er alimento = " + cantidadesOptimas[2]);
        } catch (NumberFormatException e) {
            resultado.setText("Error: Verifique que todos los campos contengan números válidos.");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (nu != null) {
            String nom = nu.getNombres();
            bienvenidoLabe.setText("Bienvenido " + nom);
        } else {
            // manejar el caso en que admin sea nulo
            bienvenidoLabe.setText("Bienvenido ");
        }

        sistemadeecuaciones.setText("Minimizar (ax + by + cz)/(x + y + z) - valorObjetivo");

        mostrarIdAtleta(tabla, idAtleta);
    }

}
