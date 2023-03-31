import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {
    // Método que devuelve la fecha actual formateada en formato "yyyy-MM-dd"
    public static String fechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formatter);
    }

    // Método que obtiene la fecha del sistema a través de una conexión a la base de datos
    public static String sistemaFecha() {
        MySQLConnector conexion = new MySQLConnector();
        try {
            String fechaSistema = Modificador_BD.getFechaActualizacion(conexion);
            return fechaSistema;
        }
        catch (Exception e){
        	System.out.println("Fallo en la obtención de la fecha");
        	return null;
        }
    }
}
