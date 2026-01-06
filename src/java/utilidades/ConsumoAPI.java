package utilidades;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumoAPI {

    private static final String URL_API = "http://sublimas.com.mx:8080/calculadora/api/envios/distancia/";

    public static Double obtenerDistancia(String cpOrigen, String cpDestino) {
        Double distancia = 0.0;
        try {
            String urlString = URL_API + cpOrigen.trim() + "," + cpDestino.trim();
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Error API Sublimas: HTTP " + conn.getResponseCode());
                return 0.0;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            br.close();

            JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();

            if (!json.get("error").getAsBoolean()) {
                distancia = json.get("distanciaKM").getAsDouble();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return distancia;
    }
}
