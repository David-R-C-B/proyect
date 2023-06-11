package clasesymetodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import proyect.Conexion;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(String nombres, String contraseña) {
        this.nombres = nombres;
        this.contraseña = contraseña;
    }

    private Integer id;
    private String nombres;
    private String apellidos;
    private String contraseña;
    private String tarjetaDeAcceso;
    private String cargo;
    private Boolean estado_actividad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getEstado_actividad() {
        return estado_actividad;
    }

    public void setEstado_actividad(Boolean estado_actividad) {
        this.estado_actividad = estado_actividad;
    }

    public void MostrarAdministrador(TableView<Administrador> TablaAdministrador) {
        //realizamos la conexion
        Conexion objetoconexion = new Conexion();
        //esto limpia las columnas para que se puedan ingresar nuevas 
        TablaAdministrador.getColumns().clear();

        ObservableList<Administrador> data = FXCollections.observableArrayList();
        //realizamos la consulta a la base de datos
        String sql = "select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,\n" +
                    "m.administrador_cargo,m.administrador_estado_actividad\n" +
                    "from mostrar_administradores() m\n" +
                    "order by m.usuario_id;";
        Statement st;

        //Declaramos las columnas 
        TableColumn idcol = new TableColumn("ID");
        TableColumn contracol = new TableColumn("Contraseña");
        TableColumn tarjetacol = new TableColumn("Tarjeta de acceso");
        TableColumn nombrecol = new TableColumn("Nombre");
        TableColumn apellidocol = new TableColumn("Apellido");
        TableColumn ocupacioncol = new TableColumn("Ocupacion");
        TableColumn cargocol = new TableColumn("Cargo del Administrador");
        TableColumn estadocol = new TableColumn("Estado");

        //declaramos de donde se sacaran los datos para las columnas
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        contracol.setCellValueFactory(new PropertyValueFactory("contraseña"));
        tarjetacol.setCellValueFactory(new PropertyValueFactory("tarjetaDeAcceso"));
        nombrecol.setCellValueFactory(new PropertyValueFactory("nombres"));
        apellidocol.setCellValueFactory(new PropertyValueFactory("apellidos"));
        ocupacioncol.setCellValueFactory(new PropertyValueFactory("ocupacion"));
        cargocol.setCellValueFactory(new PropertyValueFactory("cargo"));

        estadocol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Administrador, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Administrador, String> param) {
                Administrador administrador = param.getValue();
                String estado = administrador.getEstado_actividad() ? "Activo" : "Inactivo";
                return new SimpleStringProperty(estado);
            }
        });

        try {
            st = objetoconexion.EstablecerConeccion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //creamos un objeto
                Administrador administrador = new Administrador();
                //pasamos los datos obtenidos de la consulta a las variables que tiene atleta
                administrador.setId(rs.getInt(1));
                administrador.setContraseña(rs.getString(2));
                administrador.setTarjetaDeAcceso(rs.getString(3));
                administrador.setNombres(rs.getString(4));
                administrador.setApellidos(rs.getString(5));
                administrador.setOcupacion(rs.getString(6));
                administrador.setCargo(rs.getString(7));
                administrador.setEstado_actividad(rs.getBoolean(8));

                // Establece los demás atributos del atleta con los datos obtenidos
                data.add(administrador);
            }
            TablaAdministrador.setItems(data);
            TablaAdministrador.getColumns().addAll(idcol);
            TablaAdministrador.getColumns().addAll(contracol);
            TablaAdministrador.getColumns().addAll(tarjetacol);
            TablaAdministrador.getColumns().addAll(nombrecol);
            TablaAdministrador.getColumns().addAll(apellidocol);
            TablaAdministrador.getColumns().addAll(ocupacioncol);
            TablaAdministrador.getColumns().addAll(cargocol);
            TablaAdministrador.getColumns().addAll(estadocol);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void SeleccionarAdministrador(TableView<Administrador> TablaAdministrador, TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField cargo, ComboBox<String> estado) {

        TablaAdministrador.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Administrador administradorseleccionado = TablaAdministrador.getSelectionModel().getSelectedItem();

                id.setText(Integer.toString(administradorseleccionado.getId()));
                contraseña.setText(administradorseleccionado.getContraseña());
                tarjetacdeacceso.setText(administradorseleccionado.getTarjetaDeAcceso());
                nombre.setText(administradorseleccionado.getNombres());
                apellido.setText(administradorseleccionado.getApellidos());
                ocupacion.setText(administradorseleccionado.getOcupacion());
                cargo.setText(administradorseleccionado.getCargo());

                String estadoStr = administradorseleccionado.getEstado_actividad() ? "Activo" : "Inactivo";
                estado.setValue(estadoStr);
            };
        });
    }

    public void AgregarAdministrador(TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField cargo, ComboBox<String> estado) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para insertar un nuevo atleta en la base de datos
        String sql = "INSERT INTO Usuario (contraseña , tarjetadeacceso , nombres , apellidos , ocupacion ) VALUES (? ,? ,? ,?,?);\n"
                + "INSERT INTO Administradores (id, cargo, estado_actividad) VALUES ((SELECT id FROM Usuario WHERE nombres = ? AND apellidos = ?), ?, ?);";

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
            ps.setString(8, cargo.getText());

            boolean estadoBool = estado.getValue().equalsIgnoreCase("Activo");
            ps.setBoolean(9, estadoBool);

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

    public void ModificarAdministrador(TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField cargo, ComboBox<String> estado) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para actualizar los datos del atleta en la base de datos
        String sql = "UPDATE Usuario SET contraseña = ?, tarjetadeacceso = ?, nombres = ?, apellidos = ?, ocupacion = ? WHERE id = ?;\n"
                + "UPDATE Administradores SET  cargo=?, estado_actividad=? WHERE id = ?;";

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
            ps.setString(7, cargo.getText());
            boolean estadoBool = estado.getValue().equalsIgnoreCase("Activo");
            ps.setBoolean(8, estadoBool);
            ps.setInt(9, Integer.parseInt(id.getText()));

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

    public void EliminarAdministrador(TextField id) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para eliminar los datos del entrenador de la base de datos
        String sql = "DELETE FROM Administradores WHERE id = ?;\n"
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
