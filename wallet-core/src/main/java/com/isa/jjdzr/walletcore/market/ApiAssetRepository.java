package com.isa.jjdzr.walletcore.market;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.market.api.ApiUTIL;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ApiAssetRepository implements AssetRepository {

    private List<Asset> cryptoRates;


    public ApiAssetRepository() {
        prepareCryptoRates();
    }

    @Override
    public List<Asset> retrieveAssets() {
        return cryptoRates;
    }

    @Override
    public Asset findById(String id) {

        return cryptoRates.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(new Asset("Złe id", "Złe id", new BigDecimal(0)));
    }

    private Asset getCryptoRate(String cryptoCode, String currencyCode, String apiKey) {
//        https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency=PLN&apikey=4B32OSF9BSXQXZ8W
        String url = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
                +cryptoCode+"&to_currency=" + currencyCode + "&apikey=" + apiKey;

        try {
            String json = ApiUTIL.getJsonFromUrl(url);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject cryptoRate = jsonObject.getJSONObject("Realtime Currency Exchange Rate");

            Asset result = new Asset();
            result.setId(cryptoRate.getString("1. From_Currency Code"));
            result.setName(cryptoRate.getString("2. From_Currency Name"));
            result.setCurrentPrice(cryptoRate.getBigDecimal("5. Exchange Rate"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Asset();
        }

    }

    private void prepareCryptoRates() {
        cryptoRates = new ArrayList<>();
        List<String> cryptoCodes = List.of("BTC");

        //TODO: add this codes to cryptoCodes list after getting better API key
        //"ETH", "USDT", "BNB", "BUSD", "ADA", "SOL",
        //                "DOGE", "DOT", "SHIB", "AVAX", "LTC", "XLM", "BCH"

        String apiKey = ApiUTIL.getApiKeyFromFile();
        for (String code: cryptoCodes) {
            cryptoRates.add(getCryptoRate(code, "PLN", apiKey));
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }






}
