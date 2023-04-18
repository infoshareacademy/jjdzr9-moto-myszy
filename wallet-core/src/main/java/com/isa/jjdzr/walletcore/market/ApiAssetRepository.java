package com.isa.jjdzr.walletcore.market;

import com.isa.jjdzr.walletcore.dto.Asset;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiAssetRepository implements AssetRepository {

    private List<Asset> cryptoRates;

    @Override
    public List<Asset> retrieveAssets() {
        return null;
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
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Asset();
        }

    }

    private void prepareCryptoRates() {
        cryptoRates = new ArrayList<>();
        List<String> cryptoCodes = new ArrayList<>();

        for (String code: cryptoCodes) {
            cryptoRates.add(getCryptoRate(code, "PLN", "4B32OSF9BSXQXZ8W"));
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
