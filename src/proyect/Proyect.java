
package proyect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author David Ronald Calle Blanco
 */
public class Proyect extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        
        Scene scene = new Scene(root);
        //scene.setFill(Color.rgb(0, 0, 0));

        
        stage.setScene(scene);
        stage.show();
        
        
        stage.setTitle("Sistema del Centro de Alto Rendimiento"); // Establecer el título de todas las ventanas
        
        Image icon = new Image(getClass().getResourceAsStream("/imagenes/logo.jpg")); // Establecer el icono de la ventana
        stage.getIcons().add(icon);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
