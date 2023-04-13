package src.API;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NutritionAPI {

    public NutritionAPI() {
        String date = "20220413"; // replace with your desired date in YYYYMMDD format
        String url = "http://www7.slv.se/apilivsmedel/LivsmedelService.svc/Livsmedel/Naringsvarde/" + date;

        try {
            // create a URL object with the API URL
            URL api = new URL(url);

            // create a HttpURLConnection object to send a request to the API
            HttpURLConnection conn = (HttpURLConnection) api.openConnection();
            conn.setRequestMethod("GET");

            // read the response from the API into an InputStream
            InputStream response = conn.getInputStream();

            // create a Scanner object to parse the XML
            Scanner scanner = new Scanner(response);
            scanner.useDelimiter("\\A");

            // read the response from the API into a String
            String xml = scanner.next();

            // print the response from the API
            System.out.println(xml);

            // handle any exceptions that may occur
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NutritionAPI();
    }
}
