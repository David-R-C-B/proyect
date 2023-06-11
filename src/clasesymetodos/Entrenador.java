package clasesymetodos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import proyect.Conexion;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Entrenador extends Usuario {

    public Entrenador(String nombres, String contraseña) {
        this.nombres = nombres;
        this.contraseña = contraseña;
    }

    public Entrenador() {
    }
    
    private Integer id;
    private String nombres;
    private String apellidos;
    private String contraseña;
    private String tarjetaDeAcceso;
    private String ocupacion;
    private Integer edad;
    private LocalDate fecha;
    private String direccion;
    private String correoelectronico;

    

    @Override
    public String getNombres() {
        return nombres;
    }

    @Override
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String getContraseña() {
        return contraseña;
    }

    @Override
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String getTarjetaDeAcceso() {
        return tarjetaDeAcceso;
    }

    @Override
    public void setTarjetaDeAcceso(String tarjetaDeAcceso) {
        this.tarjetaDeAcceso = tarjetaDeAcceso;
    }

    @Override
    public String getOcupacion() {
        return ocupacion;
    }

    @Override
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    
    public void MostrarEntrenador(TableView<Entrenador> TablaEntrenador) {
        //realizamos la conexion
        Conexion objetoconexion = new Conexion();
        //esto limpia las columnas para que se puedan ingresar nuevas 
        TablaEntrenador.getColumns().clear();

        ObservableList<Entrenador> data = FXCollections.observableArrayList();
        //realizamos la consulta a la base de datos
        String sql ="select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,m.entrenador_fechadenacimiento,\n" +
                    "m.entrenador_edad,m.entrenador_dirección,m.entrenador_correoelectronico\n" +
                    "from mostrar_entrenadores() m\n" +
                    "order by m.usuario_id;";
        Statement st;

        //Declaramos las columnas 
        TableColumn idcol = new TableColumn("ID");
        TableColumn contracol = new TableColumn("Contraseña");
        TableColumn tarjetacol = new TableColumn("Tarjeta de acceso");
        TableColumn nombrecol = new TableColumn("Nombre");
        TableColumn apellidocol = new TableColumn("Apellido");
        TableColumn ocupacioncol = new TableColumn("Ocupacion");
        TableColumn fechacol = new TableColumn("Fecha de Nacimiento");
        TableColumn edadcol = new TableColumn("Edad");
        TableColumn direccioncol = new TableColumn("Direccion");
        TableColumn corelcol = new TableColumn("Correo Electronico");

        //declaramos de donde se sacaran los datos para las columnas
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        contracol.setCellValueFactory(new PropertyValueFactory("contraseña"));
        tarjetacol.setCellValueFactory(new PropertyValueFactory("tarjetaDeAcceso"));
        nombrecol.setCellValueFactory(new PropertyValueFactory("nombres"));
        apellidocol.setCellValueFactory(new PropertyValueFactory("apellidos"));
        ocupacioncol.setCellValueFactory(new PropertyValueFactory("ocupacion"));
        fechacol.setCellValueFactory(new PropertyValueFactory("fecha"));
        edadcol.setCellValueFactory(new PropertyValueFactory("edad"));
        direccioncol.setCellValueFactory(new PropertyValueFactory("direccion"));
        corelcol.setCellValueFactory(new PropertyValueFactory("correoelectronico"));

        try {
            st = objetoconexion.EstablecerConeccion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //creamos un objeto
                Entrenador entrenador =new Entrenador();
                //pasamos los datos obtenidos de la consulta a las variables que tiene atleta
                entrenador.setId(rs.getInt(1));
                entrenador.setContraseña(rs.getString(2));
                entrenador.setTarjetaDeAcceso(rs.getString(3));
                entrenador.setNombres(rs.getString(4));
                entrenador.setApellidos(rs.getString(5));
                entrenador.setOcupacion(rs.getString(6));
                Date fechaSql = rs.getDate(7);
                LocalDate fechaLocal = fechaSql.toLocalDate();
                entrenador.setFecha(fechaLocal);
                entrenador.setEdad(rs.getInt(8));
                entrenador.setDireccion(rs.getString(9));
                entrenador.setCorreoelectronico(rs.getString(10));

                // Establece los demás atributos del atleta con los datos obtenidos
                data.add(entrenador);
            }
            TablaEntrenador.setItems(data);
            TablaEntrenador.getColumns().addAll(idcol);
            TablaEntrenador.getColumns().addAll(contracol);
            TablaEntrenador.getColumns().addAll(tarjetacol);
            TablaEntrenador.getColumns().addAll(nombrecol);
            TablaEntrenador.getColumns().addAll(apellidocol);
            TablaEntrenador.getColumns().addAll(ocupacioncol);
            TablaEntrenador.getColumns().addAll(fechacol);
            TablaEntrenador.getColumns().addAll(edadcol);
            TablaEntrenador.getColumns().addAll(direccioncol);
            TablaEntrenador.getColumns().addAll(corelcol);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }
    
    
    public void SeleccionarEntrenador(TableView<Entrenador> TablaEntrenador, TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField edad,TextField direccion, TextField correoelc, 
            DatePicker datePickerFechaNacimiento) {

        TablaEntrenador.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Entrenador entrenadorseleccionado = TablaEntrenador.getSelectionModel().getSelectedItem();

                id.setText(Integer.toString(entrenadorseleccionado.getId()));
                contraseña.setText(entrenadorseleccionado.getContraseña());
                tarjetacdeacceso.setText(entrenadorseleccionado.getTarjetaDeAcceso());
                nombre.setText(entrenadorseleccionado.getNombres());
                apellido.setText(entrenadorseleccionado.getApellidos());
                ocupacion.setText(entrenadorseleccionado.getOcupacion());
                edad.setText(Integer.toString(entrenadorseleccionado.getEdad()));
                direccion.setText(entrenadorseleccionado.getDireccion());
                correoelc.setText(entrenadorseleccionado.getCorreoelectronico());
                datePickerFechaNacimiento.setValue(entrenadorseleccionado.getFecha());
            };
        });
    }
    
    public void AgregarEntrenador(TextField contraseña, TextField tarjetacdeacceso,TextField nombre, TextField apellido, 
            TextField ocupacion, TextField edad,TextField direccion, TextField correoelc, DatePicker datePickerFechaNacimiento) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para insertar un nuevo atleta en la base de datos
        String sql = "INSERT INTO Usuario (contraseña , tarjetadeacceso , nombres , apellidos , ocupacion ) VALUES (? ,? ,? ,?,?);\n" +
                    "INSERT INTO Entrenador (id , fechadenacimiento , edad , dirección , correoelectronico )" +
                    "VALUES ((SELECT id FROM Usuario WHERE nombres = ? AND apellidos = ?) ,?,?,?,?);";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, contraseña.getText());
            ps.setString(2, tarjetacdeacceso.getText());
            ps.setString(3, nombre.getText());
            ps.setString(4, apellido.getText());
            ps.setString(5, ocupacion.getText());
            ps.setString(6, nombre.getText());
            ps.setString(7, apellido.getText());
            ps.setDate(8, Date.valueOf(datePickerFechaNacimiento.getValue()));
            ps.setInt(9, Integer.parseInt(edad.getText()));
            ps.setString(10, direccion.getText());
            ps.setString(11, correoelc.getText());

            // Ejecuta la consulta
            ps.executeUpdate();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }
    
    
    public void ModificarEntrenadr(TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField edad, TextField direccion, 
            TextField correoelc, DatePicker datePickerFechaNacimiento) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para actualizar los datos del atleta en la base de datos
        String sql = "UPDATE Usuario SET contraseña = ?, tarjetadeacceso = ?, nombres = ?, apellidos = ?, ocupacion = ? WHERE id = ?;\n"
                + "UPDATE entrenador SET  fechadenacimiento=?, edad=?, dirección=?, correoelectronico=? WHERE id = ?;";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setString(1, contraseña.getText());
            ps.setString(2, tarjetacdeacceso.getText());
            ps.setString(3, nombre.getText());
            ps.setString(4, apellido.getText());
            ps.setString(5, ocupacion.getText());
            ps.setInt(6, Integer.parseInt(id.getText()));
            ps.setDate(7, Date.valueOf(datePickerFechaNacimiento.getValue()));
            ps.setInt(8, Integer.parseInt(edad.getText()));
            ps.setString(9, direccion.getText());
            ps.setString(10, correoelc.getText());
            ps.setInt(11, Integer.parseInt(id.getText()));

            // Ejecuta la consulta
            ps.executeUpdate();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }
    
    
    public void EliminarEntrenador(TextField id) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para eliminar los datos del entrenador de la base de datos
        String sql = "DELETE FROM entrenador WHERE id = ?;\n"
                + "DELETE FROM Usuario WHERE id = ?;";

        try {
            // Prepara la consulta
            PreparedStatement ps = objetoconexion.EstablecerConeccion().prepareStatement(sql);

            // Establece los valores de los parámetros de la consulta
            ps.setInt(1, Integer.parseInt(id.getText()));
            ps.setInt(2, Integer.parseInt(id.getText()));

            // Ejecuta la consulta
            ps.executeUpdate();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }
}
