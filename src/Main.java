import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear un nuevo marco con el título "YVG Conversor"
        JFrame frame = new JFrame("YVG Conversor");
        
        // Establecer el comportamiento de cierre del marco
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear una nueva conexión a la base de datos MySQL
        MySQLConnector conexion = new MySQLConnector();
        
        // Obtener la fecha actual
        String fechaActual = Fecha.fechaActual();
        
        // Obtener la fecha de la última actualización de la base de datos
        String fechaActualizacion = Modificador_BD.getFechaActualizacion(conexion);
        
        // Comprobar si la fecha actual es diferente a la fecha de actualización de la base de datos
        if (!fechaActual.equals(fechaActualizacion)) {
            // Si la fecha es diferente, actualizar la base de datos con la fecha actual
            Modificador_BD.actualizar(conexion, fechaActual);
        } else {
            // Si la fecha es la misma, imprimir un mensaje indicando que el sistema está actualizado
            System.out.println("Sistema actualizado");
        }
        
        // Establecer el icono del marco utilizando una imagen cargada desde el archivo sistema de archivos
        Image icon = new ImageIcon("../Conversor de monedas_Alura/resources/images/logo_png-02.png").getImage();
        frame.setIconImage(icon);
        
        // Crear una nueva vista de pantalla y agregarla al contenido del marco
        PantallaView pantallaView = new PantallaView();
        frame.getContentPane().add(pantallaView);
        
        // Establecer el tamaño del marco y mostrarlo en pantalla
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
