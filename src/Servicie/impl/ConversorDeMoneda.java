package Servicie.impl;

import Enteties.Moneda;
import Servicie.IConversorDeMoneda;
import com.google.gson.Gson;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorDeMoneda implements IConversorDeMoneda {
     private String apiKey = "2b9be1c13c0f0d3fb60ce9a6";

    @Override
    public void convertir(String base, String target, double monto) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"
        + base + "/" + target + "/" +monto);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            Moneda moneda = new Gson().fromJson(response.body(), Moneda.class);
            DevolverRespuesta(moneda,monto);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se ha podido hacer la conversion");
        }

    }

    private void DevolverRespuesta(Moneda moneda, double monto) {
        String respuesta = "El valor " + moneda.base_code() +"["+monto +"]," +
                " corresponde al valor final de =>>> " + moneda.conversion_result().setScale(2, RoundingMode.HALF_UP) +"[" +
                moneda.target_code()+"]";
        System.out.println(respuesta);
        System.out.println("");
    }
}
