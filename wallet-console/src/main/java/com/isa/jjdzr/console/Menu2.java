package com.isa.jjdzr.console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Menu2 implements Printable{
    @Override
    public void printActualLine(String line) {
        System.out.println("---- "+line);
    }

    @Override
    public void printIncomindCash(String cash) {


        Path path = Paths.get("fileName");
        byte[] strToBytes = cash.getBytes();

        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printError(String error) {

    }


}
