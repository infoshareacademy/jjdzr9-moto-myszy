package com.isa.jjdzr.console;

import java.util.List;

public class Printer implements Printable {
    @Override
    public void printActualLine(String line) {
        System.out.println(line);
    }

    @Override
    public void printIncomingCash(String cash) {
        printActualLine("Portfel zasilony kwotą: " + cash + "PLN");
    }

    @Override
    public void printError(String error) {
        System.err.println(error);
    }

    @Override
    public void printMenuOptions(List<String> options) {
        options.forEach(this::printActualLine);
    }

    @Override
    public void printEmptyLine() {
        printActualLine("");
    }

    @Override
    public void printDontCreateMessage() {
        printActualLine("Portfel nie zostanie utworzony.");
        printActualLine("Powrót do menu głównego");
    }
}
