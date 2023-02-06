package com.isa.jjdzr;

import com.isa.jjdzr.assets.Asset;
import com.isa.jjdzr.menu.WalletGenerator;
import com.isa.jjdzr.wallet.Wallet;

public class App
{
    public static void main( String[] args )
    {
        Wallet wallet;
        WalletGenerator wg = new WalletGenerator();
        wallet = wg.generateWallet();
    }
}
