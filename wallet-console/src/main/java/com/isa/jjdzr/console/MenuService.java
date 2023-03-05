package com.isa.jjdzr.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuService implements Service{
    private Printable printer = new Printer();
    @Override
    public void cont() {
        Scanner scan = new Scanner(System.in);
        printer.printActualLine("Aby kontynuować naciśnij ENTER");
        scan.nextLine();
    }

    @Override
    public void clearScreen() {
        printer.printActualLine("\033\143");
        System.out.flush();
    }

    @Override
    public int getMenuOption(int optionsSize) {
        Scanner scan = new Scanner(System.in);
        Integer menuOption = null;
        printer.printActualLine("Podaj opcję, którą chcesz wybrać: ");
        while (menuOption == null) {
            try {
                menuOption = scan.nextInt();
                if (menuOption > optionsSize || menuOption < 1) {
                    printer.printError("Nie ma takiej opcji, spróbuj ponownie: ");
                    menuOption = null;
                }
            } catch (InputMismatchException e) {
                printer.printError("Zła wartość, spróbuj ponownie: ");
                scan.next();
            }
        }
        return menuOption;
    }


}
