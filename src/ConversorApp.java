import Menu.impl.Menu;
import com.google.gson.Gson;
import Enum.MonedasDisponibles;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;


public class ConversorApp {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}