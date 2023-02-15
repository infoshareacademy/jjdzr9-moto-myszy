package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class WalletViewer {
    private final String[] options = { //Maciek
            "1. Wyświetl listę posiadanych aktyw",
            "2. Wyświetl wybrane aktywa",
            "3. Wyświetl wszystkie aktywa",
            "4. Zakończ"
    };

    public void startViewer() {
        boolean keepWorking = true;

        while(keepWorking) {
            printOptions();
            int option = getOptionNumber();
            switch(option) {
                case 1,2,3: printOption(option); break;
                case 4: keepWorking = false; break;
                default:
                    System.out.println("Nie ma takiej opcji.");
            }
        }
    }


    private void printOptions() {
        Arrays.asList(options).forEach(option -> {
            System.out.println(option);
        });
    }



    private int getOptionNumber() {
        Scanner scan = new Scanner(System.in);
        Integer num = null;
        System.out.println("Podaj opcje: ");
        while (num == null) {
            try {
                num = scan.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Zła wartość, spróbuj ponownie: ");
                scan.next();
            }
        }
        return num;
    }

    private void printAssetList(){
        System.out.println("Lista posiadanych aktyw");
    }

    private void printSingleAsset(){
        System.out.println("Wybrane aktywa");
    }

    private void printAllAssets(){
        System.out.println("Wszystkie aktywa");
    }

    private void printOption(int option) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case 1:
                printAssetList();
                System.out.println("Aby wyjść wciśnij ENTER");
                scan.nextLine();
                break;
            case 2:
                printSingleAsset();
                System.out.println("Aby wyjść wciśnij ENTER");
                scan.nextLine();
                break;
            case 3:
                printAllAssets();
                System.out.println("Aby wyjść wciśnij ENTER");
                scan.nextLine();
                break;
            default:
                System.out.println("Zła wartość, spróbuj ponownie: ");
                break;
        }
    }
}
