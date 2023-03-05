package com.isa.jjdzr.walletweb.hellowrld;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

//@Data
@Component
public class AssetService {

    public String returnFirstAsset() {
        return "returned Asset";
    }

    public List<String> returnAll() {
        return List.of("aa","bb");
    }
}
