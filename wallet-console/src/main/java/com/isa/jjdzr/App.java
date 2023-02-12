package com.isa.jjdzr;

import com.isa.jjdzr.consolepresentation.Menu;
import com.isa.jjdzr.datareadandwrite.WalletLoader;
import com.isa.jjdzr.datareadandwrite.WalletSaver;
import com.isa.jjdzr.dto.Asset;
import com.isa.jjdzr.dto.Wallet;
import com.isa.jjdzr.dto.WalletAsset;

public class App
{
    public static void main( String[] args )
    {
/*        Wallet wallet = new Wallet();
        Asset ass1 = new Asset("ass1", "200");
        Asset ass2 = new Asset("ass2", "120");
        Asset ass3 = new Asset("ass3", "212");
        WalletAsset ws1 = new WalletAsset(ass1,"100");
        WalletAsset ws2 = new WalletAsset(ass2,"100");
        WalletAsset ws3 = new WalletAsset(ass3,"100");
        wallet.addAsset(ws1);
        wallet.addAsset(ws2);
        wallet.addAsset(ws3);
        wallet.addCash("10000");
        new WalletSaver().saveWallet(wallet);
        Wallet wallet2 = new WalletLoader().loadWallet();*/
        new Menu().startMenu();
    }
}
