package model;

import controller.TableroController;
import model.*;
import view.MenuPrincipal;

public class  Test {
    static void main(String[] args) {
        TableroController controller = new TableroController(new MenuPrincipal());
        controller.menuFuncional();
    }
}

