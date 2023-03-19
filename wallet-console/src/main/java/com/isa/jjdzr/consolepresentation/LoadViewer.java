package com.isa.jjdzr.consolepresentation;

import com.isa.jjdzr.console.Printable;
import com.isa.jjdzr.console.Printer;
import com.isa.jjdzr.datareadandwrite.WalletLoader;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.service.WalletService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class LoadViewer {
    private final Printable printer;
    private final WalletService walletService;
    private final Scanner scan;

    public LoadViewer() {
        this.printer = new Printer();
        this.walletService = new WalletService();
        this.scan = new Scanner(System.in);
    }

    public Long load(Long userId) {
        printer.printActualLine("Lista portfeli do wczytania: ");
        printUserWallets(userId);
        int walletSize = walletService.getUsersWallets(userId).size();
        return getWalletId(walletSize);

    }

    private Long getWalletId(int size) {
        printer.printActualLine("Podaj nr portfela: ");
        Long walletId = null;
        while(isNull(walletId)) {
            try {
                walletId = scan.nextLong()-1L;
                if (walletId < 0 || walletId > size ) {
                    printer.printError("Zła wartość, spróbuj ponownie");
                    walletId = null;
                }
            } catch (InputMismatchException e) {
                printer.printError("Zła wartość, spróbuj ponownie");
            }
        }
        return walletId;
    }

    //TODO: put this in another class (mb printer ?)
    private void printUserWallets(Long userId) {
        int i = 1;
        List<Wallet> wallets = walletService.getUsersWallets(userId);
        for (Wallet w : wallets) {
            printer.printActualLine(i++ + ". " + w.getWalletName());
        }
    }
}
