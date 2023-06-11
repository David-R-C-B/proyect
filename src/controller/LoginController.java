package controller;

import clasesymetodos.Administrador;
import clasesymetodos.Atleta;
import clasesymetodos.Entrenador;
import clasesymetodos.Nutriologo;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import proyect.Conexion;

/**
 * FXML Controller class
 *
 * @author David Ronald Calle Blanco
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private void eventKey(KeyEvent event) {

        Object evt = event.getSource();
        if (evt.equals(txtPassword)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
    }

    @FXML
    private void eventAction(ActionEvent actionEvent) throws SQLException, IOException {
        String username = txtUser.getText();
        String password = txtPassword.getText();

        // Realizar la comprobación en la base de datos
        boolean nombreExiste = false;
        boolean contraseñaCorrecta = false;
        String ocupacion = "";

        // Establecer la conexión con la base de datos
        Connection connection = null;
        try {
            Conexion conexion = new Conexion();
            connection = conexion.EstablecerConeccion();

            // Comprobar si el nombre existe en la base de datos
            String queryNombre = "SELECT COUNT(*), ocupacion FROM Usuario WHERE nombres = ? GROUP BY ocupacion";
            try (PreparedStatement statement = connection.prepareStatement(queryNombre)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        nombreExiste = count > 0;
                        ocupacion = resultSet.getString("ocupacion");
                    }
                }
            }

            // Comprobar si la contraseña o tarjeta de acceso coinciden
            String queryContraseña = "SELECT COUNT(*) FROM Usuario WHERE nombres = ? AND (contraseña = ? OR tarjetadeacceso = ?)";
            try (PreparedStatement statement = connection.prepareStatement(queryContraseña)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        contraseñaCorrecta = count > 0;
                    }
                }
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Error al establecer la conexión con la base de datos");
            e.printStackTrace();
        } finally {
            // Cerrar la conexión con la base de datos
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Mostrar mensajes de acuerdo a los resultados
        if (nombreExiste && contraseñaCorrecta) {
            // El nombre y la contraseña son correctos

            // Realizar la acción correspondiente según la ocupación
            if (ocupacion.equals("Administrador")) {

                Administrador admin = new Administrador(username, password);
                String nombreAdmin = admin.getNombres();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cuadro de diálogo de bienvenida");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido " + nombreAdmin);
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pantallaadmin.fxml"));
                loader.setControllerFactory((Class<?> type) -> {
                    if (type == PantallaadminController.class) {
                        return new PantallaadminController(admin);
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow(); // Obtener la ventana actual
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else if (ocupacion.equals("Atleta")) {
                Atleta atleta = new Atleta(username, password);
                String nombreAtleta = atleta.getNombres();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cuadro de diálogo de bienvenida");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido " + nombreAtleta);
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pantallaatleta.fxml"));
                loader.setControllerFactory((Class<?> type) -> {
                    if (type == PantallaatletaController.class) {
                        return new PantallaatletaController(atleta);
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow(); // Obtener la ventana actual
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else if (ocupacion.equals("Entrenador")) {
                Entrenador entrenador = new Entrenador(username, password);
                String nombreEnt = entrenador.getNombres();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cuadro de diálogo de bienvenida");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido " + nombreEnt);
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pantallaentrenador.fxml"));
                loader.setControllerFactory((Class<?> type) -> {
                    if (type == PantallaentrenadorController.class) {
                        return new PantallaentrenadorController(entrenador);
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow(); // Obtener la ventana actual
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (ocupacion.equals("Nutriólogo")) {

                Nutriologo nutri = new Nutriologo(username, password);
                String nombreNut = nutri.getNombres();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cuadro de diálogo de bienvenida");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenido " + nombreNut);
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pantallanutriologo.fxml"));
                loader.setControllerFactory((Class<?> type) -> {
                    if (type == PantallanutriologoController.class) {
                        return new PantallanutriologoController(nutri);
                    } else {
                        try {
                            return type.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow(); // Obtener la ventana actual
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Ocupación desconocida, mostrar mensaje de error
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cuadro de diálogo de información");
                alert.setHeaderText("Ocupación desconocida");
                alert.setContentText(null);

                alert.showAndWait();
            }

        } else if (!nombreExiste) {
            // El nombre no existe
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cuadro de diálogo de información");
            alert.setHeaderText("Uusario incorrecto");
            alert.setContentText(null);
            alert.showAndWait();

        } else if (nombreExiste && !contraseñaCorrecta) {
            // La contraseña o tarjeta de acceso es incorrecta
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cuadro de diálogo de información");
            alert.setHeaderText("Contraseña incorrecta");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO

    }

}
