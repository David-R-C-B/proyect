package controller;

import clasesymetodos.Administrador;
import clasesymetodos.Atleta;
import clasesymetodos.Enlace;
import clasesymetodos.Entrenador;
import clasesymetodos.Nutriologo;
import clasesymetodos.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author David Ronald Calle Blanco
 */
public class PantallaadminController implements Initializable {

    //creamos los objetos que se nesesitaran en la clase
    Atleta objetoatleta = new Atleta();
    Entrenador objetoentrenador = new Entrenador();
    Nutriologo objetonutriologo = new Nutriologo();
    Administrador objetoadministrador = new Administrador();
    Enlace objetoenlace = new Enlace();
    Usuario usuario = new Usuario();

    @FXML
    private Label bienvenido;

    private Administrador admin;

    public PantallaadminController(Administrador admin) {
        this.admin = admin;
    }

    // Setter para el administrador
    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    //pantalla de insercion de datos atleta
    @FXML
    private ScrollPane ptablainat;

    @FXML
    private TableView<Atleta> TablaAtletas;

    @FXML
    private TextField nombrein;

    @FXML
    private TextField contrain;

    @FXML
    private TextField apellidoin;

    @FXML
    private TextField tarjetain;

    @FXML
    private TextField ocupacionin;

    @FXML
    private TextField tamañoin;

    @FXML
    private TextField pesoin;

    @FXML
    private DatePicker fechain;

    @FXML
    private TextField categoriain;

    @FXML
    private TextField edadin;

    @FXML
    private TextField disiplinain;

    @FXML
    private TextField direccionin;

    @FXML
    private TextField correoin;

    //pantalla de edicion de datos atleta
    @FXML
    private ScrollPane ptablaedat;

    @FXML
    private TableView<Atleta> TablaAtletased;

    @FXML
    private TextField ided;

    @FXML
    private TextField nombreed;

    @FXML
    private TextField contraed;

    @FXML
    private TextField apellidoed;

    @FXML
    private TextField tarjetaed;

    @FXML
    private TextField ocupacioed;

    @FXML
    private TextField tamañoed;

    @FXML
    private TextField pesoed;

    @FXML
    private DatePicker fechaed;

    @FXML
    private TextField categoriaed;

    @FXML
    private TextField edaded;

    @FXML
    private TextField disiplinaed;

    @FXML
    private TextField direccioned;

    @FXML
    private TextField correoed;

    //pantalla de edicion de datos atleta
    @FXML
    private ScrollPane ptablaelat;

    @FXML
    private TableView<Atleta> TablaAtletasel;

    @FXML
    private TextField idel;

    @FXML
    private TextField nombreel;

    @FXML
    private TextField contrael;

    @FXML
    private TextField apellidoel;

    @FXML
    private TextField tarjetael;

    @FXML
    private TextField ocupacioel;

    @FXML
    private TextField tamañoel;

    @FXML
    private TextField pesoel;

    @FXML
    private DatePicker fechael;

    @FXML
    private TextField categoriael;

    @FXML
    private TextField edadel;

    @FXML
    private TextField disiplinael;

    @FXML
    private TextField direccionel;

    @FXML
    private TextField correoel;

    //pantalla de ingreso de datos Entrenador
    @FXML
    private ScrollPane ptablainen;

    @FXML
    private TableView<Entrenador> TablaEntrenadorin;

    @FXML
    private TextField nombreinen;

    @FXML
    private TextField contrainen;

    @FXML
    private TextField apellidoinen;

    @FXML
    private TextField tarjetainen;

    @FXML
    private TextField ocupacioninen;

    @FXML
    private DatePicker fechainen;

    @FXML
    private TextField edadinen;

    @FXML
    private TextField direccioninen;

    @FXML
    private TextField correoinen;

    //pantalla de edicion de datos Entrenador
    @FXML
    private ScrollPane ptablaeden;

    @FXML
    private TableView<Entrenador> TablaEntrenadored;

    @FXML
    private TextField nombreeden;

    @FXML
    private TextField contraeden;

    @FXML
    private TextField apellidoeden;

    @FXML
    private TextField tarjetaeden;

    @FXML
    private TextField ocupacioneden;

    @FXML
    private DatePicker fechaeden;

    @FXML
    private TextField edadeden;

    @FXML
    private TextField direccioneden;

    @FXML
    private TextField correoeden;

    @FXML
    private TextField ideden;

    //pantalla de eliminacion de datos Entrenador
    @FXML
    private ScrollPane ptablaelen;

    @FXML
    private TableView<Entrenador> TablaEntrenadorel;

    @FXML
    private TextField nombreelen;

    @FXML
    private TextField contraelen;

    @FXML
    private TextField apellidoelen;

    @FXML
    private TextField tarjetaelen;

    @FXML
    private TextField ocupacionelen;

    @FXML
    private DatePicker fechaelen;

    @FXML
    private TextField edadelen;

    @FXML
    private TextField direccionelen;

    @FXML
    private TextField correoelen;

    @FXML
    private TextField idelen;

    //pantalla de ingreso de datos Nutriologo
    @FXML
    private ScrollPane ptablainnu;

    @FXML
    private TableView<Nutriologo> TablaNutriologoin;

    @FXML
    private TextField nombreinnu;

    @FXML
    private TextField contrainnu;

    @FXML
    private TextField apellidoinnu;

    @FXML
    private TextField tarjetainnu;

    @FXML
    private TextField ocupacioninnu;

    @FXML
    private DatePicker fechainnu;

    @FXML
    private TextField edadinnu;

    @FXML
    private TextField direccioninnu;

    @FXML
    private TextField correoinnu;

    //pantalla de edicion de datos Nutriologo
    @FXML
    private ScrollPane ptablaednu;

    @FXML
    private TableView<Nutriologo> TablaNutriologoed;

    @FXML
    private TextField nombreednu;

    @FXML
    private TextField contraednu;

    @FXML
    private TextField apellidoednu;

    @FXML
    private TextField tarjetaednu;

    @FXML
    private TextField ocupacionednu;

    @FXML
    private DatePicker fechaednu;

    @FXML
    private TextField edadednu;

    @FXML
    private TextField direccionednu;

    @FXML
    private TextField correoednu;

    @FXML
    private TextField idednu;

    //pantalla de eliminacion de datos Nutriologo
    @FXML
    private ScrollPane ptablaelnu;

    @FXML
    private TableView<Nutriologo> TablaNutriologoel;

    @FXML
    private TextField nombreelnu;

    @FXML
    private TextField contraelnu;

    @FXML
    private TextField apellidoelnu;

    @FXML
    private TextField tarjetaelnu;

    @FXML
    private TextField ocupacionelnu;

    @FXML
    private DatePicker fechaelnu;

    @FXML
    private TextField edadelnu;

    @FXML
    private TextField direccionelnu;

    @FXML
    private TextField correoelnu;

    @FXML
    private TextField idelnu;

    //pantalla de insercion de datos administador
    @FXML
    private ScrollPane ptablainad;

    @FXML
    private TableView<Administrador> TablaAdministrador;

    @FXML
    private TextField nombreinad;

    @FXML
    private TextField contrainad;

    @FXML
    private TextField apellidoinad;

    @FXML
    private TextField tarjetainad;

    @FXML
    private TextField ocupacioninad;

    @FXML
    private TextField cargoinad;

    @FXML
    private ComboBox<String> estado;

    //pantalla de edicion de datos Administrador
    @FXML
    private ScrollPane ptablaedad;

    @FXML
    private TableView<Administrador> TablaAdministradored;

    @FXML
    private TextField idedad;

    @FXML
    private TextField nombreedad;

    @FXML
    private TextField contraedad;

    @FXML
    private TextField apellidoedad;

    @FXML
    private TextField tarjetaedad;

    @FXML
    private TextField ocupacionedad;

    @FXML
    private TextField cargoedad;

    @FXML
    private ComboBox<String> estadoedad;

    //pantalla de eliminacion de datos Administrador
    @FXML
    private ScrollPane ptablaelad;

    @FXML
    private TableView<Administrador> TablaAdministradorel;

    @FXML
    private TextField idelad;

    @FXML
    private TextField nombreelad;

    @FXML
    private TextField contraelad;

    @FXML
    private TextField apellidoelad;

    @FXML
    private TextField tarjetaelad;

    @FXML
    private TextField ocupacionelad;

    @FXML
    private TextField cargoelad;

    @FXML
    private ComboBox<String> estadoel;

    @FXML
    private AnchorPane ancho;

    //pantalla delos enlaces 
    @FXML
    private TableView<Enlace> enlace;

    @FXML
    private TableView<Usuario> atnuen;

    @FXML
    private TextField idenlace;

    @FXML
    private TextField idenlaceatleta;

    @FXML
    private TextField idenlaceentrenador;

    @FXML
    private TextField idenlacenutriologo;

    @FXML
    private TextField nombreatleta;

    @FXML
    private TextField nombreentrenador;

    @FXML
    private TextField nombrenutriologo;

    @FXML
    private void nonumeroycaracteresespeciales(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(nombrein) || evt.equals(apellidoin) || evt.equals(disiplinain)) {
            //no deja ingresar numeros
            if (event.getCharacter().matches("\\d")) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("No se pueden ingresar Numeros");
                alert.showAndWait();
            }
        }
        if (evt.equals(nombrein) || evt.equals(apellidoin) || evt.equals(categoriain) || evt.equals(disiplinain) || evt.equals(direccionin)) {
            //no deja ingresar lo que sea distindo de a-z A-Z y espacio
            if (!event.getCharacter().matches("[a-zA-Z ]") && !event.getCharacter().matches("\\d") && !Character.isWhitespace(event.getCharacter().charAt(0)) && !Character.isISOControl(event.getCharacter().charAt(0))) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("No se pueden ingresar Caracteres especiales");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void tp(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(tamañoin) || evt.equals(tamañoed) || evt.equals(tamañoel) || evt.equals(pesoin) || evt.equals(pesoed) || evt.equals(pesoel)) {
            //no deja ingresar letras
            if (event.getCharacter().matches("[a-zA-Z]")) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("No se pueden ingresar Letras");
                alert.showAndWait();
            } //no deja ingresar espacios
            else if (event.getCharacter().matches(" ")) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("No se pueden ingresar Espacios");
                alert.showAndWait();
            } //no deja ingresar caracteres especiales excepto la tecla de retroceso
            else if (!event.getCharacter().matches("[a-zA-Z0-9 \b]")) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("No se pueden ingresar Caracteres especiales");
                alert.showAndWait();
            }
        }
    }

    //accion para menubar/insertar/datos/atleta
    @FXML
    private void handleInatAction(ActionEvent event) throws SQLException {

        objetoatleta.MostrarAtletas(TablaAtletas);

        ptablainat.setVisible(true);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/editar/datos/atleta
    @FXML
    private void handleEdatAction(ActionEvent event) throws SQLException {

        objetoatleta.MostrarAtletas(TablaAtletased);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(true);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);

    }

    //accion para menubar/eliminar/datos/atleta
    @FXML
    private void handleElatAction(ActionEvent event) throws SQLException {

        objetoatleta.MostrarAtletas(TablaAtletasel);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(true);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/insertar/datos/entrenador
    @FXML
    private void insertaren(ActionEvent event) throws SQLException {

        objetoentrenador.MostrarEntrenador(TablaEntrenadorin);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(true);
        ptablaeden.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/editar/datos/entrenador
    @FXML
    private void ediatarren(ActionEvent event) throws SQLException {

        objetoentrenador.MostrarEntrenador(TablaEntrenadored);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(true);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);

    }

    //accion para menubar/eliminar/datos/entrenador
    @FXML
    private void eliminaren(ActionEvent event) throws SQLException {

        objetoentrenador.MostrarEntrenador(TablaEntrenadorel);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(true);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);

    }

    //accion para menubar/ingresar/datos/nutriologo
    @FXML
    private void insertarnu(ActionEvent event) throws SQLException {

        objetonutriologo.MostrarNutriologo(TablaNutriologoin);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(true);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/editar/datos/nutriologo
    @FXML
    private void editarnu(ActionEvent event) throws SQLException {

        objetonutriologo.MostrarNutriologo(TablaNutriologoed);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(true);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/eliminar/datos/nutriologo
    @FXML
    private void eliminarnu(ActionEvent event) throws SQLException {

        objetonutriologo.MostrarNutriologo(TablaNutriologoel);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(true);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/ingresar/datos/administrador
    @FXML
    private void insertarad(ActionEvent event) throws SQLException {

        objetoadministrador.MostrarAdministrador(TablaAdministrador);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(true);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/editar/datos/administrador
    @FXML
    private void editarad(ActionEvent event) throws SQLException {

        objetoadministrador.MostrarAdministrador(TablaAdministradored);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(true);
        ptablaelad.setVisible(false);
        ancho.setVisible(false);
    }

    //accion para menubar/eliminar/datos/administrador
    @FXML
    private void eliminarad(ActionEvent event) throws SQLException {

        objetoadministrador.MostrarAdministrador(TablaAdministradorel);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(true);
        ancho.setVisible(false);

    }

    @FXML
    private void enlace(ActionEvent event) throws SQLException {

        objetoenlace.mostrarDatos(enlace);
        usuario.mostrarDatos(atnuen);

        ptablainat.setVisible(false);
        ptablaedat.setVisible(false);
        ptablaelat.setVisible(false);
        ptablainen.setVisible(false);
        ptablaeden.setVisible(false);
        ptablaelen.setVisible(false);
        ptablainnu.setVisible(false);
        ptablaednu.setVisible(false);
        ptablaelnu.setVisible(false);
        ptablainad.setVisible(false);
        ptablaedad.setVisible(false);
        ptablaelad.setVisible(false);
        ancho.setVisible(true);
    }

    @FXML
    private void handleSalirAction(ActionEvent event) {
        // Aquí finalizas el programa
        System.exit(0);
    }

    //accin del boton ingresar atleta
    @FXML
    private void ingresarat(ActionEvent event) {
        objetoatleta.agregarAtleta(contrain, tarjetain, nombrein, apellidoin, ocupacionin, tamañoin, pesoin, edadin, categoriain, disiplinain, direccionin, correoin, fechain);
        TablaAtletas.getItems().clear();
        objetoatleta.MostrarAtletas(TablaAtletas);

    }

    //accion del boton editar atleta
    @FXML
    private void editaratleta(ActionEvent event) {
        objetoatleta.ModificarAtleta(ided, contraed, tarjetaed, nombreed, apellidoed, ocupacioed, tamañoed, pesoed, edaded, categoriaed, disiplinaed, direccioned, correoed, fechaed);
        TablaAtletased.getItems().clear();
        objetoatleta.MostrarAtletas(TablaAtletased);
    }

    //accion del boton eliminar atleta
    @FXML
    private void eliminaratleta(ActionEvent event) {
        objetoatleta.EliminarAtleta(idel);
        TablaAtletasel.getItems().clear();
        objetoatleta.MostrarAtletas(TablaAtletasel);
    }

    //accion del boton ingresar entrenador
    @FXML
    private void insertarentrenador(ActionEvent event) {
        objetoentrenador.AgregarEntrenador(contrainen, tarjetainen, nombreinen, apellidoinen, ocupacioninen, edadinen, direccioninen, correoinen, fechainen);
        TablaEntrenadorin.getItems().clear();
        objetoentrenador.MostrarEntrenador(TablaEntrenadorin);
    }

    //accion del boton editar entrenador
    @FXML
    private void editarentrenador(ActionEvent event) {
        objetoentrenador.ModificarEntrenadr(ideden, contraeden, tarjetaeden, nombreeden, apellidoeden, ocupacioneden, edadeden, direccioneden, correoeden, fechaeden);
        TablaEntrenadored.getItems().clear();
        objetoentrenador.MostrarEntrenador(TablaEntrenadored);
    }

    //accion del boton eliminar entrenador
    @FXML
    private void eliminarentrenador(ActionEvent event) {
        objetoentrenador.EliminarEntrenador(idelen);
        TablaEntrenadorel.getItems().clear();
        objetoentrenador.MostrarEntrenador(TablaEntrenadorel);
    }

    //accion del boton insertar nutriologo 
    @FXML
    private void insertarnutriologo(ActionEvent event) {
        objetonutriologo.AgregarNutriologo(contrainnu, tarjetainnu, nombreinnu, apellidoinnu, ocupacioninnu, edadinnu, direccioninnu, correoinnu, fechainnu);
        TablaNutriologoin.getItems().clear();
        objetonutriologo.MostrarNutriologo(TablaNutriologoin);
    }

    //accion del boton editar nutriologo 
    @FXML
    private void editarnutriologo(ActionEvent event) {
        objetonutriologo.ModificarNutriologo(idednu, contraednu, tarjetaednu, nombreednu, apellidoednu, ocupacionednu, edadednu, direccionednu, correoednu, fechaednu);
        TablaNutriologoed.getItems().clear();
        objetonutriologo.MostrarNutriologo(TablaNutriologoed);
    }

    //accion del boton editar nutriologo 
    @FXML
    private void eliminarnutriologo(ActionEvent event) {
        objetonutriologo.EliminarNutriologo(ided);
        TablaNutriologoel.getItems().clear();
        objetonutriologo.MostrarNutriologo(TablaNutriologoel);
    }

    //accion del boton insertar Administrador 
    @FXML
    private void insertaradministrador(ActionEvent event) {
        objetoadministrador.AgregarAdministrador(contrainad, tarjetainad, nombreinad, apellidoinad, ocupacioninad, cargoinad, estado);
        TablaAdministrador.getItems().clear();
        objetoadministrador.MostrarAdministrador(TablaAdministrador);
    }

    //accion del boton editar Administrador 
    @FXML
    private void editaradministrador(ActionEvent event) {
        objetoadministrador.ModificarAdministrador(idedad, contraedad, tarjetaedad, nombreedad, apellidoedad, ocupacionedad, cargoedad, estadoedad);
        TablaAdministradored.getItems().clear();
        objetoadministrador.MostrarAdministrador(TablaAdministradored);
    }

    //accion del boton eliminar Administrador 
    @FXML
    private void eliminaradministrador(ActionEvent event) {
        objetoadministrador.EliminarAdministrador(idelad);
        TablaAdministradorel.getItems().clear();
        objetoadministrador.MostrarAdministrador(TablaAdministradorel);
    }
    
    //accion del boton agregar Enlace 
    @FXML
    private void ingresarenlace(ActionEvent event) {
        objetoenlace.agregarunion(idenlaceatleta, idenlaceentrenador, idenlacenutriologo);
        enlace.getItems().clear();
        objetoenlace.mostrarDatos(enlace);
    }
    
    //accion del boton editar Enlace 
    @FXML
    private void editarenlace(ActionEvent event) {
        objetoenlace.editarunion(idenlace, idenlaceatleta, idenlaceentrenador, idenlacenutriologo);
        enlace.getItems().clear();
        objetoenlace.mostrarDatos(enlace);
    }
    
    //accion del boton eliminar Enlace 
    @FXML
    private void eliminarenlace(ActionEvent event) {
        objetoenlace.eliminarunion(idenlace);
        enlace.getItems().clear();
        objetoenlace.mostrarDatos(enlace);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (admin != null) {
            String nom = admin.getNombres();
            bienvenido.setText("Bienvenido " + nom);
        } else {
            // manejar el caso en que admin sea nulo
            bienvenido.setText("Bienvenido ");
        }
        objetoatleta.seleccionarAtleta(TablaAtletased, ided, contraed, tarjetaed, nombreed, apellidoed, ocupacioed, tamañoed, pesoed, edaded, categoriaed, disiplinaed, direccioned, correoed, fechaed);
        objetoatleta.seleccionarAtleta(TablaAtletasel, idel, contrael, tarjetael, nombreel, apellidoel, ocupacioel, tamañoel, pesoel, edadel, categoriael, disiplinael, direccionel, correoel, fechael);
        objetoentrenador.SeleccionarEntrenador(TablaEntrenadored, ideden, contraeden, tarjetaeden, nombreeden, apellidoeden, ocupacioneden, edadeden, direccioneden, correoeden, fechaeden);
        objetoentrenador.SeleccionarEntrenador(TablaEntrenadorel, idelen, contraelen, tarjetaelen, nombreelen, apellidoelen, ocupacionelen, edadelen, direccionelen, correoelen, fechaelen);
        objetonutriologo.SeleccionarNutriologo(TablaNutriologoed, idednu, contraednu, tarjetaednu, nombreednu, apellidoednu, ocupacionednu, edadednu, direccionednu, correoednu, fechaednu);
        objetonutriologo.SeleccionarNutriologo(TablaNutriologoel, idelnu, contraelnu, tarjetaelnu, nombreelnu, apellidoelnu, ocupacionelnu, edadelnu, direccionelnu, correoelnu, fechaelnu);
        objetoadministrador.SeleccionarAdministrador(TablaAdministradored, idedad, contraedad, tarjetaedad, nombreedad, apellidoedad, ocupacionedad, cargoedad, estadoedad);
        objetoadministrador.SeleccionarAdministrador(TablaAdministradorel, idelad, contraelad, tarjetaelad, nombreelad, apellidoelad, ocupacionelad, cargoelad, estadoel);
        enlace.setOnMouseClicked(event -> {
            objetoenlace.seleccionarFila(enlace, idenlace, idenlaceatleta, idenlaceentrenador,
                    idenlacenutriologo, nombreatleta, nombreentrenador, nombrenutriologo);
        });
        
        atnuen.setOnMouseClicked(event -> {
            usuario.seleccionarFila(atnuen, idenlaceatleta, idenlaceentrenador, idenlacenutriologo, nombreatleta, nombreentrenador, nombrenutriologo);
        });

        ObservableList<String> list = FXCollections.observableArrayList("Activo", "Inactivo");
        estado.setItems(list);
        estadoedad.setItems(list);
        estadoel.setItems(list);

    }

}
