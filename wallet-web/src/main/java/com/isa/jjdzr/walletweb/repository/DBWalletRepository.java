package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.entity.WalletEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DBWalletRepository extends ListCrudRepository<WalletEntity, Long> {

    WalletEntity save(WalletEntity wallet);
    WalletEntity findWalletEntityById(Long id);
    List<WalletEntity> findAll();
    void deleteWalletEntityById(Long id);
}
