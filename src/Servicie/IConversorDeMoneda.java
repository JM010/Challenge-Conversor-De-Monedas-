package Servicie;

import Enteties.Moneda;

import java.io.IOException;

public interface IConversorDeMoneda {

    void convertir(String base, String target, double monto) throws IOException, InterruptedException;

    void DevolverRespuesta (Moneda moneda, double monto);

}
