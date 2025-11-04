package Menu.impl;
import Menu.IMenu;
import Enum.MonedasDisponibles;
import Servicie.impl.ConversorDeMoneda;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements IMenu{
    String base;
    String target;
    MonedasDisponibles monedas[] = MonedasDisponibles.values();
    double monto;
    @Override
    public void start() {
        while (true) {
            printMenuPrincipal();
            if (base.isEmpty()) {
                System.out.println("Saliendo del sistema....");
                break;
            }
            printMenuSecondary(listaDeMonedas(base));
            if (target.isEmpty()) {
                System.out.println("Saliendo del sistema....");
                break;
            }
           conversion();

        }

    }


    @Override
    public void printMenuPrincipal() {
        System.out.println("*********** Bienvenido al Conversor de monedas ***********");
        int i = 1;
        for (MonedasDisponibles moneda : monedas) {
            System.out.println( i+")" + moneda.getDescripcion());
            i++;
        }
        System.out.println( i+") Salir");
        System.out.print("Elija la moneda base o " + i + " si desea salir: ");
        Scanner sc =  new Scanner(System.in);
        int opcion = sc.nextInt();
        if (opcion <= 0 || opcion > i) {
            System.out.println("Opcion incorrecta, Vuelva a intentarlo");
            printMenuPrincipal();
        } else if (opcion == i) {
            base = "";
        } else  {
            base = monedas[opcion-1].name();
        }
    }

    @Override
    public void printMenuSecondary(List<MonedasDisponibles> monedasDisponibles) {
        System.out.println("*********** Elija la moneda a la que desea convertir ***********");
        int i = 1;
        for (MonedasDisponibles moneda : monedasDisponibles) {
            System.out.println( i+")" + moneda.getDescripcion());
            i++;
        }
        System.out.println( i+") Salir");
        System.out.print("Elija la moneda a la que desea convertir o " + i + " si desea salir: ");
        Scanner sc =  new Scanner(System.in);
        int opcion = sc.nextInt();
        if (opcion <= 0 || opcion> i) {
            System.out.println("Opción incorrecta, vuelva a intentarlo");
            printMenuSecondary(monedasDisponibles);
        }  else if (opcion == i) {
            target= "";
        }else {
            target = monedasDisponibles.get(opcion-1).name();
        }

    }

    private List<MonedasDisponibles> listaDeMonedas ( String base) {
        List<MonedasDisponibles> monedasDisponibles = new ArrayList<>();
        for (MonedasDisponibles moneda : MonedasDisponibles.values()){
            if (base.equals(moneda.name())) {
                continue;
            }
            monedasDisponibles.add(moneda);
        }
        return monedasDisponibles;
    }

    private void conversion () {
        Scanner sc = new Scanner(System.in);
        System.out.println("********* Ingrese el monto a Convertir *********");
        try {
            int monto = sc.nextInt();
            ConversorDeMoneda conversor = new ConversorDeMoneda();
            conversor.convertir(base,target,monto);
        } catch (InputMismatchException e){
            System.out.println("Por favor ingrese un numero valido");
            conversion();
        }
    }



}
