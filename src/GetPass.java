import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetPass {
	static String archivo = "resources/pass.txt";
	
	public static String get(String string) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
		    String pass;
		    if ((pass = br.readLine()) != null) {
		    	return pass;
		        
		    }
		    
		    
		} 
		catch (IOException e) {
		    System.err.println("Error al recuperar contraseña " + e.getMessage());
		}
		return null;
	}
}
	
