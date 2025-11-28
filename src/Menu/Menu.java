package Menu;
import Enum.MonedasDisponibles;
import Servicie.MonedasService;
import Servicie.impl.ConversorDeMoneda;

import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("*********** Bienvenido al Conversor de monedas ***********");
        while (true) {
            MonedasDisponibles base = MonedasService.selectBase();
            if (base == null) break; // usuario eligió salir

            MonedasDisponibles target = MonedasService.selectTarget(base);
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
