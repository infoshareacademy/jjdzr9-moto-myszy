package com.isa.jjdzr.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiNbp {
    // Adres URL dla API NBP
    private static final String URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    // Metoda do pobierania danych z API NBP i wypisywania informacji o kursach walut
    public void printExchangeRates() {
        try {
            // Pobierz dane z API NBP
            String json = getJsonFromUrl(URL);

            // Parsuj dane z JSON do obiektu JSON
            JSONArray jsonArray = new JSONArray(json);

            // Pobierz tablicę z kursami walut
            JSONArray ratesArray = jsonArray.getJSONObject(0).getJSONArray("rates");

            // Wypisz informacje o kursach walut
            for (int i = 0; i < ratesArray.length(); i++) {
                JSONObject rate = ratesArray.getJSONObject(i);
                String currency = rate.getString("currency");
                String code = rate.getString("code");
                double mid = rate.getDouble("mid");
                System.out.printf("%s %s: %.4f%n", code, currency, mid);
                countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metoda do pobierania danych z API NBP
    private String getJsonFromUrl(String url) {
        String json = "";
        try {
            // Utwórz obiekt URL na podstawie adresu URL
            URL apiUrl = new URL(url);

            // Otwórz połączenie HTTP z serwerem
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Odczytaj dane z połączenia HTTP
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            // Zamknij połączenie i czytelnik
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Zwróć dane w formacie JSON jako ciąg znaków
        return json;
    }

    private void countDown() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
