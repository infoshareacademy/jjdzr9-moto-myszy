package com.isa.jjdzr.assets;

import com.isa.jjdzr.dto.Asset;

import java.util.List;

interface AssetRepository {
    List<Asset> retrieveAssets();
}
