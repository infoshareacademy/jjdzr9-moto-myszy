package com.isa.jjdzr.market;

import com.isa.jjdzr.dto.Asset;

import java.util.List;

interface AssetRepository {
    List<Asset> retrieveAssets();
}
