package com.isa.jjdzr.console;

import java.util.List;

public interface Printable {
    void printActualLine(String line);
    void printIncomingCash(String cash);
    void printError(String error);
    void printMenuOptions(List<String> options);
    void printEmptyLine();
    void printDontCreateMessage();
}
