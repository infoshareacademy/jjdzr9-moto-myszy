package com.isa.jjdzr.console;

import java.util.Arrays;
import java.util.List;

public class Printer implements Printable{
    @Override
    public void printActualLine(String line){
        System.out.println(line);
    }
    @Override
    public void printIncomingCash(String cash) {
        System.out.println("Portfel zasilony kwotą: " + cash + "PLN");
    }

    @Override
    public void printError(String error) {
        System.err.println(error);
    }
    @Override
    public void printMenuOptions(List<String> options) {
        options.forEach(this::printActualLine);
    }
}
