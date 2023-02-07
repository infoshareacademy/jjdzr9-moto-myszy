package com.isa.jjdzr.assets;

import com.isa.jjdzr.dto.Asset;

import java.util.List;

public class Market {

    private final AssetRepository repository;

    public Market() {
        this.repository = new FileAssetRepository();
    }

    public List<Asset> availableAssets(){
        return repository.retrieveAssets();
    }
//metoda generująca listę assetow z narzuconej tablicy nazw i dodajace losowe ceny z zakresu
    //metoda aktualizująca ceny o +/- 5% (z wykorzystaniem randoma)


}
