import java.sql.*;
import java.util.ArrayList;

public class Modificador_BD {
	MySQLConnector conexion = new MySQLConnector();
	
public static  String getFechaActualizacion(MySQLConnector conexion) {
        
        String fechaActualizacion = null;
        
        try {
            conexion.openConnection();
            fechaActualizacion = conexion.getLastData("actualizacion_valor", "Fecha_Actualizacion");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        
        return fechaActualizacion;
    }

public static String obtenertabla(MySQLConnector conexion,String tabla) {
	try {
    	conexion.openConnection();
    	
		ResultSet resultSet = conexion.getData(tabla);
		ResultSetMetaData metadata = resultSet.getMetaData();
		int columnCount = metadata.getColumnCount();
		StringBuilder datos = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
                datos.append(resultSet.getString(i)).append("\t");
            }
            System.out.println();
            datos.append("\n");
        }
		
	} catch (SQLException e) {
		System.out.println("No fue posible obtener los datos de la tabla "+tabla);
		e.printStackTrace();
	}
    finally {
    	conexion.closeConnection();
    }
	return null;}

public static String[] obtenercolumna(MySQLConnector conexion, String tabla, String columna) {
    try {
        conexion.openConnection();
        String sql = "SELECT " + columna + " FROM " + tabla;
        Statement stmt;
        stmt = conexion.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);

        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
        }
        rs.beforeFirst();

        String[] datos = new String[rowCount];
        int i = 0;
        while (rs.next()) {
            datos[i] = rs.getString(columna);
            i++;
        }

        return datos;
    } catch (SQLException e) {
		System.out.println("No fue posible otener la columna "+columna+" De la tabla "+tabla);
		e.printStackTrace();
	} finally {
        conexion.closeConnection();
    }
    return null;
}
public static String obtenerElemento(MySQLConnector conexion, String tabla, String columna,String buscador)  {
	 try {
	        conexion.openConnection();
	        String sql = "SELECT " + columna + " FROM " + tabla + " where "+ " ID_Pais " + " = "+buscador ;
	        System.out.println(sql);
	        Statement stmt;
	        stmt = conexion.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = stmt.executeQuery(sql);
	        String ultimoDato = null;
			if (rs.last()) {
	            ultimoDato  = rs.getString(columna);
	            //System.out.println("El último dato de la columna es: " + ultimoDato); //TEST
	        }
	        rs.close();
	        stmt.close();
	        ultimoDato = ultimoDato.toLowerCase();
	        return ultimoDato;
	 }
	 catch (SQLException e) {
		 System.out.println("No fue posible recuperar el elemento de la tabla "+tabla);
			e.printStackTrace();
		}
finally {
   conexion.closeConnection();
}
	return null;

}

public static void actualizar(MySQLConnector conexion, String fechaActual) {
	try {
        conexion.openConnection();
        String[] Columna =Modificador_BD.obtenercolumna(conexion, "moneda", "Abreviacion");
        String transformacion = Columna[0];
        for(String elemento: Columna) {
        	transformacion += "%2C"+elemento;
        }
        ArrayList<Double> Valores = SolicitudAPI.obtenermonedas(transformacion);
        String sql = "INSERT INTO costo (ID_Costo,Valor_Costo) VALUES (?, ?) ON DUPLICATE KEY UPDATE Valor_Costo = ?";
        conexion.openConnection();
        PreparedStatement stmt = conexion.getConnection().prepareStatement(sql);
        for (int i = 0; i < Valores.size(); i++) { 
            stmt.setInt(1, i+1);
            stmt.setDouble(2, Valores.get(i));
            stmt.setDouble(3, Valores.get(i));
            stmt.executeUpdate();
        }
        Statement statement = conexion.getConnection().createStatement();
        statement.executeUpdate("INSERT INTO `actualizacion_valor`(`Fecha_Actualizacion`) VALUES ('" + fechaActual + "')");

	} catch (Exception e) {
		System.out.println("----No fue posible actualizar la base----");
		e.printStackTrace();
	}
	finally {
		   conexion.closeConnection();   
	}
}  


}
	
