import controller.TableroController;
import view.MenuPrincipal;

public class Main {
    static void main(String[] args) {
            TableroController controller = new TableroController(new MenuPrincipal());
            controller.menuFuncional();
    }
}
