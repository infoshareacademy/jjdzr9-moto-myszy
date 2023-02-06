package com.isa.jjdzr.menu;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String[] options = {
            "1. Utwórz portfel.",
            "2. Wczytaj portfel",
            "3. Wyświetl portfel",
            "4. Dokonaj zakupu",
            "5. Dokonaj sprzedaży",
            "6. Zakończ"
    };

    public Menu() {
    }

    public void startMenu() {
        boolean keepWorking = true;

        System.out.println("Witamy w portfelu inwestycyjnym !!!");

        while(keepWorking) {
            System.out.println("\nUżytkownik: Guest"); //bedzie wyświetlać nazwę użytkownika
            printMenuOptions();
            int option = getOptionNumber();
            switch(option) {
                case 1,2,3,4,5: printOption(option); break;
                case 6: keepWorking = false; break;
                default:
                    System.out.println("Nie ma takiej opcji.");
            }
        }
        System.out.println("Do widzenia!!!");
    }

    private void printMenuOptions() {
        Arrays.asList(options).forEach(option -> {
            System.out.println(option);
        });
    }

    private int getOptionNumber() {
        Scanner scan = new Scanner(System.in);
        Integer num = null;
        System.out.println("Podaj opcję, którą chcesz wybrać: ");
        while (num == null) {
            try {
                num = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Zła wartość, spróbuj ponownie: ");
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

}