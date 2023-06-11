package controller;

import clasesymetodos.Atleta;
import clasesymetodos.Dietas;
import clasesymetodos.Rutina;
import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import proyect.Conexion;

/**
 * FXML Controller class
 *
 * @author David Ronald Calle Blanco
 */
public class PantallaatletaController implements Initializable {

    @FXML
    private Label bienvenidoLabel;

    private Atleta at;

    public PantallaatletaController() {
    }

    public PantallaatletaController(Atleta at) {
        this.at = at;
    }

    // Setter para el atleta
    public void setAtleta(Atleta at) {
        this.at = at;
    }

    @FXML
    private ScrollPane rutina;

    @FXML
    private ScrollPane dieta;


    @FXML
    private TableView<Rutina> tablarutina;

    @FXML
    private TableView<Dietas> tabladieta;

    @FXML
    private WebView visualizacionrutina;
    
    @FXML
    private WebView visualizaciondieta;

    public void generarPDF(Atleta at) {
        try {
            // Crea un objeto Conexion y obtén una conexión a la base de datos
            Conexion objetoconexion = new Conexion();
            Connection conn = objetoconexion.EstablecerConeccion();
            // Define los valores de nombre y contraseña
            String nombre = at.getNombres();
            String contraseña = at.getContraseña();

            // Crea un objeto Statement para ejecutar la consulta SQL
            Statement st = conn.createStatement();

            // Ejecuta la consulta SQL y almacena los resultados en un ResultSet
            String query = "SELECT id_entrenador,id_atleta,tipo,fecha_asignacion,comentario  FROM Rutina "
                    + "WHERE id_atleta = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? or tarjetadeacceso = ?)) "
                    + "ORDER BY fecha_asignacion DESC LIMIT 1;";
            PreparedStatement ps = conn.prepareStatement(query);

            // Establece los valores de los parámetros de la consulta SQL
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            ResultSet rs = ps.executeQuery();

            // Crea un nuevo documento PDF
            PDDocument document = new PDDocument();

            // Define las dimensiones de la página en puntos (1 cm = 28.3465 puntos)
            float width = 13 * 28.3465f;
            float height = 13 * 28.3465f;
            PDRectangle pageSize = new PDRectangle(width, height);

            // Crea una nueva página con las dimensiones especificadas
            PDPage page = new PDPage(pageSize);
            document.addPage(page);

            // Carga la imagen desde un archivo
            //poner la ruta de una imagen que se quiera poner de fondo
            PDImageXObject image = PDImageXObject.createFromFile("C:/Users/Desktop/Downloads/galaxia.jpg", document);

            // Obtiene el tamaño de la página
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            // Calcula la posición y el tamaño de la imagen
            float imageWidth = pageWidth;
            float imageHeight = image.getHeight() * (imageWidth / image.getWidth());
            float x = 0;
            float y = (pageHeight - imageHeight) / 2;

            // Crea un nuevo stream de contenido para agregar al PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Dibuja la imagen en el PDF
            contentStream.drawImage(image, x, y, imageWidth, imageHeight);

            // Define la fuente y el tamaño del texto
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);

            // Cambia el color del texto a blanco
            contentStream.setNonStrokingColor(Color.WHITE);

            // Agrega el contenido del ResultSet al PDF
            while (rs.next()) {
                // Obtén los datos de cada columna del ResultSet

                String data1 = rs.getString("id_entrenador");
                String data2 = rs.getString("id_atleta");
                String data3 = rs.getString("tipo");
                String data4 = rs.getString("fecha_asignacion");
                String data5 = rs.getString("comentario");

                // Agrega los datos al PDF
                contentStream.beginText();
                contentStream.newLineAtOffset(5, 100);
                contentStream.showText(data1 + " " + data2 + " " + data3 + " " + data4 + " " + data5);
                contentStream.newLine();
                contentStream.endText();
            }

            // Cierra el stream de contenido y guarda el documento PDF
            contentStream.close();
            document.save("C:/Users/Desktop/Downloads/Nueva/rutina.pdf");
            document.close();
        } catch (Exception e) {
            // Maneja la excepción aquí
            e.printStackTrace();
        }
    }

    public void previsualizarPDF() throws IOException {
        // Carga el archivo PDF
        File file = new File("C:/Users/Desktop/Downloads/Nueva/rutina.pdf");
        PDDocument document = PDDocument.load(file);

        // Convierte la primera página del PDF a una imagen
        PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage image = renderer.renderImage(0);

        // Guarda la imagen en un archivo temporal
        File tempFile = File.createTempFile("pdf", ".png");
        ImageIO.write(image, "PNG", tempFile);

        // Carga la imagen en el WebView
        visualizacionrutina.getEngine().load(tempFile.toURI().toString());

        // Cierra el documento PDF
        document.close();
    }

    public void generarPDFd(Atleta at) {
        try {
            // Crea un objeto Conexion y obtén una conexión a la base de datos
            Conexion objetoconexion = new Conexion();
            Connection conn = objetoconexion.EstablecerConeccion();
            // Define los valores de nombre y contraseña
            String nombre = at.getNombres();
            String contraseña = at.getContraseña();

            // Crea un objeto Statement para ejecutar la consulta SQL
            Statement st = conn.createStatement();

            // Ejecuta la consulta SQL y almacena los resultados en un ResultSet
            String query = "SELECT id_nutriologo,id_atleta,tipo,fecha_asignacion,comentario  FROM Dieta "
                    + "WHERE id_atleta = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? or tarjetadeacceso = ?)) "
                    + "ORDER BY fecha_asignacion DESC LIMIT 1;";
            PreparedStatement ps = conn.prepareStatement(query);

            // Establece los valores de los parámetros de la consulta SQL
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, contraseña);

            ResultSet rs = ps.executeQuery();

            // Crea un nuevo documento PDF
            PDDocument document = new PDDocument();

            // Define las dimensiones de la página en puntos (1 cm = 28.3465 puntos)
            float width = 13 * 28.3465f;
            float height = 13 * 28.3465f;
            PDRectangle pageSize = new PDRectangle(width, height);

            // Crea una nueva página con las dimensiones especificadas
            PDPage page = new PDPage(pageSize);
            document.addPage(page);

            // Carga la imagen desde un archivo
            PDImageXObject image = PDImageXObject.createFromFile("C:/Users/Desktop/Downloads/galaxia.jpg", document);

            // Obtiene el tamaño de la página
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            // Calcula la posición y el tamaño de la imagen
            float imageWidth = pageWidth;
            float imageHeight = image.getHeight() * (imageWidth / image.getWidth());
            float x = 0;
            float y = (pageHeight - imageHeight) / 2;

            // Crea un nuevo stream de contenido para agregar al PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Dibuja la imagen en el PDF
            contentStream.drawImage(image, x, y, imageWidth, imageHeight);

            // Define la fuente y el tamaño del texto
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);

            // Cambia el color del texto a blanco
            contentStream.setNonStrokingColor(Color.WHITE);

            // Agrega el contenido del ResultSet al PDF
            while (rs.next()) {
                // Obtén los datos de cada columna del ResultSet

                String data1 = rs.getString("id_nutriologo");
                String data2 = rs.getString("id_atleta");
                String data3 = rs.getString("tipo");
                String data4 = rs.getString("fecha_asignacion");
                String data5 = rs.getString("comentario");

                // Agrega los datos al PDF
                contentStream.beginText();
                contentStream.newLineAtOffset(5, 100);
                contentStream.showText(data1 + " " + data2 + " " + data3 + " " + data4 + " " + data5);
                contentStream.newLine();
                contentStream.endText();
            }

            // Cierra el stream de contenido y guarda el documento PDF
            contentStream.close();
            document.save("C:/Users/Desktop/Downloads/Nueva/dieta.pdf");
            document.close();
        } catch (Exception e) {
            // Maneja la excepción aquí
            e.printStackTrace();
        }
    }
    
    public void previsualizarPDFd() throws IOException {
        // Carga el archivo PDF
        File file = new File("C:/Users/Desktop/Downloads/Nueva/dieta.pdf");
        PDDocument document = PDDocument.load(file);

        // Convierte la primera página del PDF a una imagen
        PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage image = renderer.renderImage(0);

        // Guarda la imagen en un archivo temporal
        File tempFile = File.createTempFile("pdf", ".png");
        ImageIO.write(image, "PNG", tempFile);

        // Carga la imagen en el WebView
        visualizaciondieta.getEngine().load(tempFile.toURI().toString());

        // Cierra el documento PDF
        document.close();
    }
    

    @FXML
    private void botonrutina(ActionEvent event) throws SQLException, IOException {
        generarPDF(at);
        previsualizarPDF();
    }

    @FXML
    private void botondieta(ActionEvent event) throws SQLException, IOException {
        generarPDF(at);
        previsualizarPDF();
    }

    //menubar /rutina y dietas/rutina
    @FXML
    private void mrutina(ActionEvent event) throws SQLException {
        rutina.setVisible(true);
        dieta.setVisible(false);
        mostrarRutinas(at);
    }

    //menubar /rutina y dietas/dietas
    @FXML
    private void mdieta(ActionEvent event) throws SQLException {
        rutina.setVisible(false);
        dieta.setVisible(true);
        mostrarDietas(at);
    }

    private void mostrarRutinas(Atleta at) throws SQLException {
        // Crea un objeto Conexion y obtén una conexión a la base de datos
        Conexion objetoconexion = new Conexion();
        Connection conn = objetoconexion.EstablecerConeccion();
        // Define los valores de nombre y contraseña
        String nombre = at.getNombres();
        String contraseña = at.getContraseña();

        // Crea un objeto Statement para ejecutar la consulta SQL
        Statement st = conn.createStatement();

        // Ejecuta la consulta SQL y almacena los resultados en un ResultSet
        String query = "SELECT id_entrenador,id_atleta,tipo,fecha_asignacion,comentario  FROM Rutina "
                + "WHERE id_atleta = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? or tarjetadeacceso = ?)) "
                + "ORDER BY fecha_asignacion ;";
        PreparedStatement ps = conn.prepareStatement(query);

        // Establece los valores de los parámetros de la consulta SQL
        ps.setString(1, nombre);
        ps.setString(2, contraseña);
        ps.setString(3, contraseña);

        ResultSet rs = ps.executeQuery();

        // Crea una lista observable para almacenar los datos del ResultSet
        ObservableList<Rutina> data = FXCollections.observableArrayList();

        // Agrega el contenido del ResultSet a la lista observable
        while (rs.next()) {
            // Obtén los datos de cada columna del ResultSet
            int idEntrenador = rs.getInt("id_entrenador");
            int idAtleta = rs.getInt("id_atleta");
            String tipo = rs.getString("tipo");
            Date fechaAsignacion = rs.getDate("fecha_asignacion");
            String comentario = rs.getString("comentario");

            // Crea un nuevo objeto Rutina y agrega los datos a sus propiedades
            Rutina rutina = new Rutina();
            rutina.setId_entrenador(idEntrenador);
            rutina.setId_atleta(idAtleta);
            rutina.setTipo(tipo);
            rutina.setFecha_asignacion(fechaAsignacion);
            rutina.setComentario(comentario);

            // Agrega el objeto Rutina a la lista observable
            data.add(rutina);
        }

        // Crea las columnas del TableView y define cómo se mostrarán los datos
        TableColumn<Rutina, Integer> colIdEntrenador = new TableColumn<>("ID Entrenador");
        colIdEntrenador.setCellValueFactory(new PropertyValueFactory<>("id_entrenador"));

        TableColumn<Rutina, Integer> colIdAtleta = new TableColumn<>("ID Atleta");
        colIdAtleta.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));

        TableColumn<Rutina, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Rutina, Date> colFechaAsignacion = new TableColumn<>("Fecha Asignación");
        colFechaAsignacion.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion"));

        TableColumn<Rutina, String> colComentario = new TableColumn<>("Comentario");
        colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

        // Agrega las columnas al TableView
        tablarutina.getColumns().addAll(colIdEntrenador, colIdAtleta, colTipo, colFechaAsignacion, colComentario);

        // Establece los datos del TableView
        tablarutina.setItems(data);
    }

    private void mostrarDietas(Atleta at) throws SQLException {
        // Crea un objeto Conexion y obtén una conexión a la base de datos
        Conexion objetoconexion = new Conexion();
        Connection conn = objetoconexion.EstablecerConeccion();
        // Define los valores de nombre y contraseña
        String nombre = at.getNombres();
        String contraseña = at.getContraseña();

        // Crea un objeto Statement para ejecutar la consulta SQL
        Statement st = conn.createStatement();

        // Ejecuta la consulta SQL y almacena los resultados en un ResultSet
        String query = "SELECT id_nutriologo,id_atleta,tipo,fecha_asignacion,comentario  FROM Dieta "
                + "WHERE id_atleta = (SELECT id FROM Usuario WHERE nombres = ? AND (contraseña = ? or tarjetadeacceso = ?)) "
                + "ORDER BY fecha_asignacion ;";
        PreparedStatement ps = conn.prepareStatement(query);

        // Establece los valores de los parámetros de la consulta SQL
        ps.setString(1, nombre);
        ps.setString(2, contraseña);
        ps.setString(3, contraseña);

        ResultSet rs = ps.executeQuery();

        // Crea una lista observable para almacenar los datos del ResultSet
        ObservableList<Dietas> data = FXCollections.observableArrayList();

        // Agrega el contenido del ResultSet a la lista observable
        while (rs.next()) {
            // Obtén los datos de cada columna del ResultSet
            int idNutriologo = rs.getInt("id_nutriologo");
            int idAtleta = rs.getInt("id_atleta");
            String tipo = rs.getString("tipo");
            Date fechaAsignacion = rs.getDate("fecha_asignacion");
            String comentario = rs.getString("comentario");

            // Crea un nuevo objeto Dieta y agrega los datos a sus propiedades
            Dietas dieta = new Dietas();
            dieta.setId_nutriologo(idNutriologo);
            dieta.setId_atleta(idAtleta);
            dieta.setTipo(tipo);
            dieta.setFecha_asignacion(fechaAsignacion);
            dieta.setComentario(comentario);

            // Agrega el objeto Dieta a la lista observable
            data.add(dieta);
        }

        // Crea las columnas del TableView y define cómo se mostrarán los datos
        TableColumn<Dietas, Integer> colIdNutriologo = new TableColumn<>("ID Nutriólogo");
        colIdNutriologo.setCellValueFactory(new PropertyValueFactory<>("id_nutriologo"));

        TableColumn<Dietas, Integer> colIdAtleta = new TableColumn<>("ID Atleta");
        colIdAtleta.setCellValueFactory(new PropertyValueFactory<>("id_atleta"));

        TableColumn<Dietas, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Dietas, Date> colFechaAsignacion = new TableColumn<>("Fecha Asignación");
        colFechaAsignacion.setCellValueFactory(new PropertyValueFactory<>("fecha_asignacion"));

        TableColumn<Dietas, String> colComentario = new TableColumn<>("Comentario");
        colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

        // Agrega las columnas al TableView
        tabladieta.getColumns().addAll(colIdNutriologo, colIdAtleta, colTipo, colFechaAsignacion, colComentario);

        // Establece los datos del TableView
        tabladieta.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (at != null) {
            String nom = at.getNombres();
            bienvenidoLabel.setText("Bienvenido " + nom);
        } else {
            // manejar el caso en que admin sea nulo
            bienvenidoLabel.setText("Bienvenido ");
        }

    }

}
