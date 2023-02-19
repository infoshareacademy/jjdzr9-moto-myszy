package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.dto.Wallet;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class WalletViewer {
    private final String[] options = { //Maciek
            "1. Wyświetl listę posiadanych aktyw",
            "2. Wyświetl wybrane aktywa",
            "3. Wyświetl wszystkie aktywa",
            "4. Wyświetl gotówkę",
            "5. Powród do menu"
    };

    public void startViewer(Wallet wallet) {
        boolean keepWorking = true;

        while(keepWorking) {
            printOptions();
            int option = getOptionNumber();
            switch(option) {
                case 1: new AssetsViewer().printWalletList(wallet.getWallet()); break;
                case 2: printSingleWalletAsset(wallet); break;
                case 3: new AssetsViewer().printWallet(wallet.getWallet()); break;
                case 4:
                    System.out.println("Posiadana gotówka: " + wallet.getCash() + "PLN"); break;
                case 5: keepWorking = false; break;
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

    private void printSingleWalletAsset(Wallet wallet) {
        Scanner scan = new Scanner(System.in);
        if (wallet.getWallet().size() == 0) {
            System.out.println("Brak aktyw do wyświetlenia");
        } else {
            int index = -1;
            System.out.println("Który aktyw chcesz zobaczyć ?");

            while (checkWAIndex(wallet, index)) {
                try {
                    System.out.println("Podaj wartość od 1 do " + wallet.getWallet().size());
                    index = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Zła wartość, spróbuj ponownie.");
                    scan.next();
                }
            }
            new AssetsViewer().printWalletAsset(wallet.getWallet().get(index - 1));
        }
    }

    private boolean checkWAIndex (Wallet wallet, int i) {
        return !Pattern.matches("[1-" + wallet.getWallet().size() +"]", String.valueOf(i));
    }

}
