package Servicie.impl;

import Enteties.Moneda;
import Servicie.ConsumoAPI;
import Servicie.IConversorDeMoneda;
import com.google.gson.Gson;

import java.math.RoundingMode;

public class ConversorDeMoneda implements IConversorDeMoneda {
     private String apiKey = "2b9be1c13c0f0d3fb60ce9a6";
     private static final String URL_BASE ="https://v6.exchangerate-api.com/v6/";

    @Override
    public void convertir(String base, String target, double monto) {
        ConsumoAPI consumoAPI = new ConsumoAPI();
        String url = URL_BASE + apiKey + "/pair/" + base + "/" + target + "/" + monto;
        var json = consumoAPI.obtenerDatos(url);
        Moneda moneda = new Gson().fromJson(json, Moneda.class);
        DevolverRespuesta(moneda,monto);
    }

    private void DevolverRespuesta(Moneda moneda, double monto) {
        String respuesta = "El valor " + moneda.base_code() +"["+monto +"]," +
                " corresponde al valor final de =>>> " + moneda.conversion_result().setScale(2, RoundingMode.HALF_UP) +"[" +
                moneda.target_code()+"]";
        System.out.println(respuesta);
    }
}
