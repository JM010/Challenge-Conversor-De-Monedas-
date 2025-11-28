package Servicie;
import Enum.MonedasDisponibles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MonedasService {
    private static MonedasDisponibles[] monedas = MonedasDisponibles.values();
    private static Scanner sc = new Scanner(System.in);

    // Selección genérica a partir de una lista de opciones. Devuelve null si se elige la opción de salir/volver.
    private static MonedasDisponibles selectFromList(List<MonedasDisponibles> opciones, String titulo, String opcionFinalLabel) {
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println(titulo);
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, opciones.get(i).getDescripcion());
            }
            System.out.printf("%d) %s\n", opciones.size() + 1, opcionFinalLabel);
            System.out.print("Ingrese el número de la opción: ");

            String line = sc.nextLine().trim();
            int opcion;
            try {
                opcion = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.\n");
                continue;
            }

            if (opcion == opciones.size() + 1) return null; // salir / volver
            if (opcion < 1 || opcion > opciones.size()) {
                System.out.println("Opción fuera de rango. Intente de nuevo.\n");
                continue;
            }

            return opciones.get(opcion - 1);
        }

    }
    // Muestra las monedas y permite seleccionar la base. Devuelve null si elige salir.
    public static MonedasDisponibles selectBase() {
        List<MonedasDisponibles> lista = Arrays.asList(monedas);
        return selectFromList(lista, "Elija la moneda base:", "Salir");
    }

    // Muestra las monedas (excluyendo la base) y permite seleccionar el target. Devuelve null si elige salir.
    public static MonedasDisponibles selectTarget(MonedasDisponibles base) {
        List<MonedasDisponibles> disponibles = listaDeMonedas(base.name());
        return selectFromList(disponibles, "Elija la moneda a la que desea convertir:", "Volver / Salir");
    }

    // Método que retorna las monedas disponibles
    private static List<MonedasDisponibles> listaDeMonedas(String base) {
        List<MonedasDisponibles> monedasDisponibles = new ArrayList<>();
        for (MonedasDisponibles moneda : MonedasDisponibles.values()){
            if (base.equals(moneda.name())) {
                continue;
            }
            monedasDisponibles.add(moneda);
        }
        return monedasDisponibles;
    }
}
