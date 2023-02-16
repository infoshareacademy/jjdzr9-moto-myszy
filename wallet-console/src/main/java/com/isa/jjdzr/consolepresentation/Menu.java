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
                        clearScreen();
                        wallet = new WalletGenView().start(wallet);
                    } else {
                        System.err.println("Masz już wczytany portfel, nie możesz utworzyć nowego.");
                    }
                    cont();
                    clearScreen();
                    break;
                case 2: clearScreen(); wallet = new LoadViewer().load(); cont(); break;
                case 3:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można zapisać nieistniejącego portfela!");
                    } else {
                        new SaveViewer().save(wallet);
                    }
                    cont();
                    clearScreen();
                    break;
                case 4:
                    if (isWalletNull(wallet)) {
                        System.err.println("Brak portfela do wyświetlenia. Wczytaj lub utwórz nowy.");
                    } else {
                        clearScreen();
                        new WalletViewer().startViewer(wallet);
                    }
                    cont();
                    clearScreen();
                    break;
                case 5:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        clearScreen();
                        new Broker().buy(wallet);
                    }
                    cont();
                    clearScreen();
                    break;
                case 6:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        clearScreen();
                        new Broker().sell(wallet);
                    }
                    cont();
                    clearScreen();
                    break;
                case 7:
                    if (isWalletNull(wallet)) {
                        System.err.println("Nie można wykonać operacji na nieistniejącym portfelu!");
                    } else {
                        clearScreen();
                        new WalletGenView().addCash(wallet);
                    }
                    cont();
                    clearScreen();
                    break;
                case 8: clearScreen(); new ApiNbp().printExchangeRates(); cont(); clearScreen(); break;
                case 9: keepWorking = false; break;
                default:
                    break;
            }
        }
        clearScreen();
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
                if (num > 9 || num < 1) {
                    System.err.println("Nie ma takiej opcji, spróbuj ponownie: ");
                    num = null;
                }
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

    private void cont() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Aby kontynuować naciśnij ENTER");
        scan.nextLine();
    }

    private void clearScreen() {
        System.out.print("\033\143");
        System.out.flush();
    }

}