import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Modifica_imagenes {
    
    // Método para obtener una imagen a partir de un texto
    public static ImageIcon obtenerImagen(String texto) {
        try {
            // Crear la URL con el texto dado
            String urlTexto = "https://flagcdn.com/256x192/" + texto + ".png";
            URL url = new URL(urlTexto);
            
            // Obtener la imagen en formato PNG
            BufferedImage imagen = ImageIO.read(url);
            
            // Transformar la imagen a un objeto ImageIcon
            ImageIcon imagenIcon = new ImageIcon(imagen);
            
            // Retornar la imagen
            return imagenIcon;
        }
        catch (IOException e ) {
            // Manejar la excepción de IO si la URL no puede recuperar una imagen
            System.out.println("No existe una imagen para este caso");
            
            // Devolver una imagen por defecto
            return new ImageIcon("../Conversor de monedas_Alura/resources/images/Espania.jpg");
        }
    }  
}
