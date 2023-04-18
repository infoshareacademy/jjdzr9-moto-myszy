package com.isa.jjdzr.walletcore.market;

import com.isa.jjdzr.walletcore.dto.Asset;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            String json = getJsonFromUrl(url);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject cryptoRate = jsonObject.getJSONObject("Realtime Currency Exchange Rate");

            Asset result = new Asset();
            result.setId(cryptoRate.getString("1. From_Currency Code"));
            result.setName(cryptoRate.getString("2. From_Currency Name"));
            result.setCurrentPrice(cryptoRate.getBigDecimal("5. Exchange Rate"));
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Asset();
        }

    }

    private void prepareCryptoRates() {
        cryptoRates = new ArrayList<>();
        List<String> cryptoCodes = List.of("BTC");

        //"ETH", "USDT", "BNB", "BUSD", "ADA", "SOL",
        //                "DOGE", "DOT", "SHIB", "AVAX", "LTC", "XLM", "BCH"


        for (String code: cryptoCodes) {
            cryptoRates.add(getCryptoRate(code, "PLN", "4B32OSF9BSXQXZ8W"));
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //TODO: put this in some UTIL class
    private String getJsonFromUrl(String url) {
        String json = "";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }


}
