package com.isa.jjdzr.walletcore.repositories;

import com.isa.jjdzr.walletcore.dto.Wallet;

import java.util.List;

public interface WalletRepository {

    Wallet save(Wallet wallet);
    Wallet find(Long id);
    List<Wallet> getAll();
    void delete(Long id);
}
