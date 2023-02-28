package com.isa.jjdzr.console;

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
}
