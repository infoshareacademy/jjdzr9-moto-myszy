package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletweb.entity.WalletAssetEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BDWalletAssetRepository extends ListCrudRepository<WalletAssetEntity, Long> {

    WalletAssetEntity save(WalletAssetEntity walletAsset);
    WalletAssetEntity findWalletAssetEntityById(Long id);
    List<WalletAssetEntity> findAll();
    void deleteWalletAssetEntityById(Long id);
}
