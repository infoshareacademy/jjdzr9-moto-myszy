package com.isa.jjdzr.walletcore.market;


import com.isa.jjdzr.walletcore.dto.Asset;

import java.util.List;

interface AssetRepository {
    List<Asset> retrieveAssets();

    Asset findById(String id);
}
