package Menu;
import Enum.MonedasDisponibles;
import Servicie.impl.ConversorDeMoneda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final MonedasDisponibles[] monedas = MonedasDisponibles.values();
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("*********** Bienvenido al Conversor de monedas ***********");
        while (true) {
            MonedasDisponibles base = selectBase();
            if (base == null) break; // usuario eligió salir

            MonedasDisponibles target = selectTarget(base);
            if (target == null) {
                // Volver a elegir base (o permitir salir)
                continue;
            }

            double monto = readAmount();
            ConversorDeMoneda conversor = new ConversorDeMoneda();
            conversor.convertir(base.name(), target.name(), monto);

            // Preguntar si desea realizar otra conversión
            System.out.print("\n¿Desea realizar otra conversión? (s/n): ");
            String otra = sc.nextLine().trim().toLowerCase();
            if (!otra.equals("s") && !otra.equals("si")) {
                break;
            }
        }
        System.out.println("Saliendo del sistema....");
        // No cerramos Scanner si la aplicación puede seguir usándolo externamente,
        // pero como es el final del flujo, lo cerramos.
        sc.close();
    }

    // Selección genérica a partir de una lista de opciones. Devuelve null si se elige la opción de salir/volver.
    private MonedasDisponibles selectFromList(List<MonedasDisponibles> opciones, String titulo, String opcionFinalLabel) {
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
    private MonedasDisponibles selectBase() {
        List<MonedasDisponibles> lista = Arrays.asList(monedas);
        return selectFromList(lista, "Elija la moneda base:", "Salir");
    }

    // Muestra las monedas (excluyendo la base) y permite seleccionar el target. Devuelve null si elige salir.
    private MonedasDisponibles selectTarget(MonedasDisponibles base) {
        List<MonedasDisponibles> disponibles = listaDeMonedas(base.name());
        return selectFromList(disponibles, "Elija la moneda a la que desea convertir:", "Volver / Salir");
    }

    private List<MonedasDisponibles> listaDeMonedas(String base) {
        List<MonedasDisponibles> monedasDisponibles = new ArrayList<>();
        for (MonedasDisponibles moneda : MonedasDisponibles.values()){
            if (base.equals(moneda.name())) {
                continue;
            }
            monedasDisponibles.add(moneda);
        }
        return monedasDisponibles;
    }

    private double readAmount() {
        while (true) {
            System.out.print("\nIngrese el monto a convertir: ");
            String line = sc.nextLine().trim();
            double monto;
            try {
                monto = Double.parseDouble(line);
                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor que cero. Intente de nuevo.\n");
                    continue;
                }
                return monto;
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido. Ingrese un número (ej: 10 o 10.50).\n");
            }
        }
    }

}
