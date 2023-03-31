import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//En este caso en particular, se utiliza apilayer, es necesaria una contraseña que se puede generar desde su propia página
public class SolicitudAPI {

    public static ArrayList<Double> obtenermonedas(String transformacion) throws Exception {
        URL url = new URL("https://api.apilayer.com/currency_data/live?source=USD&currencies=" + transformacion);
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apikey", GetPass.get("API"));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        ArrayList<Double> valores = ModificadorJson.extractUSDValues(response.toString());
       
        return valores;
    }

}
