package Menu;

import java.util.List;
import Enum.MonedasDisponibles;

public interface IMenu {
    void start();

    void printMenuPrincipal();

    void printMenuSecondary(List<MonedasDisponibles> monedasDisponibles);
}
