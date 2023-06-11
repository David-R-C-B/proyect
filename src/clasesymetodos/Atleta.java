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
public class Atleta extends Usuario {

    public Atleta(String nombres, String contraseña) {
        this.nombres = nombres;
        this.contraseña = contraseña;
    }

    public Atleta() {

    }

    private Integer id;
    private String nombres;
    private String apellidos;
    private String contraseña;
    private String tarjetaDeAcceso;
    private String ocupacion;
    private Integer edad;
    private LocalDate fecha;
    private String correoelectronico;
    private Integer tamaño;
    private Integer peso;
    private String direccion;
    private String categoria;
    private String disiplina;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTamaño() {
        return tamaño;
    }

    public void setTamaño(Integer tamaño) {
        this.tamaño = tamaño;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDisiplina() {
        return disiplina;
    }

    public void setDisiplina(String disiplina) {
        this.disiplina = disiplina;
    }

    /**
     * creamos un metodo MostrarAtletas para poder mostrar los atletas que estan
     * registrados en la base de datos
     *
     */
    public void MostrarAtletas(TableView<Atleta> TablaAtletas) {
        //realizamos la conexion
        Conexion objetoconexion = new Conexion();
        //esto limpia las columnas para que se puedan ingresar nuevas 
        TablaAtletas.getColumns().clear();

        ObservableList<Atleta> data = FXCollections.observableArrayList();
        //realizamos la consulta a la base de datos
        String sql ="select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,m.tamaño_centimetros,m.peso_gramos,m.atleta_fechadenacimiento,\n" +
                    "m.atleta_edad,m.categoria,m.disiplina,m.atleta_dirección,m.atleta_correoelectronico\n" +
                    "from mostrar_atletas() m\n" +
                    "order by m.usuario_id;";
        Statement st;

        //Declaramos las columnas 
        TableColumn idcol = new TableColumn("ID");
        TableColumn contracol = new TableColumn("Contraseña");
        TableColumn tarjetacol = new TableColumn("Tarjeta de acceso");
        TableColumn nombrecol = new TableColumn("Nombre");
        TableColumn apellidocol = new TableColumn("Apellido");
        TableColumn ocupacioncol = new TableColumn("Ocupacion");
        TableColumn tamañocol = new TableColumn("Tamaño");
        TableColumn pesocol = new TableColumn("Peso");
        TableColumn fechacol = new TableColumn("Fecha de Nacimiento");
        TableColumn edadcol = new TableColumn("Edad");
        TableColumn categoriacol = new TableColumn("Categoria");
        TableColumn disiplinacol = new TableColumn("Disiplina");
        TableColumn direccioncol = new TableColumn("Direccion");
        TableColumn corelcol = new TableColumn("Correo Electronico");

        //declaramos de donde se sacaran los datos para las columnas
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        contracol.setCellValueFactory(new PropertyValueFactory("contraseña"));
        tarjetacol.setCellValueFactory(new PropertyValueFactory("tarjetaDeAcceso"));
        nombrecol.setCellValueFactory(new PropertyValueFactory("nombres"));
        apellidocol.setCellValueFactory(new PropertyValueFactory("apellidos"));
        ocupacioncol.setCellValueFactory(new PropertyValueFactory("ocupacion"));
        tamañocol.setCellValueFactory(new PropertyValueFactory("tamaño"));
        pesocol.setCellValueFactory(new PropertyValueFactory("peso"));
        fechacol.setCellValueFactory(new PropertyValueFactory("fecha"));
        edadcol.setCellValueFactory(new PropertyValueFactory("edad"));
        categoriacol.setCellValueFactory(new PropertyValueFactory("categoria"));
        disiplinacol.setCellValueFactory(new PropertyValueFactory("disiplina"));
        direccioncol.setCellValueFactory(new PropertyValueFactory("direccion"));
        corelcol.setCellValueFactory(new PropertyValueFactory("correoelectronico"));

        try {
            st = objetoconexion.EstablecerConeccion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //creamos un objeto
                Atleta atleta = new Atleta();
                //pasamos los datos obtenidos de la consulta a las variables que tiene atleta
                atleta.setId(rs.getInt(1));
                atleta.setContraseña(rs.getString(2));
                atleta.setTarjetaDeAcceso(rs.getString(3));
                atleta.setNombres(rs.getString(4));
                atleta.setApellidos(rs.getString(5));
                atleta.setOcupacion(rs.getString(6));
                atleta.setTamaño(rs.getInt(7));
                atleta.setPeso(rs.getInt(8));
                Date fechaSql = rs.getDate(9);
                LocalDate fechaLocal = fechaSql.toLocalDate();
                atleta.setFecha(fechaLocal);
                atleta.setEdad(rs.getInt(10));
                atleta.setCategoria(rs.getString(11));
                atleta.setDisiplina(rs.getString(12));
                atleta.setDireccion(rs.getString(13));
                atleta.setCorreoelectronico(rs.getString(14));

                // Establece los demás atributos del atleta con los datos obtenidos
                data.add(atleta);
            }
            TablaAtletas.setItems(data);
            TablaAtletas.getColumns().addAll(idcol);
            TablaAtletas.getColumns().addAll(contracol);
            TablaAtletas.getColumns().addAll(tarjetacol);
            TablaAtletas.getColumns().addAll(nombrecol);
            TablaAtletas.getColumns().addAll(apellidocol);
            TablaAtletas.getColumns().addAll(ocupacioncol);
            TablaAtletas.getColumns().addAll(tamañocol);
            TablaAtletas.getColumns().addAll(pesocol);
            TablaAtletas.getColumns().addAll(fechacol);
            TablaAtletas.getColumns().addAll(edadcol);
            TablaAtletas.getColumns().addAll(categoriacol);
            TablaAtletas.getColumns().addAll(disiplinacol);
            TablaAtletas.getColumns().addAll(direccioncol);
            TablaAtletas.getColumns().addAll(corelcol);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.toString());
            alert.showAndWait();
        }
    }

    public void seleccionarAtleta(TableView<Atleta> TablaAtletas, TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField tamaño, TextField peso, TextField edad,
            TextField categoria, TextField disiplina, TextField direccion, TextField correoelc, DatePicker datePickerFechaNacimiento) {

        TablaAtletas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Atleta atletaSeleccionado = TablaAtletas.getSelectionModel().getSelectedItem();

                id.setText(Integer.toString(atletaSeleccionado.getId()));
                contraseña.setText(atletaSeleccionado.getContraseña());
                tarjetacdeacceso.setText(atletaSeleccionado.getTarjetaDeAcceso());
                nombre.setText(atletaSeleccionado.getNombres());
                apellido.setText(atletaSeleccionado.getApellidos());
                ocupacion.setText(atletaSeleccionado.getOcupacion());
                tamaño.setText(Integer.toString(atletaSeleccionado.getTamaño()));
                peso.setText(Integer.toString(atletaSeleccionado.getPeso()));
                edad.setText(Integer.toString(atletaSeleccionado.getEdad()));
                categoria.setText(atletaSeleccionado.getCategoria());
                disiplina.setText(atletaSeleccionado.getDisiplina());
                direccion.setText(atletaSeleccionado.getDireccion());
                correoelc.setText(atletaSeleccionado.getCorreoelectronico());
                datePickerFechaNacimiento.setValue(atletaSeleccionado.getFecha());
            };
        });
    }

    public void agregarAtleta(TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField tamaño, TextField peso, TextField edad,
            TextField categoria, TextField disiplina, TextField direccion, TextField correoelc, DatePicker datePickerFechaNacimiento) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para insertar un nuevo atleta en la base de datos
        String sql = "INSERT INTO Usuario (contraseña, tarjetadeacceso, nombres, apellidos, ocupacion) VALUES (?, ?, ?, ?, ?);\n"
                + "INSERT INTO Atleta (id, tamaño_centimetros, peso_gramos, fechadenacimiento, edad, categoria, disiplina, dirección, correoelectronico)\n"
                + "VALUES ((SELECT id FROM Usuario WHERE nombres = ? AND apellidos = ?), ?, ?, ?, ?, ?, ?, ?, ?);";

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
            ps.setInt(8, Integer.parseInt(tamaño.getText()));
            ps.setInt(9, Integer.parseInt(peso.getText()));
            ps.setDate(10, Date.valueOf(datePickerFechaNacimiento.getValue()));
            ps.setInt(11, Integer.parseInt(edad.getText()));
            ps.setString(12, categoria.getText());
            ps.setString(13, disiplina.getText());
            ps.setString(14, direccion.getText());
            ps.setString(15, correoelc.getText());

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

    public void ModificarAtleta(TextField id, TextField contraseña, TextField tarjetacdeacceso,
            TextField nombre, TextField apellido, TextField ocupacion, TextField tamaño, TextField peso, TextField edad,
            TextField categoria, TextField disiplina, TextField direccion, TextField correoelc, DatePicker datePickerFechaNacimiento) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para actualizar los datos del atleta en la base de datos
        String sql = "UPDATE Usuario SET contraseña = ?, tarjetadeacceso = ?, nombres = ?, apellidos = ?, ocupacion = ? WHERE id = ?;\n"
                + "UPDATE Atleta SET tamaño_centimetros = ?, peso_gramos = ?, fechadenacimiento = ?, edad = ?, categoria = ?, disiplina = ?, dirección = ?, correoelectronico = ? WHERE id = ?;";

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
            ps.setInt(7, Integer.parseInt(tamaño.getText()));
            ps.setInt(8, Integer.parseInt(peso.getText()));
            ps.setDate(9, Date.valueOf(datePickerFechaNacimiento.getValue()));
            ps.setInt(10, Integer.parseInt(edad.getText()));
            ps.setString(11, categoria.getText());
            ps.setString(12, disiplina.getText());
            ps.setString(13, direccion.getText());
            ps.setString(14, correoelc.getText());
            ps.setInt(15, Integer.parseInt(id.getText()));

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

    public void EliminarAtleta(TextField id) {
        // Realiza la conexión
        Conexion objetoconexion = new Conexion();

        // Crea la consulta SQL para eliminar los datos del atleta de la base de datos
        String sql = "DELETE FROM Atleta WHERE id = ?;\n"
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
