package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.api.ApiNbp;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.market.Market;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String[] options = {
            "1. Utwórz portfel.",
            "2. Wczytaj portfel",
            "3. Zapisz portfel",
            "4. Wyświetl portfel",
            "5. Dokonaj zakupu",
            "6. Dokonaj sprzedaży",
            "7. Dodaj gotówkę",
            "8. Kursy walut",
            "9. Zakończ"
    };

    public Menu() {
    }

    public void startMenu() {
        boolean keepWorking = true;
        Wallet wallet = null;
        Market market = new Market();

        System.out.println("Witamy w portfelu inwestycyjnym !!!");

        while(keepWorking) {
            System.out.println("\nUżytkownik: Guest\n"); //bedzie wyświetlać nazwę użytkownika
            printMenuOptions();
            int option = getOptionNumber();
            switch(option) {
                case 1:
                    if (isWalletNull(wallet)) {
                        wallet = new WalletGenView().start(wallet);
                    } else {
                        System.err.println("Masz już wczytany portfel, nie możesz utworzyć nowego.");
                    }
                    break;
                case 2: wallet = new LoadViewer().load(); break;
                case 3:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można zapisać nieistniejącego portfela!");
                    } else {
                        new SaveViewer().save(wallet);
                    }
                    break;
                case 4:
                    if (isWalletNull(wallet)) {
                        System.err.println("Brak portfela do wyświetlenia. Wczytaj lub utwórz nowy.");
                    } else {
                        new WalletViewer().startViewer(wallet);
                    }
                    break;
                case 5,6:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        printOption(option);
                    }
                    break;
                case 7:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        new WalletGenView().addCash(wallet);
                    }
                    break;
                case 8: new ApiNbp().printExchangeRates(); break;
                case 9: keepWorking = false; break;
                default:
                    System.out.println("Nie ma takiej opcji.");
            }
        }
        System.out.println("Do widzenia!!!");
    }

    private void printMenuOptions() {
        Arrays.asList(options).forEach(System.out::println);
    }

    private int getOptionNumber() {
        Scanner scan = new Scanner(System.in);
        Integer num = null;
        System.out.println("Podaj opcję, którą chcesz wybrać: ");
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

    private void printOption(int index) {
        Scanner scan = new Scanner(System.in);
        System.out.println(options[index-1]);
        System.out.println("Funkcja w produkcji. Aby kontynuować naciśnij ENTER");
        scan.nextLine();
    }

    private boolean isWalletNull(Wallet wallet) {
        return wallet == null;
    }

}